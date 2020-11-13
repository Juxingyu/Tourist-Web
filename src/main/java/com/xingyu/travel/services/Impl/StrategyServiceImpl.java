package com.xingyu.travel.services.Impl;

import com.xingyu.travel.mapper.StrategyMapper;
import com.xingyu.travel.mapper.StrategytypeMapper;
import com.xingyu.travel.model.Strategy;
import com.xingyu.travel.model.Strategytype;
import com.xingyu.travel.services.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.services.Impl
 * @Version: 1.0
 **/
@Service
public class StrategyServiceImpl implements StrategyService {
	@Autowired
	private StrategytypeMapper strategytypeMapper;
	@Autowired
	private StrategyMapper strategyMapper;

	/**
	 * @Description: 查询所有攻略类别
	 * @Author: Admin
	 * @Param: []
	 * @Return java.util.List<com.xingyu.travel.model.Strategytype>
	 **/
	@Override
	public List<Strategytype> findAllStrategyType() {
		return strategytypeMapper.findAllStrategyType();
	}

	/**
	 * @Description: 查询所有攻略信息
	 * @Author: Admin
	 * @Param: []
	 * @Return java.util.List<com.xingyu.travel.model.Strategy>
	 **/
	@Override
	public List<Strategy> findAllStrategy() {
		return strategyMapper.findAllStrategy();
	}

	/**
	 * @Description: 更改攻略状态（修改、删除）
	 * @Author: Admin
	 * @Param: [strategy]
	 * @Return int
	 **/
	@Override
	public int updateStrategyStatus(Strategy strategy) {
		return strategyMapper.updateByPrimaryKeySelective(strategy);
	}


	/**
	 * @Description: 添加旅游攻略
	 * @Author: Admin
	 * @Param: [strategy]
	 * @Return int
	 **/
	@Override
	public int insertStrategy(Strategy strategy) {
		return strategyMapper.insertSelective(strategy);
	}

	/**
	 * @Description: 更改攻略类型状态
	 * @Author: Admin
	 * @Param: [strategy]
	 * @Return int
	 **/
	@Override
	public int updateStraTypeStatus(Strategytype strategytype) {
		return strategytypeMapper.updateByPrimaryKeySelective(strategytype);
	}

	/**
	 * @Description: 添加攻略类型
	 * @Author: Admin
	 * @Param: [strategytype]
	 * @Return int
	 **/
	@Override
	public int insertStraType(Strategytype strategytype) {
		return strategytypeMapper.insertSelective(strategytype);
	}

	/**
	 * @Description: 推荐攻略
	 * @Author: Admin
	 * @Param: []
	 * @Return java.util.List<com.xingyu.travel.model.Strategy>
	 **/
	@Override
	public List<Strategy> findStraRefer() {
		return strategyMapper.findStraRefer();
	}

	/**
	 * @Description: 撤销推荐
	 * @Author: Admin
	 * @Param: [strategy]
	 * @Return int
	 **/
	@Override
	public int updateStatus(Strategy strategy) {
		return strategyMapper.updateByPrimaryKeySelective(strategy);
	}

	// 根据id查询攻略
	@Override
	public Strategy findStraById(Integer straId) {
		return strategyMapper.selectByPrimaryKey(straId);
	}

	//根据typeID查询攻略集合
	@Override
	public List<Strategy> findStraByTypeId(Integer stratypeId) {
		return strategyMapper.findStraByTypeId(stratypeId);
	}

	//查询攻略类别
	@Override
	public Strategytype findStraTypeById(Integer stratypeId) {
		return strategytypeMapper.findStraTypeById(stratypeId);
	}

	//模糊查询攻略
	@Override
	public List<Strategy> findStraByName(String straTitle) {
		return strategyMapper.findStraByName(straTitle);
	}

	@Override
	public int updateStrategy(Strategy straById) {
		return strategyMapper.updateByPrimaryKeySelective(straById);
	}

	@Override
	public int findStrategyReferByStraId(Strategy strategy) {
		return strategyMapper.updateByPrimaryKeySelective(strategy);
	}
}
