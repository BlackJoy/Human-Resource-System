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
    
    <title>招聘计划书信息列表页</title>
    
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
			$("#doAdd").click(function(){
				var oid = $("#orgId").val();
				location.href="<%=basePath%>employPlan_employPlanAdd.action?orgId="+oid;
			});
			$("#doUpdate").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个应聘信息");
				} else if (count > 1) {
					alert("只能对单个应聘信息进行修改");
				} else {
					$("#planForm").attr("action", "employPlan_toUpdate.action");
					$("#planForm")[0].submit();
				}
			});
			$("#doDelete").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个应聘信息");
				} else if (count > 1) {
					alert("只能对单个应聘信息进行删除");
				} else {
					if (confirm("是否确定删除该应聘信息？")) {
						$("#planForm").attr("action", "employPlan_doDeletePlan.action");
						$("#planForm")[0].submit();
					}
				}
			});
			$("#doApprove").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个应聘信息");
				} else if (count > 1) {
					alert("只能对单个应聘信息进行审批");
				} else {
					if (confirm("是否确定对该应聘信息进行审批？")) {
						$("#planForm").attr("action", "employPlan_toApprove.action");
						$("#planForm")[0].submit();
					}
				}
			});
			$("#doExcel").click(function(){
					if (confirm("是否确定打印这些条目？")) {
						$("#planForm").attr("action", "employPlan_toExcel.action");
						$("#planForm")[0].submit();
					}
			
			});
				$("#doAddAll").click(function(){
				if (confirm("是否确定批量添加？")) {
						$("#planForm").attr("action", "employPlan_toExcelFile.action");
						$("#planForm")[0].submit();
					}
			});
		function go(pageIndex){
			var oid = document.getElementById("orgId").value;
			self.location.href="<%=basePath%>employPlan_employPlanList.action?pageIndex="+pageIndex+"&orgId="+oid;
		}
		});
	</script>

  </head>
  
  <body>
    <s:if test="#request.planMsg != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.planMsg' escape='false'/>");
  		</script>
  	</s:if>
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="doAdd" type="button" value="增加" <ct:btnEnable id="1.2.4.1" /> />&nbsp;&nbsp;
    			<input id="doAddAll" type="button" value="批量增加" <ct:btnEnable id="1.2.4.6" /> />&nbsp;&nbsp;
    			<input id="doUpdate" type="button" value="修改" <ct:btnEnable id="1.2.4.2" /> />&nbsp;&nbsp;
    			<input id="doDelete" type="button" value="删除" <ct:btnEnable id="1.2.4.3" /> />&nbsp;&nbsp;
    			<input id="doApprove" type="button" value="递交招聘计划书审批" <ct:btnEnable id="1.2.4.4" /> />&nbsp;&nbsp;
    			<input id="doExcel" type="button" value="打印招聘计划书" <ct:btnEnable id="1.2.4.5" /> />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <form name="planForm" id="planForm" action="" method="post">
  	<input type="hidden" id="orgId" name="orgId" value="<s:property value="#request.orgId"/>"/>
  	<input type="hidden" id="pageIndex" name="pageIndex" value="<s:property value="pageIndex"/>"/>
    <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
    		<td width="30px"><input type="checkbox"/></td>
    		<td>招聘计划书名字</td>
    		<td>招聘岗位</td>
    		<td>招聘人数</td>
    		<td>招聘专业</td>
    		<td>性别要求</td>
    		<td>英语水平要求</td>
    		<td>是否需要岗前培训</td>
    		<td>职位要求</td>
    		<td>到岗时间</td>
    		<td>职位月薪</td>
    	</tr>
    	<s:set name="rowCount" value="0"></s:set>
    	<s:iterator var="account" value="#request.employPlan_list_page.list" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px; text-align: center;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px; text-align: center;">
			</s:else>
				<td align="center"><input class="ckb" name="planId" type="checkbox" value="<s:property value="ID"/>"/></td>
				<td><a class="info" href="<%=basePath%>employPlan_toDetailPlan.action?planId=<s:property value="ID"/>"><s:property value="PLANNAME"/></a></td>
				<td><s:property value="POSITION" /></td>
				<td><s:property value="NUM" /></td>
				<td><s:property value="MAJOR"/></td>
				<td><s:if test="SEX == 0">女</s:if><s:elseif test="SEX == 1">男</s:elseif><s:else>无</s:else></td>
				<td><s:if test="ENGLISHDEGREE == 0">英语四级</s:if><s:elseif test="ENGLISHDEGREE == 1">英语六级</s:elseif><s:else>无</s:else></td>
				<td><s:if test="ISTRAIN == 0">是</s:if><s:else>否</s:else></td>
				<td><s:property value="POSITIONASK"/></td>
				<td><s:date name="WTIME" format="yyyy-MM-dd" /></td>
				<td><s:property value="FORSALARY" /></td>
    		</tr>
    		<s:set name="rowCount" value="#i.count"></s:set>
    	</s:iterator>
    	<s:iterator begin="#rowCount+1" end="#request.employPlan_list_page.pageSize" status="i">
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
    <s:set name="pageCount" value="(#request.employPlan_list_page.rowCount+#request.employPlan_list_page.pageSize-1)/#request.employPlan_list_page.pageSize"></s:set>
    <table border="0" width="100%" height="24px" style="background: rgb(192, 212, 240); font-size:12px;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td align="right">
    			共有&nbsp;<s:property value="#request.employPlan_list_page.rowCount" />&nbsp;条信息&nbsp;&nbsp;
    			<s:property value="#request.employPlan_list_page.pageSize" />条/页&nbsp;&nbsp;
    			页次：<span style="color:red;"><s:property value="#request.employPlan_list_page.pageIndex" /></span>/<s:property value="#pageCount" />页&nbsp;&nbsp;
    			<s:if test="#request.employPlan_list_page.pageIndex == 1">首页</s:if>
    			<s:else><a class="page_l" href="javascript:go(1)">[首页]</a></s:else>
    			<s:if test="#request.employPlan_list_page.pageIndex == 1">上一页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#request.employPlan_list_page.pageIndex-1" />)">[上一页]</a></s:else>
    			<s:if test="#request.employPlan_list_page.pageIndex >= #pageCount">下一页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#request.employPlan_list_page.pageIndex+1" />)">[下一页]</a></s:else>
    			<s:if test="#request.employPlan_list_page.pageIndex >= #pageCount">尾页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#pageCount" />)">[尾页]</a></s:else>
    		</td>
    	</tr>
    </table>
  </body>
</html>
