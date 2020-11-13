package com.xingyu.travel.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Strategy implements Serializable {
	//攻略ID
	private Integer straId;
	//攻略类别ID
	private Integer stratypeId;
	//攻略标题
	private String straTitle;
	//攻略内容
	private String straContext;
	//攻略图片
	private String straPic;
	//状态
	private Integer status;
	//创建时间
	private Date createDate;
	//更新时间
	private Date updateDate;
	//点赞数
	private Integer praseCount;
	//推荐
	private Integer refer;

	private String stratypeName;

	private static final long serialVersionUID = 1L;


}