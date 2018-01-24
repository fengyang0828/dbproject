package com.nyu.dbproject.entity;

import java.io.Serializable;
import java.util.*;
/**
 * @author  Yang Feng
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;


	// User ID
	private Long uid;

	// User username
	protected String username;

	// User password
	protected String upassword;

	// User name
	protected String uname;

	// User email
	protected String uemail;

	// User city
	protected String ucity;
//
//	// Rate for the Track
//	protected Set<Rate> rate;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getUcity() {
		return ucity;
	}

	public void setUcity(String ucity) {
		this.ucity = ucity;
	}
//
//	public Set<Rate> getRate() {
//		return rate;
//	}
//
//	public void setRate(Set<Rate> rate) {
//		this.rate = rate;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
