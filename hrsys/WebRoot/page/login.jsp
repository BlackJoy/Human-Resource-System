<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="style/login.css" rel="stylesheet" />
  </head>
  
  <body>
  	<s:if test="#request.loginMsg != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.loginMsg' escape='false'/>");
  		</script>
  	</s:if>
    <div class="container">
    <div class="l_center">    
        <div class="login_row2"></div>
        <div class="login_row3">
            <form action="user_login.action" name="loginForm" method="post">
                <input class="l_name" type="text" name="user.username" /><input class="l_pass" type="password" name="user.password" />
                <input type="submit" class="l_subbtn" value="" /><input type="reset" class="l_resetbtn" value=""/>
            </form>
        </div>
        <div class="login_row4"></div>
    </div>
</div>
  </body>
</html>