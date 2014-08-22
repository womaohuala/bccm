<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>待办任务</title>
  </head>
  
  <body>
	<a href="getTasks.shtml?assignee=staff">我是员工，我要请假</a><br>
    <a href="getTasks.shtml?assignee=manager">我是经理，我要审批</a><br>
    <a href="getTasks.shtml?assignee=boss">我是老板，我要审批</a><br>
  </body>
</html>
