package com.xingyu.travel.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class History implements Serializable {
    private Integer historyId;

    private Integer userId;

    private Integer straId;

    private Integer sceId;

    private Integer notesId;

    private static final long serialVersionUID = 1L;
}