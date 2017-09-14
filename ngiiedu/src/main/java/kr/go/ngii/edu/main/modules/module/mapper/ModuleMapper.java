package kr.go.ngii.edu.main.modules.module.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.modules.module.model.Module;

@Mapper
public interface ModuleMapper {

	public Module get(Module module);
	
	public List<Module> list(Module module);
	
	public int create(Module module);
	
	public void modify(Module module);
	
	public void delete(int idx);
	
	
}

