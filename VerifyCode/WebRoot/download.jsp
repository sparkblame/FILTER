<%@ page import="java.util.ArrayList" %>
<%@ page import="vo.Download" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>下载</title>
</head>
<link rel="stylesheet" href="css/download.css">
<script type="text/javascript" src="js/download.js"></script>
<body>
<%
    List<Download> downloadList = (List<Download>)session.getAttribute("dList");
    for(int i = 0; i < downloadList.size(); i++) {
        Download download = (Download) downloadList.get(i);
        System.out.println(download);
%>
<div class="box">
<img src="<%=download.getImage() %>" alt="1" class="flag">
<div class="name"><%=download.getName() %></div>
<br>
<span class="size">大小: <%=download.getSize() %></span>
    <span>&nbsp;&nbsp;&nbsp;&nbsp;时间：<%=download.getTime() %></span>
<span>&nbsp;&nbsp;&nbsp;星级：</span>
   <%
    for(int j=0;j<download.getStar() ;j++){
    %>
    <img src="image/brightStar.png" alt="亮星星" class="star" style="height: 55px; width: 53px; ">
    <%
    }
   %>
    <%
        for(int j=0;j<5-download.getStar() ;j++){
    %>
    <img src="image/darkStar.png" alt="暗星星" class="star" style="height: 53px; width: 43px; ">
    <%
        }
    %>
    <a class="dl-btn" href="download.do?id=<%=download.getId() %>" title="点击下载">下载</a>
    <br>
<span class="description"><%=download.getDescription() %></span>
<hr/>
</div>
<%
    }
%>
</body>
</html>
