package com.cn.bccm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cn.bccm.dao.IMainPlanExecDao;
import com.cn.bccm.model.MainPlan;
import com.cn.bccm.model.MainPlanExec;
import com.cn.bccm.service.IMainPlanExecService;

@Service
@Transactional
public class MainPlanExecService implements IMainPlanExecService {

	@Autowired
	private IMainPlanExecDao execDao;

	public void saveMainPlan(MainPlanExec exec) throws Exception {
		execDao.saveOrUpdate(exec);
	}

	public MainPlanExec getPlanExec(int execId) {
		return execDao.get(execId);
	}

	public List<MainPlanExec> getMainPlanExecByPlanId(int planId) {
		MainPlan plan = new MainPlan();
		plan.setPlanId(planId);
		return execDao.list(" from MainPlanExec e where e.plan=?", plan);
	}

	
	
	
	
}
