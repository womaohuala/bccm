package com.cn.bccm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.bccm.common.constant.Constant;
import com.cn.bccm.service.LoginService;

/**
 * 
 * @author ht 
 * 	2010 10 20
 *
 */
@Controller
@RequestMapping("/login")
public class LoginAction{
	//private String staffName;
	//private String staffPsw;
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("loginIn")
	public String loginIn(HttpServletRequest request){
		String staffName = request.getParameter("staffName");
		String staffPsw = request.getParameter("staffPsw");
		System.out.println("ddddd");
		if(loginService.isLogin(staffName, staffPsw)){
			//ActionContext.getContext().getSession().put("staffName", staffName);	//保存当前用户
			request.getSession().setAttribute("staffName", staffName);
			return "jbpm/index";
			//return "index";
		}
			return "jbpm/login";
			//return "login";
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
