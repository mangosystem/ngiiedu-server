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
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> list(
			@RequestParam(value="offset", required=false, defaultValue="0") Integer offset, 
			@RequestParam(value="limit", required=false, defaultValue="10") Integer limit, 
			HttpSession session) throws Exception {
				
		
		List<User> list = null;
		
		if (offset==0 && limit==0) {
			list = userService.list(new User());
		} else {
			list = userService.list(offset, limit);
		}
		
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}
	
	

}
