package kr.go.ngii.edu.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.sample.model.User;

@Mapper
public interface UserMapper {

	List<User> getUsers();

}
