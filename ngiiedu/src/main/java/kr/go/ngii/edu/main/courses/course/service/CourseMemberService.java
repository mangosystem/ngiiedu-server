package kr.go.ngii.edu.main.courses.course.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.common.enums.EnumJoinStatus;
import kr.go.ngii.edu.common.enums.EnumRestAPIType;
import kr.go.ngii.edu.common.message.ErrorMessage;
import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.common.RestAPIClient;
import kr.go.ngii.edu.main.courses.course.mapper.CourseAuthkeyMapper;
import kr.go.ngii.edu.main.courses.course.mapper.CourseMemberMapper;
import kr.go.ngii.edu.main.courses.course.model.Course;
import kr.go.ngii.edu.main.courses.course.model.CourseMember;
import kr.go.ngii.edu.main.courses.course.model.CourseMemberInfo;
import kr.go.ngii.edu.main.users.model.PngoUser;
import kr.go.ngii.edu.main.users.model.User;
import kr.go.ngii.edu.main.users.service.UserService;

@Service
public class CourseMemberService extends BaseService {

	@Autowired
	private CourseMemberMapper courseMemberMapper;

	@Autowired
	private CourseAuthkeyMapper courseAuthkeyMapper;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private UserService userService;

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
		return courseMemberMapper.listByCourseIdAndJoinStatus(courseId, EnumJoinStatus.findCode(joinStatus));
	}
	
	/**
	 * 수업에 참여하고 있는 학생사용자 목록 조회 (뷰테이블)
	 * @param courseId
	 * @return
	 */
	public List<CourseMemberInfo> courseMemberInfoList(int courseId) {
		return courseMemberMapper.courseMemberInfoListByCourseId(courseId);
	}

	/**
	 * 수업에 참여하고 있는 학생사용자 목록을 상태별로 구별하여
	 * {@link EnumJoinStatus} 값을 참고하여 코드변환 후 조회  (뷰테이블)
	 * 
	 * @param courseId
	 * @param joinStatus
	 * @return
	 */
	public List<CourseMemberInfo> courseInfoList(int courseId, String joinStatus) {
		return courseMemberMapper.courseMemberInfoListByCourseIdAndJoinStatus(courseId, EnumJoinStatus.findCode(joinStatus));
	}

	/**
	 * 학생사용자가 수업에 참여하기
	 * 
	 * @param courseId
	 * @param userId
	 * @return
	 */
	public CourseMember create(String courseSecurityKey, int userId) {

		// 수업코드 유효성 검사
		Integer courseId = courseAuthkeyMapper.getCourseId(courseSecurityKey);
		if (courseId == null) {
			throw new RuntimeException(ErrorMessage.COURSE_AUTHKEY_FAILED);
		}

		// 수업에 참여하고 있는지 확인
		CourseMember member = courseMemberMapper.get(courseId, userId);

		if (member == null) {
			CourseMember params = null;
			try {
				params = new CourseMember();
				params.setCourseId(courseId);
				params.setUserId(userId);
				params.setJoinStatus(EnumJoinStatus.WAITING.code());
				params.setCreateDate(new Date());
				params.setModifyDate(new Date());
				courseMemberMapper.create(params);

			} catch (Exception e) {
				throw new RuntimeException(ErrorMessage.SERVER_ERROR);
			}

			return params;

		} else {
			String status = EnumJoinStatus.findKey(member.getJoinStatus());

			if ("WAITING".equals(status)) {
				// 대기중
				status = ErrorMessage.COURSE_JOIN_WAITING;
				
			} else if ("ACTIVE".equals(status) || "DEACTIVE".equals(status)) {
				// 참여중
				status = ErrorMessage.COURSE_JOIN_ACTIVE;
				
			} else if ("BLOCK".equals(status)) {
				// 블락
				status = ErrorMessage.COURSE_JOIN_BLOCK;
				
			} else {
				status = ErrorMessage.SERVER_ERROR;
			}
			
			throw new RuntimeException(status);
		}
	}

	/**
	 * 학생사용자 상태변경하기
	 * 
	 * @param courseId
	 * @param userId
	 * @param status
	 * @return
	 */
	public CourseMember updateStatus(int courseId, int userId, String joinStatus) {

		String statusCode = null;
		
		
		EnumRestAPIType enumType;
		Map<String, String> param = new HashMap<String, String>();
		
		if (joinStatus.equals(EnumJoinStatus.WAITING.name())) {
			statusCode = EnumJoinStatus.WAITING.code();
			enumType = EnumRestAPIType.PROJECT_MEMBER_REMOVE;
		} else if (joinStatus.equals(EnumJoinStatus.ACTIVE.name())) {
			statusCode = EnumJoinStatus.ACTIVE.code();
			enumType = EnumRestAPIType.PROJECT_MEMBER_CREATE;
			param.put("member_role", "WRITER");
		} else if (joinStatus.equals(EnumJoinStatus.DEACTIVE.name())) {
			statusCode = EnumJoinStatus.DEACTIVE.code();
			enumType = EnumRestAPIType.PROJECT_MEMBER_REMOVE;
		} else if (joinStatus.equals(EnumJoinStatus.BLOCK.name())) {
			statusCode = EnumJoinStatus.BLOCK.code();
			enumType = EnumRestAPIType.PROJECT_MEMBER_REMOVE;
		} else {
			statusCode = null;
			enumType = EnumRestAPIType.PROJECT_MEMBER_REMOVE;
		}

		if (statusCode == null) {
			return null;
		}
		
		try {
			
			RestAPIClient rc = new RestAPIClient();
			String apiKey = userService.getApiKey(userId);
			rc.setApiKey(apiKey);
			Course course = courseService.get(courseId);
			String projectId = course.getProjectId();
			
			User user = userService.get(userId);
			PngoUser pngoUser = userService.getPngoUser(user.getUserid());
			
			int pngoUserId = pngoUser.getIdx();
			
			Map<String, String> pathParam = new HashMap<String, String>();
			pathParam.put("project_id", projectId);
			
			Map<String, Object> r = rc.getResponseBodyWithLinkedMap(enumType, pathParam, param, apiKey);
			Map<String, String> metaData = (Map<String, String>) r.get("meta");
			if (!"OK".equalsIgnoreCase(metaData.get("status"))) {
				// throw new RuntimeException(ErrorMessage.COURSE_CREATE_FAILED);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		CourseMember params = new CourseMember();
		params.setCourseId(courseId);
		params.setUserId(userId);
		params.setJoinStatus(statusCode);
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
		
		if (courseMemberMapper.exists(courseId, userId)) {
			courseMemberMapper.deleteByCourseIdAndUserId(courseId, userId);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 수업에서 멤버 삭제하기
	 * 
	 * @param courseId
	 * @param userId
	 * @return
	 */
	public boolean leave(int courseId, String userId, String password) {
		
		// 패스워드 체크필
		User user = userService.get(userId);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (!encoder.matches(password, user.getPassword())) {
			throw new RuntimeException(ErrorMessage.PASSWORD_AUTHENTICATION_FAILED);
		}
		
		// 삭제
		if (courseMemberMapper.exists(courseId, user.getIdx())) {
			courseMemberMapper.deleteByCourseIdAndUserId(courseId, user.getIdx());
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 수업에서 멤버 삭제하기
	 * 
	 * @param courseId
	 * @param userId => users.idx
	 * @param password
	 * @return
	 */ 
	public boolean leave(int courseId, int userId, String password) {
		
		// 패스워드 체크필
		User user = userService.get(userId);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (!encoder.matches(password, user.getPassword())) {
			throw new RuntimeException(ErrorMessage.PASSWORD_AUTHENTICATION_FAILED);
		}
		
		// 삭제
		if (courseMemberMapper.exists(courseId, user.getIdx())) {
			courseMemberMapper.deleteByCourseIdAndUserId(courseId, user.getIdx());
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * 수업에서 멤버 삭제하기
	 * 
	 * @param courseId
	 * @param userId => 지우는 사용자  ID
	 * @param tagetUserIds => , 혈태의 사용자 ID 모음
	 * @param password => 지우는 사용자의 PASSWORD (확인용)
	 * @return
	 */ 
	public boolean leaves(int courseId, String userId, String tagetUserIds, String password) {
		// 패스워드 체크필
		User user = userService.get(userId);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (!encoder.matches(password, user.getPassword())) {
			throw new RuntimeException(ErrorMessage.PASSWORD_AUTHENTICATION_FAILED);
		}
		StringTokenizer s = new StringTokenizer(tagetUserIds, ",");
		
		int i = 0;
		while (s.hasMoreTokens()) {
			
			int uid = Integer.parseInt(s.nextToken().trim());
			if (courseMemberMapper.exists(courseId, uid)) {
				courseMemberMapper.deleteByCourseIdAndUserId(courseId, uid);
			} else {
				i++;
			}
		}
		if (i==0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 수업에서 멤버 삭제하기
	 * 
	 * @param courseId
	 * @param userId => 지우는 사용자  ID
	 * @param tagetUserIds => , 혈태의 사용자 ID 모음
	 * @param password => 지우는 사용자의 PASSWORD (확인용)
	 * @return
	 */ 
	public boolean leavesByUserId(int courseId, String userId, String tagetUserIds, String password) {
		// 패스워드 체크필
		User user = userService.get(userId);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (!encoder.matches(password, user.getPassword())) {
			throw new RuntimeException(ErrorMessage.PASSWORD_AUTHENTICATION_FAILED);
		}
		StringTokenizer s = new StringTokenizer(tagetUserIds, ",");
		
		int i = 0;
		while (s.hasMoreTokens()) {
			String uid = s.nextToken();
			User delUser = userService.get(uid);
			if (courseMemberMapper.exists(courseId, delUser.getIdx())) {
				courseMemberMapper.deleteByCourseIdAndUserId(courseId, delUser.getIdx());
			} else {
				i++;
			}
		}
		if (i==0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	/**
	 * 수업에서 멤버 삭제하기
	 * 
	 * @param courseId
	 * @param userId
	 * @return
	 */
	public boolean leave(int courseId, List<Integer> userids, String password) {
		// 패스워드 체크필요함.
		return false;
	}
	
	
	/**
	 * 수업에서 멤버 삭제하기  
	 * 
	 * @param courseId
	 * @return
	 */
	public boolean delete(int courseId) {
		courseMemberMapper.deleteByCourseId(courseId);
		return false;
	}

}
