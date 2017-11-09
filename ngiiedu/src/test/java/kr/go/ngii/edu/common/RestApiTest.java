package kr.go.ngii.edu.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.junit.Test;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.core.JsonParser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.common.enums.EnumRestAPIType;
import kr.go.ngii.edu.main.common.RestAPIClient;

public class RestApiTest extends BaseTest {

	
	
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
	public void testDatasetUpdate() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("dataset_id", "d=r7oFXBrCYl");
		Map<String, String> params = new HashMap<String, String>();
//		params.put("title", "학교주변 소음지도 데이터 수집");
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
		String content = "{\"kr_np_golf\":{\"type\":\"LONG\",\"value\":2},\"cat\":{\"type\":\"STRING\",\"value\":\"종로구`````\"},\"nam\":{\"type\":\"STRING\",\"value\":\"종로구`````\"},\"addr\":{\"type\":\"STRING\",\"value\":\"종로구`````\"},\"lon\":{\"type\":\"DOUBLE\",\"value\":126},\"lat\":{\"type\":\"DOUBLE\",\"value\":34},\"udate\":{\"type\":\"STRING\",\"value\":\"STRING\"},\"noise_value\":{\"type\":\"INTEGER\",\"value\":1},\"noise_zone\":{\"type\":\"STRING\",\"value\":\"STRING\"},\"noise_level\":{\"type\":\"INTEGER\",\"value\":1},\"survey_dn\":{\"type\":\"STRING\",\"value\":\"STRING\"},\"work_id\":{\"type\":\"INTEGER\",\"value\":1},\"create_team_id\":{\"type\":\"INTEGER\",\"value\":1},\"create_mem_id\":{\"type\":\"INTEGER\",\"value\":1},\"create_date\":{\"type\":\"TIMESTAMP\",\"value\":null}}";
		URLCodec codec = new URLCodec();
		try {
			params.put("content", codec.encode(content));
		} catch (EncoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		params.put("content", "{\"kr_np_golf\":{\"type\":\"LONG\",\"value\":5}," + 
//				"\"cat\":{\"type\":\"STRING\",\"value\":\"종로구`````\"}," + 
//				"\"nam\":{\"type\":\"STRING\",\"value\":\"종로구`````\"}," + 
//				"\"addr\":{\"type\":\"STRING\",\"value\":\"종로구`````\"}," + 
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
		params.put("content", "{\"noise_value\":51,\"noise_zone\":\"a\",\"noise_level\":2,\"survey_dn\":\"근배바보\",\"course_id\":null,\"work_id\":null,\"create_team_id\":null,\"create_mem_id\":null,\"create_date\":\"\",\"the_geom\":\"POINT(126.97444438934404 37.396567745417215)\"}");
//		params.put("content","{\"the_geom\": \"POINT(126.588866114616 33.3103962893675)\", \"kr_np_golf\": \"testtest\"}" );
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_ROW_CREATE, "/datasets/" +"d=KjCXc4dmy9" +"/row.json", params);
		System.out.println(r);
	}
	
	
	@Test
	public void testDataseColumnList() {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("dataset_id", "d=r7oFXBrCYl");
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
        param.put("datasetId", "d=r7oFXBrCYl");
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
        inputDatasetNode.put("datasetId", "d=r7oFXBrCYl");
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
}
