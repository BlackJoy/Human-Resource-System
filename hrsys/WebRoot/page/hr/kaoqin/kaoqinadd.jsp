<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'kaoqinadd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#btn_add").click(function(){
				var msg = "";
				if($("#username").val().length == 0){
					msg += "账号不能为空\n";
				}
				if($("#password").val().length == 0){
					msg += "密码不能为空\n";
				}
				if($("#repassword").val().length == 0){
					msg += "确认密码不能为空\n";
				}
				if($("#password").val() != $("#repassword").val()){
					msg += "两次输入的密码不一致";
				}
				if (msg.length != 0) {
					alert(msg);
				} else {
					$("#kaoqinAddForm")[0].submit();
				}
			});
			$("#btn_return").click(function(){
				var oid = $("#orgId").val();
				var pi = $("#pageIndex").val();
				self.location.href="<%=basePath%>account_list.action?orgId="+oid+"&pageIndex="+pi;
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
    <form id="kaoqinAddForm" action="kaoqin_add.action" method="post">
    <input type="hidden" id="orgId" name="orgId" value="<s:property value="orgId"/>" />
    <input type="hidden" id="empId" name="empId" value="<s:property value="empId"/>" />
    <input type="hidden" id="pageIndex" name="pageIndex" value="<s:property value="pageIndex"/>" />
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="btn_add" type="button"" value="保存" />&nbsp;&nbsp;
    			<input type="reset"" value="重置" />&nbsp;&nbsp;
    			<input id="btn_return" type="button" value="返回" />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <table border="0" width="100%" style="background: rgb(240, 240, 240); font-size: 12px;" cellpadding="0" cellspacing="2">
    	<tr height="30px;">
    		<td width="15%" align="right"><span style="color:red;">*</span>账户名：</td>
    		<td width="30%"><input type="text" name="user.username" id="username" /></td>
    		<td width="15%" align="right">&nbsp;</td>
    		<td width="20%">&nbsp;</td>
    		<td width="20%">&nbsp;</td>
    	</tr>
    	<tr height="30px;">
    		<td align="right"><span style="color:red;">*</span>密码：</td>
    		<td><input type="password" name="user.password" id="password" /></td>
    		<td align="right"><span style="color:red;">*</span>确认密码：</td>
    		<td><input type="password" name="repassword" id="repassword" /></td>
    		<td>&nbsp;</td>
    	</tr>
    	<tr height="30px;">
    		<td align="right"><span style="color:red;">*</span>账户状态：</td>
    		<td>
    			<input type="radio" name="user.enable" value="0" />注销&nbsp;&nbsp;
    			<input type="radio" name="user.enable" checked="checked" value="1" />启用
    		</td>
    		<td align="right">&nbsp;</td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    	</tr>
    </table>
    </form>
  </body>
</html>
