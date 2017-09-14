package kr.go.ngii.edu.main.courses.team.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.courses.team.model.Team;

@Mapper
public interface TeamMapper {
	
	public Team get(Team team);

	public List<Team> list(Team team);

	public int create(Team team);

	public void modify(Team team);

	public void delete(int idx);
	
}
