package com.xingyu.travel.controller.front;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xingyu.travel.model.Comment;
import com.xingyu.travel.model.Strategy;
import com.xingyu.travel.model.Strategytype;
import com.xingyu.travel.model.Users;
import com.xingyu.travel.services.CollectService;
import com.xingyu.travel.services.CommentService;
import com.xingyu.travel.services.StrategyService;
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
@RequestMapping("/front/strategy")
public class FrontStrategyController {
	@Autowired
	private StrategyService strategyService;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	@Autowired
	private CollectService collectService;

	/**
	 * @Description: 攻略详细信息
	 * @Author: Admin
	 * @Param: [straId, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/straInfo")
	public String straInfo(Integer straId, HttpServletRequest request) {
		Users users = (Users) redisTemplate.boundValueOps(request.getRemoteAddr()).get();
		Strategy strategy = strategyService.findStraById(straId);
		List<Comment> straComment = commentService.findStraComment(straId);
		ArrayList<Users> usersCom = new ArrayList<>();
		for (Comment comment : straComment) {
			Users commUsers = userService.findById(comment.getUsersId());
			usersCom.add(commUsers);
		}
		if (null != users) {
			boolean collectByUIdAndStraId = collectService.findCollectByUIdAndStraId(users.getUserId(), straId);
			request.setAttribute("collectByUIdAndStraId", collectByUIdAndStraId);
		}
		Strategytype strategytype = strategyService.findStraTypeById(strategy.getStratypeId());
		request.setAttribute("strategy", strategy);
		request.setAttribute("straComment", straComment);
		request.setAttribute("usersCom", usersCom);
		request.setAttribute("strategytype", strategytype);
		return "/front/strategy/straInfo";
	}

	/**
	 * @Description: 查询所有攻略信息（分页展示）
	 * @Author: Admin
	 * @Param: [pageNo, pageSize, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("strategyList")
	public String findAllStrategy(Integer pageNo, Integer pageSize, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 8 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Strategy> strategyList = strategyService.findAllStrategy();
		List<Strategytype> strategytypeList = strategyService.findAllStrategyType();
		PageInfo<Strategy> pageInfo = new PageInfo(strategyList);
		request.setAttribute("page", pageInfo);
		request.setAttribute("strategyList", strategyList);
		request.setAttribute("strategytypeList", strategytypeList);
		return "/front/strategy/strategyList";
	}

	/**
	 * @Description: 根据攻略类型查询景点
	 * @Author: Admin
	 * @Param: [scenictypeId, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/straType")
	public String straType(Integer pageNo, Integer pageSize, Integer stratypeId, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 6 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		//查询集合
		List<Strategy> straByTypeList = strategyService.findStraByTypeId(stratypeId);
		PageInfo<Strategy> pageInfo = new PageInfo(straByTypeList);
		//查询类别
		Strategytype stratype = strategyService.findStraTypeById(stratypeId);
		request.setAttribute("page", pageInfo);
		request.setAttribute("straByTypeList", straByTypeList);
		//查询类别
		request.setAttribute("stratype", stratype);
		return "/front/strategy/straType";
	}

	@RequestMapping("/seachByName")
	public String seachByName(Integer pageNo, Integer pageSize, String straTitle, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 6 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		//根据名字模糊查询 攻略
		List<Strategy> straList = strategyService.findStraByName(straTitle);
		//攻略推荐
		List<Strategy> straRefer = strategyService.findStraRefer();
		PageInfo pageInfo = new PageInfo(straList);
		request.setAttribute("page", pageInfo);
		request.setAttribute("straList", straList);
		request.setAttribute("straRefer", straRefer);
		return "/front/strategy/seachStraByName";
	}

	/**
	 * @Description: 跳转straDail页面
	 * @Author: Admin
	 * @Param: [scenery, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toStraDail")
	public String toStraDail(Strategy strategy, HttpServletRequest request) {
		Strategy straById = strategyService.findStraById(strategy.getStraId());
		Strategytype straTypeById = strategyService.findStraTypeById(straById.getStratypeId());
		straById.setStratypeName(straTypeById.getStratypeName());
		request.setAttribute("straById", straById);
		return "/front/strategy/straDail";
	}
}
