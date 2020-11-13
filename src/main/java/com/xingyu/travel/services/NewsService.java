package com.xingyu.travel.services;

import com.xingyu.travel.model.News;

import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @Version: 1.0
 **/
public interface NewsService {
	//查询所有的旅游资讯信息
	List<News> findAll();

	//更改状态（恢复与删除操作）
	int updateStatus(News news);

	//添加新闻资讯
	int insertNews(News news);

	//根据newsid查询公告：显示公告详情
	News findNewsById(Integer newsId);

	int updateNews(News news);
}
