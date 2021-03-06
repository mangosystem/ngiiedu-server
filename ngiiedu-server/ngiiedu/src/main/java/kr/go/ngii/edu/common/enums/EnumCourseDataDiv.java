package kr.go.ngii.edu.common.enums;

/**
 * 참여상태
 * 
 * @author mg
 */
public enum EnumCourseDataDiv {

	// 수업지도안
	TEACH_GUIDE				("CDD01", "수업지도안"),

	// 교사용 수업자료
	DATA_FOR_TEACHER		("CDD02", "교사용 수업자료"),

	// 학생용 수업자료
	DATA_FOR_STUDENT		("CDD03", "학생용 수업자료"),
	
	// 학생 활동지
	ACTIVITIES_STUDENT		("CDD04", "학생 활동지"),
		
	// 활동 매뉴얼
	ACTIVITIES_MANUAL		("CDD05", "활동 매뉴얼");


	private String code;
	private String text;

	EnumCourseDataDiv(String code, String text) {
		this.code = code;
		this.text = text;
	}

	public String code() {
		return code;
	}
	
	public String text() {
		return text;
	}
	
	
	/**
	 * 코드값으로 키값을 찾는다.
	 * @param code
	 * @return
	 */
	public static String findKey(String code) {
		EnumCourseDataDiv[] codes = EnumCourseDataDiv.values();
		for (EnumCourseDataDiv value : codes) {
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
		EnumCourseDataDiv[] codes = EnumCourseDataDiv.values();
		for (EnumCourseDataDiv value : codes) {
			if (key.equals(value.name())) {
				return value.code();
			}
		}
		return null;
	}
	
	/**
	 * 코드값으로 2번쨰 코드값(text)를 찾는다..
	 * 
	 * @param code
	 * @return
	 */
	public static String findText(String code) {
		EnumCourseDataDiv[] codes = EnumCourseDataDiv.values();
		
		for (EnumCourseDataDiv value : codes) {
			if (code.equals(value.code())) {
				return value.text();
			}
		}
		return null;
	}

}
