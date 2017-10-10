package kr.go.ngii.edu.main.courses.course.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.go.ngii.edu.BaseTest;

public class CourseAuthkeyMapperTest extends BaseTest {
	
	@Autowired
	private CourseAuthkeyMapper mapper;
	
	private int courseId = 545211;
	private String authkey = "35HCAG";


	@Test
	public void testGetAuthkey() {
		mapper.getAuthkey(courseId);
	}

	@Test
	public void testGetCourseId() {
		mapper.getCourseId(authkey);
	}

	@Test
	public void testExists() {
		mapper.exists(authkey);
	}

//	@Test
//	public void testUpdateDeactive() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCreate() {
//		fail("Not yet implemented");
//	}

}
