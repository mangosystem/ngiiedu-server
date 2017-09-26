package kr.go.ngii.edu.main.courses.team.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.courses.team.model.Authkey;

@Mapper
public interface AuthkeyMapper {
	
	public Authkey get(Authkey authkey);

	public int create(Authkey authkey);

	public void delete(String authkey);
	
}
