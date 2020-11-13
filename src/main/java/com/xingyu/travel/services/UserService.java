package com.xingyu.travel.services;

import com.xingyu.travel.model.Users;
import com.xingyu.travel.vo.LoginVo;

import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @Version: 1.0
 **/
public interface UserService {
	//根据ID查询用户（展示个人信息）
	Users findById(Integer id);

	//查询所有用户
	List<Users> findAll();

	//更改用户状态
	int updateUsersStatus(Users users);

	//根据用户ID验证老密码是否正确
	boolean findByIdAndPassword(Integer userId, String oldPwd);

	//修改用户密码
	boolean updatePass(Integer userId, String newPwd);

	//根据邮箱查询用户（验证注册邮箱唯一）
	Users findByEmail(String email);
	
	//修改邮箱操作
	int updateEmail(Users users);

	//---------------------------
	//插入用户
	int insert(Users users);

	//（用于用户登录，验证用户名与密码匹配）
	Users findByLoginVo(LoginVo vo);

	//通过用户名和邮箱查询（忘记密码功能，向邮箱发送新密码）
	Users findLoginNameAndEmail(Users users);

	//根据用户名查找用户（验证用户名唯一）
	Users findByLoginName(String loginName);


}
