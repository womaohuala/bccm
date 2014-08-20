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
import com.cn.bccm.util.ConstantValues;



@Controller
@RequestMapping("/company")
public class CoCompanyController {
	private static Log log = LogFactory.getLog(CoCompanyController.class);
	
	@Autowired
	private ICoCompanyService companyService;

	
	@RequestMapping("index")
	public String index(HttpServletRequest request) throws Exception{
		String hql="from CoopCompany where 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		String compName = request.getParameter("compName");
		if(StringUtils.isNotBlank(compName)){
			compName = CommonUtil.getUtf8Code(compName);
			hql+=" and compName like '%"+compName+"%'";
		}
		
		Page<CoopCompany> page = new Page<CoopCompany>();
		String pageNoStr=request.getParameter("pageNo");
		
		int pageNo=1;
		try{
			pageNo=Integer.parseInt(pageNoStr);
		}catch(Exception ex){
			
		}
		page.setPageNo(pageNo);
		page.setPageSize(Constant.PAGE_SIZE);
		request.setAttribute("objPage", companyService.listByPage(page, hql, params));
		request.setAttribute("compName", compName);
		return "company/company";
	}
	
	@RequestMapping("querycompany")
	public String querycompany(HttpServletRequest request){
		
		
		List<CoopCompany> list = companyService.list(new Object[]{});
		request.setAttribute("list", list);
		return "company/company";
	}
	
	@RequestMapping("companyadd")
	public String add(HttpServletRequest request){
		return "company/companyadd";
	}
	
	@RequestMapping("companyedit")
	public String edit(HttpServletRequest request){
		String id = request.getParameter("id");
		CoopCompany company = companyService.getCompany(Integer.parseInt(id));
		request.setAttribute("company", company);
		return "company/companyedit";
	}
	
	@RequestMapping("companydetail")
	public String detail(HttpServletRequest request){
		String id = request.getParameter("id");
		CoopCompany company = companyService.getCompany(Integer.parseInt(id));
		request.setAttribute("company", company);
		return "company/companydetail";
	}
	
	@RequestMapping("companysave")
	@ResponseBody
	public Result<String> save(HttpServletRequest request) throws Exception{
		Result<String> result = new Result<String>();
		String comPid = request.getParameter("compId");
		String compName = request.getParameter("compName");
		String compIntro = request.getParameter("compIntro");
		String compHead = request.getParameter("compHead");
		String compPhone = request.getParameter("compPhone");
		
		CoopCompany company = new CoopCompany();
		ValidateUtil valid = new ValidateUtil();
		if(StringUtils.isNotBlank(comPid)){
			try{
				company.setCompId(Integer.parseInt(comPid));
			}catch(Exception ex){
				result.setResultInfo("comPid 有误");
				return result;
			}
		}
		if(StringUtils.isBlank(compName)){
			result.setResultInfo("合作公司名称不能为空");
			return result;
		}
		company.setCompName(compName);
		if(StringUtils.isBlank(compIntro)){
			result.setResultInfo("公司简介不能为空");
			return result;
		}
		company.setCompIntro(compIntro);
		if(StringUtils.isBlank(compHead)){
			result.setResultInfo("未选择负责人");
			return result;
		}
		company.setCompHead(compHead);
		if(StringUtils.isBlank(compPhone)){
			result.setResultInfo("未选择负责人");
			return result;
		}
		if(!valid.validateMobilePhone(compPhone)){
			result.setResultInfo("电话号码不正确");
			return result;
		}
		company.setCompPhone(compPhone);
		companyService.saveOrUpdateCompany(company);
		result.setResult(true);
		if(StringUtils.isBlank(comPid)){
			result.setResultInfo("添加成功");
		}else{
			result.setResultInfo("修改成功");
		}
		return result;
	}
	
	
	
	@RequestMapping("delete")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		CoopCompany company = companyService.getCompany(Integer.parseInt(id));
		companyService.deleteCompany(company);
		try {
			response.sendRedirect("index.shtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
