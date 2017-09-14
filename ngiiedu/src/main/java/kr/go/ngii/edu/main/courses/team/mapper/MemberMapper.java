package kr.go.ngii.edu.main.courses.team.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.courses.team.model.Member;

@Mapper
public interface MemberMapper {
	
	public Member get(Member member);

	public List<Member> list(Member member);

	public int create(Member member);

	public void modify(Member member);

	public void delete(int idx);
	
}
