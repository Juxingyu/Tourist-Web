package com.xingyu.travel.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Notes implements Serializable {
	private Integer notesId;

	private Integer notestypeId;

	private String notesTitle;

	private String notesPic;

	private String notesContent;

	private String notesLocation;

	private Integer notesStatus;

	private Date createDate;

	private Date updateDate;

	private Integer userId;

	private Integer praseCount;

	private Integer refer;

	private String petName;

	private String notestypeName;
	private static final long serialVersionUID = 1L;
}