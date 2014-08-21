<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <form action="departmentsave.shtml" id="form1" name="form1" method="post">
        <input type="hidden" name="deptId" id="deptId" value=${department.deptId} />
        	<table class="table_class4">
            	<tr>
                	<td class="tdleft2">部门名称：</td>
                	<td class="tdright2"><input type="text" class="input_css3" name="deptName" id="deptName" value=${department.deptName} /></td>
                    <td class="tdleft2">部门简介：</td>
                    <td class="tdright2"><input type="text" class="input_css3" name="deptInfo" id="deptInfo" value=${department.deptInfo} /></td>
                </tr>
                <tr>
                	<td class="tdleft2">部门负责人：</td>
                	<td class="tdright2"><input type="text" class="input_css3" name="deptHead" id="deptHead" value=${department.deptHead} /></td>
                    <td class="tdleft2">部门电话：</td>
                    <td class="tdright2"><input type="text" class="input_css3" name="deptPhone" id="deptPhone" value=${department.deptPhone} /></td>
                </tr>
                 <tr>
                	<td class="tdleft2">上级部门：</td>
                	
                	<td class="tdright2">${department.deptId==department.deptParent.deptId?'':'经理办'}</td>
                	<input type="hidden"  name="deptParent" id="deptParent" value="2" />
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
