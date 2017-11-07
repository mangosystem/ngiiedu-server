package kr.go.ngii.edu.controller;

import java.util.HashMap;
import java.util.List;
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

import kr.go.ngii.edu.controller.rest.BaseController;
import kr.go.ngii.edu.controller.rest.ResponseData;
import kr.go.ngii.edu.main.users.model.User;
import kr.go.ngii.edu.main.users.service.UserService;

@Controller
@RequestMapping(value="/api/v1/pngo")
public class PngoController extends BaseController {
	
	
	/**
	 * DataSet row 조회
	 * 
	 * @param courseWorkId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dataset/row", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> datasetRowList(
			@RequestParam("pinogioOutputId") String pinogioOutputId, 
			HttpSession session) throws Exception {
		
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
			@RequestParam("rowId") Integer rowId,
			@RequestParam("contentJson") String contentJson, // pngo_ 테이블 참조
			HttpSession session) throws Exception {
		
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
			@RequestParam("rowId") Integer rowId, // pngo_ 테이블 참조
			HttpSession session) throws Exception {
		
		return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
	}
	
	
}
