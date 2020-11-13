package com.xingyu.travel.services;

import com.xingyu.travel.model.Notestype;

/**
 * @Description: 游记类型相关
 * @Author: Xingyu
 * @Version: 1.0
 **/
public interface NotestypeService {
	//根据typeID查询游记类型
	Notestype findNotesTypeByTypeId(Integer notestypeId);
}
