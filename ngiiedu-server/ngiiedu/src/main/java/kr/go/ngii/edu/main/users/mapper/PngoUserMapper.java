package kr.go.ngii.edu.main.users.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.ngii.edu.main.users.model.PngoUser;

@Mapper
public interface PngoUserMapper {

	public PngoUser get(PngoUser user);

	public int create(PngoUser user);

	public void delete(PngoUser userid);

	public void modify(PngoUser user);

	public void modifyUserId(@Param("idx") int idx, @Param("username") String username, @Param("newUsername") String newUsername);

	public Boolean exists(@Param("username") String username);

}
