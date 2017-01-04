<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>账号管理主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  <frameset id="contribRateFrame" rows="26,*" frameborder="0" border="0px" scrolling="NO">
  	  <frame name="contribRate-top" src="page/hr/shebao/contribRate-top.jsp" noresize="" scrolling="NO">
	 	<frame name="contribRateOpFrame" src="contribRate_list.action" noresize="" scrolling="NO">
	  
  </frameset><noframes></noframes>
</html>
