package kr.go.ngii.edu.main.courses.work.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.courses.work.model.Work;

@Mapper
public interface WorkMapper {

	public void create(Work work);

}
