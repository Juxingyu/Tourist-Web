package com.xingyu.travel.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Scenictype implements Serializable {
	private Integer scenictypeId;

	private String scenictpyeName;

	private Integer status;

	private Date createDate;

	private Date updateDate;
	private static final long serialVersionUID = 1L;
}