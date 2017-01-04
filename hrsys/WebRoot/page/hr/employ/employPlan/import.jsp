<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'import.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="style/common.css">
<script type="text/javascript" language="javascript"
	src="js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#doReturn").click(function() {

			$("#importForm").attr("action", "employPlan_employPlanList.action");
			$("#importForm")[0].submit();

		});
		$("#doImport").click(function() {
			alert("import");
			$("#importForm").attr("action", "employPlan_fromExcel.action");
			$("#importForm")[0].submit();

		});
	});
</script>
</head>

<body>
	<table border="0" width="100%" style="background: rgb(192, 212, 240);"
		cellpadding="0" cellspacing="0">
		<tr>
			<td><input id="doReturn" type="button" value="返回" />&nbsp;&nbsp;
				<input id="doImport" type="button" value="确定" />&nbsp;&nbsp;
			 </td>

		</tr>
	</table>




	<form enctype="multipart/form-data" method="post" action="" name="importForm" id="importForm" >
		
		<table border="0" width="100%"
			style="background: #fafafa; font-size: 12px;" cellpadding="0"
			cellspacing="1">
			<tr>
			<td><input type="hidden" id="orgId" name="orgId" value="<s:property value="#request.orgId"/>"/></td>
				<td><input type="file" name="excel"/></td>
				<td height="30px" valign="middle">
			</tr>

		</table>
	</form>
</body>
</html>
