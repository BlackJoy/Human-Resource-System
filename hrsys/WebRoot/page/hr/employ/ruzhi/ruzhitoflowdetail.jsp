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
