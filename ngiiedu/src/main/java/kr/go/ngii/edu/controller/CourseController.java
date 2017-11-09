package kr.go.ngii.edu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.ngii.edu.common.message.ErrorMessage;
import kr.go.ngii.edu.controller.rest.BaseController;
import kr.go.ngii.edu.controller.rest.ResponseData;
import kr.go.ngii.edu.main.courses.course.model.Course;
import kr.go.ngii.edu.main.courses.course.model.CourseInfo;
import kr.go.ngii.edu.main.courses.course.model.CourseMember;
import kr.go.ngii.edu.main.courses.course.model.CourseMemberInfo;
import kr.go.ngii.edu.main.courses.course.model.CourseTeam;
import kr.go.ngii.edu.main.courses.course.model.CourseTeamMember;
import kr.go.ngii.edu.main.courses.course.model.CourseTeamMemberInfo;
import kr.go.ngii.edu.main.courses.course.service.CourseAuthkeyService;
import kr.go.ngii.edu.main.courses.course.service.CourseMemberService;
import kr.go.ngii.edu.main.courses.course.service.CourseService;
import kr.go.ngii.edu.main.courses.course.service.CourseTeamMemberService;
import kr.go.ngii.edu.main.courses.course.service.CourseTeamService;
import kr.go.ngii.edu.main.courses.work.model.CourseWork;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkData;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkDataInfo;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkInfo;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSubOutputWithModuleWorkSub;
import kr.go.ngii.edu.main.courses.work.service.CourseWorkDataService;
import kr.go.ngii.edu.main.courses.work.service.CourseWorkService;
import kr.go.ngii.edu.main.courses.work.service.CourseWorkSubService;
import kr.go.ngii.edu.main.users.model.User;
import kr.go.ngii.edu.main.users.service.UserService;

