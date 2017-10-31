package kr.go.ngii.edu.main.modules.course.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.main.modules.course.mapper.ModuleWorkMapper;
import kr.go.ngii.edu.main.modules.course.model.ModuleWork;
import kr.go.ngii.edu.main.modules.course.model.ModuleWorkAndSub;
import kr.go.ngii.edu.main.modules.course.model.ModuleWorkSub;

@Service
public class ModuleWorkService {

	@Autowired
	private ModuleWorkMapper workMapper;


	public List<ModuleWork> list(int moduleId) {
		return workMapper.list(moduleId);
	}

	public ModuleWork get(int moduleId, int moduleWorkId) {
		ModuleWork module = new ModuleWork();
		module.setModuleId(moduleId);
		module.setIdx(moduleWorkId);
		
		return workMapper.get(module);
	}

	public ModuleWork create(int moduleId, String moduleWorkName, String moduleWorkCourseType, String moduleWorkMetadata) {
		ModuleWork param = new ModuleWork();
		param.setModuleId(moduleId);
		param.setModuleWorkName(moduleWorkName);
//		param.setModuleWorkSeq(moduleWorkSeq);
		param.setModuleWorkCourseType(moduleWorkCourseType);
		param.setModuleWorkMetadata(moduleWorkMetadata);
		param.setCreateDate(new Date());
		param.setModifyDate(new Date());

		workMapper.create(param);

		return param;
	}

	public ModuleWork modify(int moduleId, int moduleWorkId, String moduleWorkName, String moduleWorkMetadata) {
		ModuleWork param = new ModuleWork();
		param.setIdx(moduleWorkId);
		param.setModuleId(moduleId);
		param.setModuleWorkName(moduleWorkName);
		param.setModuleWorkMetadata(moduleWorkMetadata);
		param.setModifyDate(new Date());

		workMapper.modify(param);

		if (param.getIdx()!=null) {
			param = get(moduleId, moduleWorkId);
		}

		return param;
	}

	public boolean delete(int moduleId, int moduleWorkId) {
		if (get(moduleId, moduleWorkId)!=null) {
			workMapper.delete(moduleId, moduleWorkId);
			return true;
		} else {
			return false;
		}
	}
}
