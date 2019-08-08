package kr.go.ngii.edu.main.modules.course.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 모듈과정 모델 클래스.
 * 
 */
@XmlRootElement(name = "moduleWork")
public class ModuleWork implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 고유키. */
	private Integer idx;

	/** 모듈. */
	private Integer moduleId;

	/** 과정명. */
	private String moduleWorkName;

	/** 과정메타데이터. */
	private String moduleWorkMetadata;

	/** 과정순서 */
	private Integer moduleWorkSeq;

	/** 수업방식 */
	private String moduleWorkCourseType;

	/** 생성일 */
	private Date createDate;

	/**수정일 */
	private Date modifyDate;


	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleWorkName() {
		return moduleWorkName;
	}

	public void setModuleWorkName(String moduleWorkName) {
		this.moduleWorkName = moduleWorkName;
	}

	public String getModuleWorkMetadata() {
		return moduleWorkMetadata;
	}

	public void setModuleWorkMetadata(String moduleWorkMetadata) {
		this.moduleWorkMetadata = moduleWorkMetadata;
	}

	public Integer getModuleWorkSeq() {
		return moduleWorkSeq;
	}

	public void setModuleWorkSeq(Integer moduleWorkSeq) {
		this.moduleWorkSeq = moduleWorkSeq;
	}

	public String getModuleWorkCourseType() {
		return moduleWorkCourseType;
	}

	public void setModuleWorkCourseType(String moduleWorkCourseType) {
		this.moduleWorkCourseType = moduleWorkCourseType;
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
