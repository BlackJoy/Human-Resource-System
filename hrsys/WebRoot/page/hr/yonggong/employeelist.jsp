<%@page import="com.sun.xml.internal.txw2.Document"%>
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
    
    <title>员工列表页</title>
    
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
			$("#neiBorrow").click(function(){
		
					$("#accountForm").attr("action", "yonggong_toNeiBorrow.action");
					$("#accountForm")[0].submit();
			});
			$("#borrowOut").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个员工");
				} else if (count > 1) {
					alert("只能对单个员工修改信息");
				} else {
					$("#accountForm").attr("action", "yonggong_toBorrowOut.action");
					$("#accountForm")[0].submit();
				}
			});
			$("#borrowIn").click(function(){
								
						$("#accountForm").attr("action", "yonggong_toBorrowIn.action");
						$("#accountForm")[0].submit();					

			});
		
		});		
		function go(pageIndex){
			var oid = document.getElementById("orgId").value;
			self.location.href="<%=basePath%>employee_list.action?pageIndex="+pageIndex+"&orgId="+oid;
		}
	</script>

  </head>
  
  <body>
   
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="neiBorrow" type="button" value="内部借调" <ct:btnEnable id="1.3.2.1" /> />&nbsp;&nbsp;
    			<input id="borrowOut" type="button" value="外部借出" <ct:btnEnable id="1.3.2.2" /> />&nbsp;&nbsp;
    			<input id="borrowIn" type="button" value="外部借入" <ct:btnEnable id="1.3.2.3" /> />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <form name="accountForm" id="accountForm" action="" method="post">
  	<input type="hidden" id="orgId" name="orgId" value="<s:property value="#request.orgId"/>"/>
  	<input type="hidden" id="pageIndex" name="pageIndex" value="<s:property value="pageIndex"/>"/>
    <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
    		<td width="30px"><input type="checkbox"/></td>
    		<td>员工编号</td>
    		<td>员工姓名</td>
    		<td>性别</td>
    		<td>所属部门</td>
    		<td>电话号码</td>
    		<td>住址</td>  		
    		<td>毕业院校</td>
    		<td>入职时间</td>
    		<td>试用开始时间</td>
    		<td>状态</td>
    	</tr>
    	<s:set name="rowCount" value="0"></s:set>
    	<s:iterator var="account" value="#request.employee_list_page.list" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>
				<td align="center"><input class="ckb" name="employeeId" type="checkbox" <s:if test="EMPLOYEESTATUS==6">disabled="disabled"</s:if>value="<s:property value="ID"/>"/></td>
				<td align="center"><s:property value="EMPLOYEECODE"/></td>
				<td align="center"><s:property value="EMPLOYEENAME"/></td>
				<td align="center"><s:property value="GENDER"/></td>
				<td align="center"><s:property value="ORGSHORTNAME"/></td>
				<td align="center"><s:property value="PHONE"/></td>
				<td align="center"><s:property value="ADDRESS"/></td>
				<td align="center"><s:property value="GRADUATESCHOOL"/></td>
				<td align="center"><s:property value="RUZHITIME"/></td>
				<td align="center"><s:property value="SHIYONGTIME"/></td>
					<s:if test="EMPLOYEESTATUS==2">
			    <td align="center" style="color:yellow">试用</td>
			    </s:if>
			    <s:elseif test="EMPLOYEESTATUS==3">
	   		    <td align="center" style="color:blue">待入职</td>			    
			    </s:elseif>
			     <s:elseif test="EMPLOYEESTATUS==4">
	   		    <td align="center" style="color:pin">入职审批中</td>			    
			    </s:elseif>
			     <s:elseif test="EMPLOYEESTATUS==5">
	   		    <td align="center" style="color:green">在岗</td>			    
			    </s:elseif>
				 <s:elseif test="EMPLOYEESTATUS==6">
	   		    <td align="center" style="color:red">借调中</td>			    
			    </s:elseif>
    		</tr>
    		<s:set name="rowCount" value="#i.count"></s:set>
    	</s:iterator>
    	<s:iterator begin="#rowCount+1" end="#request.employee_list_page.pageSize" status="i">
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
    		</tr>
    	</s:iterator>
    </table>
    </form>
    <s:set name="pageCount" value="(#request.employee_list_page.rowCount+#request.employee_list_page.pageSize-1)/#request.employee_list_page.pageSize"></s:set>
    <table border="0" width="100%" height="24px" style="background: rgb(192, 212, 240); font-size:12px;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td align="right">
    			共有&nbsp;<s:property value="#request.employee_list_page.rowCount" />&nbsp;条信息&nbsp;&nbsp;
    			<s:property value="#request.employee_list_page.pageSize" />条/页&nbsp;&nbsp;
    			页次：<span style="color:red;"><s:property value="#request.employee_list_page.pageIndex" /></span>/<s:property value="#pageCount" />页&nbsp;&nbsp;
    			<s:if test="#request.employee_list_page.pageIndex == 1">首页</s:if>
    			<s:else><a class="page_l" href="javascript:go(1)">[首页]</a></s:else>
    			<s:if test="#request.employee_list_page.pageIndex == 1">上一页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#request.employee_list_page.pageIndex-1" />)">[上一页]</a></s:else>
    			<s:if test="#request.employee_list_page.pageIndex >= #pageCount">下一页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#request.employee_list_page.pageIndex+1" />)">[下一页]</a></s:else>
    			<s:if test="#request.employee_list_page.pageIndex >= #pageCount">尾页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#pageCount" />)">[尾页]</a></s:else>
    		</td>
    	</tr>
    </table>
  </body>
</html>
