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
				var rtnValue = window.showModalDialog("<%=basePath%>page/hr/empwage/chooseWageTime.jsp","","location:no;dialogWidth:420px;dialogHeight:285px");
				if (rtnValue.length>0) {
					$("#chooseWageTime").val(rtnValue);//录入工资的月份
					$("#empWageForm").attr("action", "empWage_toAdd.action");
					alert($("#empWageForm").attr("action"));
					$("#empWageForm")[0].submit();
				}
			});
			$("#doUpdate").click(function(){
				var rtnValue = window.showModalDialog("<%=basePath%>page/hr/empwage/chooseWageTime.jsp","","location:no;dialogWidth:420px;dialogHeight:285px");
				if (rtnValue.length>0) {
					var count = $(".ckb:checked").size();
					if (count <= 0) {
						alert("请勾选一个员工");
					} else if (count > 1) {
						alert("只能对单个员工计算薪资");
					} else {
						$("#calcWageTime").val(rtnValue);//薪资计算的月份
						$("#empWageForm").attr("action", "empWage_calcWage.action");
						$("#empWageForm")[0].submit();
					}
				}
			});
			$("#doImport").click(function(){
				$("#empWageForm").attr("action", "empWage_toImport.action");
				$("#empWageForm")[0].submit();
			});
			
			
			
		
			
		});
		function go(pageIndex){
			var oid = document.getElementById("orgId").value;
			self.location.href="<%=basePath%>post_list.action?pageIndex="
				+ pageIndex + "&orgId=" + oid;
	}
</script>

</head>

<body>
<s:if test='#session.isCwiExist == "1" '>
		<script type="text/javascript" language="javascript">
			alert("该员工工资档案已经存在！！请重新录入");
		</script>
</s:if>
<s:if test='#session.isMonthWageHas == "0" '>
		<script type="text/javascript" language="javascript">
			alert("该员工还没有该月份记录，请先登记！！！");
		</script>
</s:if>
<% session.setAttribute("isCwiExist", 0); %>
<% session.setAttribute("isMonthWageHas", 1); %>
	<table border="0" width="100%" style="background: rgb(192, 212, 240);"
		cellpadding="0" cellspacing="0">
		<tr>
			<td height="30px" valign="middle" style="padding-left: 30px;"><input
				id="doAdd" type="button" value="录入" <ct:btnEnable id="1.4.3.1" /> />&nbsp;&nbsp;
				<input id="doUpdate" type="button" value="薪资计算"
				<ct:btnEnable id="1.4.3.2" /> />&nbsp;&nbsp;  <input id="doImport"
				type="button" value="导入"  />&nbsp;&nbsp;
			</td>
		</tr>
	</table>
	<form name="empWageForm" id="empWageForm" action="" method="post">
	<input type="hidden" id="chooseWageTime" name="chooseWageTime"/>
	<input type="hidden" id="calcWageTime" name="calcWageTime"/>
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
				<td>员工编码</td>
				<td>员工名字</td>
				<td>部门名称</td>
				<td>帐套名称</td>
			</tr>
			<s:set name="rowCount" value="0"></s:set>
			<s:iterator var="account" value="#request.empWage_list_page.list"
				status="i">
				<s:if test="#i.odd">
					<tr style="background: rgb(240, 240, 240); height: 22px;">
				</s:if>
				<s:else>
					<tr style="background: rgb(248, 248, 248); height: 22px;">
				</s:else>
				<td align="center"><input class="ckb" name="empId"
					type="checkbox" value="<s:property value="EMP_ID"/>" /></td>
				<td><s:property value="EMP_NO" /></td>
				<td><s:property value="EMP_NM" /></td>
				<td><s:property value="DEPT" /></td>
				<td><s:property value="WAGE_TYPE" /></td>
				</tr>
				<s:set name="rowCount" value="#i.count"></s:set>
			</s:iterator>
			<s:iterator begin="#rowCount+1"
				end="#request.empWage_list_page.pageSize" status="i">
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
		value="(#request.empWage_list_page.rowCount+#request.empWage_list_page.pageSize-1)/#request.empWage_list_page.pageSize"></s:set>
	<table border="0" width="100%" height="24px"
		style="background: rgb(192, 212, 240); font-size: 12px;"
		cellpadding="0" cellspacing="0">
		<tr>
			<td align="right">共有&nbsp;<s:property
					value="#request.empWage_list_page.rowCount" />&nbsp;条信息&nbsp;&nbsp; <s:property
					value="#request.empWage_list_page.pageSize" />条/页&nbsp;&nbsp; 页次：<span
				style="color: red;"><s:property
						value="#request.empWage_list_page.pageIndex" /></span>/<s:property
					value="#pageCount" />页&nbsp;&nbsp; <s:if
					test="#request.empWage_list_page.pageIndex == 1">首页</s:if> <s:else>
					<a class="page_l" href="javascript:go(1)">[首页]</a>
				</s:else> <s:if test="#request.empWage_list_page.pageIndex == 1">上一页</s:if> <s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#request.empWage_list_page.pageIndex-1" />)">[上一页]</a>
				</s:else> <s:if test="#request.empWage_list_page.pageIndex >= #pageCount">下一页</s:if>
				<s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#request.empWage_list_page.pageIndex+1" />)">[下一页]</a>
				</s:else> <s:if test="#request.empWage_list_page.pageIndex >= #pageCount">尾页</s:if>
				<s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#pageCount" />)">[尾页]</a>
				</s:else>
			</td>
		</tr>
	</table>
</body>
</html>
