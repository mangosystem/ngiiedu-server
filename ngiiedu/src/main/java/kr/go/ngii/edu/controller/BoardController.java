package kr.go.ngii.edu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.Module;

import kr.go.ngii.edu.main.board.model.BbsNotice;
import kr.go.ngii.edu.main.board.model.BbsQuestion;
import kr.go.ngii.edu.main.board.model.BbsReply;
import kr.go.ngii.edu.main.board.service.BoardService;
import kr.go.ngii.edu.controller.rest.BaseController;
import kr.go.ngii.edu.controller.rest.ResponseData;

@Controller
@RequestMapping("/api/v1/board")
public class BoardController extends BaseController {

	@Autowired
	private BoardService boardService;	
	
	/**
	 * 공지사항 목록 조회하기
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getNoticeList(
			@RequestParam(value="offset", required=false, defaultValue="0") Integer offset, 
			@RequestParam(value="limit", required=false, defaultValue="0") Integer limit, 
			HttpSession session) throws Exception {
		List<BbsNotice> list = null;
		if (offset==0 && limit==0) {
			list = boardService.getNoticeList();

		} else {
			list = boardService.getNoticeList(offset, limit);
		}
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}
	
	/**
	 * 공지사항 조회하기
	 * 
	 * @param idx
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice/{noticeId}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getNoticeListbyId(
			@PathVariable("noticeId") Integer noticeId,
			HttpSession session) throws Exception {

		BbsNotice list = boardService.getNoticeListbyId(noticeId);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}
	
	/**
	 * 공지사항 생성하기
	 * 
	 * @param title
	 * @param content
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> insertNotice(
			@RequestParam(value="title", required=true, defaultValue="") String title,
			@RequestParam(value="content", required=true, defaultValue="") String content, 
			HttpSession session) throws Exception {
		
		BbsNotice result = boardService.insertNotice(title, content);
		//String result = null;
		//result = boardService.insertNotice(title, content);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
		//return new ResponseEntity<List<Notice>>(responseBody(result), HttpStatus.OK);
	}
	
	/**
	 * 공지사항 변경하기
	 * @param idx
	 * @param title
	 * @param content
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice/{noticeId}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> modifyNotice(
			@PathVariable("noticeId") Integer noticeId,
			@RequestParam(value="title", required=false, defaultValue="") String title, 
			@RequestParam(value="content", required=false) String content,
			HttpSession session) throws Exception {

		BbsNotice result = boardService.modifyNotice(noticeId, title, content);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	/**
	 * 공지사항 목록 삭제하기
	 * 
	 * @param idx
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice/{noticeId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> deleteNotice(
			@PathVariable("noticeId") Integer noticeId,
			HttpSession session) throws Exception {

		boolean result = boardService.deleteNotice(noticeId);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}

	/**
	 * Q&A 목록 조회하기
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/qa", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getQuestionList(
			@RequestParam(value="offset", required=false, defaultValue="0") Integer offset, 
			@RequestParam(value="limit", required=false, defaultValue="0") Integer limit, 
			HttpSession session) throws Exception {
		List<BbsQuestion> list = null;
		if (offset==0 && limit==0) {
			list = boardService.getQnaList();

		} else {
			list = boardService.getQnaList(offset, limit);
		}
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}
	
	/**
	 * Q&A 조회하기
	 * 
	 * @param idx
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/qa/{qaId}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getQuestionListbyId(
			@PathVariable("qaId") Integer qaId,
			HttpSession session) throws Exception {

		BbsQuestion list = boardService.getQnaListbyId(qaId);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}
	
	/**
	 * Q&A 생성하기
	 * 
	 * @param title
	 * @param content
	 * @param writer
	 * @param attach
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/qa", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> insertQuestion(
			@RequestParam(value="title", required=true, defaultValue="") String title,
			@RequestParam(value="content", required=true, defaultValue="") String content, 
			@RequestParam(value="writer", required=true, defaultValue="") String writer, 
			@RequestParam(value="attach", required=true, defaultValue="") String attach, 
			HttpSession session) throws Exception {
		
		BbsQuestion result = boardService.insertQna(title, content, writer, attach);
		//String result = null;
		//result = boardService.insertNotice(title, content);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
		//return new ResponseEntity<List<Notice>>(responseBody(result), HttpStatus.OK);
	}
	
	/**
	 * Q&A 변경하기
	 * @param idx
	 * @param title
	 * @param attach
	 * @param content
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/qa/{qaId}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> modifyQuestion(
			@PathVariable("qaId") Integer qaId,
			@RequestParam(value="title", required=false, defaultValue="") String title, 
			@RequestParam(value="content", required=false) String content,
			@RequestParam(value="attach", required=false) String attach,
			HttpSession session) throws Exception {

		BbsQuestion result = boardService.modifyQna(qaId, title, content, attach);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	/**
	 * Q&A 목록 삭제하기
	 * 
	 * @param idx
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/qa/{qaId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> deleteQuestion(
			@PathVariable("noticeId") Integer noticeId,
			HttpSession session) throws Exception {

		boolean result = boardService.deleteQna(noticeId);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	/**
	 * Q&A 댓글 조회하기
	 * 
	 * @param idx
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/qa/re/{qaId}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getReplyListbyId(
			@PathVariable("qaId") Integer qaId,
			HttpSession session) throws Exception {

		List<BbsReply> list = boardService.getReListbyId(qaId);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}
	
	/**
	 * Q&A 댓글 생성하기
	 * 
	 * @param qaId
	 * @param content
	 * @param writer
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/qa/re", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> insertReply(
			@RequestParam(value="qaId", required=true, defaultValue="") int qaId,
			@RequestParam(value="content", required=true, defaultValue="") String content, 
			@RequestParam(value="writer", required=true, defaultValue="") String writer, 
			HttpSession session) throws Exception {
		
		BbsReply result = boardService.insertRe(qaId, content, writer);
		//String result = null;
		//result = boardService.insertNotice(title, content);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
		//return new ResponseEntity<List<Notice>>(responseBody(result), HttpStatus.OK);
	}
	
	/**
	 * Q&A 댓글 변경하기
	 * @param idx
	 * @param title
	 * @param attach
	 * @param content
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/qa/re/{idx}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> modifyReply(
			@PathVariable("idx") int idx,
			//@RequestParam(value="reId", required=false, defaultValue="") int reId, 
			@RequestParam(value="content", required=false) String content,
			//@RequestParam(value="writer", required=false) String writer,
			HttpSession session) throws Exception {
		BbsReply result = boardService.modifyRe(idx, content);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	/**
	 * Q&A 댓글 삭제하기
	 * 
	 * @param idx
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/qa/re/{idx}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> deleteReply(
			@PathVariable("fid") Integer idx,
			//@RequestParam(value="reId", required=false, defaultValue="") int reId,
			HttpSession session) throws Exception {

		boolean result = boardService.deleteRe(idx);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
}