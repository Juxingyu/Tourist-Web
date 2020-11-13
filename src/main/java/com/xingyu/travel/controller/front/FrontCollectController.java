package com.xingyu.travel.controller.front;

import com.xingyu.travel.model.*;
import com.xingyu.travel.services.CollectService;
import com.xingyu.travel.services.NotesService;
import com.xingyu.travel.services.SceneryService;
import com.xingyu.travel.services.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:收藏
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.controller.front
 * @Version: 1.0
 **/
// @RestController
@Controller
@RequestMapping("/front/collect")
public class FrontCollectController {
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private CollectService collectService;
	@Autowired
	private NotesService notesService;
	@Autowired
	private SceneryService sceneryService;
	@Autowired
	private StrategyService strategyService;

	/**
	 * @Description: 查询收藏列表
	 * @Author: Admin
	 * @Param: [request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toCollect")
	public String findCollectByUId(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		Users users = (Users) redisTemplate.boundValueOps(ip).get();
		//根据用户ID查询收藏列表
		ArrayList<Notes> allNotes = new ArrayList<>();
		ArrayList<Scenery> allSce = new ArrayList<>();
		ArrayList<Strategy> allStra = new ArrayList<>();
		List<Collect> allCollect = collectService.findAllCollect(users.getUserId());
		for (Collect collect : allCollect) {
			if (collect.getNotesId() != null) {
				Notes notesById = notesService.findNotesById(collect.getNotesId());
				allNotes.add(notesById);
			} else if (collect.getSceId() != null) {
				Scenery sceById = sceneryService.findSceById(collect.getSceId());
				allSce.add(sceById);
			} else if (collect.getStraId() != null) {
				Strategy straById = strategyService.findStraById(collect.getStraId());
				allStra.add(straById);
			}
		}
		request.setAttribute("allNotes", allNotes);
		request.setAttribute("allSce", allSce);
		request.setAttribute("allStra", allStra);
		return "/front/collect/collectList";
	}


	/**
	 * @Description: 添加收藏
	 * @Author: Admin
	 * @Param:
	 * @Return
	 **/
	@RequestMapping("/addCollect")
	public void addCollect(Collect collect, HttpServletResponse response, HttpServletRequest request) throws IOException {
		Users users = (Users) redisTemplate.boundValueOps(request.getRemoteAddr()).get();
		collect.setUserId(users.getUserId());
		// Collect collectBysceId = collectService.findCollectBysceId(collect.getSceId());
		int i = collectService.addCollect(collect);
		Scenery sceById = sceneryService.findSceById(collect.getSceId());
		sceById.setPraseCount(sceById.getPraseCount() + 1);
		//同步到数据库
		sceneryService.updateScenery(sceById);
		Scenery scenery = sceneryService.findSceById(collect.getSceId());
		if (i > 0) {
			request.setAttribute("collscenery", scenery);
			response.getWriter().write("0");
		} else {
			response.getWriter().write("1");
		}
	}

	@RequestMapping("/addStraCollect")
	public void addStraCollect(Collect collect, HttpServletResponse response, HttpServletRequest request) throws IOException {
		Users users = (Users) redisTemplate.boundValueOps(request.getRemoteAddr()).get();
		collect.setUserId(users.getUserId());
		// Collect collectBysceId = collectService.findCollectBysceId(collect.getSceId());
		int i = collectService.addCollect(collect);
		Strategy straById = strategyService.findStraById(collect.getStraId());
		straById.setPraseCount(straById.getPraseCount() + 1);
		//同步到数据库
		strategyService.updateStrategy(straById);
		Strategy strategy = strategyService.findStraById(collect.getStraId());
		if (i > 0) {
			request.setAttribute("collstrategy", strategy);
			response.getWriter().write("0");
		} else {
			response.getWriter().write("1");
		}
	}

	@RequestMapping("/addNotesCollect")
	public void addNotesCollect(Collect collect, HttpServletResponse response, HttpServletRequest request) throws IOException {
		Users users = (Users) redisTemplate.boundValueOps(request.getRemoteAddr()).get();
		collect.setUserId(users.getUserId());
		int i = collectService.addCollect(collect);
		Notes notesById = notesService.findNotesById(collect.getNotesId());
		notesById.setPraseCount(notesById.getPraseCount() + 1);
		notesService.updateNotes(notesById);
		Notes notes = notesService.findNotesById(collect.getNotesId());
		if (i > 0) {
			request.setAttribute("collnotes", notes);
			response.getWriter().write("0");
		} else {
			response.getWriter().write("1");
		}
	}
}
