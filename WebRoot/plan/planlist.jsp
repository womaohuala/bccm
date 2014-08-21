<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>计划</title>
<link href="../css/uucall.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.11.1.min.js"  type="text/javascript"></script>
<script src="../js/layer/layer.min.js" type="text/javascript" ></script>
<script src="../js/My97DatePicker/WdatePicker.js" type="text/javascript" ></script>
<script type="text/javascript">
function addPlan(){
  $.layer({
    type: 2,
    shade: [0],
    fix: false,
    offset: ['8', '5'],
    title: '添加计划',
    move: 'false',
    maxmin: true,
    iframe: {src : 'planAddUpdate.shtml'},
    area: ['1000px' , '400px']/*,
    close: function(index){
       $('#form1').submit();
    }*/
    
  });
 }
 
 function editPlan(planId){
  $.layer({
    type: 2,
    shade: [0],
    fix: false,
    offset: ['8', '5'],
    title: '修改计划',
    move: 'false',
    maxmin: true,
    iframe: {src : 'planAddUpdate.shtml?planId='+planId},
    area: ['1000px' , '400px']/*,
    close: function(index){
       $('#form1').submit();
    }*/
    
  });
}
 
 


</script>
</head>

<body  style="background:#e3e8ee;">
<div class="ny_right">
	<div class="ny_right_top">
    	<div class="ny_right_top1"></div>
        <div class="ny_right_top2"><div class="ny_right_top21">当前位置：计划管理/计划管理/计划记录</div></div>
        <div class="ny_right_top3"></div>
    </div>
    <div class="clear"></div>
   
    <div class="ny_right_con">
    	<div class="ny_right_con1">
        	<div class="ul_width1">
        	  <form id="form1" name="form1" action="planlist.shtml" method="get">
                <ul>
                    <li class="li_width1"><span class="tdleft">开始时间：</span>
                    <input type="text" class="input_css1 Wdate"  readonly="readonly"  onclick="WdatePicker()" name="startTime"  id="startTime" value="${startTime}"/>
                      <span class="tdmid">-</span>
                    <input type="text" class="input_css1 Wdate" readonly="readonly"  onclick="WdatePicker()" name="endTime"  id="endTime" value="${endTime}"/></li>
                    <li><span class="tdleft">计划名称：</span><input type="text" class="input_css1" name="planName" id="planName" value="${planName}"/></li>
                    <!-- 
                    <li><span class="tdleft">注册帐号：</span><input type="text" class="input_css1" /></li>
                    <li><span class="tdleft">公司名称：</span><input type="text" class="input_css1" /></li>
                    <li><span class="tdleft">商务人员：</span><select class="select_css1"><option>请选择</option></select></li> -->
                </ul>
                </form>
 				<div class="ny_right_search">
 				<span class="btn_common"><a href="#" onclick="$('#form1').submit();return false;">查询</a></span>
 				</div>
 				<div class="ny_right_search">
 				<span class="btn_common"><a href="#" onclick="addPlan();">添加计划</a></span>
 				</div>
            </div>
             <div class="clear"></div>
             
             <div class="ul_width1">
            </div>
        </div>
        <div class="ny_right_con2">
        	<table class="table_class">
            	<tr>
                	<th>编号</th>
                    <th>计划名称</th>
                    <th>计划内容</th>
                    <th>合作公司</th>
                    <th>合作项目</th>
                    <th>负责组</th>
                    <th>负责人</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="plan" items="${objPage.pageData}" varStatus="status">
                 
                <tr <c:if test="${status.index%2 eq 0}">style="background:#fff"</c:if> <c:if test="${status.index%2 eq 1}">style="background:#f3f3f3"</c:if>>
                	<td>${plan.planId}</td>
                    <td>${plan.planName}</td>
                    <td>${plan.planInfo}</td>
                    <td>${plan.project.company.compName}</td>
                    <td>${plan.project.proName}</td>
                    <td>${plan.employee.department.deptName}</td>
                    <td>${plan.employee.empName}</td>
                    <td><fmt:formatDate value="${plan.planStartTime}" pattern="yyyy-MM-dd "/></td>
                    <td><fmt:formatDate value="${plan.planEndTime}" pattern="yyyy-MM-dd "/></td>
                    <td>
                    <a href="planDetail.shtml?planId=${plan.planId}">详情</a>&nbsp;&nbsp;<a href="#" onclick="editPlan(${plan.planId});return false;">编辑</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
        
        <div class="ny_right_con3">
            <!-- 分页功能开始 -->
            <c:set var="url"      value="../plan/planlist.shtml?"/>
            <c:set var="params"   value="&planName=${planName}&startTime=${startTime}&endTime=${endTime}"/>
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
