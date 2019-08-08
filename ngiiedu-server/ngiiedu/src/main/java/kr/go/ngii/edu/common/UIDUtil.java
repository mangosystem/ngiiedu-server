package kr.go.ngii.edu.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 고유키, 랜덤키를 생성하는 클래스.
 * 
 * @author hanjin
 */
public class UIDUtil {

	private final static char[] digitsBase36 = {
			'0','1','2','3','4','5','6','7','8','9',
			'A','B','C','D','E','F','G','H','I','J',
			'K','L','M','N','O','P','Q','R','S','T',
			'U','V','W','X','Y','Z',
	};

	private final static char[] digitsBase62 = {
			'0','1','2','3','4','5','6','7','8','9',
			'A','B','C','D','E','F','G','H','I','J',
			'K','L','M','N','O','P','Q','R','S','T',
			'U','V','W','X','Y','Z',
			'a','b','c','d','e','f','g','h','i','j',
			'k','l','m','n','o','p','q','r','s','t',
			'u','v','w','x','y','z'
	};
	
	private final static char[] digitsBaseNum = {
			'0','1','2','3','4','5','6','7','8','9',
	};


	/**
	 * 랜덤키 생성 <br/>
	 * 영문대문자, 숫자10자리를 포함하고 있는 랜덤키를 생성합니다.
	 * 
	 * @param int size : 랜덤키 자리수
	 * @return
	 */
	public static String randomKey36( int size ) {
		int idx = 0;
		StringBuffer sb = new StringBuffer();
		try {
			for(int i=0; i<size; i++){
				idx = (int)(digitsBase36.length*Math.random());
				sb.append(digitsBase36[idx]);
			}
		} catch(Exception e) {
		}
		return sb.toString();
	}
	
	/**
	 * 랜덤키 생성 <br/>
	 * 영문대문자, 영문소문자, 숫자10자리를 포함하고 있는 랜덤키를 생성합니다.
	 * 
	 * @param size
	 * @return
	 */
	public static String randomKey62( int size ) {
		int idx = 0;
		StringBuffer sb = new StringBuffer();
		try {
			for(int i=0; i<size; i++){
				idx = (int)(digitsBase62.length*Math.random());
				sb.append(digitsBase62[idx]);
			}
		} catch(Exception e) {
		}
		return sb.toString();
	}
	
	/**
	 * 랜덤키 생성 <br/>
	 * 숫자10자리를 포함하고 있는 랜덤키를 생성합니다.
	 * 
	 * @param size
	 * @return
	 */
	public static String randomKeyNum( int size ) {
		int idx = 0;
		StringBuffer sb = new StringBuffer();
		try {
			for(int i=0; i<size; i++){
				idx = (int)(digitsBaseNum.length*Math.random());
				sb.append(digitsBaseNum[idx]);
			}
		} catch(Exception e) {
		}
		return sb.toString();
	}
	

	/**
	 * MD5(Message-Digest algorithm 5) 해시값 생성 <br />
	 * 해시함수를 이용한 키값을 생성합니다.
	 * 
	 * @param value
	 * @return
	 */
	public static String encryptMD5(String value) {
		String encrypt = "";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(value.getBytes());

			byte[] digestByte = md5.digest();
			for (int i=0; i<digestByte.length; i++) {
				encrypt += Integer.toHexString(digestByte[i] & 0xF0);
			}
		} catch (NoSuchAlgorithmException e) {
		}
		return encrypt;
	}
	
	/**
	 * UUID 유틸을 이용한 고유키값을 생성
	 * 
	 * @return string
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

}
