package kr.go.ngii.edu.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.sample.mapper.SampleMapper;
import kr.go.ngii.edu.sample.model.Sample;

@Service
public class SampleService {

	@Autowired
	private SampleMapper sampleMapper;
	
	public List<Sample> getList() {
		return sampleMapper.getUsers();
	}

}
