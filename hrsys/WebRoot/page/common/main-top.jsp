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
    
    <title>主页头部</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	-->
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<style type="text/css">
	a:link,a:hover,a:visited {text-decoration: none; color:#FFF; }
	
	</style>
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("a.exit_a").click(function(){
				if (confirm("是否确定退出?")) {
					window.parent.location.href="<%=basePath%>user_logout.action";
				}
			});
		});
	</script>
  </head>
  
  <body style="background: url(images/rptXbg.jpg) repeat-x; height:87px;">
    <table style="height:87px; width:100%; background: url(images/logo.jpg) no-repeat;">
    	<tr>
    		<td align="right" valign="bottom" style="padding:0px 10px 10px 0px; color:#FFF;">
    		<a href="<%=basePath%>approve_backlogApprove.action?eid=<s:property value="#session.curr_employee.id" />" target="mainFrame">待审批流程</a>&nbsp;&nbsp;&nbsp;&nbsp;
    		<a href="<%=basePath%>approve_reviewApprove.action?eid=<s:property value="#session.curr_employee.id" />" target="mainFrame">待传阅流程</a>&nbsp;&nbsp;&nbsp;&nbsp;
    		<a href="<%=basePath%>approve_completedApprove.action?eid=<s:property value="#session.curr_employee.id" />" target="mainFrame">已批阅流程</a>&nbsp;&nbsp;&nbsp;&nbsp;
    		当前用户：<s:property value="#session.curr_employee.employeeName" />&nbsp;&nbsp;&nbsp;&nbsp;
    		<a class="exit_a" href="javascript:return false;" style="display:inline-block; width:30px; height:16px; line-height:16px; background: url(images/exit_ico.gif) no-repeat; padding-left:16px; color:#FFF;">退出</a>
    		</td>
    	</tr>
    </table>
  </body>
</html>
