package com.xingyu.travel.controller.front;

import com.xingyu.travel.model.Comment;
import com.xingyu.travel.model.Users;
import com.xingyu.travel.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.controller.front
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/front/comment")
public class FrontCommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	RedisTemplate redisTemplate;

	@RequestMapping("/insertSceComment")
	public void insertComment(Comment comment, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Users users = (Users) redisTemplate.boundValueOps(request.getRemoteAddr()).get();
		comment.setUsersId(users.getUserId());
		int i = commentService.insertSceComment(comment);
		if (i > 0) {
			response.getWriter().write("0");
		} else {
			response.getWriter().write("1");
		}
	}

	@RequestMapping("/insertStraComment")
	public void insertStraComment(Comment comment, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Users users = (Users) redisTemplate.boundValueOps(request.getRemoteAddr()).get();
		comment.setUsersId(users.getUserId());
		int i = commentService.insertSceComment(comment);
		if (i > 0) {
			response.getWriter().write("0");
		} else {
			response.getWriter().write("1");
		}
	}

	@RequestMapping("/insertNotesComment")
	public void insertNotesComment(Comment comment, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Users users = (Users) redisTemplate.boundValueOps(request.getRemoteAddr()).get();
		comment.setUsersId(users.getUserId());
		int i = commentService.insertSceComment(comment);
		if (i > 0) {
			response.getWriter().write("0");
		} else {
			response.getWriter().write("1");
		}
	}
}
