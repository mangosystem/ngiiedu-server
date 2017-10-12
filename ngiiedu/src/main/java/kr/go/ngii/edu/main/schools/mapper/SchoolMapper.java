package kr.go.ngii.edu.main.schools.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.ngii.edu.main.schools.model.School;

@Mapper
public interface SchoolMapper {

	public School get(School school);

/*	public List<School> list();*/

	public List<School> list(@Param("offset") int offset, @Param("limit") int limit,
			@Param("category") String category, @Param("keyword") String keyword, @Param("schoolLevel") String schoolLevel);

	public void create(School school);

	public void modify(School school);

	public void delete(@Param("idx") int idx);
	
	public int count();
	
	//api 동기화
	public void createAPI(School school);
	
	//인증키 중복 확인
	public boolean exists(String authkey);

	public void modifyAuthkey(School school);

	public String getAuthkey(@Param("idx") int idx);
	

}
