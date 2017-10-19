package kr.go.ngii.edu.main.courses.course.model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import kr.go.ngii.edu.main.courses.work.model.CourseWork;

/**
 * 수업 모델 클래스.
 * 
 * @author mg
 */
@XmlRootElement(name = "courseDetail")
public class CourseDetail implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 고유키. */
	private Integer idx;

	/** 모듈. */
	private Integer moduleId;

	/** 모듈명. */
	private String moduleName;
	
	/** 모듈메타데이터. */
	private String moduleMetadata;

	/** 수업명. */
	private String courseName;

	/** 수업메타데이터 */
	private String courseMetadata;

	/** 생성자. */
	private String courseCreateId;
	
	/** 생성자  userId. */
	private String courseCreateUserId;
	
	/** 사용자 구분 */
	private String userDivision;

	/** 생성일 */
	private Date createDate;

	/** 수정일. */
	private Date modifyDate;
	
	/** 사용자ID. */
	private String userId;
	
	/** 사용자 상태. */
	private String status;

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

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleMetadata() {
		return moduleMetadata;
	}

	public void setModuleMetadata(String moduleMetadata) {
		this.moduleMetadata = moduleMetadata;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseMetadata() {
		return courseMetadata;
	}

	public void setCourseMetadata(String courseMetadata) {
		this.courseMetadata = courseMetadata;
	}

	public String getCourseCreateId() {
		return courseCreateId;
	}

	public void setCourseCreateId(String courseCreateId) {
		this.courseCreateId = courseCreateId;
	}

	public String getCourseCreateUserId() {
		return courseCreateUserId;
	}

	public void setCourseCreateUserId(String courseCreateUserId) {
		this.courseCreateUserId = courseCreateUserId;
	}
	
	public String getUserDivision() {
		return userDivision;
	}

	public void setUserDivision(String userDivision) {
		this.userDivision = userDivision;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
