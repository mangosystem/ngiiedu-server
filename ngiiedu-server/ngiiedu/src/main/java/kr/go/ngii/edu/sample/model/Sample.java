package kr.go.ngii.edu.sample.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sample")
public class Sample {

	private Integer seqIdx 	= null;
	private String userid 	= null;
	private String passwd 	= null;
	private String email 	= null;


	public Integer getSeqIdx() {
		return seqIdx;
	}

	public void setSeqIdx(Integer seqIdx) {
		this.seqIdx = seqIdx;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
