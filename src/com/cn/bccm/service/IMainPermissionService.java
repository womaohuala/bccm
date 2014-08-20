package com.cn.bccm.service;

import java.util.List;
import java.util.Map;

import com.cn.bccm.common.service.BaseService;
import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.MainPermission;
import com.cn.bccm.model.MainRole;

public interface IMainPermissionService {
	public List<MainPermission> list(Object[] params);
	public void saveOrUpdatePermission(MainPermission employee)throws Exception;
	public void deletePermission(MainPermission employee);
	public MainPermission getPermission(int id);
	public List<MainPermission> listByPage(String hql, int pageNo,int pageSize,Map<String,Object> params);
	
	public  Page<MainPermission> listByPage(Page<MainPermission> page, String hql, Map<String, Object> params) throws Exception;
	/**
	 * 根据URL获取权限
	 * @param url
	 * @return
	 */
	public MainPermission getByAction(String url);
	
	
	public MainPermission getById(int i);
}
