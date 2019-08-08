package kr.go.ngii.edu.main.users.service;

import java.util.StringTokenizer;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.main.users.model.User;

public class UserServiceTest extends BaseTest {
	
	@Autowired 
	private UserService service;
	

	@Test
	public void testCreate() {

		User tUser = service.create("tuser", "123", "test1", "1");
		
		System.out.println(tUser);
	}
	
	@Test
	public void testPasswd() {

		User tUser = service.get("la1");
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("pw : " + encoder.matches("123", tUser.getPassword()));
	}
	
	
	@Test
	public void testIdSplit() {

		String ids = "aa, bb, cc, dd, az";
		StringTokenizer s = new StringTokenizer(ids, ",");
		
		while (s.hasMoreTokens()) {
			System.out.println(s.nextToken().trim());
		}
		
		
		
	}
}
