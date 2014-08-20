package com.cn.bccm.service;

import java.util.List;
import java.util.Map;

import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.CoopCompany;
import com.cn.bccm.model.CoopProject;
import com.cn.bccm.model.MainEmployee;
import com.cn.bccm.model.User;

public interface ICoProjectService {
	public List<CoopProject> list(Object[] params);
	public void saveOrUpdateProject(CoopProject project)throws Exception;
	public void deleteProject(CoopProject project);
	public CoopProject getProject(int id);
	public List<CoopProject> listByPage(String hql, int pageNo,int pageSize,Map<String,Object> params);

	public  Page<CoopProject> listByPage(Page<CoopProject> page, String hql, Map<String, Object> params) throws Exception;
}
