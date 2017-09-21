<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  請輸入地址：<input id="input" style="width:700px;" type="text" />
  <button id="button" onClick="change()">查詢</button>
  <div id="img"></div>
    <script type="text/javascript">
    function change(){
    	document.getElementById("img").innerHTML="";
    	var url = document.getElementById("input").value;
//     	window.location.reload();
//         document.getElementById("input").value(url);
    var session = new WebSocket("ws://localhost:8080/TestAndStudy/picServer")
   session.onopen=function(){
    	//https://www.dbmeinv.com/dbgroup/show.htm?cid=4&pager_offset=6
	    session.send(url);
	    session.onmessage=function(result){
	    	document.getElementById("img").innerHTML+="<img src='"+result.data+"'/>";
	    }
    }
    
    }
    </script>
  </body>
</html>
