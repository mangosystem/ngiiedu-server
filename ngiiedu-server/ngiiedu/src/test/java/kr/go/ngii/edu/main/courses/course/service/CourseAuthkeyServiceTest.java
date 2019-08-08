package kr.go.ngii.edu.main.courses.course.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.common.enums.EnumJoinStatus;

public class CourseAuthkeyServiceTest extends BaseTest {
	
	@Autowired
	private CourseAuthkeyService service;
	
	@Test
	public void testGet() {
		
//		System.out.println(service.get(17225568));
		
//		System.out.println(EnumJoinStatus.WAITING.code());
//		System.out.println(EnumJoinStatus.ACTIVE.code());
		
		System.out.println( EnumJoinStatus.findKey("CJS04") );
		System.out.println( EnumJoinStatus.findCode("BLOCK") );
		
	}

	@Test
	public void testCreate() throws Exception {
		
//		for (int i=0; i<5; i++) {
//			System.out.println(
//				service.create( Integer.parseInt(UIDUtil.randomKeyNum(2)) )
//			);
//		}
		service.create( 1 );
	}

}
