package com.xingyu.travel.services.Impl;

import com.xingyu.travel.mapper.NewsMapper;
import com.xingyu.travel.model.News;
import com.xingyu.travel.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsMapper newsMapper;

	/**
	 * @Description: 查询所有的旅游资讯信息
	 * @Author: Admin
	 * @Param: []
	 * @Return java.util.List<com.xingyu.travel.model.News>
	 **/
	@Override
	public List<News> findAll() {
		return newsMapper.findAll();
	}

	/**
	 * @Description: 更改状态（恢复与删除操作）
	 * @Author: Admin
	 * @Param: [news]
	 * @Return int
	 **/
	@Override
	public int updateStatus(News news) {
		return newsMapper.updateByPrimaryKeySelective(news);
	}

	/**
	 * @Description: 添加新闻资讯
	 * @Author: Admin
	 * @Param: [news]
	 * @Return int
	 **/
	@Override
	public int insertNews(News news) {
		return newsMapper.insertSelective(news);
	}

	// 根据newsid查询公告：显示公告详情
	@Override
	public News findNewsById(Integer newsId) {
		// return newsMapper.findNewsById(newsId);
		return newsMapper.selectByPrimaryKey(newsId);
	}

	@Override
	public int updateNews(News news) {
		return newsMapper.updateByPrimaryKeySelective(news);
	}
}
