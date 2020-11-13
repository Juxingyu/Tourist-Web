package com.xingyu.travel.mapper;

import com.xingyu.travel.base.BaseMapper;
import com.xingyu.travel.model.Notestype;

import java.util.List;

public interface NotestypeMapper extends BaseMapper<Notestype> {
	//查询所有游记类型
	List<Notestype> findAllNoteType();

	////查询notesType
	Notestype findNotesTypeById(Integer notestypeId);
}