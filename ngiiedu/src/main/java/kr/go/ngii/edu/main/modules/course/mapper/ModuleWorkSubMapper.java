package kr.go.ngii.edu.main.modules.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.ngii.edu.main.modules.course.model.ModuleWork;
import kr.go.ngii.edu.main.modules.course.model.ModuleWorkAndSub;
import kr.go.ngii.edu.main.modules.course.model.ModuleWorkSub;

@Mapper
public interface ModuleWorkSubMapper {

	public ModuleWorkSub get(ModuleWorkSub moduleWorkSub);

	public List<ModuleWorkSub> list(@Param("moduleWorkId") int moduleWorkId);

	public void create(ModuleWorkSub moduleWorkSub);

	public void modify(ModuleWorkSub moduleWorkSub);

	public void delete(ModuleWorkSub moduleWorkSub);

	public List<ModuleWorkAndSub> moduleWorkAndSubList(int moduleId);
}

