package kr.go.ngii.edu.main.schools.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.main.schools.mapper.SchoolMapper;
import kr.go.ngii.edu.main.schools.model.School;

@Service
public class SchoolService {

	@Autowired
	private SchoolMapper schoolMapper;


	public List<School> list() {
		System.out.println("list");
		return schoolMapper.list();
	}

	
	public List<School> list(int offset, int limit) {
		return schoolMapper.list(offset, limit);
	}
	
}

