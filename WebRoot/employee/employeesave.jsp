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
<link rel="StyleSheet" href="../js/dtree/dtree.css" type="text/css" />
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
     
     
    function save(){
		if(validForm()){
			idSt=idSt.replace(",0,",",")
			document.getElementById("roleId").value=idSt;
			$('#form1').submit();
		}
		return false;
	}

	 function validForm(){
		 var department=document.getElementById("department").value;
		 var empName=document.getElementById("empName").value;
		 var empUserName=document.getElementById("empUserName").value;
		 var empPassword=document.getElementById("empPassword").value;
		 if(department==""){
			 parent.layer.msg("部门不可为空！");
			 return false;
		 }
		 if(empName==""){
			 parent.layer.msg("职员姓名不可为空！");
			 return false;
		 }
		 if(empUserName==""){
			 parent.layer.msg("用户名不可为空！");
			 return false;
		 }
		 if(empPassword==""){
			 parent.layer.msg("密码不可为空！");
			 return false;
		 }
		 return true
	 }
	 
</script>

</head>
<body  style="background:#e3e8ee;" id="top">
    <div class="ny_right_con">
    	<!--  <div class="ny_right_con11 backg_col">
        	<div class="ny_right_title2">添加计划</div>
        </div>-->
        <div class="ny_right_con2" style="border:none; margin:0 15px;">
        <form action="employeesave.shtml" id="form1" name="form1" method="post">
         <input type="hidden" name="empId" id="empId" value="${employee.empId}" />
        	<table class="table_class4">
        		 <tr>
                	<td class="tdleft2">所属部门：</td>
                	<td class="tdright2">
                	  <select class="select_css4" id="department" name="department" onchange="getProject(this.value);">
	                	  <option value="">请选择</option>
	                	  <c:forEach var="dept" items="${deptList}">
	                	    <option value="${dept.deptId}"  <c:if test="${dept.deptId==employee.department.deptId}">selected</c:if>>${dept.deptName}</option>
	                	  </c:forEach>
                	  </select>
                	 </td>
                    <td class="tdleft2">职员姓名：</td>
                    <td class="tdright2"><input type="text" class="input_css3" name="empName" id="empName" value="${employee.empName}" /></td>
                </tr>
                
            	<tr>
                	<td class="tdleft2">职员职能：</td>
                	<td class="tdright2"><input type="text" class="input_css3" name="empJob" id="empJob" value="${employee.empJob}" /></td>
                    <td class="tdleft2">职员性别：</td>
                    <td class="tdright2">
                    	<select class="select_css4" id="empSex" name="empSex" onchange="getProject(this.value);">
	                	  <option value="1" <c:if test="${employee.empSex==1}">selected</c:if> >男性</option>
	                	  <option value="2" <c:if test="${employee.empSex==2}">selected</c:if> >女性</option>
                	  </select>
                    </td>
                </tr>
                  <tr>
                	<td class="tdleft2">联系电话：</td>
                	<td class="tdright2"><input type="text"  class="input_css3" id="empPhone" name="empPhone" value="${employee.empPhone}" /></td>
                    <td class="tdleft2">备注：</td>
                    <td class="tdright2"><input type="text"  class="input_css3" id="empRemark" name="empRemark" value="${employee.empRemark}" /></td>
                </tr>
                <tr>
                	<td class="tdleft2">登陆账号：</td>
                	<td class="tdright2"><input type="text"  class="input_css3" id="empUserName" name="empUserName" value="${employee.empUserName}" /></td>
                    <td class="tdleft2">登陆密码：</td>
                    <td class="tdright2"><input type="text"  class="input_css3" id="empPassword" name="empPassword" value="${employee.empPassword}" /></td>
                </tr>
                
                  <tr>
                	<td class="tdleft2">角色选择：</td>
                	<td class="tdright2"><%--
                	  <select class="select_css4" id="roleId" name="roleId" >
	                	  <option value="">请选择</option>
	                	  <c:forEach var="role" items="${roleList}">
	                	    <option value="${role.roleId}" ${role.roleId==employee.roleId?'selected="selected" ':'' }>${role.roleName}</option>
	                	  </c:forEach>
                	  </select>
                	 --%>
                	  <!-- 角色树 -->
                	  <input type="hidden"  id="roleId" name="roleId" value=""/>
					<script type="text/javascript">
							var ishead=false;
							mytree = new dTree('mytree');
							mytree.config.useCookies=false;
							mytree.config.useCheckbox = true;  //设置复选
							mytree.add(0,-1,'角色列表',false);
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
                <tr><td colspan="4"><div class="btn_common"><a href="#" onclick="return save();">提交</a></div></td></tr>
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
