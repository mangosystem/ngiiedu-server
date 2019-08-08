package kr.go.ngii.edu.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncodeUtil implements PasswordEncoder{
	
	private PasswordEncoder pe;
	
	
	public PasswordEncodeUtil() { 
		this.pe = new BCryptPasswordEncoder(); 
	} 
	
	public PasswordEncodeUtil(PasswordEncoder pe) { 
		this.pe = pe; 
	}

	@Override
	public String encode(CharSequence arg0) {
		return pe.encode(arg0);
	}

	@Override
	public boolean matches(CharSequence arg0, String arg1) {
		return pe.matches(arg0, arg1);
	}

}
