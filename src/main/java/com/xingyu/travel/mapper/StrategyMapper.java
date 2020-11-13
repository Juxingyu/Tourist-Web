package com.xingyu.travel.mapper;

import com.xingyu.travel.base.BaseMapper;
import com.xingyu.travel.model.Strategy;

import java.util.List;

public interface StrategyMapper extends BaseMapper<Strategy> {
	//查询所有的旅游攻略信息
	List<Strategy> findAllStrategy();

	//推荐攻略
	List<Strategy> findStraRefer();

	//根据typeID查询攻略集合
	List<Strategy> findStraByTypeId(Integer stratypeId);

	List<Strategy> findStraByName(String straTitle);
}