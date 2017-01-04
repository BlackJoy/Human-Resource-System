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

<title>账号列表页</title>

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
				
				
				if($("#orgId").val().length==0){
				alert("请在左侧选择一个部门");
			}else {
			
				$("#socSecBaseForm").attr("action", "socSecBase_toAdd.action");
				alert($("#socSecBaseForm").attr("action"));
				$("#socSecBaseForm")[0].submit();
			}
				
			});
			$("#doUpdate").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个员工");
				} else if (count > 1) {
					alert("只能对单个员工修改账户");
				} else {
					$("#socSecBaseForm").attr("action", "socSecBase_toUpdate.action");
					$("#socSecBaseForm")[0].submit();
				}
			});
			$("#doDelete").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个员工");
				} else if (count > 1) {
					alert("只能对单个员工的账户");
				} else {
					if (confirm("是否确定删除？")) {
						$("#socSecBaseForm").attr("action", "socSecBase_delete.action");
						$("#socSecBaseForm")[0].submit();
					}
				}
			});
		
		});
		function go(pageIndex){
			var oid = document.getElementById("orgId").value;
			self.location.href="<%=basePath%>socSecBase_list.action?pageIndex="
				+ pageIndex + "&orgId=" + oid;
	}
</script>

</head>

<body>
	<s:if test="#request.socSecBaseMsg != null">
		<script type="text/javascript" language="javascript">
			alert("<s:property value='#request.socSecBaseMsg' escape='false'/>");
		</script>
	</s:if>
	<table border="0" width="100%" style="background: rgb(192, 212, 240);"
		cellpadding="0" cellspacing="0">
		<tr>
			<td height="30px" valign="middle" style="padding-left: 30px;"><input
				id="doAdd" type="button" value="增加" <ct:btnEnable id="2.1.1.1" /> />&nbsp;&nbsp;
				<input id="doUpdate" type="button" value="修改"
				<ct:btnEnable id="2.1.1.2" /> />&nbsp;&nbsp; <input id="doDelete"
				type="button" value="删除" <ct:btnEnable id="2.1.1.3" /> />&nbsp;&nbsp;
				
			</td>
		</tr>
	</table>
	<form name="socSecBaseForm" id="socSecBaseForm" action="" method="post">
		<input type="hidden" id="orgId" name="orgId"
			value="<s:property value="#request.orgId"/>" />
			
			 <input type="hidden"
			id="pageIndex" name="pageIndex"
			value="<s:property value="pageIndex"/>" />
		<table border="0" width="100%"
			style="background: #fafafa; font-size: 12px;" cellpadding="0"
			cellspacing="1">
			<tr
				style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
				<td width="25px"></td>
				<td>员工姓名</td>
				<td>社保账号</td>				
				<td>社保基数</td>
				<td>当前项</td>
			</tr>
			<s:set name="rowCount" value="0"></s:set>
			<s:iterator var="socSecBase" value="#request.socSecBase_list_page.list"
				status="i">
				<s:if test="#i.odd">
					<tr style="background: rgb(240, 240, 240); height: 22px;">
				</s:if>
				<s:else>
					<tr style="background: rgb(248, 248, 248); height: 22px;">
				</s:else>
				<td align="center"><input class="ckb" name="Id"
					type="checkbox" value="<s:property value="ID"/>" /></td>
				<td><s:property value="EMPLOYEENAME" /></td>
				<td><s:property value="SSID" /></td>
				<td><s:property value="SAFETY_BASE" /></td>
				<td><s:property value="CURITEM" /></td>
				</tr>
				<s:set name="rowCount" value="#i.count"></s:set>
			</s:iterator>
			<s:iterator begin="#rowCount+1"
				end="#request.socSecBase_list_page.pageSize" status="i">
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
				</tr>
			</s:iterator>
		</table>
	</form>
	<s:set name="pageCount"
		value="(#request.socSecBase_list_page.rowCount+#request.socSecBase_list_page.pageSize-1)/#request.socSecBase_list_page.pageSize"></s:set>
	<table border="0" width="100%" height="24px"
		style="background: rgb(192, 212, 240); font-size: 12px;"
		cellpadding="0" cellspacing="0">
		<tr>
			<td align="right">共有&nbsp;<s:property
					value="#request.socSecBase_list_page.rowCount" />&nbsp;条信息&nbsp;&nbsp;
				<s:property value="#request.socSecBase_list_page.pageSize" />条/页&nbsp;&nbsp;
				页次：<span style="color: red;"><s:property
						value="#request.socSecBase_list_page.pageIndex" /></span>/<s:property
					value="#pageCount" />页&nbsp;&nbsp; <s:if
					test="#request.socSecBase_list_page.pageIndex == 1">首页</s:if> <s:else>
					<a class="page_l" href="javascript:go(1)">[首页]</a>
				</s:else> <s:if test="#request.socSecBase_list_page.pageIndex == 1">上一页</s:if> <s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#request.socSecBase_list_page.pageIndex-1" />)">[上一页]</a>
				</s:else> <s:if test="#request.socSecBase_list_page.pageIndex >= #pageCount">下一页</s:if>
				<s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#request.socSecBase_list_page.pageIndex+1" />)">[下一页]</a>
				</s:else> <s:if test="#request.socSecBase_list_page.pageIndex >= #pageCount">尾页</s:if>
				<s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#pageCount" />)">[尾页]</a>
				</s:else>
			</td>
		</tr>
	</table>
</body>
</html>
