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
    
    <title>增加子组织</title>
    
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
				if($("#orgCode").val().length == 0){
					msg += "组织编码不能为空\n";
				}
				if($("#orgFullName").val().length == 0){
					msg += "组织名称不能为空";
				}
				if($("#orgShortName").val().length == 0){
					msg += "组织简称不能为空";
				}
				if (msg.length != 0) {
					alert(msg);
				} else {
					$("#orgAddForm")[0].submit();
				}
			});
			$("#btn_return").click(function(){
				self.location.href="<%=basePath%>org_list.action";
			});
		});
	</script>
  </head>
  
  <body>
    <table border="0" width="100%" style="background: rgb(192, 212, 240) url(images/bg_title01.gif) repeat-x;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td>
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
		    		<td width="10px"></td>
		    		<td height="26px" width="7px"><img src="images/yq_l.gif"/></td>
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;组织信息管理&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
    <form id="orgAddForm" action="org_add.action" method="post">
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
    		<td width="15%" align="right"><span style="color:red;">*</span>组织编码：</td>
    		<td width="30%"><s:property value="#request.p_org.orgCode" />-<input type="text" name="org.orgCode" id="orgCode" />
    		<input type="hidden" name="parentCode" value="<s:property value="#request.p_org.orgCode" />"  /></td>
    		<td width="15%" align="right">父组织：</td>
    		<td width="20%"><s:property value="#request.p_org.orgFullName" /><input type="hidden" name="org.orgParentId" value="<s:property value="#request.p_org.id" />"  /></td><td width="20%">&nbsp;</td>
    	</tr>
    	<tr height="30px;">
    		<td align="right"><span style="color:red;">*</span>组织名称：</td>
    		<td><input type="text" name="org.orgFullName" id="orgFullName" /></td>
    		<td align="right"><span style="color:red;">*</span>组织简称：</td>
    		<td><input type="text" name="org.orgShortName" id="orgShortName" /></td>
    		<td>&nbsp;</td>
    	</tr>
    	<tr height="30px;">
    		<td align="right"><span style="color:red;">*</span>组织类型：</td>
    		<td>
    			<input type="radio" name="org.orgType" checked="checked" value="0" />总公司&nbsp;&nbsp;
    			<input type="radio" name="org.orgType" value="1" />分公司&nbsp;&nbsp;
    			<input type="radio" name="org.orgType" value="2" />职能部门&nbsp;&nbsp;
    			<input type="radio" name="org.orgType" value="3" />生产部门&nbsp;&nbsp;
    			<input type="radio" name="org.orgType" value="4" />经营部门
    		</td>
    		<td align="right"><span style="color:red;">*</span>组织状态：</td>
    		<td>
    			<input type="radio" name="org.orgStatus" value="0" />注销&nbsp;&nbsp;
    			<input type="radio" name="org.orgStatus" checked="checked" value="1" />正常
    		</td>
    		<td>&nbsp;</td>
    	</tr>
    </table>
    </form>
  </body>
</html>
