package com.cn.bccm.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cn.bccm.common.constant.Constant;
import com.cn.bccm.model.MainPermission;
import com.cn.bccm.service.IMainPermissionService;
import com.cn.bccm.util.StringUtil;
/**
 * 权限验证
 * @author hux
 *
 */

public class SessionInterceptor implements HandlerInterceptor {
	
//	private IMainPermissionService mps=(MainPermissionService)ServiceLocator.getBean("MainPermissionService");
	@Autowired
	private IMainPermissionService mps;
	
	private String[] uncheckUrl;

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		try {
			if(request.getRequestURI().endsWith("login.shtml")){
				 return true;
			 }
			//获取用户信息
			Object user=request.getSession().getAttribute(Constant.SESSION_USER);
			//获取权限信息
			String permissions=request.getSession().getAttribute(Constant.SESSION_PERMISSION).toString();
			
			if(StringUtil.isEmpty(user)){
				response.sendRedirect(request.getContextPath()+"/login.html");
				return false;
			}else if(!checkUrl(permissions,request.getServletPath())){
				System.out.println(request.getServletPath()+"没有权限");
//				response.sendRedirect(request.getContextPath()+"/notAuth.jsp");
//				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.sendRedirect(request.getContextPath()+"/login.html");
			return false;
		}
		return true;
	}
	
	/**
	 * 验证请求URL
	 * @param permissions
	 * @param requestURI
	 * @return
	 */
	private boolean checkUrl(String permissions, String requestURI) {
		// TODO Auto-generated method stub
		int perid=0;//权限ID
		if(isCheck(requestURI)){
			return true;
		}
		MainPermission mainPermission=mps.getByAction(requestURI);
//		System.out.println(requestURI);
		if(StringUtil.isNotEmpty(mainPermission)){
			perid=mainPermission.getPerId();
		}
		if(permissions.contains(","+perid+",")){
			return true;
		}
		return false;
	}

	/**
	 * 无需过滤的URL
	 * @param permissions
	 * @param requestURI
	 * @return
	 */

	private boolean isCheck(String requestURI) {
		// TODO Auto-generated method stub
		for(String url:uncheckUrl){
			if(url.equals(requestURI)){
				return true;
			}
		}
		return false;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//System.out.println("postHandle");
	}
	
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//System.out.println("afterCompletion");
	}

	public void setUncheckUrl(String[] uncheckUrl) {
		this.uncheckUrl = uncheckUrl;
	}

	public String[] getUncheckUrl() {
		return uncheckUrl;
	}

	

}
