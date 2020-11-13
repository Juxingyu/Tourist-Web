package com.xingyu.travel.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.vo
 * @Version: 1.0
 **/
@Data
public class SceneryVo implements Serializable {
	//景点名字
	private String secName;
	//景点类型
	private Integer scenictypeId;
	//景点图片
	private Integer secPic;
	//景点地址
	private String secSite;
	//景点描述
	private String secDesc;
}
