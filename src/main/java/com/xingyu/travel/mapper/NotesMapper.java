package com.xingyu.travel.mapper;

import com.xingyu.travel.base.BaseMapper;
import com.xingyu.travel.model.Notes;

import java.util.List;

public interface NotesMapper extends BaseMapper<Notes> {
	// 查询所有的游记
	List<Notes> findAllNotes();

	//推荐游记
	List<Notes> findNotesRefer();

	//查询游记集合根据notesID
	List<Notes> findNotesByTypeId(Integer notestypeId);

	List<Notes> findNotesByName(String notesName);

	List<Notes> findNotesByUsersId(Integer userId);
}