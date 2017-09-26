package kr.go.ngii.edu.main.school.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.ngii.edu.main.school.model.School;

@Mapper
public interface SchoolMapper {

	public School get(School school);

	public List<School> list();

	public List<School> list(@Param("offset") int offset, @Param("limit") int limit);

	public void create(School school);

	public void modify(School school);

	public void delete(@Param("idx") int idx);

}
