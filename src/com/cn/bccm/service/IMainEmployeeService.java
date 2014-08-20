package com.cn.bccm.service;

import java.util.List;
import java.util.Map;

import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.CoopCompany;
import com.cn.bccm.model.CoopProject;
import com.cn.bccm.model.MainEmployee;
import com.cn.bccm.model.MainPlan;
import com.cn.bccm.model.User;

public interface IMainEmployeeService   {
	public List<MainEmployee> list(Object[] params);
	public void saveOrUpdateEmployee(MainEmployee employee)throws Exception;
	public void deleteEmployee(MainEmployee employee);
	public MainEmployee getEmployee(int id);
	public List<MainEmployee> listByPage(String hql, int pageNo,int pageSize,Map<String,Object> params);
	
	public  Page<MainEmployee> listByPage(Page<MainEmployee> page, String hql, Map<String, Object> params) throws Exception;
	public MainEmployee getEmployeeByLogin(String username,String password);
}
