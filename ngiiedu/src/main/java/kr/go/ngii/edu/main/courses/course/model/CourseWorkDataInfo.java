package kr.go.ngii.edu.main.courses.course.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import kr.go.ngii.edu.common.enums.EnumCourseDataDiv;

/**
 * 수업 과정데이터 모델 클래스.
 * 
 * 
 */
@XmlRootElement(name = "courseWorkDataInfo")	
public class CourseWorkDataInfo implements Serializable{
//public class CourseWorkData extends ModuleWorkData implements Serializable{
	
	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** 고유키. */
	private Integer idx;
	
	/** 코스 */
	private Integer courseId;

	/** 모듈. */
	private Integer moduleId;

	/** 자료명. */
	private String moduleWorkDataName;

	/** 자료위치경로. */
	private String moduleWorkDataPath;

	/** 수업데이터구분. */
	private String moduleWorkDataDivision;

	/** 수업차수정보. */
	private String moduleWorkSeq;
	
	/** 자료 활성화 정보. */
	private String status;

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
	 * 모듈을 가져옵니다..
	 * 
	 * @return 모듈
	 */
	public Integer getCourseId() {
		return this.courseId;
	}
	
	
	/**
	 * 코스를 설정합니다..
	 * 
	 * @param courseId
	 *            코스
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	/**
	 * 모듈을 설정합니다..
	 * 
	 * @param moduleId
	 *            모듈
	 */
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * 모듈을 가져옵니다..
	 * 
	 * @return 모듈
	 */
	public Integer getModuleId() {
		return this.moduleId;
	}

	/**
	 * 자료명을 설정합니다..
	 * 
	 * @param moduleWorkOutputName
	 *            자료명
	 */
	public void setModuleWorkDataName(String moduleWorkDataName) {
		this.moduleWorkDataName = moduleWorkDataName;
	}

	/**
	 * 자료명을 가져옵니다..
	 * 
	 * @return 자료명
	 */
	public String getModuleWorkDataName() {
		return this.moduleWorkDataName;
	}

	/**
	 * 자료위치경로을 설정합니다..
	 * 
	 * @param moduleWorkOutputPath
	 *            자료위치경로
	 */
	public void setModuleWorkDataPath(String moduleWorkDataPath) {
		this.moduleWorkDataPath = moduleWorkDataPath;
	}

	/**
	 * 자료위치경로을 가져옵니다..
	 * 
	 * @return 자료위치경로
	 */
	public String getModuleWorkDataPath() {
		return this.moduleWorkDataPath;
	}

	/**
	 * 수업데이터구분을 설정합니다..
	 * 
	 * @param moduleWorkOutputDivision
	 *            수업데이터구분
	 */
	public void setModuleWorkDataDivision(String moduleWorkDataDivision) {
		this.moduleWorkDataDivision = moduleWorkDataDivision;
	}

	/**
	 * 수업데이터구분을 가져옵니다..
	 * 
	 * @return 수업데이터구분
	 */
	public String getModuleWorkDataDivision() {
		return this.moduleWorkDataDivision;
	}

	/**
	 * 수업차수정보을 설정합니다..
	 * 
	 * @param moduleWorkSeq
	 *            수업차수정보
	 */
	public void setModuleWorkSeq(String moduleWorkSeq) {
		this.moduleWorkSeq = moduleWorkSeq;
	}

	/**
	 * 수업차수정보을 가져옵니다..
	 * 
	 * @return 수업차수정보
	 */
	public String getModuleWorkSeq() {
		return this.moduleWorkSeq;
	}
	
	
	/**
	 * 수업차수정보을 설정합니다..
	 * 
	 * @param moduleWorkSeq
	 *            수업차수정보
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 수업차수정보을 가져옵니다..
	 * 
	 * @return 수업차수정보
	 */
	public String getStatus() {
		return this.status;
	}
	
	public String getModuleWorkDataDivisionText() {
		return EnumCourseDataDiv.findText(this.moduleWorkDataDivision);
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
		CourseWorkDataInfo other = (CourseWorkDataInfo) obj;
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
