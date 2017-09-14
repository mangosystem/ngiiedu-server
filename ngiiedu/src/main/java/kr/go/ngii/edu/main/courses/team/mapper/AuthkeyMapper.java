package kr.go.ngii.edu.main.courses.team.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.courses.team.model.Authkey;

@Mapper
public interface AuthkeyMapper {
	
	public Authkey get(Authkey authkey);

	public List<Authkey> list(Authkey authkey);

	public int create(Authkey authkey);

	public void modify(Authkey authkey);

	public void delete(int idx);
	
}
