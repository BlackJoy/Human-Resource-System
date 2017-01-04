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
<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#doAdd").click(function(){
			//-----------添加之前必须选择一个部门start---------------
			//alert($("#orgId").val().length==0);
			
				$("#wageItemForm").attr("action", "wageItem_toAdd.action");
				alert($("#wageItemForm").attr("action"));
				$("#wageItemForm")[0].submit();
			
			});
			
			
			$("#doUpdate").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个工资项");
				} else if (count > 1) {
					alert("只能对单个工资项操作");
				} else {
					$("#wageItemForm").attr("action", "wageItem_toUpdate.action");
					$("#wageItemForm")[0].submit();
				}
			});
		
			$("#doDelete").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个工资项");
				} else if (count > 1) {
					alert("只能对单个工资项删除");
				} else {
					if (confirm("是否确定删除该工资项？")) {
						$("#wageItemForm").attr("action", "wageItem_delete.action");
						alert($("#wageItemForm").attr("action"));
						$("#wageItemForm")[0].submit();
					}
				}
			});
			$("#doEdit").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个工资项");
				} else if (count > 1) {
					alert("只能对单个工资项编辑");
				} else {
					
						$("#wageItemForm").attr("action", "wageItem_toEdit.action");
						alert($("#wageItemForm").attr("action"));
						$("#wageItemForm")[0].submit();
				}
			});
		});
		function go(pageIndex){
			var oid = document.getElementById("orgId").value;
			self.location.href="<%=basePath%>wageItem_list.action?pageIndex="
				+ pageIndex + "&orgId=" + oid;
	}
</script>

</head>

<body>

	<table border="0" width="100%" style="background: rgb(192, 212, 240);"
		cellpadding="0" cellspacing="0">
		<tr>
			<td height="30px" valign="middle" style="padding-left: 30px;"><input
				id="doAdd" type="button" value="增加" <ct:btnEnable id="1.4.2.1" /> />&nbsp;&nbsp;
				 <input id="doDelete"
				type="button" value="删除" <ct:btnEnable id="1.4.2.2" /> />&nbsp;&nbsp;
				 <input id="doEdit"
				type="button" value="添加到帐套" <ct:btnEnable id="1.4.2.3" /> />&nbsp;&nbsp;
				 <input id="doUpdate"
				type="button" value="更新" <ct:btnEnable id="1.4.2.4" /> />&nbsp;&nbsp;
			</td>
		</tr>
	</table>
	<form name="wageItemForm" id="wageItemForm" action="" method="post">
		<input type="hidden" id="orgId" name="orgId"
			value="<s:property value="#request.orgId"/>" /> <input type="hidden"
			id="pageIndex" name="pageIndex"
			value="<s:property value="pageIndex"/>" />
		<table border="0" width="100%"
			style="background: #fafafa; font-size: 12px;" cellpadding="0"
			cellspacing="1">
			<tr
				style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
				<td width="30px"></td>
				<td>工资项编码</td>
				<td>工资项名称</td>
				<td>创建日期</td>
				<td>操作(+/-)</td>
				<td>是否被激活</td>
			</tr>
			<s:set name="rowCount" value="0"></s:set>
			<s:iterator var="account" value="#request.wageitem_list_page.list"
				status="i">
				<s:if test="#i.odd">
					<tr style="background: rgb(240, 240, 240); height: 22px;">
				</s:if>
				<s:else>
					<tr style="background: rgb(248, 248, 248); height: 22px;">
				</s:else>
				<td align="center"><input class="ckb" name="wageId"
					type="checkbox" value="<s:property value="WAGE_ID"/>" /></td>
				<td><s:property value="WAGE_NO" /></td>
				<td><s:property value="WAGE_NAME" /></td>
				<td><s:property value="CREATE_DAT" /></td>
				<td><s:property value="OPER_NAM" /></td>
				<td><s:if test="IACTIVE==0">否</s:if><s:elseif test="IACTIVE==1">是</s:elseif></td>
				</tr>
				<s:set name="rowCount" value="#i.count"></s:set>
			</s:iterator>
			
			<s:iterator begin="#rowCount+1"
				end="#request.wageitem_list_page.pageSize" status="i">
				<s:if test="(#i.count+#rowCount) % 2 != 0">
					<tr style="background: rgb(240, 240, 240); height: 22px;">
				</s:if>
				<s:else>
					<tr style="background: rgb(248, 248, 248); height: 22px;">
				</s:else>
				<td align="center">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				</tr>
			</s:iterator>
		</table>
	</form>
	<s:set name="pageCount"
		value="(#request.wageitem_list_page.rowCount+#request.wageitem_list_page.pageSize-1)/#request.wageitem_list_page.pageSize"></s:set>
	<table border="0" width="100%" height="24px"
		style="background: rgb(192, 212, 240); font-size: 12px;"
		cellpadding="0" cellspacing="0">
		<tr>
			<td align="right">共有&nbsp;<s:property
					value="#request.wageitem_list_page.rowCount" />&nbsp;条信息&nbsp;&nbsp; <s:property
					value="#request.wageitem_list_page.pageSize" />条/页&nbsp;&nbsp; 页次：<span
				style="color: red;"><s:property
						value="#request.wageitem_list_page.pageIndex" /></span>/<s:property
					value="#pageCount" />页&nbsp;&nbsp; <s:if
					test="#request.wageitem_list_page.pageIndex == 1">首页</s:if> <s:else>
					<a class="page_l" href="javascript:go(1)">[首页]</a>
				</s:else> <s:if test="#request.wageitem_list_page.pageIndex == 1">上一页</s:if> <s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#request.wageitem_list_page.pageIndex-1" />)">[上一页]</a>
				</s:else> <s:if test="#request.wageitem_list_page.pageIndex >= #pageCount">下一页</s:if>
				<s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#request.wageitem_list_page.pageIndex+1" />)">[下一页]</a>
				</s:else> <s:if test="#request.wageitem_list_page.pageIndex >= #pageCount">尾页</s:if>
				<s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#pageCount" />)">[尾页]</a>
				</s:else>
			</td>
		</tr>
	</table>
</body>
</html>
