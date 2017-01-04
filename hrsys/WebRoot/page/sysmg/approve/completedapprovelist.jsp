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
    
    <title>已批阅列表</title>
    
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
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript">
	function go(pageIndex){
		window.location.href="<%=basePath%>approve_backlogApprove.action?pageIndex="+pageIndex;
	}
	$(document).ready(function(){
		$("#doApprove").click(function(){
			var count = $(".ids:checked").size();
			if (count <= 0) {
				alert("请勾选一个已批阅项目");
			} else if (count > 1) {
				alert("只能对单个已批阅项目进行审批");
			} else {
				$("#backlogApproveForm")[0].submit();
			}
		});
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
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;已批阅项目&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
    <!-- 
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="doApprove" type="button" value="项目审批" onclick="doAdd()" />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
     -->
    <form name="backlogApproveForm" id="backlogApproveForm" action="approve_toApprove.action" method="post">
    <input type="hidden" name="approverId" value="<s:property value="#session.curr_employee.id" />" />
    <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
    		<td width="30px"><input disabled="disabled" type="checkbox"/></td>
    		<td width="40%">审批项目名</td>
    		<td>审批开始时间</td>
    		<td>审批状态</td>
    	</tr>
    	<s:set name="rowCount" value="0"></s:set>
    	<s:iterator var="approve" value="#request.completedApprove_page.list" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>
				<td align="center"><input class="ids" name="id" type="checkbox" value="<s:property value="ID"/>" disabled="disabled" /></td>
				<td align="center"><a href="approve_completedApproveDetail.action?id=<s:property value="ID"/>&approvoerId=<s:property value="#session.curr_employee.id" />"><s:property value="APPROVENAME"/></a></td>
				<td align="center"><s:date name="STARTTIME" format="yyyy-MM-dd" /></td>
				<td align="center">
					<s:if test="STATUS == 1">审批中</s:if>
					<s:elseif test="STATUS == 2">审批未通过</s:elseif>
					<s:elseif test="STATUS == 3">审批通过</s:elseif>
				</td>			
    		</tr>
    		<s:set name="rowCount" value="#i.count"></s:set>
    	</s:iterator>
    	<s:iterator begin="#rowCount+1" end="#request.completedApprove_page.pageSize" status="i">
    		<s:if test="(#i.count+#rowCount) % 2 != 0">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>
				<td align="center"></td>
				<td width="300px"></td>
				<td>&nbsp;</td>			
				<td>&nbsp;</td>			
    		</tr>
    	</s:iterator>
    </table>
    </form>
    <s:set name="pageCount" value="(#request.completedApprove_page.rowCount+#request.completedApprove_page.pageSize-1)/#request.completedApprove_page.pageSize"></s:set>
    <table border="0" width="100%" height="24px" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td align="right">
    			共有&nbsp;<s:property value="#request.completedApprove_page.rowCount" />&nbsp;条信息&nbsp;&nbsp;
    			<s:property value="#request.completedApprove_page.pageSize" />条/页&nbsp;&nbsp;
    			页次：<span style="color:red;"><s:property value="#request.completedApprove_page.pageIndex" /></span>/<s:property value="#pageCount" />页&nbsp;&nbsp;
    			<s:if test="#request.completedApprove_page.pageIndex == 1">首页</s:if>
    			<s:else><a class="page_l" href="javascript:go(1)">[首页]</a></s:else>
    			<s:if test="#request.completedApprove_page.pageIndex == 1">上一页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#request.completedApprove_page.pageIndex-1" />)">[上一页]</a></s:else>
    			<s:if test="#request.completedApprove_page.pageIndex >= #pageCount">下一页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#request.completedApprove_page.pageIndex+1" />)">[下一页]</a></s:else>
    			<s:if test="#request.completedApprove_page.pageIndex >= #pageCount">尾页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#pageCount" />)">[尾页]</a></s:else>
    		</td>
    	</tr>
    </table>
  </body>
</html>
