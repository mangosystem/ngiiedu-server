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
	private Integer course_id;

	/** 유저 테이블 참조키 */
	private Integer user_id;

	/** 참여자상태 */
	private String join_status;
	
	/** 사용자 ID */
	private String userid;
	
	/** 사용자 e mail */
	private String user_email;
	
	/** 사용자 이름 */
	private String user_name;
	
	/** 팀 테이블 참조키 */
	private Integer team_id;
	
	/** 팀명 */
	private String team_name;
	
	/** 팀 순서 */
	private Integer team_seq;

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public Integer getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getJoin_status() {
		return join_status;
	}

	public void setJoin_status(String join_status) {
		this.join_status = join_status;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getTeam_id() {
		return team_id;
	}

	public void setTeam_id(Integer team_id) {
		this.team_id = team_id;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public Integer getTeam_seq() {
		return team_seq;
	}

	public void setTeam_seq(Integer team_seq) {
		this.team_seq = team_seq;
	}

}
