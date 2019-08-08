package kr.go.ngii.edu.common.enums;

/**
 * 참여상태
 * 
 * @author mg
 */
public enum EnumWorkOutputType {

	LAYER		("layer", "layer_id"),

	MAPS		("maps", "maps_id"),

	DATASET		("dataset", "dataset_id");


	private String code;
	private String idField;

	EnumWorkOutputType(String code, String idField) {
		this.code = code;
		this.idField = idField;
	}

	public String code() {
		return code;
	}
	
	public String idField() {
		return idField;
	}
	
	
	/**
	 * 코드값으로 키값을 찾는다.
	 * @param code
	 * @return
	 */
	public static String findKey(String code) {
		EnumWorkOutputType[] codes = EnumWorkOutputType.values();
		
		for (EnumWorkOutputType value : codes) {
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
		EnumWorkOutputType[] codes = EnumWorkOutputType.values();
		for (EnumWorkOutputType value : codes) {
			if (key.equals(value.name())) {
				return value.code();
			}
		}
		return null;
	}
}
