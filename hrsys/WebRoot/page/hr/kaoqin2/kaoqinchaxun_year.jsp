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
    
    <title>年度考勤汇总页</title>
    
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
		
		$(document).ready(function(){
			
			$("#fanhui").click(function(){
			
					$("#kaoqinForm").attr("action", "kaoqinyear_list.action");
					$("#kaoqinForm")[0].submit();
					
			});
			
			
			$("#daochu").click(function(){
					
					$("#kaoqinForm").attr("action", "kaoqinmonth_daochujilu.action");
					$("#kaoqinForm")[0].submit();
					
			});
		
		});
		function go(pageIndex){
			var oid = document.getElementById("orgId").value;
			self.location.href="<%=basePath%>kaoqinyear_list.action?pageIndex="+pageIndex+"&orgId="+oid;
		}
	</script>

  </head>
  
  <body>
   
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    	
    			<input id="fanhui" type="button" value="返回"/>&nbsp;&nbsp;
				<input id="daochu" type="button" value="导出记录"/>&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <form name="kaoqinForm" id="kaoqinForm" action="" method="post">
  	
	<input type="hidden" id="orgId" name="orgId" value="<s:property value="#request.orgId"/>"/>
  	<input type="hidden" id="pageIndex" name="pageIndex" value="<s:property value="pageIndex"/>"/>
  	<input type="hidden" id="kqTime" name="kqTime" value="<s:property value="#request.kqTime"/>"/>
  	
    <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
    		<td width="30px"><input type="checkbox"/></td>
    		<td>员工名称</td>
      		<td>所在部门</td>
    		<td>岗位名称</td>   		
    		<td>考勤时间</td>
    		
    		<td>缺勤次数</td>
    		<td>出勤次数</td>
    		<td>请假次数</td>
    		<td>迟到次数</td>
    		<td>加班次数</td>
    		
    		
    	</tr>
    	<s:set name="rowCount" value="0"></s:set>
    	<s:iterator var="account" value="#request.kaoqinyear_list_page.list" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>
				<td align="center"><input class="ckb" name="kaoqinId" type="checkbox" value="<s:property value="ID"/>"/></td>
				
				<td><s:property value="EMPLOYEENAME"/></td>
				<td><s:property value="ORGID" /></td>
				<td><s:property value="POSTNAME"/></td>
				<td><s:property value="KAOQINTIME"/></td>
				<td><s:property value="STATE_QQ"/></td>
				<td><s:property value="STATE_CQ"/></td>
				<td><s:property value="STATE_QJ"/></td>
				<td><s:property value="STATE_CD"/></td>
				<td><s:property value="ISADD_JB"/></td>
				
    		</tr>
    		<s:set name="rowCount" value="#i.count"></s:set>
    	</s:iterator>
    	<s:iterator begin="#rowCount+1" end="#request.kaoqinyear_list_page.pageSize" status="i">
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
		
    		</tr>
    	</s:iterator>
    </table>
    </form>
    <s:set name="pageCount" value="(#request.kaoqinyear_list_page.rowCount+#request.kaoqinyear_list_page.pageSize-1)/#request.kaoqinyear_list_page.pageSize"></s:set>
    <table border="0" width="100%" height="24px" style="background: rgb(192, 212, 240); font-size:12px;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td align="right">
    			共有&nbsp;<s:property value="#request.kaoqinyear_list_page.rowCount" />&nbsp;条信息&nbsp;&nbsp;
    			<s:property value="#request.kaoqinyear_list_page.pageSize" />条/页&nbsp;&nbsp;
    			页次：<span style="color:red;"><s:property value="#request.kaoqinyear_list_page.pageIndex" /></span>/<s:property value="#pageCount" />页&nbsp;&nbsp;
    			<s:if test="#request.kaoqinyear_list_page.pageIndex == 1">首页</s:if>
    			<s:else><a class="page_l" href="javascript:go(1)">[首页]</a></s:else>
    			<s:if test="#request.kaoqinyear_list_page.pageIndex == 1">上一页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#request.kaoqinyear_list_page.pageIndex-1" />)">[上一页]</a></s:else>
    			<s:if test="#request.kaoqinyear_list_page.pageIndex >= #pageCount">下一页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#request.kaoqinyear_list_page.pageIndex+1" />)">[下一页]</a></s:else>
    			<s:if test="#request.kaoqinyear_list_page.pageIndex >= #pageCount">尾页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#pageCount" />)">[尾页]</a></s:else>
    		</td>
    	</tr>
    </table>
  </body>
</html>
