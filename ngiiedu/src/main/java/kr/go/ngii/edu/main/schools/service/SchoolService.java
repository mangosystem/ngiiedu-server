package kr.go.ngii.edu.main.schools.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.main.modules.module.model.Module;
import kr.go.ngii.edu.main.schools.mapper.SchoolMapper;
import kr.go.ngii.edu.main.schools.model.School;

@Service
public class SchoolService {

	@Autowired
	private SchoolMapper schoolMapper;


/*	public List<School> list() {
		System.out.println("list");
		return schoolMapper.list();
	}*/

	
	public List<School> list(int offset, int limit, String category, String keyword) {
		System.out.println(category);
		return schoolMapper.list(offset, limit, category, keyword);
	}
	
	public School get(School school) {
		return schoolMapper.get(school);
	}
	
	public School get(int idx) {
		School school = new School();
		school.setIdx(idx);
		return schoolMapper.get(school);
	}
	
	public boolean delete(int idx) {
		if (get(idx)!=null) {
			schoolMapper.delete(idx);
			return true;
		} else {
			return false;
		}
	}
	
}

