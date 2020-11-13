package com.xingyu.travel.services.Impl;

import com.xingyu.travel.mapper.SceneryMapper;
import com.xingyu.travel.mapper.ScenictypeMapper;
import com.xingyu.travel.model.Scenery;
import com.xingyu.travel.model.Scenictype;
import com.xingyu.travel.services.SceneryService;
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
public class SceneryServiceImpl implements SceneryService {
	@Autowired
	private SceneryMapper sceneryMapper;
	@Autowired
	private ScenictypeMapper scenictypeMapper;

	/**
	 * @Description: 查询所有的景点类型
	 * @Author: Admin
	 * @Param: []
	 * @Return java.util.List<com.xingyu.travel.model.Scenictype>
	 **/
	@Override
	public List<Scenictype> findAllSceneryType() {
		return scenictypeMapper.findAllSceneryType();
	}

	/**
	 * @Description: 查询所有景点信息
	 * @Author: Admin
	 * @Param: []
	 * @Return java.util.List<com.xingyu.travel.model.Scenery>
	 **/
	@Override
	public List<Scenery> findAllScenery() {
		return sceneryMapper.findAllScenery();
	}

	/**
	 * @Description: 更改景点状态（删除/恢复景点）
	 * @Author: Admin
	 * @Param: [scenery]
	 * @Return int
	 **/
	@Override
	public int updateSceneryStatus(Scenery scenery) {
		return sceneryMapper.updateByPrimaryKeySelective(scenery);
	}

	/**
	 * @Description: 添加景点
	 * @Author: Admin
	 * @Param: [scenery]
	 * @Return void
	 **/
	@Override
	public int insertScenery(Scenery scenery) {
		return sceneryMapper.insertSelective(scenery);
	}

	/**
	 * @Description: 添加景点类别
	 * @Author: Admin
	 * @Param: [scenictype]
	 * @Return int
	 **/
	@Override
	public int insertSceType(Scenictype scenictype) {
		return scenictypeMapper.insert(scenictype);
	}

	/**
	 * @Description: 修改景点类别（修改、删除景点类别状态）
	 * @Author: Admin
	 * @Param: [scenictype]
	 * @Return int
	 **/
	@Override
	public int updateSceTypeStatus(Scenictype scenictype) {
		return scenictypeMapper.updateByPrimaryKeySelective(scenictype);
	}

	/**
	 * @Description: 景点推荐
	 * @Author: Admin
	 * @Param: []
	 * @Return java.util.List<com.xingyu.travel.model.Scenery>
	 **/
	@Override
	public List<Scenery> findSceRefer() {
		return sceneryMapper.findSceRefer();
	}

	/**
	 * @Description: 撤销景点推荐
	 * @Author: Admin
	 * @Param: [scenery]
	 * @Return int
	 **/
	@Override
	public int updateStatus(Scenery scenery) {
		return sceneryMapper.updateByPrimaryKeySelective(scenery);
	}

	//根据id查询景点信息
	@Override
	public Scenery findSceById(Integer id) {
		return sceneryMapper.selectByPrimaryKey(id);
	}

	//查询景点类别
	@Override
	public Scenictype findSceTypeById(Integer scenictypeId) {
		return scenictypeMapper.selectByPrimaryKey(scenictypeId);
	}

	//根据景点类型ID查询景点
	@Override
	public List<Scenery> findSceByTypeId(Integer scenictypeId) {
		return sceneryMapper.findSceByTypeId(scenictypeId);
	}

	//查询景点信息
	@Override
	public List<Scenery> findAllSce() {
		return sceneryMapper.findAllSce();
	}

	//查询景点类型
	@Override
	public List<Scenictype> findAllSceType() {
		return scenictypeMapper.findAllSceneryType();
	}

	@Override
	public int updateScenery(Scenery sceById) {
		return sceneryMapper.updateByPrimaryKeySelective(sceById);
	}

	@Override
	public List<Scenery> findSceByName(String secName) {
		return sceneryMapper.findSceByName(secName);
	}

	//根据typeId查询景点名称
	@Override
	public String findSceTypeName(Integer scenictypeId) {
		return scenictypeMapper.findSceTypeName(scenictypeId);
	}

	//评论的景点名称
	@Override
	public String findSceNameById(Integer sceId) {
		return sceneryMapper.findSceNameById(sceId);
	}

	@Override
	public int findSceneryReferBySceId(Scenery scenery) {
		return sceneryMapper.updateByPrimaryKeySelective(scenery);
	}
}
