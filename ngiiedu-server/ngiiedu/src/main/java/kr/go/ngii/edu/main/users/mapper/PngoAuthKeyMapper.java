package kr.go.ngii.edu.main.users.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PngoAuthKeyMapper {

	public int create(@Param("userId") int userId, @Param("apikey") String apikey);
	
	public boolean exists(int userId);

	public String getApikey(int userId);
}
