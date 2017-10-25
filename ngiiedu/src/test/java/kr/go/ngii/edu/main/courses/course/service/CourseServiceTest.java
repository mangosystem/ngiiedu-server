package kr.go.ngii.edu.main.courses.course.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.common.UIDUtil;
import kr.go.ngii.edu.main.courses.course.mapper.CourseMapper;
import kr.go.ngii.edu.main.courses.course.model.Course;

public class CourseServiceTest extends BaseTest {
	
	@Autowired 
	private CourseService service;
	
	@Autowired
	private CourseMapper mapper;


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
	
	@Test
	public void testUIDGenerator() {
		System.out.println(UIDUtil.randomKey36(8));
		System.out.println(UIDUtil.randomKey62(8));
		System.out.println(UIDUtil.uuid());
		System.out.println(UIDUtil.uuid());
		System.out.println(UIDUtil.uuid());
		System.out.println(UIDUtil.uuid());
		System.out.println(UIDUtil.uuid());
		System.out.println(UIDUtil.uuid());
		
	}
	
	@Test
	public void testDel() {
		Course params = new Course();
		params.setIdx(45);
//		service.delete(45);
		System.out.println(mapper.delete(params));
	}
	
	@Test
	public void testList() {
		Course result = service.get(44);
		System.out.println(result.toString());
	}
	
}
