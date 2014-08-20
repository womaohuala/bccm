package com.cn.bccm.service;


import java.util.Map;
import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.MainPlan;

public interface IMainPlanService {
	public void saveMainPlan(MainPlan plan)throws Exception;
	public MainPlan getMainPlan(int planId);
	public  Page<MainPlan> listByPage(Page<MainPlan> page, String hql, Map<String, Object> params) throws Exception;
	
}
