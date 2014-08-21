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
import com.cn.bccm.dao.IUserDao;
import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.CoopCompany;
import com.cn.bccm.model.MainDepartment;
import com.cn.bccm.model.MainEmployee;
import com.cn.bccm.model.MainPlan;
import com.cn.bccm.model.User;
import com.cn.bccm.service.IMainDepartmentService;
import com.cn.bccm.service.IMainEmployeeService;
import com.cn.bccm.service.IUserService;

@Service
@Transactional
public class MainEmployeeService implements IMainEmployeeService  {
	@Autowired
	private IMainEmployeeDao employeeDao;

	public void deleteEmployee(MainEmployee employee) {
		employeeDao.delete(employee);
		
	}

	public MainEmployee getEmployee(int id) {
		return employeeDao.get(id);
	}

	public List<MainEmployee> list(Object[] params) {
		return employeeDao.list("from MainEmployee", params);
	}
	
	public List<MainEmployee> listByPage(String hql, int pageNo, int pageSize,
			Map<String, Object> params) {
		return employeeDao.listByPage(hql, pageNo, pageSize, params);
	}

	public void saveOrUpdateEmployee(MainEmployee employee) throws Exception {
		employeeDao.saveOrUpdate(employee);
	}
	
	public  Page<MainEmployee> listByPage(Page<MainEmployee> page, String hql, Map<String, Object> params) throws Exception {
		page.setTotalCount(employeeDao.findCount("select count(*) "+hql, params));
		return employeeDao.listByPage(page, hql, params);
	}
	
	public MainEmployee getEmployeeByLogin(String username, String password) {
		List<MainEmployee> list= employeeDao.list("from MainEmployee e where e.empUserName=? and e.empPassword=? ", new Object[]{username,password});
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	


	
}
