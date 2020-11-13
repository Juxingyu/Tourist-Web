package com.xingyu.travel.controller.back;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xingyu.travel.model.Strategy;
import com.xingyu.travel.model.Strategytype;
import com.xingyu.travel.services.StrategyService;
import com.xingyu.travel.util.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: 攻略相关
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.controller
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/strategy")
public class StrategyController {
	@Autowired
	private StrategyService strategyService;

	/**
	 * @Description: 查询所有攻略（分页展示）
	 * @Author: Admin
	 * @Param: [pageNo, pageSize, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/strategyList")
	public String findAllStrategy(Integer pageNo, Integer pageSize, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 100 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Strategy> strategyList = strategyService.findAllStrategy();
		PageInfo<Strategy> pageInfo = new PageInfo(strategyList);
		request.setAttribute("page", pageInfo);
		request.setAttribute("strategyList", strategyList);

		return "/back/strategy/strategyList";
	}

	/**
	 * @Description: 更改旅游攻略状态（删除、修改）
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/updateStrategyStatus")
	public String updateStrategyStatus(Strategy strategy) {
		strategyService.updateStrategyStatus(strategy);
		return "/back/strategy/strategyList";
	}

	/**
	 * @Description: 跳转添加旅游攻略界面
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toInsertStrategy")
	public String toInsertStrategy(HttpServletRequest request) {
		//跳转界面，携带所有攻略类型数据
		List<Strategytype> strategyTypeList = strategyService.findAllStrategyType();
		request.setAttribute("strategyTypeList", strategyTypeList);
		return "/back/strategy/insertStrategy";
	}

	/**
	 * @Description: 添加旅游攻略
	 * @Author: Admin
	 * @Param: [strategy, response]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/insertStrategy")
	public String insertStrategy(Strategy strategy, @RequestParam(value = "file", required = false) MultipartFile[] files) throws Exception {
		if (null != files) {
			String fileNameStr = UploadFileUtil.uploadFileByFiles(files);
			strategy.setStraPic(fileNameStr);
		}
		int i = strategyService.insertStrategy(strategy);
		if (i > 0) {
			return "/back/strategy/insertStrategy";
		} else {
			return "/back/error/error";
		}

	}

	/**
	 * @Description: 查询所有攻略类别(分页展示)
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/straTypeList")
	public String straTypeList(Integer pageNo, Integer pageSize, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 100 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Strategytype> strategyTypeList = strategyService.findAllStrategyType();
		PageInfo<Strategytype> pageInfo = new PageInfo(strategyTypeList);
		request.setAttribute("page", pageInfo);
		request.setAttribute("strategyTypeList", strategyTypeList);
		return "/back/strategy/straTypeList";
	}

	/**
	 * @Description: 更改攻略类别状态
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/updateStraTypeStatus")
	public String updateStraTypeStatus(Strategytype strategytype) {
		strategyService.updateStraTypeStatus(strategytype);
		return "/back/strategy/straTypeList";
	}

	/**
	 * @Description: 跳转添加攻略类型界面
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toInsertStraType")
	public String toInsertStraType() {
		return "/back/strategy/insertStraType";
	}

	/**
	 * @Description: 添加攻略类型
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/insertStraType")
	public String insertStraType(Strategytype strategytype) {
		strategyService.insertStraType(strategytype);
		return "/back/strategy/insertStraType";
	}

	/**
	 * @Description: 根据Id查询攻略信息
	 * @Author: Admin
	 * @Param: [strategy, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/straInfo")
	public String straInfo(Strategy strategy, HttpServletRequest request) {
		Strategy straById = strategyService.findStraById(strategy.getStraId());
		Strategytype straTypeById = strategyService.findStraTypeById(straById.getStratypeId());
		straById.setStratypeName(straTypeById.getStratypeName());
		request.setAttribute("straById", straById);
		return "/back/strategy/straInfo";
	}

	/**
	 * @Description: 修改攻略信息
	 * @Author: Admin
	 * @Param: [strategy, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/updateStrategy")
	public String updateStrategy(Strategy strategy, HttpServletRequest request) {
		int i = strategyService.updateStrategy(strategy);
		request.setAttribute("i", i);
		return "/back/strategy/straInfo";
	}

	/**
	 * @Description: 修改strategy的refer状态（推荐攻略）
	 * @Author: Admin
	 * @Param: [notes]
	 * @Return void
	 **/
	@RequestMapping("/referStrategy")
	public String findStrategyReferByStraId(Strategy strategy, HttpServletRequest request) {
		int i = strategyService.findStrategyReferByStraId(strategy);
		request.setAttribute("i", i);
		return "/back/strategy/strategyList";
	}
}
