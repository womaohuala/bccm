<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>执行计划详情</title>
<link href="../css/uucall.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.11.1.min.js"  type="text/javascript"></script>
<script src="../js/layer/layer.min.js" type="text/javascript" ></script>
<script src="../js/My97DatePicker/WdatePicker.js" type="text/javascript" ></script>
  <script type="text/javascript">
	$(document).ready(function(){
	//首先将#back-to-top隐藏
	$("#back-to-top").hide();
	//当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失
	$(function () {
		$(window).scroll(function(){
		if ($(window).scrollTop()>100){
		$("#back-to-top").fadeIn(1500);
		}
		else
		{
		$("#back-to-top").fadeOut(1500);
		}
		});
		//当点击跳转链接后，回到页面顶部位置
		$("#back-to-top").click(function(){
		$('body,html').animate({scrollTop:0},1000);
		return false;
		});
		});
		});
		
		
function addExec(){
  $.layer({
    type: 2,
    shade: [0],
    fix: false,
    offset: ['8', '5'],
    title: '添加执任务',
    move: 'false',
    maxmin: true,
    iframe: {src : 'execAddUpdate.shtml?planId=${plan.planId}'},
    area: ['1000px' , '350px']/*,
    close: function(index){
       $('#form1').submit();
    }*/
    
  });
 }
 
 function editeExec(execId){
  $.layer({
    type: 2,
    shade: [0],
    fix: false,
    offset: ['8', '5'],
    title: '添加执任务',
    move: 'false',
    maxmin: true,
    iframe: {src : 'execAddUpdate.shtml?planId=${plan.planId}&execId='+execId},
    area: ['1000px' , '350px']/*,
    close: function(index){
       $('#form1').submit();
    }*/
    
  });
 }
  </script>
</head>

