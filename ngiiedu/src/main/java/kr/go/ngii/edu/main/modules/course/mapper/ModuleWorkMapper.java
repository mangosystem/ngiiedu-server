package kr.go.ngii.edu.main.modules.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.ngii.edu.main.modules.course.model.ModuleWork;

@Mapper
public interface ModuleWorkMapper {

	public ModuleWork get(ModuleWork work);

	public List<ModuleWork> list(@Param("moduleId") int moduleId);

	public void create(ModuleWork work);

	public void modify(ModuleWork work);

	public void delete(@Param("moduleId") int moduleId, @Param("moduleWorkId") int moduleWorkId);
}

