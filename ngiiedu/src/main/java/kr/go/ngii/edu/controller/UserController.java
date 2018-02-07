package kr.go.ngii.edu.controller;

import java.util.Map;

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
import kr.go.ngii.edu.main.schools.model.School;
import kr.go.ngii.edu.main.schools.service.SchoolService;
import kr.go.ngii.edu.main.users.model.User;
import kr.go.ngii.edu.main.users.service.UserService;

@Controller
@RequestMapping(value="/api/v1/users")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private SchoolService schoolService;



	/**
	 * 사용자 목록 조회하기
	 * 
	 * @param category
	 * @param keyword
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> list(
			@RequestParam(value="offset", required=false, defaultValue="0") Integer offset, 
			@RequestParam(value="limit", required=false, defaultValue="20") Integer limit,
			@RequestParam(value="keyword", required=false, defaultValue="") String keyword,
			HttpSession session) throws Exception {

		Map<String,Object> result = userService.list(offset, limit, keyword);

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 사용자 조회하기
	 * 
	 * @param userid
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{userid}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> get(
			@PathVariable("userid") String userid,
			HttpSession session) throws Exception {

		User user = null;
		user = userService.get(userid);

		return new ResponseEntity<ResponseData>(responseBody(user), HttpStatus.OK);
	}


	/**
	 * 사용자 로그인 활성화여부 변경
	 * 
	 * @param userid
	 * @param userState
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{userid}/state", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> modifyUserState(
			@PathVariable("userid") String userId,
			@RequestParam(value="userState", required=true) boolean userState, 
			HttpSession session) throws Exception {

		boolean result = false;

		if (isAdmin(session)) {
			result = userService.modifyState(userId, userState);
		}

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}


	/**
	 * 사용자 이름(별칭) 변경
	 * 
	 * @param userid
	 * @param userState
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{userid}/name", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> modifyUserName(
			@PathVariable("userid") String userId,
			@RequestParam(value="userName", required=true) String userName, 
			HttpSession session) throws Exception {

		boolean result = false;

		String sessionUserId = (String)session.getAttribute("USER_ID");

		if (userId.equals(sessionUserId)) {
			result = userService.modifyUserName(userId, userName);

			if (result) {
				User newUser = (User) session.getAttribute("USER_INFO");
				newUser.setUserName(userName);

				session.setAttribute("USER_INFO", newUser);
				return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
			}
		}

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}


	/**
	 * 사용자 패스워드 변경
	 * @param userid
	 * @param userName
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{userid}/password", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> modifyUserPassword(
			@PathVariable("userid") String userId,
			@RequestParam(value="oldpasswd", required=true) String oldPasswd, 
			@RequestParam(value="newpasswd", required=true) String newPasswd, 
			HttpSession session) throws Exception {

		boolean result = false;

		try {
			if (newPasswd.length() < 9) {
				return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.BAD_REQUEST);
			}

			String sessionUserId = (String)session.getAttribute("USER_ID");

			if (userId.equals(sessionUserId)) {
				result = userService.modifyPassword(userId, oldPasswd, newPasswd);
			}
			return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}




//	@RequestMapping(value="/{userid}/userid", method=RequestMethod.PUT)
//	public @ResponseBody ResponseEntity<ResponseData> modifyUserId(
//			@PathVariable("userid") String userId,
//			@RequestParam(value="newUserid", required=true) String newUserId, 
//			@RequestParam(value="passwd", required=true) String passwd, 
//
//			HttpSession session) throws Exception {
//
//		boolean result = false;
//
//		try {
//			String sessionUserId = (String)session.getAttribute("USER_ID");
//
//			if (userId.equals(sessionUserId)) {
//				result = userService.modifyUserId(userId, newUserId, passwd);
//			}
//			return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
//
//		} catch (Exception e) {
//			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}


	/**
	 * 회원가입
	 * 
	 * @param 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> create(
			@RequestParam(value="userid", required=true) String userid,
			@RequestParam(value="password", required=true) String password,
			@RequestParam(value="userName", required=true) String userName,
			@RequestParam(value="userEmail", required=false, defaultValue="") String userEmail,
			@RequestParam(value="userDivision", required=false, defaultValue="2") String userDivision,
			@RequestParam(value="schoolAuthkey", required=false) String schoolAuthkey,
			HttpSession session) throws Exception {

		User user = null;

		if ("1".equals(userDivision)) {
			School list = schoolService.get(schoolAuthkey);

			if (list!=null) {
				user = userService.create(userid, password, userName, userDivision);
				return new ResponseEntity<ResponseData>(responseBody(user), HttpStatus.OK);
			} else {
				return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.BAD_REQUEST);
			}

		} else if ("2".equals(userDivision)) {
			user = userService.create(userid, password, userName, userDivision);
			return new ResponseEntity<ResponseData>(responseBody(user), HttpStatus.OK);

		} else {
			user = null;
			return new ResponseEntity<ResponseData>(responseBody(user), HttpStatus.BAD_REQUEST);

		}
	}


	// 이메일 체크
	//public static boolean isEmail(String email) {
	//	if (email==null) return false;
	//	boolean b = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+",email.trim());
	//	return b;
	//}

}
