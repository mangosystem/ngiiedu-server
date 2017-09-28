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
	
/*	public List<User> list(User user) {
		
		User param = new User();
		
		param.setUserid(user.getUserid());
		param.setUserEmail(user.getUserEmail());
		param.setUserName(user.getUserName());
		
		return null;
		return userMapper.list(param);
	}
	
	public List<User> list(int offset, int limit) {
		return null;
		return userMapper.list(offset, limit);
	}*/
	
	public List<User> list(int offset, int limit, String category, String keyword) {
		return userMapper.list(offset, limit, category, keyword);
	}
	
	public User get(User user) {
		return userMapper.get(user);
	}
	
	//확인필요
	public int create(User user) {
		return userMapper.create(user);
	}
	
	//확인필요
	public User modify(User user) {
		User param = new User();
		
		param.setIdx(user.getIdx());
		param.setPassword(user.getPassword());
		param.setUserName(user.getUserName());
		param.setUserState(user.getUserState());
		
		userMapper.modify(param);
		
		return param;
	}
	
	public User get(int idx) {
		User user = new User();
		user.setIdx(idx);
		return userMapper.get(user);
	}
	
	public boolean delete(int idx) {
		if (get(idx) != null) {
			userMapper.delete(idx);
			return true;
		} else {
			return false;
		}
	}

}
