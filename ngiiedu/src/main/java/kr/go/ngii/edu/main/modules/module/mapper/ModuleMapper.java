package kr.go.ngii.edu.main.modules.module.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.ngii.edu.main.modules.module.model.Module;

@Mapper
public interface ModuleMapper {

	public Module get(Module module);

	public List<Module> list();

	public List<Module> list(@Param("offset") int offset, @Param("limit") int limit);

	public void create(Module module);

	public void modify(Module module);

	public void delete(@Param("idx") int idx);

}
