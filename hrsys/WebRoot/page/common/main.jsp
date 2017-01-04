<%@ page language="java" import="java.util.*,com.sys.login.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if(session.getAttribute(ILoginConstant.CURRENT_USER)==null){
	response.sendRedirect(basePath+"index.jsp");
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>系统首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	-->
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<link rel="stylesheet" type="text/css" href="style/main.css">
  </head>

  <frameset id="wholeFrame" rows="88,*" bordercolor="rgb(191, 219, 255)" frameborder="1" border="5px" scrolling="NO">
  	<frame name="topFrame" src="page/common/main-top.jsp" noresize="" scrolling="NO">
  	<frameset name="buttomFrame" cols="155,*" bordercolor="rgb(191, 219, 255)" frameborder="1" border="5px">
  		<frame name="menuFrame" src="leftMenu_getLeft.action?menuId=" noresize="" scrolling="auto">
  		<frame name="mainFrame" src="page/common/init-content.jsp" scrolling="YES">
  	</frameset>
  </frameset><noframes></noframes>
</html>
