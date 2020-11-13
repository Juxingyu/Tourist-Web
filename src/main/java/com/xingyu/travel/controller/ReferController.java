package com.xingyu.travel.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xingyu.travel.model.*;
import com.xingyu.travel.services.NotesService;
import com.xingyu.travel.services.SceneryService;
import com.xingyu.travel.services.StrategyService;
import com.xingyu.travel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.controller
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/refer")
public class ReferController {
	@Autowired
	private NotesService notesService;
	@Autowired
	private StrategyService strategyService;
	@Autowired
	private SceneryService sceneryService;
	@Autowired
	private UserService userService;

	/**
	 * @Description: 跳转游记推荐界面
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toNotesRefer")
	public String toNotesRefer(Integer pageNo, Integer pageSize, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 100 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Notes> notesRefer = notesService.findNotesRefer();
		for (Notes notes : notesRefer) {
			Users userServiceById = userService.findById(notes.getUserId());
			notes.setPetName(userServiceById.getPetName());
		}
		PageInfo<Notes> pageInfo = new PageInfo(notesRefer);
		request.setAttribute("page", pageInfo);
		request.setAttribute("notesRefer", notesRefer);
		return "/back/refer/notesRefer";
	}

	/**
	 * @Description: 撤销推荐
	 * @Author: Admin
	 * @Param: [notes]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/updateStatus")
	public String updateStatus(Notes notes, Strategy strategy, Scenery scenery) {
		if (null != notes.getNotesId()) {
			notesService.updateStatus(notes);
			return "/back/refer/notesRefer";
		} else if (null != strategy.getStraId()) {
			strategyService.updateStatus(strategy);
			return "/back/refer/straRefer";
		} else if (null != scenery.getSceId()) {
			sceneryService.updateStatus(scenery);
			return "/back/refer/sceRefer";
		} else {
			return "";
		}
	}

	/**
	 * @Description: 攻略推荐
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toStraRefer")
	public String toStraRefer(Integer pageNo, Integer pageSize, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 100 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Strategy> straRefer = strategyService.findStraRefer();
		PageInfo<Strategy> pageInfo = new PageInfo(straRefer);
		request.setAttribute("page", pageInfo);
		request.setAttribute("straRefer", straRefer);
		return "/back/refer/straRefer";
	}

	/**
	 * @Description: 景点推荐
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toSceRefer")
	public String toSceRefer(Integer pageNo, Integer pageSize, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 100 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Scenery> sceRefer = sceneryService.findSceRefer();
		for (Scenery scenery : sceRefer) {
			Scenictype sceTypeById = sceneryService.findSceTypeById(scenery.getScenictypeId());
			scenery.setScenictpyeName(sceTypeById.getScenictpyeName());
		}
		PageInfo<Scenery> pageInfo = new PageInfo(sceRefer);
		request.setAttribute("page", pageInfo);
		request.setAttribute("sceRefer", sceRefer);
		return "/back/refer/sceRefer";
	}
}
