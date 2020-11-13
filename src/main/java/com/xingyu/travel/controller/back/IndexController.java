package com.xingyu.travel.controller.back;

import com.xingyu.travel.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:首页
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.controller
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/back/index")
public class IndexController {
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private NewsService newsService;

	@RequestMapping("/index")
	public String index() {
		return "/back/index/index_1";
	}

	@RequestMapping("/toWelcome")
	public String toWelcome(HttpServletRequest request) {
		//格式化日期格式
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String now = simpleDateFormat.format(new Date());
		request.setAttribute("now", now);
		return "/back/index/welcome";
	}

}
