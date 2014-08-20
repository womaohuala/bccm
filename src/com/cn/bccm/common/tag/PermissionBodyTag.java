package com.cn.bccm.common.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.PageContext;

import com.cn.bccm.common.constant.Constant;
import com.cn.bccm.common.webContext.ServiceLocator;
import com.cn.bccm.model.MainPermission;
import com.cn.bccm.service.IMainPermissionService;
import com.cn.bccm.util.StringUtil;
import javax.servlet.jsp.PageContext;

public class PermissionBodyTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String permissionId;
	
	private IMainPermissionService mps=ServiceLocator.getBean(IMainPermissionService.class);

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public int doStartTag() throws JspTagException {
		// @SuppressWarnings("unchecked")
		// List<String> permsList = (List<String>) pageContext.getSession()
		// .getAttribute(COMMON_CONSTANT.SESSION_USER_RIGHT);
		System.out.println("ddsfsdfs%%%%5");
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();//获取页面Servlet中 request 和out 对象
		String permissions=request.getSession().getAttribute(Constant.SESSION_PERMISSION).toString();
		if (hasPermission(permissions,permissionId)) {
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}

	private boolean hasPermission(String permissions,String permissionId) {
		// TODO Auto-generated method stub
		if(permissions.indexOf(","+permissionId+",")>-1){
			return true;
		}
		return false;
	}

}
