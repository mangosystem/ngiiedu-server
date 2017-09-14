package kr.go.ngii.edu.main.modules.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.modules.course.model.ModuleWork;


@Mapper
public interface ModuleWorkMapper {
	
	public ModuleWork get(ModuleWork moduleWork);
	
	public List<ModuleWork> list(ModuleWork moduleWork);
	
	public int create(ModuleWork moduleWork);
	
	public void modify(ModuleWork moduleWork);
	
	public void delete(int idx);

}

