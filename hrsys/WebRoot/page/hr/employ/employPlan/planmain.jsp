<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>应聘信息录入主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  <frameset id="planFrame" rows="26,*" frameborder="0" border="0px" scrolling="NO">
  	  <frame name="plan-top" src="page/hr/employ/employPlan/plan-top.jsp" noresize="" scrolling="NO">
	  <frameset id="planCtnFrame" cols="155,*" bordercolor="rgb(191, 219, 255)" frameborder="1" border="10px" scrolling="NO">
	  	<frame name="orgFrame" src="org_listByDtreeShowPara.action?para1=employPlan_employPlanList" noresize="" scrolling="auto">
	  	<frame name="zhangtaoOpFrame" src="employPlan_employPlanList.action" noresize="" scrolling="NO">
	  </frameset>
  </frameset><noframes></noframes>
</html>
