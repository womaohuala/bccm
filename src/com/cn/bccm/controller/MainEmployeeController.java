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
import com.cn.bccm.service.ICoProjectService;
import com.cn.bccm.service.IMainDepartmentService;
import com.cn.bccm.service.IMainEmployeeService;
import com.cn.bccm.util.ConstantValues;



@Controller
@RequestMapping("/employee")
public class MainEmployeeController {
	private static Log log = LogFactory.getLog(MainEmployeeController.class);
	
	@Autowired
	private IMainDepartmentService departmentService;

	@Autowired
	private IMainEmployeeService employeeService;

	
	@RequestMapping("index")
	public String index(HttpServletRequest request) throws Exception{
		String hql="from MainEmployee where 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		String empSex = request.getParameter("empSex");
		String empName = request.getParameter("empName");
		if(StringUtils.isNotBlank(empSex)){
			hql+=" and empSex=:empSex ";
			params.put("empSex", Short.parseShort(empSex));
		}
		if(StringUtils.isNotBlank(empName)){
			empName = CommonUtil.getUtf8Code(empName);
			hql+=" and empName like '%"+empName+"%'";
		}
		
		Page<MainEmployee> page = new Page<MainEmployee>();
		String pageNoStr=request.getParameter("pageNo");
		
		int pageNo=1;
		try{
			pageNo=Integer.parseInt(pageNoStr);
		}catch(Exception ex){
			
		}
		page.setPageNo(pageNo);
		page.setPageSize(Constant.PAGE_SIZE);
		request.setAttribute("objPage", employeeService.listByPage(page, hql, params));
		request.setAttribute("empSex", empSex);
		request.setAttribute("empName", empName);
		return "employee/employee";
	}
	
	@RequestMapping("queryemployee")
	public String querycompany(HttpServletRequest request){
		//List<CoopProject> list = projectService.list(new Object[]{});
		List<MainEmployee> list = employeeService.list(new Object[]{});
		request.setAttribute("list", list);
		return "employee/employee";
	}
	
	@RequestMapping("employeeadd")
	public String add(HttpServletRequest request){
		List<MainDepartment> deptList = departmentService.list(new Object[]{});
		request.setAttribute("deptList", deptList);
		return "employee/employeeadd";
	}
	
	@RequestMapping("employeeedit")
	public String edit(HttpServletRequest request){
		String id = request.getParameter("id");
		MainEmployee employee = employeeService.getEmployee(Integer.parseInt(id));
		request.setAttribute("employee", employee);
		List<MainDepartment> deptList = departmentService.list(new Object[]{});
		request.setAttribute("deptList", deptList);
		return "employee/employeeedit";
	}
	
	@RequestMapping("employeedetail")
	public String detail(HttpServletRequest request){
		String id = request.getParameter("id");
		MainEmployee employee = employeeService.getEmployee(Integer.parseInt(id));
		request.setAttribute("employee", employee);
		List<MainDepartment> deptList = departmentService.list(new Object[]{});
		request.setAttribute("deptList", deptList);
		return "employee/employeedetail";
	}
	
	@RequestMapping("employeesave")
	@ResponseBody
	public Result<String> save(HttpServletRequest request) throws Exception{
		MainEmployee employee = new MainEmployee();
		Result<String> result = new Result<String>();
		String empId = request.getParameter("empId");
		String deptId = request.getParameter("department");
		if(StringUtils.isBlank(deptId)){
			result.setResultInfo("部门名称不能为空");
			return result;
		}
		MainDepartment department = departmentService.getDepartment(Integer.parseInt(deptId));
		employee.setDepartment(department);
		String empName = request.getParameter("empName");
		String empJob = request.getParameter("empJob");
		String empSex = request.getParameter("empSex");
		String empPhone = request.getParameter("empPhone");
		String empRemark = request.getParameter("empRemark");
		String empUserName = request.getParameter("empUserName");
		String empPassword = request.getParameter("empPassword");
		
		
		ValidateUtil valid = new ValidateUtil();
		if(StringUtils.isNotBlank(empId)){
			try{
				employee.setEmpId(Integer.parseInt(empId));
			}catch(Exception ex){
				result.setResultInfo("empId有误");
				return result;
			}
		}
		
		if(StringUtils.isBlank(empName)){
			result.setResultInfo("职员名称不能为空");
			return result;
		}
		employee.setEmpName(empName);
		if(StringUtils.isBlank(empJob)){
			result.setResultInfo("职员只能不能为空");
			return result;
		}
		employee.setEmpJob(empJob);
		if(StringUtils.isBlank(empSex)){
			result.setResultInfo("职员性别不能为空");
			return result;
		}
		employee.setEmpSex(Short.parseShort(empSex));
		if(StringUtils.isBlank(empPhone)){
			result.setResultInfo("职员联系号码不能为空");
			return result;
		}
		
		if(!valid.validateMobilePhone(empPhone)){
			result.setResultInfo("职员联系号码不正确");
			return result;
		}
		employee.setEmpPhone(empPhone);
		if(StringUtils.isBlank(empRemark)){
			result.setResultInfo("备注不能为空");
			return result;
		}
		employee.setEmpRemark(empRemark);
		
		if(StringUtils.isBlank(empUserName)){
			result.setResultInfo("登陆用户名不能为空");
			return result;
		}
		employee.setEmpUserName(empUserName);
		
		if(StringUtils.isBlank(empPassword)){
			result.setResultInfo("登陆密码不能为空");
			return result;
		}
		employee.setEmpPassword(empPassword);
		employeeService.saveOrUpdateEmployee(employee);
		result.setResult(true);
		if(StringUtils.isBlank(empId)){
			result.setResultInfo("添加成功");
		}else{
			result.setResultInfo("修改成功");
		}
		return result;
	}
	
	
	
	@RequestMapping("delete")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		MainEmployee employee = employeeService.getEmployee(Integer.parseInt(id));
		employeeService.deleteEmployee(employee);
		try {
			response.sendRedirect("index.shtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
