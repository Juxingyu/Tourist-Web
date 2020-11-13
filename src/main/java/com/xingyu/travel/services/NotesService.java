package com.xingyu.travel.services;

import com.xingyu.travel.model.Notes;
import com.xingyu.travel.model.Notestype;

import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @Version: 1.0
 **/
public interface NotesService {
	//查询所有的游记类型
	List<Notestype> findAllNoteType();

	//查询所有的游记
	List<Notes> findAllNotes();

	//更改游记状态（审核游记）
	int updateNotesStatus(Notes notes);

	//根据游记Id查询游记详情（展示游记具体内容）
	Notes findNotesByNotesId(Integer notesId);

	//修改notes的refer状态（推荐游记）
	int findNotesReferByNotesId(Notes notes);

	//推荐游记
	List<Notes> findNotesRefer();

	//撤销推荐
	int updateStatus(Notes notes);

	//根据id查询notes
	Notes findNotesById(Integer notesId);

	//查询notesType
	Notestype findNotesTypeById(Integer notestypeId);

	//查询游记集合根据notesID
	List<Notes> findNotesByTypeId(Integer notestypeId);

	//查询游记所有类别
	List<Notestype> findAllNotesType();

	//根据名字进行模糊查询
	List<Notes> findNotesByName(String notesName);

	int updateNotes(Notes notesById);

	int insertNotes(Notes notes);

	List<Notes> findNotesByUsersId(Integer userId);
}
