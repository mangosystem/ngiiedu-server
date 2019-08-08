package kr.go.ngii.edu.main.modules.comp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.modules.comp.model.Comp;


@Mapper
public interface CompMapper {
	
	public Comp get(Comp comp);
	
	public List<Comp> list(Comp comp);
	
	public int create(Comp comp);
	
	public void modify(Comp comp);
	
	public void delete(int idx);

}

