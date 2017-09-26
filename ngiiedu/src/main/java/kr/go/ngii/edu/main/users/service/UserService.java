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
	
	public List<User> list(User user) {
		return userMapper.list(user);
	}
	
	public List<User> list(int offset, int limit) {
		return userMapper.list(offset, limit);
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
