package com.xingyu.travel.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Scenery implements Serializable {
	private Integer sceId;

	private Integer scenictypeId;

	private String secName;

	private String secPic;

	private String secSite;

	private String secLevel;

	private String openTime;

	private Integer status;

	private Date createDate;

	private Date updateDate;

	private String secDesc;

	private Integer praseCount;

	private Integer refer;

	private String scenictpyeName;

	private static final long serialVersionUID = 1L;

}