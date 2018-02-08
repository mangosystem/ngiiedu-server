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
import kr.go.ngii.edu.config.LocalResourceBundle;
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
				
				boolean isPublicCourse = false;
				
				String courseNSM = LocalResourceBundle.PUBLIC_COURSE_CODE_NOISEMAP;
				String courseGPS = LocalResourceBundle.PUBLIC_COURSE_CODE_GPS;
				String coursePOP = LocalResourceBundle.PUBLIC_COURSE_CODE_POPULATION;
				String courseTER = LocalResourceBundle.PUBLIC_COURSE_CODE_TERRITORY;
				String courseECO = LocalResourceBundle.PUBLIC_COURSE_CODE_ECOLOGY;
				String courseARC = LocalResourceBundle.PUBLIC_COURSE_CODE_ACCURACY;
				String courseDOK = LocalResourceBundle.PUBLIC_COURSE_CODE_DOKDO;
				
				// 공개 수업여부 확인
				if (courseSecurityKey.equals(courseNSM)) {
					isPublicCourse = true;
				} else if (courseSecurityKey.equals(courseGPS)) {
					isPublicCourse = true;
				} else if (courseSecurityKey.equals(coursePOP)) {
					isPublicCourse = true;
				} else if (courseSecurityKey.equals(courseTER)) {
					isPublicCourse = true;
				} else if (courseSecurityKey.equals(courseECO)) {
					isPublicCourse = true;
				} else if (courseSecurityKey.equals(courseARC)) {
					isPublicCourse = true;
				} else if (courseSecurityKey.equals(courseDOK)) {
					isPublicCourse = true;
				} else {
					isPublicCourse = false;
				}

				params = new CourseMember();
				params.setCourseId(courseId);
				params.setUserId(userId);
				params.setJoinStatus(EnumJoinStatus.WAITING.code());
				params.setCreateDate(new Date());
				params.setModifyDate(new Date());
				courseMemberMapper.create(params);

				if (isPublicCourse) {
					params = updateStatus(courseId, userId, "ACTIVE");
				}

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

	//	/**
	//	 * 학생사용자 상태변경하기
	//	 * 
	//	 * @param courseId
	//	 * @param userId
	//	 * @param status
	//	 * @return
	//	 */
	//	public CourseMember updateStatus(int courseId, int userId, String joinStatus) {
	//
	//		String statusCode = null;
	//		
	//		EnumRestAPIType enumType;
	//		Map<String, String> param = new HashMap<String, String>();
	//		
	//		RestAPIClient rc = new RestAPIClient();
	//		User loginUser = (User) getHttpSession().getAttribute("USER_INFO");
	//		String apiKey = userService.getApiKey(loginUser.getIdx());
	//		rc.setApiKey(apiKey);
	//		
	//		Course course = courseService.get(courseId);
	//		String projectId = course.getProjectId();
	//		User user = userService.get(userId);
	//		PngoUser pngoUser = userService.getPngoUser(user.getUserid());
	////		param.put("member_id", pngoUser.getIdx().toString());
	//		Map<String, String> pathParam = new HashMap<String, String>();
	//		pathParam.put("project_id", projectId);
	//		pathParam.put("member_id", pngoUser.getIdx().toString());
	//		
	//		try {
	//			
	//			Map<String, Object> getMemberResult = rc.getResponseBodyWithLinkedMap(EnumRestAPIType.PEOJECT_MEMBER_GET, pathParam, param, apiKey);
	//			Map<String, String> getMemberMetadata = (Map<String, String>) getMemberResult.get("meta");
	//			if ("OK".equalsIgnoreCase(getMemberMetadata.get("status"))) {
	//				// throw new RuntimeException(ErrorMessage.COURSE_CREATE_FAILED);
	//				// Member 있음
	//				if (joinStatus.equals(EnumJoinStatus.WAITING.name())) {
	//					statusCode = EnumJoinStatus.WAITING.code();
	//					enumType = EnumRestAPIType.PROJECT_MEMBER_REMOVE;
	//				} else if (joinStatus.equals(EnumJoinStatus.ACTIVE.name())) {
	//					statusCode = EnumJoinStatus.ACTIVE.code();
	//					enumType = null;
	//					//param.put("member_role", "WRITER");
	//				} else if (joinStatus.equals(EnumJoinStatus.DEACTIVE.name())) {
	//					statusCode = EnumJoinStatus.DEACTIVE.code();
	//					enumType = EnumRestAPIType.PROJECT_MEMBER_REMOVE;
	//				} else if (joinStatus.equals(EnumJoinStatus.BLOCK.name())) {
	//					statusCode = EnumJoinStatus.BLOCK.code();
	//					enumType = EnumRestAPIType.PROJECT_MEMBER_REMOVE;
	//				} else {
	//					statusCode = null;
	//					enumType = EnumRestAPIType.PROJECT_MEMBER_REMOVE;
	//				}
	//
	//				if (statusCode == null) {
	//					try {
	//						Map<String, Object> r = rc.getResponseBodyWithLinkedMap(enumType, pathParam, param, apiKey);
	//						Map<String, String> metaData = (Map<String, String>) r.get("meta");
	//						if (!"OK".equalsIgnoreCase(metaData.get("status"))) {
	//							// throw new RuntimeException(ErrorMessage.COURSE_CREATE_FAILED);
	//						}
	//					} catch (Exception e) {
	//						e.printStackTrace();
	//					}
	//				}
	//			} else {
	//				// 메버 없음
	//				if (joinStatus.equals(EnumJoinStatus.WAITING.name())) {
	//					statusCode = EnumJoinStatus.WAITING.code();
	//					enumType = null;
	//				} else if (joinStatus.equals(EnumJoinStatus.ACTIVE.name())) {
	//					statusCode = EnumJoinStatus.ACTIVE.code();
	//					enumType = EnumRestAPIType.PROJECT_MEMBER_CREATE;
	//					param.put("member_role", "WRITER");
	//				} else if (joinStatus.equals(EnumJoinStatus.DEACTIVE.name())) {
	//					statusCode = EnumJoinStatus.DEACTIVE.code();
	//					enumType = null;
	//				} else if (joinStatus.equals(EnumJoinStatus.BLOCK.name())) {
	//					statusCode = EnumJoinStatus.BLOCK.code();
	//					enumType = null;
	//				} else {
	//					enumType = null;
	//					statusCode = null;
	//				}
	//
	//				if (enumType != null) {
	//					try {
	//						Map<String, Object> r = rc.getResponseBodyWithLinkedMap(enumType, pathParam, param, apiKey);
	//						Map<String, String> metaData = (Map<String, String>) r.get("meta");
	//						if (!"OK".equalsIgnoreCase(metaData.get("status"))) {
	//							// throw new RuntimeException(ErrorMessage.COURSE_CREATE_FAILED);
	//						}
	//					} catch (Exception e) {
	//						e.printStackTrace();
	//					}
	//				}
	//			}
	////			if (statusCode == null) {
	////				throw new RuntimeException(ErrorMessage.COURSE_CREATE_FAILED);
	////			}
	//			CourseMember params = new CourseMember();
	//			params.setCourseId(courseId);
	//			params.setUserId(userId);
	//			params.setJoinStatus(statusCode);
	//			params.setModifyDate(new Date());
	//			courseMemberMapper.modify(params);
	//			return courseMemberMapper.get(courseId, userId);
	//			
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			return null;
	//		}
	//	}

	
	/**
	 * 학생사용자 상태변경하기
	 * @param courseId
	 * @param userId
	 * @param updateStatus
	 * @return
	 */
	public CourseMember updateStatus(int courseId, int userId, String updateStatus) {

		CourseMember courseMember = courseMemberMapper.get(courseId, userId);

		if (courseMember == null) {
			return null;
		}

		String joinStatus = courseMember.getJoinStatus();

		// 수업멤버 변경 API 요청 여부 확인
		boolean apiInserted = false;
		if (joinStatus.equalsIgnoreCase("CJS01") || joinStatus.equalsIgnoreCase("CJS03") || joinStatus.equalsIgnoreCase("CJS04")) {
			apiInserted = false;
		} else if (joinStatus.equalsIgnoreCase("CJS02")) {
			apiInserted = true;
		} else {
			return null;
		}

		//수업의 프로젝트 ID 획인
		Course course = courseService.get(courseId);
		String projectId = course.getProjectId();

		// 변경할 회원정보 확인 - PngoUser
		User user = userService.get(userId);

		//-- API 서버에 회원 조회
		EnumRestAPIType restAPIType = null;

		RestAPIClient rc = new RestAPIClient();
		User loginUser = (User) getHttpSession().getAttribute("USER_INFO");
		String apiKey = userService.getApiKey(loginUser.getIdx());
		rc.setApiKey(apiKey);

		Map<String, String> apiPathParam = new HashMap<String, String>();
		apiPathParam.put("project_id", projectId);
		apiPathParam.put("member_id", user.getIdx().toString());

		Map<String, String> apiParam = new HashMap<String, String>();
		try {
			Map<String, Object> getMemberResult = rc.getResponseBodyWithLinkedMap(EnumRestAPIType.PEOJECT_MEMBER_GET, apiPathParam, apiParam, apiKey);
			Map<String, String> getMemberData = (Map<String, String>) getMemberResult.get("data");
			
			Map<String, String> setApiPathParam = new HashMap<String, String>();
			setApiPathParam.put("project_id", projectId);
			
			if (getMemberData == null) {
				if (updateStatus.equalsIgnoreCase("WAITING") || updateStatus.equalsIgnoreCase("DEACTIVE") || updateStatus.equalsIgnoreCase("BLOCK")) {
					if (apiInserted) {
						// API로 삭제
						restAPIType = EnumRestAPIType.PROJECT_MEMBER_REMOVE;
						setApiPathParam.put("member_id", user.getIdx().toString());
					}
				} else if (updateStatus.equalsIgnoreCase("ACTIVE")) {
					if (!apiInserted) {
						// API로 추가
						restAPIType = EnumRestAPIType.PROJECT_MEMBER_CREATE;
						apiParam.put("member_id", user.getIdx().toString());
						apiParam.put("member_role", "WRITER");
					}
				}
			} else {
				if (updateStatus.equalsIgnoreCase("WAITING") || updateStatus.equalsIgnoreCase("DEACTIVE") || updateStatus.equalsIgnoreCase("BLOCK")) {
					restAPIType = EnumRestAPIType.PROJECT_MEMBER_REMOVE;
					setApiPathParam.put("member_id", user.getIdx().toString());
				}
			}

			if (restAPIType != null) {
				Map<String, Object> r = rc.getResponseBodyWithLinkedMap(restAPIType, setApiPathParam, apiParam, apiKey);
				Map<String, String> metaData = (Map<String, String>) r.get("meta");
			}

			CourseMember params = new CourseMember();
			params.setCourseId(courseId);
			params.setUserId(userId);
			params.setJoinStatus(EnumJoinStatus.findCode(updateStatus));
			params.setModifyDate(new Date());
			courseMemberMapper.modify(params);

			return courseMemberMapper.get(courseId, userId);

		} catch (Exception e) {
			return null;
		}
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
