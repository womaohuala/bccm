<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/uucall.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.11.1.min.js"  type="text/javascript"></script>
<script src="../js/layer/layer.min.js" type="text/javascript" ></script>
<script type="text/javascript">
function addDepartment(){
  $.layer({
    type: 2,
    shade: [0],
    fix: false,
    offset: ['8', '5'],
    title: '添加部门',
    move: 'false',
    maxmin: true,
    iframe: {src : 'departmentadd.shtml'},
    area: ['800px' , '180px']
  });
 }
 
 function editDepartment(id){
  $.layer({
    type: 2,
    shade: [0],
    fix: false,
    offset: ['8', '5'],
    title: '修改部门',
    move: 'false',
    maxmin: true,
    iframe: {src : 'departmentedit.shtml?id='+id},
    area: ['800px' , '180px']
  });
}

 function detailDepartment(id){
	  $.layer({
	    type: 2,
	    shade: [0],
	    fix: false,
	    offset: ['8', '5'],
	    title: '部门详情',
	    move: 'false',
	    maxmin: true,
	    iframe: {src : 'departmentdetail.shtml?id='+id},
	    area: ['800px' , '180px']
	  });
	}
 function searchDepartment(){
		form1.submit();
		
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
                    <li><span class="tdleft">部门名称：</span><input type="text" class="input_css1" name="deptName" id="deptName" value="${deptName}" /></li>
                </ul>
 				<div class="ny_right_search"><span class="btn_common"><a href="#" onclick="searchDepartment()">查询</a></span></div>
 				<div class="ny_right_search"><span class="btn_common"><a href="#" onclick="addDepartment()">增加</a></span></div>
            </form>
            </div>
             <div class="clear"></div>
        </div>
        <div class="ny_right_con2">
        	<table class="table_class">
            	<tr>
            		<td>序号</td>
                	<th>部门名称</th>
                    <th>部门负责人</th>
                    <th>部门电话</th>
                    <th>部门信息</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="department" items="${objPage.pageData}" begin="0" varStatus="obj">
                 <c:if test="${obj.count%2 == '0'}">
 			     <tr style="background:#f3f3f3">
 			        <td>${objPage.pageSize*(objPage.pageNo-1)+obj.index+1}</td>
 			        <td>${department.deptName}</td>
                    <td>${department.deptInfo}</td>
                    <td>${department.deptHead}</td>
                    <td>${department.deptPhone}</td>
                    <td><a href="#" onclick="editDepartment(${department.deptId})">修改</a>&nbsp;<a href="delete.shtml?id=${department.deptId}">删除</a>&nbsp;<a href="#" onclick="detailDepartment(${department.deptId})">详情</a></td>
	              </tr>
  				  </c:if>
  				 <c:if test="${obj.count%2 != '0'}">
                 <tr style="background:#fff">
                 	<td>${objPage.pageSize*(objPage.pageNo-1)+obj.index+1}</td>
                	 <td>${department.deptName}</td>
                    <td>${department.deptInfo}</td>
                    <td>${department.deptHead}</td>
                    <td>${department.deptPhone}</td>
                    <td><a href="#" onclick="editDepartment(${department.deptId})">修改</a>&nbsp;<a href="delete.shtml?id=${department.deptId}">删除</a>&nbsp;<a href="#" onclick="detailDepartment(${department.deptId})">详情</a></td>
                  </tr>
                 </c:if>
		        </c:forEach>
                
            </table>
        </div>
         <div class="ny_right_con3">
            <!-- 分页功能开始 -->
            <c:set var="url"      value="../department/index.shtml?"/>
            <c:set var="params"   value="&deptName=${deptName}"/>
        	<%@ include file="../include/page.jsp"%> 
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
