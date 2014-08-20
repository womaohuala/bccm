package com.cn.bccm.controller;

import javax.servlet.http.HttpServletRequest;

import org.jbpm.api.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.bccm.model.Leave;
import com.cn.bccm.service.JBPMService;

/**
 * 
 * @author ht 
 * 	2010 10 20
 *
 */
@Controller
@RequestMapping("/request")
public class RequestAction  {
	private final static String resourceName="leave.jpdl.xml";		
	/*
	private String taskId;
	private String leaveLong;
	private String leaveContent;
	private String leaveInstanceId;
	*/
	@Autowired
	protected JBPMService jBPMService;
	@RequestMapping("index")
	public String execute(HttpServletRequest request ){
		if(jBPMService.getAllPd().size()==0){	//如果还没有流程定义则发布
			jBPMService.deployNew(resourceName);
		}
		String pdId=jBPMService.getAllPd().get(0).getId();	//获得第一个Id
		String staffName = (String) request.getSession().getAttribute("staffName");
		String leaveLong = request.getParameter("leaveLong");
		String leaveContent = request.getParameter("leaveContent");
		ProcessInstance processInstance=jBPMService.startPI(staffName, pdId);		//开始流程实例
		jBPMService.applyLeave(staffName, leaveLong, leaveContent,processInstance.getId());	//添加进入数据库
		jBPMService.completeTask(jBPMService.getTaskId(staffName),leaveLong,leaveContent);	//完成任务
		return "jbpm/index";
		//return "index";
	}
	
	//处理被驳回的请求
	@RequestMapping("reRequest")
	public String reRequest(HttpServletRequest request){
		String taskId = request.getParameter("taskId");
		String leaveLong = request.getParameter("leaveLong");
		String leaveContent = request.getParameter("leaveContent");
		Leave leave=jBPMService.getLeaveDetail(taskId).get(0);
		jBPMService.updateLeave(leave,leaveLong,leaveContent);
		jBPMService.completeTask(taskId);
		return "jbpm/index";		
		//return "index";
	}
	
	

}
