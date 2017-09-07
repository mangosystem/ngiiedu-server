package kr.go.ngii.edu.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.ngii.edu.sample.model.User;
import kr.go.ngii.edu.sample.service.UserService;

@Controller
public class SampleController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/message", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Message> index() {
		
		Message msg = new Message("이름", "텍스트");
		
		return new ResponseEntity<Message>(msg, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<User> getUser() {
		return new ResponseEntity<User>(userService.getList().get(0), HttpStatus.OK);
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<User>> getUsers() {
		
		return new ResponseEntity<List<User>>(userService.getList(), HttpStatus.OK);
	}
	
}
