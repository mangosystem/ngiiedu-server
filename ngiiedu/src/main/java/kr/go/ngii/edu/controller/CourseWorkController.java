package kr.go.ngii.edu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

@Controller
@RequestMapping(value="/api/v1/coursesWork")
public class CourseWorkController extends BaseController {

	private RestAPIClient apiClient = new RestAPIClient();

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
			@RequestParam(value="courseWorkSubId", required=true) String courseWorkSubId,
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="sources", required=false) String sources,
			HttpSession session) throws Exception {

		Map<String, String> paramVals = new HashMap<String,String>();
		paramVals.put("project_id", LocalResourceBundle.PINOGIO_API_PROJECT_ID);
		paramVals.put("title", title);
		paramVals.put("sources", sources);

		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_CREATE, "/layers", paramVals);

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
			HttpSession session) throws Exception {

		Map<String, String> paramVals = new HashMap<String,String>();

		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_REMOVE, "/layers/"+layerId, paramVals);

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
	
	
	
	

}
