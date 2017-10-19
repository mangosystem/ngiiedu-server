package kr.go.ngii.edu.main.courses.work.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 수업과정 모델 클래스.
 * 
 * @author mg
 */
@XmlRootElement(name = "courseWork")
public class CourseWork implements Serializable {

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

	/** 과정 활성화상태 */
	private boolean status;

	/** 과정팀유형 */
	private String workTeamDivision;

	/** 생성일 */
	private Date createDate;

	/** 수정일. */
	private Date modifyDate;


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

	public Integer getWorkSeq() {
		return workSeq;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean getStatus() {
		return status;
	}

	public void setWorkSeq(Integer workSeq) {
		this.workSeq = workSeq;
	}

	public String getWorkTeamDivision() {
		return workTeamDivision;
	}

	public void setWorkTeamDivision(String workTeamDivision) {
		this.workTeamDivision = workTeamDivision;
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
