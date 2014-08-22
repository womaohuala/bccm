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
		 parent.layer.msg(data.resultInfo);
		 parent.closeWindow();	    
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
        <form action="request.shtml" id="form1" name="form1" method="post">
        	<table class="table_class4">
        		<tr>
        			<td class="tdleft2">申请人</td>
        			<td class="tdright2">
        				${sessionScope.session_user.empName}
        			</td>
        			<td class="tdleft2">申请时长：</td>
                	<td class="tdright2">
                	 	<input type="text" class="input_css3" name="leaveLong" id="leaveLong"></input>
                	 </td>
        		</tr>
        		<tr>
                	<td class="tdleft2">下一位处理人员：</td>
                	<td class="tdright2" colspan="3">
                			<input type="hidden"  id="roleId" name="roleId" value=""/>
                			<script type="text/javascript">
							var ishead=false;
							mytree = new dTree('mytree');
							mytree.config.useCookies=false;
							mytree.config.useCheckbox = true;  //设置复选
							mytree.add(0,-1,'职员列表',true);
							//已有角色权限选中
							idSt=",";
							//alert('size:'+${fn:length(employeesDto)});
							<c:forEach var="obj" items="${employeesDto}"> 
								//alert('aa:'+'${obj.type}');
								if("${obj.type}"=='a'){
									mytree.add("${obj.id}","${obj.parentId}","${obj.name}",true,false);
								}else{
									mytree.add("${obj.id}","${obj.parentId}","${obj.name}",false,false);	
								}
								
							</c:forEach> 
							document.write(mytree);
							mytree.openAll();
							if(ishead)mytree.selectCheckboxMe(0);
							
					</script>
                	</td>
                </tr>
            	<tr>
                	<td class="tdleft2">申请原因：</td>
                	<td class="tdright2" colspan="3"><textarea class="input_css3" rows="5" cols="40" name="leaveContent"></textarea></td>
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
