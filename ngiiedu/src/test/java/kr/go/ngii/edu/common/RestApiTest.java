package kr.go.ngii.edu.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.common.enums.EnumRestAPIType;
import kr.go.ngii.edu.config.LocalResourceBundle;
import kr.go.ngii.edu.controller.rest.ResponseData;
import kr.go.ngii.edu.main.common.RestAPIClient;
import kr.go.ngii.edu.main.courses.work.model.WorkOutput;
import kr.go.ngii.edu.main.courses.work.service.WorkOutputService;
import kr.go.ngii.edu.main.users.model.User;

public class RestApiTest extends BaseTest {

	@Autowired
	private WorkOutputService workOutputService;
	
	@Test
	public void testDatasetGet() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("dataset_id", "d=r7oFXBrCYl");
		Map<String, String> params = new HashMap<String, String>();
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_GET, uriParams, params);
		System.out.println(r);
	}
	
	@Test
	public void testDatasetCreate() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		Map<String, String> params = new HashMap<String, String>();
		params.put("project_id", "p=pppppppp");
		params.put("ufile", "p=pppppppp");
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_CREATE, uriParams, params);
		System.out.println(r);
	}
	
	
	@Test
	public void testDatasetUpdate() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("dataset_id", "d=r7oFXBrCYl");
		Map<String, String> params = new HashMap<String, String>();
//		params.put("title", "�븰援먯＜蹂� �냼�쓬吏��룄 �뜲�씠�꽣 �닔吏�");
		params.put("description", "cc");
		params.put("metadata", "aa");
//		params.put("privacy", "private");
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_UPDATE, uriParams, params);
		System.out.println(r);
	}
	
	
	@Test
	public void testDatasetRowList() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("dataset_id", "d=r7oFXBrCYl");
//		uriParams.put("row_id", "12");
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("content", "cc");
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_ROW_LIST, uriParams);
		System.out.println(r);
	}
	
	@Test
	public void testDatasetRowGet() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("dataset_id", "d=r7oFXBrCYl");
		uriParams.put("row_id", "12");
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("content", "cc");
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_ROW_GET, uriParams);
		System.out.println(r);
	}
	
	@Test
	public void testDatasetRowDelete() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("dataset_id", "d=KjCXc4dmy9");
		uriParams.put("row_id", "9");
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("content", "cc");
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_ROW_REMOVE, uriParams);
		System.out.println(r);
	}
	
	
	@Test
	public void testDatasetRowUpdate() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("dataset_id", "d=r7oFXBrCYl");
		uriParams.put("row_id","278" );
		Map<String, String> params = new HashMap<String, String>();
		String content = "{\"kr_np_golf\":{\"type\":\"LONG\",\"value\":2},\"cat\":{\"type\":\"STRING\",\"value\":\"醫낅줈援�`````\"},\"nam\":{\"type\":\"STRING\",\"value\":\"醫낅줈援�`````\"},\"addr\":{\"type\":\"STRING\",\"value\":\"醫낅줈援�`````\"},\"lon\":{\"type\":\"DOUBLE\",\"value\":126},\"lat\":{\"type\":\"DOUBLE\",\"value\":34},\"udate\":{\"type\":\"STRING\",\"value\":\"STRING\"},\"noise_value\":{\"type\":\"INTEGER\",\"value\":1},\"noise_zone\":{\"type\":\"STRING\",\"value\":\"STRING\"},\"noise_level\":{\"type\":\"INTEGER\",\"value\":1},\"survey_dn\":{\"type\":\"STRING\",\"value\":\"STRING\"},\"work_id\":{\"type\":\"INTEGER\",\"value\":1},\"create_team_id\":{\"type\":\"INTEGER\",\"value\":1},\"create_mem_id\":{\"type\":\"INTEGER\",\"value\":1},\"create_date\":{\"type\":\"TIMESTAMP\",\"value\":null}}";
		URLCodec codec = new URLCodec();
		try {
			params.put("content", codec.encode(content));
		} catch (EncoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		params.put("content", "{\"kr_np_golf\":{\"type\":\"LONG\",\"value\":5}," + 
//				"\"cat\":{\"type\":\"STRING\",\"value\":\"醫낅줈援�`````\"}," + 
//				"\"nam\":{\"type\":\"STRING\",\"value\":\"醫낅줈援�`````\"}," + 
//				"\"addr\":{\"type\":\"STRING\",\"value\":\"醫낅줈援�`````\"}," + 
//				"\"lon\":{\"type\":\"DOUBLE\",\"value\":126}," + 
//				"\"lat\":{\"type\":\"DOUBLE\",\"value\":\"36\"}," + 
//				"\"udate\":{\"type\":\"STRING\",\"value\":null}," + 
//				"\"noise_value\":{\"type\":\"INTEGER\",\"value\":null}," + 
//				"\"noise_zone\":{\"type\":\"STRING\",\"value\":null}," + 
//				"\"noise_level\":{\"type\":\"INTEGER\",\"value\":null}," + 
//				"\"survey_dn\":{\"type\":\"STRING\",\"value\":null}," + 
//				"\"work_id\":{\"type\":\"INTEGER\",\"value\":null}," + 
//				"\"create_team_id\":{\"type\":\"INTEGER\",\"value\":null}," + 
//				"\"create_mem_id\":{\"type\":\"INTEGER\",\"value\":null}," + 
//				"\"create_date\":{\"type\":\"TIMESTAMP\",\"value\":null}" + 
//				"}");
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_ROW_UPDATE, uriParams, params);
		System.out.println(r);
	}
	
	@Test
	public void testDatasetRowCreate() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("dataset_id", "d=KjCXc4dmy9");
		Map<String, String> params = new HashMap<String, String>();
		params.put("content", "{\"noise_value\":51,\"noise_zone\":\"a\",\"noise_level\":2,\"survey_dn\":\"洹쇰같諛붾낫\",\"course_id\":null,\"work_id\":null,\"create_team_id\":null,\"create_mem_id\":null,\"create_date\":\"\",\"the_geom\":\"POINT(126.97444438934404 37.396567745417215)\"}");
