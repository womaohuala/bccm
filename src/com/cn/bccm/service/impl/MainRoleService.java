package com.cn.bccm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.bccm.dao.ICoCompanyDao;
import com.cn.bccm.dao.IMainDepartmentDao;
import com.cn.bccm.dao.IMainEmployeeDao;
import com.cn.bccm.dao.IMainRoleDao;
import com.cn.bccm.dao.IUserDao;
import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.CoopCompany;
import com.cn.bccm.model.MainDepartment;
import com.cn.bccm.model.MainEmployee;
import com.cn.bccm.model.MainPlan;
import com.cn.bccm.model.MainRole;
import com.cn.bccm.model.User;
import com.cn.bccm.service.IMainDepartmentService;
import com.cn.bccm.service.IMainEmployeeService;
import com.cn.bccm.service.IMainRoleService;
import com.cn.bccm.service.IUserService;

@Service
@Transactional
public class MainRoleService implements IMainRoleService  {
	@Autowired
	private IMainRoleDao roleDao;

	public void deleteRole(MainRole role) {
		roleDao.delete(role);
		
	}

	public MainRole getRole(int id) {
		return roleDao.get(id);
	}

	public List<MainRole> list(Object[] params) {
		return roleDao.list("from MainRole", params);
	}

	public List<MainRole> listByPage(String hql, int pageNo, int pageSize,
			Map<String, Object> params) {
		return roleDao.listByPage(hql, pageNo, pageSize, params);
	}

	public void saveOrUpdateRole(MainRole role) throws Exception {
		roleDao.saveOrUpdate(role);
	}
	
	public  Page<MainRole> listByPage(Page<MainRole> page, String hql, Map<String, Object> params) throws Exception {
		page.setTotalCount(roleDao.findCount("select count(*) "+hql, params));
		return roleDao.listByPage(page, hql, params);
	}
	
	public MainRole getRoleByLogin(String username, String password) {
		List<MainRole> list= roleDao.list("from MainRole e where e.empUserName=? and e.empPassword=? ", new Object[]{username,password});
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}
