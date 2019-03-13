package kr.go.ngii.edu.main.board.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BbsPdsFile")
public class BbsPdsFile implements Serializable {
	
	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private int idx;
	private int pdsId;
	private String filePath;
	private String fileName;
	private Date createDate;
	private Date modifyDate;
	private String ext;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getPdsId() {
		return pdsId;
	}
	public void setPdsId(int pdsId) {
		this.pdsId = pdsId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
}
