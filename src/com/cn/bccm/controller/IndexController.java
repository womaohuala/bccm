package com.cn.bccm.controller;



import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.bccm.common.constant.Constant;
import com.cn.bccm.model.MainEmployee;
import com.cn.bccm.model.MainEmployeeRole;
import com.cn.bccm.model.MainRole;
import com.cn.bccm.service.IMainEmployeeService;
import com.cn.bccm.util.StringUtil;


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
	public Result<String> login(HttpServletRequest request,HttpSession session){
		Result<String> result = new Result<String>();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String roleper="";//用户权限组
		if(StringUtils.isBlank(username)){
			result.setResultInfo("用户名不能为空！");
			return result;
		}
		
		if(StringUtils.isBlank(password)){
			result.setResultInfo("密码不能为空！");
			return result;
		}
		MainEmployee employee=employeeService.getEmployeeByLogin(username, password);
		
		//解析用户权限
		Set<MainEmployeeRole> rs=employee.getEmployRoles();
		Iterator ite=rs.iterator();
		while(ite.hasNext()){
			MainEmployeeRole me=(MainEmployeeRole)ite.next();
			MainRole role=me.getRole();
			roleper+=role.getRolePer();//多个角色叠加
		}
		
		if(StringUtil.isEmpty(employee)){
			result.setResultInfo("用户名和密码不存在！");
			return result;
		}
		
		request.getSession().setAttribute(Constant.SESSION_USER, employee);
		request.getSession().setAttribute(Constant.SESSION_PERMISSION, roleper);
		result.setResult(true);
		result.setResultInfo("登录成功！");
		result.setObject("index.shtml");
		return result;
	}
}
