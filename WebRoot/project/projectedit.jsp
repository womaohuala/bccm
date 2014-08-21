<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加合作公司</title>
<link href="../css/uucall.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.11.1.min.js"  type="text/javascript"></script>
<script src="../js/jquery.form.js"  type="text/javascript"></script>
<script src="../js/My97DatePicker/WdatePicker.js" type="text/javascript" ></script>
<script type="text/javascript">

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
           for(var i=0;i<projects.length;i++){
           project.append("<option value='"+projects[i].proId+"'>"+projects[i].proName+"</option>");
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
           for(var i=0;i<employees.length;i++){
           employee.append("<option value='"+employees[i].empId+"'>"+employees[i].empName+"</option>");
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
	 });
	 
	 /*
	 function  processJson(data){
	    var a = parent.layer.msg(data.resultInfo);
	 }
	 */
	 function  processJson(data){
		    //1修改成功
		    if(data.result){
		    	parent.layer.alert(data.resultInfo, 1, function(){
			    	 parent.location.reload(); //自动关闭后可做一些刷新页面等操作
				     parent.layer.closeAll()
			    })
			}else{
				parent.layer.msg(data.resultInfo,1);
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
       <form action="projectsave.shtml" id="form1" name="form1" method="post">
       <input type="hidden" name="proId" id="proId" value=${project.proId} />
        	<table class="table_class4">
        		 <tr>
                	<td class="tdleft2">合作公司：</td>
                	<td class="tdright2">
                	  <select class="select_css4" id="company" name="company"  onchange="getProject(this.value);">
	                	  <option value="">请选择</option>
	                	  <c:forEach var="com" items="${comList}">
	                	    <option value="${com.compId}"  <c:if test="${com.compId==project.company.compId}">selected</c:if>>${com.compName}</option>
	                	  </c:forEach>
                	  </select>
                	 </td>
                    <td class="tdleft2">项目名称：</td>
                    <td class="tdright2"><input type="text" class="input_css3"  name="proName" id="proName" value=${project.proName} /></td>
                </tr>
                
            	<tr>
                	<td class="tdleft2">项目负责人：</td>
                	<td class="tdright2"><input type="text" class="input_css3"  name="proHead" id="proHead" value=${project.proHead} /></td>
                    <td class="tdleft2">项目联系电话：</td>
                    <td class="tdright2"><input type="text" class="input_css3"  name="proPhone" id="proPhone" value=${project.proPhone} /></td>
                </tr>
                  <tr>
                	<td class="tdleft2">项目开始时间：</td>
                	<td class="tdright2"><input type="text"  class="input_css3 Wdate" id="beginTime" name="beginTime"  value="<fmt:formatDate value="${project.beginTime}" pattern="yyyy-MM-dd "/>" onclick="WdatePicker()"/></td>
                    <td class="tdleft2">项目结束时间：</td>
                    <td class="tdright2"><input type="text"  class="input_css3 Wdate" id="endTime" name="endTime"  value="<fmt:formatDate value="${project.endTime}" pattern="yyyy-MM-dd "/>" onclick="WdatePicker()"/></td>
                </tr>
                <tr>
                	<td class="tdleft2">项目信息：</td>
                	<td class="tdright2" colspan="3"><textarea class="textarea_class1"  id="proInfo" name="proInfo" >${project.proInfo}</textarea></td>
                </tr>
                
                <tr>
                	<td class="tdleft2">备注：</td>
                	<td class="tdright2" colspan="3"><textarea class="textarea_class1"  id="remark" name="remark">${project.remark}</textarea></td>
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
