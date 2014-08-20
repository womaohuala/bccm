package com.cn.bccm.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.bccm.common.util.JBPMUtil;
import com.cn.bccm.dao.ILeaveDao;
import com.cn.bccm.dao.IMainEmployeeDao;
import com.cn.bccm.dao.IStaffDao;
import com.cn.bccm.model.Leave;
import com.cn.bccm.model.MainEmployee;
import com.cn.bccm.model.Staff;
import com.cn.bccm.service.JBPMService;


@Service
@Transactional
public class JBPMServiceImpl implements JBPMService {
	@Autowired
	private JBPMUtil jBPMUtil;
	@Autowired
	private ILeaveDao leaveDAO;
	@Autowired
	private IStaffDao staffDAO;
	@Autowired
	private IMainEmployeeDao employeeDao;
	
	
	public JBPMUtil getjBPMUtil() {
		return jBPMUtil;
	}

	public void setjBPMUtil(JBPMUtil jBPMUtil) {
		this.jBPMUtil = jBPMUtil;
	}

	public ILeaveDao getLeaveDAO() {
		return leaveDAO;
	}

	public void setLeaveDAO(ILeaveDao leaveDAO) {
		this.leaveDAO = leaveDAO;
	}
	
	public IStaffDao getStaffDAO() {
		return staffDAO;
	}

	public void setStaffDAO(IStaffDao staffDAO) {
		this.staffDAO = staffDAO;
	}
	
	//发布流程
	public String  deployNew(String resourceName) {
		return jBPMUtil.deployNew(resourceName);
	}
	

	//发布流程
	public String  deployZipNew(String resourceZipName) {
		return jBPMUtil.deployZipNew(resourceZipName);
	}

	//开始流程实例
	public ProcessInstance startPI(String staffName,String id) {
		Map map=new HashMap();
		map.put("staff", staffName);
		return jBPMUtil.startPIById(id, map);
		
	}

	//完成任务
	public void completeTask(String taskId) {
		
		jBPMUtil.completeTask(taskId);
	}
	
	//完成任务
	public void completeTask(String taskId, String result) {
		jBPMUtil.completeTask(taskId, result);	
	}

	

	//添加请假信息进入库表
	public void applyLeave(String staffName, String leaveLong,
			String leaveContent,String leaveInstanceId) {
		/*
		String queryString="from Staff where staffName='"+staffName+"'";
		Staff staff = staffDAO.list(queryString, new HashMap<String, Object>()).get(0);
		*/
		String queryString = " from MainEmployee where empName='"+staffName+"'";
		MainEmployee employee = employeeDao.list(queryString, new HashMap<String, Object>()).get(0);
		//Staff staff =staffDAO.findStaff(queryString).get(0);
		Leave leave=new Leave();
		leave.setLeaveContent(leaveContent);
		leave.setLeaveLong(leaveLong);
		//leave.setStaff(staff);
		leave.setEmployee(employee);
		leave.setLeaveState("审核中");
		leave.setLeaveInstanceId(leaveInstanceId);
		leaveDAO.save(leave);
		//leaveDAO.saveLeave(leave);
	}
	//更新请假信息的流程状态
	public void updateLeave(Leave leave,String result) {		
		
		if(leave.getLeaveState()=="审核中"&&result=="批准"){
			leave.setLeaveState("经理审核通过");
		}else if(leave.getLeaveState()=="经理审核通过"&&result=="批准"){
			leave.setLeaveState("老板审核通过");
		}
		leaveDAO.update(leave);
		//leaveDAO.updateLeave(leave);
	}
	
	//被驳回的请假信息重新填写
	public void updateLeave(Leave leave,String leaveLong,String leaveContent) {		
		leave.setLeaveLong(leaveLong);
		leave.setLeaveContent(leaveContent);
		leaveDAO.update(leave);
		//leaveDAO.updateLeave(leave);
	}

	public String getTaskId(String staffName) {
		return jBPMUtil.findPersonalTasks(staffName).get(0).getId();
	}

	public List<Task> getTasksList(String assignee) {
		
		return jBPMUtil.findPersonalTasks(assignee);
	}

	public List<Leave> getLeaveDetail(String taskId) {
		String leaveInstanceId=jBPMUtil.getTaskService().getTask(taskId).getExecutionId();
		System.out.println("leaveInstanceId="+leaveInstanceId);
		String queryString="from Leave where leaveInstanceId='"+leaveInstanceId+"'";
		return leaveDAO.list(queryString, new HashMap<String, Object>());
		//return leaveDAO.findLeaves(queryString);
	}

	public List<ProcessDefinition> getAllPd() {
		return jBPMUtil.getAllPdList();
	}

	public void completeTask(String taskId, String leaveLong,
			String leaveContent) {
		Map map=new HashMap();
		map.put("leaveLong", leaveLong);
		map.put("leaveContent", leaveContent);
		jBPMUtil.completeTask(taskId, map);
	}

	public InputStream findInstancePic(String instanceId) {
		return jBPMUtil.findProcessInstancePic(instanceId);
	}

	

	
	
	
	
	
	
	
}
