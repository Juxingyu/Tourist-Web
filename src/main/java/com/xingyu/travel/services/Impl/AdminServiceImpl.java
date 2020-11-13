package com.xingyu.travel.services.Impl;

import com.xingyu.travel.mapper.AdminMapper;
import com.xingyu.travel.model.Admin;
import com.xingyu.travel.services.AdminService;
import com.xingyu.travel.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.services.Impl
 * @Version: 1.0
 **/
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper adminMapper;

	//（用于用户登录，验证用户名与密码匹配）
	@Override
	public Admin findByLoginVo(LoginVo vo) {
		return adminMapper.findByLoginVo(vo);
	}

	@Override
	public int insert(Admin admin) {
		return adminMapper.insertSelective(admin);
	}

	//通过用户名和邮箱查询（忘记密码功能，向邮箱发送新密码）
	@Override
	public Admin findLoginNameAndEmail(Admin admin) {
		return adminMapper.findLoginNameAndEmail(admin);
	}

	//修改用户密码
	@Override
	public boolean updatePass(Integer id, String newEcodePassword) {
		return adminMapper.updatePass(id, newEcodePassword);
	}

	@Override
	public boolean updateAdminPass(Admin admin) {
		int i = adminMapper.updateByPrimaryKeySelective(admin);
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean findByIdAndPassword(Integer id, String oldPwd) {
		Admin admin = adminMapper.findByIdAndPassword(id, oldPwd);
		if (null == admin) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int updateEmail(Admin admin) {
		return adminMapper.updateByPrimaryKeySelective(admin);
	}

	@Override
	public Admin findByEmail(String email) {
		return adminMapper.findByEmail(email);
	}
}
