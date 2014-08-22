<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限录入</title>
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
		if(validForm("#form1")){
			$('#form1').submit();
		}
		return false;
	}

</script>
<body  style="background:#e3e8ee;" id="top">
    <div class="ny_right_con">
    	<!--  <div class="ny_right_con11 backg_col">
        	<div class="ny_right_title2">添加计划</div>
        </div>-->
        <div class="ny_right_con2" style="border:none; margin:0 15px;">
        <form action="save.shtml" id="form1" name="form1" method="post">
         <input type="hidden" name="perId" id="perId" value="${per.perId}" />
         <input type="hidden" name="type" id="type" value="1" />
         <input type="hidden" name="perParent" id="perParent" value="${perParent }" />
        	<table class="table_class4">
        		 <tr>
                    <td class="tdleft2">权限名称：</td>
                    <td class="tdright2"><input type="text" class="input_css3" name="perName" info="权限名称" id="perName" value="${per.perName }"/></td>
                	<td class="tdleft2">权限描述：</td>
                	<td class="tdright2"><input type="text" class="input_css3" name="perInfo" info="权限描述" id="perInfo" value="${per.perInfo}" /></td>
                </tr>
        		 <tr>
                    <td class="tdleft2">权限URL：</td>
                    <td class="tdright2"><input type="text"  class="input_css31" name="perAction" info="权限URL" id="perAction" value="${per.perAction }"/></td>
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
