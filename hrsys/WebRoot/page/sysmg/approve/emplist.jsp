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
    
    <title>员工列表页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#doConfirm").click(function(){
				if($(".rdi:checked").length > 0){
					var rv = $(".rdi:checked:first").val();
					window.parent.returnValue = rv;
					window.parent.close();
				} else {
					alert("请选择一个员工");
				}
			});
			$("#doClose").click(function(){
				window.parent.returnValue = "";
				window.parent.close();
			});
		});
	</script>
  </head>
  
  <body>
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="doConfirm" type="button" value="确定" />&nbsp;&nbsp;
    			<input id="doClose" type="button" value="关闭" />
    		</td>
    	</tr>
    </table>
  	<input type="hidden" id="orgId" name="orgId" value="<s:property value="#request.orgId"/>"/>
  	<input type="hidden" id="pageIndex" name="pageIndex" value="<s:property value="pageIndex"/>"/>
    <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
    		<td width="30px">&nbsp;</td>
    		<td>员工编号</td>
    		<td>员工姓名</td>
    		<td>所在组织</td>
    		<td>职位</td>
    	</tr>
    	<s:set name="rowCount" value="0"></s:set>
    	<s:iterator var="account" value="#request.emplist_page.list" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>
				<td align="center"><input class="rdi" name="empId" type="radio" value="<s:property value="ID"/>,<s:property value="EMPLOYEENAME"/>"/></td>
				<td><s:property value="EMPLOYEECODE"/></td>
				<td><s:property value="EMPLOYEENAME"/></td>
				<td><s:property value="ORGSHORTNAME"/></td>
				<td><s:property value="POSITION" /></td>
    		</tr>
    	</s:iterator>
    </table>
  </body>
</html>
