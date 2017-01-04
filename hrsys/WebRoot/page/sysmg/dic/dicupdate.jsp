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
    
    <title>修改字典类型</title>
    
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
				var dtc = $("#dicTypeCode").val();
				if (dtc.length == 0) {
					alert("字典编码不能为空");
					return;
				}
				var dtn = $("#dicTypeName").val();
				if (dtn.length == 0) {
					alert("字典名不能为空");
					return;
				}
				$("#dicTypeUpdateForm")[0].submit();
			});
			$("#btn_return").click(function(){
				self.location.href="<%=basePath%>dic_list.action";
			});
		});
	</script>
  </head>
  
  <body>
  	<s:if test="#request.roleAdd == true">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.dicMsg' escape='false'/>");
  		</script>
  	</s:if>
  	<table border="0" width="100%" style="background: rgb(192, 212, 240) url(images/bg_title01.gif) repeat-x;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td>
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
		    		<td width="10px"></td>
		    		<td height="26px" width="7px"><img src="images/yq_l.gif"/></td>
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;数据字典设置&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
  	<form id="dicTypeUpdateForm" action="dic_updateType.action" method="post">
  	<input name="dicType.id" id="dicTypeId" type="hidden" value="<s:property value="#request.currDicType.id" />">
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
    	<tr>
    		<td width="30%" align="right"><span style="color:red;">*</span>字典类型编码：</td>
    		<td><input type="text" name="dicType.dicTypeCode" id="dicTypeCode"  value="<s:property value="#request.currDicType.dicTypeCode" />" /></td>
    	</tr>
    	<tr>
    		<td width="30%" align="right"><span style="color:red;">*</span>字典类型名：</td>
    		<td><input type="text" name="dicType.dicTypeName" id="dicTypeName"  value="<s:property value="#request.currDicType.dicTypeName" />" /></td>
    	</tr>
    	<tr>
    		<td width="30%" align="right">备注：</td>
    		<td><textarea name="dicType.dicTypeComment" cols="70" rows="3"><s:property value="#request.currDicType.dicTypeComment" /></textarea></td>
    	</tr>
    </table>
    </form>
  </body>
</html>
