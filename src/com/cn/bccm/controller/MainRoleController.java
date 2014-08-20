package com.cn.bccm.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cn.bccm.common.constant.Constant;
import com.cn.bccm.common.util.CommonUtil;
import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.MainRole;
import com.cn.bccm.service.IMainRoleService;



@Controller
@RequestMapping("/role")
public class MainRoleController {
	private static Log log = LogFactory.getLog(MainRoleController.class);
	
	@Autowired
	private IMainRoleService roleService;

	
	@RequestMapping("index")
	public String index(HttpServletRequest request) throws Exception{
		String hql="from MainRole where 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		String roleName = request.getParameter("roleName");
		if(StringUtils.isNotBlank(roleName)){
			roleName = CommonUtil.getUtf8Code(roleName);
			hql+=" and roleName like '%"+roleName+"%'";
		}
		
		Page<MainRole> page = new Page<MainRole>();
		String pageNoStr=request.getParameter("pageNo");
		
		int pageNo=1;
		try{
			pageNo=Integer.parseInt(pageNoStr);
		}catch(Exception ex){
			
		}
		page.setPageNo(pageNo);
		page.setPageSize(Constant.PAGE_SIZE);
		request.setAttribute("objPage", roleService.listByPage(page, hql, params));
		request.setAttribute("roleName", roleName);
		return "jsp/role/rolelist";
	}
	
}
