package kr.go.ngii.edu.common.enums;

/**
 * 참여상태
 * 
 * @author mg
 */
public enum EnumJoinStatus {

	// 대기
	WAITING		("CJS01"),

	// 활성화
	ACTIVE		("CJS02"),

	// 비활성화
	DEACTIVE		("CJS03"),

	// 차단
	BLOCK		("CJS04");


	private String code;

	EnumJoinStatus(String code) {
		this.code = code;
	}

	public String code() {
		return code;
	}
	
	
	/**
	 * 코드값으로 키값을 찾는다.
	 * @param code
	 * @return
	 */
	public static String findKey(String code) {
		EnumJoinStatus[] codes = EnumJoinStatus.values();
		
		for (EnumJoinStatus value : codes) {
			if (code.equals(value.code())) {
				return value.name();
			}
		}
		return null;
	}
	
	/**
	 * 키값으로 코드를 찾는다.
	 * 
	 * @param key
	 * @return
	 */
	public static String findCode(String key) {
		EnumJoinStatus[] codes = EnumJoinStatus.values();
		for (EnumJoinStatus value : codes) {
			if (key.equals(value.name())) {
				return value.code();
			}
		}
		return null;
	}

}
