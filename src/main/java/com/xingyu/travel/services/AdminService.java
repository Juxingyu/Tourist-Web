package com.xingyu.travel.services;

import com.xingyu.travel.model.Admin;
import com.xingyu.travel.vo.LoginVo;

/**
 * @Description:
 * @Author: Xingyu
 * @Version: 1.0
 **/
public interface AdminService {
	//（用于用户登录，验证用户名与密码匹配）
	Admin findByLoginVo(LoginVo vo);

	//插入用户
	int insert(Admin admin);

	//通过用户名和邮箱查询（忘记密码功能，向邮箱发送新密码）
	Admin findLoginNameAndEmail(Admin admin);

	//修改用户密码
	boolean updatePass(Integer id, String newPwd);

	boolean updateAdminPass(Admin admin);


	// 根据用户ID验证老密码是否正确
	boolean findByIdAndPassword(Integer id, String oldPwd);

	int updateEmail(Admin admin);

	Admin findByEmail(String email);
}
