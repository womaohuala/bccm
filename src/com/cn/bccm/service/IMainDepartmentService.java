package com.cn.bccm.service;

import java.util.List;
import java.util.Map;

import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.CoopCompany;
import com.cn.bccm.model.CoopProject;
import com.cn.bccm.model.MainDepartment;
import com.cn.bccm.model.MainEmployee;
import com.cn.bccm.model.User;

public interface IMainDepartmentService   {
	public List<MainDepartment> list(Object[] params);
	public void saveOrUpdateDepartment(MainDepartment department)throws Exception;
	public void deleteDepartment(MainDepartment department);
	public MainDepartment getDepartment(int id);
	public List<MainDepartment> listByPage(String hql, int pageNo,int pageSize,Map<String,Object> params);
	public List<MainDepartment> listAll();
	
	public  Page<MainDepartment> listByPage(Page<MainDepartment> page, String hql, Map<String, Object> params) throws Exception;
}
