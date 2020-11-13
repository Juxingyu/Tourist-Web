package com.xingyu.travel.services.Impl;

import com.xingyu.travel.mapper.FoodMapper;
import com.xingyu.travel.model.Food;
import com.xingyu.travel.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.services.Impl
 * @Version: 1.0
 **/
@Service
public class FoodServiceImpl implements FoodService {
	@Autowired
	private FoodMapper foodMapper;

	@Override
	public List<Food> findAllFood() {
		return foodMapper.findAllFood();
	}

	@Override
	public int updateFoodStatus(Food food) {
		return foodMapper.updateByPrimaryKeySelective(food);
	}

	@Override
	public Food findFoodById(Integer id) {
		return foodMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateFood(Food food) {
		return foodMapper.updateByPrimaryKeySelective(food);
	}

	@Override
	public int insertFood(Food food) {
		return foodMapper.insertSelective(food);
	}

	@Override
	public List<Food> findFoodByName(String foodName) {
		return foodMapper.findFoodByName(foodName);
	}
}
