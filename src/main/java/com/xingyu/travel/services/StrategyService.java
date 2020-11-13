package com.xingyu.travel.services;

import com.xingyu.travel.model.Strategy;
import com.xingyu.travel.model.Strategytype;

import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @Version: 1.0
 **/
public interface StrategyService {
	//查询攻略类别集合
	List<Strategytype> findAllStrategyType();

	//查询所有攻略信息
	List<Strategy> findAllStrategy();

	//更改攻略状态（修改、删除）
	int updateStrategyStatus(Strategy strategy);

	//添加旅游攻略
	int insertStrategy(Strategy strategy);

	//更改攻略类型状态
	int updateStraTypeStatus(Strategytype strategytype);

	//添加攻略类别
	int insertStraType(Strategytype strategytype);

	//查询推荐攻略
	List<Strategy> findStraRefer();

	//撤销推荐
	int updateStatus(Strategy strategy);

	//根据id查询攻略
	Strategy findStraById(Integer straId);

	//根据typeID查询攻略集合
	List<Strategy> findStraByTypeId(Integer stratypeId);

	//查询攻略类别
	Strategytype findStraTypeById(Integer stratypeId);

	List<Strategy> findStraByName(String straTitle);

	int updateStrategy(Strategy straById);

	int findStrategyReferByStraId(Strategy strategy);
}
