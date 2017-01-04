<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="ct" uri="/custom-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>招聘计划书信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<!-- 
	 -->
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="js/Calendar2.js"></script>
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#btn_return").click(function(){
				self.location.href="<%=basePath%>employPlan_employPlanList.action";
			});
		});
	</script>
  </head>
  
  <body>
  	<s:if test="#request.planMsg != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.planMsg' escape='false'/>");
  		</script>
  	</s:if>
  	<table border="0" width="100%" style="background: rgb(192, 212, 240) url(images/bg_title01.gif) repeat-x;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td>
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
		    		<td width="10px"></td>
		    		<td height="26px" width="7px"><img src="images/yq_l.gif"/></td>
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;应聘信息管理&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="btn_return" type="button" value="返回" />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <table border="0" width="100%" style="background: rgb(240, 240, 240); font-size: 12px;" cellpadding="0" cellspacing="2">
    	<tr>
    		<td width="15%" align="right"><span style="color:red;">*</span>招聘书标题：</td>
    		<td width="25%"><s:property value="#planDetial.name"/></td>
    		<td width="15%" align="right"><span style="color:red;">*</span>招聘职位：</td>
    		<td width="25%"><s:property value="#planDetial.position"/></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>招聘人数：</td>
    		<td width="25%"><s:property value="#planDetial.num"/></td>
    		<td align="right"><span style="color:red;">*</span>要求学历：</td>
    		<td width="25%"><s:property value="#planDetial.major"/></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>性别要求：</td>
    		<td width="25%"><s:property value="#planDetial.sex"/></td>
    		<td align="right"><span style="color:red;">*</span>英语水平要求：</td>
			<td width="25%"><s:property value="#planDetial.englishDegree"/></td>    
		</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>是否需要培训：</td>
    		<td width="25%"><s:property value="#planDetial.isTrain"/></td>
    		<td align="right"><span style="color:red;">*</span>职位要求：</td>
    		<td width="25%"><s:property value="#planDetial.positionAsk"/></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>入职时间要求：</td>
    		<td width="25%"><s:property value="#planDetial.wTime"/></td>
    		<td align="right"><span style="color:red;">*</span>薪水：</td>
    		<td width="25%"><s:property value="#planDetial.forSalary"/></td>
    	</tr>
    </table>
  </body>
</html>
