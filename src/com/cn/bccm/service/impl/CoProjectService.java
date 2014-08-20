package com.cn.bccm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.bccm.dao.ICoCompanyDao;
import com.cn.bccm.dao.ICoProjectDao;
import com.cn.bccm.dao.IUserDao;
import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.CoopCompany;
import com.cn.bccm.model.CoopProject;
import com.cn.bccm.model.User;
import com.cn.bccm.service.ICoCompanyService;
import com.cn.bccm.service.ICoProjectService;
import com.cn.bccm.service.IUserService;

@Service
@Transactional
public class CoProjectService implements ICoProjectService {
	@Autowired
	private ICoProjectDao projectDao;

	public List<CoopProject> list(Object[] params) {
		return projectDao.list("from CoopProject", params);
	}

	public void saveOrUpdateProject(CoopProject project) throws Exception{
		projectDao.saveOrUpdate(project);
	}

	public void deleteProject(CoopProject project) {
		projectDao.delete(project);
	}

	public List<CoopProject> listByPage(String hql, int pageNo,int pageSize,Map<String,Object> params){
		return projectDao.listByPage(hql, pageNo, pageSize, params);
	}

	public CoopProject getProject(int id) {
		return projectDao.get(id);
	}

	public Page<CoopProject> listByPage(Page<CoopProject> page, String hql,
			Map<String, Object> params) throws Exception {
		page.setTotalCount(projectDao.findCount("select count(*) "+hql, params));
		return projectDao.listByPage(page, hql, params);
	}
	
	


	
}
