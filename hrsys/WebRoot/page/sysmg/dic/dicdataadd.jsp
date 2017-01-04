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
    
    <title>增加字典数据</title>
    
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
				var ddc = $("#dicDataCode").val();
				if (ddc.length == 0) {
					alert("字典数据编号不能为空");
					return;
				}
				var ddn = $("#dicDataName").val();
				if (ddn.length == 0) {
					alert("字典数据名不能为空");
					return;
				}
				$("#dicDataAddForm")[0].submit();
			});
			$("#btn_return").click(function(){
				var dtid = $("#dicTypeId").val();
				self.location.href="<%=basePath%>dic_toDataList.action?dicTypeId=" + dtid;
			});
		});
	</script>
  </head>
  
  <body>
  	<s:if test="#request.dicMsg != null">
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
  	<form id="dicDataAddForm" action="dic_addData.action" method="post">
  	<input id="dicTypeId" name="dicTypeId" type="hidden" value="<s:property value="dicTypeId" />" />
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
    		<td width="30%" align="right"><span style="color:red;">*</span>字典数据编号：</td>
    		<td><input type="text" name="dicData.dicDataCode" id="dicDataCode" /></td>
    	</tr>
    	<tr>
    		<td width="30%" align="right"><span style="color:red;">*</span>字典数据名：</td>
    		<td><input type="text" name="dicData.dicDataName" id="dicDataName" /></td>
    	</tr>
    	<tr>
    		<td width="30%" align="right">备注：</td>
    		<td><textarea name="dicData.dicDataComment" cols="70" rows="3"></textarea></td>
    	</tr>
    </table>
    </form>
  </body>
</html>
