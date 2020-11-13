package com.xingyu.travel.services;

import com.xingyu.travel.model.Scenery;
import com.xingyu.travel.model.Scenictype;

import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @Version: 1.0
 **/
public interface SceneryService {
	//查询所有的景点类型
	List<Scenictype> findAllSceneryType();

	//查询所有景点信息
	List<Scenery> findAllScenery();

	//更改景点状态（删除/恢复景点）
	int updateSceneryStatus(Scenery scenery);

	//添加景点信息
	int insertScenery(Scenery scenery);

	//添加景点类别
	int insertSceType(Scenictype scenictype);

	//修改景点类别（修改、删除景点类别状态）
	int updateSceTypeStatus(Scenictype scenictype);

	//景点推荐
	List<Scenery> findSceRefer();

	//撤销推荐景点
	int updateStatus(Scenery scenery);

	//根据主键查询景点信息
	Scenery findSceById(Integer id);

	//查询景点类别
	Scenictype findSceTypeById(Integer scenictypeId);

	//根据景点类型ID查询景点
	List<Scenery> findSceByTypeId(Integer scenictypeId);

	//查询景点信息
	List<Scenery> findAllSce();

	//查询景点类型
	List<Scenictype> findAllSceType();

	int updateScenery(Scenery sceById);

	List<Scenery> findSceByName(String secName);

	//根据typeId查询景点名称
	String findSceTypeName(Integer scenictypeId);

	String findSceNameById(Integer sceId);

	int findSceneryReferBySceId(Scenery scenery);
}
