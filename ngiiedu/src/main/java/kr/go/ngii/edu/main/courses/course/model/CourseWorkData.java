package kr.go.ngii.edu.main.courses.course.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 수업 과정데이터 모델 클래스.
 * 
 * 
 */
@XmlRootElement(name = "courseWorkData")	
public class CourseWorkData implements Serializable{
//public class CourseWorkData extends ModuleWorkData implements Serializable{
	
	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** 고유키. */
	private Integer idx;
	
	/** 코스 */
	private Integer courseId;
	
	/** 모듈과정자료 ID	 */
	private Integer moduleWorkDataId;
	
	/** 자료 활성화 정보. */
	private boolean status;

	/**
	 * 고유키을 가져옵니다..
	 * 
	 * @return 고유키
	 */
	public Integer getIdx() {
		return idx;
	}

	/**
	 * 고유키을 설정합니다..
	 * 
	 * @param idx
	 *            고유키
	 */
	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	/**
	 * 모듈과정자료 Id 가져옵니다..
	 * 
	 * @return 모듈과정자료 Id
	 */
	public Integer getModuleWorkDataId() {
		return this.moduleWorkDataId;
	}
	
	
	/**
	 * 모듈과정자료 Id를 설정합니다..
	 * 
	 * @param courseId
	 *            모듈과정자료 Id
	 */
	public void setModuleWworkDataId(int moduleWorkDataId) {
		this.moduleWorkDataId = moduleWorkDataId;
	}

	
	/**
	 * 코스 Id 가져옵니다..
	 * 
	 * @return 코스 Id
	 */
	public Integer getCourseId() {
		return this.courseId;
	}
	
	
	/**
	 * 코코스 Id를 설정합니다..
	 * 
	 * @param courseId
	 *            코스Id
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	
	
	/**
	 * 수업자료상태를 설정합니다..
	 * 
	 * @param moduleWorkSeq
	 *            수업자료상태정보
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * 수업자료상태를 가져옵니다..
	 * 
	 * @return 수업자료상태정보
	 */
	public boolean getStatus() {
		return this.status;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idx == null) ? 0 : idx.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CourseWorkData other = (CourseWorkData) obj;
		if (idx == null) {
			if (other.idx != null) {
				return false;
			}
		} else if (!idx.equals(other.idx)) {
			return false;
		}
		return true;
	}
	
	

}
