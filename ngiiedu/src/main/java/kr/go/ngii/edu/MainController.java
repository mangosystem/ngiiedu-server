package kr.go.ngii.edu;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import kr.go.ngii.edu.controller.rest.BaseController;

@Controller
public class MainController extends BaseController {
	
	
	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public ModelAndView getLoginPage(Principal principal) {

		ModelAndView view = null;

		if (principal == null) {
			view = new ModelAndView("/login");

		} else {
			RedirectView redirectView = new RedirectView("./");
			redirectView.setExposeModelAttributes(false);
			view = new ModelAndView(redirectView);
		}

		return view;
	}
	
	@RequestMapping(value={"/join"}, method = RequestMethod.GET)
	public ModelAndView getJoinPage(Principal principal) {

		ModelAndView view = null;

		if (principal == null) {
			view = new ModelAndView("/join");

		} else {
			RedirectView redirectView = new RedirectView("./");
			redirectView.setExposeModelAttributes(false);
			view = new ModelAndView(redirectView);
		}

		return view;
	}

	@RequestMapping(value={"*"}, method = RequestMethod.GET)
	public ModelAndView getViewPage(HttpServletRequest request, HttpSession session) {
		ModelAndView view = new ModelAndView("/index");
		return view;
	}
	
	@RequestMapping(value={"/course/**"}, method = RequestMethod.GET)
	public ModelAndView getCoursePage(HttpServletRequest request, HttpSession session) {
		ModelAndView view = new ModelAndView("/index");
		return view;
	}
	
	@RequestMapping(value={"/cm-admin/**"}, method = RequestMethod.GET)
	public ModelAndView getAdminPage(HttpServletRequest request, HttpSession session) {
		ModelAndView view = new ModelAndView("/index");
		return view;
	}

	@RequestMapping(value={"/errorFobridden"}, method = RequestMethod.GET)
	public ModelAndView getErrorFobriddenPage(HttpServletRequest request, HttpSession session) {

		RedirectView redirectView = new RedirectView("/login");
		redirectView.setExposeModelAttributes(false);
		return new ModelAndView(redirectView);
	}

}
