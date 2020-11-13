package com.xingyu.travel.base;

/**
 * @Description:
 * @Author: Xingyu
 * @Version: 1.0
 **/
public interface BaseMapper<T> {
	int deleteByPrimaryKey(Integer funId);

	int insert(T record);

	int insertSelective(T record);

	T selectByPrimaryKey(Integer funId);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);
}
