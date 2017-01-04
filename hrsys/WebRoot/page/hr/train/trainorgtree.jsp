<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="ct" uri="/custom-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>组织树页面</title>
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("div.org_parent").click(function(event){
				if($(this).nextAll("ul:first").css("display")=="none"){
					$(this).nextAll("ul:first").css("display","block");
					if ($(this).attr("isend")=="true") {
						$(this).find("img:first").attr("src","images/M2.gif");
					} else {
						$(this).find("img:first").attr("src","images/P2.gif");
					}
				} else {
					$(this).nextAll("ul:first").css("display","none");
					if ($(this).attr("isend")=="true") {
						$(this).find("img:first").attr("src","images/M1.gif");
					} else {
						$(this).find("img:first").attr("src","images/P1.gif");
					}
				}
				event.stopPropagation();
			});
		});
	</script>
  </head>
  
  <body>
  	<div><img src="images/root.gif" align="middle"">组织结构</div>
    <ct:orgTreeTag action="train_trainList.action" target="trainOpFrame"/>
  </body>
</html>
