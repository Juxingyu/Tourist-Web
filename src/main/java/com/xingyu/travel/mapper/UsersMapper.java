package com.xingyu.travel.mapper;

import com.xingyu.travel.base.BaseMapper;
import com.xingyu.travel.model.Users;
import com.xingyu.travel.vo.LoginVo;

import java.util.List;

public interface UsersMapper extends BaseMapper<Users> {
	//查询所有用户
	List<Users> findAll();

	//登录验证，根据Vo查找用户密码
	Users findByLoginVo(LoginVo vo);

	//根据用户ID验证老密码是否正确
	boolean findByIdAndPassword(Integer userId, String oldPwd);

	//修改用户密码
	boolean updatePass(Integer userId, String newPwd);

	//根据邮箱查询用户（验证注册邮箱唯一）
	Users findByEmail(String email);

	Users findLoginNameAndEmail(Users users);

	Users findByLoginName(String loginName);
}