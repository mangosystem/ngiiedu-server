package kr.go.ngii.edu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.ngii.edu.controller.rest.BaseController;
import kr.go.ngii.edu.controller.rest.ResponseData;
import kr.go.ngii.edu.main.comp.population.pyramid.service.PopulationPyramidService;

@Controller
@RequestMapping("/api/v1/utils")
public class UtilsController extends BaseController {

	@Autowired
	private PopulationPyramidService populationPyramidService;


	@RequestMapping(value="/population", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> list(
			@RequestParam(value="sido_code", required=true) Integer sidoCd, 
			HttpSession session) throws Exception {

		List<Map<String, Object>> list = populationPyramidService.list(sidoCd);

		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}


}
