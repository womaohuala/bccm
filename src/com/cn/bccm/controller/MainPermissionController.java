package com.cn.bccm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.cn.bccm.service.IMainPermissionService;
import com.cn.bccm.util.StringUtil;

@Controller
@RequestMapping("/permission")
public class MainPermissionController {
	private static Log log = LogFactory.getLog(MainPermissionController.class);

	@Autowired
	private IMainPermissionService permissionService;

	@RequestMapping("index")
	public String index(HttpServletRequest request) throws Exception {
		String hql = "from MainPermission where 1=1 and perParent=0 ";
		Map<String, Object> params = new HashMap<String, Object>();
		String permissionName = request.getParameter("permissionName");
		if (StringUtils.isNotBlank(permissionName)) {
			permissionName = CommonUtil.getUtf8Code(permissionName);
			hql += " and permissionName like '%" + permissionName + "%'";
		}

		Page<MainPermission> page = new Page<MainPermission>();
		String pageNoStr = request.getParameter("pageNo");

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception ex) {

		}
		page.setPageNo(pageNo);
		page.setPageSize(Constant.PAGE_SIZE);
		request.setAttribute("objPage", permissionService.listByPage(page, hql,
				params));
		request.setAttribute("permissionName", permissionName);
		return "jsp/permission/permodual";
	}

	/**
	 * 查看模块具体权限
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detailmodual")
	public String detailmodual(HttpServletRequest request) throws Exception {
		String id=request.getParameter("id");
		String hql = "from MainPermission where 1=1 and perParent="+id;
		Map<String, Object> params = new HashMap<String, Object>();
		String permissionName = request.getParameter("permissionName");
		if (StringUtils.isNotBlank(permissionName)) {
			permissionName = CommonUtil.getUtf8Code(permissionName);
			hql += " and permissionName like '%" + permissionName + "%'";
		}
		
		Page<MainPermission> page = new Page<MainPermission>();
		String pageNoStr = request.getParameter("pageNo");
		
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception ex) {
			
		}
		page.setPageNo(pageNo);
		page.setPageSize(Constant.PAGE_SIZE);
		request.setAttribute("objPage", permissionService.listByPage(page, hql,
				params));
		request.setAttribute("perParent", id);
		return "jsp/permission/perlist";
	}
	
	/**
	 * 查看权限详细
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	public String detail(HttpServletRequest request) throws Exception {
		String id=request.getParameter("id");
		MainPermission permission= permissionService.getPermission(Integer.parseInt(id));
		
		request.setAttribute("per", permission);
		return "jsp/permission/perdetail";
	}
	
	/**
	 * 添加模块界面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addmodual")
	public String addmodual(HttpServletRequest request) throws Exception {
		return "jsp/permission/permodualsave";
	}
	/**
	 * 修改模块界面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editmodual")
	public String editmodual(HttpServletRequest request) throws Exception {
		String id=request.getParameter("id");
		MainPermission permission= permissionService.getPermission(Integer.parseInt(id));
		request.setAttribute("permission", permission);
		return "jsp/permission/permodualsave";
	}
	/**
	 * 添加权限界面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add")
	public String add(HttpServletRequest request) throws Exception {
		String perParent=request.getParameter("perParent");
		List<MainPermission> perList=permissionService.list(new Object[]{});
		request.setAttribute("perList", perList);
		request.setAttribute("perParent", perParent);
		return "jsp/permission/persave";
	}
	/**
	 * 修改权限界面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	public String edit(HttpServletRequest request) throws Exception {
		String id=request.getParameter("id");
		MainPermission permission= permissionService.getPermission(Integer.parseInt(id));
		
		request.setAttribute("per", permission);
		request.setAttribute("perParent", permission.getPerParent());
		return "jsp/permission/persave";
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
		MainPermission permission;
		try {
			String perId=request.getParameter("perId");
			String perName=request.getParameter("perName");
			String perInfo=request.getParameter("perInfo");
			String perParent=request.getParameter("perParent");
			String perAction=request.getParameter("perAction");
			String type=request.getParameter("type");
			if(StringUtil.isNotEmpty(perId)){
				permission = permissionService.getPermission(Integer.parseInt(perId));
				
			}else{
				permission =new MainPermission();
			}
			
			permission.setPerName(perName);
			permission.setPerInfo(perInfo);
			if(type.equals("1")){
				permission.setPerAction(perAction);
				permission.setPerParent(Integer.parseInt(perParent));
			}else{
				permission.setPerAction("");
				permission.setPerParent(0);
			}
			permissionService.saveOrUpdatePermission(permission);
			result.setResult(true);
			if(StringUtils.isBlank(perId)){
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
	 * 删除权限
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
			MainPermission permission= permissionService.getPermission(Integer.parseInt(id));
			permissionService.deletePermission(permission);
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
	/**
	 * 删除模块权限
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deletemodual")
	@ResponseBody
	public Result<String> deletemodual(HttpServletRequest request) throws Exception {
		Result<String> result = new Result<String>();
		
		try {
			String id=request.getParameter("id");
			int num=permissionService.deleteByParent(id);
			if(num>=0){
				MainPermission permission= permissionService.getPermission(Integer.parseInt(id));
				permissionService.deletePermission(permission);
			}
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
