package kr.go.ngii.edu.main.modules.module.service;

import org.springframework.beans.factory.annotation.Autowired;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.main.courses.work.service.CourseWorkService;

public class ModuleWorkServiceTest extends BaseTest {

	@Autowired
	private CourseWorkService courseWorkService;
	
//	@Test
//	public void testList() {
//		System.out.println(courseWorkService.getCourseWorkName(7));
//	}
}
