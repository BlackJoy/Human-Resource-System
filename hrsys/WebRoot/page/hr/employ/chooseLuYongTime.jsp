<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Date now = new Date();
request.setAttribute("currTime",now);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'chooseTime.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="js/Calendar2.js"></script>
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#doConfirm").click(function(){
				var tt = $("#txtTime").val();
				window.returnValue = tt;
				window.close();
			});
			$("#doClose").click(function(){
				window.returnValue = "";
				window.close();
			});
		});
	</script>
  </head>
  
  <body>
    <div style="font-size: 14px;">请选择入职时间：<input id="txtTime" value='<s:date name="#request.currTime" format="yyyy-MM-dd" />' onfocus="setday(this)" />
    	<input id="doConfirm" type="button" value="确定" />&nbsp;&nbsp;
    	<input id="doClose" type="button" value="关闭" />
    </div>
  </body>
</html>
