package com.cn.bccm.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cn.bccm.dao.IMainPlanDao;
import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.MainPlan;
import com.cn.bccm.service.IMainPlanService;

@Service
@Transactional
public class MainPlanService implements IMainPlanService {

	@Autowired
	private IMainPlanDao mainPlanDao;

	public void saveMainPlan(MainPlan plan) throws Exception {
		mainPlanDao.saveOrUpdate(plan);
	}
	
	public  Page<MainPlan> listByPage(Page<MainPlan> page, String hql, Map<String, Object> params) throws Exception {
		page.setTotalCount(mainPlanDao.findCount("select count(*) "+hql, params));
		return mainPlanDao.listByPage(page, hql, params);
	}

	public MainPlan getMainPlan(int planId) {
		return mainPlanDao.get(planId);
	}
	
	
	
	
}
