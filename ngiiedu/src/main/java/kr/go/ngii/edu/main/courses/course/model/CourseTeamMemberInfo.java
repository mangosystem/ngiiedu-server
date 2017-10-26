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

	public Integer getCourse_id() {
		return courseId;
	}

	public void setCourse_id(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getUser_id() {
		return userId;
	}

	public void setUser_id(Integer userId) {
		this.userId = userId;
	}

	public String getJoin_status() {
		return joinStatus;
	}

	public void setJoin_status(String join_status) {
		this.joinStatus = join_status;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUser_email() {
		return userEmail;
	}

	public void setUser_email(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUser_name() {
		return userName;
	}

	public void setUser_name(String user_name) {
		this.userName = userName;
	}

	public Integer getTeam_id() {
		return teamId;
	}

	public void setTeam_id(Integer teamId) {
		this.teamId = teamId;
	}

	public String getTeam_name() {
		return teamName;
	}

	public void setTeam_name(String teamName) {
		this.teamName = teamName;
	}

	public Integer getTeam_seq() {
		return teamSeq;
	}

	public void setTeam_seq(Integer teamSeq) {
		this.teamSeq = teamSeq;
	}

}
