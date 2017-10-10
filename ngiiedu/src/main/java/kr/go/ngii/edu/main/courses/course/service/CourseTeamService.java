package kr.go.ngii.edu.main.courses.course.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.courses.course.mapper.CourseTeamMapper;
import kr.go.ngii.edu.main.courses.course.mapper.CourseTeamMemberMapper;
import kr.go.ngii.edu.main.courses.course.model.CourseTeam;

@Service
public class CourseTeamService extends BaseService {

	@Autowired
	private CourseTeamMapper courseTeamMapper;

	@Autowired
	private CourseTeamMemberMapper courseTeamMemberMapper;


	public List<CourseTeam> list(int courseId) {

		List<CourseTeam> teamList = courseTeamMapper.list(courseId);

		for (CourseTeam team : teamList) {
			team.setTeamMember( courseTeamMemberMapper.list(courseId, team.getIdx()) );
		}

		return teamList;
	}

	public CourseTeam get(int courseId, int teamId) {
		CourseTeam team = courseTeamMapper.get(courseId, teamId);
		team.setTeamMember( courseTeamMemberMapper.list(courseId, team.getIdx()) );
		return team;
	}

	public CourseTeam create(int courseId, String teamName) {
		CourseTeam params = new CourseTeam();
		params.setCourseId(courseId);
		params.setTeamName(teamName);
		params.setTeamSeq(courseTeamMapper.countByCourseId(courseId));
		params.setCreateDate(new Date());
		params.setModifyDate(new Date());
		courseTeamMapper.create(params);

		return params;
	}

	public CourseTeam modifyName(int courseId, int teamId, String teamName) {
		courseTeamMapper.modifyTeamName(courseId, teamId, teamName, new Date());

		return courseTeamMapper.get(courseId, teamId);
	}

	public boolean delete(int courseId, int teamId) {
		
		if (courseTeamMapper.exists(courseId, teamId)) {
			//팀삭제 전 팀멤버를 먼저 삭제
			courseTeamMemberMapper.deleteByTeamId(courseId, teamId);
			
			//팀삭제
			courseTeamMapper.delete(courseId, teamId);
			
			return true;
			
		} else {
			return false;
		}
		
	}

}
