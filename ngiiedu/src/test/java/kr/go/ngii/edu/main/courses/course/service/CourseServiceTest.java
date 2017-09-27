package kr.go.ngii.edu.main.courses.course.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.main.courses.course.model.Course;

public class CourseServiceTest extends BaseTest {
	
	@Autowired private CourseService service;

	@Test
	public void testCreate() {
		
		int moduleId = 1;
		List<Integer> moduleWorkIds = new ArrayList<Integer>();
		String courseName = "수업명";
		String courseMetadata = "메타데이터";
		
		moduleWorkIds.add(1);
		moduleWorkIds.add(2);
		moduleWorkIds.add(3);
		
		try {
			Course result = service.create(moduleId, moduleWorkIds, courseName, courseMetadata);
			
			System.out.println(result.getIdx());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
