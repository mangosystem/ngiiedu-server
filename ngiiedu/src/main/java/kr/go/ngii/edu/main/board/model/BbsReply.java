package kr.go.ngii.edu.main.board.model;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bbsReply")
public class BbsReply  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idx;
	private Integer qnaId 	= null;
	private String content 	= null;
	private String writer	= null;
	private Date createDate = null;
	private Date modifyDate;

	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	public Integer getQnaId() {
		return qnaId;
	}
	public void setQnaId(Integer qaId) {
		this.qnaId = qaId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
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
	public void setCreateDate(Date create_date) {
		this.createDate = create_date;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
