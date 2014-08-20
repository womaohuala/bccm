package com.cn.bccm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.bccm.dao.ICoCompanyDao;
import com.cn.bccm.dao.IUserDao;
import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.CoopCompany;
import com.cn.bccm.model.User;
import com.cn.bccm.service.ICoCompanyService;
import com.cn.bccm.service.IUserService;

@Service
@Transactional
public class CoCompanyService implements ICoCompanyService {
	@Autowired
	private ICoCompanyDao companyDao;

	public List<CoopCompany> list(Object[] params) {
		return companyDao.list("from CoopCompany", params);
	}

	public void saveOrUpdateCompany(CoopCompany company) throws Exception{
		companyDao.saveOrUpdate(company);
	}

	public void deleteCompany(CoopCompany company) {
		companyDao.delete(company);
	}
	
	public CoopCompany getCompany(int id){
		return  companyDao.get(id);
	}

	public List<CoopCompany> listByPage(String hql, int pageNo,int pageSize,Map<String,Object> params){
		return companyDao.listByPage(hql, pageNo, pageSize, params);
	}

	public List<CoopCompany> listAllCompany() {
		return companyDao.getAll();
	}

	/* 
	 * 2014-7-26 wangjl
	 */
	public Page<CoopCompany> listByPage(Page<CoopCompany> page, String hql,
			Map<String, Object> params) throws Exception {
		page.setTotalCount(companyDao.findCount("select count(*) "+hql, params));
		return companyDao.listByPage(page, hql, params);
	}
	
	
	
	

	
}
