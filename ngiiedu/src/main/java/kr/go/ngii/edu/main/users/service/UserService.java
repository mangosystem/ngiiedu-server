package kr.go.ngii.edu.main.users.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

	public List<User> list(int offset, int limit, String keyword) {
		return userMapper.list(offset, limit, keyword);
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
//		int pngoAuthKeyCreatedResult = pngoAuthKeyMapper.create(pngoUserIdx, UUID.randomUUID().toString().replaceAll("-", ""));
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
		
		return userMapper.get(param);
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
	public User modify(String userid, String userName, String password, boolean userState) {
		User param = get(userid);
		
		param.setPassword(password);
		param.setUserName(userName);
		param.setUserState(userState);
		
		userMapper.modify(param);
		
		return param;
	}
	
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
