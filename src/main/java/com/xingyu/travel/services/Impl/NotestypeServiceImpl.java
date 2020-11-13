package com.xingyu.travel.services.Impl;

import com.xingyu.travel.mapper.NotestypeMapper;
import com.xingyu.travel.model.Notestype;
import com.xingyu.travel.services.NotestypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.services.Impl
 * @Version: 1.0
 **/
@Service
public class NotestypeServiceImpl implements NotestypeService {
	@Autowired
	private NotestypeMapper notestypeMapper;

	/**
	 * @Description: 根据notetypeID查询游记类型
	 * @Author: Admin
	 * @Param: [notestypeId]
	 * @Return com.xingyu.travel.model.Notestype
	 **/
	@Override
	public Notestype findNotesTypeByTypeId(Integer notestypeId) {
		return notestypeMapper.selectByPrimaryKey(notestypeId);
	}
}
