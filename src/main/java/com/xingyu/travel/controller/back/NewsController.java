package com.xingyu.travel.controller.back;

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
 * @PackageName: com.xingyu.travel.controller
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/news")
public class NewsController {
	@Autowired
	private NewsService newsService;

	/**
	 * @Description: 查询所有的旅游资讯信息(分页展示)
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/newsList")
	public String findAllNews(Integer pageNo, Integer pageSize, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 100 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<News> newsList = newsService.findAll();
		PageInfo<News> pageInfo = new PageInfo(newsList);
		request.setAttribute("page", pageInfo);
		request.setAttribute("newsList", newsList);
		return "/back/news/newsList";
	}

	/**
	 * @Description: 更改状态（恢复与删除操作）
	 * @Author: Admin
	 * @Param: [news]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/updateStatus")
	public String updateStatus(News news) {
		newsService.updateStatus(news);
		return "/back/news/newsList";
	}

	/**
	 * @Description: 跳转添加新闻资讯界面
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toInsertNews")
	public String toInsertNews() {
		return "/back/news/insertNews";
	}

	/**
	 * @Description: 添加新闻资讯
	 * @Author: Admin
	 * @Param: [news]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/insertNews")
	public String insertNews(News news) {
		newsService.insertNews(news);
		return "/back/news/insertNews";
	}

	/**
	 * @Description: 查询信息 进行修改
	 * @Author: Admin
	 * @Param: [news, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/newsInfo")
	public String newsInfo(News news, HttpServletRequest request) {
		News newsById = newsService.findNewsById(news.getNewsId());
		request.setAttribute("newsById", newsById);
		return "/back/news/newsInfo";
	}

	@RequestMapping("/updateNews")
	public String updateNews(News news, HttpServletRequest request) {
		int i = newsService.updateNews(news);
		request.setAttribute("i", i);
		return "/back/news/newsInfo";
	}
}

