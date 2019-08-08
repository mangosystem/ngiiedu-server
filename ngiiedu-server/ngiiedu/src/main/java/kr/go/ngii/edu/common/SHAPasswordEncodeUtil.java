package kr.go.ngii.edu.common;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class SHAPasswordEncodeUtil implements PasswordEncoder {
	private ShaPasswordEncoder spe;
	private Object salt = null;

	public SHAPasswordEncodeUtil() {
		spe = new ShaPasswordEncoder();
	}

	public SHAPasswordEncodeUtil(int byteLen) {
		spe = new ShaPasswordEncoder(byteLen);
	}

	public void setEncodeHashAsBase64(boolean flag) {
		spe.setEncodeHashAsBase64(flag);
	}

	public void setSalt(Object salt) {
		this.salt = salt;
	}

	@Override
	public String encode(CharSequence pw) {
		return spe.encodePassword(pw.toString(), salt);
	}

	@Override
	public boolean matches(CharSequence pw, String ePw) {
		return spe.isPasswordValid(ePw, pw.toString(), salt);
	}
}
