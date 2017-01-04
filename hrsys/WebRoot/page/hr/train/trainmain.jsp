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
  <frameset id="trainFrame" rows="26,*" frameborder="0" border="0px" scrolling="NO">
  	  <frame name="trainApply-top" src="page/hr/train/trainApply-top.jsp" noresize="" scrolling="NO">
	<frameset id="trainCtnFrame" cols="155,*" bordercolor="rgb(191, 219, 255)" frameborder="1" border="10px" scrolling="NO">
	      <frame name="orgFrame" src="page/hr/train/trainorgtree.jsp"  scrolling="NO">
	      <frame name="trainOpFrame" src="train_trainList.action" noresize="" scrolling="NO">
	</frameset>
  </frameset>
  <noframes></noframes>
</html>
