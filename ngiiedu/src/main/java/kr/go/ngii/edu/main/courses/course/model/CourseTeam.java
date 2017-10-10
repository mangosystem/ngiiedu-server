package kr.go.ngii.edu.main.courses.course.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 팀 모델 클래스.
 * 
 * @author mg
 */
@XmlRootElement(name = "courseTeam")
public class CourseTeam implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 고유키. */
	private Integer idx;

	/** 수업 아아디 */
	private Integer courseId;

	/** 팀명 */
	private String teamName;

	/** 순서 */
	private Integer teamSeq;

	/** 생성일 */
	private Date createDate;

	/** 수정일 */
	private Date modifyDate;

	/** 멤버목록 */
	private List<CourseTeamMember> TeamMember;


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

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getTeamSeq() {
		return teamSeq;
	}

	public void setTeamSeq(Integer teamSeq) {
		this.teamSeq = teamSeq;
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

	public List<CourseTeamMember> getTeamMember() {
		return TeamMember;
	}

	public void setTeamMember(List<CourseTeamMember> teamMember) {
		TeamMember = teamMember;
	}

}
