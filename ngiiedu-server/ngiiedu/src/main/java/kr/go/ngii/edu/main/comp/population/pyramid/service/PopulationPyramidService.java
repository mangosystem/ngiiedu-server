package kr.go.ngii.edu.main.comp.population.pyramid.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.comp.population.pyramid.mapper.PopulationPyramidMapper;

@Service
public class PopulationPyramidService extends BaseService {

	@Autowired
	private PopulationPyramidMapper mapper;


	public List<Map<String, Object>> list(int sidoCd) {
		try {
			return mapper.listBySidoCd(sidoCd);
		} catch (Exception e) {
			return null;
		}
	}

}
