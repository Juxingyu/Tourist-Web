package com.xingyu.travel.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Collect implements Serializable {
	private Integer collectId;

	private Integer userId;

	private Integer notesId;

	private Integer sceId;

	private Integer straId;

	private Integer foodId;

	private Notes notes;

	private Strategy strategy;

	private Scenery scenery;

	private Food food;

	private static final long serialVersionUID = 1L;

}