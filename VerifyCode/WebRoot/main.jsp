<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>main</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/main.css">


  </head>
  
  <body>
    <div id="container">
      <div id="header">
        <div id = "rightTop">
          当前用户：<span>${userName}</span> &nbsp;【<a href="logout.do">安全退出</a>】
        </div>
        <div id="menu">
          <ul>
            <li><a href="login.html">首页</a></li>
            <li class="menuDiv"></li>
            <li><a href="getDownloadList.do">资源下载</a></li>
            <li class="menuDiv"></li>
            <li><a href="userMannger.jsp">用户管理</a></li>
            <li class="menuDiv"></li>
            <li><a href="resourcesMannger.jsp">资源管理</a></li>
            <li class="menuDiv"></li>
            <li><a href="personalPlace.jsp">个人中心</a></li>
          </ul>
        </div>
        <div id="banner">
          <img src="image/main.png" alt="">
        </div>
      </div>
    </div>
  </body>
</html>
