package com.xingyu.travel.controller.back;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xingyu.travel.model.Scenery;
import com.xingyu.travel.model.Scenictype;
import com.xingyu.travel.services.SceneryService;
import com.xingyu.travel.util.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: 景点相关
 * @Author: Admin
 * @Param:
 * @Return
 **/
@Controller
@RequestMapping("/scenery")
public class SceneryController {
	@Autowired
	private SceneryService sceneryService;

	/**
	 * @Description: 查询所有景点信息（分页展示）
	 * @Author: Admin
	 * @Param: [pageNo, pageSize, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/sceneryList")
	public String findAllScenery(Integer pageNo, Integer pageSize, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 100 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		//查询所有的景点
		List<Scenery> sceneryList = sceneryService.findAllScenery();
		//遍历景点信息
		for (Scenery scenery : sceneryList) {
			//根据typeId查询typeName
			String sceTypeName = sceneryService.findSceTypeName(scenery.getScenictypeId());
			scenery.setScenictpyeName(sceTypeName);
		}
		PageInfo<Scenery> pageInfo = new PageInfo(sceneryList);
		request.setAttribute("page", pageInfo);
		request.setAttribute("sceneryList", sceneryList);
		return "/back/scenery/sceneryList";
	}

	/**
	 * @Description: 更改景点状态（删除/恢复景点）
	 * @Author: Admin
	 * @Param: [scenery]
	 * @Return int
	 **/
	@RequestMapping("/updateSceneryStatus")
	public String updateSceneryStatus(Scenery scenery) {
		int i = sceneryService.updateSceneryStatus(scenery);
		return "/back/scenery/sceneryList";
	}

	/**
	 * @Description: 跳转添加景点界面
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toInsertScenery")
	public String toInsertScenery(HttpServletRequest request) {
		//查询所有的类别
		List<Scenictype> scenicTypeList = sceneryService.findAllSceneryType();
		request.setAttribute("scenicTypeList", scenicTypeList);
		return "/back/scenery/insertScenery";
	}

	/**
	 * @Description: 添加景点
	 * @Author: Admin
	 * @Param: [scenery]
	 * @Return void
	 **/
	@RequestMapping("/insertScenery")
	public String insertScenery(Scenery scenery, HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile[] files) throws Exception {
		if (null != files) {
			String fileNameStr = UploadFileUtil.uploadFileByFiles(files);
			scenery.setSecPic(fileNameStr);
		}
		int i = sceneryService.insertScenery(scenery);
		request.setAttribute("i", i);
		return "/back/scenery/insertScenery";
	}

	/**
	 * @Description: 跳转添加景点类型界面
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toInsertSceType")
	public String toInsertSceType() {
		return "/back/scenery/insertSceType";
	}

	/**
	 * @Description: 添加景点类别
	 * @Author: Admin
	 * @Param: [scenictype]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/insertSceType")
	public String insertSceType(Scenictype scenictype) {
		sceneryService.insertSceType(scenictype);
		return "/back/scenery/insertSceType";
	}

	/**
	 * @Description: 查询所有景点类别展示
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/sceTypeList")
	public String findAllSceType(Integer pageNo, Integer pageSize, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 100 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Scenictype> scenicTypeList = sceneryService.findAllSceneryType();
		PageInfo<Scenery> pageInfo = new PageInfo(scenicTypeList);
		request.setAttribute("page", pageInfo);
		request.setAttribute("scenicTypeList", scenicTypeList);
		return "/back/scenery/sceTypeList";
	}

	/**
	 * @Description: 根据Id查询景点信息
	 * @Author: Admin
	 * @Param: [scenery, request, session]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/sceInfo")
	public String sceInfo(Scenery scenery, HttpServletRequest request) {
		//对应的景点对象
		Scenery sceById = sceneryService.findSceById(scenery.getSceId());
		//根据景点Id查询type 名
		Scenictype sceTypeById = sceneryService.findSceTypeById(sceById.getScenictypeId());
		sceById.setScenictpyeName(sceTypeById.getScenictpyeName());
		//将景点对象返回到页面
		request.setAttribute("sceById", sceById);
		return "/back/scenery/sceInfo";
	}


	/**
	 * @Description: 修改景点信息
	 * @Author: Admin
	 * @Param: [scenery, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/updateScenery")
	public String updateScenery(Scenery scenery, HttpServletRequest request) {
		int i = sceneryService.updateScenery(scenery);
		request.setAttribute("i", i);
		return "/back/scenery/sceInfo";
	}

	/**
	 * @Description: 删除景点类别（修改状态）
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/updateSceTypeStatus")
	public String updateSceTypeStatus(Scenictype scenictype) {
		sceneryService.updateSceTypeStatus(scenictype);
		return "/back/scenery/sceTypeList";
	}

	/**
	 * @Description: 修改scenery的refer状态（推荐景点）
	 * @Author: Admin
	 * @Param: [notes]
	 * @Return void
	 **/
	@RequestMapping("/referScenery")
	public String findSceneryReferBySceId(Scenery scenery, HttpServletRequest request) {
		int i = sceneryService.findSceneryReferBySceId(scenery);
		request.setAttribute("i", i);
		return "/back/scenery/sceneryList";
	}
}
