package com.xingyu.travel.services.Impl;

import com.xingyu.travel.mapper.CommentReplyMapper;
import com.xingyu.travel.model.CommentReply;
import com.xingyu.travel.services.ComReplyService;
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
public class ComReplyServiceImpl implements ComReplyService {
	@Autowired
	private CommentReplyMapper commentReplyMapper;

	@Override
	public List<CommentReply> findReplyById(Integer commentId) {
		return commentReplyMapper.findReplyById(commentId);
	}
}
