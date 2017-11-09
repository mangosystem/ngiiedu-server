package kr.go.ngii.edu.main.modules.module.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.main.courses.work.service.CourseWorkService;
import kr.go.ngii.edu.main.modules.course.mapper.ModuleWorkMapper;
import kr.go.ngii.edu.main.modules.course.service.ModuleWorkService;
import kr.go.ngii.edu.main.modules.module.mapper.ModuleMapper;
import kr.go.ngii.edu.main.modules.module.model.Module;

public class ModuleWorkServiceTest extends BaseTest {

	@Autowired
	private CourseWorkService courseWorkService;
	
//	@Test
//	public void testList() {
//		System.out.println(courseWorkService.getCourseWorkName(7));
//	}
}
