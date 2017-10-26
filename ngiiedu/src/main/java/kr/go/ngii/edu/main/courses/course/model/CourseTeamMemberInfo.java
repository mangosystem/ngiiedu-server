package kr.go.ngii.edu.main.courses.course.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 팀 멤버 클래스.
 * 
 * @author mg
 */
@XmlRootElement(name = "courseTeamMemberInfo")
public class CourseTeamMemberInfo implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 고유키. */
	private Integer idx;

	/** 코스 테이블 참조키 */
	private Integer courseId;

	/** 유저 테이블 참조키 */
	private Integer userId;

	/** 참여자상태 */
	private String joinStatus;
	
	/** 사용자 ID */
	private String userid;
	
	/** 사용자 e mail */
	private String userEmail;
	
	/** 사용자 이름 */
	private String userName;
	
	/** 팀 테이블 참조키 */
	private Integer teamId;
	
	/** 팀명 */
	private String teamName;
	
	/** 팀 순서 */
	private Integer teamSeq;

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

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
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

	

}
