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
    
    <title>录用信息</title>
    
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
				self.location.href="<%=basePath%>employ_luyonglist.action";
			});
		});
	</script>
  </head>
  
  <body>
  	<s:if test="#request.employMsg != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.employMsg' escape='false'/>");
  		</script>
  	</s:if>
  	<table border="0" width="100%" style="background: rgb(192, 212, 240) url(images/bg_title01.gif) repeat-x;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td>
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
		    		<td width="10px"></td>
		    		<td height="26px" width="7px"><img src="images/yq_l.gif"/></td>
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;录用管理&nbsp;</td>
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
    		<td width="15%" align="right"><span style="color:red;">*</span>姓名：</td>
    		<td width="25%"><s:property value="#request.curr_luyong.EMPLOYEENAME"/></td>
    		<td width="15%" align="right"><span style="color:red;">*</span>性别：</td>
    		<td><s:if test="#request.curr_luyong.GENDER == 0">女</s:if><s:else>男</s:else></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>身份证：</td>
    		<td><s:property value="#request.curr_luyong.IDENTITYCARD"/></td>
    		<td align="right"><span style="color:red;">*</span>学历：</td>
    		<td><s:property value="#request.curr_luyong.EDU"/></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>专业：</td>
    		<td><s:property value="#request.curr_luyong.SPECIALTY"/></td>
    		<td align="right"><span style="color:red;">*</span>毕业院校：</td>
    		<td><s:property value="#request.curr_luyong.GRADUATESCHOOL"/></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>地址：</td>
    		<td><s:property value="#request.curr_luyong.ADDRESS"/></td>
    		<td align="right"><span style="color:red;">*</span>联系电话：</td>
    		<td><s:property value="#request.curr_luyong.PHONE"/></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>所属组织：</td>
    		<td><s:property value="#request.curr_luyong.ORGSHORTNAME"/></td>
    		<td align="right"><span style="color:red;">*</span>录用职位：</td>
    		<td><s:property value="#request.curr_luyong.PO"/></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>应聘时间：</td>
    		<td><s:property value="#request.curr_luyong.LYT"/></td>
    	</tr>
    </table>
  </body>
</html>
