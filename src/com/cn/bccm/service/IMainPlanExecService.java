package com.cn.bccm.service;


import java.util.List;

import com.cn.bccm.model.MainPlanExec;

public interface IMainPlanExecService {
	public void saveMainPlan(MainPlanExec exec)throws Exception;
	
	public MainPlanExec getPlanExec(int execId);
	
	public List<MainPlanExec> getMainPlanExecByPlanId(int planId);
}
