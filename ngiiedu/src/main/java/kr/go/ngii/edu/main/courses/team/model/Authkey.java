package kr.go.ngii.edu.main.courses.team.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 팀인증키 모델 클래스.
 * 
 */
@XmlRootElement(name = "authkey")
public class Authkey implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 고유키. */
	private Integer idx;

	/** 인증키. */
	private String authkey;


	/**
	 * @return the idx
	 */
	public Integer getIdx() {
		return idx;
	}

	/**
	 * @param idx the idx to set
	 */
	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	/**
	 * @return the authkey
	 */
	public String getAuthkey() {
		return authkey;
	}

	/**
	 * @param authkey the authkey to set
	 */
	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}

}
