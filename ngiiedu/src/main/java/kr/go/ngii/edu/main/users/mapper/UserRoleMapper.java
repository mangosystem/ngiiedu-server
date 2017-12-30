package kr.go.ngii.edu.main.users.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.ngii.edu.main.users.model.UserRole;

@Mapper
public interface UserRoleMapper {
	
	public List<UserRole> list(int userIdx);

	public void create(UserRole userRole);
	
	public Boolean exists(@Param("userid") int userid);

}
