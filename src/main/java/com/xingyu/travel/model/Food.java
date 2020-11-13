package com.xingyu.travel.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Food implements Serializable {
	private Integer id;

	private String foodName;

	private BigDecimal price;

	private String pic;

	private String foodDesc;

	private String address;

	private Date updateTime;

	private Integer status;

	private static final long serialVersionUID = 1L;
}