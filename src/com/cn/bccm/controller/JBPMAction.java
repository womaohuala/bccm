package com.cn.bccm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.bccm.common.constant.Constant;
import com.cn.bccm.model.Leave;
import com.cn.bccm.model.MainEmployee;
import com.cn.bccm.service.JBPMService;
import com.cn.bccm.service.LoginService;
/**
 * 
 * @author ht 
 * 	2010 10 20
 *
 */
@Controller
@RequestMapping("/jbpm")
public class JBPMAction {
	private final static String resourceName="leave.jpdl.xml";	
	//private String taskId;
	private List<Leave> leaveList;
	@Autowired
	protected JBPMService jBPMService;
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("detailAction")
	public String detail(HttpServletRequest request ){
		String taskId = request.getParameter("taskId");
		leaveList=jBPMService.getLeaveDetail(taskId);		//根据任务Id从库表获取相应的记录详细情况
		request.setAttribute("leaveList", leaveList);
		request.setAttribute("taskId", taskId);
		return "jbpm/taskDetail";
	}
	
	@RequestMapping("reRequest")
	public String reRequest(HttpServletRequest request ){
		String taskId = request.getParameter("taskId");
		leaveList=jBPMService.getLeaveDetail(taskId);
		request.setAttribute("leaveList", leaveList);
		request.setAttribute("taskId", taskId);
		return "jbpm/index";
		//return "redetail";
	}
	
	@RequestMapping("exam")
	public void exam(HttpServletRequest request,HttpServletResponse response){
		String taskId = request.getParameter("taskId");
		String result = request.getParameter("result");
		Leave leave=jBPMService.getLeaveDetail(taskId).get(0);	//需要修改请假表中状态的数据，需要获得该记录
		jBPMService.updateLeave(leave, result);				//更新
		jBPMService.completeTask(taskId, result);	//完成任务，进入下一个流程
		try {
			response.sendRedirect("getTasks.shtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return "jbpm/index";
		//return "index";
	}
	
	@RequestMapping("loginIn")
	public String loginIn(HttpServletRequest request){
		String staffName = request.getParameter("staffName");
		String staffPsw = request.getParameter("staffPsw");
		if(loginService.isLogin(staffName, staffPsw)){
			//ActionContext.getContext().getSession().put("staffName", staffName);	//保存当前用户
			request.getSession().setAttribute("staffName", staffName);
			return "jbpm/index";
			//return "index";
		}
			return "jbpm/login";
			//return "login";
	}
	
	@RequestMapping("request")
	public void request(HttpServletRequest request,HttpServletResponse response ){
		if(jBPMService.getAllPd().size()==0){	//如果还没有流程定义则发布
			jBPMService.deployNew(resourceName);
		}
		String pdId=jBPMService.getAllPd().get(0).getId();	//获得第一个Id
		//String staffName = (String) request.getSession().getAttribute("staffName");
		String staffName = ((MainEmployee)request.getSession().getAttribute(Constant.SESSION_USER)).getempName();
		String leaveLong = request.getParameter("leaveLong");
		String leaveContent = request.getParameter("leaveContent");
		ProcessInstance processInstance=jBPMService.startPI(staffName, pdId);		//开始流程实例
		jBPMService.applyLeave(staffName, leaveLong, leaveContent,processInstance.getId());	//添加进入数据库
		jBPMService.completeTask(jBPMService.getTaskId(staffName),leaveLong,leaveContent);	//完成任务
		try {
			response.sendRedirect("getTasks.shtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return "jbpm/index";
		//return "index";
	}
	
	//处理被驳回的请求
	@RequestMapping("reRequestRefuse")
	public String reRequestRefuse(HttpServletRequest request){
		String taskId = request.getParameter("taskId");
		String leaveLong = request.getParameter("leaveLong");
		String leaveContent = request.getParameter("leaveContent");
		Leave leave=jBPMService.getLeaveDetail(taskId).get(0);
		jBPMService.updateLeave(leave,leaveLong,leaveContent);
		jBPMService.completeTask(taskId);
		return "jbpm/index";		
		//return "index";
	}
	
	@RequestMapping("getTasks")
	public String getTasks(HttpServletRequest request){
		List<Task> taskList = new ArrayList<Task>();
		String staffName = ((MainEmployee)request.getSession().getAttribute(Constant.SESSION_USER)).getempName();
		String assignee = request.getParameter("assignee");
		if(assignee==null||assignee.equals("staff")){	//用户身份
			taskList=jBPMService.getTasksList(staffName);
			if(taskList.size()==0){		//如果没有任务,跳转到请求页面
				return "jbpm/request";
			}else{					//如果有被驳回的任务，跳转到原请求列表页面
				request.setAttribute("taskList", taskList);
				return "jbpm/requestList";
			}
		}
		taskList=jBPMService.getTasksList(assignee);
		request.setAttribute("taskList", taskList);
		return "jbpm/taskList";
		//return "tasks";
	}

	public List<Leave> getLeaveList() {
		return leaveList;
	}

	public void setLeaveList(List<Leave> leaveList) {
		this.leaveList = leaveList;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	
}
