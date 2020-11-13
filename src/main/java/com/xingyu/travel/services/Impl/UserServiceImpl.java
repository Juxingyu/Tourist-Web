package com.xingyu.travel.services.Impl;

import com.xingyu.travel.mapper.UsersMapper;
import com.xingyu.travel.model.Users;
import com.xingyu.travel.services.UserService;
import com.xingyu.travel.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UsersMapper usersMapper;

	//根据ID查询用户信息
	@Override
	public Users findById(Integer id) {
		return usersMapper.selectByPrimaryKey(id);
	}

	//查询所有用户
	@Override
	public List<Users> findAll() {
		return usersMapper.findAll();
	}

	//更改用户状态
	@Override
	public int updateUsersStatus(Users users) {
		return usersMapper.updateByPrimaryKeySelective(users);
	}

	//根据用户ID验证老密码是否正确
	@Override
	public boolean findByIdAndPassword(Integer userId, String oldPwd) {
		return usersMapper.findByIdAndPassword(userId, oldPwd);
	}

	//修改用户密码
	@Override
	public boolean updatePass(Integer userId, String newPwd) {
		return usersMapper.updatePass(userId, newPwd);
	}

	//根据邮箱查询用户（验证注册邮箱唯一）
	@Override
	public Users findByEmail(String email) {
		return usersMapper.findByEmail(email);
	}

	//修改邮箱操作
	@Override
	public int updateEmail(Users users) {
		return usersMapper.updateByPrimaryKeySelective(users);
	}


	//----------------------------
	//插入用户
	@Override
	public int insert(Users users) {
		return usersMapper.insertSelective(users);
	}


	//根据用户名查找用户（验证用户名唯一）
	@Override
	public Users findByLoginName(String loginName) {
		return usersMapper.findByLoginName(loginName);
	}

	//通过用户名和邮箱查询（忘记密码功能，向邮箱发送新密码）
	@Override
	public Users findLoginNameAndEmail(Users users) {
		return usersMapper.findLoginNameAndEmail(users);
	}

	//（用于用户登录，验证用户名与密码匹配）
	@Override
	public Users findByLoginVo(LoginVo vo) {
		return usersMapper.findByLoginVo(vo);
	}

}
