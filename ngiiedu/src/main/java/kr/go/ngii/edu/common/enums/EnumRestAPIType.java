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
	PROJECT_CREATE			("/projects.json", HttpMethod.POST),
	PROJECT_UPDATE			("/projects/{project_id}.json", HttpMethod.PUT),
	PROJECT_REMOVE			("/projects/{project_id}.json", HttpMethod.DELETE),

	PROJECT_MEMBER_LIST		("/projects/{projrct_id}/member.json", HttpMethod.GET),
	PEOJECT_MEMBER_GET		("/projects/{project_id}/member/{member_id}.json", HttpMethod.GET),
	PROJECT_MEMBER_CREATE	("/projects/{project_id}/member.json", HttpMethod.POST),
	PROJECT_MEMBER_UPDATE	("/projects/{project_id}/member/{member_id}.json", HttpMethod.PUT),
	PROJECT_MEMBER_REMOVE	("/projects/{project_id}/member/{member_id}.json", HttpMethod.DELETE),
	
	DATASET_LIST			("/datasets.json", HttpMethod.GET),
	DATASET_GET				("/datasets/{dataset_id}.json", HttpMethod.GET),
	
	DATASET_CREATE			("/datasets", HttpMethod.POST),
	DATASET_EMPTY_CREATE	("/datasets/createEmpty", HttpMethod.POST),
	DATASET_UPLOAD_CREATE	("/datasets/createUpload", HttpMethod.POST),
	
	DATASET_BOUNDARYJOIN_CREATE	("/datasets/createBoundaryJoin", HttpMethod.POST),
	DATASET_ONLINE_CREATE	("/datasets/createOnlineDataset", HttpMethod.POST),

	DATASET_UPDATE			("/datasets/{dataset_id}.json", HttpMethod.PUT),
	DATASET_REMOVE			("/datasets/{dataset_id}.json", HttpMethod.DELETE),
	
	DATASET_ROW_LIST		("/datasets/{dataset_id}/row.json", HttpMethod.GET),
	DATASET_ROW_GET			("/datasets/{dataset_id}/row/{row_id}.json", HttpMethod.GET),
	DATASET_ROW_CREATE		("/datasets/{dataset_id}/row.json", HttpMethod.POST),
	DATASET_ROW_UPDATE		("/datasets/{dataset_id}/row/{row_id}.json", HttpMethod.POST),
	DATASET_ROW_REMOVE		("/datasets/{dataset_id}/row/{row_id}.json", HttpMethod.DELETE),
	
	DATASET_ROWUNIQUE_GET	("/datasets/{dataset_id}/rowUnique.json", HttpMethod.GET),

	DATASET_COLUMN_LIST		("/datasets/{dataset_id}/column.json", HttpMethod.GET),
	DATASET_COLUMN_GET		("/datasets/{dataset_id}/column/{column_id}.json", HttpMethod.GET),
	DATASET_COLUMN_CREATE	("/datasets/{dataset_id}/column/{column_id}.json", HttpMethod.POST),
	DATASET_COLUMN_UPDATE	("/datasets/{dataset_id}/column/{column_id}.json", HttpMethod.PUT),
	DATASET_COLUMN_REMOVE	("/datasets/{dataset_id}/column.json", HttpMethod.DELETE),

	DATASET_DOWNLOAD		("/datasets/{dataset_id}/download.json", HttpMethod.GET),
	
	DATASET_PRIVACY_UPDATE	("/datasets/{dataset_id}/privacy.json", HttpMethod.PUT),
		
	LAYER_LIST				("/layers.json", HttpMethod.GET),
	LAYER_GET				("/layers/{layer_id}.json", HttpMethod.GET),
	LAYER_CREATE			("/layers.json", HttpMethod.POST),
	LAYER_UPDATE			("/layers/{layer_id}.json", HttpMethod.PUT),
	LAYER_REMOVE			("/layers/{layer_id}.json", HttpMethod.DELETE),
	
	// 硫뷀��뜲�씠�꽣 �젙蹂� 蹂�寃�
	LAYER_METADATA_UPDATE	("/layers/{layer_id}/metadata.json", HttpMethod.PUT),
	
	// 硫뷀��뜲�씠�꽣 �냼�뒪 蹂�寃�
	LAYER_METADATA_SOURCE_UPDATE	("/layers/{layer_id}/source.json", HttpMethod.PUT),
	
	// 硫뷀��뜲�씠�꽣 �봽濡쒖꽭�뒪 蹂�寃�
	LAYER_METADAT_PROCESS_UPDATE	("/layers/{layer_id}/process.json", HttpMethod.PUT),

	// 硫뷀��뜲�씠�꽣 �뒪���씪 蹂�寃�
	LAYER_METADATA_STYLING_UPDATE	("/layers/{layer_id}/styling.json", HttpMethod.PUT),

	// �젅�씠�뼱 �긽�깭 �솗�씤
	LAYER_JOB_STATUS	("/layers/{layer_id}/job_status.json", HttpMethod.GET),

	// �옒�씠�뼱 而щ읆 紐⑸줉
	LAYER_COLUMN_LIST	("/layers/{layer_id}/column.json", HttpMethod.GET),
	// �젅�씠�뼱 而щ읆 �젙蹂�
	LAYER_COLUMN_GET	("/layers/{layer_id}/column/{column_id}.json", HttpMethod.GET),
	// �젅�씠�뼱 �뜲�씠�꽣 媛� 遺꾨쪟
	LAYER_STYLING_CLASSIFY	("/layers/{layer_id}/styling/classify.json", HttpMethod.GET),
	// 吏��썝�봽濡쒖꽭�뒪 紐⑸줉
	LAYER_MODULE_SUPPORT_PROCESS	("/layers/metadata/support_process.json", HttpMethod.GET),
	// 紐⑤뱢 �뙆�씪誘명꽣
	LAYER_MODULE_PROCESS_PARAM	("/layers/metadata/module_params.json", HttpMethod.GET),
	
	LAYER_PRIVACY_UPDATE	("/layers/{layer_id}/privacy.json", HttpMethod.PUT),
	
	// Maps 紐⑸줉
	MAPS_LIST	("/maps.json", HttpMethod.GET),
	// Maps �긽�꽭�젙蹂�
	MAPS_GET	("/maps/{maps_id}.json", HttpMethod.GET),
	// Maps �깮�꽦
	MAPS_CREATE	("/maps.json", HttpMethod.POST),
	// Maps �닔�젙
	MAPS_UPDATE	("/maps/{maps_id}.json", HttpMethod.PUT),
	// Maps �궘�젣
	MAPS_REMOVE	("/maps/{maps_id}.json", HttpMethod.DELETE),
	
	// Maps �븘�씠�뀥 紐⑸줉
	MAPS_ITEM_LIST ("/maps/{maps_id}/item.json", HttpMethod.GET),
	// Maps �븘�씠�뀥 �긽�꽭�젙蹂�
	MAPS_ITEM_GET ("/maps/{maps_id}/item/{item_id}.json", HttpMethod.GET),
	// Maps �븘�씠�뀥 �깮�꽦
	MAPS_ITEM_CREATE ("/maps/{maps_id}/item.json", HttpMethod.POST),
	// Maps �븘�씠�뀥 �닔�젙
//	MAPS_ITEM_UPDATE ("/maps/{maps_id}/item/{item_id}.json", HttpMethod.PUT),
	MAPS_ITEM_UPDATE ("/maps/{maps_id}/item/{item_id}.json", HttpMethod.POST),
	// Maps �븘�씠�뀥 �궘�젣
	MAPS_ITEM_REMOVE ("/maps/{maps_id}/item/{item_id}.json", HttpMethod.DELETE),

	// Maps �닚�꽌蹂�寃�
	MAPS_ITEMORDER_UPDATE ("/maps/{maps_id}/itemOrder.json", HttpMethod.PUT),
	
	MAPS_PRIVACY_UPDATE	("/layers/{dataset_id}/privacy.json", HttpMethod.PUT);

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
	 * 肄붾뱶媛믪쑝濡� �궎媛믪쓣 李얜뒗�떎.
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
	 * �궎媛믪쑝濡� 肄붾뱶瑜� 李얜뒗�떎.
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
