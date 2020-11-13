package com.xingyu.travel.services;

import com.xingyu.travel.model.Food;

import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @Version: 1.0
 **/
public interface FoodService {
	List<Food> findAllFood();

	int updateFoodStatus(Food food);

	//根据id查看food对象
	Food findFoodById(Integer id);

	int updateFood(Food food);

	int insertFood(Food food);

	List<Food> findFoodByName(String foodName);
}
