package kr.go.ngii.edu;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import kr.go.ngii.edu.config.LocalResourceBundle;
import kr.go.ngii.edu.controller.rest.BaseController;
import kr.go.ngii.edu.controller.rest.ResponseData;
import kr.go.ngii.edu.main.board.model.BbsFAQuestion;
import kr.go.ngii.edu.main.board.model.BbsNotice;
import kr.go.ngii.edu.main.board.model.BbsPageCriteria;
import kr.go.ngii.edu.main.board.model.BbsPds;
import kr.go.ngii.edu.main.board.model.BbsQuestion;
import kr.go.ngii.edu.main.board.model.BbsReply;
import kr.go.ngii.edu.main.board.service.BoardService;
import kr.go.ngii.edu.main.courses.course.model.Course;
import kr.go.ngii.edu.main.users.model.User;

@Controller
public class MainController extends BaseController {

	@Autowired
	private BoardService boardService;	

	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public ModelAndView getLoginPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = null;
		if (principal == null) {
			view = new ModelAndView("/index");
		} else {
			RedirectView redirectView = new RedirectView(request.getContextPath() + "/");
			redirectView.setExposeModelAttributes(false);
			view = new ModelAndView(redirectView);
		}
		return view;
	}

	@RequestMapping(value={"/join"}, method = RequestMethod.GET)
	public ModelAndView getJoinPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = null;
		if (principal == null) {
			view = new ModelAndView("/index");
		} else {
			RedirectView redirectView = new RedirectView(request.getContextPath() + "/");
			redirectView.setExposeModelAttributes(false);
			view = new ModelAndView(redirectView);
		}
		return view;
	}

	@RequestMapping(value={"*"}, method = RequestMethod.GET)
	public ModelAndView getViewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {
		
		ModelAndView view = new ModelAndView("/index");
		return view;
	}
	
	@RequestMapping(value={"/main"}, method = RequestMethod.GET)
	public ModelAndView getMainPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {
		ModelAndView view = new ModelAndView("/main");
		List<BbsNotice> bbsNoticeList = boardService.getNoticeList(0, 5); 
		List<BbsQuestion> bbsQuestionList = boardService.getQnaList(0, 5); 
		view.getModelMap().addAttribute("noticeItems", bbsNoticeList);
		view.getModelMap().addAttribute("qnaItems", bbsQuestionList);
		return view;
	}

	@RequestMapping(value={"/course/**"}, method = RequestMethod.GET)
	public ModelAndView getCoursePage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {
		
		ModelAndView view = new ModelAndView("/index");
		return view;
	}
	
	@RequestMapping(value={"/storymap/**"}, method = RequestMethod.GET)
	public ModelAndView getStorymapPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {
		
		ModelAndView view = new ModelAndView("/index");
		return view;
	}
	
	@RequestMapping(value={"/map/**"}, method = RequestMethod.GET)
	public ModelAndView getMapPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {
		
		ModelAndView view = new ModelAndView("/index");
		return view;
	}

	@RequestMapping(value={"/courseCreate"}, method = RequestMethod.GET)
	public ModelAndView getCourseCreatePage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/index");

		User user = (User) session.getAttribute("USER_INFO");
		if (user == null) {
			return view;
		} else {
			if ("1".equals(user.getUserDivision().trim())) {
				return view;
			} else {
				RedirectView redirectView = new RedirectView(request.getContextPath() + "/login");
				redirectView.setExposeModelAttributes(false);
				return new ModelAndView(redirectView);

			}
		}
	}

	@RequestMapping(value={"/cm-admin/**"}, method = RequestMethod.GET)
	public ModelAndView getAdminPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {
		
		ModelAndView view = new ModelAndView("/index");
		return view;
	}

	@RequestMapping(value={"/errorFobridden"}, method = RequestMethod.GET)
	public ModelAndView getErrorFobriddenPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		RedirectView redirectView = new RedirectView(request.getContextPath() + "/login");
		redirectView.setExposeModelAttributes(false);
		return new ModelAndView(redirectView);
	}
	
	
	@RequestMapping(value={"/filetest"}, method = RequestMethod.GET)
	public ModelAndView getFileTestPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/filetest");
		return view;
	}

	
	@RequestMapping(value={"/gallary"}, method = RequestMethod.GET)
	public ModelAndView getGellaryPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/gallary/gallary");
		return view;
	}
	
	//notice page
	@RequestMapping(value={"/surport/notice"}, method = RequestMethod.GET)
	public ModelAndView getNoticePage(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value="page", required=false, defaultValue="1") Integer page,
			@RequestParam(value="limit", required=false, defaultValue="0") Integer limit,
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/surport/notice");
		
		User user = (User)session.getAttribute("USER_INFO");
		
		limit = limit == 0 ? LocalResourceBundle.BBS_NOTICE_POSTS_SIZE : limit;
		
		if (page == 0) {
			page = 1;
		}

		int offset = (page -1) * limit;
		
		BbsPageCriteria bbsPageCriteria = new BbsPageCriteria(page, LocalResourceBundle.BBS_NOTICE_LIST_SIZE, limit);
		List<BbsNotice> bbsNoticeList = boardService.getNoticeList(offset, limit); 
		bbsPageCriteria.setRecordsNum(boardService.getNoticeCnt());
		
		String bbsrole = "1".equals(user.getUserDivision().trim()) ? "ADM" : "USR";
		view.getModelMap().addAttribute("bbsrole", bbsrole);
		view.getModelMap().addAttribute("items", bbsNoticeList);
		view.getModelMap().addAttribute("criteria", bbsPageCriteria);
		
		return view;
	}
	
	@RequestMapping(value={"/surport/noticeNew"}, method = RequestMethod.GET)
	public ModelAndView getNoticeNewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {
		ModelAndView view = new ModelAndView("/surport/notice_new");
		return view;
	}
	
	@RequestMapping(value={"/surport/noticeModify/{noticeId}"}, method = RequestMethod.GET)
	public ModelAndView getNoticeModifyPage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer noticeId,
			HttpSession session, Principal principal) {
		ModelAndView view = new ModelAndView("/surport/notice_mod");
		
		User user = (User)session.getAttribute("USER_INFO");
		
		BbsNotice bbsNotice = boardService.getNoticeListbyId(noticeId);
		
		view.getModelMap().addAttribute("postItem", bbsNotice);
		return view;
	}
	
	//notice view page
	@RequestMapping(value={"/surport/noticeView/{noticeId}"}, method = RequestMethod.GET)
	public ModelAndView getNoticeViewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal,
			@PathVariable Integer noticeId) {
		ModelAndView view = new ModelAndView("/surport/notice_view");
		
		BbsNotice bbsNotice = boardService.getNoticeListbyId(noticeId);
		
		User user = (User)session.getAttribute("USER_INFO");
		
		String bbsrole = "1".equals(user.getUserDivision().trim()) ? "ADM" : "USR";
		view.getModelMap().addAttribute("bbsrole", bbsrole);
		view.getModelMap().addAttribute("postItem", bbsNotice);
		return view;
	}
	
	@RequestMapping(value={"/surport/faq"}, method = RequestMethod.GET)
	public ModelAndView getFaqPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="page", required=false, defaultValue="1") Integer page,
			@RequestParam(value="limit", required=false, defaultValue="0") Integer limit,
			HttpSession session, Principal principal) {
		ModelAndView view = new ModelAndView("/surport/faq");
		
		User user = (User)session.getAttribute("USER_INFO");
		
		limit = limit == 0 ? LocalResourceBundle.BBS_FAQ_POSTS_SIZE : limit;
		
		if (page == 0) {
			page = 1;
		}

		int offset = (page -1) * limit;
		
		BbsPageCriteria bbsPageCriteria = new BbsPageCriteria(page, LocalResourceBundle.BBS_FAQ_LIST_SIZE, limit);
		List<BbsFAQuestion> bbsFAQuestionList = boardService.getFaqList(offset, limit); 
		bbsPageCriteria.setRecordsNum(boardService.getFaqCnt());
		
		String bbsrole = "1".equals(user.getUserDivision().trim()) ? "ADM" : "USR";
		view.getModelMap().addAttribute("bbsrole", bbsrole);
		view.getModelMap().addAttribute("items", bbsFAQuestionList);
		view.getModelMap().addAttribute("criteria", bbsPageCriteria);
		return view;
	}
	
	@RequestMapping(value={"/surport/faqNew"}, method = RequestMethod.GET)
	public ModelAndView getFaqNewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {
		ModelAndView view = new ModelAndView("/surport/faq_new");
		return view;
	}
	
	
	@RequestMapping(value={"/surport/qna"}, method = RequestMethod.GET)
	public ModelAndView getQnaPage(
			@RequestParam(value="page", required=false, defaultValue="1") Integer page,
			@RequestParam(value="limit", required=false, defaultValue="0") Integer limit,
			HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {
		ModelAndView view = new ModelAndView("/surport/qna");
		
		User user = (User)session.getAttribute("USER_INFO");
		
		limit = limit == 0 ? LocalResourceBundle.BBS_QNA_POSTS_SIZE : limit;
		
		if (page == 0) {
			page = 1;
		}

		int offset = (page -1) * limit;
		
		BbsPageCriteria bbsPageCriteria = new BbsPageCriteria(page, LocalResourceBundle.BBS_QNA_LIST_SIZE, limit);
		List<BbsQuestion> bbsQuestionList = boardService.getQnaList(offset, limit); 
		bbsPageCriteria.setRecordsNum(boardService.getQnaCnt());
		
		String bbsrole = "1".equals(user.getUserDivision().trim()) ? "ADM" : "USR";
		view.getModelMap().addAttribute("bbsrole", bbsrole);
		view.getModelMap().addAttribute("items", bbsQuestionList);
		view.getModelMap().addAttribute("criteria", bbsPageCriteria);
		return view;
	}
	
	//qna view page
	@RequestMapping(value={"/surport/qnaView/{qnaId}"}, method = RequestMethod.GET)
	public ModelAndView getQnaViewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal,
			@PathVariable Integer qnaId) {
		ModelAndView view = new ModelAndView("/surport/qna_view");
		BbsQuestion bbsQuestion = boardService.getQnaListbyId(qnaId);
		List<BbsReply> bbsReply = boardService.getReListbyQnaId(qnaId);
		
		// user 및 권한 변경 필요함
		User user = (User)session.getAttribute("USER_INFO");
		String bbsrole = "1".equals(user.getUserDivision().trim()) ? "ADM" : "USR";
		view.getModelMap().addAttribute("bbsrole", bbsrole);
		view.getModelMap().addAttribute("postItem", bbsQuestion);
		view.getModelMap().addAttribute("reItems", bbsReply);
		return view;
	}
	
	//qna new page
	@RequestMapping(value={"/surport/qnaNew"}, method = RequestMethod.GET)
	public ModelAndView getQnaNewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {
		ModelAndView view = new ModelAndView("/surport/qna_new");
		return view;
	}
	
	@RequestMapping(value={"/surport/qnaModify/{qnaId}"}, method = RequestMethod.GET)
	public ModelAndView getQnaModifyPage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer qnaId,
			HttpSession session, Principal principal) {
		ModelAndView view = new ModelAndView("/surport/qna_mod");
		
		User user = (User)session.getAttribute("USER_INFO");
		
		BbsQuestion bbsQuestion = boardService.getQnaListbyId(qnaId);
		
		if (user.getIdx() == bbsQuestion.getUserId()) {
			return view;
		}
		
		view.getModelMap().addAttribute("postItem", bbsQuestion);
		return view;
	}
	
	@RequestMapping(value={"/surport/download"}, method = RequestMethod.GET)
	public ModelAndView getPdsPage(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value="page", required=false, defaultValue="1") Integer page,
			@RequestParam(value="limit", required=false, defaultValue="0") Integer limit,
			HttpSession session, Principal principal) {
		ModelAndView view = new ModelAndView("/surport/download");
		
		User user = (User)session.getAttribute("USER_INFO");
		
		limit = limit == 0 ? LocalResourceBundle.BBS_PDS_POSTS_SIZE : limit;
		
		if (page == 0) {
			page = 1;
		}

		int offset = (page -1) * limit;
		
		BbsPageCriteria bbsPageCriteria = new BbsPageCriteria(page, LocalResourceBundle.BBS_PDS_LIST_SIZE, limit);
		List<BbsPds> bbsPdsList = boardService.getPdsList(offset, limit); 
		bbsPageCriteria.setRecordsNum(boardService.getPdsCnt());
		
		String bbsrole = "1".equals(user.getUserDivision().trim()) ? "ADM" : "USR";
		view.getModelMap().addAttribute("bbsrole", bbsrole);
		view.getModelMap().addAttribute("items", bbsPdsList);
		view.getModelMap().addAttribute("criteria", bbsPageCriteria);
		return view;
	}
	
	@RequestMapping(value={"/surport/downloadView/{pdsId}"}, method = RequestMethod.GET)
	public ModelAndView getDownloadViewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal,
			@PathVariable Integer pdsId) {
		
		ModelAndView view = new ModelAndView("/surport/download_view");

		BbsPds bbsPds = boardService.getPdsById(pdsId);
		
		// user 및 권한 변경 필요함
		User user = (User)session.getAttribute("USER_INFO");
		String bbsrole = "1".equals(user.getUserDivision().trim()) ? "ADM" : "USR";
		view.getModelMap().addAttribute("bbsrole", bbsrole);
		view.getModelMap().addAttribute("postItem", bbsPds);
		return view;
	}
	
	@RequestMapping(value={"/surport/downloadNew"}, method = RequestMethod.GET)
	public ModelAndView getDownloadNewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {
		ModelAndView view = new ModelAndView("/surport/download_new");
		return view;
	}
	
	@RequestMapping(value={"/surport/downloadModify/{pdsId}"}, method = RequestMethod.GET)
	public ModelAndView getDownloadModifyPage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer pdsId,
			HttpSession session, Principal principal) {
		ModelAndView view = new ModelAndView("/surport/download_mod");
		
		User user = (User)session.getAttribute("USER_INFO");
		
		BbsPds bbsPds = boardService.getPdsById(pdsId);
		
		if (user.getIdx() == bbsPds.getUserId()) {
			return view;
		}
		
		view.getModelMap().addAttribute("postItem", bbsPds);
		return view;
	}
	
	@RequestMapping(value={"/introduce"}, method = RequestMethod.GET)
	public ModelAndView getIntroducePage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/introduce/introduce");
		return view;
	}
	
	@RequestMapping(value={"/introduce/{courseId}"}, method = RequestMethod.GET)
	public ModelAndView getIntroduceSubPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal,@PathVariable String courseId) {
		ModelAndView view = new ModelAndView("/introduce/"+courseId);
		return view;
	}
	
	
}
