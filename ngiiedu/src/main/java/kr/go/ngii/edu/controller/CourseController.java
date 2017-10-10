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

import kr.go.ngii.edu.controller.rest.BaseController;
import kr.go.ngii.edu.controller.rest.ResponseData;
import kr.go.ngii.edu.main.courses.course.model.Course;
import kr.go.ngii.edu.main.courses.course.model.CourseMember;
import kr.go.ngii.edu.main.courses.course.service.CourseMemberService;
import kr.go.ngii.edu.main.courses.course.service.CourseService;


@Controller
@RequestMapping("/api/v1/courses")
public class CourseController extends BaseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseMemberService courseMemberService;


	/**
	 * 교사사용자가 모듈을 선택하여 새로운 수업을 만듭니다.
	 * 
	 * @param moduleId
	 * @param moduleWorkIds
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
			@RequestParam(value="userId", required=true) Integer userId, 
			HttpSession session) throws Exception {

		CourseMember result = courseMemberService.create(courseSecurityKey, userId);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}


	/**
	 * 수업에 참여하고 있는 사용자의 상태 변경하기 - 관리자용
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
	 * 수업 목록 조회하기
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> list(
			@RequestParam(value="offset", required=false, defaultValue="0") Integer offset, 
			@RequestParam(value="limit", required=false, defaultValue="0") Integer limit, 
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
	 * 수업 조회하기
	 * 
	 * @param idx
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

}
