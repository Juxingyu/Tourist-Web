package com.xingyu.travel.mapper;

import com.xingyu.travel.base.BaseMapper;
import com.xingyu.travel.model.Collect;
import com.xingyu.travel.model.Notes;
import com.xingyu.travel.model.Scenery;
import com.xingyu.travel.model.Strategy;

import java.util.List;

public interface CollectMapper extends BaseMapper<Collect> {

	List<Notes> findAllNotes(Integer userId);

	List<Scenery> findAllSce(Integer userId);

	List<Strategy> findAllStra(Integer userId);

	List<Collect> findAllCollect(Integer userId);

	Collect findCollectBysceId(Integer sceId);

	Collect findCollectByUIdAndSceId(Integer userId, Integer sceId);

	Collect findCollectByUIdAndNotesId(Integer userId, Integer notesId);

	Collect findCollectByUIdAndStraId(Integer userId, Integer straId);

	Collect findCollectByUIdAndFoodId(Integer userId, Integer id);

}