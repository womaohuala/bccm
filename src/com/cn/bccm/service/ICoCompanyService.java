package com.cn.bccm.service;

import java.util.List;
import java.util.Map;

import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.CoopCompany;
import com.cn.bccm.model.MainDepartment;

public interface ICoCompanyService {
	public List<CoopCompany> list(Object[] params);
	public void saveOrUpdateCompany(CoopCompany company)throws Exception;
	public void deleteCompany(CoopCompany company);
	public CoopCompany getCompany(int id);
	public List<CoopCompany> listByPage(String hql, int pageNo,int pageSize,Map<String,Object> params);
	
	public List<CoopCompany> listAllCompany();
	
	public  Page<CoopCompany> listByPage(Page<CoopCompany> page, String hql, Map<String, Object> params) throws Exception;
}
