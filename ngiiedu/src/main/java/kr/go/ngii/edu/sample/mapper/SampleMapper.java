package kr.go.ngii.edu.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.sample.model.Sample;

@Mapper
public interface SampleMapper {

	List<Sample> getUsers();

}
