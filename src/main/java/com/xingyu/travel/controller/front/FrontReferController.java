package com.xingyu.travel.controller.front;

import com.xingyu.travel.model.Scenery;
import com.xingyu.travel.model.Strategy;
import com.xingyu.travel.services.SceneryService;
import com.xingyu.travel.services.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.controller.front
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/front/refer")
public class FrontReferController {
	@Autowired
	private SceneryService sceneryService;
	@Autowired
	private StrategyService strategyService;

	/**
	 * @Description: 推荐景点
	 * @Author: Admin
	 * @Param: [request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/referSce")
	public String referSce(HttpServletRequest request) {
		List<Scenery> sceReferList = sceneryService.findSceRefer();
		request.setAttribute("sceReferList", sceReferList);
		return "/front/refer/referSce";
	}

	/**
	 * @Description: 推荐攻略
	 * @Author: Admin
	 * @Param:
	 * @Return
	 **/
	@RequestMapping("/referStra")
	public String referStra(HttpServletRequest request) {
		List<Strategy> straReferList = strategyService.findStraRefer();
		request.setAttribute("straReferList", straReferList);
		return "/front/refer/referStra";
	}
}
