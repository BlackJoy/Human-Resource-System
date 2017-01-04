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
				if($("#safetyBase").val().length == 0){
					msg += "保险基数不能为空\n";
				}				
			
				if (msg.length != 0) {
					alert(msg);
				} else {
					//alert("将要插入一个岗位");
					$("#socSecBaseAddForm")[0].submit();
				}
			});
			$("#btn_return").click(function(){
				var pi = $("#pageIndex").val();
				var oId = $("#orgId").val();
				self.location.href="<%=basePath%>socSecBase_toAdd.action?pageIndex=" + pi+"&orgId="+oId;
				                              }
			);
			//------------------选择部门start-------------------------
		<%-- 	$(".choose_approver").live("click",function(){
				var rtnVal = openWin();
				if(rtnVal.length > 0){
					var e = rtnVal.split(",");
					$(this).prevAll(".approverName").val(e[1]);
					$(this).prevAll(".approverId").val(e[0]);
				}
			});
			
			
			function openWin(){
				var rtnValue = window.showModalDialog("<%=basePath%>page/sysmg/approve/chooseEmp.jsp","","location:no;dialogWidth:600px;dialogHeight:400px");
				return rtnValue;
			} --%>
		//------------------选择部门end-------------------------
		
			
			
		});
		
		
		
		
		
		
</script>

</head>

<body>
	<s:if test="#request.accountMsg != null">
		<script type="text/javascript" language="javascript">
			alert("<s:property value='#request.accountMsg' escape='false'/>");
		</script>
	</s:if>
	<form id="socSecBaseAddForm" action="socSecBase_add.action" method="post">
		<input type="hidden" id="orgId" name="orgId" value="<s:property value="orgId"/>" />
		<input type="hidden" id="empId" name="empId" value="<s:property value="empId"/>" /> 
		<input type="hidden" id="pageIndex" name="pageIndex" value="<s:property value="pageIndex"/>" />
		<input type="hidden" id="employeeCode" name="socSecBase.employeeCode" value="<s:property value="emp.employeeCode"/>" /> 
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
				<td align="right"><span style="color: red;">*</span>员工姓名：</td>
				<td><input type="text" id="employeeName" name="socSecBase.employeeName" value="<s:property value="ssInfo.employeeName"/>" Readonly="true"/></td>
			</tr>
			<tr height="30px;">
				<td align="right"><span style="color: red;">*</span>社保ID：</td>
				<td><input type="text" name="socSecBase.ssId" id="ssId" value="<s:property value="ssInfo.ssId"/>" Readonly="true"/></td>
			</tr>
			<tr height="30px;">
				<td align="right"><span style="color: red;">*</span>保险基数：</td>
				<td><input type="text" name="socSecBase.safetyBase" id="safetyBase" /></td>				
			</tr>
			<tr height="30px;">
				<td width="15%" align="right"><span style="color: red;">*</span>当前保险金项：</td>
				<td width="30%">
				<s:select name="socSecBase.templateId" id="templateId" list="bXList" listKey="Id" listValue="curItem" theme="simple" >
				</s:select>
				</td>		
			</tr>
		</table>
	</form>
</body>
</html>
