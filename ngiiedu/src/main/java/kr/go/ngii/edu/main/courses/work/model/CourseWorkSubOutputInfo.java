package kr.go.ngii.edu.main.courses.work.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "courseWorkSubOutputInfo")
public class CourseWorkSubOutputInfo implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 고유키. */
	private Integer idx;

	/** 수업 과정 아이디*/
	private int courseWorkId;
	
	/** 모듈 하위 과정 아이디*/
	private int moduleWorkSubId;
	
	/** 생성일 */
	private Date createDate;

	/** 수정일. */
	private Date modifyDate;
	
	/** 결과물타입. */
	private String outputDivision;

	/** 피노지오결과물아이디. */
	private String pinogioOutputId;

	/** 팀아이디. */
	private String outputTeamId;
	
	/** 사용자아이디. */
	private String outputUserid;
	
	private String outputName;
	
	private Object pngoData;
	
	private String output_type;
	
	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public int getCourseWorkId() {
		return courseWorkId;
	}

	public void setCourseWorkId(int courseWorkId) {
		this.courseWorkId = courseWorkId;
	}

	public int getModuleWorkSubId() {
		return moduleWorkSubId;
	}

	public void setModuleWorkSubId(int moduleWorkSubId) {
		this.moduleWorkSubId = moduleWorkSubId;
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

	public String getOutputDivision() {
		return outputDivision;
	}

	public void setOutputDivision(String outputDivision) {
		this.outputDivision = outputDivision;
	}

	public String getPinogioOutputId() {
		return pinogioOutputId;
	}

	public void setPinogioOutputId(String pinogioOutputId) {
		this.pinogioOutputId = pinogioOutputId;
	}

	public String getOutputTeamId() {
		return outputTeamId;
	}

	public void setOutputTeamId(String outputTeamId) {
		this.outputTeamId = outputTeamId;
	}

	public String getOutputUserid() {
		return outputUserid;
	}

	public void setOutputUserid(String outputUserid) {
		this.outputUserid = outputUserid;
	}
	
	public String getOutputName() {
//		return outputName;
		String returnVal = "";
		try {
			returnVal = ((Map<String, String>) pngoData).get("title");
		} catch (Exception e) {
			return "";
		}
		return returnVal;	
	}

	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}
	
	public Object getPngoData() {
		return pngoData;
	}

	public void setPngoData(Object object) {
		this.pngoData = object;
	}

	public String getOutput_type() {
		return output_type;
	}

	public void setOutput_type(String output_type) {
		this.output_type = output_type;
	}
}
