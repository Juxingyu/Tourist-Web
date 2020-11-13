package com.xingyu.travel.controller.front;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xingyu.travel.model.News;
import com.xingyu.travel.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/front/news")
public class FrontNewsController {
	@Autowired
	private NewsService newsService;

	/**
	 * @Description: 查询所有的公告信息
	 * @Author: Admin
	 * @Param: [request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/newsList")
	public String newsList(Integer pageNo, Integer pageSize, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 6 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<News> newsList = newsService.findAll();
		PageInfo<News> pageInfo = new PageInfo(newsList);
		request.setAttribute("page", pageInfo);
		request.setAttribute("newsList", newsList);
		return "/front/news/newsList";
	}

	/**
	 * @Description: 根据newsid查询公告：显示公告详情
	 * @Author: Admin
	 * @Param: [newsId, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/newsInfo")
	public String newsInfo(Integer newsId, HttpServletRequest request) {
		News news = newsService.findNewsById(newsId);
		request.setAttribute("news", news);
		return "/front/news/newsInfo";
	}
}