//		params.put("content","{\"the_geom\": \"POINT(126.588866114616 33.3103962893675)\", \"kr_np_golf\": \"testtest\"}" );
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_ROW_CREATE, "/datasets/" +"d=KjCXc4dmy9" +"/row.json", params);
		System.out.println(r);
	}
	
	
	@Test
	public void testDataseColumnList() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("dataset_id", "l=AnyangDong");
//		uriParams.put("row_id", "d=r7oFXBrCYl");
//		uriParams.put("dataset_id", "d=r7oFXBrCYl");
		Map<String, String> params = new HashMap<String, String>();
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_COLUMN_LIST, uriParams);
		System.out.println(r);
	}
	
	@Test
	public void testLayerGet() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("layer_id", "l=oBGRzS7ijB");
		Map<String, String> params = new HashMap<String, String>();
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.LAYER_GET, uriParams);
		System.out.println(r);
	}
	
	@Test
	public void testLayerCreate() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		Map<String, String> params = new HashMap<String, String>();
		params.put("project_id", "p=pppppppp");
		params.put("title", "test111");
		params.put("sources", mapToString(jsonCreateTest()));
//		params.put("sources", "{\"inputDataset\":{\"type\":\"dataset\",\"datasetId\":\"d=r7oFXBrCYl\",\"filter\":[]}}");
//		params.put("sources", "{inputDataset={type=dataset,datasetId=d=r7oFXBrCYl,filter=[]}}");
//		params.put("sources", "{inputDataset:{type=dataset,datasetId:d=r7oFXBrCYl,filter=[]}}");
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.LAYER_CREATE, uriParams, params);
		System.out.println(r);
	}
	
	
	public Map<String, ?> stringToMap(String jsonString) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(jsonString, new TypeReference<HashMap<String, ?>>(){} );
		} catch (IOException e) {
			return null;
		}
	}

	public String mapToString(Map<String, ?> map) {
		try {
			return new ObjectMapper().writeValueAsString(map);
		} catch (Exception e) {
			return null;
		}
	}
	
	public Map<String, Object> jsonCreateTest() {
        
		ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> param = new HashMap<>();
        Map<String, Object> mParam = new HashMap<>();
        ArrayNode filterArray = mapper.createArrayNode();
        param.put("type", "dataset");
        param.put("datasetId", "d=AnyangDong");
        param.put("filter", filterArray);
        mParam.put("inputDataset", param);
//        System.out.println(mapToString(param));
 
 
        return mParam;
	}
	
	public String jacksonJsonCreateTest() {
        
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		ObjectNode sourceNode = mapper.createObjectNode();
        ObjectNode inputDatasetNode = mapper.createObjectNode();
        ArrayNode filterArray = mapper.createArrayNode();
        inputDatasetNode.put("type", "dataset");
        inputDatasetNode.put("datasetId", "d=AnyangDong");
        inputDatasetNode.put("filter", filterArray);
        sourceNode.put("inputDataset", inputDatasetNode);
 
        System.out.println(sourceNode.toString());
 
        try {
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sourceNode));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return sourceNode.asText();

	}
	
	@Test
	public void testLayerDelete() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("layer_id", "l=oBGRzS7ijB");
		Map<String, String> params = new HashMap<String, String>();
		params.put("project_id", "p=pppppppp");
		params.put("confirm_title", "");
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.LAYER_REMOVE, uriParams, params);
		System.out.println(r);
	}
	
	
	@Test
	public void testLayerUpdateMetaData() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("layer_id", "l=oBGRzS7ijB");
		Map<String, String> params = new HashMap<String, String>();
		params.put("project_id", "p=pppppppp");
		params.put("title", "nanana");
		params.put("description", "nanana");
		params.put("metadata", "nan");
		params.put("privacy", "PRIVATE");
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.LAYER_METADATA_UPDATE, uriParams, params);
		System.out.println(r);
	}
	
	
	@Test
	public void testLayerUpdateProcess() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("layer_id", "l=oBGRzS7ijB");
		Map<String, String> params = new HashMap<String, String>();
		params.put("process", "");
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.LAYER_METADAT_PROCESS_UPDATE, uriParams, params);
		System.out.println(r);
	}
	
	@Test
	public void testLayerUpdateStyling() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("layer_id", "l=oBGRzS7ijB");
		Map<String, String> params = new HashMap<String, String>();
		params.put("styling", "");
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.LAYER_METADATA_STYLING_UPDATE, uriParams, params);
		System.out.println(r);
	}
	
	@Test
	public void testLayerColumnList() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("layer_id", "l=oBGRzS7ijB");
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.LAYER_COLUMN_LIST, uriParams);
		System.out.println(r);
	}
	
	@Test
	public void testMapsGet() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("maps_id", "m=XAjbWilXu0");
		Map<String, String> params = new HashMap<String, String>();
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.MAPS_GET, uriParams);
		
		System.out.println(r);
		Map<String, Object> r2 = (Map<String, Object>) r.get("data");
		System.out.println(r2);
		System.out.println((int) r2.get("id"));
		System.out.println((String) r2.get("projectId"));
		System.out.println((String) r2.get("mapsId"));
		System.out.println((String) r2.get("title"));
		System.out.println((String) r2.get("description"));
		System.out.println((String) r2.get("metadata"));
		System.out.println((String) r2.get("typeKind"));
		System.out.println((String) r2.get("privacy"));
	}
	
	@Test
	public void testMapsCreate() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("project_id", LocalResourceBundle.PINOGIO_API_PROJECT_ID);
		uriParams.put("title", "aaa");
		uriParams.put("description", "a");
		uriParams.put("maps_type", "STORY");
		uriParams.put("privacy", "PUBLIC");
		uriParams.put("metadata", "null");
		uriParams.put("type_kind", "TAB");
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.MAPS_CREATE, "/maps.json", uriParams);
		System.out.println(r);
		WorkOutput workOutputResult = workOutputService.create(25, "1",  r, 40, "maps", "true", "false");
		System.out.println(workOutputResult);
	}
	
	@Test
	public void testMapsUpdate() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("maps_id", "m=Al4uSj6hAh");
		Map<String, String> params = new HashMap<String, String>();
		params.put("project_id", LocalResourceBundle.PINOGIO_API_PROJECT_ID);
		params.put("title", "asd");
		params.put("description", "a");
		params.put("maps_type", "STORY");
		params.put("privacy", "PUBLIC");
		params.put("metadata", "null");
		params.put("type_kind", "TAB");
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.MAPS_UPDATE, uriParams, params);
		System.out.println(r);
		Map<String, String> metaData = (Map<String, String>) r.get("meta");
		
		String metaDataMessage = metaData.get("message");
		
		System.out.println(metaDataMessage);
		
