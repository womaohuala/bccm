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
import com.cn.bccm.dao.IUserDao;
import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.CoopCompany;
import com.cn.bccm.model.MainDepartment;
import com.cn.bccm.model.MainEmployee;
import com.cn.bccm.model.User;
import com.cn.bccm.service.ICoCompanyService;
import com.cn.bccm.service.IMainDepartmentService;
import com.cn.bccm.service.IUserService;

@Service
@Transactional
public class MainDepartmentService implements IMainDepartmentService  {
	@Autowired
	private IMainDepartmentDao departmentDao;

	public List<MainDepartment> list(Object[] params) {
		return departmentDao.list("from MainDepartment", params);
	}

	public void saveOrUpdateDepartment(MainDepartment department) throws Exception{
		departmentDao.saveOrUpdate(department);
	}

	public void deleteDepartment(MainDepartment department) {
		departmentDao.delete(department);
	}
	
	public MainDepartment getDepartment(int id){
		return  departmentDao.get(id);
	}

	public List<MainDepartment> listByPage(String hql, int pageNo,int pageSize,Map<String,Object> params){
		return departmentDao.listByPage(hql, pageNo, pageSize, params);
	}

	public List<MainDepartment> listAll() {
		return departmentDao.getAll();
	}

	/* 
	 * 2014-7-26 wangjl
	 */
	public Page<MainDepartment> listByPage(Page<MainDepartment> page,
			String hql, Map<String, Object> params) throws Exception {
		page.setTotalCount(departmentDao.findCount("select count(*) "+hql, params));
		return departmentDao.listByPage(page, hql, params);
	}
	
	
	
	

	
}
