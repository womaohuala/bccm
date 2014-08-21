<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>重新贴写申请请假</title>
  </head>
  
  <body>
  <form action="reRequestRefuse.shtml">
  <c:forEach var="leave" items="${leaveList}">
  	<label>申请人：${sessionScope.staffName}</label>
  	 <br/>
	申请时长：<input type="text" name="leaveLong" value="${leave.leaveLong}"/><br/>
	申请原因：<textarea rows="3" cols="15" name="leaveContent">${leave.leaveContent}</textarea>
    <input type="hidden" name="taskId" value="${leavv.staff.taskId}"/>
    <input type="submit" value="提交"/> 
  </c:forEach>
  </form>
  
  </body>
</html>