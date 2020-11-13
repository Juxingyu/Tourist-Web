package com.xingyu.travel.mapper;

import com.xingyu.travel.base.BaseMapper;
import com.xingyu.travel.model.Food;

import java.util.List;

public interface FoodMapper extends BaseMapper<Food> {
	List<Food> findAllFood();

	List<Food> findFoodByName(String foodName);
}