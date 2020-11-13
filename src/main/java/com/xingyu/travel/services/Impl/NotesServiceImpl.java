package com.xingyu.travel.services.Impl;

import com.xingyu.travel.mapper.NotesMapper;
import com.xingyu.travel.mapper.NotestypeMapper;
import com.xingyu.travel.model.Notes;
import com.xingyu.travel.model.Notestype;
import com.xingyu.travel.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.services.Impl
 * @Version: 1.0
 **/
@Service
public class NotesServiceImpl implements NotesService {
	@Autowired
	private NotestypeMapper notestypeMapper;
	@Autowired
	private NotesMapper notesMapper;

	/**
	 * @Description: 查询所有的游记类型
	 * @Author: Admin
	 * @Param: []
	 * @Return java.util.List<com.xingyu.travel.model.Notestype>
	 **/
	@Override
	public List<Notestype> findAllNoteType() {
		return notestypeMapper.findAllNoteType();
	}

	/**
	 * @Description: 查询所有的游记
	 * @Author: Admin
	 * @Param: []
	 * @Return java.util.List<com.xingyu.travel.model.Notes>
	 **/
	@Override
	public List<Notes> findAllNotes() {
		return notesMapper.findAllNotes();
	}

	/**
	 * @Description: 更改游记状态（审核游记）
	 * @Author: Admin
	 * @Param: [notes]
	 * @Return int
	 **/
	@Override
	public int updateNotesStatus(Notes notes) {
		return notesMapper.updateByPrimaryKeySelective(notes);
	}

	/**
	 * @Description: 根据游记Id查询游记详情（展示游记具体内容）
	 * @Author: Admin
	 * @Param: [notesId]
	 * @Return com.xingyu.travel.model.Notes
	 **/
	@Override
	public Notes findNotesByNotesId(Integer notesId) {
		return notesMapper.selectByPrimaryKey(notesId);
	}

	/**
	 * @Description: 修改notes的refer状态（推荐游记）
	 * @Author: Admin
	 * @Param: [notes]
	 * @Return void
	 **/
	@Override
	public int findNotesReferByNotesId(Notes notes) {
		return notesMapper.updateByPrimaryKeySelective(notes);
	}

	/**
	 * @Description: 推荐游记
	 * @Author: Admin
	 * @Param: []
	 * @Return java.util.List<com.xingyu.travel.model.Notes>
	 **/
	@Override
	public List<Notes> findNotesRefer() {
		return notesMapper.findNotesRefer();
	}

	/**
	 * @Description: 撤销推荐
	 * @Author: Admin
	 * @Param: [notes]
	 * @Return int
	 **/
	@Override
	public int updateStatus(Notes notes) {
		return notesMapper.updateByPrimaryKeySelective(notes);
	}

	//根据id查询notes
	@Override
	public Notes findNotesById(Integer notesId) {
		return notesMapper.selectByPrimaryKey(notesId);
	}

	//查询notesType
	@Override
	public Notestype findNotesTypeById(Integer notestypeId) {
		return notestypeMapper.findNotesTypeById(notestypeId);
	}

	//查询游记集合根据notesID
	@Override
	public List<Notes> findNotesByTypeId(Integer notestypeId) {
		return notesMapper.findNotesByTypeId(notestypeId);
	}

	// /查询游记类别集合
	@Override
	public List<Notestype> findAllNotesType() {
		return notestypeMapper.findAllNoteType();
	}

	@Override
	public List<Notes> findNotesByName(String notesName) {
		return notesMapper.findNotesByName(notesName);
	}

	@Override
	public int updateNotes(Notes notesById) {
		return notesMapper.updateByPrimaryKeySelective(notesById);
	}

	@Override
	public int insertNotes(Notes notes) {
		return notesMapper.insertSelective(notes);
	}

	@Override
	public List<Notes> findNotesByUsersId(Integer userId) {
		return notesMapper.findNotesByUsersId(userId);
	}
}
