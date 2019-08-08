package kr.go.ngii.edu.main.users.model;

import org.springframework.security.core.GrantedAuthority;

public class UserRole implements GrantedAuthority {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4900817341708755288L;


	private Integer idx;

	private Integer userId;

	private String authority;


	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
