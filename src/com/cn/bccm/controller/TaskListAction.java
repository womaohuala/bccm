package com.cn.bccm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.bccm.service.JBPMService;

/**
 * 
 * @author ht 
 * 	2010 10 20
 *
 */
@Controller
@RequestMapping("/taskList")
public class TaskListAction {
	@Autowired
	protected JBPMService jBPMService;

	//private String assignee;
	 
	private List taskList;
	 
	@RequestMapping("getTasks")
	public String getTasks(HttpServletRequest request){
		String staffName = (String) request.getSession().getAttribute("staffName");
		String assignee = request.getParameter("assignee");
		if(assignee.equals("staff")){	//用户身份
			taskList=jBPMService.getTasksList(staffName);
			if(taskList.size()==0){		//如果没有任务,跳转到请求页面
				return "request";
			}else{					//如果有被驳回的任务，跳转到原请求列表页面
				return "requestList";
			}
		}
		taskList=jBPMService.getTasksList(assignee);
		request.setAttribute("taskList", taskList);
		return "jbpm/taskList";
		//return "tasks";
	}

	
	public List getTaskList() {
		return taskList;
	}

	public void setTaskList(List taskList) {
		this.taskList = taskList;
	}


	


	
}
