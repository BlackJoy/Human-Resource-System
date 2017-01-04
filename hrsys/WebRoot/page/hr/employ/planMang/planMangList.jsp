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
    
    <title>招聘计划书管理列表页</title>
    
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
			$("#doFlow").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个信息");
				} else if (count > 1) {
					alert("只能对单个招聘计划书信息进行操作");
				} else {
					$("#planMangForm").attr("action", "employPlan_doFlow.action");
					$("#planMangForm")[0].submit();
				}
			});
			$("#doPublish").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个信息");
				} else if (count > 1) {
					alert("只能对单个招聘计划书信息进行操作");
				} else {
					$("#planMangForm").attr("action", "employPlan_doPublish.action");
					$("#planMangForm")[0].submit();
				}
			});
		});
		function go(pageIndex){
			var oid = document.getElementById("orgId").value;
			self.location.href="<%=basePath%>employPlan_planMangList.action?pageIndex="+pageIndex+"&orgId="+oid;
		}
	</script>

  </head>
  
  <body>
    <s:if test="#request.planMangMsg != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.planMangMsg' escape='false'/>");
  		</script>
  	</s:if>
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="doFlow" type="button" value="发起审批流程" <ct:btnEnable id="1.2.5.1" /> />&nbsp;&nbsp;
    			<input id="doPublish" type="button" value="转到招聘计划书发布" <ct:btnEnable id="1.2.5.2" /> />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <form name="planMangForm" id="planMangForm" action="" method="post">
  	<input type="hidden" id="orgId" name="orgId" value="<s:property value="#request.orgId"/>"/>
  	<input type="hidden" id="pageIndex" name="pageIndex" value="<s:property value="pageIndex"/>"/>
     <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
    		<td width="30px"><input type="checkbox"/></td>
    		<td>招聘计划书名字</td>
    		<td>招聘职位</td>
    		<td>招聘人数</td>
    		<td>招聘专业</td>
    		<td>性别要求</td>
    		<td>英语水平要求</td>
    		<td>是否需要岗前培训</td>
    		<td>职位要求</td>
    		<td>到岗时间</td>
    		<td>职位月薪</td>
    		<td>状态</td>
    		<td>存档</td>
    	</tr>
    	<s:set name="rowCount" value="0"></s:set>
    	<s:iterator var="account" value="#request.planMang_list_page.list" status="i">
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
				<td><s:if test="STATUS == 1">待发流程</s:if><s:elseif test="STATUS == 2">流程审批中</s:elseif><s:elseif test="STATUS == 3">审批完成</s:elseif></td>
				<td><s:property value="0" /></td>
    		</tr>
    		<s:set name="rowCount" value="#i.count"></s:set>
    	</s:iterator>
    	<s:iterator begin="#rowCount+1" end="#request.planMang_list_page.pageSize" status="i">
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
				<td>&nbsp;</td>
				<td>&nbsp;</td>		
    		</tr>
    	</s:iterator>
    </table>
    </form>
    <s:set name="pageCount" value="(#request.planMang_list_page.rowCount+#request.planMang_list_page.pageSize-1)/#request.planMang_list_page.pageSize"></s:set>
    <table border="0" width="100%" height="24px" style="background: rgb(192, 212, 240); font-size:12px;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td align="right">
    			共有&nbsp;<s:property value="#request.planMang_list_page.rowCount" />&nbsp;条信息&nbsp;&nbsp;
    			<s:property value="#request.planMang_list_page.pageSize" />条/页&nbsp;&nbsp;
    			页次：<span style="color:red;"><s:property value="#request.planMang_list_page.pageIndex" /></span>/<s:property value="#pageCount" />页&nbsp;&nbsp;
    			<s:if test="#request.planMang_list_page.pageIndex == 1">首页</s:if>
    			<s:else><a class="page_l" href="javascript:go(1)">[首页]</a></s:else>
    			<s:if test="#request.planMang_list_page.pageIndex == 1">上一页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#request.planMang_list_page.pageIndex-1" />)">[上一页]</a></s:else>
    			<s:if test="#request.planMang_list_page.pageIndex >= #pageCount">下一页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#request.planMang_list_page.pageIndex+1" />)">[下一页]</a></s:else>
    			<s:if test="#request.planMang_list_page.pageIndex >= #pageCount">尾页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#pageCount" />)">[尾页]</a></s:else>
    		</td>
    	</tr>
    </table>
  </body>
</html>
