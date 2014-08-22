package com.cn.bccm.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.bccm.common.constant.Constant;
import com.cn.bccm.common.util.CommonUtil;
import com.cn.bccm.common.util.JBPMUtil;
import com.cn.bccm.dao.base.Page;
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
	
	@Autowired
	private JBPMUtil jbpmUtil;
	
	@RequestMapping("detail")
	public String detail(HttpServletRequest request ){
		String taskId = request.getParameter("taskId");
		String proInstanceId = jbpmUtil.getTask(taskId).getExecutionId();
		leaveList=jBPMService.getLeaveDetail(taskId);		//根据任务Id从库表获取相应的记录详细情况
		request.setAttribute("leaveList", leaveList);
		request.setAttribute("leave", leaveList.get(0));
		request.setAttribute("taskId", taskId);
		request.setAttribute("proInstanceId", proInstanceId);
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
	@ResponseBody
	public Result<String> exam(HttpServletRequest request,HttpServletResponse response){
		Result<String> result = new Result<String>();
		String taskId = request.getParameter("taskId");
		String resultStr = request.getParameter("result");
		Leave leave=jBPMService.getLeaveDetail(taskId).get(0);	//需要修改请假表中状态的数据，需要获得该记录
		jBPMService.updateLeave(leave, resultStr);				//更新
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("boss", "boss");
		String executionId = jBPMService.getExectionIdByTaskId(taskId);
		jBPMService.setVariable(executionId, "boss", "9");
		//jBPMService.completeTask(taskId, map);
		jBPMService.completeTask(taskId, resultStr);	//完成任务，进入下一个流程
		result.setResult(true);
		result.setResultInfo("提交成功");
		return result;
		/*
		try {
			response.sendRedirect("getTasks.shtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
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
	
	@RequestMapping("requestBegin")
	public String requestBegin(HttpServletRequest request){
		return "jbpm/request";
	}
	
	
	@RequestMapping("request")
	@ResponseBody
	public Result<String> request(HttpServletRequest request,HttpServletResponse response ){
		Result<String> result = new Result<String>();
		if(jBPMService.getAllPd().size()==0){	//如果还没有流程定义则发布
			jBPMService.deployNew(resourceName);
		}
		String pdId=jBPMService.getAllPd().get(0).getId();	//获得第一个Id
		//String staffName = (String) request.getSession().getAttribute("staffName");
		//String staffName = ((MainEmployee)request.getSession().getAttribute(Constant.SESSION_USER)).getempName();
		String staffName = String.valueOf(((MainEmployee)request.getSession().getAttribute(Constant.SESSION_USER)).getEmpId());
		String leaveLong = request.getParameter("leaveLong");
		if(StringUtils.isBlank(leaveLong)){
			result.setResultInfo("请假时间长度不能为空");
			return result;
		}
		String leaveContent = request.getParameter("leaveContent");
		if(StringUtils.isBlank(leaveContent)){
			result.setResultInfo("请假原因不能为空");
			return result;
		}
		
		ProcessInstance processInstance=jBPMService.startPI(staffName, pdId);		//开始流程实例
		jBPMService.applyLeave(staffName, leaveLong, leaveContent,processInstance.getId());	//添加进入数据库
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("leaveLong", leaveLong);
		map.put("leaveContent", leaveContent);
		map.put("manager", "8");
		jBPMService.completeTask(jBPMService.getTaskId(staffName), map);
		result.setResult(true);
		result.setResultInfo("提交成功");
		return result;
		/*
		try {
			response.sendRedirect("getTasks.shtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
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
		String staffName = String.valueOf(((MainEmployee)request.getSession().getAttribute(Constant.SESSION_USER)).getEmpId());
		//String assignee = request.getParameter("assignee");
		/*
		if(assignee==null||assignee.equals(staffName)){	//用户身份
			taskList=jBPMService.getTasksList(staffName);
			if(taskList.size()==0){		//如果没有任务,跳转到请求页面
				return "jbpm/request";
			}else{					//如果有被驳回的任务，跳转到原请求列表页面
				request.setAttribute("taskList", taskList);
				return "jbpm/requestList";
			}
		}
		*/
		taskList = jBPMService.getTasksList(staffName);
		//taskList=jBPMService.getTasksList(assignee);
		//request.setAttribute("taskList", taskList);
		
		Page<Task> page = new Page<Task>();
		String pageNoStr=request.getParameter("pageNo");
		int pageNo=1;
		try{
			pageNo=Integer.parseInt(pageNoStr);
		}catch(Exception ex){
			
		}
		page.setTotalCount(taskList.size());
		page.setPageNo(pageNo);
		page.setPageSize(Constant.PAGE_SIZE);
		List<Task> lastList = CommonUtil.getTasksByPage(taskList, pageNo, Constant.PAGE_SIZE);
		page.setPageData(lastList);
		request.setAttribute("objPage", page);
		return "jbpm/taskList";
	}
	
	
	@RequestMapping("getPic")
	 public void pic(HttpServletRequest request, HttpServletResponse response) {  
		    String proInstanceId = request.getParameter("proInstanceId");
	        InputStream inputStream = jbpmUtil.findProcessInstancePic(proInstanceId);  
	        PrintWriter pw = null;  
	        if (inputStream == null) {  
	            try {  
	                pw = response.getWriter();  
	                pw.write("error");  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            } finally {  
	                pw.close();  
	            }  
	        } else {  
	            byte[] b = new byte[1024];  
	            int len = -1;  
	            ServletOutputStream sos = null;  
	            try {  
	                sos = response.getOutputStream();  
	                while ((len = inputStream.read(b, 0, 1024)) != -1) {  
	                    sos.write(b, 0, len);  
	                }  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            } finally {  
	                if (sos != null) {  
	                    try {  
	                        sos.close();  
	                    } catch (IOException e) {  
	                        e.printStackTrace();  
	                    }  
	                }  
	            }  
	        }  
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
