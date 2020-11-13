package com.xingyu.travel.services;

import com.xingyu.travel.model.Funs;

import java.util.List;

public interface FunsService {
	//根据ID查询权限
	Funs findById(Integer id);

	//查询所有权限
	List<Funs> findAll();

	//查询用户ID 的权限集合
	List<Funs> findFunsByUsersId(Integer userId);

	//根据权限ID查询此权限下的二级菜单
	List<Funs> findSubFunsByFunId(Integer funsId);

	//查询管理员的权限集合
	List<Funs> findFunsByAdminId(Integer id);
}
