package kr.go.ngii.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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

import kr.go.ngii.edu.common.enums.EnumRestAPIType;
import kr.go.ngii.edu.controller.rest.BaseController;
import kr.go.ngii.edu.controller.rest.ResponseData;
import kr.go.ngii.edu.main.common.RestAPIClient;
import kr.go.ngii.edu.main.users.model.User;
import kr.go.ngii.edu.main.users.service.UserService;

@Controller
@RequestMapping(value="/api/v1/pngo")
public class PngoController extends BaseController {
	
	/**
	 * DataSet row 목록 조회
	 * 
	 * @param courseWorkId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset/row/list", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> datasetRowList(
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
			@RequestParam("contentJson") String contentJson, // pngo_ 테이블 참조
			HttpSession session) throws Exception {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("dataset_id", "d=r7oFXBrCYl");
		Map<String, String> params = new HashMap<String, String>();
//		params.put("content", "{\"the_geom\": \"POINT(126.588866114616 33.3103962893675)\", " + 
//		"\"kr_np_golf\": \"testtest\", " + 
//		"\"cat\": \"\", " + 
//		"\"nam\": \"\", " + 
//		"\"addr\": \"testtest\", " + 
//		"\"lon\": \"\", " + 
//		"\"lat\": \"\", " + 
//		"\"udate\": \"\", " + 
//		"\"noise_value\": \"\"," + 
//		"\"noise_zone\": \"\", " + 
//		"\"noise_level\": \"\"," + 
//		"\"survey_dn\": \"\"," + 
//		"\"work_id\": \"\", " + 
//		"\"create_team_id\": \"\"," + 
//		"\"create_mem_id\": \"\"," + 
//		"\"create_date: \"\"" + 
//		"}");
		params.put("content", contentJson);
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_ROW_CREATE, uriParams, params);
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
//		uriParams.put("dataset_id", "d=r7oFXBrCYl");
//		uriParams.put("row_id", "12");
		Map<String, String> params = new HashMap<String, String>();
		params.put("content", contentJson);
//		params.put("content", "{\"the_geom\": \"POINT(126.588866114616 33.3103962893675)\", " + 
//		"\"kr_np_golf\": \"testtest\", " + 
//		"\"cat\": \"\", " + 
//		"\"nam\": \"\", " + 
//		"\"addr\": \"testtest\", " + 
//		"\"lon\": \"\", " + 
//		"\"lat\": \"\", " + 
//		"\"udate\": \"\", " + 
//		"\"noise_value\": \"\"," + 
//		"\"noise_zone\": \"\", " + 
//		"\"noise_level\": \"\"," + 
//		"\"survey_dn\": \"\"," + 
//		"\"work_id\": \"\", " + 
//		"\"create_team_id\": \"\"," + 
//		"\"create_mem_id\": \"\"," + 
//		"\"create_date: \"\"" + 
//		"}");
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_ROW_UPDATE, uriParams, params);
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
			@RequestParam("pinogioOutputId") String pinogioOutputId, 
			@RequestParam("rowId") String rowId,
			HttpSession session) throws Exception {
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("dataset_id", pinogioOutputId);
		uriParams.put("row_id", rowId);
//		uriParams.put("dataset_id", "d=r7oFXBrCYl");
//		uriParams.put("row_id", "277");
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
//		uriParams.put("dataset_id", "d=r7oFXBrCYl");
		param.put("dataset_id", pinogioOutputId);
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_COLUMN_LIST, param);
		return new ResponseEntity<ResponseData>(responseBody(r), HttpStatus.OK);
	}
	
}
