<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="ct" uri="/custom-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>字典数据列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<style type="text/css">
	a.page_l{ text-decoration: none; color: blue;}
	a.page_l:hover{ text-decoration: underline; color: blue;}
	a.page_l:visited{ text-decoration: none; color: blue;}
	</style>
	<script type="text/javascript" language="javascript">
	function doAdd(){
		var dtid = document.getElementById("dicTypeId").value;
		self.location.href="<%=basePath%>dic_toDataAdd.action?dicTypeId="+dtid;
	}
	function doUpdate(){
		var dtid = document.getElementById("dicTypeId").value;
		var roleIds = document.getElementsByName("ddid");
		var isChecked = false;
		var count = 0;
		var ri;
		for ( var i = 0; i < roleIds.length; i++) {
			if (roleIds[i].checked==true) {
				isChecked = true;
				count++;
				ri = roleIds[i].value;
			}
		}
		if (isChecked) {
			if (count == 1) {
				self.location.href="<%=basePath%>dic_toDicDataUpdate.action?dicDataId="+ri+"&dicTypeId="+dtid;
			} else {
				alert("不允许对多条记录进行修改");
			}
		} else {
			alert("请勾选要修改的记录");
		}
	}
	function doDelete(){
		var dtid = document.getElementById("dicTypeId").value;
		var roleIds = document.getElementsByName("ddid");
		var isChecked = false;
		var count = 0;
		var ri;
		for ( var i = 0; i < roleIds.length; i++) {
			if (roleIds[i].checked==true) {
				isChecked = true;
				count++;
				ri = roleIds[i].value;
			}
		}
		if (isChecked) {
			if (count == 1) {
				if (confirm("确定要删除选择的记录吗？")) {
					self.location.href="<%=basePath%>dic_deleteDicData.action?dicDataId="+ri+"&dicTypeId="+dtid;
				}
			} else {
				alert("不允许对多条记录进行删除");
			}
		} else {
			alert("请勾选要删除的记录");
		}
	}
	function doReturn(){
		self.location.href="<%=basePath%>dic_list.action";
	}
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
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;字典设置&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="doAdd" type="button" value="增加" onclick="doAdd()" />&nbsp;&nbsp;
    			<input id="doUpdate" type="button" value="修改" onclick="doUpdate()" />&nbsp;&nbsp;
    			<input id="doDelete" type="button" value="删除" onclick="doDelete()" />&nbsp;&nbsp;
    			<input id="btn_return" type="button" value="返回" onclick="doReturn()" />
    		</td>
    	</tr>
    </table>
    <input name="dicTypeId" id="dicTypeId" type="hidden" value="<s:property value="dicTypeId" />" />
    <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
    		<td width="30px"><input type="checkbox"/></td>
    		<td width="100px">字典数据编号</td>
    		<td width="300px">字典数据名</td>
    		<td>备注</td>
    	</tr>
    	<s:iterator var="dicData" value="#request.dicDataList" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>
				<td align="center"><input class="ddid" name="ddid" type="checkbox" value="<s:property value="id"/>"/></td>
				<td align="center"><s:property value="dicDataCode"/></td>
				<td align="center"><s:property value="dicDataName"/></td>
				<td align="center"><s:property value="dicDataComment"/></td>		
    		</tr>
    	</s:iterator>
    </table>
  </body>
</html>
