package com.cn.bccm.dao.impl;

import org.springframework.stereotype.Repository;

import com.cn.bccm.dao.IUserDao;
import com.cn.bccm.dao.base.BaseHibernateDAO;
import com.cn.bccm.model.User;

@Repository
public class UserDao extends BaseHibernateDAO<User, Integer> implements IUserDao {


}