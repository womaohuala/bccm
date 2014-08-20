package com.cn.bccm.dao.impl;

import org.springframework.stereotype.Repository;

import com.cn.bccm.dao.IMainPlanDao;
import com.cn.bccm.dao.base.BaseHibernateDAO;
import com.cn.bccm.model.MainPlan;

@Repository
public class MainPlanDao extends BaseHibernateDAO<MainPlan, Integer> implements IMainPlanDao {

}