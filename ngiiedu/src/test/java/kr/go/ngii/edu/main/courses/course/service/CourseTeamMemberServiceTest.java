package kr.go.ngii.edu.main.courses.course.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.main.courses.course.mapper.CourseTeamMemberMapper;
import kr.go.ngii.edu.main.courses.course.model.CourseTeamMember;

public class CourseTeamMemberServiceTest extends BaseTest {
	
	@Autowired
	private CourseTeamMemberMapper courseTeamMemberMapper;
	
	@Test
	public void CreateTeamMember() {
		CourseTeamMember params = new CourseTeamMember();
		params.setMemberId(1);
		params.setTeamId(17);
		params.setCreateDate(new Date());
		courseTeamMemberMapper.create(params);
		
		CourseTeamMember params1 = new CourseTeamMember();
		params1.setMemberId(2);
		params1.setTeamId(17);
		params1.setCreateDate(new Date());
		courseTeamMemberMapper.create(params1);
		
		CourseTeamMember params2 = new CourseTeamMember();
		params2.setMemberId(4);
		params2.setTeamId(18);
		params2.setCreateDate(new Date());
		courseTeamMemberMapper.create(params2);
		
		
		CourseTeamMember params3 = new CourseTeamMember();
		params3.setMemberId(18);
		params3.setTeamId(18);
		params3.setCreateDate(new Date());
		courseTeamMemberMapper.create(params3);
		
		
		CourseTeamMember params4 = new CourseTeamMember();
		params4.setMemberId(29);
		params4.setTeamId(20);
		params4.setCreateDate(new Date());
		courseTeamMemberMapper.create(params4);
		
		CourseTeamMember params5 = new CourseTeamMember();
		params5.setMemberId(30);
		params5.setTeamId(20);
		params5.setCreateDate(new Date());
		courseTeamMemberMapper.create(params5);
		
		CourseTeamMember params6 = new CourseTeamMember();
		params6.setMemberId(31);
		params6.setTeamId(21);
		params6.setCreateDate(new Date());
		courseTeamMemberMapper.create(params6);
	}

}
