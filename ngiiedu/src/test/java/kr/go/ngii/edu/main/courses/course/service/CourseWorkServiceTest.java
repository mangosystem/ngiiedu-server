package kr.go.ngii.edu.main.courses.course.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.main.courses.work.model.CourseWork;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkInfo;
import kr.go.ngii.edu.main.courses.work.service.CourseWorkService;

public class CourseWorkServiceTest extends BaseTest {

	@Autowired
	private CourseWorkService service;

	@Test
	public void testlist() {
		List<CourseWorkInfo> cwinfo = service.listCourseWorkInfo(20);
	}
	
	
	@Test
	public void modify() {
		CourseWork cw = service.modify(36, false);
		System.out.println(cw.getIdx());
	}
}
