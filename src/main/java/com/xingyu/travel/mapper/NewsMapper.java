package com.xingyu.travel.mapper;

import com.xingyu.travel.base.BaseMapper;
import com.xingyu.travel.model.News;

import java.util.List;

public interface NewsMapper extends BaseMapper<News> {
	//	查询所有新闻资讯
	List<News> findAll();

	//根据newsid查询公告：显示公告详情
	News findNewsById(Integer newsId);
}