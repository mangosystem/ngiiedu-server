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
	DATASET_CREATE			("/datasets", HttpMethod.POST),
	DATASET_UPDATE			("/datasets/{dataset_id}.json", HttpMethod.PUT),
	DATASET_REMOVE			("/datasets/{dataset_id}.json", HttpMethod.DELETE),
	
	DATASET_ROW_LIST		("/datasets/{dataset_id}/row.json", HttpMethod.GET),
	DATASET_ROW_GET			("/datasets/{dataset_id}/row/{row_id}.json", HttpMethod.GET),
	DATASET_ROW_CREATE		("/datasets/{dataset_id}/row/{row_id}.json", HttpMethod.POST),
	DATASET_ROW_UPDATE		("/datasets/{dataset_id}/row/{row_id}.json", HttpMethod.PUT),
	DATASET_ROW_REMOVE		("/datasets/{dataset_id}/row.json", HttpMethod.DELETE),
	
	DATASET_COLUMN_LIST		("/datasets/{dataset_id}/column.json", HttpMethod.GET),
	DATASET_COLUMN_GET		("/datasets/{dataset_id}/column/{column_id}.json", HttpMethod.GET),
	DATASET_COLUMN_CREATE	("/datasets/{dataset_id}/column/{column_id}.json", HttpMethod.POST),
	DATASET_COLUMN_UPDATE	("/datasets/{dataset_id}/column/{column_id}.json", HttpMethod.PUT),
	DATASET_COLUMN_REMOVE	("/datasets/{dataset_id}/column.json", HttpMethod.DELETE),
	
	LAYER_LIST				("/layers.json", HttpMethod.GET),
	LAYER_GET				("/layers/{layer_id}.json", HttpMethod.GET),
	LAYER_CREATE			("/layers", HttpMethod.POST),
	LAYER_UPDATE			("/layers", HttpMethod.PUT),
	LAYER_REMOVE			("/layers", HttpMethod.DELETE),
	
	// 메타데이터 정보 변경
	LAYER_METADATA_UPDATE	("/layers/{layer_id}/metadata.json", HttpMethod.PUT),
	
	// 메타데이터 소스 변경
	LAYER_METADATA_SOURCE_UPDATE	("/layers/{layer_id}/source.json", HttpMethod.PUT),
	
	// 메타데이터 프로세스 변경
	LAYER_METADAT_PROCESS_UPDATE	("/layers/{layer_id}/process.json", HttpMethod.PUT),

	// 메타데이터 스타일 변경
	LAYER_METADATA_STYLING_UPDATE	("/layers/{layer_id}/styling.json", HttpMethod.PUT),

	// 레이어 상태 확인
	LAYER_JOB_STATUS	("/layers/{layer_id}/job_status.json", HttpMethod.GET),

	// 래이어 컬럼 목록
	LAYER_COLUMN_LIST	("/layers/{layer_id}/column.json", HttpMethod.GET),
	// 레이어 컬럼 정보
	LAYER_COLUMN_GET	("/layers/{layer_id}/column/{column_id}.json", HttpMethod.GET),
	// 레이어 데이터 값 분류
	LAYER_STYLING_CLASSIFY	("/layers/{layer_id}/styling/classify.json", HttpMethod.GET),
	// 지원프로세스 목록
	LAYER_MODULE_SUPPORT_PROCESS	("/layers/metadata/support_process.json", HttpMethod.GET),
	// 모듈 파라미터
	LAYER_MODULE_PROCESS_PARAM	("/layers/metadata/module_params.json", HttpMethod.GET),
	
	// Maps 목록
	MAPS_LIST	("/maps.json", HttpMethod.GET),
	// Maps 상세정보
	MAPS_GET	("/maps/{maps_id}.json", HttpMethod.GET),
	// Maps 생성
	MAPS_CREATE	("/maps.json", HttpMethod.POST),
	// Maps 수정
	MAPS_UPDATE	("/maps/{maps_id}.json", HttpMethod.POST),
	// Maps 삭제
	MAPS_REMOVE	("/maps/{maps_id}.json", HttpMethod.POST);

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
