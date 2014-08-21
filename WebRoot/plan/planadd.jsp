<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加计划</title>
<link href="../css/uucall.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.11.1.min.js"  type="text/javascript"></script>
<script src="../js/jquery.form.js"  type="text/javascript"></script>
<script src="../js/My97DatePicker/WdatePicker.js" type="text/javascript" ></script>
<script type="text/javascript">
     var compMap=[
        <c:forEach var="com" items="${comList}">
		  {
		    'compId':'${com.compId}',
		    'projects':[
		                  <c:forEach var="pro" items="${com.projects}">
		                   {'proId':'${pro.proId}','proName':'${pro.proName}'},
		                  </c:forEach>
		               ]
		  },
		</c:forEach>
     ];
     
    var deptMap=[
        <c:forEach var="dept" items="${deptList}">
		  {
		    'deptId':'${dept.deptId}',
		    'employees':[
		                  <c:forEach var="emp" items="${dept.employees}">
		                   {'empId':'${emp.empId}','empName':'${emp.empName}'},
		                  </c:forEach>
		               ]
		  },
		</c:forEach>
     ];
     
     var projectId="";
     var employeeId="";
    <c:if test="${plan !=null}">
      projectId="${plan.project.proId}";
      employeeId="${plan.employee.empId}";
    </c:if>
    
    function getProject(value){
       var project= $("#project");
       project.empty();
       if(value.length==0){
          project.append("<option value=''>请选择</option>");
       }else{
        var projects=new Array();
         for(var i=0;i<compMap.length;i++){
           if(compMap[i].compId==value){
             projects=compMap[i].projects;
             break;
           }
         }
         if(projects.length<1){
            project.append("<option value=''>无项目</option>");
         }else{
           project.append("<option value=''>请选择</option>");
           var select;
           for(var i=0;i<projects.length;i++){
              select = "";
             if(projectId==projects[i].proId){
               select="selected='selected'";
             }
            project.append("<option value='"+projects[i].proId+"' "+select+">"+projects[i].proName+"</option>");
           }
         }
       }
     }
     
     
     function getEmployee(value){
       var employee= $("#employee");
       employee.empty();
       if(value.length==0){
          employee.append("<option value=''>请选择</option>");
       }else{
        var employees=new Array();
         for(var i=0;i<deptMap.length;i++){
           if(deptMap[i].deptId==value){
             employees=deptMap[i].employees;
             break;
           }
         }
         if(employees.length<1){
            employee.append("<option value=''>无员工</option>");
         }else{
           employee.append("<option value=''>请选择</option>");
           var select;
           for(var i=0;i<employees.length;i++){
             select = "";
             if(employeeId==employees[i].empId){
               select="selected='selected'";
             }
            employee.append("<option value='"+employees[i].empId+"' "+select+">"+employees[i].empName+"</option>");
           }
         }
       }
     }
     
     
     	
	$(document).ready(function() { 
	    $('#form1').ajaxForm({ 
	        dataType:  'json', 
	        beforeSubmit:validForm,
	        success: processJson,
	        error: processError
	    }); 
	    
	    <c:if test="${plan !=null}">
	    getEmployee($("#department").val());
	     getProject($("#company").val());
	   </c:if>
	 });
	 
	 
	 function  processJson(data){
	    if(!data.result){
	       parent.layer.msg(data.resultInfo);
	    }else{
	       parent.layer.msg(data.resultInfo);
	       parent.location.reload();
	    }
	   
	 }
	 function validForm(){
	   return true;
	 }
	 
	 
	 function processError(){
	   parent.layer.msg('提交失败');
	 }
	 
</script>

