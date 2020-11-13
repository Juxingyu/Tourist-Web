package com.xingyu.travel.mapper;

import com.xingyu.travel.base.BaseMapper;
import com.xingyu.travel.model.Admin;
import com.xingyu.travel.vo.LoginVo;

/**
 * @Description: admin的Mapper接口
 * @Author: Admin
 **/
public interface AdminMapper extends BaseMapper<Admin> {
	/**
	 * @Description: 用于用户登录，验证用户名与密码匹配
	 * @Author: Admin
	 * @Param: [vo] vo
	 * @Return com.xingyu.travel.model.Admin
	 */
	Admin findByLoginVo(LoginVo vo);

	/**
	 * @Description: 通过用户名和邮箱查询（忘记密码功能，向邮箱发送新密码）
	 * @Author: Admin
	 * @Param:
	 * @Return
	 **/
	Admin findLoginNameAndEmail(Admin admin);

	/**
	 * @Description: 修改用户密码
	 * @Author: Admin
	 * @Param: [id, newEcodePassword]
	 * @Return boolean
	 **/
	boolean updatePass(Integer id, String newEcodePassword);

	/**
	 * @Description: 通过ID和旧密码进行查询
	 * @Author: Admin
	 * @Param: [id, oldPwd]
	 * @Return com.xingyu.travel.model.Admin
	 **/
	Admin findByIdAndPassword(Integer id, String oldPwd);

	/**
	 * @Description: 通过email进行查询
	 * @Author: Admin
	 * @Param: [email]
	 * @Return com.xingyu.travel.model.Admin
	 **/
	Admin findByEmail(String email);
}