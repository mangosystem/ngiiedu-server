package kr.go.ngii.edu.common.enums;

/**
 * 참여상태
 * 
 * @author mg
 */
public enum EnumWorkType {

	// 전체
	ALL			("CWT01"),

	// 개인
	PERSONAL		("CWT02"),

	// 팀
	TEAM			("CWT03");


	private String code;

	EnumWorkType(String code) {
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
		EnumWorkType[] codes = EnumWorkType.values();
		
		for (EnumWorkType value : codes) {
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
		EnumWorkType[] codes = EnumWorkType.values();
		for (EnumWorkType value : codes) {
			if (key.equals(value.name())) {
				return value.code();
			}
		}
		return null;
	}

}
