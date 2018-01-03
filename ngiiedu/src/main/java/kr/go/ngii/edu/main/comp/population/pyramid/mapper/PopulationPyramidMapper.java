package kr.go.ngii.edu.main.comp.population.pyramid.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PopulationPyramidMapper {

	public List<Map<String, Object>> listBySidoCd(@Param("sidoCd") int sidoCd);

}