//		System.out.println((int) r2.get("id"));
//		System.out.println((String) r2.get("projectId"));
//		System.out.println((String) r2.get("mapsId"));
//		System.out.println((String) r2.get("title"));
//		System.out.println((String) r2.get("description"));
//		System.out.println((String) r2.get("metadata"));
//		System.out.println((String) r2.get("typeKind"));
//		System.out.println((String) r2.get("privacy"));
	}
	
	
	
	@Test
	public void testMapsItemUpdate() {
		RestAPIClient rc = new RestAPIClient();
		
		String mapsId = "m=XAjbWilXu0";
		String itemId = "21";
		String title = "ttt";
		String description = "";
		String metadata = "";
		String baseLayer = "";
		String pinoLayer = "";
		String mapOptions = "";
		
		
		
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("maps_id", mapsId);
		pathParamVals.put("item_id", itemId);
		
		Map<String, String> paramVals = new HashMap<String,String>();

		Map<String, Object> mapsItemGetResult = rc.getResponseBody(EnumRestAPIType.MAPS_ITEM_GET, pathParamVals, paramVals);
		Map<String, Object> mapsItemGetResultData = (Map<String, Object>) mapsItemGetResult.get("data");
		
		title = "".equals(title) ? (String) mapsItemGetResultData.get("title") : title;
		description = "".equals(description) ? (String) mapsItemGetResultData.get("description") : description;
		metadata = "".equals(metadata) ? (String) mapsItemGetResultData.get("metadata") : metadata;
		baseLayer = "".equals(baseLayer) ? (String) mapsItemGetResultData.get("baseLayer") : baseLayer;
		pinoLayer = "".equals(pinoLayer) ? (String) mapsItemGetResultData.get("pinoLayer") : pinoLayer;
		mapOptions = "".equals(mapOptions) ? (String) mapsItemGetResultData.get("mapOptions") : mapOptions;
		
		paramVals.put("title", title);
		paramVals.put("description", description);
//		paramVals.put("description", description.replaceAll("/", "%2F"));
		paramVals.put("metadata", metadata);
		paramVals.put("base_layer", baseLayer);
		paramVals.put("pino_layer", pinoLayer);
		paramVals.put("map_options", mapOptions);

		Map<String, Object> updateResult = rc.getResponseBody(EnumRestAPIType.MAPS_ITEM_UPDATE, pathParamVals, paramVals);
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_ITEM_UPDATE, "/maps/"+mapsId+"/item/"+ itemId +".json", paramVals);
		Map<String, String> metaData = (Map<String, String>) updateResult.get("meta");
		String metaDataMessage = metaData.get("message");
		if ("Updated".equalsIgnoreCase(metaDataMessage)) {
			Map<String, Object> result = rc.getResponseBody(EnumRestAPIType.MAPS_ITEM_GET, pathParamVals, null);
			System.out.println(result);
		} else {
		}
	}
	
	@Test
	public void testProjectsCreate() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> pathParam = new HashMap<String, String>();
		Map<String, String> param = new HashMap<String, String>();
		param.put("title", "asd");
		param.put("description", "aa");
		param.put("privacy", "TEAM");
		
		String apiKey = "cacbf08b5c7a4b49a1d06ecb8c0278af";
		Map<String, Object> r = rc.getResponseBodyWithLinkedMap(EnumRestAPIType.PROJECT_CREATE, pathParam, param, apiKey);
		System.out.println(r);
		Map<String, String> metaData = (Map<String, String>) r.get("meta");
		String metaDataMessage = metaData.get("message");
		System.out.println(metaDataMessage);
	}
	
	@Test
	public void testProjectsMemberCreate() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> pathParam = new HashMap<String, String>();
		pathParam.put("project_id", "p=3JC65hTd");
		Map<String, String> param = new HashMap<String, String>();
		param.put("member_id", "13");
		param.put("member_role", "WRITER");
		
		String apiKey = "a62016b8459b411da642f1ef55b6be3e";
		Map<String, Object> r = rc.getResponseBodyWithLinkedMap(EnumRestAPIType.PROJECT_MEMBER_CREATE, pathParam, param, apiKey);
		System.out.println(r);
		Map<String, String> metaData = (Map<String, String>) r.get("meta");
		String metaDataMessage = metaData.get("message");
		System.out.println(metaDataMessage);
	}
	
	@Test
	public void testProjectsMemberCreate2() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> pathParam = new HashMap<String, String>();
		pathParam.put("project_id", "p=3JC65hTd");
		Map<String, String> param = new HashMap<String, String>();
		param.put("member_id", "14");
		param.put("member_role", "WRITER");
		
		String apiKey = "a62016b8459b411da642f1ef55b6be3e";
		Map<String, Object> r = rc.getResponseBodyWithLinkedMap(EnumRestAPIType.PROJECT_MEMBER_CREATE, pathParam, param, apiKey);
		System.out.println(r);
		Map<String, String> metaData = (Map<String, String>) r.get("meta");
		String metaDataMessage = metaData.get("message");
		System.out.println(metaDataMessage);
	}
	
	@Test
	public void testProjectsMemberGet() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> pathParam = new HashMap<String, String>();
		pathParam.put("project_id", "p=3JC65hTd");
		Map<String, String> param = new HashMap<String, String>();
		param.put("member_id", "11");
//		param.put("member_role", "WRITER");
		
		String apiKey = "a62016b8459b411da642f1ef55b6be3e";
		Map<String, Object> r = rc.getResponseBodyWithLinkedMap(EnumRestAPIType.PEOJECT_GET, pathParam, param, apiKey);
		System.out.println(r);
		Map<String, String> metaData = (Map<String, String>) r.get("meta");
		String metaDataMessage = metaData.get("message");
		System.out.println(metaDataMessage);
	}
	
	
}
