package com.xingyu.travel.mapper;

import com.xingyu.travel.base.BaseMapper;
import com.xingyu.travel.model.Comment;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {

	//查询所有的评论(景点、游记、攻略)
	List<Comment> findAllComment(Comment comment);

	//查询所有游记相关评论
	List<Comment> findAllNotesComment();

	//查询所有攻略相关评论
	List<Comment> findAllStraComment();

	//查询所有景点相关评论
	List<Comment> findAllSceComment();

	List<Comment> findNotesComment(Integer notesId);

	List<Comment> findSceComment(Integer sceId);

	List<Comment> findStraComment(Integer straId);

	List<Comment> findFoodComment(Integer id);
}