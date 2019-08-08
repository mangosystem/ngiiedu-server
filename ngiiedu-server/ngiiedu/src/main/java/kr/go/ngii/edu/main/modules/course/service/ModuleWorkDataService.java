package kr.go.ngii.edu.main.modules.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.main.modules.course.mapper.ModuleWorkDataMapper;
import kr.go.ngii.edu.main.modules.course.model.ModuleWorkData;


@Service
public class ModuleWorkDataService {
	
	@Autowired
	private ModuleWorkDataMapper moduleWorkDataMapper;

	public ModuleWorkData get(int moduleId) {
		ModuleWorkData param = new ModuleWorkData();
		param.setModuleId(moduleId);
		return moduleWorkDataMapper.get(param);
	}
	
	public List<ModuleWorkData> list(int moduleId) {
		ModuleWorkData param = new ModuleWorkData();
		param.setModuleId(moduleId);
		return moduleWorkDataMapper.list(param);
	}
	

	public ModuleWorkData create(int moduleId, String moduleWorkName, String moduleWorkDataName, 
			String moduleWorkDataPath, String moduleWorkDataDivision, String moduleWorkSeq) {
		ModuleWorkData param = new ModuleWorkData();
		param.setModuleId(moduleId);
		param.setModuleWorkOutputName(moduleWorkName);
		param.setModuleWorkOutputPath(moduleWorkDataPath);
		param.setModuleWorkOutputName(moduleWorkDataDivision);
		param.setModuleWorkSeq(moduleWorkSeq);
		moduleWorkDataMapper.create(param);
		return param;
	}

	public ModuleWorkData modify(int moduleWorkDataId, String moduleWorkName, String moduleWorkDataName, 
			String moduleWorkDataPath, String moduleWorkDataDivision, String moduleWorkSeq) {
		ModuleWorkData param = new ModuleWorkData();
		param.setIdx(moduleWorkDataId);
		param.setModuleWorkOutputName(moduleWorkName);
		param.setModuleWorkOutputPath(moduleWorkDataPath);
		param.setModuleWorkOutputName(moduleWorkDataDivision);
		param.setModuleWorkSeq(moduleWorkSeq);

		moduleWorkDataMapper.modify(param);

		if (param.getIdx()!=null) {
			param = get(moduleWorkDataId);
		}

		return param;
	}

	public boolean delete(int moduleWorkDataId) {
		if (get(moduleWorkDataId)!=null) {
			moduleWorkDataMapper.delete(moduleWorkDataId);
			return true;
		} else {
			return false;
		}
	}

}
