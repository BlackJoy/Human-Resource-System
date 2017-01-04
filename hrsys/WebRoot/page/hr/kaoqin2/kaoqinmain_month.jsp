<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>月度考勤汇总</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  <frameset id="accountFrame" rows="26,*" frameborder="0" border="0px" scrolling="NO">
  	  <frame name="kaoqin-top" src="page/hr/kaoqin2/kaoqin-top.jsp" noresize="" scrolling="NO">
	  <frameset id="accountFrame" cols="155,*" bordercolor="rgb(191, 219, 255)" frameborder="1" border="10px" scrolling="NO">
	  	<frame name="orgFrame" src="org_listByDtreeShowPara.action?para1=kaoqinmonth_list" noresize="" scrolling="auto">
	  	<frame name="zhangtaoOpFrame" src="kaoqinmonth_list.action" noresize="" scrolling="NO">
	  </frameset>
  </frameset><noframes></noframes>
</html>
