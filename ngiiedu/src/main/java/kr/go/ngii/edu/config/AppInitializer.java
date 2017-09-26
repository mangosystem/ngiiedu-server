package kr.go.ngii.edu.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import kr.go.ngii.edu.config.filter.CORSFilter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WebConfig.class  };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");

		HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
		hiddenHttpMethodFilter.setMethodParam("_method__");

		return new Filter[] { characterEncodingFilter,  hiddenHttpMethodFilter, new CORSFilter() };
	}

}
