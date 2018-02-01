package kr.go.ngii.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

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
import kr.go.ngii.edu.main.users.model.User;
import kr.go.ngii.edu.main.users.service.UserService;

@Controller
@RequestMapping(value="/api/v1/users")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	
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
			@RequestParam(value="limit", required=false, defaultValue="1000") Integer limit,
			@RequestParam(value="keyword", required=false, defaultValue="") String keyword,
			HttpSession session) throws Exception {
		
		List<User> list = userService.list(offset, limit, keyword);
		
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
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
		
		if (isEmail(userid)) {			
			user = userService.getByEmail(userid);
		} else {
			user = userService.get(userid);
		}
		
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
			@PathVariable("userid") String userid,
			@RequestParam(value="userState", required=true) boolean userState, 
			HttpSession session) throws Exception {		
		
		User user = userService.modify(userid, null, null, userState);
		
		return new ResponseEntity<ResponseData>(responseBody(user), HttpStatus.OK);
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
			@PathVariable("userid") String userid,
			@RequestParam(value="userName", required=true) String userName, 
			HttpSession session) throws Exception {		
		
		User user = userService.modify(userid, userName, null, true);
		
		User newUser = (User) session.getAttribute("USER_INFO");
		newUser.setUserName(userName);
		
		session.setAttribute("USER_INFO", newUser);
		
		return new ResponseEntity<ResponseData>(responseBody(user), HttpStatus.OK);
	}
	
	
//	/**
//	 * 사용자 로그인
//	 * 수정필요!! -> 사용안함
//	 * 
//	 * @param userid
//	 * @param password
//	 * @param session
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public @ResponseBody ResponseEntity<ResponseData> get(
//			@RequestParam(value="userid", required=true) String userid,
//			@RequestParam(value="password", required=true) String password, 
//			HttpSession session) throws Exception {
//		
//		User user = null;
//		if (isEmail(userid)) {			
//			user = userService.getByEmail(userid);
//		} else {
//			user = userService.get(userid);
//		}
//		
//		HashMap<String, String> result = new HashMap<>();
//		
//		if (user == null) {
//			result.put("msg", "일치하는 아이디가 없습니다.");
//			result.put("check", "fail");
//		} else if ( !user.getPassword().equals(password) ) {
//			System.out.println(user.getPassword());
//			result.put("msg", "비밀번호가 일치하지 않습니다.");
//			result.put("check", "fail");
//		} else if ( user.getPassword().equals(password) ) {
//			result.put("msg", "로그인 성공");
//			result.put("check", "OK");
//			
//			session.setAttribute("userid", userid);
//		}
//		
//		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
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
			@RequestParam(value="schoolName", required=false) String schoolName,
			@RequestParam(value="userDivision", required=false, defaultValue="2") String userDivision,
			HttpSession session) throws Exception {
		
//		User user = userService.create(userid, password, userEmail, userName, userDivision);
		User user = userService.create(userid, password, userName, userDivision);
		
		return new ResponseEntity<ResponseData>(responseBody(user), HttpStatus.OK);
	}
	
	// 이메일 체크
	public static boolean isEmail(String email) {
        if (email==null) return false;
        boolean b = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+",email.trim());
        return b;
    }

}
