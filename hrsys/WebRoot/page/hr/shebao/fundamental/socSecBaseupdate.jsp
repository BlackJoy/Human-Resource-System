<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

<title>My JSP 'accountadd.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="style/common.css">
<script type="text/javascript" language="javascript"
	src="js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#btn_add").click(function(){
				var msg = "";
				if($("#foundId").val().length == 0){
					msg += "工资项名称不能为空\n";
				} 
				if($("#ssId").val().length == 0){
					msg += "工资项编码不能为空\n";
				}
				
				
				
				if (msg.length != 0) {
					alert(msg);
				} else {
					//alert("将要更新一个");
					$("#ssInfoUpdateForm")[0].submit();
				}
			});
			
			
			$("#btn_return").click(function(){
				var pi = $("#pageIndex").val();
				var oId = $("#orgId").val();
				//alert("返回");
				self.location.href="<%=basePath%>ssInfo_list.action?pageIndex=" + pi+"&orgId="+oId;
										
			});
		});
			
			

</script>

</head>

<body>
	<s:if test="#request.accountMsg != null">
		<script type="text/javascript" language="javascript">
			alert("<s:property value='#request.accountMsg' escape='false'/>");
		</script>
	</s:if>
	<form id="ssInfoUpdateForm" action="ssInfo_update.action"
		method="post">
		<input type="hidden" id="orgId" name="orgId"
			value="<s:property value="orgId"/>" /> <input type="hidden"
			id="pageIndex" name="pageIndex"
			value="<s:property value="pageIndex"/>" />  <input type="hidden"
			id="pageIndex" name="ssInfo.employeeCode" 
			value="<s:property value="ssInfo.employeeCode"/>" /> 
			 <input type="hidden"
			id="pageIndex" name="ssInfo.Id" 
			value="<s:property value="ssInfo.Id"/>" /> 
		<table border="0" width="100%" style="background: rgb(192, 212, 240);"
			cellpadding="0" cellspacing="0">
			<tr>
				<td height="30px" valign="middle" style="padding-left: 30px;">
					<input id="btn_add" type="button" value="保存" />&nbsp;&nbsp; <input id="btn_return"
					type="button" value="返回" />&nbsp;&nbsp;
				</td>
			</tr>
		</table>

		<table border="0" width="100%"
			style="background: rgb(240, 240, 240); font-size: 12px;"
			cellpadding="0" cellspacing="2">
			<tr height="30px;">
				<td width="15%" align="right"><span style="color: red;">*</span>员工姓名：</td>
				<td width="30%"><input type="text" name="ssInfo.employeeName "
					id="employeeName"  value='<s:property value="ssInfo.employeeName"/>' Readonly="true"/></td>
			</tr>
			<tr>
				<td width="15%" align="right"><span style="color: red;">*</span>社保ID：</td>
				<td width="30%"><input type="text" name="ssInfo.ssId"
					id="ssId"  value='<s:property value="ssInfo.ssId"/>' /></td>
			</tr>
				<td width="15%" align="right"><span style="color: red;">*</span>公积金ID：</td>
				<td width="30%"><input type="text" name="ssInfo.foundId"
					id="foundId"  value='<s:property value="ssInfo.foundId"/>' /></td>
			<tr>
				<td width="15%" align="right"><span style="color: red;">*</span>备注：</td>
				<td width="30%"><input type="text" name="ssInfo.remark"
					id="remark"  value='<s:property value="ssInfo.remark"/>' /></td>
				<td width="10%">&nbsp;</td>
			</tr>
		

		<%-- 	<tr height="30px;">
				<td align="right"><span style="color: red;">*</span>是否激活：</td>
				<td><input type="radio" name="ssInfo.iactive"
					<s:if test="ssInfo.iactive == 0">checked="checked"</s:if> value="0"
					value="0" />否&nbsp;&nbsp; <input type="radio" name="ssInfo.iactive"
					<s:if test="ssInfo.iactive == 1">checked="checked"</s:if> value="1" />是</td>
				<td align="right">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr> --%>
			
			
				
		</table>
	

	</form>
</body>
</html>
