package com.cn.bccm.controller;


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
@RequestMapping("/exam")
public class ExamAction  {
	//private String taskId;
	//private String result;
	@Autowired
	protected JBPMService jBPMService;
	@RequestMapping("index")
	public String execute(HttpServletRequest request){
		String taskId = request.getParameter("taskId");
		String result = request.getParameter("result");
		Leave leave=jBPMService.getLeaveDetail(taskId).get(0);	//需要修改请假表中状态的数据，需要获得该记录
		jBPMService.updateLeave(leave, result);				//更新
		jBPMService.completeTask(taskId, result);	//完成任务，进入下一个流程
		return "jbpm/index";
		//return "index";
	}
	
}
