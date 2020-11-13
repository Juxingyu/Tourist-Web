package com.xingyu.travel.util;

import java.util.UUID;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.util
 * @Version: 1.0
 **/
public class UUidUtil {
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static void main(String[] args) {
		System.out.println(getUUID());
		System.out.println(getUUID());
		System.out.println(getUUID());
		System.out.println(getUUID());
		System.out.println(getUUID());
		System.out.println(getUUID());
		System.out.println(getUUID());
		System.out.println(getUUID());
		System.out.println(getUUID());
		System.out.println(getUUID());
	}
}
