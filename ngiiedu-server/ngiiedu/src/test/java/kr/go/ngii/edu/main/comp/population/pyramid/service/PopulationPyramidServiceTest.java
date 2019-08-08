package kr.go.ngii.edu.main.comp.population.pyramid.service;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.go.ngii.edu.BaseTest;

public class PopulationPyramidServiceTest extends BaseTest {

	@Autowired
	private PopulationPyramidService service;


	@Test
	public void testList() {
		
//		List<Map<String, Object>> list = service.list(11);
//		System.out.println(list.size());
		
		
		System.out.println(
			StringEscapeUtils.escapeHtml3("<script>abcdefg</script>")
		);
		
		
		System.out.println(
			StringEscapeUtils.unescapeHtml3(StringEscapeUtils.escapeHtml3("<script>abcdefg</script>"))
		);
		
	}

}
