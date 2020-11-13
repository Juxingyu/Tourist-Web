package com.xingyu.travel.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Funs implements Serializable {
	private Integer funId;

	private String funName;

	private Integer funPid;

	private String funUrl;

	private Integer seq;

	private String funKey;

	private List<Funs> subList;

	private static final long serialVersionUID = 1L;
}