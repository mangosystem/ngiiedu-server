package kr.go.ngii.edu.main.courses.course.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.main.courses.course.model.CourseTeam;

public class CourseTeamServiceTest extends BaseTest {

	@Autowired
	private CourseTeamService teamService;
	
	
	@Test
	public void testList() {
		int courseId = 20;
		List<CourseTeam> list = teamService.list(courseId);
		
		System.out.println(list.size());
	}

	@Test
	public void testCreate() {
		int courseId = 20;
		String teamName = "팀4";

		CourseTeam model = teamService.create(courseId, teamName);
		System.out.println(model.getIdx());
		
	}

	@Test
	public void testModifyName() {
		int courseId = 20;
		int teamId = 9;
		String teamName = "팀444";

		teamService.modifyName(courseId, teamId, teamName);
	}

	@Test
	public void testDelete() {
		int courseId = 20;
		int teamId = 9;
		
		teamService.delete(courseId, teamId);
	}

}
