package com.cn.bccm.service;

import java.util.List;
import java.util.Map;

import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.MainRole;

public interface IMainRoleService   {
	public List<MainRole> list(Object[] params);
	public void saveOrUpdateRole(MainRole role)throws Exception;
	public void deleteRole(MainRole role);
	public MainRole getRole(int id);
	public List<MainRole> listByPage(String hql, int pageNo,int pageSize,Map<String,Object> params);
	
	public  Page<MainRole> listByPage(Page<MainRole> page, String hql, Map<String, Object> params) throws Exception;
	public MainRole getRoleByLogin(String username,String password);
}
