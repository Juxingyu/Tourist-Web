package com.xingyu.travel.services;

import com.xingyu.travel.model.CommentReply;

import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @Version: 1.0
 **/
public interface ComReplyService {
	//回复评论的评论
	List<CommentReply> findReplyById(Integer commentId);
}
