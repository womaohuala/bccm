package com.cn.bccm.dao.impl;

import org.springframework.stereotype.Repository;

import com.cn.bccm.dao.ICoCompanyDao;
import com.cn.bccm.dao.ICoProjectDao;
import com.cn.bccm.dao.IMainDepartmentDao;
import com.cn.bccm.dao.IMainEmployeeDao;
import com.cn.bccm.dao.IMainRoleDao;
import com.cn.bccm.dao.IUserDao;
import com.cn.bccm.dao.base.BaseHibernateDAO;
import com.cn.bccm.model.CoopCompany;
import com.cn.bccm.model.CoopProject;
import com.cn.bccm.model.MainDepartment;
import com.cn.bccm.model.MainEmployee;
import com.cn.bccm.model.MainRole;
import com.cn.bccm.model.User;

@Repository
public class MainEmployeeDao extends BaseHibernateDAO<MainEmployee, Integer> implements IMainEmployeeDao {


}