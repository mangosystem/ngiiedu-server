package kr.go.ngii.edu.main.courses.work.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.courses.work.mapper.WorkOutputMapper;
import kr.go.ngii.edu.main.courses.work.model.CourseWork;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkInfo;
import kr.go.ngii.edu.main.courses.work.model.WorkOutput;

@Service
public class WorkOutputService extends BaseService {
	
	@Autowired
	private WorkOutputMapper workOutputMapper;
	
	
	/**
	 * 결과물을 등록 한다.
	 * 
	 * @param courseId
	 * @param moduleWorkIds
	 * @return
	 * @throws Exception
	 */
	public List<WorkOutput> create(int workId) throws Exception {

		List<WorkOutput> createResult = new ArrayList<WorkOutput >();

		CourseWork param = null;

//		for (int i=0; i<moduleWorkIds.size(); i++) {
//			param = new CourseWork();
//			param.setCourseId(courseId);
//			param.setModuleWorkId(moduleWorkIds.get(i));
//			param.setWorkSeq(i);
//			//param.setWorkTeamDivision(workTeamDivision);
//			param.setCreateDate(new Date());
//			param.setModifyDate(new Date());
//
//			workOutputMapper.create(param);
//
//			createResult.add(param);
//		}

		return createResult;
	}
	
	public List<WorkOutput> list(WorkOutput workOutput) {
		return workOutputMapper.list(workOutput);
	}
	
	public WorkOutput get(WorkOutput workOutput) {
		return workOutputMapper.get(workOutput);
	}
	
	public WorkOutput modify(WorkOutput workOutput) {
		WorkOutput params = new WorkOutput();
//		params.setIdx(idx);
//		params.setStatus(status);
//		params.setModifyDate(new Date());
//		workOutputMapper.modify(params);
		return params;
	}
	
	public void delete(WorkOutput workOutput) {
		workOutputMapper.delete(1);
	}
}
