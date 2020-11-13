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
public class LoginVo implements Serializable {
	private String loginName;
	private String pass;
	private String validateCode;
}
