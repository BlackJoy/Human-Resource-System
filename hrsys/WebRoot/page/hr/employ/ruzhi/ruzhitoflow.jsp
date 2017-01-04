<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="ct" uri="/custom-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>入职流程</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<!-- 
	 -->
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="js/Calendar2.js"></script>
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#btn_add").click(function(){
				var aid = $("#apId").val();
				if (aid == "0") {
					alert("审批模板不能为空");
					return;
				}
				var aname = $("#apprName").val();
				if(aname.length == 0){
					alert("审批项目名不能为空");
					return;
				}
				$("#ruzhiAppFlowForm").attr("action","<%=basePath%>employ_saveAppFlow.action");
				$("#ruzhiAppFlowForm").removeAttr("target");
				$("#ruzhiAppFlowForm")[0].submit();
			});
			$("#btn_return").click(function(){
				self.location.href="<%=basePath%>employ_ruzhilist.action";
			});
			$("#apId").change(function(){
				var apid = $("#apId").val();
				if (apid != "0") {
					$("#ruzhiAppFlowForm").attr("action","<%=basePath%>employ_loadAppFlow.action");
					$("#ruzhiAppFlowForm").attr("target","apFlow");
					$("#ruzhiAppFlowForm")[0].submit();
				} else {
					$("#apFlow").attr("src","");
				}
			});
		});
	</script>
  </head>
  
  <body>
  	<s:if test="#request.employMsg != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.employMsg' escape='false'/>");
  		</script>
  	</s:if>
  	<table border="0" width="100%" style="background: rgb(192, 212, 240) url(images/bg_title01.gif) repeat-x;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td>
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
		    		<td width="10px"></td>
		    		<td height="26px" width="7px"><img src="images/yq_l.gif"/></td>
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;入职流程&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
  	<form id="ruzhiAppFlowForm" action="employ_doAddYingPin.action" method="post">
  	<input type="hidden" id="employId" name="employId" value="<s:property value="#request.employId"/>"/>
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="btn_add" type="button"" value="保存" />&nbsp;&nbsp;
    			<input id="btn_return" type="button" value="返回" />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <table border="0" width="100%" style="background: rgb(240, 240, 240); font-size: 12px;" cellpadding="0" cellspacing="2">
    	<tr>
    		<td width="15%" align="right"><span style="color:red;">*</span>请选择审批模板：</td>
    		<td>
    			<select name="apId" id="apId">
    				<option value="0">--------请选择审批模板--------</option>
    				<s:iterator var="ap" value="#request.aplist">
    					<option value="<s:property value="ID" />"><s:property value="NAME" /></option>
    				</s:iterator>
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td width="15%" align="right"><span style="color:red;">*</span>请输入审批项目名：</td>
    		<td><input name="apprName" id="apprName" /></td>
    	</tr>
    </table>
    </form>
    <iframe id="apFlow" name="apFlow" width="100%" height="180px" style="border: 0px;"></iframe>
    <table border="0" width="100%" style="background: rgb(192, 212, 240) url(images/bg_title01.gif) repeat-x;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td>
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
		    		<td width="10px"></td>
		    		<td height="26px" width="7px"><img src="images/yq_l.gif"/></td>
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;入职人员信息&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
    <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
    		<td>姓名</td>
    		<td>录用时间</td>
    		<td>入职时间</td>
    		<td>录用组织</td>
    		<td>录用职位</td>
    		<td>性别</td>
    		<td>学历</td>
    		<td>联系电话</td>
    	</tr>
    	<s:iterator var="account" value="#request.curr_ruzhi" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px; text-align: center;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px; text-align: center;">
			</s:else>
				<td><s:property value="EMPLOYEENAME"/></td>
				<td><s:date name="SHIYONGTIME" format="yyyy-MM-dd" /></td>
				<td><s:date name="RUZHITIME" format="yyyy-MM-dd" /></td>
				<td><s:property value="ORGSHORTNAME"/></td>
				<td><s:property value="PO"/></td>
				<td><s:if test="GENDER == 0">女</s:if><s:else>男</s:else></td>
				<td><s:property value="EDU"/></td>
				<td><s:property value="PHONE"/></td>
    		</tr>
    	</s:iterator>
    </table>
  </body>
</html>
