package com.cn.bccm.service;

import java.util.List;
import java.util.Map;

import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.CoopCompany;
import com.cn.bccm.model.CoopProject;
import com.cn.bccm.model.MainEmployee;
import com.cn.bccm.model.MainEmployeeRole;
import com.cn.bccm.model.MainPlan;
import com.cn.bccm.model.User;

public interface IMainEmployeeRoleService   {
	public List<MainEmployeeRole> list(Object[] params);
	public void saveOrUpdateMainEmployeeRole(MainEmployeeRole employee)throws Exception;
	public void deleteMainEmployeeRole(MainEmployeeRole employeeRole);
	public MainEmployeeRole getMainEmployeeRole(int id);
	public List<MainEmployeeRole> listByPage(String hql, int pageNo,int pageSize,Map<String,Object> params);
	
	public  Page<MainEmployeeRole> listByPage(Page<MainEmployeeRole> page, String hql, Map<String, Object> params) throws Exception;
	
	public int deleteByEmp(String roleId, Integer empId);
}
