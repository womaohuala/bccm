package com.cn.bccm.service.impl;

import java.sql.Connection;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.IdentityService;
import org.jbpm.api.ManagementService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.cmd.Command;

import com.cn.bccm.service.IprocessEngine;

public class MyProcessEngine implements IprocessEngine{

	public void close() {
		// TODO Auto-generated method stub
		
	}

	public <T> T execute(Command<T> command) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T get(Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public ExecutionService getExecutionService() {
		// TODO Auto-generated method stub
		return null;
	}

	public HistoryService getHistoryService() {
		// TODO Auto-generated method stub
		return null;
	}

	public IdentityService getIdentityService() {
		// TODO Auto-generated method stub
		return null;
	}

	public ManagementService getManagementService() {
		// TODO Auto-generated method stub
		return null;
	}

	public RepositoryService getRepositoryService() {
		// TODO Auto-generated method stub
		return null;
	}

	public TaskService getTaskService() {
		// TODO Auto-generated method stub
		return null;
	}

	public ProcessEngine setAuthenticatedUserId(String authenticatedUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ProcessEngine setHibernateSession(Object hibernateSession) {
		// TODO Auto-generated method stub
		return null;
	}

	public ProcessEngine setJdbcConnection(Connection jdbcConnection) {
		// TODO Auto-generated method stub
		return null;
	}

}
