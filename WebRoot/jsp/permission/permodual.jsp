<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="bccm"  uri="bccmTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限模块列表</title>
<link href="../css/uucall.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.11.1.min.js"  type="text/javascript"></script>
<script src="../js/layer/layer.min.js" type="text/javascript" ></script>
<script type="text/javascript" src="../js/base.js"></script>
<script type="text/javascript">

function searchKey(){
	 $('#form1').submit();
 }  

function add(){
	var src="addmodual.shtml";
	newIframe(src,"添加权限模块");
}

function edit(id){
	var src="editmodual.shtml?id="+id;
	newIframe(src,"修改权限模块");
}

function delById(id){
	var src="deletemodual.shtml?id="+id;
	del(src);
}

function detail(id){
	var src="detailmodual.shtml?id="+id;
	location.href=src;
}

</script>
</head>

<body  style="background:#e3e8ee;">
<div class="ny_right">
	<div class="ny_right_top">
    	<div class="ny_right_top1"></div>
        <div class="ny_right_top2"><div class="ny_right_top21">当前位置：后台系统/客户管理/充值记录</div></div>
        <div class="ny_right_top3"></div>
    </div>
    <div class="clear"></div>
    
    <div class="ny_right_con">
    	<div class="ny_right_con1">
        	<div class="ul_width1">
        	 <form action="index.shtml" id="form1" name="form1" method="get">
                <ul>
                    <li><span class="tdleft">模块名称：</span><input type="text" class="input_css1" value="${perName}" name="perName" id="perName"   /></li>
                </ul>
 				<div class="ny_right_search"><span class="btn_common"><a href="#" onclick="searchKey()">查询</a></span></div>
 				<bccm:permission permissionId="70">
 				<div class="ny_right_search"><span class="btn_common"><a href="#" onclick="add()">增加</a></span></div>
                </bccm:permission>
            </form>
            </div>
             <div class="clear"></div>
        </div>
        <div class="ny_right_con2">
        	<table class="table_class">
            	<tr>
            		<th>序号</th>
                	<th>模块ID</th>
                    <th>模块名称</th>
                    <th>模块描述</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="per" items="${objPage.pageData}" begin="0" varStatus="obj">
 			     <tr ${obj.count%2 == '0'?'style="background:#f3f3f3"':'style="background:#fff"'} >
 			        <td>${objPage.pageSize*(objPage.pageNo-1)+obj.index+1}</td>
 			        <td>${per.perId}</td>
 			        <td>${per.perName}</td>
 			        <td>${per.perInfo}</td><!--
 			        <td>${role.rolePer}</td>
                    --><td>
                <bccm:permission permissionId="71">
                    <a href="#" onclick="edit(${per.perId})" >修改</a>&nbsp;
                </bccm:permission>
                <bccm:permission permissionId="78">
                    <a href="#" onclick="delById(${per.perId})" >删除</a>&nbsp;
                </bccm:permission>
                <bccm:permission permissionId="75">
                    <a href="#" onclick="detail(${per.perId})">详情</a></td>
                </bccm:permission>
	              </tr>
		        </c:forEach>
                
            </table>
        </div>
         <div class="ny_right_con3">
            <!-- 分页功能开始 -->
            <c:set var="url"      value="../permission/index.shtml?"/>
            <c:set var="params"   value="&perName=${perName}"/>
        	<%@ include file="../../include/page.jsp"%> 
        	  <!-- 分页功能结束-->
        </div>
        <div class="ny_right_bottom">
        	<div class="ny_right_bottom1"></div>
            <div class="ny_right_bottom2"></div>
            <div class="ny_right_bottom3"></div>
        </div>
    </div>
</div>
</body>

</html>
