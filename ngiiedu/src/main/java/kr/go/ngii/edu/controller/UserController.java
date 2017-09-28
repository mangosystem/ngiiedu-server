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
import kr.go.ngii.edu.main.users.model.User;
import kr.go.ngii.edu.main.users.service.UserService;

@Controller
@RequestMapping(value="api/v1/users")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 사용자 목록 조회하기
	 * 
	 * @param idx
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> list(
			@RequestParam(value="offset", required=false, defaultValue="0") Integer offset, 
			@RequestParam(value="limit", required=false, defaultValue="10") Integer limit,
			@RequestParam(value="category", required=false, defaultValue="") String category,
			@RequestParam(value="keyword", required=false, defaultValue="") String keyword,
			HttpSession session) throws Exception {
		
		List<User> list = userService.list(offset, limit, category, keyword);
		
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}
	
	/**
	 * 사용자 조회하기
	 * 
	 * @param idx
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{userid}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> get(
			@PathVariable("userid") String userid,
			HttpSession session) throws Exception {
		
		User user = new User();
		user.setUserid(userid);
		user = userService.get(user);
		
		return new ResponseEntity<ResponseData>(responseBody(user), HttpStatus.OK);
	}
	
	
	/**
	 * 사용자 로그인 활성화여부 변경
	 * 
	 * @param idx
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/userState/{userid}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> modify(
			@PathVariable("userid") String userid,
			@RequestParam(value="userState", required=true) boolean userState, 
			HttpSession session) throws Exception {
		
		
		User user = new User();
		user.setUserid(userid);
		user = userService.get(user);
		
		user.setUserState(userState);

		user = userService.modify(user);
		
		return new ResponseEntity<ResponseData>(responseBody(user), HttpStatus.OK);
	}

}