@Controller
@RequestMapping("/api/v1/courses")
public class CourseController extends BaseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseMemberService courseMemberService;

	@Autowired
	private CourseTeamService courseTeamService;

	@Autowired
	private CourseTeamMemberService courseTeamMemberService;

	@Autowired
	private CourseAuthkeyService courseAuthkeyService;

	@Autowired
	private CourseWorkService courseWorkService;

	@Autowired
	private CourseWorkDataService courseWorkDataService;

	@Autowired
	private CourseWorkSubService courseWorkSubService;


	@Autowired
	private UserService userService;


	// 수업관련 --------------------------------------------------------------------
	/**
	 * 교사사용자가 모듈을 선택하여 새로운 수업을 만듭니다.
	 * 
	 * @param moduleId
	 * @param moduleWorkIds[]
	 * @param courseName
	 * @param courseMetadata
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> create(
			@RequestParam(value="moduleId", required=true) Integer moduleId, 
			@RequestParam(value="moduleWorkIds[]", required=true) List<Integer> moduleWorkIds, 
			@RequestParam(value="courseName", required=true) String courseName, 
			@RequestParam(value="courseMetadata", required=false) String courseMetadata,
			HttpSession session) throws Exception {

		Course result = courseService.create(moduleId, moduleWorkIds, courseName, courseMetadata);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 수업 목록 조회하기
	 * 
	 * @param offset
	 * @param limit
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> list(
			@RequestParam(value="offset", required=false, defaultValue="0") Integer offset, 
			@RequestParam(value="limit", required=false, defaultValue="20") Integer limit, 
			HttpSession session) throws Exception {

		List<Course> list = null;

		if (offset==0 && limit==0) {
			list = courseService.list();

		} else {
			list = courseService.list(offset, limit);
		}
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}

	/**
	 * 수업 목록 조회하기 
	 * 뷰 테이블을 조회한다.
	 * 
	 * @param offset
	 * @param limit
	 * @param keyword
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list/courseInfos", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> courseInfoList(
			@RequestParam(value="offset", required=false, defaultValue="0") Integer offset, 
			@RequestParam(value="limit", required=false, defaultValue="10") Integer limit, 
			@RequestParam(value="keyword", required=false, defaultValue="") String keyword,
			HttpSession session) throws Exception {

		List<CourseInfo> list = courseService.courseDetailList(offset, limit, keyword);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}

	/**
	 * 참여된 수업  목록 조회하기
	 * 뷰 테이블을 조회한다
	 * 
	 * @param userId
	 * @param offset
	 * @param limit
	 * @param keyword
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list/courseInfoListJoin", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> joinCourseInfoList(
			@RequestParam(value="offset", required=false, defaultValue="0") Integer offset, 
			@RequestParam(value="limit", required=false, defaultValue="10") Integer limit, 
			@RequestParam(value="keyword", required=false, defaultValue="") String keyword,
			HttpSession session) throws Exception {

		User user = (User)session.getAttribute("USER_INFO");
		if (user != null) {
			List<CourseInfo> list = courseService.courseInfoListJoin(user.getIdx(), offset, limit, keyword);
			return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
		} else {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
	}

	/**
	 * 자신이 생성한 수업 목록 조회하기
	 * 뷰 테이블을 조회한다
	 * 
	 * @param userId
	 * @param offset
	 * @param limit
	 * @param keyword
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list/courseInfoListOwn", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> createdCourseInfoList(
			@RequestParam(value="offset", required=false, defaultValue="0") Integer offset, 
			@RequestParam(value="limit", required=false, defaultValue="10") Integer limit, 
			@RequestParam(value="keyword", required=false, defaultValue="") String keyword,
			HttpSession session) throws Exception {

		User user = (User)session.getAttribute("USER_INFO");
		if (user != null) {
			List<CourseInfo> list = courseService.courseInfoListOwn(user.getIdx(), offset, limit, keyword);
			return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
		} else {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
	}

	/**
	 * 수업 조회하기
	 * courseId 의 수업 상세정보를 조회 한다.
	 * 
	 * @param courseId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> get(
			@PathVariable("courseId") Integer courseId,
			HttpSession session) throws Exception {

		Course list = courseService.get(courseId);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}

	/**
	 * 수업 정보 수정하기 (courseName, courseMetadata)
	 * 
	 * @param courseId
	 * @param courseName
	 * @param courseMetadata
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> modify(
			@PathVariable("courseId") Integer idx,
			@RequestParam(value="courseName", required=false, defaultValue="") String courseName,
			@RequestParam(value="courseMetadata", required=false, defaultValue="") String courseMetadata,
			HttpSession session) throws Exception {

		Course result = courseService.modify(idx, courseName, courseMetadata);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 교사사용자가 수업 상태를 변경합니다. (활성화/비활성화)
	 * 
	 * @param courseId
	 * @param status
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/status", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> modify(
			@PathVariable("courseId") Integer idx,
			@RequestParam(value="status", required=false, defaultValue="") boolean status,
			HttpSession session) throws Exception {
		Course result = courseService.updateStatus(idx, status);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 교사사용자가 수업을 삭제합니다.
	 * 
	 * @param courseId
	 * @param userid
	 * @param password
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> delete(
			@PathVariable("courseId") Integer courseId,
			@RequestParam(value="userid", required=false, defaultValue="") Integer userid,
			@RequestParam(value="password", required=false, defaultValue="") String password,
			HttpSession session) throws Exception {
		courseService.delete(courseId, userid, password);
		return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
	}

	// 과정 관련 --------------------------------------------------------------------
	/**
	 * 수업에 설정되어 있는 과정 목록 초회
	 * 
	 * @param courseId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/work", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> workList(
			@PathVariable("courseId") Integer courseId,
			HttpSession session) throws Exception {

		List<CourseWorkInfo> list = courseWorkService.listCourseWorkInfo(courseId);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}

	/**
	 * 과정 상태 업데이트
	 * 
	 * @param courseId
	 * @param status
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/work", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> workUpdate(
			@RequestParam("idx") Integer idx,
			@RequestParam(value="status", required=true) Boolean status,
			HttpSession session) throws Exception {

		CourseWork list = courseWorkService.modify(idx, status);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}

	/**
	 * 과정 상태 업데이트 (활성화/비활성화)
	 * 
	 * @param courseId
	 * @param status
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/work/status", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> workStatusUpdate(
			@PathVariable("courseId") Integer courseId,
			@RequestParam("moduleWorkId") Integer moduleWorkId,
			@RequestParam(value="status", required=true) Boolean status,
			HttpSession session) throws Exception {

		CourseWork list = courseWorkService.updateStatus(courseId, moduleWorkId, status);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}

	/**
	 * 과정의 하부과정 조회 
	 * 
	 * @param courseId
	 * @param workId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/work/{workId}/subWork", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> workSubList(
			@PathVariable("courseId") Integer courseId,
			@PathVariable("workId") Integer workId,
			HttpSession session) throws Exception {

		//		List<CourseWorkInfo> list = moduleWorkService.moduleWorkSubList(courseId);
		return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
	}

	/**
	 * 과정 결과물 조회
	 * 
	 * @param courseId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/work/{workId}/output", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> workOutputList(
			@PathVariable("courseId") Integer courseId,
			@PathVariable("workId") Integer workId,
			HttpSession session) throws Exception {

		//		List<CourseWorkInfo> list = courseWorkService.outputList(courseId, workId);
		return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
	}


	// 멤버 관련  --------------------------------------------------------------------
	/**
	 * 수업에 참여하고 있는 사용자 목록 조회
	 * 
	 * @param courseId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/member", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> memberList(
			@PathVariable("courseId") Integer courseId,
			HttpSession session) throws Exception {

		List<CourseMember> list = courseMemberService.list(courseId);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}

	/**
	 * 수업에 참여하고 있는 사용자 목록 조회 
	 * (뷰테이블 - 사용자 상태, 이메일, 이름, 사용자 ID 포함 정보)
	 * 
	 * @param courseId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/memberInfos", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> memberInfoList(
			@PathVariable("courseId") Integer courseId,
			HttpSession session) throws Exception {

		List<CourseMemberInfo> list = courseMemberService.courseMemberInfoList(courseId);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}

	/**
	 * 수업코드를 이용한 학생사용자의 수업참여하기
	 * 
	 * @param courseSecurityKey
	 * @param userId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseSecurityKey}/member", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> memberJoin(
			@PathVariable("courseSecurityKey") String courseSecurityKey, 
			HttpSession session) throws Exception {

		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}

		CourseMember result = courseMemberService.create(courseSecurityKey, user.getIdx());
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 수업에 참여하고 있는 사용자의 상태 변경하기 - 관리자용 (활성화/비활성화)
	 * @param courseId
	 * @param userId
	 * @param status
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/member/{userId}/status", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> memberStatus(
			@PathVariable("courseId") Integer courseId,
			@PathVariable("userId") Integer userId,
			@RequestParam(value="status", required=true) String status, 
			HttpSession session) throws Exception {

		CourseMember result = courseMemberService.updateStatus(courseId, userId, status);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 수업에서 탈퇴하기
	 * 
	 * @param courseId
	 * @param userId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/member/{userId}/leave", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> memberLeave(
			@PathVariable("courseId") Integer courseId,
			@PathVariable("userId") Integer userId,
			HttpSession session) throws Exception {

		boolean result = courseMemberService.leave(courseId, userId);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 수업내 팀 멤버 삭제
	 * 
	 * @param courseId
	 * @param userid
	 * @param password
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/member", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> memberDelete(
			@PathVariable("courseId") Integer courseId,
			@RequestParam(value="userid", required=false, defaultValue="") Integer userid,
			@RequestParam(value="password", required=false, defaultValue="") String password,
			HttpSession session) throws Exception {

		boolean result = courseMemberService.leave(courseId, userid, password);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 수업내 팀 멤버 삭제
	 * 
	 * @param courseId
	 * @param userid
	 * @param password
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/members", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> membersDelete(
			@PathVariable("courseId") Integer courseId,
			@RequestParam(value="userids", required=false, defaultValue="") String userids,
			@RequestParam(value="password", required=false, defaultValue="") String password,
			HttpSession session) throws Exception {

//		boolean result = courseMemberService.leave(courseId, userids, password);
		return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
	}

	// 팀 관련  --------------------------------------------------------------------
	/**
	 * 수업내 팀 추가
	 * @param courseId
	 * @param teamName
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/team", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> teamCreate(
			@PathVariable("courseId") Integer courseId,
			@RequestParam(value="teamName", required=true) String teamName, 
			HttpSession session) throws Exception {

		CourseTeam result = courseTeamService.create(courseId, teamName);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 수업내 팀 목록 조회
	 * @param courseId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/team", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> teamList(
			@PathVariable("courseId") Integer courseId,
			HttpSession session) throws Exception {

		List<CourseTeam> list = courseTeamService.list(courseId);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}

	/**
	 * 수업내 팀 싱세 정보 조회
	 * @param courseId
	 * @param teamId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/team/{teamId}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> teamGet(
			@PathVariable("courseId") Integer courseId,
			@PathVariable("teamId") Integer teamId,
			HttpSession session) throws Exception {

		CourseTeam result = courseTeamService.get(courseId, teamId);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 수업내 팀 명 변경
	 * 
	 * @param courseId
	 * @param teamId
	 * @param teamName
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/team/{teamId}/name", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> teamModifyName(
			@PathVariable("courseId") Integer courseId,
			@PathVariable("teamId") Integer teamId,
			@RequestParam(value="teamName", required=true) String teamName, 
			HttpSession session) throws Exception {

		CourseTeam result = courseTeamService.modifyName(courseId, teamId, teamName);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

//	/**
//	 * 수업내 팀 순서 변경
//	 * @param courseId
//	 * @param teamId
//	 * @param teamSeq
//	 * @param session
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value="/{courseId}/team/{teamId}/sequence", method=RequestMethod.PUT)
//	public @ResponseBody ResponseEntity<ResponseData> teamModifySeq(
//			@PathVariable("courseId") Integer courseId,
//			@PathVariable("teamId") Integer teamId,
//			@RequestParam(value="seq", required=true) Integer seq, 
//			HttpSession session) throws Exception {

//		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
//	}

	/**
	 * 수업내 팀 삭제
	 * 
	 * @param courseId
	 * @param teamId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/team/{teamId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> teamDelete(
			@PathVariable("courseId") Integer courseId,
			@PathVariable("teamId") Integer teamId,
			HttpSession session) throws Exception {

		boolean result = courseTeamService.delete(courseId, teamId);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	// 팀 멥버 관련 --------------------------------------------------------------------

	/**
	 * 수업내 팀 멤버 추가
	 * @param courseId
	 * @param teamId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/team/{teamId}/member", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> teamMemberCreate(
			@PathVariable("courseId") Integer courseId,
			@PathVariable("teamId") Integer teamId,
			@RequestParam(value="memberId", required=true) Integer memberId,
			HttpSession session) throws Exception {

		CourseTeamMember result = courseTeamMemberService.create(courseId, teamId, memberId);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 수업내 팀 멤버 목록 조회
	 * 
	 * @param courseId
	 * @param teamId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/team/{teamId}/member", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> teamMemberList(
			@PathVariable("courseId") Integer courseId,
			@PathVariable("teamId") Integer teamId,
			HttpSession session) throws Exception {

		List<CourseTeamMember> list = courseTeamMemberService.list(courseId, teamId);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}

	/**
	 * 수업내 팀 멤버 목록 조회 (뷰테이블)
	 * 
	 * @param courseId
	 * @param teamId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/team/memberInfos", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> teamMemberInfoList(
			@PathVariable("courseId") Integer courseId,
			HttpSession session) throws Exception {

		List<CourseTeamMemberInfo> list = courseTeamMemberService.courseTeamMemberInfoList(courseId);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}

	/**
	 * 수업내 팀 멤버 삭제
	 * 
	 * @param courseId
	 * @param teamId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/team/{teamId}/member/{memberId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> teamMemberDelete(
			@PathVariable("courseId") Integer courseId,
			@PathVariable("teamId") Integer teamId,
			@PathVariable("memberId") Integer memberId,
			HttpSession session) throws Exception {

		boolean result = courseTeamMemberService.delete(courseId, teamId, memberId);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	// 인증키 관련  --------------------------------------------------------------------

	/**
	 * 인증키 조회
	 * 
	 * @param courseId
	 * 
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/authkey", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getAuthkey(
			@PathVariable(value="courseId") Integer courseId
			) throws Exception {

		String authKey = courseAuthkeyService.get(courseId);
		return new ResponseEntity<ResponseData>(responseBody(authKey), HttpStatus.OK);
	}

	/**
	 * 인증키 재발급
	 * 
	 * @param courseId
	 * 
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/authkey/modify", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> modifyAuthkey(
			@PathVariable(value="courseId") Integer courseId
			) throws Exception {

		String result = courseAuthkeyService.modify(courseId);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	// 수업 관련 데이터 관련  --------------------------------------------------------------------
	/**
	 * 수업내 관련 데이터 조회
	 * 
	 * @param courseId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseId}/workData", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> courseWorkDataList(
			@PathVariable("courseId") Integer courseId,
			HttpSession session) throws Exception {

		List<CourseWorkDataInfo> list = courseWorkDataService.list(courseId);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}

	/**
	 * 수업내 관련 데이터 상태 수정
	 * 
	 * @param idx
	 * @param status
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{idx}/workData", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> modifyCourseWorkData(
			@PathVariable("idx") Integer idx,
			@RequestParam(value="status", required=true) Boolean status,
			HttpSession session) throws Exception {

		CourseWorkData result = courseWorkDataService.modify(idx, status);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	// 수업 결과물 관련  --------------------------------------------------------------------
	/**
	 * 수업 과정내 하위 과정 및 결과물 조회
	 * 
	 * @param courseWorkId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseWorkId}/workSubData", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> courseWorkSubDataList(
			@PathVariable("courseWorkId") Integer courseWorkId,
			HttpSession session) throws Exception {
		User user = (User)session.getAttribute("USER_INFO");
		
		if (user != null) {
			int userId = user.getIdx();
			List<CourseWorkSubOutputWithModuleWorkSub> list = courseWorkSubService.list(courseWorkId, userId);
//			List<CourseWorkSubOutputWithModuleWorkSub> list = courseWorkSubService.list(courseWorkId);
			return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
		} else {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
	}
	
	/**
	 * 수업 과정내 하위 과정 및 결과물 생성
	 * 
	 * @param courseWorkId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseWorkId}/workSubData", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> courseWorkSubCreate(
			@PathVariable("courseWorkId") Integer courseWorkId,
			@RequestParam("outputType") String outputType,
			@RequestParam("teamId") Integer teamId,
			@RequestParam("pinogioOutputId") String pinogioOutputId, 
			HttpSession session) throws Exception {
		
		return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
	}
	
	/**
	 * 수업 과정내 하위 과정 및 결과물 삭제
	 * 
	 * @param courseWorkId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseWorkId}/workSubData/{courseWorkSubId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> courseWorkSubDelete(
			@PathVariable("courseWorkId") Integer courseWorkId,
			@PathVariable("courseWorkSubId") Integer courseWorkSubId,
			HttpSession session) throws Exception {
		
		// team id, user id
		return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
	}
	
	/**
	 * 수업 과정내 하위 과정 및 결과물 수정
	 * 
	 * @param courseWorkId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseWorkId}/workSubData/{courseWorkSubId}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> courseWorkSubModify(
			@PathVariable("courseWorkId") Integer courseWorkId,
			@PathVariable("courseWorkSubId") Integer courseWorkSubId,
			@RequestParam("pngoDataJson") String pngoDataJson, // pngo_ 테이블 참조
			HttpSession session) throws Exception {
		
		// team id, user id
		return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
	}

}
