package kr.go.ngii.edu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.go.ngii.edu.common.enums.EnumRestAPIType;
import kr.go.ngii.edu.config.LocalResourceBundle;
import kr.go.ngii.edu.controller.rest.BaseController;
import kr.go.ngii.edu.controller.rest.ResponseData;
import kr.go.ngii.edu.main.common.RestAPIClient;
import kr.go.ngii.edu.main.courses.work.service.WorkOutputService;
import kr.go.ngii.edu.main.users.model.User;

@Controller
@RequestMapping(value="/api/v1/coursesWork")
public class CourseWorkController extends BaseController {

	private RestAPIClient apiClient = new RestAPIClient();

	@Autowired
	private WorkOutputService workOutputService;

	//	@RequestMapping(value="/layer", method=RequestMethod.GET)
	//	public @ResponseBody ResponseEntity<ResponseData> listLayer(
	//			@RequestParam(value="courseId", required=false) String courseId) throws Exception {
	//		return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
	//	}

	/**
	 * localhost:8080/ngiiedu/api/v1/coursesWork/layers/l=oBGRzS7ijB.json
	 * 
	 * @param layerId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/layers/{layer_id}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getLayer(
			@PathVariable(value="layer_id", required=false) String layerId,
			HttpSession session) throws Exception {

		Map<String, String> paramPath = new HashMap<String,String>();
		paramPath.put("layer_id", layerId);

		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_GET, paramPath);

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 
	 * localhost:8080/ngiiedu/api/v1/coursesWork/layers.json?title=제목&sources={"inputDataset":{"filter":[],"datasetId":"d=r7oFXBrCYl","type":"dataset"}}
	 * 
	 * @param courseWorkSubId
	 * @param title
	 * @param sources
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/layers", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> createLayer(
			@RequestParam(value="courseWorkSubId", required=true) int courseWorkSubId,
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="sources", required=false) String sources,
			HttpSession session) throws Exception {

		User user = (User)session.getAttribute("USER_INFO");
		Map<String, String> paramVals = new HashMap<String,String>();
		paramVals.put("project_id", LocalResourceBundle.PINOGIO_API_PROJECT_ID);
		paramVals.put("title", title);
		paramVals.put("sources", sources);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_CREATE, "/layers", paramVals);

		// output division 추가..필요
		workOutputService.create(courseWorkSubId, "1",  result, user.getIdx(), "layer");

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 
	 * localhost:8080/ngiiedu/api/v1/coursesWork/layers.json
	 * 
	 * @param layerId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/layers/{layer_id}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> deleteLayer(
			@PathVariable(value="layer_id", required=false) String layerId,
			@RequestParam(value="works_output_id", required=true) int worksOutputId,
			HttpSession session) throws Exception {

		Map<String, String> paramVals = new HashMap<String,String>();

		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_REMOVE, "/layers/"+layerId, paramVals);

		workOutputService.delete(worksOutputId);

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 
	 * localhost:8080/ngiiedu/api/v1/coursesWork/layers/l=atucjVOa0O/metadata.json?title=한글은?!&description&metadata&privacy=FRIEND
	 * 
	 * @param layerId
	 * @param title
	 * @param description
	 * @param metadata
	 * @param privacy
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/layers/{layer_id}/metadata", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> updateMetadata(
			@PathVariable("layer_id") String layerId,
			@RequestParam(value="title", required=false, defaultValue="제목없음") String title,
			@RequestParam(value="description", required=false, defaultValue="") String description,
			@RequestParam(value="metadata", required=false, defaultValue="") String metadata,
			@RequestParam(value="privacy", required=false, defaultValue="PUBLIC") String privacy,
			HttpSession session) throws Exception {

		Map<String, String> paramVals = new HashMap<String,String>();
		paramVals.put("title", title);
		paramVals.put("description", description);
		paramVals.put("metadata", metadata);
		paramVals.put("privacy", privacy);

		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_METADATA_UPDATE, "/layers/"+layerId+"/metadata", paramVals);

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 
	 * localhost:8080/ngiiedu/api/v1/coursesWork/layers/l=H6WMCoLPPj/process.json?process={"identifier":"hexagonBinning","options":{"sideLen":"5000","validGrid":"false"},"inputDataset":{"type":"dataset","datasetId":"d=r7oFXBrCYl","filter":[]}}
	 * 
	 * @param layerId
	 * @param process
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/layers/{layer_id}/process", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> updateProcess(
			@PathVariable("layer_id") String layerId,
			@RequestParam(value="process", required=true) String process,
			HttpSession session) throws Exception {

		Map<String, String> paramVals = new HashMap<String,String>();
		paramVals.put("process", process);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_METADAT_PROCESS_UPDATE, "/layers/"+layerId+"/process", paramVals);

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 
	 * @param layerId
	 * @param styling
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/layers/{layer_id}/styling", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> updateStyling(
			@PathVariable("layer_id") String layerId, 
			@RequestParam(value="styling", required=true) String styling,
			HttpSession session) throws Exception {

		Map<String, String> paramVals = new HashMap<String,String>();
		paramVals.put("styling", styling);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_METADATA_STYLING_UPDATE, "/layers/"+layerId+"/styling", paramVals);

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 
	 * @param layerId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/layers/{layer_id}/column", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> listColumn(
			@PathVariable("layer_id") String layerId, 
			HttpSession session) throws Exception {

		Map<String, String> paramVals = new HashMap<String,String>();
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_COLUMN_LIST, "/layers/"+layerId+"/column", paramVals);

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}


	// --------------------------------------------------------------------------------------------------------------------------------------------
	//	@RequestMapping(value="/layer", method=RequestMethod.GET)
	//	public @ResponseBody ResponseEntity<ResponseData> listLayer(
	//			@RequestParam(value="courseId", required=false) String courseId) throws Exception {
	//		return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
	//	}

	/**
	 * 
	 * @param dataset_id
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset/{dataset_id}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getDataset(
			@PathVariable(value="dataset_id", required=false) String datasetId,
			HttpSession session) throws Exception {

		Map<String, String> paramPath = new HashMap<String,String>();
		paramPath.put("dataset_id", datasetId);

		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_GET, paramPath);

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 
	 * 
	 * @param courseWorkSubId
	 * @param title
	 * @param sources
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> createDataset(
			@RequestParam(value="courseWorkSubId", required=true) int courseWorkSubId,
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="sources", required=false) String sources,
			HttpSession session) throws Exception {

		User user = (User)session.getAttribute("USER_INFO");
		Map<String, String> paramVals = new HashMap<String,String>();
		paramVals.put("project_id", LocalResourceBundle.PINOGIO_API_PROJECT_ID);
		paramVals.put("title", title);
		paramVals.put("sources", sources);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_CREATE, "/dataset", paramVals);

		workOutputService.create(courseWorkSubId, "1",  result, user.getIdx(), "layer");

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 
	 * localhost:8080/ngiiedu/api/v1/coursesWork/layers.json
	 * 
	 * @param layerId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset/{dataset_id}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> deleteDataset(
			@PathVariable(value="layer_id", required=false) String layerId,
			@RequestParam(value="works_output_id", required=true) int worksOutputId,
			HttpSession session) throws Exception {

		Map<String, String> paramVals = new HashMap<String,String>();

		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_REMOVE, "/dataset/"+layerId, paramVals);

		workOutputService.delete(worksOutputId);

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}


	/**
	 * DataSet row 목록 조회
	 * 
	 * @param courseWorkId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset/row/list", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> datasetCreate(
			@RequestParam(value="pinogioOutputId", required=true) String pinogioOutputId, 
			HttpSession session) throws Exception {

		RestAPIClient rc = new RestAPIClient();
		Map<String, String> param = new HashMap<String, String>();
		//		uriParams.put("dataset_id", "d=r7oFXBrCYl");
		param.put("dataset_id", pinogioOutputId);
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_ROW_LIST, param);
		return new ResponseEntity<ResponseData>(responseBody(r), HttpStatus.OK);
	}



	/**
	 * DataSet row 조회
	 * 
	 * @param courseWorkId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset/row", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> datasetRowGet(
			@RequestParam("pinogioOutputId") String pinogioOutputId, 
			@RequestParam("rowId") String rowId, 
			HttpSession session) throws Exception {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("dataset_id", pinogioOutputId);
		uriParams.put("row_id", rowId);
		//		uriParams.put("dataset_id", "d=r7oFXBrCYl");
		//		uriParams.put("row_id", "12");
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_ROW_GET, uriParams);
		System.out.println(r);
		return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
	}


	/**
	 * DataSet row 입력
	 * 
	 * @param courseWorkId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset/row", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> datasetRowCreate(
			@RequestParam("pinogioOutputId") String pinogioOutputId, 
			@RequestParam("contentJson") String contentJson, 
			HttpSession session) throws Exception {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> params = new HashMap<String, String>();
		params.put("content", contentJson);
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_ROW_CREATE, "/datasets/"+pinogioOutputId+"/row.json", params);
		return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
	}


	/**
	 * DataSet row 수정
	 * 
	 * @param courseWorkId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset/row", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> datasetRowModify(
			@RequestParam("pinogioOutputId") String pinogioOutputId, 
			@RequestParam("rowId") String rowId,
			@RequestParam("contentJson") String contentJson, // pngo_ 테이블 참조
			HttpSession session) throws Exception {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("dataset_id", pinogioOutputId);
		uriParams.put("row_id", rowId);
		Map<String, String> params = new HashMap<String, String>();
		params.put("content", contentJson);
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_ROW_UPDATE, "/datasets/" + pinogioOutputId+ "/row"+ rowId +".json", params);
		return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
	}

	/**
	 * DataSet row 삭제
	 * 
	 * @param courseWorkId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset/row", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> datasetRowDelete(
			@RequestParam(value="pinogioOutputId", required=true) String pinogioOutputId, 
			@RequestParam(value="rowId", required=true) String rowId, 
			HttpSession session) throws Exception {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("dataset_id", pinogioOutputId);
		uriParams.put("row_id", rowId);
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_ROW_REMOVE, uriParams);
		return new ResponseEntity<ResponseData>(responseBody(r), HttpStatus.OK);
	}


	/**
	 * DataSet column 목록 조회
	 * 
	 * @param courseWorkId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset/column/list", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> datasetColumnList(
			@RequestParam(value="pinogioOutputId", required=true) String pinogioOutputId, 
			HttpSession session) throws Exception {

		RestAPIClient rc = new RestAPIClient();
		Map<String, String> param = new HashMap<String, String>();
		param.put("dataset_id", pinogioOutputId);
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_COLUMN_LIST, param);
		return new ResponseEntity<ResponseData>(responseBody(r), HttpStatus.OK);
	}


	// maps
	/**
	 * 
	 * Maps 조회
	 * 
	 * @param layerId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/maps/{maps_id}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getMaps(
			@PathVariable(value="maps_id", required=false) String mapsId,
			HttpSession session) throws Exception {

		Map<String, String> paramPath = new HashMap<String,String>();
		paramPath.put("maps_id", mapsId);

		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_GET, paramPath);

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 
	 * Maps 입력
	 * 
	 * title 제목
	 * description 설명
	 * maps_type "SERIES" || ....
	 * privacy "PUBLIC" || "FRIEND" || ...
	 * metadata {"type":"tabs"} || {"type":"accordion"}
	 * 
	 * 
	 * @param courseWorkSubId
	 * @param title
	 * @param sources
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/maps", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> creaMaps(
			@RequestParam(value="courseWorkSubId", required=true) int courseWorkSubId,
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="description", required=false) String description,
			@RequestParam(value="maps_type", required=false) String mapsType,
			@RequestParam(value="privacy", required=false) String privacy,
			@RequestParam(value="metadata", required=false) String metadata,
			HttpSession session) throws Exception {

		User user = (User)session.getAttribute("USER_INFO");
		Map<String, String> paramVals = new HashMap<String,String>();
		paramVals.put("project_id", LocalResourceBundle.PINOGIO_API_PROJECT_ID);
		paramVals.put("title", title);
		paramVals.put("description", description);
		paramVals.put("maps_type", mapsType);
		paramVals.put("privacy", privacy);
		paramVals.put("metadata", metadata);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_CREATE, "/maps", paramVals);

		// output division 추가..필요
		workOutputService.create(courseWorkSubId, "1",  result, user.getIdx(), "maps");

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 
	 * 
	 * @param maps_id
	 * @param courseWorkSubId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/maps/{maps_id}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> deleteMaps(
			@PathVariable(value="maps_id", required=false) String mapsId,
			@RequestParam(value="works_output_id", required=true) int worksOutputId,
			HttpSession session) throws Exception {

		Map<String, String> paramVals = new HashMap<String,String>();

		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_REMOVE, "/maps/"+mapsId+".json", paramVals);

		workOutputService.delete(worksOutputId);

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 
	 * 
	 * @param maps_id
	 * @param title
	 * @param description
	 * @param metadata
	 * @param privacy
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/maps/{maps_id}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> updateMaps(
			@PathVariable("maps_id") String mapsId,
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="description", required=false) String description,
			@RequestParam(value="maps_type", required=false) String mapsType,
			@RequestParam(value="privacy", required=false) String privacy,
			@RequestParam(value="metadata", required=false) String metadata,
			HttpSession session) throws Exception {

		Map<String, String> paramVals = new HashMap<String,String>();
		paramVals.put("project_id", LocalResourceBundle.PINOGIO_API_PROJECT_ID);
		paramVals.put("title", title);
		paramVals.put("description", description);
		paramVals.put("maps_type", mapsType);
		paramVals.put("privacy", privacy);
		paramVals.put("metadata", metadata);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_UPDATE, "/maps/"+mapsId+".json", paramVals);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping(value="/maps/{maps_id}/item", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> listMapsItem(
			@PathVariable("maps_id") String mapsId,
			HttpSession session) throws Exception {
		
		Map<String, String> paramVals = new HashMap<String,String>();

		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_ITEM_LIST, "/maps/"+mapsId+"/item.json", paramVals);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	@RequestMapping(value="/maps/{maps_id}/item/{item_id}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getMapsItem(
			@PathVariable("maps_id") String mapsId,
			@PathVariable("item_id") String itemId,
			HttpSession session) throws Exception {
		
		Map<String, String> paramVals = new HashMap<String,String>();

		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_ITEM_GET, "/maps/"+mapsId+"/item/"+ itemId +".json", paramVals);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	@RequestMapping(value="/maps/{maps_id}/item", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> createMapsItem(
			@PathVariable("maps_id") String mapsId,
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="description", required=false) String description,
			@RequestParam(value="metadata", required=false) String metadata,
			HttpSession session) throws Exception {
		
		Map<String, String> paramVals = new HashMap<String,String>();
		paramVals.put("title", title);
		paramVals.put("description", description);
		paramVals.put("metadata", metadata);
		
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_ITEM_CREATE, "/maps/"+mapsId+"/item.json", paramVals);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	@RequestMapping(value="/maps/{maps_id}/item/{item_id}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> updateMapsItem(
			@PathVariable("maps_id") String mapsId,
			@PathVariable("item_id") String itemId,
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="description", required=false) String description,
			@RequestParam(value="metadata", required=false) String metadata,
			HttpSession session) throws Exception {
		
		Map<String, String> paramVals = new HashMap<String,String>();
		paramVals.put("title", title);
		paramVals.put("description", description);
		paramVals.put("metadata", metadata);

		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_ITEM_UPDATE, "/maps/"+mapsId+"/item/"+ itemId +".json", paramVals);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
		
	@RequestMapping(value="/maps/{maps_id}/item/{item_id}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> deleteMapsItem(
			@PathVariable("maps_id") String mapsId,
			@PathVariable("item_id") String itemId,
			HttpSession session) throws Exception {
		
		Map<String, String> paramVals = new HashMap<String,String>();

		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_ITEM_REMOVE, "/maps/"+mapsId+"/item/"+ itemId +".json", paramVals);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
}
