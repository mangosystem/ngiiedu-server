package kr.go.ngii.edu.main.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.main.users.mapper.UserMapper;
import kr.go.ngii.edu.main.users.mapper.UserRoleMapper;
import kr.go.ngii.edu.main.users.model.User;
import kr.go.ngii.edu.main.users.model.UserRole;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRoleMapper roleMapper;
	

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

}
