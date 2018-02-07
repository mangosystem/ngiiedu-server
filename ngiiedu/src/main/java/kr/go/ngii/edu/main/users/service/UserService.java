package kr.go.ngii.edu.main.users.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.common.message.ErrorMessage;
import kr.go.ngii.edu.main.users.mapper.PngoAuthKeyMapper;
import kr.go.ngii.edu.main.users.mapper.PngoUserMapper;
import kr.go.ngii.edu.main.users.mapper.UserMapper;
import kr.go.ngii.edu.main.users.mapper.UserRoleMapper;
import kr.go.ngii.edu.main.users.model.PngoUser;
import kr.go.ngii.edu.main.users.model.User;
import kr.go.ngii.edu.main.users.model.UserRole;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoleMapper roleMapper;

	@Autowired
	private PngoUserMapper pngoUserMapper;

	@Autowired
	private PngoAuthKeyMapper pngoAuthKeyMapper;


	public Map<String, Object> list(int offset, int limit, String keyword) {

		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("list", userMapper.list(offset, limit, keyword));
			result.put("count", userMapper.listCount(keyword));
			result.put("offset", offset);
			result.put("limit", limit);

			return result;

		} catch (Exception e) {
			return null;
		}
	}


	public User create(String userid, String password, String userEmail, String userName, String userDivision) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		User param = new User();

		param.setUserid(userid);
		param.setPassword(encoder.encode(password));
		param.setUserEmail(userEmail);
		param.setUserName(userName);
		param.setUserDivision(userDivision);
		userMapper.create(param);

		UserRole role = new UserRole();
		role.setUserId(param.getIdx());
		role.setAuthority("ROLE_USER");
		roleMapper.create(role);

		return param;
	}

	public User create(String userid, String password, String userName, String userDivision) {

		// id 중복체크
		if (this.checkDuplicated(userid)) {
			throw new RuntimeException(ErrorMessage.DUPICATE_ID);
		}

		// 암호화..
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPasswd = encoder.encode(password);

		// pngo_user 추가
		PngoUser pngoUser = new PngoUser();
		pngoUser.setUsername(userid);
		pngoUser.setPasswd(encodedPasswd);
		pngoUser.setFirstName(userName);
		//		int pngoUserCreateResult = pngoUserMapper.create(pngoUser);
		pngoUserMapper.create(pngoUser);

		pngoUser = pngoUserMapper.get(pngoUser);

		int pngoUserIdx = pngoUser.getIdx();

		// user role 추가
		UserRole role = new UserRole();
		role.setUserId(pngoUserIdx);
		role.setAuthority("ROLE_USER");
		roleMapper.create(role);

		// apikey 생성
		//int pngoAuthKeyCreatedResult = pngoAuthKeyMapper.create(pngoUserIdx, UUID.randomUUID().toString().replaceAll("-", ""));
		pngoAuthKeyMapper.create(pngoUserIdx, UUID.randomUUID().toString().replaceAll("-", ""));

		// user
		User param = new User();
		param.setIdx(pngoUserIdx);
		param.setUserid(userid);
		param.setPassword(encodedPasswd);
		param.setUserName(userName);
		// email x
		param.setUserEmail("");
		param.setUserDivision(userDivision);
		userMapper.create(param);

		param.setPassword(null);

		return param;
	}

	public boolean checkDuplicated(String userid) {
		PngoUser pngoUser = new PngoUser();
		pngoUser.setUsername(userid);
		boolean hasPngoUser = pngoUserMapper.exists(userid);
		boolean hasUser = userMapper.exists(userid);

		if (hasPngoUser || hasUser) {
			throw new RuntimeException(ErrorMessage.DUPICATE_ID);
		}
		return false;
	}


	//확인필요
	//	public User modify(String userid, String userName, String password, boolean userState) {
	//		User param = get(userid);
	//		
	//		param.setPassword(password);
	//		param.setUserName(userName);
	//		param.setUserState(userState);
	//		
	//		userMapper.modify(param);
	//		
	//		return param;
	//	}

	/**
	 * 상태변경
	 * 
	 * @param userId
	 * @param userState
	 * @return
	 */
	public boolean modifyState(String userId, boolean userState) {
		try {
			User param = get(userId);
			param.setUserName(null);
			param.setPassword(null);
			param.setUserDivision(null);
			param.setUserState(userState);

			userMapper.modify(param);

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 이름변경
	 * @param userId
	 * @param changeName
	 * @return
	 */
	public boolean modifyUserName(String userId, String changeName) {

		try {
			User user = get(userId);

			if (user == null) {
				return false;
			}

			if (changeName != null && !"".equals(changeName)) {
				user.setUserName(changeName);
				user.setPassword(null);
				user.setUserDivision(null);
				user.setUserState(null);
				userMapper.modify(user);

				PngoUser pngoUser = getPngoUser(userId);
				pngoUser.setUsername(null);
				pngoUser.setPasswd(null);
				pngoUser.setFirstName(changeName);
				pngoUserMapper.modify(pngoUser);

				return true;

			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 패스워드 변경
	 * @param userId
	 * @param oldPasswd
	 * @param newPasswd
	 * @return
	 */
	public boolean modifyPassword(String userId, String oldPasswd, String newPasswd) {
		try {
			User user = get(userId);

			if (user == null) {
				return false;
			}

			if (new BCryptPasswordEncoder().matches(oldPasswd, user.getPassword())) {

				String passwd = new BCryptPasswordEncoder().encode(newPasswd);

				user.setUserName(null);
				user.setPassword(passwd);
				user.setUserDivision(null);
				user.setUserState(null);
				userMapper.modify(user);

				PngoUser pngoUser = getPngoUser(userId);
				pngoUser.setUsername(null);
				pngoUser.setPasswd(passwd);
				pngoUser.setFirstName(null);
				pngoUserMapper.modify(pngoUser);

				return true;

			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}
	
	
//	/**
//	 * 사용자 아이디 변경
//	 * @param userId
//	 * @param newUserId
//	 * @param passwd
//	 * @return
//	 */
//	public boolean modifyUserId(String userId, String newUserId, String passwd) {
//		
//		try {
//			User user = get(userId);
//
//			if (user == null) {
//				return false;
//			} else {
//				if (!"3".equals(user.getUserDivision().trim())) {
//					return false;
//				}
//			}
//
//			if (new BCryptPasswordEncoder().matches(passwd, user.getPassword())) {
//				
//				user.setUserName(null);
//				user.setPassword(passwd);
//				user.setUserDivision(null);
//				user.setUserState(null);
//				userMapper.modify(user);
//
//				PngoUser pngoUser = getPngoUser(userId);
//				pngoUser.setUsername(null);
//				pngoUser.setPasswd(passwd);
//				pngoUser.setFirstName(null);
//				pngoUserMapper.modify(pngoUser);
//				
//				
//				
//				
//			} else {
//				return false;
//			}
//
//		} catch (Exception e) {
//			return false;
//		}
//	}
	

	public User get(String userid) {
		User user = new User();
		user.setUserid(userid);;
		return userMapper.get(user);
	}

	public User get(int idx) {
		User user = new User();
		user.setIdx(idx);
		return userMapper.get(user);
	}

	public User getByEmail(String userEmail) {
		User user = new User();
		user.setUserEmail(userEmail);
		return userMapper.get(user);
	}

	public boolean delete(String userid) {
		if (get(userid) != null) {
			userMapper.delete(userid);
			return true;
		} else {
			return false;
		}
	}

	public PngoUser getPngoUser(String username) {
		PngoUser pngoUser = new PngoUser();
		pngoUser.setUsername(username);
		return pngoUserMapper.get(pngoUser);
	}

	public String getApiKey(int userId) {
		return pngoAuthKeyMapper.getApikey(userId);
	}



}
