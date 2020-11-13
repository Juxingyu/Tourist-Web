package com.xingyu.travel.controller.front;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xingyu.travel.model.Comment;
import com.xingyu.travel.model.Scenery;
import com.xingyu.travel.model.Scenictype;
import com.xingyu.travel.model.Users;
import com.xingyu.travel.services.CollectService;
import com.xingyu.travel.services.CommentService;
import com.xingyu.travel.services.SceneryService;
import com.xingyu.travel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 景点相关
 * @Author: Admin
 * @Param:
 * @Return
 **/
@Controller
@RequestMapping("/front/scenery")
public class FrontSceneryController {
	@Autowired
	private SceneryService sceneryService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private CollectService collectService;

	/**
	 * @Description: 查询景点详细信息以及评论
	 * @Author: Admin
	 * @Param: [sceId, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/sceInfo")
	public String sceInfo(Integer sceId, HttpServletRequest request) {
		Users users = (Users) redisTemplate.boundValueOps(request.getRemoteAddr()).get();
		Scenery scenery = sceneryService.findSceById(sceId);
		List<Comment> sceComment = commentService.findSceComment(sceId);
		ArrayList<Users> usersCom = new ArrayList<>();
		for (Comment comment : sceComment) {
			//返回用户信息
			Users commUsers = userService.findById(comment.getUsersId());
			//评论id查询恢复评论
			// List<CommentReply> replyByIdList = comReplyService.findReplyById(comment.getCommentId());
			usersCom.add(commUsers);
		}
		//用户登录
		if (null != users) {
			//根据用户id、sceid查询收藏表
			boolean collectByUIdAndSceId = collectService.findCollectByUIdAndSceId(users.getUserId(), sceId);

			request.setAttribute("collectByUIdAndSceId", collectByUIdAndSceId);

		}
		Scenictype scenictype = sceneryService.findSceTypeById(scenery.getScenictypeId());
		request.setAttribute("scenery", scenery);
		request.setAttribute("sceComment", sceComment);
		//comment的评论人信息
		request.setAttribute("usersCom", usersCom);
		//comReply的品论人信息
		// request.setAttribute("usersReply", usersReply);
		request.setAttribute("scenictype", scenictype);
		return "/front/scenery/sceInfo";
	}

	/**
	 * @Description: 根据景点类型查询景点
	 * @Author: Admin
	 * @Param: [scenictypeId, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/sceType")
	public String sceType(Integer pageNo, Integer pageSize, Integer scenictypeId, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 8 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Scenery> sceByTypeList = sceneryService.findSceByTypeId(scenictypeId);
		PageInfo<Scenery> pageInfo = new PageInfo(sceByTypeList);
		Scenictype scenictype = sceneryService.findSceTypeById(scenictypeId);
		request.setAttribute("page", pageInfo);
		request.setAttribute("sceByTypeList", sceByTypeList);
		request.setAttribute("scenictype", scenictype);
		return "/front/scenery/sceByType";
	}

	/**
	 * @Description: 查询所有攻略信息（分页展示）
	 * @Author: Admin
	 * @Param: [pageNo, pageSize, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("sceList")
	public String findAllStrategy(Integer pageNo, Integer pageSize, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 8 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Scenery> sceList = sceneryService.findAllSce();
		List<Scenictype> scetypeList = sceneryService.findAllSceType();
		PageInfo<Scenery> pageInfo = new PageInfo(sceList);
		request.setAttribute("page", pageInfo);
		request.setAttribute("sceList", sceList);
		request.setAttribute("scetypeList", scetypeList);
		return "/front/scenery/sceList";
	}

	/**
	 * @Description: 根据名字模糊搜索（分页展示）
	 * @Author: Admin
	 * @Param: [notesName]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/seachByName")
	public String seachByName(Integer pageNo, Integer pageSize, String secName, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 6 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		//根据名字模糊查询 景点
		List<Scenery> sceList = sceneryService.findSceByName(secName);
		List<Scenery> sceReferList = sceneryService.findSceRefer();
		PageInfo pageInfo = new PageInfo(sceList);
		request.setAttribute("page", pageInfo);
		request.setAttribute("sceList", sceList);
		//景点相关推荐
		request.setAttribute("sceReferList", sceReferList);
		return "/front/scenery/seachSceByName";
	}


	/**
	 * @Description: 跳转sceDail页面
	 * @Author: Admin
	 * @Param: [scenery, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toSceDail")
	public String toSceDail(Scenery scenery, HttpServletRequest request) {
		Scenery sceById = sceneryService.findSceById(scenery.getSceId());
		request.setAttribute("sceById", sceById);
		return "/front/scenery/sceDail";
	}
}
