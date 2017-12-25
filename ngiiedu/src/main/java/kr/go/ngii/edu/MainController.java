package kr.go.ngii.edu;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import kr.go.ngii.edu.controller.rest.BaseController;
import kr.go.ngii.edu.controller.rest.ResponseData;
import kr.go.ngii.edu.main.courses.course.model.Course;
import kr.go.ngii.edu.main.users.model.User;

@Controller
public class MainController extends BaseController {


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
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/surport/notice");
		return view;
	}
	
	//notice view page
	@RequestMapping(value={"/surport/noticeView/{noticeId}"}, method = RequestMethod.GET)
	public ModelAndView getNoticeViewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal,
			@PathVariable String noticeId) {
		System.out.println(noticeId);
		ModelAndView view = new ModelAndView("/surport/notice_view");
		return view;
	}
	
	@RequestMapping(value={"/surport/faq"}, method = RequestMethod.GET)
	public ModelAndView getFaqPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/surport/faq");
		return view;
	}
	
	//qna
	@RequestMapping(value={"/surport/qna"}, method = RequestMethod.GET)
	public ModelAndView getQnaPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/surport/qna");
		return view;
	}
	
	//qna view page
	@RequestMapping(value={"/surport/qnaView/{qnaId}"}, method = RequestMethod.GET)
	public ModelAndView getQnaViewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal,
			@PathVariable String qnaId) {
		System.out.println(qnaId);
		ModelAndView view = new ModelAndView("/surport/qna_view");
		return view;
	}
	
	//qna new page
	@RequestMapping(value={"/surport/qnaNew"}, method = RequestMethod.GET)
	public ModelAndView getQnaNewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/surport/qna_new");
		return view;
	}
	
	@RequestMapping(value={"/surport/download"}, method = RequestMethod.GET)
	public ModelAndView getDownloadPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal) {

		ModelAndView view = new ModelAndView("/surport/download");
		return view;
	}
	
	@RequestMapping(value={"/surport/downloadView/{download}"}, method = RequestMethod.GET)
	public ModelAndView getDownloadViewPage(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Principal principal,
			@PathVariable String download) {
		ModelAndView view = new ModelAndView("/surport/download_view");
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
