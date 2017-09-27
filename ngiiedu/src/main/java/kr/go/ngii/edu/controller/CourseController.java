package kr.go.ngii.edu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.ngii.edu.controller.rest.BaseController;
import kr.go.ngii.edu.controller.rest.ResponseData;
import kr.go.ngii.edu.main.courses.course.model.Course;
import kr.go.ngii.edu.main.courses.course.service.CourseService;

@Controller
@RequestMapping("/api/v1/course")
public class CourseController extends BaseController {

	@Autowired
	private CourseService courseService;

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

}









