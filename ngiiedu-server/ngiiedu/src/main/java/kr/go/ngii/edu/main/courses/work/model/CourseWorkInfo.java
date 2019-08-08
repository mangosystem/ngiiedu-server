package kr.go.ngii.edu.main.courses.work.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 수업과정 모델 클래스.
 * 
 * @author mg
 */
@XmlRootElement(name = "courseWorkInfo")
public class CourseWorkInfo implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 고유키. */
	private Integer idx;

	/** 수업 아이디 */
	private Integer courseId;

	/** 모듈과정 아이디 */
	private Integer moduleWorkId;

	/** 과정순서 */
	private Integer workSeq;

	/** 과정팀유형 */
	private String workTeamDivision;

	/** 과정명 */
	private String moduleWorkName;
	
	/** 과정수업방식  */
	private String moduleWorkCourseType;
	
	/** 과정메타데이터  */
	private String moduleWorkMetadata;
	
	/** 과정 활성화상태 */
	private boolean status;
	
	private List<CourseWorkSubInfo> courseWorkSubInfos;

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getModuleWorkId() {
		return moduleWorkId;
	}

	public void setModuleWorkId(Integer moduleWorkId) {
		this.moduleWorkId = moduleWorkId;
	}
	
	public String getWorkTeamDivision() {
		return workTeamDivision;
	}

	public void setWorkTeamDivision(String workTeamDivision) {
		this.workTeamDivision = workTeamDivision;
	}
	
	public boolean getStatus() {
		return status;
	}

	public void setWorkSeq(Integer workSeq) {
		this.workSeq = workSeq;
	}
	
	public String getModuleWorkName() {
		return moduleWorkName;
	}

	public void setModuleWorkName(String moduleWorkName) {
		this.moduleWorkName = moduleWorkName;
	}

	public String getModuleWorkCourseType() {
		return moduleWorkCourseType;
	}

	public void setModuleWorkCourseType(String moduleWorkCourseType) {
		this.moduleWorkCourseType = moduleWorkCourseType;
	}

	public String getModuleWorkMetadata() {
		return moduleWorkMetadata;
	}

	public void setModuleWorkMetadata(String moduleWorkMetadata) {
		this.moduleWorkMetadata = moduleWorkMetadata;
	}
	
	public Integer getWorkSeq() {
		return workSeq;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<CourseWorkSubInfo> getCourseWorkSubInfos() {
		return courseWorkSubInfos;
	}

	public void setCourseWorkSubInfos(List<CourseWorkSubInfo> courseWorkSubInfos) {
		this.courseWorkSubInfos = courseWorkSubInfos;
	}
	
	

}
