package kr.go.ngii.edu.main.modules.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.modules.course.model.ModuleWorkComp;


@Mapper
public interface ModuleWorkCompMapper {
	
	public ModuleWorkComp get(ModuleWorkComp moduleWorkData);
	
	public List<ModuleWorkComp> list(ModuleWorkComp moduleWorkData);
	
	public int create(ModuleWorkComp moduleWorkData);
	
	public void modify(ModuleWorkComp moduleWorkData);
	
	public void delete(int idx);

}

