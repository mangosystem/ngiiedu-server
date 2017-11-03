package kr.go.ngii.edu.main.courses.work.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.main.courses.work.model.CourseWork;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSubOutputInfo;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSubOutputWithModuleWorkSub;

public class CourseWorkSubServiceTest extends BaseTest {

	@Autowired
	private CourseWorkSubService courseWorkSubService;
	
	@Autowired
	private CourseWorkService courseWorkService;
	

	@Test
	public void testCourseWorkSubList() {
		CourseWork courseWork = new CourseWork();
		courseWork.setModuleWorkId(3);
		List<CourseWorkSubOutputWithModuleWorkSub> list = courseWorkSubService.list(courseWork);
		
		System.out.println("size : " + list.size());
		
		for(CourseWorkSubOutputWithModuleWorkSub m : list) {
			System.out.println(m.getModuleWorkSubName());
			
			List<CourseWorkSubOutputInfo> l = m.getCourseWorkSubOutputInfoList();
			
			System.out.println("------------------------------------------");

			for(CourseWorkSubOutputInfo ll : l) {
				System.out.println(ll.getPinogioOutputId());
				System.out.println(ll.getOutputName());
			}
			System.out.println("------------------------------------------");
			System.out.println();
			
		}
	}
	
	@Test
	public void testCourseWorkSubList2() {

		CourseWork param = new CourseWork();
		param.setIdx(12);
		param = courseWorkService.get(param);
		
		List<CourseWorkSubOutputWithModuleWorkSub> list = courseWorkSubService.list(12);
		
		
		System.out.println("size : " + list.size());
		
		for(CourseWorkSubOutputWithModuleWorkSub m : list) {
			System.out.println(m.getModuleWorkSubName());
			
			List<CourseWorkSubOutputInfo> l = m.getCourseWorkSubOutputInfoList();
			
			System.out.println("------------------------------------------");

			for(CourseWorkSubOutputInfo ll : l) {
				System.out.println(ll.getPinogioOutputId());
				System.out.println(ll.getOutputName());
			}
			System.out.println("------------------------------------------");
			System.out.println();
			
		}
			
	}
	

}
