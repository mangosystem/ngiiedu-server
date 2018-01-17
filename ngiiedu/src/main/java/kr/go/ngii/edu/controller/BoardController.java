package kr.go.ngii.edu.controller;

import java.io.File;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.Module;

import kr.go.ngii.edu.main.board.model.BbsFAQuestion;
import kr.go.ngii.edu.main.board.model.BbsNotice;
import kr.go.ngii.edu.main.board.model.BbsPds;
import kr.go.ngii.edu.main.board.model.BbsPdsFile;
import kr.go.ngii.edu.main.board.model.BbsQuestion;
import kr.go.ngii.edu.main.board.model.BbsReply;
import kr.go.ngii.edu.main.board.service.BoardService;
import kr.go.ngii.edu.main.common.RestAPIClient;
import kr.go.ngii.edu.main.courses.work.model.WorkOutput;
import kr.go.ngii.edu.main.courses.work.service.WorkOutputService;
import kr.go.ngii.edu.main.users.model.User;
import kr.go.ngii.edu.common.enums.EnumRestAPIType;
import kr.go.ngii.edu.config.LocalResourceBundle;
import kr.go.ngii.edu.controller.rest.BaseController;
import kr.go.ngii.edu.controller.rest.OutputStreamConnect;
import kr.go.ngii.edu.controller.rest.ResponseData;

