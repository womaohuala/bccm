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
<script type="text/javascript" src="../js/dtree/dtree.js"></script>
<script type="text/javascript" src="../js/base.js"></script>
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
        <form action="exam.shtml" id="form1" name="form1" method="post">
         <input type="hidden" name="taskId" id="taskId" value=${taskId} />
        	<table class="table_class4">
        		 <tr>
                	<td class="tdleft2">请假申请人：</td>
                	<td class="tdright2">
                		${leave.employee.empName}
                	 </td>
                    <td class="tdleft2">请假时间长度：</td>
                    <td class="tdright2">
						${leave.leaveLong}
                    </td>
                </tr>
                
            	<tr>
                	<td class="tdleft2">请假原因：</td>
                	<td class="tdright2">
                		${leave.leaveContent}
                	</td>
                    <td class="tdleft2">目前状态：</td>
                    <td class="tdright2">
                    	${leave.leaveState}
                    </td>
                </tr>
                <tr>
                	<td class="tdleft2">下一位处理人员:</td>
                	<td class="tdright2">
                		<script type="text/javascript">
							var ishead=false;
							mytree = new dTree('mytree');
							mytree.config.useCookies=false;
							mytree.config.useCheckbox = true;  //设置复选
							mytree.add(0,-1,'职员列表',false);
							//已有角色权限选中
							idSt=",";
							<c:forEach var="obj" items="${employee.employRoles}"> 
								idSt+="${obj.role.roleId}"+",";
							</c:forEach> 

							<c:forEach var="obj" items="${roleList}"> 
								var flag=false;
								if(idSt.indexOf(","+"${obj.roleId}"+",")>-1){
									flag=true;
									ishead=true;
								}
								mytree.add("${obj.roleId}","0","${obj.roleName}",false,flag);
							</c:forEach> 
							document.write(mytree);
							mytree.openAll();
							if(ishead)mytree.selectCheckboxMe(0);
							
					</script>
                	</td>
                </tr>
                <tr>
                	<td class="tdleft2">是否通过：</td>
                	<td class="tdright2" colspan="3">
						<input type="radio" name="result" value="批准"/>批准
						<input type="radio" name="result" value="驳回"/>驳回
                	</td>
                </tr>
                <tr><td colspan="4"><div class="btn_common"><a href="#" onclick="$('#form1').submit();return false;">批准</a></div></td></tr>
            </table>
            </form>
        </div>
        <div class="ny_right_bottom">
        	<div class="ny_right_bottom1"></div>
            <div class="ny_right_bottom2"></div>
            <div class="ny_right_bottom3"></div>
        </div>
    	<img src="getPic.shtml?proInstanceId=${proInstanceId}" alt="" />
    </div>
    
</body>
</html>
