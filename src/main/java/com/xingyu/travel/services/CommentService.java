package com.xingyu.travel.services;

import com.xingyu.travel.model.Comment;

import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @Version: 1.0
 **/
public interface CommentService {

	//查询所有的评论(景点、游记、攻略)
	List<Comment> findAllComment(Comment comment);

	//查询所有游记相关评论
	List<Comment> findAllNotesComment();

	//更改状态
	int updateStatus(Comment comment);

	//查询所有攻略相关评论
	List<Comment> findAllStraComment();

	//查询所有景点相关评论
	List<Comment> findAllSceComment();

	//根据游记id查询评论集合
	List<Comment> findNotesComment(Integer notesId);

	//根据景点id查询所有的评论
	List<Comment> findSceComment(Integer sceId);

	int insertSceComment(Comment comment);

	//根据攻略id查询所有的评论
	List<Comment> findStraComment(Integer straId);

	//美食评论
	List<Comment> findFoodComment(Integer id);
}
