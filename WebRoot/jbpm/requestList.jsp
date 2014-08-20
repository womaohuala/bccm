<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${assignee}待办任务</title>
  </head>
  
  <body>

    <table border="1" width="100%">
      <caption>待办任务</caption>
      <thead>
        <tr>
          <td>taskId</td>
          <td>taskName</td>
          <td>assignee</td>
          <td>重新填写并申请</td>
        </tr>
      </thead>
      <tbody>
		
		<c:forEach var="task" items="${taskList}">
    		 <tr>
		      <td><c:out value="${task.id}" /></td>
		      <td><c:out value="${task.name}" /> </td>
		      <td><c:out value="${task.assignee}" /></td>
		      <td><a href="reRequest.shtml?taskId=${task.id}">重新填写并申请</a></td>
		    </tr>
    		
		</c:forEach>
	
		<!--  
		<s:iterator value="taskList">
	    <tr>
	      <td>${id}</td>
	      <td>${name}</td>
	      <td>${assignee}</td>
	      <td><a href="jbpm/reRequest.shtml?taskId=${id}">重新填写并申请</a></td>
	    </tr>
		</s:iterator>
		-->
  		</tbody>
	</table> 
  </body>
</html>
