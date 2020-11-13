package com.xingyu.travel.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Roles implements Serializable {
	private Integer rolesId;

	private String rolesName;

	private static final long serialVersionUID = 1L;
}