package com.xingyu.travel.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentReply implements Serializable {
	//回复的id
	private Integer id;
	//评论表的id
	private Integer commentId;
	//用户id
	private Integer usersId;
	//被回复人的id（先评论的人）
	private Integer replyusersId;

	private String content;

	private Integer status;

	private Integer praseCount;

	private Date createDate;


	private static final long serialVersionUID = 1L;
}