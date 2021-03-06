package kr.go.ngii.edu.main.courses.work.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 과정결과물 모델 클래스.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@XmlRootElement(name = "workOutput")
public class WorkOutput implements Serializable {
	
	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 고유키. */
	private Integer idx;

	/** 수업과정 아이디*/
	private int courseWorkSubId;

	/** 팀아이디. */
	private int outputTeamId;

	/** 사용자아이디. */
	private int outputUserid;
	
	private String outputUserAlias;
	
	private String outputUserName;

	/** 결과물타입. */
	private String outputDivision;

	/** 피노지오결과물아이디. */
	private String pinogioOutputId;

	private String outputType;
	
	private Object pngoData;
	
	private String outputName;
	
	private boolean isShared;
	
	private boolean isDone;
	
	private String thumbNailPath;
	
	public String getOutputName() {
//		String returnVal = "";
//		try {
//			returnVal = ((LinkedHashMap<String, String>) pngoData).get("title");
//		} catch (Exception e) {
//			return "";
//		}
//		return returnVal;
		return outputName;
	}

	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}

	/**
	 * 생성자.
	 */
	public WorkOutput() {
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
	 * 고유키을 가져옵니다..
	 * 
	 * @return 고유키
	 */
	public Integer getIdx() {
		return this.idx;
	}

	
	public int getCourseWorkSubId() {
		return courseWorkSubId;
	}

	public void setCourseWorkSubId(int courseWorkSubId) {
		this.courseWorkSubId = courseWorkSubId;
	}

	/**
	 * 팀아이디을 설정합니다..
	 * 
	 * @param outputTeamId
	 *            팀아이디
	 */
	public void setOutputTeamId(int outputTeamId) {
		this.outputTeamId = outputTeamId;
	}

	/**
	 * 팀아이디을 가져옵니다..
	 * 
	 * @return 팀아이디
	 */
	public int getOutputTeamId() {
		return this.outputTeamId;
	}

	/**
	 * 사용자아이디을 설정합니다..
	 * 
	 * @param outputUserid
	 *            사용자아이디
	 */
	public void setOutputUserid(int outputUserid) {
		this.outputUserid = outputUserid;
	}

	/**
	 * 사용자아이디을 가져옵니다..
	 * 
	 * @return 사용자아이디
	 */
	public int getOutputUserid() {
		return this.outputUserid;
	}

	/**
	 * 결과물타입을 설정합니다..
	 * 
	 * @param outputDivision
	 *            결과물타입
	 */
	public void setOutputDivision(String outputDivision) {
		this.outputDivision = outputDivision;
	}

	/**
	 * 결과물타입을 가져옵니다..
	 * 
	 * @return 결과물타입
	 */
	public String getOutputDivision() {
		return this.outputDivision;
	}

	/**
	 * 피노지오결과물아이디을 설정합니다..
	 * 
	 * @param pinogioOutputId
	 *            피노지오결과물아이디
	 */
	public void setPinogioOutputId(String pinogioOutputId) {
		this.pinogioOutputId = pinogioOutputId;
	}

	/**
	 * 피노지오결과물아이디을 가져옵니다..
	 * 
	 * @return 피노지오결과물아이디
	 */
	public String getPinogioOutputId() {
		return this.pinogioOutputId;
	}
	
	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}
	
	public Object getPngoData() {
		return pngoData;
	}

	public void setPngoData(Object pngoData) {
		this.pngoData = pngoData;
	}
	
	public boolean getIsShared() {
		return isShared;
	}

	public void setIsShared(boolean isShared) {
		this.isShared = isShared;
	}

	public boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	public String getThumbNailPath() {
		return thumbNailPath;
	}

	public void setThumbNailPath(String thumbNailPath) {
		this.thumbNailPath = thumbNailPath;
	}

	public void setShared(boolean isShared) {
		this.isShared = isShared;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	

	public String getOutputUserAlias() {
		return outputUserAlias;
	}

	public void setOutputUserAlias(String outputUserAlias) {
		this.outputUserAlias = outputUserAlias;
	}

	public String getOutputUserName() {
		return outputUserName;
	}

	public void setOutputUserName(String outputUserName) {
		this.outputUserName = outputUserName;
	}

	@Override
	public String toString() {
		return "WorkOutput [idx=" + idx + ", courseWorkSubId=" + courseWorkSubId + ", outputTeamId=" + outputTeamId
				+ ", outputUserid=" + outputUserid + ", outputDivision=" + outputDivision + ", pinogioOutputId="
				+ pinogioOutputId + ", outputType=" + outputType + ", pngoData=" + pngoData + ", outputName="
				+ outputName + ", isShared=" + isShared + ", isDone=" + isDone + "]";
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
		WorkOutput other = (WorkOutput) obj;
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