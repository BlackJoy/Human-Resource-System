<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="ct" uri="/custom-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>项目审批</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript">
		var init_width = 88;
		var append_width = 210;
		var content_width = init_width;
		$(document).ready(function(){
			$("#btn_return").click(function(){
				self.location.href="<%=basePath%>approve_reviewApprove.action?eid=<s:property value="#session.curr_employee.id" />";
			});
			$("#doSave").click(function(){
				$("#doApproveFrm")[0].submit();
			});
			content_width = init_width + "${fn:length(requestScope.selFlow)}" * append_width;
			$("#flow_view").width(content_width);
		});
	</script>
  </head>
  
  <body>
  	<s:if test="#request.approveMsg != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.approveMsg' escape='false'/>");
  		</script>
  	</s:if>
  	<table border="0" width="100%" style="background: rgb(192, 212, 240) url(images/bg_title01.gif) repeat-x;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td>
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
		    		<td width="10px"></td>
		    		<td height="26px" width="7px"><img src="images/yq_l.gif"/></td>
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;项目传阅&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="doSave" type="button" value="保存" />&nbsp;&nbsp;
    			<input id="btn_return" type="button" value="返回" />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <table border="0" width="100%" style="background: rgb(240, 240, 240); font-size: 12px;" cellpadding="0" cellspacing="5">
    	<tr>
    		<td width="30%" align="right"><span style="color:red;">*</span>审批项目名：</td>
    		<td><s:property value="#request.selApp.approveName" /></td>
    	</tr>
    	<tr>
    		<td width="30%" align="right"><span style="color:red;">*</span>审批开始时间：</td>
    		<td><s:date name="#request.selApp.startTime" format="yyyy-MM-dd" /></td>
    	</tr>
    </table>
    <div id="flow" style="padding: 20px; font-size: 12px; overflow: auto;">
    	<div id="flow_view" style="width:100%;">
	    	<div id="flow_start" class="flow_item" style="width: 50px; height: 80px; text-align: center; line-height: 80px; border: 1px solid #999; float: left; display:inline; position: relative;" flowOrder="0">
	    		开始
	    	</div>
	    	<s:iterator var="flowNode" value="#request.selFlow">
	    	<div class="flow_item" style="width: 198px; float: left; display:inline; position: relative; margin-left: 12px;">
	    		<div class="flow_arrow" style="float: left;"><img src="images/flow_arrow.gif" /></div>
		    	<div class="flow_node" style="width: 120px; padding: 5px; border: 1px solid #999; float: left; position: relative;">
		    		<div style=" text-align: center;">审批节点：<s:property value="#flowNode.key" /></div>
		    		<s:iterator var="flow" value="#flowNode.value">
		    		<div class="flow_approve" 
		    			<s:if test="status==0">style="width: 108px; padding: 5px; margin-top: 5px; border: 1px solid blue; color:blue;"</s:if>
		    			<s:elseif test="status==1">style="width: 108px; padding: 5px; margin-top: 5px; border: 1px solid green; color:green;"</s:elseif>
		    			<s:elseif test="status==2">style="width: 108px; padding: 5px; margin-top: 5px; border: 1px solid red; color:red;"</s:elseif>
		    			<s:elseif test="status==3">style="width: 108px; padding: 5px; margin-top: 5px; border: 1px solid green;"</s:elseif>
		    			<s:else>style="width: 108px; padding: 5px; margin-top: 5px; border: 1px solid #999;"</s:else>
		    		>
		    			<div style="text-align: center;"><s:if test="#flow.approveType == 1">审批</s:if><s:else>传阅</s:else></div>
		    			<div style="padding-top: 5px; text-align: center;">
		    				<s:property value="approverName"/>
		    			</div>
		    			<div style="padding-top: 5px; text-align: center;">
		    				<s:if test="status==0">待审批<br/></s:if>
		    				<s:elseif test="status==1">同意<br/></s:elseif>
		    				<s:elseif test="status==2">不同意<br/></s:elseif>
		    				<s:elseif test="status==3">已阅<br/></s:elseif>
		    				<s:property value="approverComment"/>
		    			</div>
		    		</div>
		    		</s:iterator>
		    	</div>
	    	</div>
	    	</s:iterator>
    	</div>
    </div>
    <table border="0" width="100%" style="background: rgb(192, 212, 240) url(images/bg_title01.gif) repeat-x;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td>
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
		    		<td width="10px"></td>
		    		<td height="26px" width="7px"><img src="images/yq_l.gif"/></td>
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;审批内容&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
    <iframe name="contentFrame" width="100%" style="border:0px;" src="<s:property value="#request.selApp.contentURL" />?id=<s:property value="#request.selApp.contentID" />"></iframe>
    <form name="doApproveFrm" id="doApproveFrm" action="approve_saveFlowApprove.action" method="post">
    <input type="hidden" name="approverId" value="<s:property value="#session.curr_employee.id" />" />
    <input type="hidden" name="id" id="approveId" value="<s:property value="#request.currFlow.id" />" />
    <table border="0" width="100%" style="background: rgb(240, 240, 240); font-size: 12px;" cellpadding="0" cellspacing="5">
    	<tr>
    		<td width="30%" align="right"><span style="color:red;">*</span>审批：</td>
    		<td>
    			<s:if test="#request.currFlow.approveType == 1">
    				<input type="radio" name="approveStatus" value="1" checked="checked" />同意&nbsp;&nbsp;&nbsp;&nbsp;
    				<input type="radio" name="approveStatus" value="2" />不同意
    			</s:if>
    			<s:else><input type="radio" name="approveStatus" value="3" checked="checked" />已阅</s:else>
    		</td>
    	</tr>
    	<tr>
    		<td width="30%" align="right"><span style="color:red;">*</span>审批意见：</td>
    		<td><textarea name="approveComment" id="approveComment" rows="3" cols="50"></textarea></td>
    	</tr>
    </table>
    </form>
  </body>
</html>
