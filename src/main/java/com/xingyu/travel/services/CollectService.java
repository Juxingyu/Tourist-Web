package com.xingyu.travel.services;

import com.xingyu.travel.model.Collect;
import com.xingyu.travel.model.Notes;
import com.xingyu.travel.model.Scenery;
import com.xingyu.travel.model.Strategy;

import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.services
 * @Version: 1.0
 **/
public interface CollectService {
	//根据用户ID查询收藏列表
	List<Notes> findAllNotes(Integer userId);

	List<Scenery> findAllSce(Integer userId);

	List<Strategy> findAllStra(Integer userId);

	List<Collect> findAllCollect(Integer userId);

	int addCollect(Collect collect);

	Collect findCollectBysceId(Integer sceId);

	//根据用户id、sceid查询收藏表
	boolean findCollectByUIdAndSceId(Integer userId, Integer sceId);

	//根据用户id、notesId查询收藏表
	boolean findCollectByUIdAndNotesId(Integer userId, Integer notesId);

	// 根据用户id、notesId查询收藏表
	boolean findCollectByUIdAndStraId(Integer userId, Integer straId);

	// 根据用户id、foodId查询收藏表
	boolean findCollectByUIdAndFoodId(Integer userId, Integer id);
}