@Controller
@RequestMapping("/api/v1/board")
public class BoardController extends BaseController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private WorkOutputService workOutputService;
	
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
	 * @param description
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> insertNotice(
			@RequestParam(value="title", required=true, defaultValue="") String title,
			@RequestParam(value="description", required=true, defaultValue="") String description, 
			HttpSession session) throws Exception {
		
		BbsNotice result = boardService.insertNotice(title, description);
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
			@RequestParam(value="description", required=false) String description,
			HttpSession session) throws Exception {

		BbsNotice result = boardService.modifyNotice(noticeId, title, description);
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
	 * FAQ 목록 조회하기
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/faq", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getFAQuestionList(
			@RequestParam(value="offset", required=false, defaultValue="0") Integer offset, 
			@RequestParam(value="limit", required=false, defaultValue="0") Integer limit, 
			HttpSession session) throws Exception {
		List<BbsFAQuestion> list = null;
		if (offset==0 && limit==0) {
			list = boardService.getFaqList();

		} else {
			list = boardService.getFaqList(offset, limit);
		}
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}
	
	/**
	 * FAQ 조회하기
	 * 
	 * @param idx
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/faq/{faqId}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getFAQuestionListbyId(
			@PathVariable("faqId") Integer qaId,
			HttpSession session) throws Exception {

		BbsFAQuestion list = boardService.getFaqListbyId(qaId);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}
	
	/**
	 * FAQ 생성하기
	 * 
	 * @param title
	 * @param content
	 * @param writer
	 * @param attach
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/faq", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> insertFAQuestion(
			@RequestParam(value="title", required=true, defaultValue="") String title,
			@RequestParam(value="description", required=true, defaultValue="") String description, 
//			@RequestParam(value="writer", required=true, defaultValue="") String writer, 
//			@RequestParam(value="attach", required=true, defaultValue="") String attach, 
			HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		BbsFAQuestion result = boardService.insertFaq(title, description, user.getIdx());
		//String result = null;
		//result = boardService.insertNotice(title, content);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
		//return new ResponseEntity<List<Notice>>(responseBody(result), HttpStatus.OK);
	}
	
	/**
	 * FAQ 변경하기
	 * @param idx
	 * @param title
	 * @param attach
	 * @param content
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/faq/{faqId}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> modifyFAQuestion(
			@PathVariable("faqId") int faqId,
			@RequestParam(value="title", required=false, defaultValue="") String title, 
			@RequestParam(value="description", required=false) String description,
			HttpSession session) throws Exception {

		BbsFAQuestion result = boardService.modifyFaq(faqId, title, description);
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
	@RequestMapping(value="/faq/{faqId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> deleteFAQuestion(
			@PathVariable("faqId") Integer faqId,
			HttpSession session) throws Exception {
		boolean result = boardService.deleteFaq(faqId);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	

	/**
	 * Q&A 목록 조회하기
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/qna", method=RequestMethod.GET)
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
	@RequestMapping(value="/qna/{qnaId}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getQuestionListbyId(
			@PathVariable("qnId") Integer qaId,
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
	@RequestMapping(value="/qna", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> insertQuestion(
			@RequestParam(value="title", required=true, defaultValue="") String title,
			@RequestParam(value="description", required=true, defaultValue="") String description, 
//			@RequestParam(value="writer", required=true, defaultValue="") String writer, 
//			@RequestParam(value="attach", required=true, defaultValue="") String attach, 
			HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		BbsQuestion result = boardService.insertQna(title, description, user.getIdx());
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
	@RequestMapping(value="/qna/{qnaId}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> modifyQuestion(
			@PathVariable("qnaId") Integer qnaId,
			@RequestParam(value="title", required=false, defaultValue="") String title, 
			@RequestParam(value="description", required=false) String description,
			@RequestParam(value="attach", required=false) String attach,
			HttpSession session) throws Exception {

		BbsQuestion result = boardService.modifyQna(qnaId, title, description);
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
	@RequestMapping(value="/qna/{qnaId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> deleteQuestion(
			@PathVariable("qnaId") Integer qnaId,
			HttpSession session) throws Exception {

		boolean result = boardService.deleteQna(qnaId);
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
	@RequestMapping(value="/qna/re/{qnaReId}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getReplyListbyId(
			@PathVariable("qaId") Integer qnaReId,
			HttpSession session) throws Exception {

		List<BbsReply> list = boardService.getReListbyQnaId(qnaReId);
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
	@RequestMapping(value="/qna/re", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> insertReply(
			@RequestParam(value="qnaId", required=true, defaultValue="") int qnaId,
			@RequestParam(value="description", required=true, defaultValue="") String description, 
//			@RequestParam(value="writer", required=true, defaultValue="") String writer, 
			HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
		BbsReply result = boardService.insertRe(qnaId, description, user.getIdx());
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
	@RequestMapping(value="/qna/re/{qnaReId}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> modifyReply(
			@PathVariable("qnaReId") int qnaReId,
			//@RequestParam(value="reId", required=false, defaultValue="") int reId, 
			@RequestParam(value="description", required=false) String description,
			//@RequestParam(value="writer", required=false) String writer,
			HttpSession session) throws Exception {
		BbsReply result = boardService.modifyRe(qnaReId, description);
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
	@RequestMapping(value="/qna/re/{qnaReId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> deleteReply(
			@PathVariable("qnaReId") Integer qnaReId,
			//@RequestParam(value="reId", required=false, defaultValue="") int reId,
			HttpSession session) throws Exception {
		
		boolean result = boardService.deleteRe(qnaReId);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	
	
	/**
	 * 자료실 목록 조회하기
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/pds", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getPdsList(
			@RequestParam(value="offset", required=false, defaultValue="0") Integer offset, 
			@RequestParam(value="limit", required=false, defaultValue="0") Integer limit, 
			HttpSession session) throws Exception {
		List<BbsPds> list = null;
		if (offset==0 && limit==0) {
			list = boardService.getPdsList();

		} else {
			list = boardService.getPdsList(offset, limit);
		}
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}
	
	/**
	 * 자료실 조회하기
	 * 
	 * @param idx
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/pds/{pdsId}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getPdsListbyId(
			@PathVariable("pdsId") int pdsId,
			HttpSession session) throws Exception {

		BbsPds list = boardService.getPdsById(pdsId);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}
	
	/**
	 * 자료실 생성하기
	 * 
	 * @param title
	 * @param description
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/pds", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> insertPds(
			@RequestParam(value="title", required=true, defaultValue="") String title,
			@RequestParam(value="description", required=true, defaultValue="") String description, 
			@RequestParam(value="attach", required=false) MultipartFile attach,
			HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("USER_INFO");
		if (user == null) {
			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
		}
		
//		System.out.println(session.getServletContext().getRealPath("/"));
		BbsPds result = boardService.insertPds(title, description, attach);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	/**
	 * 자료실 파일 다운로드 생성하기
	 * 
	 * @param title
	 * @param description
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/pds/file/{fileId}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<InputStreamResource> getPdsFile(
			@PathVariable(value="fileId") String fileId,
			HttpSession session) throws Exception { 
		
		BbsPdsFile bbsPdsFile = boardService.getPdsFileByUUID(fileId);
		
		StringBuffer fileName = new StringBuffer()
				.append(bbsPdsFile.getFilePath()).append(".").append(bbsPdsFile.getExt());
        String path = String.format("%s%s", LocalResourceBundle.FILE_SAVE_REPOSITORY, fileName);
		FileSystemResource resource = new FileSystemResource(path);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    	String encordedFilename = URLEncoder.encode(bbsPdsFile.getFileName(), "UTF-8").replace("+", "%20");
    	responseHeaders.set("Content-Disposition",
    			  "attachment;filename=" + encordedFilename + ";filename*= UTF-8''" + encordedFilename);

//    	responseHeaders.set("Content-Disposition", "attachment;filename=\"" + fileName + "\";");
    	responseHeaders.set("Content-Transfer-Encoding", "binary");
    	return new ResponseEntity<InputStreamResource>(new InputStreamResource(resource.getInputStream()), responseHeaders, HttpStatus.OK);
	}
	
	
	/**
	 * 자료실 변경하기
	 * @param idx
	 * @param title
	 * @param content
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/pds/{pdsId}", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> modifyPds(
			@PathVariable("pdsId") Integer pdsId,
			@RequestParam(value="title", required=true, defaultValue="") String title,
			@RequestParam(value="description", required=true, defaultValue="") String description, 
			@RequestParam(value="attach", required=false) MultipartFile attach,
			HttpSession session) throws Exception {

		BbsPds result = boardService.modifyPds(pdsId, title, description, attach);
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
	@RequestMapping(value="/pds/{pdsId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> deletelPds(
			@PathVariable("pdsId") int pdsId,
			HttpSession session) throws Exception {

		boolean result = boardService.deletePds(pdsId);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	/**
	 * gallerty 목록 
	 * 
	 * @param title
	 * @param description
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/gallery", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getGalleryList(
			@RequestParam(value="offset", required=false, defaultValue="0") int offset, 
			@RequestParam(value="limit", required=false, defaultValue="100") int limit, 
			HttpSession session) throws Exception {
		
//		String rootPath = session.getServletContext().getRealPath(File.separator) + 
//				File.separator + "assets" + File.separator + "thumbnail" + File.separator;
		String rootPath = session.getServletContext().getRealPath(File.separator) + 
				File.separator + "assets" + File.separator + "images" + File.separator;
//		User user = (User)session.getAttribute("USER_INFO");
//		if (user == null) {
//			return new ResponseEntity<ResponseData>(responseBody(null), HttpStatus.OK);
//		}
		List<WorkOutput> workOutputList = workOutputService.getGalleryList(offset, limit);
		RestAPIClient apiClient = new RestAPIClient();
		for (WorkOutput item:workOutputList) {
			String imgId = item.getPinogioOutputId();
//			String savePath = rootPath + imgId + ".png";
			
			String type = item.getOutputType();
			String apiImageSource = "";
			if ("layer".equals(type)) {
				StringBuffer apiImgPath = new StringBuffer();
				apiImgPath.append(LocalResourceBundle.PINOGIO_SERVER).append("data/thumbnail/layers/")
					.append(imgId).append("/")
					.append(400).append("/")
					.append(400).append(".png");
				apiImageSource = apiImgPath.toString();
			} else if ("maps".equals(type)) {
				
				try {
					Map<String, String> pathParamVals = new HashMap<String,String>();
					pathParamVals.put("maps_id", imgId);
//					apiClient.setApiKey(apiKey);
					Map<String, Object> mapsGetResult = apiClient.getResponseBody(EnumRestAPIType.MAPS_GET, pathParamVals, null);
					Map<String, String> mapsGetResultData = (Map<String, String>) mapsGetResult.get("data");
					
					String mapsType = mapsGetResultData.get("mapsType");
					String typeKind = mapsGetResultData.get("typeKind");
					
					String imageFileName = "";
					if ("BASIC".equalsIgnoreCase(mapsType)) {
						// left, right, top
						imageFileName = typeKind;
					} else if ("STORY".equalsIgnoreCase(mapsType)) {
						// tab
						imageFileName = typeKind;
					} else if ("SWIPE".equalsIgnoreCase(mapsType)) {
						imageFileName = "swipe";
					} else if ("SERIES".equalsIgnoreCase(mapsType)) {
						// slide
						imageFileName = typeKind;
					} else if ("SPLIT".equalsIgnoreCase(mapsType)) {
						imageFileName = "split";
					}
					StringBuffer apiImgPath = new StringBuffer();
					apiImgPath.append(rootPath).append(imageFileName).append(".png");
					apiImageSource = apiImgPath.toString();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (!"".equals(apiImageSource)) {
//				GISServerConnect.requestGET(new URL(apiImageSource), savePath);
				item.setThumbNailPath(apiImageSource);
//				StringBuffer sb = new StringBuffer();
//				sb.append(session.getServletContext().getContextPath()).append(File.separator)
//					.append("assets").append(File.separator)
//					.append("thumbnail").append(File.separator)
//					.append(imgId).append(".png");
//				item.setThumbNailPath(sb.toString());
			}
		}
		return new ResponseEntity<ResponseData>(responseBody(workOutputList), HttpStatus.OK);
	}
	
}
