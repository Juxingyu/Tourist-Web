package com.xingyu.travel.model;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {
	private Integer newsId;

	private String newsTitle;

	private String newsPic;

	private String newsContext;

	private Integer status;

	private Date createDate;

	private Date updateDate;

	private static final long serialVersionUID = 1L;

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle == null ? null : newsTitle.trim();
	}

	public String getNewsPic() {
		return newsPic;
	}

	public void setNewsPic(String newsPic) {
		this.newsPic = newsPic == null ? null : newsPic.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getNewsContext() {
		return newsContext;
	}

	public void setNewsContext(String newsContext) {
		this.newsContext = newsContext == null ? null : newsContext.trim();
	}

}