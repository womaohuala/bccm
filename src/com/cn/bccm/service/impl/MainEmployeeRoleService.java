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
import com.cn.bccm.dao.IMainEmployeeRoleDao;
import com.cn.bccm.dao.IUserDao;
import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.CoopCompany;
import com.cn.bccm.model.MainDepartment;
import com.cn.bccm.model.MainEmployee;
import com.cn.bccm.model.MainEmployeeRole;
import com.cn.bccm.model.MainPlan;
import com.cn.bccm.model.User;
import com.cn.bccm.service.IMainDepartmentService;
import com.cn.bccm.service.IMainEmployeeRoleService;
import com.cn.bccm.service.IMainEmployeeService;
import com.cn.bccm.service.IUserService;

@Service
@Transactional
public class MainEmployeeRoleService implements IMainEmployeeRoleService  {
	@Autowired
	private IMainEmployeeRoleDao employeeRoleDao;

	public void deleteMainEmployeeRole(MainEmployeeRole employeeRole) {
		employeeRoleDao.delete(employeeRole);
		
	}

	public MainEmployeeRole getMainEmployeeRole(int id) {
		return employeeRoleDao.get(id);
	}

	public List<MainEmployeeRole> list(Object[] params) {
		return employeeRoleDao.list("from MainEmployeeRole", params);
	}

	public List<MainEmployeeRole> listByPage(String hql, int pageNo, int pageSize,
			Map<String, Object> params) {
		return employeeRoleDao.listByPage(hql, pageNo, pageSize, params);
	}

	public void saveOrUpdateMainEmployeeRole(MainEmployeeRole employeeRole) throws Exception {
		employeeRoleDao.saveOrUpdate(employeeRole);
	}
	
	public  Page<MainEmployeeRole> listByPage(Page<MainEmployeeRole> page, String hql, Map<String, Object> params) throws Exception {
		page.setTotalCount(employeeRoleDao.findCount("select count(*) "+hql, params));
		return employeeRoleDao.listByPage(page, hql, params);
	}

	public int deleteByEmp(String roleId, Integer empId) {
		// TODO Auto-generated method stub
		return employeeRoleDao.executeUpdate("delete from MainEmployeeRole where employee.empId=? ", new Object[]{empId});
	}
	
	


	
}
