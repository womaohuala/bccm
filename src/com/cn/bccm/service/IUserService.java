package com.cn.bccm.service;

import java.util.List;

import com.cn.bccm.model.User;

public interface IUserService {
	public List<User> list();
	public void saveUser(User user)throws Exception;
}
