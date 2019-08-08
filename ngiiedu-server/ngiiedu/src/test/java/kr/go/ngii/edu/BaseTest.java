package kr.go.ngii.edu;

import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import junit.framework.TestCase;
import kr.go.ngii.edu.config.AppInitializer;
import kr.go.ngii.edu.config.DatabaseConfig;
import kr.go.ngii.edu.config.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)    
@WebAppConfiguration
@ContextConfiguration(classes= {
	AppInitializer.class, 
	WebConfig.class,
	DatabaseConfig.class
})
public abstract class BaseTest extends TestCase {

//	protected final Logger LOGGER = LoggerFactory.getLogger( this.getClass() );

}
