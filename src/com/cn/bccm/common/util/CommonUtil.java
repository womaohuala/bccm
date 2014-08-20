package com.cn.bccm.common.util;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class CommonUtil {

	public static String getUtf8Code(String str){
		try {
			return new String(str.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return str;
		}
	}
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(CommonUtil.getUtf8Code("中国"));
	}
	
	
}
