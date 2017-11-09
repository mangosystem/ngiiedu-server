package kr.go.ngii.edu.main.courses.work.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 모듈과정 모델 클래스.
 * 
 */
@XmlRootElement(name = "courseWorkSubOutputWithModuleWorkSub")
public class CourseWorkSubOutputWithModuleWorkSub implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 고유키. */
	private Integer idx;

	/** 모듈 과정 id (외래키). */
	private Integer moduleWorkId;
	
	private Integer moduleWorkSubId;

	/** 모듈 하위 과정 이름. */
	private String moduleWorkSubName;

	/** 모듈 하위 과정 순서 */
	private Integer moduleWorkSubSeq;

	/** 생성일 */
	private Date createDate;

	/**수정일 */
	private Date modifyDate;
	
	/** 자료 종류. */
	private String outputType;
	
	private List<WorkOutput> workOutputList;
	
	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public Integer getModuleWorkId() {
		return moduleWorkId;
	}

	public void setModuleWorkId(Integer moduleWorkId) {
		this.moduleWorkId = moduleWorkId;
	}

	public String getModuleWorkSubName() {
		return moduleWorkSubName;
	}

	public void setModuleWorkSubName(String moduleWorkSubName) {
		this.moduleWorkSubName = moduleWorkSubName;
	}

	public Integer getModuleWorkSubSeq() {
		return moduleWorkSubSeq;
	}

	public void setModuleWorkSubSeq(Integer moduleWorkSubSeq) {
		this.moduleWorkSubSeq = moduleWorkSubSeq;
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
	
	
	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}
	
	
	public Integer getModuleWorkSubId() {
		return moduleWorkSubId;
	}

	public void setModuleWorkSubId(Integer moduleWorkSubId) {
		this.moduleWorkSubId = moduleWorkSubId;
	}
	
	
	public List<WorkOutput> getWorkOutputList() {
		return workOutputList;
	}

	public void setWorkOutputList(List<WorkOutput> workOutputList) {
		this.workOutputList = workOutputList;
	}
	
	public List<WorkOutput> getCourseWorkSubOutputInfoList() {
		return workOutputList;
	}
}
