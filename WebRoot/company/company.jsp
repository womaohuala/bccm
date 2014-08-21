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
function addCompany(){
  $.layer({
    type: 2,
    shade: [0],
    fix: false,
    offset: ['8', '5'],
    title: '添加合作公司',
    move: 'false',
    maxmin: true,
    iframe: {src : 'companyadd.shtml'},
    area: ['800px' , '180px']
  });
 }
 
 function editCompany(id){
  $.layer({
    type: 2,
    shade: [0],
    fix: false,
    offset: ['8', '5'],
    title: '修改合作公司',
    move: 'false',
    maxmin: true,
    iframe: {src : 'companyedit.shtml?id='+id},
    area: ['800px' , '180px']
  });
}

 function detailCompany(id){
	  $.layer({
	    type: 2,
	    shade: [0],
	    fix: false,
	    offset: ['8', '5'],
	    title: '合作公司详情',
	    move: 'false',
	    maxmin: true,
	    iframe: {src : 'companydetail.shtml?id='+id},
	    area: ['800px' , '180px']
	  });
	}
 
 function searchCompany(){
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
                    <li><span class="tdleft">公司名称：</span><input type="text" class="input_css1" name="compName" id="compName"/></li>
                </ul>
 				<div class="ny_right_search"><span class="btn_common"><a href="#" onclick="searchCompany()">查询</a></span></div>
 				<bccm:permission permissionId="2">
 				<div class="ny_right_search"><span class="btn_common"><a href="#" onclick="addCompany()">增加</a></span></div>
 				</bccm:permission>
            </form>
            </div>
             <div class="clear"></div>
        </div>
        <div class="ny_right_con2">
        	<table class="table_class">
            	<tr>
            		<td>序号</td>
                	<th>公司名称</th>
                    <th>公司简介</th>
                    <th>公司负责人</th>
                    <th>公司电话</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="company" items="${objPage.pageData}" begin="0" varStatus="obj">
 			    <tr ${obj.count%2 == '0'?'style="background:#f3f3f3"':'style="background:#fff"'} >
 			        <td>${objPage.pageSize*(objPage.pageNo-1)+obj.index+1}</td>
 			        <td>${company.compName}</td>
                    <td>${company.compIntro}</td>
                    <td>${company.compHead}</td>
                    <td>${company.compPhone}</td>
                    <td><bccm:permission permissionId="7">
                    <a href="#" onclick="editCompany(${company.compId})">修改</a>
                    </bccm:permission>
                    <bccm:permission permissionId="5">
                    &nbsp;<a href="delete.shtml?id=${company.compId}">删除</a>
                    </bccm:permission>
                    <bccm:permission permissionId="6">
                    &nbsp;<a href="#" onclick="detailCompany(${company.compId})">详情</a></td>
                    </bccm:permission>
	              </tr>
		        </c:forEach>
                
            </table>
        </div>
         <div class="ny_right_con3">
            <!-- 分页功能开始 -->
            <c:set var="url"      value="../company/index.shtml?"/>
            <c:set var="params"   value="&compName=${compName}"/>
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
