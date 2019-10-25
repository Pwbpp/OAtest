package org.peng.OA.domain;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
	private Integer id;             //id
	private String username;        //用户名
	private String loginname;       //登录名
	private String password;	    //密码
	private Integer userstatus;		//状态
	private Date createDate;        //建档日期
	
	public User() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getUserstatus() {
		return userstatus;
	}
	public void setUserstatus(Integer userstatus) {
		this.userstatus = userstatus;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
