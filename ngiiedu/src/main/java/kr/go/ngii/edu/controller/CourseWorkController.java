package kr.go.ngii.edu.controller;

import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.go.ngii.edu.common.StringUtil;
import kr.go.ngii.edu.common.enums.EnumRestAPIType;
import kr.go.ngii.edu.config.LocalResourceBundle;
import kr.go.ngii.edu.controller.rest.BaseController;
import kr.go.ngii.edu.controller.rest.GISServerConnect;
import kr.go.ngii.edu.controller.rest.ResponseData;
import kr.go.ngii.edu.main.common.RestAPIClient;
import kr.go.ngii.edu.main.courses.course.model.Course;
import kr.go.ngii.edu.main.courses.course.service.CourseService;
import kr.go.ngii.edu.main.courses.work.model.CourseWork;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSub;
import kr.go.ngii.edu.main.courses.work.model.WorkOutput;
import kr.go.ngii.edu.main.courses.work.service.CourseWorkService;
import kr.go.ngii.edu.main.courses.work.service.CourseWorkSubService;
import kr.go.ngii.edu.main.courses.work.service.WorkOutputService;
import kr.go.ngii.edu.main.users.model.PngoUser;
import kr.go.ngii.edu.main.users.model.User;
import kr.go.ngii.edu.main.users.service.UserService;

@Controller
@RequestMapping(value="/api/v1/coursesWork")
public class CourseWorkController extends BaseController {

	private RestAPIClient apiClient = new RestAPIClient();

	@Autowired
	private WorkOutputService workOutputService;
	
	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseWorkService courseWorkService;
	
	@Autowired
	private CourseWorkSubService courseWorkSubService;
	
	@Autowired
	private UserService userService;
	
	
	// --------------------------------------------------------------------
	// --------- Dataset
	// --------------------------------------------------------------------
	
