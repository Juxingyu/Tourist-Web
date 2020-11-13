package com.xingyu.travel.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Comment implements Serializable {
	private Integer commentId;

	private Integer notesId;

	private Integer straId;

	private Integer sceId;

	private Integer foodId;

	private Integer usersId;

	private String commentContent;

	private Integer status;

	private Integer praseCount;

	private Date createDate;

	private Date updateDate;

	private Users users;

	private String secName;

	private String straTitle;

	private String notesTitle;

	private static final long serialVersionUID = 1L;
}