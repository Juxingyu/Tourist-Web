package com.xingyu.travel.mapper;

import com.xingyu.travel.base.BaseMapper;
import com.xingyu.travel.model.Strategytype;

import java.util.List;

public interface StrategytypeMapper extends BaseMapper<Strategytype> {
	//查询所有攻略的类型
	List<Strategytype> findAllStrategyType();

	// 查询攻略类别
	Strategytype findStraTypeById(Integer stratypeId);
}