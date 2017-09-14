package kr.go.ngii.edu.main.users.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.users.model.User;

@Mapper
public interface UserMapper {

	public User get(User user);
	
	public List<User> list(User user);
	
	public int create(User user);
	
	public void modify(User user);
	
	public void delete(int idx);
	
}
