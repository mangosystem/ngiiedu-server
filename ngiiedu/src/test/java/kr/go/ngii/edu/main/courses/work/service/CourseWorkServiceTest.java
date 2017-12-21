package kr.go.ngii.edu.main.courses.work.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.main.courses.work.model.CourseWork;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkInfo;

public class CourseWorkServiceTest extends BaseTest {

	@Autowired
	private CourseWorkService service;
	
	@Autowired
	private CourseWorkSubService courseWorkSubService;
	
	@Test
	public void testlist() {
		List<CourseWorkInfo> cwinfo = service.listCourseWorkInfo(67);
		System.out.println(cwinfo);
	}
	
	@Test
	public void modify() {
		CourseWork cw = service.modify(36, false);
		System.out.println(cw.getIdx());
	}
	
	@Test
	public void testListAndSubData() {
		List<CourseWorkInfo> cwinfos = service.listCourseWorkInfo(65);
		for(CourseWorkInfo cwinfo : cwinfos) {
			cwinfo.setCourseWorkSubInfos(courseWorkSubService.list(cwinfo.getIdx()));
		}
		System.out.println(cwinfos);
	}
	
}
