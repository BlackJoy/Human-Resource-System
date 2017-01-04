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

<title>应聘信息列表页</title>
<link rel="stylesheet" type="text/css" href="style/common.css">
<style type="text/css">
a.page_l,a.info {
	text-decoration: none;
	color: blue;
}

a.page_l:hover,a.info:hover {
	text-decoration: underline;
	color: blue;
}

a.page_l:visited,a.info:visited {
	text-decoration: none;
	color: blue;
}
</style>
<script type="text/javascript" language="javascript"
	src="js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#doAdd").click(function(){
				var oid = $("#orgId").val();
				location.href="<%=basePath%>train_toAddTrain.action?orgId="+oid;
			});
			$("#doUpdate").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个培训申请");
				} else if (count > 1) {
					alert("只能对单个培训申请进行修改");
				} else {
					$("#trainForm").attr("action", "train_toUpdateTrain.action");
					$("#trainForm")[0].submit();
				}
			});
			$("#doDelete").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个培训申请 信息");
				} else if (count > 1) {
					alert("只能对单个培训申请进行删除");
				} else {
					if (confirm("是否确定删除该培训申请信息？")) {
						$("#trainForm").attr("action", "train_doDeleteTrain.action");
						$("#trainForm")[0].submit();
					}
				}
			});
			$("#doShenPi").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个培训申请信息");
				} else if (count > 1) {
					alert("只能对单个培训申请信息进行操作");
				} else {					
					
					$("#trainForm").attr("action", "train_doFlow.action");
							$("#trainForm")[0].submit();
				}
			});
		});
		function go(pageIndex){
			var oid = document.getElementById("orgId").value;
			self.location.href="<%=basePath%>train_trainList.action?pageIndex="
				+ pageIndex + "&orgId=" + oid;
	}
</script>

</head>

