package kr.go.ngii.edu.main.courses.course.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.common.enums.EnumCourseDataDiv;
import kr.go.ngii.edu.main.courses.course.model.CourseMember;
import kr.go.ngii.edu.main.courses.course.model.CourseWorkData;
import kr.go.ngii.edu.main.courses.course.model.CourseWorkDataInfo;

public class CourseWorkDataServiceTest extends BaseTest {

	
	@Autowired
	private CourseWorkDataService service;
	
	
	@Test
	public void testListIntString() {
		System.out.println(EnumCourseDataDiv.findText("CDD01"));
		System.out.println(EnumCourseDataDiv.findKey("CDD01"));
		System.out.println(EnumCourseDataDiv.findCode("TEACH_GUIDE"));
	}
	
	@Test
	public void testModify() {
		CourseWorkData cwdi = service.modify(4, false);
		System.out.println(cwdi);
	}
	
	
	

}

