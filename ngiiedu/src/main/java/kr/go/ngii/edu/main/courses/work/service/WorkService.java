package kr.go.ngii.edu.main.courses.work.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.courses.work.mapper.WorkMapper;
import kr.go.ngii.edu.main.courses.work.model.Work;

@Service
public class WorkService extends BaseService {

	@Autowired
	private WorkMapper workMapper;

	
	/**
	 * 수업과정을 업로드 한다.
	 * 파라미터로 받은 moduleWorkIds의 값으로 모듈과정 정보를 저장하고
	 * <br/>
	 * 	저장된 모든 Work 모델의 리스트를 리턴한다.
	 * 
	 * @param courseId
	 * @param moduleWorkIds
	 * @return
	 * @throws Exception
	 */
	public List<Work> create(int courseId, List<Integer> moduleWorkIds) throws Exception {
		
		List<Work> createResult = new ArrayList<Work>();

		Work param = null;
		
		for (int i=0; i<moduleWorkIds.size(); i++) {
			param = new Work();
			param.setCourseId(courseId);
			param.setModuleWorkId(moduleWorkIds.get(i));
			param.setWorkSeq(i);
			//param.setWorkTeamDivision(workTeamDivision);
			param.setCreateDate(new Date());
			param.setModifyDate(new Date());

			workMapper.create(param);
			
			createResult.add(param);
		}
		
		return createResult;
	}

}
