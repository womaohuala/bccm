package com.cn.bccm.dao.impl;

import org.springframework.stereotype.Repository;

import com.cn.bccm.dao.ICoCompanyDao;
import com.cn.bccm.dao.ICoProjectDao;
import com.cn.bccm.dao.IUserDao;
import com.cn.bccm.dao.base.BaseHibernateDAO;
import com.cn.bccm.model.CoopCompany;
import com.cn.bccm.model.CoopProject;
import com.cn.bccm.model.User;

@Repository
public class CoProjectDao extends BaseHibernateDAO<CoopProject, Integer> implements ICoProjectDao {


}