package com.xingyu.travel.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Notestype implements Serializable {
    private Integer notestypeId;

    private String notestypeName;

    private static final long serialVersionUID = 1L;
}