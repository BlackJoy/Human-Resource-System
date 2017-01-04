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
				//alert("岗位增加");
				var msg = "";
				if($("#postName").val().length == 0){
					msg += "岗位名称不能为空\n";
				}
				if($("#postCod").val().length == 0){
					msg += "岗位编码不能为空\n";
				}
				if($("#postType").val().length == 0){
					msg += "岗位类型不能为空\n";
				}
				if($("#postDuty").val().length == 0){
					msg += "岗位职责不能为空\n";
				}
				
				
				if (msg.length != 0) {
					alert(msg);
				} else {
					//alert("将要插入一个岗位");
					$("#postAddForm")[0].submit();
				}
			});
			$("#btn_return").click(function(){
				var oid = $("#orgId").val();
				var pi = $("#pageIndex").val();
				self.location.href="<%=basePath%>post_list.action?orgId="
													+ oid + "&pageIndex=" + pi;
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
	<form id="postAddForm" action="post_add.action" method="post">
		<input type="hidden" id="orgId" name="orgId"
			value="<s:property value="orgId"/>" /> <input type="hidden"
			id="empId" name="empId" value="<s:property value="empId"/>" /> <input
			type="hidden" id="pageIndex" name="pageIndex"
			value="<s:property value="pageIndex"/>" />
		<table border="0" width="100%" style="background: rgb(192, 212, 240);"
			cellpadding="0" cellspacing="0">
			<tr>
				<td height="30px" valign="middle" style="padding-left: 30px;">
					<input id="btn_add" type="button" " value="保存" />&nbsp;&nbsp; <input
					type="reset" " value="重置" />&nbsp;&nbsp; <input id="btn_return"
					type="button" value="返回" />&nbsp;&nbsp;
				</td>
			</tr>
		</table>
		<table border="0" width="100%"
			style="background: rgb(240, 240, 240); font-size: 12px;"
			cellpadding="0" cellspacing="2">
			<tr height="30px;">
				<td width="15%" align="right"><span style="color: red;">*</span>岗位名：</td>
				<td width="30%"><input type="text" name="post.postName"
					id="postName" /></td>
				<td width="15%" align="right">&nbsp;</td>
				<td width="20%">&nbsp;</td>
				<td width="20%">&nbsp;</td>
			</tr>
			<tr height="30px;">
				<td align="right"><span style="color: red;">*</span>岗位编码：</td>
				<td><input type="text" name="post.postCod" id="postCod" /></td>
				<td align="right"><span style="color: red;">*</span>岗位类型：</td>
				<td><input type="text" name="post.postType" id="postType" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr height="30px;">
				<td width="15%" align="right"><span style="color: red;">*</span>岗位职责：</td>
				<td width="30%"><input type="text" name="post.postDuty"
					id="postDuty" /></td>
				<td width="15%" align="right">&nbsp;</td>
				<td width="20%">&nbsp;</td>
				<td width="20%">&nbsp;</td>
			</tr>
			<tr height="30px;">
				<td align="right"><span style="color: red;">*</span>是否关键岗位：</td>
				<td><input type="radio" name="post.iskey" value="0" />否&nbsp;&nbsp;
					<input type="radio" name="post.iskey" checked="checked" value="1" />是
				</td>
				<td align="right">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>
