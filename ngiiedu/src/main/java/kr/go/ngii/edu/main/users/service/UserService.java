package kr.go.ngii.edu.main.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.main.users.mapper.UserMapper;
import kr.go.ngii.edu.main.users.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	

	public List<User> list(int offset, int limit, String keyword) {
		return userMapper.list(offset, limit, keyword);
	}
	

	public User create(String userid, String password, String userEmail, String userName, String userDivision) {
		User param = new User();
		
		param.setUserid(userid);
		param.setPassword(password);
		param.setUserEmail(userEmail);
		param.setUserName(userName);
		param.setUserDivision(userDivision);
		userMapper.create(param);
		
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
