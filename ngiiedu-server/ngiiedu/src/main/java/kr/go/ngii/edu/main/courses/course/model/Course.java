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
@XmlRootElement(name = "course")
public class Course implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 고유키. */
	private Integer idx;

	/** 모듈. */
	private Integer moduleId;

	/** 수업명. */
	private String courseName;

	/** 수업메타데이터 */
	private String courseMetadata;

	/** 생성자. */
	private Integer courseCreateId;

	/** 생성일 */
	private Date createDate;

	/** 수정일. */
	private Date modifyDate;
	
	private boolean status;
	
	List<CourseWork> work;
	
	private String projectId;

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

	public Integer getCourseCreateId() {
		return courseCreateId;
	}

	public void setCourseCreateId(Integer courseCreateId) {
		this.courseCreateId = courseCreateId;
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

	public List<CourseWork> getWork() {
		return work;
	}

	public void setWork(List<CourseWork> work) {
		this.work = work;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getProjectId() {
		return projectId;
	}
	
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	
	
}
