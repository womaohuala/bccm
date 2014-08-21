<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色录入</title>
<link href="../css/uucall.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.11.1.min.js"  type="text/javascript"></script>
<script src="../js/jquery.form.js"  type="text/javascript"></script>
<script src="../js/My97DatePicker/WdatePicker.js" type="text/javascript" ></script>
<link rel="StyleSheet" href="../js/dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="../js/dtree/dtree.js"></script>
<script type="text/javascript" src="../js/base.js"></script>
</head>
<script type="text/javascript">


	$(document).ready(function() { 
	    $('#form1').ajaxForm({ 
	        dataType:  'json', 
	        success: processJson,
	        error: processError
	    }); 
	 });
	 
	function save(){
		if(validForm()){
			idSt=idSt.replace(",0,",",")
			document.getElementById("rolePer").value=idSt;
			$('#form1').submit();
		}
		return false;
	}

	 function validForm(){
		 var roleName=document.getElementById("roleName").value;
		 var roleInfo=document.getElementById("roleInfo").value;
		 if(roleName==""){
			 parent.layer.msg("角色名称不可为空！");
			 return false;
		 }
		 return true
	 }

</script>
<body  style="background:#e3e8ee;" id="top">
    <div class="ny_right_con">
    	<!--  <div class="ny_right_con11 backg_col">
        	<div class="ny_right_title2">添加计划</div>
        </div>-->
        <div class="ny_right_con2" style="border:none; margin:0 15px;">
        <form action="save.shtml" id="form1" name="form1" method="post">
         <input type="hidden" name="roleId" id="roleId" value="${role.roleId}" />
         <input type="hidden" name="rolePer" id="rolePer" value="" />
        	<table class="table_class4">
        		 <tr>
                    <td class="tdleft2">角色名称：</td>
                    <td class="tdright2"><input type="text" class="input_css3" name="roleName" id="roleName" value="${role.roleName }"/></td>
                	<td class="tdleft2">角色描述：</td>
                	<td class="tdright2"><input type="text" class="input_css3" name="roleInfo" id="roleInfo" value="${role.roleInfo}" /></td>
                </tr>
                
            	<tr>
                	<td class="tdleft2">角色权限：</td>
                	<td class="tdright2">
                	<!-- 角色树 -->
					<script type="text/javascript">
							var ishead=false;
							mytree = new dTree('mytree');
							mytree.config.useCookies=false;
							mytree.config.useCheckbox = true;  //设置复选
							mytree.add(0,-1,'权限列表',false);
							//已有角色权限选中
							idSt="${role.rolePer}";
							
							<c:forEach var="obj" items="${perList}"> 
								var flag=false;
								if(idSt.indexOf("${obj.perId}")>-1){
									flag=true;
									ishead=true;
								}
								mytree.add("${obj.perId}","${obj.perParent}","${obj.perName}",false,flag);
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
