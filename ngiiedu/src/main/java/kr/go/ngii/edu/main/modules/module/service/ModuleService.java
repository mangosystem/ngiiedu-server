package kr.go.ngii.edu.main.modules.module.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.main.modules.module.mapper.ModuleMapper;
import kr.go.ngii.edu.main.modules.module.model.Module;

@Service
public class ModuleService {

	@Autowired
	private ModuleMapper moduleMapper;


	public List<Module> list() {
		return moduleMapper.list();
	}

	public List<Module> list(int offset, int limit) {
		return moduleMapper.listByOffsetAndLimit(offset, limit);
	}

	public Module get(int idx) {
		Module module = new Module();
		module.setIdx(idx);
		return moduleMapper.get(module);
	}

	public Module create(String moduleName, String moduleMetadata) {
		Module param = new Module();
		param.setModuleName(moduleName);
		param.setModuleMetadata(moduleMetadata);
		param.setCreateDate(new Date());
		param.setModifyDate(new Date());
		moduleMapper.create(param);

		return param;
	}

	public Module modify(int idx, String moduleName, String moduleMetadata) {
		Module param = new Module();
		param.setIdx(idx);
		param.setModuleName(moduleName);
		param.setModuleMetadata(moduleMetadata);
		param.setModifyDate(new Date());
		moduleMapper.modify(param);

		if (param.getIdx()!=null) {
			param = get(idx);
		}

		return param;
	}

	public boolean delete(int idx) {
		if (get(idx)!=null) {
			moduleMapper.delete(idx);
			return true;
		} else {
			return false;
		}
	}

}