<body  style="background:#e3e8ee;" id="top">
<p id="back-to-top"><a href="#top"><span></span>回到顶部</a></p>
<div class="ny_right">
	<div class="ny_right_top">
    	<div class="ny_right_top1"></div>
        <div class="ny_right_top2"><div class="ny_right_top21">当前位置：后台系统/计划管理/计划详情</div>
        <div class="ny_right_top3"></div>
    </div>
    <div class="clear"></div>
    <div class="ny_right_con">
    	<div class="ny_right_con11 backg_col">
        	<div class="ny_right_title2">计划信息</div>
        </div>
        <div class="ny_right_con2" style="border:none; margin:0 15px;">
        	<table class="table_class4">
            	<tr>
                	<td class="tdleft2">计划名称：</td>
                	<td class="tdright2">${plan.planName}</td>
                    <td class="tdleft2">计划内容：</td>
                    <td class="tdright2">${plan.planInfo}</td>
                </tr>
                <tr>
                	<td class="tdleft2">负责部门：</td>
                	<td class="tdright2">
                	  ${plan.employee.department.deptName}
                	 </td>
                    <td class="tdleft2">负责人：</td>
                    <td class="tdright2">
                     ${plan.employee.empName}
                    </td>
                </tr>
                <tr>
                	<td class="tdleft2">合作公司：</td>
                	<td class="tdright2">
                	 ${plan.project.company.compName}
                	 </td>
                    <td class="tdleft2">合作项目：</td>
                    <td class="tdright2">
                      ${plan.project.proName}
                    </td>
                </tr>
                <tr>
                	<td class="tdleft2">合作开始时间：</td>
                	<td class="tdright2"><fmt:formatDate value="${plan.planStartTime}" pattern="yyyy-MM-dd "/></td>
                    <td class="tdleft2">合作结束时间：</td>
                    <td class="tdright2"><fmt:formatDate value="${plan.planEndTime}" pattern="yyyy-MM-dd "/></td>
                </tr>
                <tr>
                 	<td class="tdleft2">预计成本：</td>
                	<td class="tdright2">${plan.planPrime}</td>
                    <td class="tdleft2">预计耗费：</td>
                    <td class="tdright2">${plan.planCost}</td>
               </tr>
               <tr>
	               <td class="tdleft2">预计销售额：</td>
	               <td class="tdright2"  colspan="3">${plan.planSale}</td>
              </tr>
              <tr>
                 <td class="tdleft2">其他备注：</td>
                <td class="tdright2" colspan="3">${plan.planRemark}</td>
              </tr>
                <tr><td colspan="4"><div class="btn_common"><a href="planlist.shtml" >返回</a></div></td></tr>
            </table>
        </div>
        <div class="ny_right_con11">
        	<div class="ny_right_title1"><span class="btn_add"><a href="" onclick="addExec();return false;">添加执行</a></span>执行详细情况</div>
        </div>
        <div class="ny_right_con2">
        	<table class="table_class">
            	<tr>
                	<th>执行名称</th>
                    <th>执行内容</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>执行成本</th>
                    <th>执行耗费</th>
                    <th>执行销售额</th>
                     <th>操作</th>
                </tr>
                <c:set var="primeAll" value="0"/>
                <c:set var="costAll" value="0"/>
                <c:set var="saletAll" value="0"/>
                <c:forEach var="exec" items="${execs}" varStatus="status">
                <c:set var="primeAll" value="${primeAll+exec.execPrime}"/>
                <c:set var="costAll" value="${costAll+exec.execCost}"/>
                <c:set var="saletAll" value="${saletAll+exec.execSale}"/>
                <tr <c:if test="${status.index%2 eq 0}">style="background:#fff"</c:if> <c:if test="${status.index%2 eq 1}">style="background:#f3f3f3"</c:if>>
                	<td>${exec.execName}</td>
                    <td>${exec.execInfo}</td>
                     <td><fmt:formatDate value="${exec.execStartTime}" pattern="yyyy-MM-dd "/></td>
                    <td><fmt:formatDate value="${exec.execEndTime}" pattern="yyyy-MM-dd "/></td>
                    <td>${exec.execPrime}</td>
                    <td>${exec.execCost}</td>
                    <td>${exec.execSale}</td>
                    <td><a href="#" onclick="editeExec('${exec.execId}');return false;">编辑</a></td>
                </tr>
                </c:forEach>
               <!-- <tr bgcolor="#fcfcfc"><td colspan="14" height="40" align="center" style=" font-size:14px; color:#b2b2b2; line-height:40px;"><img src="../images/loading2.gif" style="vertical-align:middle;"/>更多监控记录...</td></tr> --> 
            </table>
             <div class="clear" style="height:20px;"></div>
            <table class="table_class4">
	             <tr>
	                 	<th  style="text-align: center;height:30px;" colspan="5">统计结果</th>
	             </tr>
                <tr>
                 	<td  style="text-align: center;">类型</td>
                	<td  style="text-align: center;">成本(元)</td>
                    <td  style="text-align: center;">耗费(元)</td>
                    <td   style="text-align: center;">销售额(元)</td>
                    <td  style="text-align: left;">盈利(元)</td>
               </tr>
               <tr>
                 	<td  style="text-align: center;">计划结果</td>
                	<td  style="text-align: center;">${plan.planPrime}</td>
                    <td  style="text-align: center;">${plan.planCost}</td>
                    <td  tyle="text-align: center;">${plan.planSale}</td>
                    <c:set var="xc1" value="${plan.planSale-plan.planCost-plan.planPrime}"/>
                     <td  style="text-align: left;">${xc1}</td>
               </tr>
                <tr>
                 	<td style="text-align: center;">执行结果</td>
                	<td  style="text-align: center;">${primeAll}</td>
                    <td  style="text-align: center;">${costAll}</td>
                    <td style="text-align: center;">${saletAll}</td>
                     <c:set var="xc2" value="${saletAll-primeAll-costAll}"/>
                    <td  style="text-align:left;">${xc2}</td>
               </tr>
               
                <tr>
                 	<td style="text-align: center;">结果相差</td>
                	<td  style="text-align: center;">${plan.planPrime-primeAll}</td>
                    <td  style="text-align: center;">${plan.planCost-costAll}</td>
                    <td style="text-align: center;">${plan.planSale-saletAll}</td>
                    <td  style="text-align:left;">${xc1-xc2 }</td>
               </tr>
              
             
            </table>
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
