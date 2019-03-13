package kr.go.ngii.edu;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import kr.go.ngii.edu.common.message.ErrorMessage;
import kr.go.ngii.edu.config.LocalResourceBundle;
import kr.go.ngii.edu.controller.rest.BaseController;
import kr.go.ngii.edu.controller.rest.OutputStreamConnect;
import kr.go.ngii.edu.main.board.model.BbsFAQuestion;
import kr.go.ngii.edu.main.board.model.BbsNotice;
import kr.go.ngii.edu.main.board.model.BbsPageCriteria;
import kr.go.ngii.edu.main.board.model.BbsPds;
import kr.go.ngii.edu.main.board.model.BbsPdsFile;
import kr.go.ngii.edu.main.board.model.BbsQuestion;
import kr.go.ngii.edu.main.board.model.BbsReply;
import kr.go.ngii.edu.main.board.service.BoardService;
import kr.go.ngii.edu.main.users.model.User;

@Controller
public class MainController extends BaseController {

	@Autowired
	private BoardService boardService;	

	@RequestMapping(value={"","/"}, method = RequestMethod.GET)
	public ModelAndView getViewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/index");
		List<BbsNotice> bbsNoticeList = boardService.getNoticeList(0, 5); 
		List<BbsQuestion> bbsQuestionList = boardService.getQnaList(0, 5); 
		view.getModelMap().addAttribute("noticeItems", bbsNoticeList);
		view.getModelMap().addAttribute("qnaItems", bbsQuestionList);
		return view;
	}

	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public ModelAndView getLoginPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = null;
		if (principal == null) {
			view = new ModelAndView("/course");
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
			view = new ModelAndView("/course");
		} else {
			RedirectView redirectView = new RedirectView(request.getContextPath() + "/");
			redirectView.setExposeModelAttributes(false);
			view = new ModelAndView(redirectView);
		}
		return view;
	}

	@RequestMapping(value={"/course"}, method = RequestMethod.GET)
	public ModelAndView getCourseViewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/course");
		return view;
	}

	@RequestMapping(value={"/course/**"}, method = RequestMethod.GET)
	public ModelAndView getCoursePage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/course");
		return view;
	}

	@RequestMapping(value={"/storymap/**"}, method = RequestMethod.GET)
	public ModelAndView getStorymapPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/course");
		return view;
	}

	@RequestMapping(value={"/map/**"}, method = RequestMethod.GET)
	public ModelAndView getMapPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/course");
		return view;
	}

	@RequestMapping(value={"/maps/**"}, method = RequestMethod.GET)
	public ModelAndView getMapsPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/course");
		return view;
	}

	@RequestMapping(value={"/swipe"}, method = RequestMethod.GET)
	public ModelAndView getSwipePage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/course");
		return view;
	}

	@RequestMapping(value={"/split"}, method = RequestMethod.GET)
	public ModelAndView getSplitPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/course");
		return view;
	}

	@RequestMapping(value={"/courseCreate"}, method = RequestMethod.GET)
	public ModelAndView getCourseCreatePage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/course");

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

		User user = (User)session.getAttribute("USER_INFO");

		if (user == null) {
			return new ModelAndView("redirect:" + request.getContextPath());
		}

		if ("3".equals(user.getUserDivision().trim())) {
			return new ModelAndView("/course");
		} else {
			return new ModelAndView("redirect:/");
		}
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


	@RequestMapping(value={"/gallery/view/l/{layer_id}"}, method = RequestMethod.GET)
	public ModelAndView getGalleryViewLayer(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable("layer_id") String layerId,
			HttpSession session, Principal principal) {

		ModelAndView view = null;
		view = new ModelAndView("/course");
		return view;
	}

	@RequestMapping(value={"/gallery/view/m/{maps_id}"}, method = RequestMethod.GET)
	public ModelAndView getGalleryViewMaps(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable("maps_id") String mapsId,
			HttpSession session, Principal principal) {

		ModelAndView view = null;
		view = new ModelAndView("/course");
		return view;
	}


	@RequestMapping(value={"/gallery"}, method = RequestMethod.GET)
	public ModelAndView getGellaryPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/gallery/gallery");
		return view;
	}


	/**
	 * 공지사항 목록 페이지
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param limit
	 * @param session
	 * @param principal
	 * @return
	 */
	@RequestMapping(value={"/surport/notice"}, method = RequestMethod.GET)
	public ModelAndView getNoticePage(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value="page", required=false, defaultValue="1") Integer page,
			@RequestParam(value="limit", required=false, defaultValue="0") Integer limit,
			HttpSession session, Principal principal) {


		User user = (User)session.getAttribute("USER_INFO");
		String bbsrole = "GUEST";

		if (user != null) {
			bbsrole = "3".equals(user.getUserDivision().trim()) ? "ADM" : "USR";
		}

		limit = limit == 0 ? LocalResourceBundle.BBS_NOTICE_POSTS_SIZE : limit;

		if (page == 0) {
			page = 1;
		}

		int offset = (page -1) * limit;

		BbsPageCriteria bbsPageCriteria = new BbsPageCriteria(page, LocalResourceBundle.BBS_NOTICE_LIST_SIZE, limit);
		List<BbsNotice> bbsNoticeList = boardService.getNoticeList(offset, limit); 
		bbsPageCriteria.setRecordsNum(boardService.getNoticeCnt());

		ModelAndView view = new ModelAndView("/surport/notice");
		view.getModelMap().addAttribute("bbsrole", bbsrole);
		view.getModelMap().addAttribute("items", bbsNoticeList);
		view.getModelMap().addAttribute("criteria", bbsPageCriteria);

		return view;
	}

	/**
	 * 공지사항 추가하기 페이지
	 * @param request
	 * @param response
	 * @param session
	 * @param principal
	 * @return
	 */
	@RequestMapping(value={"/surport/noticeNew"}, method = RequestMethod.GET)
	public ModelAndView getNoticeNewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		User user = (User)session.getAttribute("USER_INFO");

		if (user == null) {
			RedirectView redirectView = new RedirectView(request.getContextPath() + "/login");
			redirectView.setExposeModelAttributes(false);
			return new ModelAndView(redirectView);

		} else {
			if (!"3".equals(user.getUserDivision().trim())) {
				RedirectView redirectView = new RedirectView(request.getContextPath() + "/surport/notice");
				redirectView.setExposeModelAttributes(false);
				return new ModelAndView(redirectView);
			}

			String bbsrole = "3".equals(user.getUserDivision().trim()) ? "ADM" : "USR";

			ModelAndView view = new ModelAndView("/surport/notice_new");
			view.getModelMap().addAttribute("bbsrole", bbsrole);
			return view;
		}
	}

	/**
	 * 공지사항 수정하기 페이지
	 * 
	 * @param request
	 * @param response
	 * @param noticeId
	 * @param session
	 * @param principal
	 * @return
	 */
	@RequestMapping(value={"/surport/noticeModify/{noticeId}"}, method = RequestMethod.GET)
	public ModelAndView getNoticeModifyPage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer noticeId,
			HttpSession session, Principal principal) {

		User user = (User)session.getAttribute("USER_INFO");

		if (user == null) {
			RedirectView redirectView = new RedirectView(request.getContextPath() + "/login");
			redirectView.setExposeModelAttributes(false);
			return new ModelAndView(redirectView);

		} else {
			if (!"3".equals(user.getUserDivision().trim())) {
				RedirectView redirectView = new RedirectView(request.getContextPath() + "/surport/notice");
				redirectView.setExposeModelAttributes(false);
				return new ModelAndView(redirectView);
			}

			String bbsrole = "3".equals(user.getUserDivision().trim()) ? "ADM" : "USR";

			ModelAndView view = new ModelAndView("/surport/notice_mod");
			BbsNotice bbsNotice = boardService.getNoticeListbyId(noticeId);
			view.getModelMap().addAttribute("postItem", bbsNotice);
			view.getModelMap().addAttribute("bbsrole", bbsrole);
			return view;
		}
	}

	/**
	 * 공지사항 게시물보기 페이지
	 * @param request
	 * @param response
	 * @param session
	 * @param principal
	 * @param noticeId
	 * @return
	 */
	@RequestMapping(value={"/surport/noticeView/{noticeId}"}, method = RequestMethod.GET)
	public ModelAndView getNoticeViewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal,
			@PathVariable Integer noticeId) {

		BbsNotice bbsNotice = boardService.getNoticeListbyId(noticeId);

		User user = (User)session.getAttribute("USER_INFO");
		String bbsrole = "GUEST";

		if (user != null) {
			bbsrole = "3".equals(user.getUserDivision().trim()) ? "ADM" : "USR";
		}

		ModelAndView view = new ModelAndView("/surport/notice_view");
		view.getModelMap().addAttribute("bbsrole", bbsrole);
		view.getModelMap().addAttribute("postItem", bbsNotice);

		return view;
	}

	/**
	 * 자주 묻는 질문 리스트
	 * @param request
	 * @param response
	 * @param page
	 * @param limit
	 * @param session
	 * @param principal
	 * @return
	 */
	@RequestMapping(value={"/surport/faq"}, method = RequestMethod.GET)
	public ModelAndView getFaqPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="page", required=false, defaultValue="1") Integer page,
			@RequestParam(value="limit", required=false, defaultValue="0") Integer limit,
			HttpSession session, Principal principal) {

		User user = (User)session.getAttribute("USER_INFO");
		String bbsrole = "GUEST";

		if (user != null) {
			bbsrole = "3".equals(user.getUserDivision().trim()) ? "ADM" : "USR";
		}

		limit = limit == 0 ? LocalResourceBundle.BBS_FAQ_POSTS_SIZE : limit;

		if (page == 0) {
			page = 1;
		}

		int offset = (page -1) * limit;

		BbsPageCriteria bbsPageCriteria = new BbsPageCriteria(page, LocalResourceBundle.BBS_FAQ_LIST_SIZE, limit);
		List<BbsFAQuestion> bbsFAQuestionList = boardService.getFaqList(offset, limit); 
		bbsPageCriteria.setRecordsNum(boardService.getFaqCnt());

		ModelAndView view = new ModelAndView("/surport/faq");
		view.getModelMap().addAttribute("items", bbsFAQuestionList);
		view.getModelMap().addAttribute("criteria", bbsPageCriteria);
		view.getModelMap().addAttribute("bbsrole", bbsrole);

		return view;
	}

	/**
	 * 자주 묻는 질문 추가하기
	 * @param request
	 * @param response
	 * @param session
	 * @param principal
	 * @return
	 */
	@RequestMapping(value={"/surport/faqNew"}, method = RequestMethod.GET)
	public ModelAndView getFaqNewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		User user = (User)session.getAttribute("USER_INFO");

		if (user == null) {
			RedirectView redirectView = new RedirectView(request.getContextPath() + "/login");
			redirectView.setExposeModelAttributes(false);
			return new ModelAndView(redirectView);

		} else {
			if (!"3".equals(user.getUserDivision().trim())) {
				RedirectView redirectView = new RedirectView(request.getContextPath() + "/surport/faq");
				redirectView.setExposeModelAttributes(false);
				return new ModelAndView(redirectView);
			}

			String bbsrole = "3".equals(user.getUserDivision().trim()) ? "ADM" : "USR";

			ModelAndView view = new ModelAndView("/surport/faq_new");
			view.getModelMap().addAttribute("bbsrole", bbsrole);
			return view;
		}
	}

	/**
	 * 자주 묻는 질문 수정하기
	 * @param request
	 * @param response
	 * @param faqId
	 * @param session
	 * @param principal
	 * @return
	 */
	@RequestMapping(value={"/surport/faqModify/{faqId}"}, method = RequestMethod.GET)
	public ModelAndView getFaqModifyPage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer faqId,
			HttpSession session, Principal principal) {

		User user = (User)session.getAttribute("USER_INFO");

		if (user == null) {
			RedirectView redirectView = new RedirectView(request.getContextPath() + "/login");
			redirectView.setExposeModelAttributes(false);
			return new ModelAndView(redirectView);

		} else {
			if (!"3".equals(user.getUserDivision().trim())) {
				RedirectView redirectView = new RedirectView(request.getContextPath() + "/surport/faq");
				redirectView.setExposeModelAttributes(false);
				return new ModelAndView(redirectView);
			}

			String bbsrole = "3".equals(user.getUserDivision().trim()) ? "ADM" : "USR";

			ModelAndView view = new ModelAndView("/surport/faq_mod");

			BbsFAQuestion bbsFAQuestion = boardService.getFaqListbyId(faqId);
			view.getModelMap().addAttribute("postItem", bbsFAQuestion);
			view.getModelMap().addAttribute("bbsrole", bbsrole);
			return view;
		}
	}

	/**
	 * 묻고 답하기 목록 페이지
	 * @param page
	 * @param limit
	 * @param request
	 * @param response
	 * @param session
	 * @param principal
	 * @return
	 */
	@RequestMapping(value={"/surport/qna"}, method = RequestMethod.GET)
	public ModelAndView getQnaPage(
			@RequestParam(value="page", required=false, defaultValue="1") Integer page,
			@RequestParam(value="limit", required=false, defaultValue="0") Integer limit,
			HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		User user = (User)session.getAttribute("USER_INFO");
		String bbsrole = "GUEST";

		if (user != null) {
			bbsrole = "3".equals(user.getUserDivision().trim()) ? "ADM" : "USR";
		}

		limit = limit == 0 ? LocalResourceBundle.BBS_QNA_POSTS_SIZE : limit;

		if (page == 0) {
			page = 1;
		}

		int offset = (page -1) * limit;

		BbsPageCriteria bbsPageCriteria = new BbsPageCriteria(page, LocalResourceBundle.BBS_QNA_LIST_SIZE, limit);
		List<BbsQuestion> bbsQuestionList = boardService.getQnaList(offset, limit); 
		bbsPageCriteria.setRecordsNum(boardService.getQnaCnt());

		ModelAndView view = new ModelAndView("/surport/qna");
		view.getModelMap().addAttribute("bbsrole", bbsrole);
		view.getModelMap().addAttribute("items", bbsQuestionList);
		view.getModelMap().addAttribute("criteria", bbsPageCriteria);
		return view;
	}

	/**
	 * 묻고 답하기 게시물보기 페이지
	 * @param request
	 * @param response
	 * @param session
	 * @param principal
	 * @param qnaId
	 * @return
	 */
	@RequestMapping(value={"/surport/qnaView/{qnaId}"}, method = RequestMethod.GET)
	public ModelAndView getQnaViewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal,
			@PathVariable Integer qnaId) {

		BbsQuestion bbsQuestion = boardService.getQnaListbyId(qnaId);
		List<BbsReply> bbsReply = boardService.getReListbyQnaId(qnaId);

		// user 및 권한 변경 필요함
		String bbsrole = "";
		try {
			User user = (User)session.getAttribute("USER_INFO");
			String division = user.getUserDivision().trim();
			if ("3".equals(division)) {
				bbsrole = "ADM";
			} else {
				if (user.getIdx() == bbsQuestion.getUserId()) {
					bbsrole = "WRITER";
				} else {
					bbsrole = "USR";
				}
			}
		} catch (NullPointerException e) {
			bbsrole = "GUEST";
		}

		ModelAndView view = new ModelAndView("/surport/qna_view");
		view.getModelMap().addAttribute("bbsrole", bbsrole);
		view.getModelMap().addAttribute("postItem", bbsQuestion);
		view.getModelMap().addAttribute("reItems", bbsReply);
		return view;
	}

	/**
	 * 묻고 답하기 게시물 작성 페이지
	 * @param request
	 * @param response
	 * @param session
	 * @param principal
	 * @return
	 */
	@RequestMapping(value={"/surport/qnaNew"}, method = RequestMethod.GET)
	public ModelAndView getQnaNewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		User user = (User)session.getAttribute("USER_INFO");

		if (user == null) {
			RedirectView redirectView = new RedirectView(request.getContextPath() + "/login");
			redirectView.setExposeModelAttributes(false);
			return new ModelAndView(redirectView);

		} else {
			String bbsrole = "3".equals(user.getUserDivision().trim()) ? "ADM" : "USR";

			ModelAndView view = new ModelAndView("/surport/qna_new");
			view.getModelMap().addAttribute("bbsrole", bbsrole);
			return view;
		}
	}

	/**
	 * 묻고 답하기 게시물 수정 페이지
	 * @param request
	 * @param response
	 * @param qnaId
	 * @param session
	 * @param principal
	 * @return
	 */
	@RequestMapping(value={"/surport/qnaModify/{qnaId}"}, method = RequestMethod.GET)
	public ModelAndView getQnaModifyPage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer qnaId,
			HttpSession session, Principal principal) {

		User user = (User)session.getAttribute("USER_INFO");

		if (user == null) {
			RedirectView redirectView = new RedirectView(request.getContextPath() + "/login");
			redirectView.setExposeModelAttributes(false);
			return new ModelAndView(redirectView);

		} else {

			BbsQuestion bbsQuestion = boardService.getQnaListbyId(qnaId);

			if (!"3".equals(user.getUserDivision().trim()) && user.getIdx() != bbsQuestion.getUserId()) {
				RedirectView redirectView = new RedirectView(request.getContextPath() + "/surport/qna");
				redirectView.setExposeModelAttributes(false);
				return new ModelAndView(redirectView);
			}

			String bbsrole = "3".equals(user.getUserDivision().trim()) ? "ADM" : "USR";

			ModelAndView view = new ModelAndView("/surport/qna_mod");
			view.getModelMap().addAttribute("postItem", bbsQuestion);
			view.getModelMap().addAttribute("bbsrole", bbsrole);

			return view;
		}
	}

	/**
	 * 자료실 목록 페이지
	 * @param request
	 * @param response
	 * @param page
	 * @param limit
	 * @param session
	 * @param principal
	 * @return
	 */
	@RequestMapping(value={"/surport/download"}, method = RequestMethod.GET)
	public ModelAndView getPdsPage(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value="page", required=false, defaultValue="1") Integer page,
			@RequestParam(value="limit", required=false, defaultValue="0") Integer limit,
			HttpSession session, Principal principal) {

		User user = (User)session.getAttribute("USER_INFO");
		String bbsrole = "GUEST";

		if (user != null) {
			bbsrole = "3".equals(user.getUserDivision().trim()) ? "ADM" : "USR";
		}

		limit = limit == 0 ? LocalResourceBundle.BBS_PDS_POSTS_SIZE : limit;

		if (page == 0) {
			page = 1;
		}

		int offset = (page -1) * limit;

		BbsPageCriteria bbsPageCriteria = new BbsPageCriteria(page, LocalResourceBundle.BBS_PDS_LIST_SIZE, limit);
		List<BbsPds> bbsPdsList = boardService.getPdsList(offset, limit); 
		bbsPageCriteria.setRecordsNum(boardService.getPdsCnt());

		ModelAndView view = new ModelAndView("/surport/download");
		view.getModelMap().addAttribute("bbsrole", bbsrole);
		view.getModelMap().addAttribute("items", bbsPdsList);
		view.getModelMap().addAttribute("criteria", bbsPageCriteria);

		return view;
	}

	/**
	 * 자료실 게시물 보기 페이지
	 * @param request
	 * @param response
	 * @param session
	 * @param principal
	 * @param pdsId
	 * @return
	 */
	@RequestMapping(value={"/surport/downloadView/{pdsId}"}, method = RequestMethod.GET)
	public ModelAndView getDownloadViewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal,
			@PathVariable Integer pdsId) {

		User user = (User)session.getAttribute("USER_INFO");
		String bbsrole = "GUEST";

		if (user != null) {
			bbsrole = "3".equals(user.getUserDivision().trim()) ? "ADM" : "USR";
		}

		BbsPds bbsPds = boardService.getPdsById(pdsId);
		List<BbsPdsFile> bbsPdsFileList = boardService.getPdsFileList(pdsId);

		ModelAndView view = new ModelAndView("/surport/download_view");
		view.getModelMap().addAttribute("bbsrole", bbsrole);
		view.getModelMap().addAttribute("postItem", bbsPds);
		view.getModelMap().addAttribute("fileItems", bbsPdsFileList);

		return view;
	}

	/**
	 * 다운로드 게시글 추가 페이지
	 * @param request
	 * @param response
	 * @param session
	 * @param principal
	 * @return
	 */
	@RequestMapping(value={"/surport/downloadNew"}, method = RequestMethod.GET)
	public ModelAndView getDownloadNewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		User user = (User)session.getAttribute("USER_INFO");

		if (user == null) {
			RedirectView redirectView = new RedirectView(request.getContextPath() + "/login");
			redirectView.setExposeModelAttributes(false);
			return new ModelAndView(redirectView);

		} else {
			if (!"3".equals(user.getUserDivision().trim())) {
				RedirectView redirectView = new RedirectView(request.getContextPath() + "/surport/download");
				redirectView.setExposeModelAttributes(false);
				return new ModelAndView(redirectView);
			}

			String bbsrole = "3".equals(user.getUserDivision().trim()) ? "ADM" : "USR";

			ModelAndView view = new ModelAndView("/surport/download_new");
			view.getModelMap().addAttribute("bbsrole", bbsrole);
			return view;
		}
	}

	/**
	 * 다운로드 게시글 수정 페이지
	 * @param request
	 * @param response
	 * @param pdsId
	 * @param session
	 * @param principal
	 * @return
	 */
	@RequestMapping(value={"/surport/downloadModify/{pdsId}"}, method = RequestMethod.GET)
	public ModelAndView getDownloadModifyPage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer pdsId, HttpSession session, Principal principal) {
		
		User user = (User)session.getAttribute("USER_INFO");

		if (user == null) {
			RedirectView redirectView = new RedirectView(request.getContextPath() + "/login");
			redirectView.setExposeModelAttributes(false);
			return new ModelAndView(redirectView);

		} else {
			if (!"3".equals(user.getUserDivision().trim())) {
				RedirectView redirectView = new RedirectView(request.getContextPath() + "/surport/download");
				redirectView.setExposeModelAttributes(false);
				return new ModelAndView(redirectView);
			}

			String bbsrole = "3".equals(user.getUserDivision().trim()) ? "ADM" : "USR";
			
			BbsPds bbsPds = boardService.getPdsById(pdsId);
			ModelAndView view = new ModelAndView("/surport/download_mod");
			view.getModelMap().addAttribute("postItem", bbsPds);
			view.getModelMap().addAttribute("bbsrole", bbsrole);
			return view;
		}
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



	@RequestMapping(value={"/rule/copyrightPolicy"}, method = RequestMethod.GET)
	public ModelAndView getCopyrightPolicy(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {
		ModelAndView view = new ModelAndView("/rule/copyrightPolicy");
		return view;
	}

	@RequestMapping(value={"/rule/publicInformation"}, method = RequestMethod.GET)
	public ModelAndView getPublicInformationPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {
		ModelAndView view = new ModelAndView("/rule/publicInformation");
		return view;
	}

	@RequestMapping(value={"/rule/emailNonCollection"}, method = RequestMethod.GET)
	public ModelAndView getEmailNonCollectionPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {
		ModelAndView view = new ModelAndView("/rule/emailNonCollection");
		return view;
	}

	@RequestMapping(value={"/rule/openSourceLicense"}, method = RequestMethod.GET)
	public ModelAndView getOpenSourceLicensePage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {
		ModelAndView view = new ModelAndView("/rule/openSourceLicense");
		return view;
	}

	@RequestMapping(value={"/modifyUserInfo"}, method = RequestMethod.GET)
	public ModelAndView modifyUserInfo(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {
		ModelAndView view = new ModelAndView("/info/modifyUserInfo");
		return view;
	}


	@RequestMapping(value="/ngiiemapProxy", method=RequestMethod.GET)
	public void requestGETImage(
			@RequestParam(value="ngiiproxy", required=false) String ngiiProxy,
			@RequestParam(value="URL", required=false) String ngiiImgUrl,
			HttpServletRequest request, HttpServletResponse response
			) {

		try {
			OutputStreamConnect.requestGETImage(new URL(ngiiProxy + "&URL=" + ngiiImgUrl), request, response);
		} catch (MalformedURLException e) {
		}
	}

}