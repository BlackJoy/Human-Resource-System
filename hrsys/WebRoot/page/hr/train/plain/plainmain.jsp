<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>培训申请页面</title>
  </head>
  <frameset id="trainPlainFrame" rows="26,*" frameborder="0" border="0px" scrolling="NO">
  	  <frame name="trainPlain-top" src="page/hr/train/plain/trainPlain-top.jsp" noresize="" scrolling="NO">
	  	<frame name="trainPlainOpFrame" src="trainPlain_trainPlainList.action" noresize="" scrolling="NO">
	</frameset><noframes></noframes>
</html>
