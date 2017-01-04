<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>员工选择页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<frameset id="empFrame" cols="155,*" bordercolor="rgb(191, 219, 255)"
	frameborder="1" border="10px" scrolling="NO">
	<frame name="orgFrame" src="page/sysmg/approve/orgtree.jsp" noresize=""
		scrolling="auto">
	<frame name="empsFrame" src="approve_empList.action" noresize=""
		scrolling="auto">
</frameset>
</html>
