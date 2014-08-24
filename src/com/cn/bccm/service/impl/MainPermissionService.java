package com.cn.bccm.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.bccm.dao.IMainPermissionDao;
import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.MainPermission;
import com.cn.bccm.service.IMainPermissionService;

@Service
@Transactional
public class MainPermissionService implements IMainPermissionService  {
	@Autowired
	private IMainPermissionDao permissionDao;
	
	public void deletePermission(MainPermission permission) {
		permissionDao.delete(permission);
	}

	public MainPermission getPermission(int id) {
		return permissionDao.get(id);
	}

	public List<MainPermission> list(Object[] params) {
		return permissionDao.list("from MainPermission", params);
	}
	
	public List<MainPermission> listModual(Object[] params) {
		return permissionDao.list("from MainPermission where perParent=0", params);
	}

	public List<MainPermission> listByPage(String hql, int pageNo, int pageSize,
			Map<String, Object> params) {
		return permissionDao.listByPage(hql, pageNo, pageSize, params);
	}

	public void saveOrUpdatePermission(MainPermission permission) throws Exception {
		permissionDao.saveOrUpdate(permission);
	}
	
	public  Page<MainPermission> listByPage(Page<MainPermission> page, String hql, Map<String, Object> params) throws Exception {
		page.setTotalCount(permissionDao.findCount("select count(*) "+hql, params));
		return permissionDao.listByPage(page, hql, params);
	}
	

	public MainPermission getByAction(String url) {
		// TODO Auto-generated method stub
		return permissionDao.findUnique("from MainPermission e where e.perAction =? ", new Object[]{url});
	}

	public MainPermission getById(int id) {
		// TODO Auto-generated method stub
		return permissionDao.findUnique("from MainPermission e where e.perId =? ", new Object[]{id});
	}

	public int deleteByParent(String id) {
		// TODO Auto-generated method stub
		return permissionDao.executeUpdate("delete from MainPermission e where e.perParent =? ", new Object[]{Integer.parseInt(id)});
	}

}
