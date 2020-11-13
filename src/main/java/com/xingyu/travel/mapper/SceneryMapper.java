package com.xingyu.travel.mapper;

import com.xingyu.travel.base.BaseMapper;
import com.xingyu.travel.model.Scenery;

import java.util.List;

public interface SceneryMapper extends BaseMapper<Scenery> {
	//查询所有的景点信息
	List<Scenery> findAllScenery();

	//景点推荐
	List<Scenery> findSceRefer();

	//根据景点类型ID查询景点
	List<Scenery> findSceByTypeId(Integer scenictypeId);

	//查询景点信息
	List<Scenery> findAllSce();

	List<Scenery> findSceByName(String secName);

	//评论的景点名称
	String findSceNameById(Integer sceId);
}