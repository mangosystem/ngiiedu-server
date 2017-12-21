package kr.go.ngii.edu.main.board.model;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bbsNotice")
public class BbsNotice implements Serializable{
	
	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private Integer idx;
	private String title;
	private String content;
	private Date createDate;
	private Date modifyDate;

	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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



}
