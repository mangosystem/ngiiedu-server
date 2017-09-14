package kr.go.ngii.edu.main.modules.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.modules.course.model.ModuleWorkData;


@Mapper
public interface ModuleWorkDataMapper {
	
	public ModuleWorkData get(ModuleWorkData moduleWorkData);
	
	public List<ModuleWorkData> list(ModuleWorkData moduleWorkData);
	
	public int create(ModuleWorkData moduleWorkData);
	
	public void modify(ModuleWorkData moduleWorkData);
	
	public void delete(int idx);

}