<body>
	<s:if test="#request.employMsg != null">
		<script type="text/javascript" language="javascript">
			alert("<s:property value='#request.trainMsg' escape='false'/>");
		</script>
	</s:if>
	<table border="0" width="100%" style="background: rgb(192, 212, 240);"
		cellpadding="0" cellspacing="0">
		<tr>
			<td height="30px" valign="middle" style="padding-left: 30px;"><input
				id="doAdd" type="button" value="增加" <ct:btnEnable id="1.10.1.1" /> />&nbsp;&nbsp;
				<input id="doUpdate" type="button" value="修改"
				<ct:btnEnable id="1.10.1.2" /> />&nbsp;&nbsp; 
				<input id="doDelete" type="button" value="删除" <ct:btnEnable id="1.10.1.3" /> />&nbsp;&nbsp;
				<input id="doShenPi" type="button" value="转入培训申请审批" <ct:btnEnable id="1.10.1.4" /> />&nbsp;&nbsp;
				<strong style="color:blue;font-size:medium;">
				<s:property value="#request.orgShortName"/>
				</strong>
			</td>
		</tr>
	</table>
	<form name="trainForm" id="trainForm" action="" method="post">
		<input type="hidden" id="orgId" name="orgId"
			value="<s:property value="#request.orgId"/>" /> <input type="hidden"
			id="pageIndex" name="pageIndex"
			value="<s:property value="pageIndex"/>" /> 
		<table border="0" width="100%"
			style="background: #fafafa; font-size: 12px;" cellpadding="0"
			cellspacing="1">
			<tr
				style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
				<td width="30px"><input type="checkbox" /></td>
				<!-- <td>培训编号</td> -->
				<td>培训项目</td>
				<td>培训类别</td>
				<!-- <td>培训对象</td> -->
				<td>主办单位</td>
				<td>开始时间</td>
				<td>结束时间</td>
				<td>培训价格</td>
				<td>培训方式</td><!-- 
				<td>备注</td> -->
				<td>状态</td>
			</tr>
			<s:set name="rowCount" value="0"></s:set>
			<s:iterator var="account" value="#request.train_list_page.list"
				status="i">
				<s:if test="#i.odd">
			<tr style="background: rgb(240, 240, 240); height: 22px; text-align: center;">
				</s:if>
				<s:else>
					<tr
						style="background: rgb(248, 248, 248); height: 22px; text-align: center;">
				</s:else>
				<td align="center">
				<input class="ckb" name="trainId"
					type="checkbox" value="<s:property value="ID" />" <s:if test="TRAINSTATUS != 1">disabled="disabled"</s:if>/></td>
				<%-- <td><a class="info"
					href="<%=basePath%>train_toDetailTrain.action?trainId=<s:property value="ID"/>&orgId=<s:property value="orgId"/>">
					<s:property value="TRAINCODE" /></a></td> --%>
				<td><a class="info"
					href="<%=basePath%>train_toDetailTrain.action?trainId=<s:property value="ID"/>&orgId=<s:property value="orgId"/>&pageIndex=<s:property value="pageIndex"/>">
					<s:property value="TRAINSUBJECT" /></a></td>
				<td><s:property value="TRAINTYPE" /></td>
				<%-- <td><s:property value="TRAINOBJECT" /></td> --%>
				<td><s:property value="ORGFULLNAME" /></td>				
				<td><s:date name="TRAINSTART" format="yyyy-MM-dd" /></td>
				<td><s:date name="TRAINEND" format="yyyy-MM-dd" /></td>
				<td><s:property value="TRAINPRICE" /></td>
				<td><s:property value="TRAINMANNER" /></td>	<%-- 			
				<td><s:property value="TRAINREMARKS" /></td> --%>				
				<td><s:if test="TRAINSTATUS == 1"><font style="color:black;font:bold;">申请中</font></s:if> 
				    <s:elseif test="TRAINSTATUS == 2"><font style="color:green;font:bold;">审批中</font></s:elseif>
				    <s:elseif test="TRAINSTATUS == 3"><font style="color:green;font:bold;">审批成功</font></s:elseif>
				    <s:elseif test="TRAINSTATUS == 4"><font style="color:red;font:bold;">审请失败</font></s:elseif> 
				    <s:else><font style="color:red;font:bold;">审批失败</font></s:else>
				</td>
			</tr>
				<s:set name="rowCount" value="#i.count"></s:set>
			</s:iterator>
			<s:iterator begin="#rowCount+1"
				end="#request.train_list_page.pageSize" status="i">
				<s:if test="(#i.count+#rowCount) % 2 != 0">
					<tr style="background: rgb(240, 240, 240); height: 22px;">
				</s:if>
				<s:else>
					<tr style="background: rgb(248, 248, 248); height: 22px;">
				</s:else>
				<td align="center">&nbsp;</td>
				<!-- <td>&nbsp;</td> -->
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			<!-- 	<td>&nbsp;</td> -->
				<td>&nbsp;</td>
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
		value="(#request.train_list_page.rowCount+#request.train_list_page.pageSize-1)/#request.train_list_page.pageSize"></s:set>
	<table border="0" width="100%" height="24px"
		style="background: rgb(192, 212, 240); font-size: 12px;"
		cellpadding="0" cellspacing="0">
		<tr>
			<td align="right">共有&nbsp;<s:property
					value="#request.train_list_page.rowCount" />&nbsp;条信息&nbsp;&nbsp;
				<s:property value="#request.train_list_page.pageSize" />条/页&nbsp;&nbsp;
				页次：<span style="color: red;"><s:property
						value="#request.train_list_page.pageIndex" /></span>/<s:property
					value="#pageCount" />页&nbsp;&nbsp; <s:if
					test="#request.train_list_page.pageIndex == 1">首页</s:if> <s:else>
					<a class="page_l" href="javascript:go(1)">[首页]</a>
				</s:else> <s:if test="#request.train_list_page.pageIndex == 1">上一页</s:if> <s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#request.train_list_page.pageIndex-1" />)">[上一页]</a>
				</s:else> <s:if test="#request.train_list_page.pageIndex >= #pageCount">下一页</s:if>
				<s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#request.train_list_page.pageIndex+1" />)">[下一页]</a>
				</s:else> <s:if test="#request.train_list_page.pageIndex >= #pageCount">尾页</s:if>
				<s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#pageCount" />)">[尾页]</a>
				</s:else>
			</td>
		</tr>
	</table>
</body>
</html>