</head>
<body  style="background:#e3e8ee;" id="top">
    <div class="ny_right_con">
    	<!--  <div class="ny_right_con11 backg_col">
        	<div class="ny_right_title2">添加计划</div>
        </div>-->
        <div class="ny_right_con2" style="border:none; margin:0 15px;">
        <form action="saveplan.shtml" id="form1" name="form1" method="post">
        <input type="hidden" name="planId" id="planId" value="${plan.planId}"/>
        	<table class="table_class4">
            	<tr>
                	<td class="tdleft2">计划名称：</td>
                	<td class="tdright2"><input type="text" class="input_css3" name="planName" id="planName" maxlength="50" value="${plan.planName}"/></td>
                    <td class="tdleft2">计划内容：</td>
                    <td class="tdright2"><input type="text" class="input_css3" name="planInfo" id="planInfo" maxlength="100" value="${plan.planInfo}"/></td>
                </tr>
                <tr>
                	<td class="tdleft2">负责部门：</td>
                	<td class="tdright2">
                	  <select class="select_css4" id="department" name="department" onchange="getEmployee(this.value);">
	                	  <option value="" >请选择</option>
	                	  <c:forEach var="dept" items="${deptList}">
	                	   <option value="${dept.deptId}" <c:if test="${dept.deptId eq plan.employee.department.deptId}">selected="selected"</c:if>>${dept.deptName}</option>
	                	  </c:forEach>
                	  </select>
                	 </td>
                    <td class="tdleft2">负责人：</td>
                    <td class="tdright2">
                      <select class="select_css4" id="employee" name="employee" >
	                	  <option value="">请选择</option>
                	  </select>
                    </td>
                </tr>
                <tr>
                	<td class="tdleft2">合作公司：</td>
                	<td class="tdright2">
                	  <select class="select_css4" id="company" name="company" onchange="getProject(this.value);">
	                	  <option value="">请选择</option>
	                	  <c:forEach var="com" items="${comList}">
	                	    <option value="${com.compId}"  <c:if test="${com.compId eq plan.project.company.compId}">selected="selected"</c:if>>${com.compName}</option>
	                	  </c:forEach>
                	  </select>
                	 </td>
                    <td class="tdleft2">合作项目：</td>
                    <td class="tdright2">
                      <select class="select_css4" id="project" name="project">
	                	  <option value="">请选择</option>
                	  </select>
                    </td>
                </tr>
                <tr>
                	<td class="tdleft2">合作开始时间：</td>
                	<td class="tdright2"><input type="text"  class="input_css3 Wdate" id="planStartTime" name="planStartTime" readonly="readonly" value="<fmt:formatDate value="${plan.planStartTime}" pattern="yyyy-MM-dd "/>" onclick="WdatePicker()"/></td>
                    <td class="tdleft2">合作结束时间：</td>
                    <td class="tdright2"><input type="text" class="input_css3 Wdate" id="planEndTime" name="planEndTime" readonly="readonly" value="<fmt:formatDate value="${plan.planEndTime}" pattern="yyyy-MM-dd "/>" onclick="WdatePicker()"/></td>
                </tr>
                <tr>
                 	<td class="tdleft2">预计成本：</td>
                	<td class="tdright2"><input type="text" class="input_css3" id="planPrime" name="planPrime" value="${plan.planPrime}"/></td>
                    <td class="tdleft2">预计耗费：</td>
                    <td class="tdright2"><input type="text" class="input_css3" id="planCost" name="planCost"  value="${plan.planCost}"/></td>
               </tr>
               <tr>
	               <td class="tdleft2">预计销售额：</td>
	               <td class="tdright2"  colspan="3"><input type="text" class="input_css3" id="planSale"  name="planSale"  value="${plan.planSale}"/></td>
              </tr>
              <tr>
                 <td class="tdleft2">其他备注：</td>
                <td class="tdright2" colspan="3"><textarea class="textarea_class1" id="planRemark" name="planRemark">${plan.planRemark}</textarea></td>
              </tr>
                <tr><td colspan="4"><div class="btn_common"><a href="#" onclick="$('#form1').submit();return false;">提交</a></div></td></tr>
            </table>
            </form>
        </div>
        <div class="ny_right_bottom">
        	<div class="ny_right_bottom1"></div>
            <div class="ny_right_bottom2"></div>
            <div class="ny_right_bottom3"></div>
        </div>
    </div>
</body>
</html>
