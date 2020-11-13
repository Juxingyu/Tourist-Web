package com.xingyu.travel.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Admin implements Serializable {
	private Integer id;

	private String loginName;

	private String petName;

	private String pass;

	private String email;

	private String pic;

	private Integer rolesId;

	private Integer status;

	private Date update;

	private List<Funs> funsList;

	//游记所有类型
	private List<Notestype> notesTypeList;

	//景点所有类型
	private List<Scenictype> scenicTypeList;

	//攻略所有类型
	private List<Strategytype> strategyTypeList;

	private static final long serialVersionUID = 1L;

}