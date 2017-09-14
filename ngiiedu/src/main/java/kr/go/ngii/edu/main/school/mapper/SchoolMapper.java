package kr.go.ngii.edu.main.school.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.school.model.School;

@Mapper
public interface SchoolMapper {

	public School get(School school);

	public List<School> list(School school);

	public int create(School school);

	public void modify(School school);

	public void delete(int idx);

}
