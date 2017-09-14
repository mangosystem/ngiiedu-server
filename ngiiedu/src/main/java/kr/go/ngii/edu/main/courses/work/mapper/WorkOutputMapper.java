package kr.go.ngii.edu.main.courses.work.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.courses.work.model.WorkOutput;

@Mapper
public interface WorkOutputMapper {
	
	public WorkOutput get(WorkOutput workoutput);

	public List<WorkOutput> list(WorkOutput workoutput);

	public int create(WorkOutput workoutput);

	public void modify(WorkOutput workoutput);

	public void delete(int idx);
	
}
