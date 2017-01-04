<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="ct" uri="/custom-tags"%>
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

<title>岗位列表页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="style/common.css">
<style type="text/css">
a.page_l {
	text-decoration: none;
	color: blue;
}

a.page_l:hover {
	text-decoration: underline;
	color: blue;
}

a.page_l:visited {
	text-decoration: none;
	color: blue;
}
</style>
<script type="text/javascript" language="javascript"
	src="js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" language="javascript"
	src="js/jquery.form.js"></script>
<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#btn_return").click(function(){

				alert("返回");
				self.location.href="<%=basePath%>allEmpWage_list.action";
										
			});
		
			
			
			
			$("#btn_export").click(function(){
				//alert("export");
				$("#showEmpWageForm").attr("action", "allEmpWage_export.action");
				$("#showEmpWageForm")[0].submit();
				
			
			});

	});
</script>

</head>

<body>

	<table border="0" width="100%" style="background: rgb(192, 212, 240);"
		cellpadding="0" cellspacing="0">
		<tr>
			<td height="30px" valign="middle" style="padding-left: 30px;"><input
				id="btn_return" type="button" value="返回" />&nbsp;&nbsp;</td>
			<td height="30px" valign="middle" style="padding-left: 30px;"><input
				id="btn_export" type="button" value="导出" />&nbsp;&nbsp;</td>
		</tr>
	</table>
	<form name="showEmpWageForm" id="showEmpWageForm" action=""
		method="post">
		<input type="hidden" name="chooseZtId" value="<s:property value="chooseZtId"/>"/>
		<input type="hidden" name="allEmpWageListJson" value="<s:property value="#request.allEmpWageListJson"/>"/>
		<input type="hidden" name="wageItemListJson" value="<s:property value="#request.wageItemListJson"/>"/>
		<input type="hidden" id="orgId" name="orgId"
			value="<s:property value="#request.orgId"/>" /> <input type="hidden"
			id="pageIndex" name="pageIndex"
			value="<s:property value="pageIndex"/>" />
		<table border="0" width="100%"
			style="background: #fafafa; font-size: 12px;" cellpadding="0"
			cellspacing="1">
			<tr
				style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
				
				<s:iterator value="#request.allEmpWageList[0]" id="m">
				<s:iterator value="#m">
				
				<td><s:property value="key"/></td>
				</s:iterator>
				</s:iterator>
			</tr>
			<s:iterator var="account" value="#request.allEmpWageList" status="i" id="m">
				<s:if test="#i.odd">
					<tr style="background: rgb(240, 240, 240); height: 22px;">
				</s:if>
				<s:else>
					<tr style="background: rgb(248, 248, 248); height: 22px;">
				</s:else>
				
				<s:iterator value="#m">
				
				<td><s:property value="value"/></td>
				</s:iterator>
				<td></td>
				</tr>
			</s:iterator>


		</table>
		
		
	</form>
</body>
</html>
