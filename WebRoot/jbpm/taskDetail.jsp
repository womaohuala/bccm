<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>具体信息审核页面</title>

  </head>
  
  <body>
  	<!--  
    <s:iterator value="leaveList">
    	<p>请假申请人：${staff.staffName}</p>
    	<p>请假时间:${leaveLong}</p>
    	<p>请假原因：${leaveContent}</p>
    	<p>目前状态：${leaveState}</p>
    	
    </s:iterator>
    -->
    <c:forEach var="leave" items="${leaveList}">
	    <p>请假申请人：${leave.employee.empName}</p>
    	<p>请假时间:${leave.leaveLong}</p>
    	<p>请假原因：${leave.leaveContent}</p>
    	<p>目前状态：${leave.leaveState}</p>
   </c:forEach>
    
    
    <form action="exam.shtml" method="post">
    	<input type="hidden" name="taskId" value="${taskId}"/>
    	<input type="submit" name="result" value="批准"/>
    	<input type="submit" name="result" value="驳回"/>
    </form>
    
     <img src="servlet/PicServlet?processInstanceId=leave.7" style="position:absolute;left:0px;top:0px;"/>  
  </body>
</html>
