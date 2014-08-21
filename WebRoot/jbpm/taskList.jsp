<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>待办任务</title>
  </head>
  
  <body>

    <table border="1" width="100%">
      <caption>待办任务</caption>
      <thead>
        <tr>
          <td>taskId</td>
          <td>taskName</td>
          <td>assignee</td>
          <td>查看详情并处理</td>
        </tr>
      </thead>
      <tbody>
	
		<!--  
		<s:iterator value="taskList">
	    <tr>
	      <td>${id}</td>
	      <td>${name}</td>
	      <td>${assignee}</td>
	      <td><a href="${formResourceName}?taskId=${id}&executionId=${executionId}">查看具体信息</a></td>
	    </tr>
		</s:iterator>
		-->
		<c:forEach var="task" items="${taskList}">
		   <tr>
		      <td>${task.id}</td>
		      <td>${task.name}</td>
		      <td>${task.assignee}</td>
		      <td><a href="${task.formResourceName}?taskId=${task.id}&executionId=${task.executionId}">查看具体信息</a></td>
	    	</tr>
   		</c:forEach>
		
  		</tbody>
	</table> 
  </body>
</html>
