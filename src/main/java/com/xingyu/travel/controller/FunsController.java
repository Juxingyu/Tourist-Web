package com.xingyu.travel.controller;

import com.xingyu.travel.model.Funs;
import com.xingyu.travel.services.FunsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.controller
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/funs")
public class FunsController {

	@Autowired
	private FunsService funsService;

	/**
	 * @Description: 查询所有权限集合
	 * @Author: Admin
	 * @Param: []
	 * @Return java.util.List<com.xingyu.travel.model.Funs>
	 **/
	@RequestMapping("findAll")
	public List<Funs> findAll() {
		return funsService.findAll();
	}

	/**
	 * @Description: 根据ID查询权限
	 * @Author: Admin
	 * @Param: [id]
	 * @Return com.xingyu.travel.model.Funs
	 **/
	@RequestMapping("/findById")
	public Funs findById(Integer id) {
		return funsService.findById(id);
	}

	/**
	 * @Description: 查询二级导航集合
	 * @Author: Admin
	 * @Param: [funsId]
	 * @Return java.util.List<com.xingyu.travel.model.Funs>
	 **/
	@RequestMapping("/findSubFunsByFunId")
	public List<Funs> findSubFunsByFunId(Integer funsId) {
		return funsService.findSubFunsByFunId(funsId);
	}
}
