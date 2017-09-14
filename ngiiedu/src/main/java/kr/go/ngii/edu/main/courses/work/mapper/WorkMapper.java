package kr.go.ngii.edu.main.courses.work.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.courses.work.model.Work;

@Mapper
public interface WorkMapper {
	
	public Work get(Work work);

	public List<Work> list(Work work);

	public int create(Work work);

	public void modify(Work work);

	public void delete(int idx);

}
