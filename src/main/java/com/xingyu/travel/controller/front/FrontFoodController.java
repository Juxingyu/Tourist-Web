package com.xingyu.travel.controller.front;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xingyu.travel.model.Comment;
import com.xingyu.travel.model.Food;
import com.xingyu.travel.model.Users;
import com.xingyu.travel.services.CollectService;
import com.xingyu.travel.services.CommentService;
import com.xingyu.travel.services.FoodService;
import com.xingyu.travel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.controller.front
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/front/food")
public class FrontFoodController {
	@Autowired
	private FoodService foodService;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	@Autowired
	private CollectService collectService;

	/**
	 * @Description: 查询所有美食
	 * @Author: Admin
	 * @Param: [pageNo, pageSize, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/foodList")
	public String foodList(Integer pageNo, Integer pageSize, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 8 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Food> foodList = foodService.findAllFood();
		PageInfo<Food> pageInfo = new PageInfo(foodList);
		request.setAttribute("page", pageInfo);
		request.setAttribute("foodList", foodList);
		request.setAttribute("pageInfo", pageInfo);
		return "/front/food/foodList";
	}

	/**
	 * @Description: 查询美食详细信息以及评论
	 * @Author: Admin
	 * @Param: [sceId, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/foodInfo")
	public String foodInfo(Integer id, HttpServletRequest request) {
		Users users = (Users) redisTemplate.boundValueOps(request.getRemoteAddr()).get();
		Food food = foodService.findFoodById(id);
		List<Comment> foodComment = commentService.findFoodComment(id);
		ArrayList<Users> usersCom = new ArrayList<>();
		for (Comment comment : foodComment) {
			//返回用户信息
			Users commUsers = userService.findById(comment.getUsersId());
			//评论id查询恢复评论
			// List<CommentReply> replyByIdList = comReplyService.findReplyById(comment.getCommentId());
			usersCom.add(commUsers);
		}
		if (null != users) {
			//根据用户id、sceid查询收藏表
			boolean collectByUIdAndFoodId = collectService.findCollectByUIdAndFoodId(users.getUserId(), id);
			request.setAttribute("collectByUIdAndFoodId", collectByUIdAndFoodId);
		}
		request.setAttribute("food", food);
		request.setAttribute("foodComment", foodComment);
		//comment的评论人信息
		request.setAttribute("usersCom", usersCom);
		//comReply的品论人信息
		// request.setAttribute("usersReply", usersReply);
		// request.setAttribute("scenictype", scenictype);
		return "/front/food/foodInfo";
	}

	// @RequestMapping("/seachByName")
	// public String seachByName(Integer pageNo, Integer pageSize, String foodName, HttpServletRequest request) {
	// 	pageNo = pageNo == null ? 1 : pageNo;
	// 	pageSize = pageSize == null ? 6 : pageSize;
	// 	PageHelper.startPage(pageNo, pageSize);
	// 	//根据名字模糊查询 美食
	// 	List<Food> foodByName = foodService.findFoodByName(foodName);
	// 	PageInfo pageInfo = new PageInfo(foodByName);
	// 	request.setAttribute("page", pageInfo);
	// 	request.setAttribute("foodByName", foodByName);
	// 	return "/front/scenery/seachSceByName";
	// }
}
