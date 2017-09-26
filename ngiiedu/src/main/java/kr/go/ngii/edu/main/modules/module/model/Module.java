package kr.go.ngii.edu.main.modules.module.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 모듈 모델 클래스.
 * 
 * @author mg
 */
@XmlRootElement(name = "module")
public class Module implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 고유키. */
	private Integer idx;

	/** 모듈명. */
	private String moduleName;

	/** 메타데이터. */
	private String moduleMetadata;

	/** 생성일. */
	private Date createDate;

	/** 수정일. */
	private Date modifyDate;


	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleMetadata() {
		return moduleMetadata;
	}

	public void setModuleMetadata(String moduleMetadata) {
		this.moduleMetadata = moduleMetadata;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}
