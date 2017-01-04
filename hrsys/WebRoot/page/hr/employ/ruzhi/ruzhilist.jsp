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
    
    <title>入职管理列表页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<style type="text/css">
	a.page_l,a.info{ text-decoration: none; color: blue;}
	a.page_l:hover,a.info:hover{ text-decoration: underline; color: blue;}
	a.page_l:visited,a.info:visited{ text-decoration: none; color: blue;}
	</style>
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#doLuYong").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个录用信息");
				} else if (count > 1) {
					alert("只能对单个录用信息进行操作");
				} else {
					$("#ruzhiForm").attr("action", "employ_doLuYong.action");
					$("#ruzhiForm")[0].submit();
				}
			});
			$("#doFlow").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个入职信息");
				} else if (count > 1) {
					alert("只能对单个入职信息进行操作");
				} else {
					$("#ruzhiForm").attr("action", "employ_doFlow.action");
					$("#ruzhiForm")[0].submit();
				}
			});
		});
		function go(pageIndex){
			var oid = document.getElementById("orgId").value;
			self.location.href="<%=basePath%>employ_ruzhilist.action?pageIndex="+pageIndex+"&orgId="+oid;
		}
	</script>

  </head>
  
  <body>
    <s:if test="#request.employMsg != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.employMsg' escape='false'/>");
  		</script>
  	</s:if>
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="doLuYong" type="button" value="转入录用考核" <ct:btnEnable id="1.2.3.1" /> />&nbsp;&nbsp;
    			<input id="doFlow" type="button" value="发起入职流程" <ct:btnEnable id="1.2.3.2" /> />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <form name="ruzhiForm" id="ruzhiForm" action="" method="post">
  	<input type="hidden" id="orgId" name="orgId" value="<s:property value="#request.orgId"/>"/>
  	<input type="hidden" id="pageIndex" name="pageIndex" value="<s:property value="pageIndex"/>"/>
  	<input type="hidden" id="ruzhiTime" name="ruzhiTime" />
    <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
    		<td width="30px"><input type="checkbox"/></td>
    		<td>姓名</td>
    		<td>录用时间</td>
    		<td>入职时间</td>
    		<td>录用组织</td>
    		<td>录用职位</td>
    		<td>性别</td>
    		<td>学历</td>
    		<td>联系电话</td>
    		<td>状态</td>
    		<td>存档</td>
    	</tr>
    	<s:set name="rowCount" value="0"></s:set>
    	<s:iterator var="account" value="#request.ruzhi_list_page.list" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px; text-align: center;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px; text-align: center;">
			</s:else>
				<td align="center"><input class="ckb" name="employId" type="checkbox" value="<s:property value="ID"/>" <s:if test="ASTATUS != 0">disabled="disabled"</s:if>/></td>
				<td><!-- <a class="info" href="<%=basePath%>employ_toDetailYingPin.action?employId=<s:property value="ID"/>"></a> --><s:property value="EMPLOYEENAME"/></td>
				<td><s:date name="SHIYONGTIME" format="yyyy-MM-dd" /></td>
				<td><s:date name="RUZHITIME" format="yyyy-MM-dd" /></td>
				<td><s:property value="ORGSHORTNAME"/></td>
				<td><s:property value="PO"/></td>
				<td><s:if test="GENDER == 0">女</s:if><s:else>男</s:else></td>
				<td><s:property value="EDU"/></td>
				<td><s:property value="PHONE"/></td>
				<td>
					<s:if test="ASTATUS == 0">待发流程</s:if>
					<s:elseif test="ASTATUS == 1"><span style="color: #ffaf04;">流程审批中</span></s:elseif>
					<s:elseif test="ASTATUS == 2"><span style="color: red;">流程审批未通过</span></s:elseif>
					<s:elseif test="ASTATUS == 3"><span style="color: #30b226;">流程审批通过</span></s:elseif>
				</td>
				<td>
					<s:if test="ISOVERANDSAVE == 2">已存档</s:if>
					<s:if test="(ASTATUS == 2 || ASTATUS == 3) && ISOVERANDSAVE == 1"><a class="info" href="<%=basePath%>employ_flowover.action?apId=<s:property value="AID"/>">数据存档</a></s:if>
				</td>
    		</tr>
    		<s:set name="rowCount" value="#i.count"></s:set>
    	</s:iterator>
    	<s:iterator begin="#rowCount+1" end="#request.ruzhi_list_page.pageSize" status="i">
    		<s:if test="(#i.count+#rowCount) % 2 != 0">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>
				<td align="center">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>			
				<td>&nbsp;</td>			
    		</tr>
    	</s:iterator>
    </table>
    </form>
    <s:set name="pageCount" value="(#request.ruzhi_list_page.rowCount+#request.ruzhi_list_page.pageSize-1)/#request.ruzhi_list_page.pageSize"></s:set>
    <table border="0" width="100%" height="24px" style="background: rgb(192, 212, 240); font-size:12px;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td align="right">
    			共有&nbsp;<s:property value="#request.ruzhi_list_page.rowCount" />&nbsp;条信息&nbsp;&nbsp;
    			<s:property value="#request.ruzhi_list_page.pageSize" />条/页&nbsp;&nbsp;
    			页次：<span style="color:red;"><s:property value="#request.ruzhi_list_page.pageIndex" /></span>/<s:property value="#pageCount" />页&nbsp;&nbsp;
    			<s:if test="#request.ruzhi_list_page.pageIndex == 1">首页</s:if>
    			<s:else><a class="page_l" href="javascript:go(1)">[首页]</a></s:else>
    			<s:if test="#request.ruzhi_list_page.pageIndex == 1">上一页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#request.ruzhi_list_page.pageIndex-1" />)">[上一页]</a></s:else>
    			<s:if test="#request.ruzhi_list_page.pageIndex >= #pageCount">下一页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#request.ruzhi_list_page.pageIndex+1" />)">[下一页]</a></s:else>
    			<s:if test="#request.ruzhi_list_page.pageIndex >= #pageCount">尾页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#pageCount" />)">[尾页]</a></s:else>
    		</td>
    	</tr>
    </table>
  </body>
</html>
