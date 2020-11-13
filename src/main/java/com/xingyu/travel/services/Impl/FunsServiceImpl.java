package com.xingyu.travel.services.Impl;

import com.xingyu.travel.mapper.FunsMapper;
import com.xingyu.travel.model.Funs;
import com.xingyu.travel.services.FunsService;
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
public class FunsServiceImpl implements FunsService {
	@Autowired
	private FunsMapper funsMapper;

	/**
	 * @Description: 根据ID查询权限
	 * @Author: Admin
	 * @Param: [id]
	 * @Return com.xingyu.travel.model.Funs
	 **/
	@Override
	public Funs findById(Integer id) {
		return funsMapper.selectByPrimaryKey(id);
	}

	/**
	 * @Description: 查询所有权限
	 * @Author: Admin
	 * @Param: []
	 * @Return java.util.List<com.xingyu.travel.model.Funs>
	 **/
	@Override
	public List<Funs> findAll() {
		return funsMapper.findAll();
	}

	/**
	 * @Description: 查询用户ID 的权限集合
	 * @Author: Admin
	 * @Param: [userId]
	 * @Return java.util.List<com.xingyu.travel.model.Funs>
	 **/
	@Override
	public List<Funs> findFunsByUsersId(Integer userId) {
		return funsMapper.findFunsByUsersId(userId);
	}

	/**
	 * @Description: 根据权限ID查询此权限下的二级菜单
	 * @Author: Admin
	 * @Param: [funsId]
	 * @Return java.util.List<com.xingyu.travel.model.Funs>
	 **/
	@Override
	public List<Funs> findSubFunsByFunId(Integer funsId) {
		return funsMapper.findSubFunsByFunId(funsId);
	}

	//查询管理员的权限集合
	@Override
	public List<Funs> findFunsByAdminId(Integer id) {
		return funsMapper.findFunsByAdminId(id);
	}
}
