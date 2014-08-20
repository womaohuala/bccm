package com.cn.bccm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("/detail")
public class DetailAction {
	//private String taskId;
	private List<Leave> leaveList;
	@Autowired
	protected JBPMService jBPMService;
	
	@RequestMapping("index")
	public String execute(HttpServletRequest request ){
		String taskId = request.getParameter("taskId");
		leaveList=jBPMService.getLeaveDetail(taskId);		//根据任务Id从库表获取相应的记录详细情况
		request.setAttribute("leaveList", leaveList);
		return "jbpm/taskDetail";
	}
	
	@RequestMapping("reRequest")
	public String reRequest(HttpServletRequest request ){
		String taskId = request.getParameter("taskId");
		leaveList=jBPMService.getLeaveDetail(taskId);
		request.setAttribute("leaveList", leaveList);
		return "jbpm/index";
		//return "redetail";
	}


	public List<Leave> getLeaveList() {
		return leaveList;
	}

	public void setLeaveList(List<Leave> leaveList) {
		this.leaveList = leaveList;
	}
}
