package com.cn.bccm.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.bccm.dao.IStaffDao;
import com.cn.bccm.model.Staff;
import com.cn.bccm.service.LoginService;


@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private IStaffDao staffDAO;
	
	public boolean isLogin(String staffName, String staffPsw) {
		String queryString ="from Staff as model where model.staffName='"+staffName+"' and model.staffPsw='"+staffPsw+"'";
		List<Staff> staffs = staffDAO.list(queryString, new HashMap<String, Object>());
		if(staffs.size()==1){
			return true;
		}else{
			return false;
		}
		//return staffDAO.findStaff(queryString).size()==1?true:false;
	}
	
	public IStaffDao getStaffDAO() {
		return staffDAO;
	}
	public void setStaffDAO(IStaffDao staffDAO) {
		this.staffDAO = staffDAO;
	}

}
