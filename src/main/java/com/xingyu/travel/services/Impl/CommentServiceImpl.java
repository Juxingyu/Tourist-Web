package com.xingyu.travel.services.Impl;

import com.xingyu.travel.mapper.CommentMapper;
import com.xingyu.travel.model.Comment;
import com.xingyu.travel.services.CommentService;
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
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentMapper;

	//查询所有的评论(景点、游记、攻略)
	@Override
	public List<Comment> findAllComment(Comment comment) {
		return commentMapper.findAllComment(comment);
	}

	//查询所有游记相关评论
	@Override
	public List<Comment> findAllNotesComment() {
		return commentMapper.findAllNotesComment();
	}

	//更改状态
	@Override
	public int updateStatus(Comment comment) {
		return commentMapper.updateByPrimaryKeySelective(comment);
	}

	//查询所有攻略相关评论
	@Override
	public List<Comment> findAllStraComment() {
		return commentMapper.findAllStraComment();
	}

	//查询所有景点相关评论
	@Override
	public List<Comment> findAllSceComment() {
		return commentMapper.findAllSceComment();
	}

	@Override
	public List<Comment> findNotesComment(Integer notesId) {
		return commentMapper.findNotesComment(notesId);
	}

	@Override
	public List<Comment> findSceComment(Integer sceId) {
		return commentMapper.findSceComment(sceId);
	}

	@Override
	public int insertSceComment(Comment comment) {
		return commentMapper.insertSelective(comment);
	}

	@Override
	public List<Comment> findStraComment(Integer straId) {
		return commentMapper.findStraComment(straId);
	}

	@Override
	public List<Comment> findFoodComment(Integer id) {
		return commentMapper.findFoodComment(id);
	}
}
