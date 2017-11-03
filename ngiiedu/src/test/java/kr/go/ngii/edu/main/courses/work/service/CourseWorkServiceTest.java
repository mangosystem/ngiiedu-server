package kr.go.ngii.edu.main.courses.work.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.main.courses.work.model.CourseWork;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSubOutputWithModuleWorkSub;

public class CourseWorkServiceTest extends BaseTest {

	@Autowired
	private CourseWorkService service;
	
	@Test
	public void testlist() {
//		List<CourseWorkInfo> cwinfo = service.listCourseWorkInfo(20);
	}
	
	@Test
	public void modify() {
//		CourseWork cw = service.modify(36, false);
//		System.out.println(cw.getIdx());
	}
	
	 private RestTemplate getRestTempalte() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(1000 * 60 * 5);  // 5분
        factory.setConnectTimeout(5000);
        RestTemplate restTemplate = new RestTemplate(factory);
        return restTemplate;
	}

	 @Test
	public void test() {
		 RestTemplate restTemplate = getRestTempalte();
//		 restTemplate.
	}
}
