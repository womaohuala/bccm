package com.cn.bccm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.bccm.dao.IUserDao;
import com.cn.bccm.model.User;
import com.cn.bccm.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService {
	
	@Autowired
	private IUserDao userDao;

	public List<User> list() {
		return userDao.list("from User", new Object[]{});
	}

	public void saveUser(User user) throws Exception{
		userDao.saveOrUpdate(user);
		Random r = new Random();
		if(true){
			throw new  Exception();
		}
		userDao.saveOrUpdate(user);
		
	}

}
