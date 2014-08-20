package com.cn.bccm.common.constant;

public class Constant {
	public static final String SESSION_USER = "session_user";
	public static final String CONTEXT_PATH="contextPath";
	public static       String CONTEXT_REALPATH_VALUE="/";
	
	public static final int PAGE_SIZE = 10;
	
	public static class PersonDuty{
		/**
		 * 0-管理员，1-部门管理员，2-普通人员
		 */
		public static final int MANAGER=0;
		public static final int DEPARTMENT_MANAGER=1;
		public static final int COMMON=2;
		
	}
	

}
