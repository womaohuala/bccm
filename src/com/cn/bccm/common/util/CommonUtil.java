package com.cn.bccm.common.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.jbpm.api.task.Task;

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
	
	public static List<Task> getTasksByPage(List<Task> tasks,int pageNo,int pageSize){
		List<Task> lastTask = new ArrayList<Task>();
		if(tasks!=null){
			tasks = tasks.subList((pageNo-1)*pageSize, tasks.size());
		}
		Iterator<Task> it = tasks.iterator();
		for(int i=0;i<pageSize;i++){
			if(it.hasNext()){
				lastTask.add(it.next());
			}
		}
		return lastTask;
	}
	
	
}
