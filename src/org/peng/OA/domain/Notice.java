package org.peng.OA.domain;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {
	private Integer id;				//id
	private String title;			//标题
	private String content;			//内容
	private Date createDate;		//发布日期
	private User user;				//发布者
	
	public Notice() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setContent(String cpmtent) {
		this.content = cpmtent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
