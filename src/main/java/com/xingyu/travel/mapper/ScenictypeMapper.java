package com.xingyu.travel.mapper;

import com.xingyu.travel.base.BaseMapper;
import com.xingyu.travel.model.Scenictype;

import java.util.List;

public interface ScenictypeMapper extends BaseMapper<Scenictype> {
	//查询所有的攻略类别
	List<Scenictype> findAllSceneryType();

	//根据typeId查询景点名称
	String findSceTypeName(Integer scenictypeId);
}