package kr.go.ngii.edu.main.courses.course.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.common.UIDUtil;
import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.courses.course.mapper.CourseAuthkeyMapper;
import kr.go.ngii.edu.main.courses.course.model.CourseAuthkey;

@Service
public class CourseAuthkeyService extends BaseService {

	@Autowired
	private CourseAuthkeyMapper mapper;


	/**
	 * 수업아이디로 수업인증코드를 조회한다.
	 * 
	 * @param courseId
	 * @return
	 */
	public String get(int courseId) {
		return mapper.getAuthkey(courseId);
	}


	/**
	 * 인증코드로 수업아이디를 조회한다.
	 * 
	 * @param authkey
	 * @return
	 */
	public Integer get(String authkey) {
		return mapper.getCourseId(authkey);
	}

	/**
	 * 수업인증코드 생성
	 * 
	 * @param courseId
	 * @throws Exception 
	 */
	public String create(int courseId) {

		try {
			
			mapper.updateDeactive(courseId);
			
			CourseAuthkey param = new CourseAuthkey();
			param.setCourseId(courseId);
			param.setAuthkey(getAuthkey());
			param.setCreateDate(new Date());
			param.setStatus(true);

			mapper.create(param);

			if (param.getIdx() != null) {
				return param.getAuthkey();
			}

		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 중복되지 않는 수업코드를 자동으로 생성하여 리턴한다.
	 * 
	 * @return
	 * @throws Exception
	 */
	private String getAuthkey() throws Exception {

		String key = UIDUtil.randomKey36(6);

		if (mapper.exists(key)) {
			return getAuthkey();
		} else {
			return key;
		}
	}

}
