package com.cn.bccm.controller;



import java.util.Date;
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
import com.cn.bccm.common.util.DateUtil;
import com.cn.bccm.common.util.ValidateUtil;
import com.cn.bccm.common.webContext.ServiceLocator;
import com.cn.bccm.dao.IMainDepartmentDao;
import com.cn.bccm.dao.base.Page;
import com.cn.bccm.model.CoopCompany;
import com.cn.bccm.model.CoopProject;
import com.cn.bccm.model.MainDepartment;
import com.cn.bccm.model.MainEmployee;
import com.cn.bccm.model.MainPlan;
import com.cn.bccm.model.MainPlanExec;
import com.cn.bccm.service.ICoCompanyService;
import com.cn.bccm.service.IMainDepartmentService;
import com.cn.bccm.service.IMainPlanExecService;
import com.cn.bccm.service.IMainPlanService;


@Controller
@RequestMapping("/plan")
public class PlanController {
	private static Log log = LogFactory.getLog(PlanController.class);
	
	@Autowired
	private IMainPlanService planService;
	
	@Autowired
	private IMainPlanExecService execService;
	
	/**
	 * 查询计划
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("planlist")
	public String index(HttpServletRequest request)throws Exception{
		Page<MainPlan> page = new Page<MainPlan>();
		ValidateUtil valid = new ValidateUtil();
		String pageNoStr=request.getParameter("pageNo");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String planName = request.getParameter("planName");
		if(StringUtils.isNotBlank(planName)){
			planName=CommonUtil.getUtf8Code(planName);
		}
		
		int pageNo=1;
		try{
			pageNo=Integer.parseInt(pageNoStr);
		}catch(Exception ex){
			
		}
		page.setPageNo(pageNo);
		page.setPageSize(Constant.PAGE_SIZE);
		 Map<String, Object> params = new HashMap<String, Object>();
		String hql="from MainPlan p where 0=0 ";
		if(StringUtils.isNotBlank(startTime)){
			if(valid.isValidDate(startTime,"yyyy-MM-dd")){
				hql=hql+" and p.planStartTime >= :startTime";
				params.put("startTime", DateUtil.fmtStrTime(startTime, "yyyy-MM-dd"));
			}
		}
		
		if(StringUtils.isNotBlank(endTime)){
			if(valid.isValidDate(endTime,"yyyy-MM-dd")){
				hql=hql+" and p.planEndTime <= :endTime";
				params.put("endTime", DateUtil.fmtStrTime(endTime, "yyyy-MM-dd"));
			}
		}
		
		if(StringUtils.isNotBlank(planName)){
			planName=planName.trim();
			hql=hql+" and p.planName like :planName";
			params.put("planName", "%"+planName+"%");
		}
		
		request.setAttribute("objPage", planService.listByPage(page, hql, params));
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("planName", planName);
		
		return "plan/planlist";
	}
	
	/**
	 * 添加计划
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("planAddUpdate")
	public String planAddorUpdate(HttpServletRequest request)throws Exception{
		ICoCompanyService companyService = ServiceLocator.getBean("coCompanyService",ICoCompanyService.class);
		IMainDepartmentService departmentService = ServiceLocator.getBean("mainDepartmentService",IMainDepartmentService.class);
		String planId = request.getParameter("planId");
	    List<CoopCompany> comList=companyService.listAllCompany();
		List<MainDepartment> deptList=departmentService.listAll();
		request.setAttribute("comList", comList);
		request.setAttribute("deptList", deptList);
		
		ValidateUtil valid = new ValidateUtil();
			if(valid.validateNum(planId)){
				MainPlan plan=	planService.getMainPlan(Integer.parseInt(planId));
				if(plan!=null){
					request.setAttribute("plan", plan);
				}
			}
			
		return "plan/planadd";
	}
	
	
	/**
	 * 添加计划
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("planDetail")
	public String planDetail(HttpServletRequest request)throws Exception{
		String planId = request.getParameter("planId");
		ValidateUtil valid = new ValidateUtil();
		if(valid.validateNum(planId)){
			MainPlan plan=	planService.getMainPlan(Integer.parseInt(planId));
			if(plan!=null){
				request.setAttribute("plan", plan);
				List<MainPlanExec> execs =execService.getMainPlanExecByPlanId(Integer.parseInt(planId));
				request.setAttribute("execs", execs);
			}
		}
		return "plan/planDetail";
	}
	
	/**
	 * 保存计划
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveplan")
	@ResponseBody
	public Result<String> saveplan(HttpServletRequest request)throws Exception{
		 Result<String> result = new Result<String>();
		String planId = request.getParameter("planId");
		String planName = request.getParameter("planName");
		String planInfo = request.getParameter("planInfo");
		String department = request.getParameter("department");
		String employee = request.getParameter("employee");
		String company = request.getParameter("company");
		String project = request.getParameter("project");
		String planStartTime = request.getParameter("planStartTime").trim();
		String planEndTime = request.getParameter("planEndTime").trim();
		String planPrime = request.getParameter("planPrime");
		String planCost = request.getParameter("planCost");
		String planSale = request.getParameter("planSale");
		String planRemark=request.getParameter("planRemark");
		
		MainPlan plan = new MainPlan();
		ValidateUtil valid = new ValidateUtil();
		if(StringUtils.isNotBlank(planId)){
			try{
				plan.setPlanId(Integer.parseInt(planId));
			}catch(Exception ex){
				result.setResultInfo("planId 有误");
				return result;
			}
		}
		if(StringUtils.isBlank(planName)){
			result.setResultInfo("计划名称不能为空");
			return result;
		}
		plan.setPlanName(planName);
		if(StringUtils.isBlank(planInfo)){
			result.setResultInfo("计划内容不能为空");
			return result;
		}
		plan.setPlanInfo(planInfo);
		
		if(StringUtils.isBlank(employee)){
			result.setResultInfo("未选择负责人");
			return result;
		}
		MainEmployee emp = new MainEmployee();
		emp.setEmpId(Integer.parseInt(employee));
		plan.setEmployee(emp);
		
		if(StringUtils.isBlank(project)){
			result.setResultInfo("未选择项目");
			return result;
		}
		CoopProject pro = new CoopProject();
		pro.setProId(Integer.parseInt(project));
		plan.setProject(pro);
		if(!valid.isValidDate(planStartTime,"yyyy-MM-dd")){
			result.setResultInfo("计划开始时间格式不正确 格式：yyyy-MM-dd");
			return result;
		}
		plan.setPlanStartTime(DateUtil.fmtStrTime(planStartTime, "yyyy-MM-dd"));
		if(!valid.isValidDate(planEndTime,"yyyy-MM-dd")){
			result.setResultInfo("计划结束时间不能为空：yyyy-MM-dd");
			return result;
		}
		plan.setPlanEndTime(DateUtil.fmtStrTime(planEndTime, "yyyy-MM-dd"));
		
		if(!valid.validateNum(planPrime)){
			result.setResultInfo("计划成本格式错误 必须为数字");
			return result;
		}
		plan.setPlanPrime(Integer.parseInt(planPrime));
		
		if(!valid.validateNum(planCost)){
			result.setResultInfo("计划耗资格式错误 必须为数字");
			return result;
		}
		plan.setPlanCost(Integer.parseInt(planCost));
		
		if(!valid.validateNum(planSale)){
			result.setResultInfo("计划销售额格式错误 必须为数字");
			return result;
		}
		plan.setPlanSale(Integer.parseInt(planSale));
		plan.setPlanRemark(planRemark);
		
		MainEmployee user = (MainEmployee)request.getSession().getAttribute(Constant.SESSION_USER);
		plan.setPlanCreateEmp(user.getEmpId());
		plan.setPlanCreateTime(new Date());
		planService.saveMainPlan(plan);
		result.setResult(true);
		if(StringUtils.isBlank(planId)){
			result.setResultInfo("添加成功");
		}else{
			result.setResultInfo("修改成功");
		}
		return result;
	}
	
	
	/**
	 * 添加计划
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("execAddUpdate")
	public String execAddUpdate(HttpServletRequest request)throws Exception{
		ICoCompanyService companyService = ServiceLocator.getBean("coCompanyService",ICoCompanyService.class);
		IMainDepartmentService departmentService = ServiceLocator.getBean("mainDepartmentService",IMainDepartmentService.class);
		String planId = request.getParameter("planId");
		String execId = request.getParameter("execId");
		
		ValidateUtil valid = new ValidateUtil();
		if(valid.validateNum(execId)){
			MainPlanExec exec=	execService.getPlanExec(Integer.parseInt(execId));
			if(exec!=null){
				request.setAttribute("exec", exec);
			}
		}
		
		
		request.setAttribute("planId", planId);
		return "plan/execadd";
	}
	
	
	/**
	 * 保存执行任务
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveexec")
	@ResponseBody
	public Result<String> saveexec(HttpServletRequest request)throws Exception{
		 Result<String> result = new Result<String>();
		 String planId = request.getParameter("planId");
		String execId = request.getParameter("execId");
		String execName = request.getParameter("execName");
		String execInfo = request.getParameter("execInfo");
		String execStartTime = request.getParameter("execStartTime").trim();
		String execEndTime = request.getParameter("execEndTime").trim();
		String execPrime = request.getParameter("execPrime");
		String execCost = request.getParameter("execCost");
		String execSale = request.getParameter("execSale");
		String execRemark=request.getParameter("execRemark");
		
		MainPlanExec exec = new MainPlanExec();
		ValidateUtil valid = new ValidateUtil();
		if(StringUtils.isNotBlank(execId)){
			try{
				exec.setExecId(Integer.parseInt(execId));
			}catch(Exception ex){
				result.setResultInfo("execId 有误");
				return result;
			}
		}
		if(StringUtils.isBlank(execName)){
			result.setResultInfo("执行名称不能为空");
			return result;
		}
		exec.setExecName(execName);
		if(StringUtils.isBlank(execInfo)){
			result.setResultInfo("执行内容不能为空");
			return result;
		}
		exec.setExecInfo(execInfo);
		

		
		MainPlan plan = new MainPlan();
		try{
			plan.setPlanId(Integer.parseInt(planId));
		}catch(Exception ex){
			result.setResultInfo("planId 有误");
			return result;
		}
		exec.setPlan(plan);
		
		if(!valid.isValidDate(execStartTime,"yyyy-MM-dd")){
			result.setResultInfo("执行开始时间格式不正确 格式：yyyy-MM-dd");
			return result;
		}
		exec.setExecStartTime(DateUtil.fmtStrTime(execStartTime, "yyyy-MM-dd"));
		if(!valid.isValidDate(execEndTime,"yyyy-MM-dd")){
			result.setResultInfo("执行结束时间不能为空：yyyy-MM-dd");
			return result;
		}
		exec.setExecEndTime(DateUtil.fmtStrTime(execEndTime, "yyyy-MM-dd"));
		
		if(!valid.validateNum(execPrime)){
			result.setResultInfo("执行成本格式错误 必须为数字");
			return result;
		}
		exec.setExecPrime(Integer.parseInt(execPrime));
		
		if(!valid.validateNum(execCost)){
			result.setResultInfo("执行耗资格式错误 必须为数字");
			return result;
		}
		exec.setExecCost(Integer.parseInt(execCost));
		
		if(!valid.validateNum(execSale)){
			result.setResultInfo("执行销售额格式错误 必须为数字");
			return result;
		}
		exec.setExecSale(Integer.parseInt(execSale));
		exec.setExecRemark(execRemark);
		MainEmployee user = (MainEmployee)request.getSession().getAttribute(Constant.SESSION_USER);
		exec.setExecCreateEmp(user.getEmpId());
		exec.setExecCreateTime(new Date());
		execService.saveMainPlan(exec);
		result.setResult(true);
		if(StringUtils.isBlank(execId)){
			result.setResultInfo("添加成功");
		}else{
			result.setResultInfo("修改成功");
		}
		return result;
	}
	
	
}
