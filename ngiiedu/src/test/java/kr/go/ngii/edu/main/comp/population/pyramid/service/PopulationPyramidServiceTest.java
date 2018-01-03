package kr.go.ngii.edu.main.comp.population.pyramid.service;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.go.ngii.edu.BaseTest;

public class PopulationPyramidServiceTest extends BaseTest {

	@Autowired
	private PopulationPyramidService service;


	@Test
	public void testList() {
		
		List<Map<String, Object>> list = service.list(11);
		System.out.println(list.size());
	}

}
