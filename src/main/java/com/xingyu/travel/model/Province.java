package com.xingyu.travel.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Province implements Serializable {
    private Integer id;

    private Integer parentId;

    private String areaName;

    private Integer areaType;

    private static final long serialVersionUID = 1L;
}