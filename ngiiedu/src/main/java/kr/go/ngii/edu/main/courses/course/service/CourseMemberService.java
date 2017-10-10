package kr.go.ngii.edu.main.courses.course.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.common.enums.EnumJoinStatus;
import kr.go.ngii.edu.main.courses.course.mapper.CourseAuthkeyMapper;
import kr.go.ngii.edu.main.courses.course.mapper.CourseMemberMapper;
import kr.go.ngii.edu.main.courses.course.model.CourseAuthkey;
import kr.go.ngii.edu.main.courses.course.model.CourseMember;

@Service
public class CourseMemberService {

	@Autowired
	private CourseMemberMapper courseMemberMapper;

	@Autowired
	private CourseAuthkeyMapper courseAuthkeyMapper;


	/**
	 * 수업에 참여하고 있는 학생사용자 목록 조회
	 * @param courseId
	 * @return
	 */
	public List<CourseMember> list(int courseId) {
		return courseMemberMapper.listByCourseId(courseId);
	}

	/**
	 * 수업에 참여하고 있는 학생사용자 목록을 상태별로 구별하여
	 * {@link EnumJoinStatus} 값을 참고하여 코드변환 후 조회
	 * 
	 * @param courseId
	 * @param joinStatus
	 * @return
	 */
	public List<CourseMember> list(int courseId, String joinStatus) {
		return courseMemberMapper.listByCourseIdAndStatus(courseId, EnumJoinStatus.findCode(joinStatus));
	}

	/**
	 * 학생사용자가 수업에 참여하기
	 * 
	 * @param courseId
	 * @param userId
	 * @return
	 */
	public CourseMember create(String courseSecurityKey, int userId) {

		Integer courseId = courseAuthkeyMapper.getCourseId(courseSecurityKey);

		if (courseId == null) {
			return null;
		}

		if (courseMemberMapper.get(courseId, userId)!=null) {
			return null;
		}

		CourseMember params = new CourseMember();
		params.setCourseId(courseId);
		params.setUserId(userId);
		params.setStatus(EnumJoinStatus.WAITING.code());
		params.setCreateDate(new Date());
		params.setModifyDate(new Date());

		courseMemberMapper.create(params);

		return params;
	}

	/**
	 * 학생사용자의 상태변경하기
	 * 
	 * @param courseId
	 * @param userId
	 * @param status
	 * @return
	 */
	public CourseMember updateStatus(int courseId, int userId, String status) {

		String statusCode = null;

		if (status.equals(EnumJoinStatus.WAITING.name())) {
			statusCode = EnumJoinStatus.WAITING.code();

		} else if (status.equals(EnumJoinStatus.ACTIVE.name())) {
			statusCode = EnumJoinStatus.ACTIVE.code();

		} else if (status.equals(EnumJoinStatus.DEACTIVE.name())) {
			statusCode = EnumJoinStatus.DEACTIVE.code();

		} else if (status.equals(EnumJoinStatus.BLOCK.name())) {
			statusCode = EnumJoinStatus.BLOCK.code();

		} else {
			statusCode = null;
		}

		if (statusCode == null) {
			return null;
		}

		CourseMember params = new CourseMember();
		params.setCourseId(courseId);
		params.setUserId(userId);
		params.setStatus(statusCode);
		params.setModifyDate(new Date());
		courseMemberMapper.modify(params);

		return courseMemberMapper.get(courseId, userId);
	}


	/**
	 * 수업에서 탈퇴하기
	 * 
	 * @param courseId
	 * @param userId
	 * @return
	 */
	public boolean leave(int courseId, int userId) {

		courseMemberMapper.delete(courseId, userId);

		if (courseMemberMapper.get(courseId, userId) == null) {
			return true;
		} else {
			return false;
		}
	}

}
