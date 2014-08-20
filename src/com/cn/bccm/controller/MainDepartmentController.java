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
import org.springframework.web.servlet.ModelAndView;
import com.cn.bccm.common.constant.Constant;
import com.cn.bccm.common.util.CommonUtil;
import com.cn.bccm.common.util.DateUtil;
import com.cn.bccm.common.util.ValidateUtil;
import com.cn.bccm.dao.ICoCompanyDao;
import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.CoopCompany;
import com.cn.bccm.model.CoopProject;
import com.cn.bccm.model.MainDepartment;
import com.cn.bccm.model.MainEmployee;
import com.cn.bccm.model.MainPlan;
import com.cn.bccm.service.ICoCompanyService;
import com.cn.bccm.service.IMainDepartmentService;
import com.cn.bccm.util.ConstantValues;



@Controller
@RequestMapping("/department")
public class MainDepartmentController {
	private static Log log = LogFactory.getLog(MainDepartmentController.class);
	
	@Autowired
	private IMainDepartmentService departmentService;

	
	@RequestMapping("index")
	public String index(HttpServletRequest request) throws Exception{
		String hql="from MainDepartment where 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		String deptName = request.getParameter("deptName");
		if(StringUtils.isNotBlank(deptName)){
			deptName = CommonUtil.getUtf8Code(deptName);
			hql+=" and deptName like '%"+deptName+"%'";
		}
		
		Page<MainDepartment> page = new Page<MainDepartment>();
		String pageNoStr=request.getParameter("pageNo");
		
		int pageNo=1;
		try{
			pageNo=Integer.parseInt(pageNoStr);
		}catch(Exception ex){
			
		}
		page.setPageNo(pageNo);
		page.setPageSize(Constant.PAGE_SIZE);
		request.setAttribute("objPage", departmentService.listByPage(page, hql, params));
		request.setAttribute("deptName", deptName);
		return "department/department";
	}
	
	@RequestMapping("querydepartment")
	public String querydepartment(HttpServletRequest request){
		List<MainDepartment> list = departmentService.list(new Object[]{});
		request.setAttribute("list", list);
		return "department/department";
	}
	
	@RequestMapping("departmentadd")
	public String add(HttpServletRequest request){
		return "department/departmentadd";
	}
	
	@RequestMapping("departmentedit")
	public String edit(HttpServletRequest request){
		String id = request.getParameter("id");
		MainDepartment department = departmentService.getDepartment(Integer.parseInt(id));
		request.setAttribute("department", department);
		return "department/departmentedit";
	}
	
	@RequestMapping("departmentdetail")
	public String detail(HttpServletRequest request){
		String id = request.getParameter("id");
		MainDepartment department = departmentService.getDepartment(Integer.parseInt(id));
		request.setAttribute("department", department);
		return "department/departmentdetail";
	}
	
	@RequestMapping("departmentsave")
	@ResponseBody
	public Result<String> save(HttpServletRequest request) throws Exception{
		Result<String> result = new Result<String>();
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String deptInfo = request.getParameter("deptInfo");
		String deptHead = request.getParameter("deptHead");
		String deptPhone = request.getParameter("deptPhone");
		
		//CoopCompany company = new CoopCompany();
		MainDepartment department = new MainDepartment();
		ValidateUtil valid = new ValidateUtil();
		if(StringUtils.isNotBlank(deptId)){
			try{
				department.setDeptId(Integer.parseInt(deptId));
				//company.setCompId(Integer.parseInt(comPid));
			}catch(Exception ex){
				result.setResultInfo("comPid 有误");
				return result;
			}
		}
		if(StringUtils.isBlank(deptName)){
			result.setResultInfo("部门名称不能为空");
			return result;
		}
		department.setDeptName(deptName);
		if(StringUtils.isBlank(deptInfo)){
			result.setResultInfo("部门简介不能为空");
			return result;
		}
		department.setDeptInfo(deptInfo);
		if(StringUtils.isBlank(deptHead)){
			result.setResultInfo("部门负责人不能为空");
			return result;
		}
		department.setDeptHead(deptHead);
		if(StringUtils.isBlank(deptPhone)){
			result.setResultInfo("部门联系电话");
			return result;
		}
		if(!valid.validateMobilePhone(deptPhone)){
			result.setResultInfo("电话号码不正确");
			return result;
		}
		department.setDeptPhone(deptPhone);
		departmentService.saveOrUpdateDepartment(department);
		result.setResult(true);
		if(StringUtils.isBlank(deptId)){
			result.setResultInfo("添加成功");
		}else{
			result.setResultInfo("修改成功");
		}
		return result;
	}
	
	
	
	@RequestMapping("delete")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		MainDepartment department = departmentService.getDepartment(Integer.parseInt(id));
		departmentService.deleteDepartment(department);
		try {
			response.sendRedirect("index.shtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
