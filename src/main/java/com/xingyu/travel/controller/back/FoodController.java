package com.xingyu.travel.controller.back;

import com.xingyu.travel.model.Food;
import com.xingyu.travel.services.FoodService;
import com.xingyu.travel.util.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.controller.back
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/food")
public class FoodController {
	@Autowired
	private FoodService foodService;

	@RequestMapping("/foodList")
	public String foodList(HttpServletRequest request) {
		List<Food> foodList = foodService.findAllFood();
		request.setAttribute("foodList", foodList);
		return "/back/food/foodList";
	}

	@RequestMapping("/updateFoodStatus")
	public String updateFoodStatus(Food food, HttpServletRequest request) {
		int i = foodService.updateFoodStatus(food);
		request.setAttribute("i", i);
		return "/back/food/foodList";
	}

	@RequestMapping("/toInsertFood")
	public String toInsertFood() {
		return "/back/food/insertFood";
	}

	@RequestMapping("/foodInfo")
	public String foodInfo(Food food, HttpServletRequest request) {
		Food foodById = foodService.findFoodById(food.getId());
		request.setAttribute("foodById", foodById);
		return "/back/food/foodInfo";
	}

	@RequestMapping("/updateFood")
	public String updateFood(Food foodById, HttpServletRequest request) {
		int i = foodService.updateFood(foodById);
		request.setAttribute("i", i);
		return "/back/food/foodInfo";
	}

	@RequestMapping("/insertFood")
	public String insertFood(Food food, HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile[] files) throws Exception {
		if (null != files) {
			String fileNameStr = UploadFileUtil.uploadFileByFiles(files);
			food.setPic(fileNameStr);
		}
		int i = foodService.insertFood(food);
		request.setAttribute("i", i);
		return "/back/food/insertFood";
	}
}
