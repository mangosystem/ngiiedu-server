package kr.go.ngii.edu.common.enums;

import org.springframework.http.HttpMethod;

/**
 * EnumRestAPIType
 * 
 * @author mg
 */
public enum EnumRestAPIType {
	
	
	PROJECT_LIST			("/projects.json", HttpMethod.GET),
	PEOJECT_GET				("/projects/{project_id}.json", HttpMethod.GET),
	PROJECT_CREATE			("/projects", HttpMethod.POST),
	PROJECT_UPDATE			("/projects", HttpMethod.PUT),
	PROJECT_REMOVE			("/projects", HttpMethod.DELETE),
	DATASET_LIST			("/datasets.json", HttpMethod.GET),
	DATASET_GET				("/datasets/{dataset_id}.json", HttpMethod.GET),
	DATASET_CREATE			("/datasets", HttpMethod.GET),
	DATASET_UPDATE			("/datasets", HttpMethod.GET),
	DATASET_REMOVE			("/datasets", HttpMethod.GET),
	DATASET_ROW_LIST		("/datasets/{dataset_id}/row.json", HttpMethod.GET),
	DATASET_ROW_GET			("/datasets/{dataset_id}/row/{row_id}.json", HttpMethod.GET),
	DATASET_ROW_CREATE		("/datasets/{dataset_id}/row/{row_id}.json", HttpMethod.POST),
	DATASET_ROW_UPDATE		("/datasets/{dataset_id}/row/{row_id}.json", HttpMethod.PUT),
	DATASET_ROW_REMOVE		("/datasets/{dataset_id}/row.json", HttpMethod.DELETE),
	LAYER_LIST				("/layers.json", HttpMethod.GET),
	LAYER_GET				("/layers/{layer_id}.json", HttpMethod.GET),
	LAYER_CREATE			("/layers", HttpMethod.POST),
	LAYER_UPDATE			("/layers", HttpMethod.PUT),
	LAYER_REMOVE			("/layers", HttpMethod.DELETE),
	LAYER_METADATA_UPDATE	("//layers/{layer_id}/metadata.json", HttpMethod.DELETE),
	COMPUTER_PRACTICE	("CPD03", HttpMethod.GET);


	private String code;
	private HttpMethod method;

	EnumRestAPIType(String code, HttpMethod method) {
		this.code = code;
		this.method = method;
	}

	public String code() {
		return code;
	}
	
	public HttpMethod method() {
		return method;
	}
	
	/**
	 * 코드값으로 키값을 찾는다.
	 * @param code
	 * @return
	 */
	public static String findKey(String code) {
		EnumRestAPIType[] codes = EnumRestAPIType.values();
		
		for (EnumRestAPIType value : codes) {
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
		EnumRestAPIType[] codes = EnumRestAPIType.values();
		for (EnumRestAPIType value : codes) {
			if (key.equals(value.name())) {
				return value.code();
			}
		}
		return null;
	}
	
	public static HttpMethod findMethod(String code) {
		EnumRestAPIType[] codes = EnumRestAPIType.values();
		
		for (EnumRestAPIType value : codes) {
			if (code.equals(value.code())) {
				return value.method();
			}
		}
		return null;
	}

}
