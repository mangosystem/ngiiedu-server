package kr.go.ngii.edu.main.courses.course.model;
import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 팀원 모델 클래스.
 * 
 * @author mg
 */
@XmlRootElement(name = "courseMember")
public class CourseMember implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 고유키. */
	private Integer idx;

	/** 수업 아아디 */
	private Integer courseId;

	/** 참여자 아이디 */
	private Integer userId;

	/** 참여자상태 */
	private String joinStatus;

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getJoinStatus() {
//		return EnumJoinStatus.findKey(status);
		return joinStatus;
	}

	public void setJoinStatus(String joinStatus) {
		this.joinStatus = joinStatus;
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
