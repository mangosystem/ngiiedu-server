package kr.go.ngii.edu.main.courses.course.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.main.courses.course.model.CourseMember;
import kr.go.ngii.edu.main.courses.course.model.CourseMemberInfo;

public class CourseMemberServiceTest extends BaseTest {

	@Autowired
	private CourseMemberService service;


	@Test
	public void testListInt() {
		int courseId = 1;
		List<CourseMember> list = service.list(courseId);

		System.out.println(list.size());
	}

	@Test
	public void testListIntString() {
		int courseId = 1;

		List<CourseMember> list = service.list(courseId, "WAITING");
		System.out.println(list.size());

		list = service.list(courseId, "ACTIVE");
		System.out.println(list.size());

		list = service.list(courseId, "DEACTIVE");
		System.out.println(list.size());

		list = service.list(courseId, "BLOCK");
		System.out.println(list.size());
	}

	@Test
	public void testCreate() {

		CourseMember member3 = service.create("CAPTOZ", 9);
		CourseMember member4 = service.create("CAPTOZ", 18);
//		System.out.println(member.getIdx());
	}

	@Test
	public void testUpdateStatus() {
		service.updateStatus(1, 5, "ACTIVE", null);
	}

	@Test
	public void testLeave() {
		service.leave(1, 5);
	}
	
	@Test
	public void testCourseMemberInfoList() {
		List<CourseMemberInfo> cmiList = service.courseMemberInfoList(20);
		System.out.println(cmiList.size());
	}

}
