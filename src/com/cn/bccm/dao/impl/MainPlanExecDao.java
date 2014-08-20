package com.cn.bccm.dao.impl;

import org.springframework.stereotype.Repository;

import com.cn.bccm.dao.IMainPlanExecDao;
import com.cn.bccm.dao.base.BaseHibernateDAO;
import com.cn.bccm.model.MainPlanExec;

@Repository
public class MainPlanExecDao extends BaseHibernateDAO<MainPlanExec, Integer> implements IMainPlanExecDao {

}