package kr.go.ngii.edu.main.modules.module.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.main.modules.course.mapper.ModuleWorkSubMapper;
import kr.go.ngii.edu.main.modules.course.model.ModuleWorkAndSub;
import kr.go.ngii.edu.main.modules.course.model.ModuleWorkSub;

public class ModuleWorkSubServiceTest extends BaseTest {

	@Autowired
	private ModuleWorkSubMapper mapper;
	
	@Test
	public void testModuleWorkAndSubList() {
		
		List<ModuleWorkAndSub> list = mapper.moduleWorkAndSubList(1);
		for (ModuleWorkAndSub m : list) {
			System.out.println(m.getModuleWorkName());
			if (m.getModuleWorkSubList().size() > 0) {
				System.out.println("====================================");
				for(ModuleWorkSub s : m.getModuleWorkSubList()) {
					System.out.println(s.getModuleWorkSubName());
				}
				System.out.println("====================================");
			}
			System.out.println("");
		}
	}
	
	@Test
	public void testList() {
		List<ModuleWorkSub> list = mapper.list(3);
		for (ModuleWorkSub m : list) {
			System.out.println(m.getModuleWorkSubName());
		}
	}
}
