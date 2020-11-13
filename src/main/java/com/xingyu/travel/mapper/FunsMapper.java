package com.xingyu.travel.mapper;

import com.xingyu.travel.base.BaseMapper;
import com.xingyu.travel.model.Funs;

import java.util.List;

public interface FunsMapper extends BaseMapper<Funs> {
	//查询所有权限集合
	List<Funs> findAll();

	//查询用户ID 的权限集合
	List<Funs> findFunsByUsersId(Integer userId);

	//查询用户ID 的权限集合
	List<Funs> findFunsByAdminId(Integer userId);

	//根据权限ID查询此权限下的二级菜单
	List<Funs> findSubFunsByFunId(Integer funsId);
}