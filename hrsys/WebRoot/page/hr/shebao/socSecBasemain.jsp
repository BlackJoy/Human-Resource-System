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
  <!-- <frameset id="socSecBaseFrame" rows="26,*" frameborder="0" border="0px" scrolling="NO">
  	  <frame name="socSecBase-top" src="page/hr/shebao/socSecBase-top.jsp" noresize="" scrolling="NO">	 	
	  <frame name="socSecBaseOpFrame" src="socSecBase_list.action" noresize="" scrolling="NO">
  </frameset><noframes></noframes>
   -->
  <frameset id="socSecBaseFrame" rows="26,*" frameborder="0" border="0px"
	scrolling="NO">
	<frame name="socSecBase-top" src="page/hr/shebao/socSecBase-top.jsp"
		noresize="" scrolling="NO">
	<frameset id="socSecBaseFrame" cols="155,*"
		bordercolor="rgb(191, 219, 255)" frameborder="1" border="10px"
		scrolling="NO">
		<frame name="orgFrame" src="page/hr/shebao/orgtree.jsp" noresize=""
			scrolling="auto">
		<frame name="socSecBaseOpFrame" src="socSecBase_list.action" noresize=""
			scrolling="NO">
	</frameset>
</frameset>
</html>
