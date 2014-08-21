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

	$(document).ready(function() { 
	    $('#form1').ajaxForm({ 
	        dataType:  'json', 
	        beforeSubmit:validForm,
	        success: processJson,
	        error: processError
	    }); 
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
        <form action="saveexec.shtml" id="form1" name="form1" method="post">
        <input type="hidden" name="planId" id="planId" value="${planId}"/>
        <input type="hidden" name="execId" id="planId" value="${exec.execId}"/>
        	<table class="table_class4">
            	<tr>
                	<td class="tdleft2">执行名称：</td>
                	<td class="tdright2"><input type="text" class="input_css3" name="execName" id="execName" maxlength="50" value="${exec.execName}"/></td>
                    <td class="tdleft2">执行内容：</td>
                    <td class="tdright2"><input type="text" class="input_css3" name="execInfo" id="execInfo" maxlength="100" value="${exec.execInfo}"/></td>
                </tr>
                <tr>
                	<td class="tdleft2">执行开始时间：</td>
                	<td class="tdright2"><input type="text"  class="input_css3 Wdate" id="execStartTime" name="execStartTime" readonly="readonly" value="<fmt:formatDate value="${exec.execStartTime}" pattern="yyyy-MM-dd "/>" onclick="WdatePicker()"/></td>
                    <td class="tdleft2">执行结束时间：</td>
                    <td class="tdright2"><input type="text" class="input_css3 Wdate" id="execEndTime" name="execEndTime" readonly="readonly" value="<fmt:formatDate value="${exec.execEndTime}" pattern="yyyy-MM-dd "/>" onclick="WdatePicker()"/></td>
                </tr>
                <tr>
                 	<td class="tdleft2">执行成本：</td>
                	<td class="tdright2"><input type="text" class="input_css3" id="execPrime" name="execPrime" value="${exec.execPrime}"/></td>
                    <td class="tdleft2">执行耗费：</td>
                    <td class="tdright2"><input type="text" class="input_css3" id="execCost" name="execCost"  value="${exec.execCost}"/></td>
               </tr>
               <tr>
	               <td class="tdleft2">执行销售额：</td>
	               <td class="tdright2"  colspan="3"><input type="text" class="input_css3" id="execSale"  name="execSale"  value="${exec.execSale}"/></td>
              </tr>
              <tr>
                 <td class="tdleft2">其他备注：</td>
                <td class="tdright2" colspan="3"><textarea class="textarea_class1" id="execRemark" name="execRemark">${exec.execRemark}</textarea></td>
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
