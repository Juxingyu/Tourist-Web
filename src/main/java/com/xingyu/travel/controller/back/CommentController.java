package com.xingyu.travel.controller.back;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xingyu.travel.model.Comment;
import com.xingyu.travel.model.Users;
import com.xingyu.travel.services.CommentService;
import com.xingyu.travel.services.SceneryService;
import com.xingyu.travel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: 评论相关
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.controller
 * @Version: 1.0
 **/
// @RestController
@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	@Autowired
	private SceneryService sceneryService;

	// 查询所有的评论(景点 、 游记 、 攻略)
	@RequestMapping("/commentList")
	public List<Comment> findAllComment(Comment comment) {
		return commentService.findAllComment(comment);
	}

	@RequestMapping("/notesComment")
	public List<Comment> notesComment() {
		return commentService.findAllNotesComment();
	}

	/**
	 * @Description: 跳转游记评论管理界面
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toNotesCom")
	public String toNotesCom(Comment comm, Integer pageNo, Integer pageSize, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 100 : pageSize;
		comm = comm == null ? new Comment() : comm;
		PageHelper.startPage(pageNo, pageSize);
		List<Comment> notesComment = commentService.findAllNotesComment();
		for (Comment comment : notesComment) {
			Users users = userService.findById(comment.getUsersId());
			comment.setUsers(users);
		}
		PageInfo<Comment> pageInfo = new PageInfo(notesComment);
		request.setAttribute("page", pageInfo);
		request.setAttribute("notesComment", notesComment);
		return "/back/comment/notesComment";
	}

	/**
	 * @Description: 修改评论的状态
	 * @Author: Admin
	 * @Param: [comment]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/updateStatus")
	public String updateStatus(Comment comment) {
		commentService.updateStatus(comment);
		return "/back/comment/notesComment";
	}

	/**
	 * @Description: 跳转攻略评论管理界面
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toStraCom")
	public String toStraCom(Comment comm, Integer pageNo, Integer pageSize, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 100 : pageSize;
		comm = comm == null ? new Comment() : comm;
		PageHelper.startPage(pageNo, pageSize);
		List<Comment> straComment = commentService.findAllStraComment();
		for (Comment comment : straComment) {
			Users users = userService.findById(comment.getUsersId());
			comment.setUsers(users);
		}
		PageInfo<Comment> pageInfo = new PageInfo(straComment);
		request.setAttribute("page", pageInfo);
		request.setAttribute("straComment", straComment);
		return "/back/comment/straComment";
	}

	/**
	 * @Description: 跳转景点评论管理界面
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toSceCom")
	public String toSceCom(Comment comm, Integer pageNo, Integer pageSize, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 100 : pageSize;
		comm = comm == null ? new Comment() : comm;
		PageHelper.startPage(pageNo, pageSize);
		List<Comment> sceComment = commentService.findAllSceComment();
		for (Comment comment : sceComment) {
			//评论者
			Users users = userService.findById(comment.getUsersId());
			comment.setUsers(users);
			//评论的景点名称
			String sceNameById = sceneryService.findSceNameById(comment.getSceId());
			comment.setSecName(sceNameById);
		}
		PageInfo<Comment> pageInfo = new PageInfo(sceComment);
		request.setAttribute("page", pageInfo);
		request.setAttribute("sceComment", sceComment);
		return "/back/comment/sceComment";
	}
}
