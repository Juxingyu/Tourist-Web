package com.xingyu.travel.controller.front;

import com.xingyu.travel.model.*;
import com.xingyu.travel.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.controller.front
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/front/index")
public class FrontIndexController {
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private NewsService newsService;
	@Autowired
	private NotesService notesService;
	@Autowired
	private StrategyService strategyService;
	@Autowired
	private SceneryService sceneryService;
	@Autowired
	private FoodService foodService;
	@Autowired
	private UserService userService;

	/**
	 * @Description: 跳转前端首页
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		List<Notes> notesReferList = notesService.findNotesRefer();
		for (Notes notes : notesReferList) {
			Users users = userService.findById(notes.getUserId());
			notes.setPetName(users.getPetName());
		}
		List<Strategy> straReferList = strategyService.findStraRefer();
		List<Scenery> sceReferList = sceneryService.findSceRefer();
		List<Food> foodList = foodService.findAllFood();
		request.setAttribute("foodList", foodList);
		request.setAttribute("notesReferList", notesReferList);
		request.setAttribute("straReferList", straReferList);
		request.setAttribute("sceReferList", sceReferList);
		return "/front/index/index";
	}
}
