package kr.go.ngii.edu.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.sample.mapper.UserMapper;
import kr.go.ngii.edu.sample.model.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public List<User> getList() {
		return userMapper.getUsers();
	}

}
