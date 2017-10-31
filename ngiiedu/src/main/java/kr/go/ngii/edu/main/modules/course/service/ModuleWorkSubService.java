package kr.go.ngii.edu.main.modules.course.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.main.modules.course.mapper.ModuleWorkMapper;
import kr.go.ngii.edu.main.modules.course.mapper.ModuleWorkSubMapper;
import kr.go.ngii.edu.main.modules.course.model.ModuleWork;
import kr.go.ngii.edu.main.modules.course.model.ModuleWorkAndSub;
import kr.go.ngii.edu.main.modules.course.model.ModuleWorkSub;

@Service
public class ModuleWorkSubService {

	@Autowired
	private ModuleWorkSubMapper moduleWorkSubMapper;


	public List<ModuleWorkSub> list(int moduleWorkId) {
		return moduleWorkSubMapper.list(moduleWorkId);
	}
	
	public List<ModuleWorkAndSub> moduleWorkAndSubList(int moduleId) {
		return moduleWorkSubMapper.moduleWorkAndSubList(moduleId);
	}

	public ModuleWorkSub get(int moduleWorkSubId) {
		ModuleWorkSub moduleWorkSub = new ModuleWorkSub();
		moduleWorkSub.setIdx(moduleWorkSubId);
		return moduleWorkSubMapper.get(moduleWorkSub);
	}

	public ModuleWorkSub create(int moduleWorkId, String moduleWorkSubName, int moduleWorkSubSeq) {
		ModuleWorkSub param = new ModuleWorkSub();
		param.setModuleWorkId(moduleWorkId);
		param.setModuleWorkSubName(moduleWorkSubName);
		param.setModuleWorkSubSeq(moduleWorkSubSeq);
		param.setCreateDate(new Date());
		param.setModifyDate(new Date());

		moduleWorkSubMapper.create(param);

		return param;
	}

	public ModuleWorkSub modify(int moduleWorkSubId, int moduleWorkId, String moduleWorkSubName, 
			int moduleWorkSubSeq) {
		ModuleWorkSub param = new ModuleWorkSub();
		param.setIdx(moduleWorkSubId);
		param.setModuleWorkId(moduleWorkId);
		param.setModuleWorkSubName(moduleWorkSubName);
		param.setModuleWorkSubSeq(moduleWorkSubSeq);
		param.setModifyDate(new Date());

		moduleWorkSubMapper.modify(param);

		if (param.getIdx()!=null) {
			param = get(moduleWorkSubId);
		}

		return param;
	}

	public boolean delete(int moduleWorkSubId) {
		if (get(moduleWorkSubId)!=null) {
			ModuleWorkSub param = new ModuleWorkSub();
			param.setIdx(moduleWorkSubId);
			moduleWorkSubMapper.delete(param);
			return true;
		} else {
			return false;
		}
	}

}
