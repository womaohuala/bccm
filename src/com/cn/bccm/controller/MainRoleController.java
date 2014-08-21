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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.bccm.common.constant.Constant;
import com.cn.bccm.common.util.CommonUtil;
import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.MainPermission;
import com.cn.bccm.model.MainRole;
import com.cn.bccm.service.IMainPermissionService;
import com.cn.bccm.service.IMainRoleService;
import com.cn.bccm.util.StringUtil;

@Controller
@RequestMapping("/role")
public class MainRoleController {
	private static Log log = LogFactory.getLog(MainRoleController.class);

	@Autowired
	private IMainRoleService roleService;
	@Autowired
	private IMainPermissionService permissionService;

	@RequestMapping("index")
	public String index(HttpServletRequest request) throws Exception {
		String hql = "from MainRole where 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		String roleName = request.getParameter("roleName");
		if (StringUtils.isNotBlank(roleName)) {
			roleName = CommonUtil.getUtf8Code(roleName);
			hql += " and roleName like '%" + roleName + "%'";
		}

		Page<MainRole> page = new Page<MainRole>();
		String pageNoStr = request.getParameter("pageNo");

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception ex) {

		}
		page.setPageNo(pageNo);
		page.setPageSize(Constant.PAGE_SIZE);
		request.setAttribute("objPage", roleService.listByPage(page, hql,
				params));
		request.setAttribute("roleName", roleName);
		return "jsp/role/rolelist";
	}

	/**
	 * 添加角色界面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	public String detail(HttpServletRequest request) throws Exception {
		String id=request.getParameter("id");
		MainRole role= roleService.getRole(Integer.parseInt(id));
		
		List<MainPermission> perList=permissionService.list(new Object[]{});
		request.setAttribute("perList", perList);
		request.setAttribute("role", role);
		return "jsp/role/roledetail";
	}
	
	/**
	 * 添加角色界面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add")
	public String add(HttpServletRequest request) throws Exception {
		List<MainPermission> perList=permissionService.list(new Object[]{});
		request.setAttribute("perList", perList);
		return "jsp/role/rolesave";
	}
	/**
	 * 修改角色界面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	public String edit(HttpServletRequest request) throws Exception {
		String id=request.getParameter("id");
		MainRole role= roleService.getRole(Integer.parseInt(id));
		
		List<MainPermission> perList=permissionService.list(new Object[]{});
		request.setAttribute("perList", perList);
		request.setAttribute("role", role);
		return "jsp/role/rolesave";
	}
	/**
	 * 保存
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public Result<String> save(HttpServletRequest request) throws Exception {
		Result<String> result = new Result<String>();
		MainRole role;
		try {
			String roleId=request.getParameter("roleId");
			String roleName=request.getParameter("roleName");
			String roleInfo=request.getParameter("roleInfo");
			String rolePer=request.getParameter("rolePer");
			if(StringUtil.isNotEmpty(roleId)){
				role = roleService.getRole(Integer.parseInt(roleId));
				
			}else{
				role =new MainRole();
			}
			
			role.setRoleName(roleName);
			role.setRoleInfo(roleInfo);
			role.setRolePer(rolePer);
			roleService.saveOrUpdateRole(role);
			result.setResult(true);
			if(StringUtils.isBlank(roleId)){
				result.setResultInfo("添加成功");
			}else{
				result.setResultInfo("修改成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setResult(false);
			result.setResultInfo("操作失败");
		}
		return result;
	}
	/**
	 * 删除角色
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Result<String> delete(HttpServletRequest request) throws Exception {
		Result<String> result = new Result<String>();
		
		try {
			String id=request.getParameter("id");
			MainRole role= roleService.getRole(Integer.parseInt(id));
			roleService.deleteRole(role);
			result.setResult(true);
			result.setResultInfo("删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			// TODO: handle exception
			e.printStackTrace();
			result.setResult(false);
			result.setResultInfo("操作失败");
		}
		return result;
	}

}
