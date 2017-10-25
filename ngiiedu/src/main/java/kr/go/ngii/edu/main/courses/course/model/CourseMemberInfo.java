package kr.go.ngii.edu.main.courses.course.model;
import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 팀원 모델 클래스.
 * 
 * @author mg
 */
@XmlRootElement(name = "courseMemberInfo")
public class CourseMemberInfo implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 고유키. */
	private Integer idx;

	/** 수업 아아디 */
	private Integer courseId;

	/** 참여자 아이디 */
	private Integer userId;

	/** 참여 상태 */
	private String joinStatus;

	/** 사용자 아이디(키값아님) */
	private String userid;

	/** 사용자 이메일. */
	private String userEmail;

	/** 사용자 이름. */
	private String userName;

	/** 사요자 상태. */
	private boolean userState;

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
		return joinStatus;
	}

	public void setJoinStatus(String joinStatus) {
		this.joinStatus = joinStatus;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isUserState() {
		return userState;
	}

	public void setUserState(boolean userState) {
		this.userState = userState;
	}
}