	/**
	 * 
	 * Dataset 목록
	 * 
	 * @param courseWorkSubId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> datasetList(
			@PathVariable(value="datasetId", required=false) String datasetId,
			HttpSession session) throws Exception {
		User user = (User)session.getAttribute("USER_INFO");
//		if (user == null) {
//			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
//		}
		
		Map<String, String> pathParamVals = new HashMap<String,String>();
		Map<String, String> paramVals = new HashMap<String,String>();
		pathParamVals.put("dataset_id", datasetId);
		String apiKey = "";
		try {
			apiKey = userService.getApiKey(user.getIdx());
		} catch (NullPointerException e) {
		}
		apiClient.setApiKey(apiKey);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_GET, pathParamVals, paramVals);
//			Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_GET, paramPath);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	/**
	 * 
	 * Dataset 정보
	 * 
	 * @param dataset_id
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset/{datasetId}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> datasetGet(
			@PathVariable(value="datasetId", required=false) String datasetId,
			HttpSession session) throws Exception {
		User user = (User)session.getAttribute("USER_INFO");
//		if (user == null) {
//			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
//		}
		Map<String, String> pathParamVals = new HashMap<String,String>();
		Map<String, String> paramVals = new HashMap<String,String>();
		pathParamVals.put("dataset_id", datasetId);
		
		String apiKey = "";
		try {
			apiKey = userService.getApiKey(user.getIdx());
		} catch (NullPointerException e) {
		}
		apiClient.setApiKey(apiKey);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_GET, pathParamVals, paramVals);
//			Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_GET, paramPath);

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 
	 * Dataset 생성
	 * 
	 * @param courseWorkSubId
	 * @param title
	 * @param sources
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> datasetCreate(
			@RequestParam(value="courseId", required=true) int courseId,
			@RequestParam(value="courseWorkId", required=true) int courseWorkId,
//			@RequestParam(value="title", required=false, defaultValue="untitled") String title,
			@RequestParam(value="options", required=false, defaultValue="") String options,
			@RequestParam(value="metadata", required=false, defaultValue="") String metadata,
			@RequestParam(value="privacy", required=false, defaultValue="PRIVATE") String privacy,
			@RequestParam(value="uFile", required=false) MultipartFile uFile,
//			@RequestParam(value="sources", required=false, defaultValue="") String sources,
			MultipartHttpServletRequest request,
			HttpSession session) throws Exception {
//
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		// project id 조회
		Course course = courseService.get(courseId);
		String projectId = course.getProjectId();
		String apiKey = userService.getApiKey(user.getIdx());
		
		Map<String, Object> paramVals = new HashMap<String, Object>();
		Map<String, String> pathParamVals = new HashMap<String, String>();
		paramVals.put("project_id", projectId);
//		paramVals.put("title", title);
		paramVals.put("ufile", uFile);
		paramVals.put("metadata", metadata);
		paramVals.put("privacy", privacy);
		
		String uFileType = ""; 
		try {
			String uFileName = uFile.getOriginalFilename();
			uFileType = uFileName.substring(uFileName.lastIndexOf(".") + 1);
		} catch (NullPointerException ne) {
		} catch (IndexOutOfBoundsException ie) {
		} catch (Exception e) {
		}
		
		if ("".equals(options)) {
			if ("zip".equals(uFileType)) {
				options = "{\"charset\":\"x-windows-949\",  \"srid\":5179 }";
			} else if ("csv".equals(uFileType)) {
				options = "{\"lon\":\"x\",  \"lat\":\"y\", \"delimiter\":\",\" , \"headerLine\":1}";
			} else if ("excel".equals(uFileType)) {
				options = "{\"lon\":\"x\",  \"lat\":\"y\"}";
			}
		}
		paramVals.put("options", options);
		
		apiClient.setApiKey(apiKey);
		
//		paramVals.put("sources", sources);
//			Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_CREATE, "/dataset.json", paramVals);
		Map<String, Object> result = apiClient.getResponseBodyWithLinkedMap(EnumRestAPIType.DATASET_UPLOAD_CREATE, pathParamVals, paramVals, uFile, apiKey);
//		String result = apiClient.getResponseBodyWithFiles(EnumRestAPIType.DATASET_UPLOAD_CREATE, pathParamVals, paramVals, uFile);
		
		// output Division 
//		WorkOutput workOutputResult = workOutputService.create(courseWorkSubId, "1",  result, 40, "dataset");
//		WorkOutput workOutputResult = workOutputService.create(courseWorkSubId, "1",  result, user.getIdx(), "dataset");
//		result.put("worksOutputId", workOutputResult.getIdx());
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * Dataset 수정
	 * 
	 * @param courseWorkSubId
	 * @param title
	 * @param sources
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset/{datasetId}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> datasetModify(
			@PathVariable(value="datasetId", required=false) String datasetId,
			@RequestParam(value="title", required=false, defaultValue="") String title,
			@RequestParam(value="description", required=false, defaultValue="") String description,
			@RequestParam(value="metadata", required=false, defaultValue="") String metadata,
			@RequestParam(value="privacy", required=false, defaultValue="") String privacy,
			HttpSession session) throws Exception {

		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		Map<String, String> pathParamVals = new HashMap<String, String>();
		pathParamVals.put("dataset_id", datasetId);
		Map<String, String> paramVals = new HashMap<String, String>();
		
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);
		
		Map<String, Object> datasetGetResult = apiClient.getResponseBody(EnumRestAPIType.DATASET_GET, pathParamVals, paramVals);
		Map<String, Object> datasetGetResultdata = (Map<String, Object>) datasetGetResult.get("data");
				
		title = "".equals(title) ? (String) datasetGetResultdata.get("title") : title;
		description = "".equals(description) ? (String) datasetGetResultdata.get("description") : description;
		metadata = "".equals(metadata) ? (String) datasetGetResultdata.get("metadata") : metadata;
		privacy = "".equals(privacy) ? (String) datasetGetResultdata.get("privacy") : privacy;
		
		paramVals.put("title", title);
		paramVals.put("description", description);
		paramVals.put("metadata", metadata);
		paramVals.put("privacy", privacy);
		
		apiClient.setApiKey(apiKey);
		
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_CREATE, "/dataset.json", paramVals);
		Map<String, Object> updateResult = apiClient.getResponseBody(EnumRestAPIType.DATASET_UPDATE, pathParamVals, paramVals);
		Map<String, String> metaData = (Map<String, String>) updateResult.get("meta");
		String metaDataMessage = metaData.get("message");
		
		if ("Updated".equalsIgnoreCase(metaDataMessage)) {
			Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_GET, pathParamVals, null);
			return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
		} else {
			return new ResponseEntity<ResponseData>(responseBody("failed"), HttpStatus.OK);
		}
	}

	/**
	 * 
	 * Dataset 삭제
	 * 
	 * @param datasetId
	 * @param worksOutputId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset/{datasetId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> datasetDelete(
			@PathVariable(value="datasetId", required=false) String datasetId,
			@RequestParam(value="worksOutputId", required=true) int worksOutputId,
			HttpSession session) throws Exception {

		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		Map<String, String> paramVals = new HashMap<String,String>();
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("dataset_id", datasetId);
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_REMOVE, pathParamVals, paramVals);
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_REMOVE, "/dataset/"+dataset_id+".json", paramVals);

		result.put("worksOutputIsDeleted", workOutputService.delete(worksOutputId));
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}


	/**
	 * DataSet row 목록 조회
	 * 
	 * @param datasetId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset/row/list", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> datasetRowList(
			@RequestParam(value="datasetId", required=true) String datasetId, 
			HttpSession session) throws Exception {

		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		Map<String, String> paramVals = new HashMap<String,String>();
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("dataset_id", datasetId);
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);
//			Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_ROW_LIST, param);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_ROW_LIST, pathParamVals, paramVals);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}



	/**
	 * DataSet row 조회
	 * 
	 * @param datasetId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset/row", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> datasetRowGet(
			@RequestParam("datasetId") String datasetId, 
			@RequestParam("rowId") String rowId, 
			HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		Map<String, String> paramVals = new HashMap<String,String>();
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("dataset_id", datasetId);
		pathParamVals.put("row_id", rowId);
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_ROW_GET, pathParamVals, paramVals);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
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
			@RequestParam("datasetId") String datasetId, 
			@RequestParam("contentJson") String contentJson, 
			HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		Map<String, String> paramVals = new HashMap<String, String>();
		paramVals.put("content", contentJson);
		Map<String, String> pathParamVals = new HashMap<String, String>();
		pathParamVals.put("dataset_id", datasetId);
		pathParamVals.put("content", contentJson);
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_ROW_CREATE, pathParamVals, paramVals);
//			Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_ROW_CREATE, "/datasets/"+pinogioOutputId+"/row.json", params);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
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
			@RequestParam("datasetId") String datasetId, 
			@RequestParam("rowId") String rowId,
			@RequestParam("contentJson") String contentJson, 
			HttpSession session) throws Exception {
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		Map<String, String> paramVals = new HashMap<String, String>();
		paramVals.put("content", contentJson);
		Map<String, String> pathParamVals = new HashMap<String, String>();
		pathParamVals.put("dataset_id", datasetId);
		pathParamVals.put("row_id", rowId);
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_ROW_UPDATE, pathParamVals, paramVals);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
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
			@RequestParam(value="datasetId", required=true) String datasetId, 
			@RequestParam(value="rowId", required=true) String rowId, 
			HttpSession session) throws Exception {
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		Map<String, String> paramVals = new HashMap<String, String>();
		Map<String, String> pathParamVals = new HashMap<String, String>();
		pathParamVals.put("dataset_id", datasetId);
		 pathParamVals.put("row_id", rowId);
		 
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);
		
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_ROW_REMOVE, pathParamVals, paramVals);
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_ROW_REMOVE, uriParams);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	
	/**
	 * DataSet unique row 조회
	 * 
	 * @param courseWorkId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset/{datasetId}/rowUnique", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> datasetRowUniqueValue(
			@PathVariable(value="datasetId", required=true) String datasetId,
			@RequestParam(value="columnName", required=true) String columnName, 
			@RequestParam(value="page", required=false, defaultValue="") String page, 
			@RequestParam(value="size", required=false, defaultValue="") String size, 
			HttpSession session) throws Exception {
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		Map<String, String> paramVals = new HashMap<String, String>();
		Map<String, String> pathParamVals = new HashMap<String, String>();
		
		pathParamVals.put("dataset_id", datasetId);
		
		paramVals.put("columnName", columnName);
		
		if(!"".equals(page)) {
			paramVals.put("page", page);
		}
		
		if(!"".equals(size)) {
			paramVals.put("size", size);
		}
		
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);

		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_ROWUNIQUE_GET, pathParamVals, paramVals);
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_ROW_REMOVE, uriParams);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
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
			@RequestParam(value="datasetId", required=true) String datasetId, 
			HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		Map<String, String> paramVals = new HashMap<String, String>();
		Map<String, String> pathParamVals = new HashMap<String, String>();
		pathParamVals.put("dataset_id", datasetId);
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_COLUMN_LIST, pathParamVals, paramVals);
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.DATASET_COLUMN_LIST, param);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/dataset/{datasetId}/download", method=RequestMethod.GET)
    public @ResponseBody void datasetDownloadGet(
            HttpServletRequest request, HttpServletResponse response, 
            @PathVariable("datasetId") String datasetId,
            @RequestParam(value="format", required=true) String format,
            HttpSession session) throws Exception {
        
        User user = (User)session.getAttribute("USER_INFO");
        if (user != null) {
            response.setHeader("apikey", userService.getApiKey(user.getIdx()));
            
            StringBuffer sb = new StringBuffer();
            sb.append(LocalResourceBundle.PINOGIO_API_SERVER).append("/datasets/")
                .append(datasetId).append("/")
                .append("download?format=")
                .append(format);
            
            URL url = new URL(sb.toString());
            GISServerConnect.requestGET(url, request, response);
        }
    }
	
	@RequestMapping(value="/dataset/thumbNail/{datasetId}", method=RequestMethod.GET)
	public @ResponseBody void datasetThumbnailGet(
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable("datasetId") String datasetId,
			@RequestParam(value="width", required=false, defaultValue="400") String width,
			@RequestParam(value="height", required=false, defaultValue="400") String height,
			@RequestParam(value="ext", required=false, defaultValue="png") String ext,
			HttpSession session) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		sb.append(LocalResourceBundle.PINOGIO_SERVER).append("/data/thumbnail/datasets/")
			.append(datasetId).append("/")
			.append(width).append("/")
			.append(height).append(".").append(ext);
		URL url = new URL(sb.toString());
		GISServerConnect.requestGET(url, request, response);
	}

	
	// --------------------------------------------------------------------
	// --------- Layers
	// --------------------------------------------------------------------

	//	@RequestMapping(value="/layer", method=RequestMethod.GET)
	//	public @ResponseBody ResponseEntity<ResponseData> layerList(
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
	@RequestMapping(value="/layers/{layerId}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> layerGet(
			@PathVariable(value="layerId", required=false) String layerId,
			HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}

		Map<String, String> paramVals = new HashMap<String, String>();
		Map<String, String> pathParamVals = new HashMap<String, String>();
		pathParamVals.put("layer_id", layerId);
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_GET, pathParamVals, paramVals);
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_GET, paramPath);

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
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
	public @ResponseBody ResponseEntity<ResponseData> layerCreate(
			@RequestParam(value="courseWorkSubId", required=true) int courseWorkSubId,
			@RequestParam(value="title", required=false, defaultValue="untitled") String title,
			@RequestParam(value="sources", required=false, defaultValue="null") String sources,
			@RequestParam(value="privacy", required=false, defaultValue="PRIVATE") String privacy,
			@RequestParam(value="isShared", required=false, defaultValue="false") String isShared,
			@RequestParam(value="isDone", required=false, defaultValue="false") String isDone,
			HttpSession session) throws Exception {

		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		CourseWorkSub courseWorkSub = new CourseWorkSub();
		courseWorkSub.setIdx(courseWorkSubId);
		courseWorkSub = courseWorkSubService.get(courseWorkSub);
		
		CourseWork courseWork = new CourseWork();
		courseWork.setIdx(courseWorkSub.getCourseWorkId());
		courseWork = courseWorkService.get(courseWork);
		
		int courseId = courseWork.getCourseId();
		
		Course course = courseService.get(courseId);
		String projectId = course.getProjectId();
		String apiKey = userService.getApiKey(user.getIdx());
		
		Map<String, String> paramVals = new HashMap<String,String>();
		Map<String, String> pathParamVals = new HashMap<String,String>();
		paramVals.put("project_id", projectId);
		paramVals.put("title", title);
		paramVals.put("privacy", privacy);
		paramVals.put("sources", sources);
		
		apiClient.setApiKey(apiKey);
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_CREATE, "/layers.json", paramVals);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_CREATE, pathParamVals, paramVals);

//		String layerId = ((Map<String, String>) result.get("data")).get("layerId");
//		Map<String, String> layerCreatePathParamVals = new HashMap<String,String>();
//		layerCreatePathParamVals.put("layer_id", layerId);
//		Map<String, String> layerCreateparamVals = new HashMap<String,String>();
//		layerCreateparamVals.put("privacy", "TEAM");
//		Map<String, Object> create = apiClient.getResponseBody(EnumRestAPIType.LAYER_PRIVACY_UPDATE, layerCreatePathParamVals, layerCreateparamVals);
		
		// output division
		WorkOutput workOutputResult = workOutputService.create(courseWorkSubId, "1",  result, user.getIdx(), "layer", "true".equals(isShared), "true".equals(isDone));
		result.put("worksOutputId", workOutputResult.getIdx());
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
	@RequestMapping(value="/layers/{layerId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> layerDelete(
			@PathVariable(value="layerId", required=false) String layerId,
			@RequestParam(value="worksOutputId", required=true) int worksOutputId,
			HttpSession session) throws Exception {
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		Map<String, String> paramVals = new HashMap<String,String>();
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("layer_id", layerId);
		
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);
		
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_REMOVE, pathParamVals, paramVals);
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_REMOVE, paramVals);
		result.put("worksOutputIsDelete", workOutputService.delete(worksOutputId));
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
	@RequestMapping(value="/layers/{layerId}/metadata", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> layerMetadataModify(
			@PathVariable("layerId") String layerId,
			@RequestParam(value="title", required=false, defaultValue="untitled") String title,
			@RequestParam(value="description", required=false, defaultValue="") String description,
			@RequestParam(value="metadata", required=false, defaultValue="") String metadata,
			@RequestParam(value="privacy", required=false, defaultValue="") String privacy,
			HttpSession session) throws Exception {
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		Map<String, String> paramVals = new HashMap<String,String>();
		paramVals.put("title", title);
		paramVals.put("description", description);
		paramVals.put("metadata", metadata);
		paramVals.put("privacy", privacy);
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("layer_id", layerId);
		
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);

		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_METADATA_UPDATE, pathParamVals, paramVals);
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_METADATA_UPDATE, "/layers/"+layerId+"/metadata.json", paramVals);

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
	@RequestMapping(value="/layers/{layerId}/process", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> layerProcessModify(
			@PathVariable("layerId") String layerId,
			@RequestParam(value="process", required=true) String process,
			HttpSession session) throws Exception {

		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		Map<String, String> paramVals = new HashMap<String,String>();
//		paramVals.put("process", StringUtil.encodeURIComponent(process));
		paramVals.put("process", process);
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("layer_id", layerId);
		
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);
		
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_METADAT_PROCESS_UPDATE, pathParamVals, paramVals);
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_METADAT_PROCESS_UPDATE, "/layers/"+layerId+"/process.json", paramVals);

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
	@RequestMapping(value="/layers/{layerId}/styling", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> layerStylingModify(
			@PathVariable("layerId") String layerId, 
			@RequestParam(value="styling", required=true) String styling,
			HttpSession session) throws Exception {

		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		Map<String, String> paramVals = new HashMap<String,String>();
		paramVals.put("styling", styling);
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("layer_id", layerId);
		
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);
		
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_METADATA_STYLING_UPDATE, "/layers/"+layerId+"/styling.json", paramVals);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_METADATA_STYLING_UPDATE, pathParamVals, paramVals);

		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 
	 * @param layerId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/layers/{layerId}/column", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> layerColumnList(
			@PathVariable("layerId") String layerId, 
			HttpSession session) throws Exception {
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		Map<String, String> paramVals = new HashMap<String,String>();
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("layer_id", layerId);
		
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);
		
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_COLUMN_LIST, "/layers/"+layerId+"/column.json", paramVals);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_COLUMN_LIST, pathParamVals, paramVals);
		
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	
	@RequestMapping(value="/layers/thumbNail/{layerId}", method=RequestMethod.GET)
	public @ResponseBody void layerThumbnailGet(
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable("layerId") String layerId,
			@RequestParam(value="width", required=false, defaultValue="400") String width,
			@RequestParam(value="height", required=false, defaultValue="400") String height,
			@RequestParam(value="ext", required=false, defaultValue="png") String ext,
			HttpSession session) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		sb.append(LocalResourceBundle.PINOGIO_SERVER).append("/data/thumbnail/layers/")
			.append(layerId).append("/")
			.append(width).append("/")
			.append(height).append(".").append(ext);
		URL url = new URL(sb.toString());
		GISServerConnect.requestGET(url, request, response);
	}
	
	// --------------------------------------------------------------------
	// --------- Maps
	// --------------------------------------------------------------------


	/**
	 * Maps 조회
	 * 
	 * @param layerId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/maps/{mapsId}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> mapsGet(
			@PathVariable(value="mapsId", required=false) String mapsId,
			HttpSession session) throws Exception {

		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		Map<String, Object> result = new HashMap<>();
		result.put("data", workOutputService.getByPinogioOutputId(mapsId));
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * Maps 추가하기
	 * 
	 * title 제목
	 * description 설명
	 * maps_type "SERIES" || ....
	 * privacy "PUBLIC" || "FRIEND" || ...
	 * metadata {"type":"tabs"} || {"type":"accordion"}
	 * 
	 * @param courseWorkSubId
	 * @param title
	 * @param sources
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/maps", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> mapsCreate(
			@RequestParam(value="courseWorkSubId", required=true) int courseWorkSubId,
			@RequestParam(value="title", required=false, defaultValue="untitled") String title,
			@RequestParam(value="description", required=false) String description,
			@RequestParam(value="mapsType", required=false) String mapsType,
			@RequestParam(value="metadata", required=false, defaultValue="null") String metadata,
			@RequestParam(value="privacy", required=false, defaultValue="PRIVATE") String privacy,
			@RequestParam(value="typeKind", required=false) String typeKind,
			@RequestParam(value="isShared", required=false, defaultValue="false") String isShared,
			@RequestParam(value="isDone", required=false, defaultValue="false") String isDone,
			HttpSession session) throws Exception {

		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		CourseWorkSub courseWorkSub = new CourseWorkSub();
		courseWorkSub.setIdx(courseWorkSubId);
		courseWorkSub = courseWorkSubService.get(courseWorkSub);
		
		CourseWork courseWork = new CourseWork();
		courseWork.setIdx(courseWorkSub.getCourseWorkId());
		courseWork = courseWorkService.get(courseWork);
		
		int courseId = courseWork.getCourseId();
		
		Course course = courseService.get(courseId);
		String projectId = course.getProjectId();
		String apiKey = userService.getApiKey(user.getIdx());
		
		Map<String, String> paramVals = new HashMap<String, String>();
		Map<String, String> pathParamVals = new HashMap<String, String>();
		paramVals.put("project_id", projectId);
		paramVals.put("title", title);
		paramVals.put("description", description);
		paramVals.put("maps_type", mapsType);
		paramVals.put("metadata", metadata);
		paramVals.put("privacy", privacy);
		paramVals.put("type_kind", typeKind);
		
		apiClient.setApiKey(apiKey);
		
//		int userId = user.getIdx();
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_CREATE, pathParamVals, paramVals);
		
		// output division?
		WorkOutput workOutputResult = workOutputService.create(courseWorkSubId, "1",  result, user.getIdx(), "maps", "true".equals(isShared), "true".equals(isDone));
		result.put("result", workOutputResult);
//		
//		String layerId = ((Map<String, String>) result.get("data")).get("mapsId");
//		Map<String, String> layerCreatePathParamVals = new HashMap<String,String>();
//		layerCreatePathParamVals.put("maps_id", layerId);
//		Map<String, String> layerCreateparamVals = new HashMap<String,String>();
//		layerCreateparamVals.put("privacy", "TEAM");
//		Map<String, Object> create = apiClient.getResponseBody(EnumRestAPIType.MAPS_PRIVACY_UPDATE, layerCreatePathParamVals, layerCreateparamVals);
		
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	/**
	 * 맵스 수정하기
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
	@RequestMapping(value="/maps/{mapsId}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> mapsModify(
			@PathVariable("mapsId") String mapsId,
			@RequestParam(value="title", required=false, defaultValue="") String title,
			@RequestParam(value="description", required=false, defaultValue="") String description,
			@RequestParam(value="mapsType", required=false, defaultValue="") String mapsType,
			@RequestParam(value="metadata", required=false, defaultValue="") String metadata,
			@RequestParam(value="privacy", required=false, defaultValue="") String privacy,
			@RequestParam(value="typeKind", required=false, defaultValue="") String typeKind,
			@RequestParam(value="isShared", required=false, defaultValue="true") String isShared,
			@RequestParam(value="isDone", required=false, defaultValue="false") String isDone,
			HttpSession session) throws Exception {

		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		WorkOutput workOutput = workOutputService.getByPinogioOutputId(mapsId);
		
		CourseWorkSub courseWorkSub = new CourseWorkSub();
		courseWorkSub.setIdx(workOutput.getCourseWorkSubId());
		courseWorkSub = courseWorkSubService.get(courseWorkSub);
		
		CourseWork courseWork = new CourseWork();
		courseWork.setIdx(courseWorkSub.getCourseWorkId());
		courseWork = courseWorkService.get(courseWork);
		
		int courseId = courseWork.getCourseId();
		
		Course course = courseService.get(courseId);
		String projectId = course.getProjectId();
		String apiKey = userService.getApiKey(user.getIdx());
		
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("maps_id", mapsId);
		
		apiClient.setApiKey(apiKey);
		
		Map<String, Object> mapsGetResult = apiClient.getResponseBody(EnumRestAPIType.MAPS_GET, pathParamVals, null);
		Map<String, Object> mapsGetResultData = (Map<String, Object>) mapsGetResult.get("data");
		
		title = "".equals(title) ? (String) mapsGetResultData.get("title") : title;
		description = "".equals(description) ? (String) mapsGetResultData.get("description") : description;
		mapsType = "".equals(mapsType) ? (String) mapsGetResultData.get("mapsType") : mapsType;
		metadata = "".equals(metadata) ? (String) mapsGetResultData.get("metadata") : metadata;
		privacy = "".equals(privacy) ? (String) mapsGetResultData.get("privacy") : privacy;
		typeKind = "".equals(typeKind) ? (String) mapsGetResultData.get("typeKind") : typeKind;
		
		Map<String, String> paramVals = new HashMap<String,String>();
		paramVals.put("project_id", projectId);
		paramVals.put("title", title);
		paramVals.put("description", description);
		paramVals.put("maps_type", mapsType);
		paramVals.put("metadata", metadata);
		paramVals.put("privacy", privacy);
		paramVals.put("type_kind", typeKind);
		
		apiClient.setApiKey(apiKey);
		
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_UPDATE, pathParamVals, paramVals);
		result.put("result", workOutputService.getByPinogioOutputId(mapsId));
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 맵스 삭제하기
	 * 
	 * @param maps_id
	 * @param courseWorkSubId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/maps/{mapsId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> mapsDelete(
			@PathVariable(value="mapsId", required=false) String mapsId,
			@RequestParam(value="worksOutputId", required=true) int worksOutputId,
			HttpSession session) throws Exception {

		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("maps_id", mapsId);
		Map<String, String> paramVals = new HashMap<String,String>();
		
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);

		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_REMOVE, pathParamVals, paramVals);
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_REMOVE, "/maps/"+mapsId+".json", paramVals);
		
		result.put("worksOutputIsDelete", workOutputService.delete(worksOutputId));
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	
	/**
	 * 맵스 아이템 목록
	 * 
	 * @param mapsId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/maps/{mapsId}/item", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> mapsItemList(
			@PathVariable("mapsId") String mapsId,
			HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("maps_id", mapsId);
		Map<String, String> paramVals = new HashMap<String,String>();
		
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);

		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_ITEM_LIST, pathParamVals, paramVals);
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_ITEM_LIST, "/maps/"+mapsId+"/item.json", paramVals);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	/**
	 * 맵스 아이템 상세정보
	 * 
	 * @param mapsId
	 * @param itemId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/maps/{mapsId}/item/{itemId}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> mapsItemGet(
			@PathVariable("mapsId") String mapsId,
			@PathVariable("itemId") String itemId,
			HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("maps_id", mapsId);
		pathParamVals.put("item_id", itemId);
		Map<String, String> paramVals = new HashMap<String,String>();
		
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);
		
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_ITEM_GET, pathParamVals, paramVals);
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_ITEM_GET, "/maps/"+mapsId+"/item/"+ itemId +".json", paramVals);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	/**
	 * 맵스 아이템 추가하기
	 * @param mapsId
	 * @param title
	 * @param description
	 * @param metadata
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/maps/{mapsId}/item", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> mapsItemCreate(
			@PathVariable("mapsId") String mapsId,
			@RequestParam(value="title", required=false, defaultValue="untitled") String title,
			@RequestParam(value="description", required=false, defaultValue="") String description,
			@RequestParam(value="metadata", required=false, defaultValue="") String metadata,
			@RequestParam(value="baseLayer	", required=false, defaultValue="") String baseLayer,
			@RequestParam(value="pinoLayer", required=false, defaultValue="") String pinoLayer,
			@RequestParam(value="mapOptions", required=false, defaultValue="") String mapOptions,
			HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);
		
		Map<String, String> paramVals = new HashMap<String,String>();
		paramVals.put("title", title);
		//if (!"".equals(description)) {
			paramVals.put("description", description);
		//}
		if (!"".equals(metadata)) {
			paramVals.put("metadata", metadata);
		}
		if (!"".equals(baseLayer)) {
			paramVals.put("base_layer", baseLayer);
		}
		//if (!"".equals(pinoLayer)) {
			paramVals.put("pino_layer", pinoLayer);
		//}
		if (!"".equals(mapOptions)) {
			paramVals.put("map_options", mapOptions);
		}
		
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("maps_id", mapsId);
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_ITEM_CREATE, pathParamVals, paramVals);
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_ITEM_CREATE, "/maps/"+mapsId+"/item.json", paramVals);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	/**
	 * 맵스 아이템 수정하기
	 * 
	 * @param mapsId
	 * @param itemId
	 * @param title
	 * @param description
	 * @param metadata
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/maps/{mapsId}/item/{itemId}", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> mapsItemModify(
			@PathVariable("mapsId") String mapsId,
			@PathVariable("itemId") String itemId,
			@RequestParam(value="title", required=false, defaultValue="") String title,
			@RequestParam(value="description", required=false, defaultValue="") String description,
			@RequestParam(value="metadata", required=false, defaultValue="null") String metadata,
			@RequestParam(value="baseLayer", required=false, defaultValue="") String baseLayer,
			@RequestParam(value="pinoLayer", required=true, defaultValue="") String pinoLayer,
			@RequestParam(value="mapOptions", required=false, defaultValue="null") String mapOptions,
//			MultipartHttpServletRequest request,
			HttpSession session) throws Exception {
		// metadata,  mapOptions 값없으면 에러생김...
		
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("maps_id", mapsId);
		pathParamVals.put("item_id", itemId);
		
		Map<String, String> paramVals = new HashMap<String,String>();
		
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);

		Map<String, Object> mapsItemGetResult = apiClient.getResponseBody(EnumRestAPIType.MAPS_ITEM_GET, pathParamVals, paramVals);
		Map<String, Object> mapsItemGetResultData = (Map<String, Object>) mapsItemGetResult.get("data");
		
		title = "".equals(title) ? (String) mapsItemGetResultData.get("title") : title;
		description = "".equals(description) ? (String) mapsItemGetResultData.get("description") : description;
		baseLayer = "".equals(baseLayer) ? (String) mapsItemGetResultData.get("baseLayer") : baseLayer;
//		pinoLayer = "".equals(pinoLayer) ? (String) mapsItemGetResultData.get("pinoLayer") : pinoLayer;
		metadata = "".equals(metadata) ? (String) mapsItemGetResultData.get("metadata") : metadata;
		mapOptions = "".equals(mapOptions) ? (String) mapsItemGetResultData.get("mapOptions") : mapOptions;
		
		paramVals.put("title", title);
		paramVals.put("description", description);
		//baseLayer.paramVals.put("description", description.replaceAll("/", "%2F"));
		paramVals.put("metadata", metadata);
		paramVals.put("base_layer", baseLayer);
		paramVals.put("pino_layer", pinoLayer);
		paramVals.put("map_options", mapOptions);
		
		apiClient.setApiKey(apiKey);

		Map<String, Object>  updateResult = apiClient.getResponseBodyWithLinkedMap(EnumRestAPIType.MAPS_ITEM_UPDATE, pathParamVals, paramVals, "");
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_ITEM_UPDATE, "/maps/"+mapsId+"/item/"+ itemId +".json", paramVals);
		
		Map<String, String> metaData = (Map<String, String>) updateResult.get("meta");
		String metaDataMessage = metaData.get("message");
		if ("Updated".equalsIgnoreCase(metaDataMessage)) {
			Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_ITEM_GET, pathParamVals, null);
			return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
		} else {
			return new ResponseEntity<ResponseData>(responseBody("failed"), HttpStatus.OK);
		}
	}
	
	/**
	 * 맵스 아이템 삭제하기
	 * 
	 * @param mapsId
	 * @param itemId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/maps/{mapsId}/item/{itemId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> mapsItemDelete(
			@PathVariable("mapsId") String mapsId,
			@PathVariable("itemId") String itemId,
			HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("maps_id", mapsId);
		pathParamVals.put("item_id", itemId);
		Map<String, String> paramVals = new HashMap<String,String>();
		
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);

		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_ITEM_REMOVE, pathParamVals, paramVals);
//		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_ITEM_REMOVE, "/maps/"+mapsId+"/item/"+ itemId +".json", paramVals);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	
	/**
	 * 맵스 아이템 순서 수정하기
	 * priority : [{“itemId”:“9", “priority”:“4"}, {“itemId”:“10", “priority”:“2"}, {“itemId”:“11", “priority”:“10"}, {“itemId”:“12", “priority”:“3"}]
	 * 
	 * 
	 * @param mapsId
	 * @param priority
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/maps/{mapsId}/itemOrder", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> mapsItemOrderModify(
			@PathVariable("mapsId") String mapsId,
			@RequestParam(value="priority", required=false, defaultValue="") String priority,
			HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("maps_id", mapsId);
		
		Map<String, String> paramVals = new HashMap<String,String>();
		paramVals.put("priority", priority);

		
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);
		
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_ITEMORDER_UPDATE, pathParamVals, paramVals);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	/**
	 * course_work_id로 Course Work Name 가져오기
	 * 
	 * @param courseWorkId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{courseWorkId}/getTitle", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getTitle(
			@PathVariable("courseWorkId") int courseWorkId,
			HttpSession session) throws Exception {
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		String courseWorkName = courseWorkService.getCourseWorkName(courseWorkId);
		return new ResponseEntity<ResponseData>(responseBody(courseWorkName), HttpStatus.OK);
	}
	
	@RequestMapping(value="/maps/thumbNail/{mapsId}", method=RequestMethod.GET)
	public @ResponseBody void mapsThumbnailGet(
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable("layerId") String mapsId,
			@RequestParam(value="width", required=false, defaultValue="400") String width,
			@RequestParam(value="height", required=false, defaultValue="400") String height,
			@RequestParam(value="ext", required=false, defaultValue="png") String ext,
			HttpSession session) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		sb.append(LocalResourceBundle.PINOGIO_API_SERVER).append("/data/photo/")
			.append(mapsId).append("/")
			.append(width).append("/")
			.append(height).append(".").append(ext);
		URL url = new URL(sb.toString());
		GISServerConnect.requestGET(url, request, response);
	}
	
	@RequestMapping(value="/maps/{mapsId}/share", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> mapsShareModify(
			@PathVariable("mapsId") String mapsId,
			@RequestParam(value="isShared", required=false, defaultValue="false") String isShared,
			HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("maps_id", mapsId);
		
		String privacy = "true".equals(isShared) ? "PUBLIC" : "TEAM";
		
		Map<String, String> paramVals = new HashMap<String,String>();
		paramVals.put("privacy", privacy);
		
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);
		
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.MAPS_PRIVACY_UPDATE, pathParamVals, paramVals);
		
		workOutputService.modifyShare(mapsId, "true".equals(isShared));
		
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	@RequestMapping(value="/layers/{layerId}/share", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> layerShareModify(
			@PathVariable("layerId") String layerId,
			@RequestParam(value="isShared", required=false, defaultValue="false") String isShared,
			HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		Map<String, String> pathParamVals = new HashMap<String,String>();
		pathParamVals.put("layer_id", layerId);
		
		String privacy = "true".equals(isShared) ? "PUBLIC" : "TEAM";
		
		Map<String, String> paramVals = new HashMap<String,String>();
		paramVals.put("privacy", privacy);
		
		String apiKey = userService.getApiKey(user.getIdx());
		apiClient.setApiKey(apiKey);
		
		Map<String, Object> result = apiClient.getResponseBody(EnumRestAPIType.LAYER_PRIVACY_UPDATE, pathParamVals, paramVals);
		
		workOutputService.modifyShare(layerId, "true".equals(isShared));
		
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	@RequestMapping(value="/maps/{mapsId}/done", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> mapsDoneModify(
			@PathVariable("mapsId") String mapsId,
			@RequestParam(value="isDone", required=false, defaultValue="false") String isDone,
			HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		workOutputService.modifyDone(mapsId, "true".equals(isDone));
		return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
	}
	
	@RequestMapping(value="/layers/{layerId}/done", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> layerDoneModify(
			@PathVariable("layerId") String layerId,
			@RequestParam(value="isDone", required=false, defaultValue="false") String isDone,
			HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		WorkOutput result = workOutputService.modifyDone(layerId, "true".equals(isDone));
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
}
