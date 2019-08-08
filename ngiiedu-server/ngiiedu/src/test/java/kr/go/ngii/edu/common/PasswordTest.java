package kr.go.ngii.edu.common;

import java.util.regex.Pattern;

import org.junit.Test;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.go.ngii.edu.BaseTest;

public class PasswordTest extends BaseTest {

	
	@Test
	public void passwordEncoding() {
		
		
		String password = "1234";
		SHAPasswordEncodeUtil shaPasswordEncoder = new SHAPasswordEncodeUtil(256);
		shaPasswordEncoder.setEncodeHashAsBase64(true);
		PasswordEncodeUtil passwordEncoding = new PasswordEncodeUtil(shaPasswordEncoder);

		System.out.println("SHA 암호화: " + passwordEncoding.encode(password));
		System.out.println("SHA 비교: " + passwordEncoding.matches(password, passwordEncoding.encode(password)));
		System.out.println("SHA 암호화: " + passwordEncoding.encode(password));
		System.out.println("SHA 비교: " + passwordEncoding.matches(password, "A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ="));

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		passwordEncoding = new PasswordEncodeUtil(passwordEncoder);

		System.out.println("BCrypt 암호화: " + passwordEncoding.encode(password));
		System.out.println("BCrypt 비교: " + passwordEncoding.matches(password, "$2a$10$3JwYjsTyTLFp0pxn1LCKSO9Cly/QNcPDGSMXIqV6UjQGnE5PI3.5G"));
		
		// 다시 암호화
		System.out.println("BCrypt 암호화: " + passwordEncoding.encode(password));
		System.out.println("BCrypt 비교: " + passwordEncoding.matches(password, "$2a$10$xgmDgEXP/m477rABQUFGvOPCjBzAGi.mH9E5r/GPyjVTHMHWDLd.K"));
	
	
		ShaPasswordEncoder spe = new ShaPasswordEncoder(256);
		spe.setEncodeHashAsBase64(true);
		System.out.println(spe.encodePassword("1234", null));
		System.out.println(spe.encodePassword("1234", null));
		System.out.println(spe.encodePassword("1234", null));
		
	}
	
	@Test
	public void testRegexp() {
		
		// 영문
		System.out.println( Pattern.matches("^[a-zA-Z]*$", "aASDfab") );
		
		// 숫자
		System.out.println( Pattern.matches("^[0-9]*$", "123309829084") );
		
		// 영문, 숫자
		System.out.println( Pattern.matches("^[a-zA-z0-9]*$", "ASDF23sdfs") );
		
		// 영문, 숫자, 특수문자
//		System.out.println( Pattern.matches("^[a-zA-z0-9~!@#$%\\^&[*]\\(\\)_[+]\\{\\}\\[\\][|]'?.,]*[$]", "AS!@#DF23sdfs") );
		
		System.out.println( Pattern.matches("^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~`]+$", "askjkdf!#@!a@#23VS") );
		
	}
	
}
