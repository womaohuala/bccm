<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单</title>
<link href="css/uucall.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="ny_left">
	<div class="ny_left_title">
    	<div class="ny_left_title1">计划管理</div>
    </div>
    <div class="ny_left_con">
    	<ul>
        	<li><a href="plan/planlist.shtml" target="main">计划管理</a></li>
        </ul>
        <div class="clear"></div>
    </div>
     <div class="ny_left_title">
    	<div class="ny_left_title1">部门管理</div>
    </div>
    <div class="ny_left_con">
    	<ul>
        	<li><a href="department/index.shtml" target="main">部门管理</a></li>
            <li><a href="employee/index.shtml" target="main">员工管理</a></li>
        	<li><a href="role/index.shtml" target="main">角色管理</a></li>
        </ul>
        <div class="clear"></div>
    </div>
     <div class="ny_left_title">
    	<div class="ny_left_title1">合作公司管理</div>
    </div>
    <div class="ny_left_con">
    	<ul>
        	<li><a href="company/index.shtml" target="main">合作公司管理</a></li>
            <li><a href="project/index.shtml" target="main">合作公司项目管理</a></li>
        </ul>
        <div class="clear"></div>
    </div>
    
      <div class="ny_left_title">
    	<div class="ny_left_title1">任务管理</div>
    </div>
    <div class="ny_left_con">
    	<ul>
        	<li><a href="jbpm/getTasks.shtml?assignee=${sessionScope.session_user.empName}" target="main">查看任务</a></li>
        	<li><a href="jbpm/request.shtml" target="main">发起流程</a></li>
         </ul>
        <div class="clear"></div>
    </div>
    
    
    
      <div class="ny_left_title">
    	<div class="ny_left_title1">个人管理</div>
    </div>
    <div class="ny_left_con">
    	<ul>
        	<li><a href="logout.shtml" target="main">退出</a></li>
        </ul>
        <div class="clear"></div>
    </div>
</div>
</body>
</html>
