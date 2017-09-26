package kr.go.ngii.edu.main.modules.module.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.main.modules.module.mapper.ModuleMapper;
import kr.go.ngii.edu.main.modules.module.model.Module;

public class ModuleServiceTest extends BaseTest {

	@Autowired
	private ModuleMapper moduleMapper;
	
	@Test
	public void testList() {
		
		List<Module> list = moduleMapper.list();
//		
		for (Module m : list) {
			System.out.println(m.getModuleName());
		}
		
		System.out.println("ss");
		
	}

}
