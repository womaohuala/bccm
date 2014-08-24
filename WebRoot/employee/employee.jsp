<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="bccm"  uri="bccmTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/uucall.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.11.1.min.js"  type="text/javascript"></script>
<script src="../js/layer/layer.min.js" type="text/javascript" ></script>
<script type="text/javascript">
function searchEmployee(){
	form1.submit();
	
 }

function addEmployee(){
  $.layer({
    type: 2,
    shade: [0],
    fix: false,
    offset: ['8', '5'],
    title: '添加员工',
    move: 'false',
    maxmin: true,
    iframe: {src : 'employeeadd.shtml'},
    area: ['800px' , '240px']
  });
 }
 
 function editEmployee(id){
  $.layer({
    type: 2,
    shade: [0],
    fix: false,
    offset: ['8', '5'],
    title: '修改员工',
    move: 'false',
    maxmin: true,
    iframe: {src : 'employeeedit.shtml?id='+id},
    area: ['800px' , '240px']
  });
}

 function detailEmployee(id){
	  $.layer({
	    type: 2,
	    shade: [0],
	    fix: false,
	    offset: ['8', '5'],
	    title: '员工详情',
	    move: 'false',
	    maxmin: true,
	    iframe: {src : 'employeedetail.shtml?id='+id},
	    area: ['800px' , '240px']
	  });
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
                    <li><span class="tdleft">性别：</span>
                    <select  id="empSex" name="empSex" >
                    	  <option value=""  >请选择</option>
                          <option value="0" <c:if test="${empSex==0}">selected</c:if> >男性</option>
	                	  <option value="1" <c:if test="${empSex==1}">selected</c:if> >女性</option>
                	  </select></li>
                    <li><span class="tdleft">职员姓名：</span><input type="text" class="input_css1" value="${empName}" name="empName" id="empName"   /></li>
                </ul>
 				<div class="ny_right_search"><span class="btn_common"><a href="#" onclick="searchEmployee()">查询</a></span></div>
 				 <bccm:permission permissionId="41">
 				<div class="ny_right_search"><span class="btn_common"><a href="#" onclick="addEmployee()">增加</a></span></div>
 				 </bccm:permission>
 				 
            </form>
            </div>
             <div class="clear"></div>
        </div>
        <div class="ny_right_con2">
        	<table class="table_class">
            	<tr>
            		<th>序号</th>
                	<th>职员名称</th>
                    <th>职员性别</th>
                    <th>联系方式</th>
                    <th>所属部门</th>
                    <th>登陆用户名</th>
                    <th>登陆密码</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="employee" items="${objPage.pageData}" begin="0" varStatus="obj">
                <tr ${obj.count%2 == '0'?'style="background:#f3f3f3"':'style="background:#fff"'} >
 			        <td>${objPage.pageSize*(objPage.pageNo-1)+obj.index+1}</td>
 			        <td>${employee.empName}</td>
                    <td>
                    ${employee.empSex==1?'男性':'女性'}
                    </td>
                    <td>${employee.empPhone}</td>
                    <td>${employee.department.deptName}</td>
                    <td>${employee.empUserName}</td>
                    <td>${employee.empPassword}</td>
                    <td>
                    <bccm:permission permissionId="46">
                    <a href="#" onclick="editEmployee(${employee.empId})">修改</a>
                    </bccm:permission>
                    <bccm:permission permissionId="44">
                    &nbsp;<a href="delete.shtml?id=${employee.empId}">删除</a>
                    </bccm:permission>
                    <bccm:permission permissionId="45">
                    &nbsp;<a href="#" onclick="detailEmployee(${employee.empId})">详情</a>
                    </bccm:permission>
                    </td>
	              </tr>
		        </c:forEach>
                
            </table>
        </div>
         <div class="ny_right_con3">
            <!-- 分页功能开始 -->
            <c:set var="url"      value="../employee/index.shtml?"/>
            <c:set var="params"   value="&empSex=${empSex}&empName=${empName}"/>
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
