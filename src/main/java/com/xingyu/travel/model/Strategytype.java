package com.xingyu.travel.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Strategytype implements Serializable {
	//攻略类别ID
	private Integer stratypeId;
	//攻略类别ID
	private String stratypeName;
	//状态
	private Integer status;
	//创建时间
	private Date createDate;
	//修改时间
	private Date updateDate;

	private static final long serialVersionUID = 1L;

}