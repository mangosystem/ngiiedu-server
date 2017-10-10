package kr.go.ngii.edu.common.enums;

/**
 * 참여상태
 * 
 * @author mg
 */
public enum EnumCourseProcessDiv {

	// 토론형
	DISCUSSION			("CPD01"),

	// 현장실습형
	FIELD_PRACTICE		("CPD02"),

	// 전산실습형
	COMPUTER_PRACTICE	("CPD03");


	private String code;

	EnumCourseProcessDiv(String code) {
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
		EnumCourseProcessDiv[] codes = EnumCourseProcessDiv.values();
		
		for (EnumCourseProcessDiv value : codes) {
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
		EnumCourseProcessDiv[] codes = EnumCourseProcessDiv.values();
		for (EnumCourseProcessDiv value : codes) {
			if (key.equals(value.name())) {
				return value.code();
			}
		}
		return null;
	}

}
