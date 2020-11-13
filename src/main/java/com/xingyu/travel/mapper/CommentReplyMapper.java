package com.xingyu.travel.mapper;

import com.xingyu.travel.base.BaseMapper;
import com.xingyu.travel.model.CommentReply;

import java.util.List;

public interface CommentReplyMapper extends BaseMapper<CommentReply> {

	List<CommentReply> findReplyById(Integer commentId);
}