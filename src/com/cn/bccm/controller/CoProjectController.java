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
import com.cn.bccm.model.MainEmployee;
import com.cn.bccm.model.MainPlan;
import com.cn.bccm.service.ICoCompanyService;
import com.cn.bccm.service.ICoProjectService;
import com.cn.bccm.util.ConstantValues;



@Controller
@RequestMapping("/project")
public class CoProjectController {
	private static Log log = LogFactory.getLog(CoProjectController.class);
	
	@Autowired
	private ICoProjectService projectService;
	
	@Autowired
	private ICoCompanyService compnayService;

	
	@RequestMapping("index")
	public String index(HttpServletRequest request) throws Exception{
		String hql="from CoopProject where 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		String proName = request.getParameter("proName");
		String proHead = request.getParameter("proHead");
		if(StringUtils.isNotBlank(proName)){
			proName = CommonUtil.getUtf8Code(proName);
			hql+=" and proName like '%"+proName+"%'";
		}
		if(StringUtils.isNotBlank(proHead)){
			proHead = CommonUtil.getUtf8Code(proHead);
			hql+=" and proHead like '%"+proHead+"%'";
		}
		
		Page<CoopProject> page = new Page<CoopProject>();
		String pageNoStr=request.getParameter("pageNo");
		
		int pageNo=1;
		try{
			pageNo=Integer.parseInt(pageNoStr);
		}catch(Exception ex){
			
		}
		page.setPageNo(pageNo);
		page.setPageSize(Constant.PAGE_SIZE);
		request.setAttribute("objPage", projectService.listByPage(page, hql, params));
		request.setAttribute("proName", proName);
		request.setAttribute("proHead", proHead);
		return "project/project";
	}
	
	@RequestMapping("queryproject")
	public String querycompany(HttpServletRequest request){
		
		
		List<CoopProject> list = projectService.list(new Object[]{});
		request.setAttribute("list", list);
		return "project/project";
	}
	
	@RequestMapping("projectadd")
	public String add(HttpServletRequest request){
		List<CoopCompany> comList = compnayService.list(new Object[]{});
		request.setAttribute("comList", comList);
		return "project/projectadd";
	}
	
	@RequestMapping("projectedit")
	public String edit(HttpServletRequest request){
		String id = request.getParameter("id");
		CoopProject project = projectService.getProject(Integer.parseInt(id));
		request.setAttribute("project", project);
		List<CoopCompany> comList = compnayService.list(new Object[]{});
		request.setAttribute("comList", comList);
		return "project/projectedit";
	}
	
	@RequestMapping("projectdetail")
	public String detail(HttpServletRequest request){
		String id = request.getParameter("id");
		CoopProject project = projectService.getProject(Integer.parseInt(id));
		request.setAttribute("project", project);
		List<CoopCompany> comList = compnayService.list(new Object[]{});
		request.setAttribute("comList", comList);
		return "project/projectdetail";
	}
	
	@RequestMapping("projectsave")
	@ResponseBody
	public Result<String> save(HttpServletRequest request) throws Exception{
		CoopProject project = new CoopProject();
		Result<String> result = new Result<String>();
		String proId = request.getParameter("proId");
		String compId = request.getParameter("company");
		if(StringUtils.isBlank(compId)){
			result.setResultInfo("合作公司名称不能为空");
			return result;
		}
		CoopCompany company = compnayService.getCompany(Integer.parseInt(compId));
		String proName = request.getParameter("proName");
		String proHead = request.getParameter("proHead");
		String proPhone = request.getParameter("proPhone");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String proInfo = request.getParameter("proInfo");
		String remark = request.getParameter("remark");
		
		
		ValidateUtil valid = new ValidateUtil();
		if(StringUtils.isNotBlank(proId)){
			try{
				project.setProId(Integer.parseInt(proId));
			}catch(Exception ex){
				result.setResultInfo("proId有误");
				return result;
			}
		}
		
		project.setCompany(company);
		if(StringUtils.isBlank(proName)){
			result.setResultInfo("项目名称不能为空");
			return result;
		}
		project.setProName(proName);
		if(StringUtils.isBlank(proHead)){
			result.setResultInfo("项目负责人不能为空");
			return result;
		}
		project.setProHead(proHead);
		if(StringUtils.isBlank(proPhone)){
			result.setResultInfo("联系电话不能为空");
			return result;
		}
		
		if(!valid.validateMobilePhone(proPhone)){
			result.setResultInfo("电话号码不正确");
			return result;
		}
		project.setProPhone(proPhone);
		if(StringUtils.isBlank(proInfo)){
			result.setResultInfo("项目信息不能为空");
			return result;
		}
		project.setProInfo(proInfo);
		
		if(StringUtils.isBlank(remark)){
			result.setResultInfo("备注不能为空");
			return result;
		}
		project.setRemark(remark);
		
		if(!valid.isValidDate(beginTime,"yyyy-MM-dd")){
			result.setResultInfo("项目开始时间格式不正确 格式：yyyy-MM-dd");
			return result;
		}
		project.setBeginTime(DateUtil.fmtStrTime(beginTime, "yyyy-MM-dd"));
		
		if(!valid.isValidDate(endTime,"yyyy-MM-dd")){
			result.setResultInfo("项目结束时间格式不正确 格式：yyyy-MM-dd");
			return result;
		}
		project.setEndTime(DateUtil.fmtStrTime(endTime, "yyyy-MM-dd"));
		projectService.saveOrUpdateProject(project);
		result.setResult(true);
		if(StringUtils.isBlank(proId)){
			result.setResultInfo("添加成功");
		}else{
			result.setResultInfo("修改成功");
		}
		return result;
	}
	
	
	
	@RequestMapping("delete")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		//CoopCompany company = companyService.getCompany(Integer.parseInt(id));
		//companyService.deleteCompany(company);
		CoopProject project = projectService.getProject(Integer.parseInt(id));
		projectService.deleteProject(project);
		try {
			response.sendRedirect("index.shtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
