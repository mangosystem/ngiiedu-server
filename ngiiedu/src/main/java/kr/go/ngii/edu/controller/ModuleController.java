package kr.go.ngii.edu.controller;

import java.util.List;

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

import kr.go.ngii.edu.controller.rest.BaseController;
import kr.go.ngii.edu.controller.rest.ResponseData;
import kr.go.ngii.edu.main.modules.course.model.ModuleWork;
import kr.go.ngii.edu.main.modules.course.model.ModuleWorkSub;
import kr.go.ngii.edu.main.modules.course.service.ModuleWorkService;
import kr.go.ngii.edu.main.modules.course.service.ModuleWorkSubService;
import kr.go.ngii.edu.main.modules.module.model.Module;
import kr.go.ngii.edu.main.modules.module.service.ModuleService;

@Controller
@RequestMapping("/api/v1/modules")
public class ModuleController extends BaseController {

	@Autowired
	private ModuleService moduleService;

	@Autowired
	private ModuleWorkService workService;
	
	@Autowired
	private ModuleWorkSubService moduleWorkSubService;


	/**
	 * 모듈 목록 조회하기
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> list(
			@RequestParam(value="offset", required=false, defaultValue="0") Integer offset, 
			@RequestParam(value="limit", required=false, defaultValue="0") Integer limit, 
			HttpSession session) throws Exception {

		List<Module> list = null;

		if (offset==0 && limit==0) {
			list = moduleService.list();
		} else {
			list = moduleService.list(offset, limit);
		}

		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}

	/**
	 * 모듈 조회하기
	 * 
	 * @param idx
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{moduleId}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> get(
			@PathVariable("moduleId") Integer moduleId,
			HttpSession session) throws Exception {

		Module list = moduleService.get(moduleId);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}

	/**
	 * 모듈 생성하기
	 * 
	 * @param moduleName
	 * @param moduleMetadata
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> create(
			@RequestParam(value="moduleName", required=false) String moduleName, 
			@RequestParam(value="moduleMetadata", required=false) String moduleMetadata,
			HttpSession session) throws Exception {
		
		Module result = moduleService.create(moduleName, moduleMetadata);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 모듈 변경하기
	 * @param idx
	 * @param moduleName
	 * @param moduleMetadata
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{moduleId}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> modify(
			@PathVariable("moduleId") Integer moduleId,
			@RequestParam(value="moduleName", required=false) String moduleName, 
			@RequestParam(value="moduleMetadata", required=false) String moduleMetadata,
			HttpSession session) throws Exception {

		Module result = moduleService.modify(moduleId, moduleName, moduleMetadata);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * 모듈 삭제하기
	 * 
	 * @param idx
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{moduleId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> delete(
			@PathVariable("moduleId") Integer moduleId,
			HttpSession session) throws Exception {

		boolean result = moduleService.delete(moduleId);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}


	@RequestMapping(value="/{moduleId}/moduleWork", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> workList(
			@PathVariable("moduleId") Integer moduleId,
			HttpSession session) throws Exception {

		List<ModuleWork> list = workService.list(moduleId);

		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}

	@RequestMapping(value="/{moduleId}/moduleWork/{moduleWorkId}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> workGet(
			@PathVariable("moduleId") Integer moduleId,
			@PathVariable("moduleWorkId") Integer moduleWorkId,
			HttpSession session) throws Exception {

		ModuleWork moduleWork = workService.get(moduleId, moduleWorkId);

		return new ResponseEntity<ResponseData>(responseBody(moduleWork), HttpStatus.OK);
	}
	
	
	/**
	 * 과정 아래 하위 과정 조회
	 * 
	 * @param moduleId
	 * @param moduleWorkId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{moduleId}/moduleWork/{moduleWorkId}/subWork", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> modulWorkSubList(
			@PathVariable("moduleId") Integer moduleId,
			@PathVariable("moduleWorkId") Integer moduleWorkId,
			HttpSession session) throws Exception {
		// @ moduleId ??
		List<ModuleWorkSub> list = moduleWorkSubService.list(moduleWorkId);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}
}
