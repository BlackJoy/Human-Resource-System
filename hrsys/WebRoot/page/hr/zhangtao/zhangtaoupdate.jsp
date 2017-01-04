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
				if($("#wageTypeName").val().length == 0){
					msg += "帐套名称不能为空\n";
				}
				if($("#wageTypeNo").val().length == 0){
					msg += "帐套编码不能为空\n";
				}
				
				
				
				if (msg.length != 0) {
					alert(msg);
				} else {
					alert("将要更新一个帐套");
					$("#zhangtaoUpdateForm")[0].submit();
				}
			});
			$("#btn_return").click(function(){
				var oid = $("#orgId").val();
				var pi = $("#pageIndex").val();
				self.location.href="<%=basePath%>zhangtao_list.action?orgId="
													+ oid + "&pageIndex=" + pi;
										});
			
			
			
			
			//------------------选择部门start-------------------------
				$(".choose_org").live("click",function(){
					//---------------------------------------------------
					if(navigator.appName.indexOf("Micro")!=-1){//IE浏览器
						var rtnValue = window.showModalDialog("<%=basePath%>page/hr/zhangtao/chooseOrg.jsp","","location:no;dialogWidth:600px;dialogHeight:400px");
						//alert(rtnValue);
						var a=rtnValue.split(",");
						if (rtnValue.length > 0) {
							$("#orgName").val(a[0]);//文本框中显示选中的组织名称
							$("#orgId").val(a[1]);//保存选中的组织id
							//alert($("#orgName").val());
							//alert($("#orgId").val());
							
						}
						return rtnVaule;
						
					}else{
						//alert("火狐浏览器");
						window.open("<%=basePath%>page/hr/zhangtao/chooseOrg.jsp","","width=600,height=500,menubar=no,toolbar=no,location=no,scrollbars=no,status=no,modal=yes");
					}
					
					//--------------------------------------------------
					
				});
					
					
					
					
					
					
				
			
				});
				
			//------------------选择部门end-------------------------
				
			
			
			
			
</script>

</head>

<body>
	<s:if test="#request.accountMsg != null">
		<script type="text/javascript" language="javascript">
			alert("<s:property value='#request.accountMsg' escape='false'/>");
		</script>
	</s:if>
	<form id="zhangtaoUpdateForm" action="zhangtao_update.action"
		method="post">
		<input type="hidden" id="orgId" name="orgId"
			value="<s:property value="orgId"/>" /> <input type="hidden"
			id="pageIndex" name="pageIndex"
			value="<s:property value="pageIndex"/>" /> <input type="hidden"
			id="pageIndex" name="zt.wageTypeId" value="<s:property value="zt.wageTypeId"/>" />

		<table border="0" width="100%" style="background: rgb(192, 212, 240);"
			cellpadding="0" cellspacing="0">
			<tr>
				<td height="30px" valign="middle" style="padding-left: 30px;">
					<input id="btn_add" type="button" " value="保存" />&nbsp;&nbsp; <input
					type="reset" value="重置" />&nbsp;&nbsp; <input id="btn_return"
					type="button" value="返回" />&nbsp;&nbsp;
				</td>
			</tr>
		</table>

		<table border="0" width="100%"
			style="background: rgb(240, 240, 240); font-size: 12px;"
			cellpadding="0" cellspacing="2">
			<tr height="30px;">
				<td width="15%" align="right"><span style="color: red;">*</span>帐套名称：</td>
				<td width="30%"><input type="text" name="zt.wageTypeName"
					id="wageTypeName" value='<s:property value="zt.wageTypeName"/>' /></td>
				<td width="15%" align="right">&nbsp;</td>
				<td width="20%">&nbsp;</td>
				<td width="20%">&nbsp;</td>
			</tr>
			<tr height="30px;">
				<td align="right"><span style="color: red;">*</span>帐套编码：</td>
				<td><input type="text" name="zt.wageTypeNo" id="wageTypeNo"
					value='<s:property value="zt.wageTypeNo"/>' /></td>

			</tr>

			<tr height="30px;">
				<td align="right"><span style="color: red;">*</span>是否激活：</td>
				<td><input type="radio" name="zt.iactive"
					<s:if test="zt.iactive == 0">checked="checked"</s:if> value="0"
					value="0" />否&nbsp;&nbsp; <input type="radio" name="zt.iactive"
					<s:if test="zt.iactive == 1">checked="checked"</s:if> value="1" />是</td>
				<td align="right">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		<!-- 弹出框选择部门 start-->
		<div
			style="float: left; background: rgb(240, 240, 240); font-size: 12px;">
			<span style="color: red;"> *</span>所在部门名称：<input type="text"
				name="orgName" id="orgName" value='<s:property value="orgName"/>' />
		</div>
		<div class='choose_org'
			style='width: 18px; height: 18px; float: left; cursor: pointer; background: rgb(240, 240, 240); font-size: 12px;'>
			<img src='images/choose.jpg' />
		</div>

		<!-- 弹出框选择部门 end-->

	</form>
</body>
</html>
