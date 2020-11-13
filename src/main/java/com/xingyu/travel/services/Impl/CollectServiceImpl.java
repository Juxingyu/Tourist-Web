package com.xingyu.travel.services.Impl;

import com.xingyu.travel.mapper.CollectMapper;
import com.xingyu.travel.model.Collect;
import com.xingyu.travel.model.Notes;
import com.xingyu.travel.model.Scenery;
import com.xingyu.travel.model.Strategy;
import com.xingyu.travel.services.CollectService;
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
public class CollectServiceImpl implements CollectService {
	@Autowired
	private CollectMapper collectMapper;

	@Override
	public List<Notes> findAllNotes(Integer userId) {
		return collectMapper.findAllNotes(userId);
	}

	@Override
	public List<Scenery> findAllSce(Integer userId) {
		return collectMapper.findAllSce(userId);
	}

	@Override
	public List<Strategy> findAllStra(Integer userId) {
		return collectMapper.findAllStra(userId);
	}

	@Override
	public List<Collect> findAllCollect(Integer userId) {
		return collectMapper.findAllCollect(userId);
	}

	@Override
	public int addCollect(Collect collect) {
		return collectMapper.insertSelective(collect);
	}

	@Override
	public Collect findCollectBysceId(Integer sceId) {
		return collectMapper.findCollectBysceId(sceId);
	}

	@Override
	public boolean findCollectByUIdAndSceId(Integer userId, Integer sceId) {
		Collect collectByUIdAndSceId = collectMapper.findCollectByUIdAndSceId(userId, sceId);
		if (collectByUIdAndSceId == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean findCollectByUIdAndNotesId(Integer userId, Integer notesId) {
		Collect collectByUIdAndNotesId = collectMapper.findCollectByUIdAndNotesId(userId, notesId);
		if (collectByUIdAndNotesId == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean findCollectByUIdAndStraId(Integer userId, Integer straId) {
		Collect collectByUIdAndStraId = collectMapper.findCollectByUIdAndStraId(userId, straId);
		if (collectByUIdAndStraId == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean findCollectByUIdAndFoodId(Integer userId, Integer id) {
		collectMapper.findCollectByUIdAndFoodId(userId, id);
		return false;
	}
}
