package com.cn.bccm.controller;



import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.bccm.common.constant.Constant;
import com.cn.bccm.model.MainEmployee;
import com.cn.bccm.service.IMainEmployeeService;


@Controller
@RequestMapping("/")
public class IndexController {
	private static Log log = LogFactory.getLog(IndexController.class);
	
	@Autowired
	private IMainEmployeeService employeeService;
	@RequestMapping("index")
	public String index(HttpServletRequest request){
		request.setAttribute("sss", "ddddd");
		return "frame";
	}
	
	@RequestMapping("left")
	public String left(HttpServletRequest request){
		return "left";
	}
	
	@RequestMapping("head")
	public String head(HttpServletRequest request){
		return "head";
	}
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute(Constant.SESSION_USER);
		return "redirect:login.html";
	}
	
	
	@RequestMapping("login")
	@ResponseBody
	public Result<String> login(HttpServletRequest request){
		Result<String> result = new Result<String>();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		if(StringUtils.isBlank(username)){
			result.setResultInfo("用户名不能为空！");
			return result;
		}
		
		if(StringUtils.isBlank(password)){
			result.setResultInfo("密码不能为空！");
			return result;
		}
		MainEmployee employee=employeeService.getEmployeeByLogin(username, password);
		if(employee==null){
			result.setResultInfo("用户名和密码不存在！");
			return result;
		}
		request.getSession().setAttribute(Constant.SESSION_USER, employee);
		result.setResult(true);
		result.setResultInfo("登录成功！");
		result.setObject("index.shtml");
		return result;
	}
}
