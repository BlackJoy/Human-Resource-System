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
    
    <title>审批流程详情</title>
    
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
				self.location.href="<%=basePath%>approve_list.action";
			});
			$("#doUpdate").click(function(){
				$("#approveFrm")[0].action = "approve_toUpdate.action";
				$("#approveFrm")[0].submit();
			});
			$("#doDelete").click(function(){
				if (confirm("确定要删除选择的记录吗？")) {
					$("#approveFrm")[0].action = "approve_doDelete.action";
					$("#approveFrm")[0].submit();
				}
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
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;审批流程管理&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="doUpdate" type="button" value="修改" <ct:btnEnable id="2.3.1.2" /> />&nbsp;&nbsp;
    			<input id="doDelete" type="button" value="删除" <ct:btnEnable id="2.3.1.3" /> />&nbsp;&nbsp;
    			<input id="btn_return" type="button" value="返回" />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <form name="approveFrm" id="approveFrm" action="" method="post">
    <input type="hidden" name="id" id="approveId" value="<s:property value="#request.selApp.id" />" />
    <table border="0" width="100%" style="background: rgb(240, 240, 240); font-size: 12px;" cellpadding="0" cellspacing="5">
    	<tr>
    		<td width="30%" align="right"><span style="color:red;">*</span>审批项目名：</td>
    		<td><s:property value="#request.selApp.name" /></td>
    	</tr>
    	<tr>
    		<td width="30%" align="right"><span style="color:red;">*</span>审批期限（天）：</td>
    		<td><s:property value="#request.selApp.timeLimit" /></td>
    	</tr>
    	<tr>
    		<td width="30%" align="right"><span style="color:red;">*</span>审批内容页面：</td>
    		<td>
    			<s:iterator var="ac" value="#application.approveContent">
    				<s:if test="#request.selApp.approveContent==#ac.action"><s:property value="name" /></s:if>
    			</s:iterator>
    		</td>
    	</tr>
    </table>
    </form>
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
		    		<div class="flow_approve" style="width: 108px; padding: 5px; margin-top: 5px; border: 1px solid #999;">
		    			<div style="text-align: center;"><s:if test="#flow.approveType == 1">审批</s:if><s:else>传阅</s:else></div>
		    			<div style="padding-top: 5px; text-align: center;">
		    				<s:property value="approverName"/>
		    			</div>
		    		</div>
		    		</s:iterator>
		    	</div>
	    	</div>
	    	</s:iterator>
    	</div>
    </div>
  </body>
</html>
