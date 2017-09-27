package kr.go.ngii.edu.main.common;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Transactional
public class BaseService {

	protected final Logger LOGGER = LoggerFactory.getLogger( this.getClass() );
	

	/**
	 * HttpSession 객체를 가져온다.
	 * 
	 * @return {@link HttpSession}
	 */
	protected HttpSession getHttpSession() {
		try {
			ServletRequestAttributes attribute = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attribute.getRequest().getSession();
			return session;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 세션 고유값을 리턴한다.
	 * 
	 * @return String sessionId
	 */
	protected String getSessionId() {
		return this.getHttpSession().getId();
	}

}
