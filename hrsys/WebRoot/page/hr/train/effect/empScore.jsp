<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="ct" uri="/custom-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>培训效果评价管理页</title>
<link rel="stylesheet" type="text/css" href="style/common.css">

<script type="text/javascript" language="javascript"
	src="js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" language="javascript">
		$(document).ready(function(){
		   $("#btn_doReturn").click(
		   function(){
		        $("#trainPlainForm").attr("action", "effectAction_effectList");
				$("#trainPlainForm")[0].submit();		   
		   }		   
		   );
		   $("#btn_save").click(
		       function(){
		       // $(":text").css("background-color","#B2E0FF");
		        $(":text").each(function(){
		                
    					$("#empsScore").val($(this).attr("id")+":"+$(this).val()+","+$("#empsScore").val());
  					});
		        
		     alert($("#empsScore").val());
		     $("#trainPlainForm").attr("action", "effectAction_saveEmpScores");
			$("#trainPlainForm")[0].submit();
		        
		       }		   
		   );
		});
		function go(pageIndex){
			var oid = document.getElementById("orgId").value;
			self.location.href="<%=basePath%>train_effectlist.action?pageIndex="
				+ pageIndex + "&orgId=" + oid;
	}
</script>
</head>
<body>
	<table border="0" width="100%" style="background: rgb(192, 212, 240);"
		cellpadding="0" cellspacing="0">
		<tr>
			<td height="30px" valign="middle" style="padding-left: 30px;">	
			<input type="button" id="btn_save" value="保存"/>		
	        <input type="button" id="btn_doReturn" value="返回"/>
	     </td><td><!-- 
	     <input id="doEmpManage" type="button" value="培训人员管理" <ct:btnEnable id="1.10.2.3" /> />&nbsp;&nbsp; -->
				<strong style="color:blue;font-size:medium;">
				   培训项目：<s:property value="#request.trainPlainName"/>
				</strong>
			</td>
		</tr>
	</table>
	<form name="trainPlainForm" id="trainPlainForm" action="" method="post">
		
		<input type="hidden"
			id="pageIndex" name="pageIndex"
			value="<s:property value="pageIndex"/>" /> 
		<input type="hidden"
			id="trainId" name="trainId"
			value="<s:property value="trainId"/>" /> 
		<table border="0" width="100%"
			style="background: #fafafa; font-size: 12px;" cellpadding="0"
			cellspacing="1">
			<tr
				style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
							
				<td>员工姓名</td>
				<td>员工编号</td>
				<td>评价</td>
				<td>员工分数</td>
				<td>操作</td>
			</tr>
			<s:set name="rowCount" value="0"></s:set>
			<s:iterator var="emp" value="#request.empScorePage.list"
				status="i">
				<s:if test="#i.odd">
			<tr style="background: rgb(240, 240, 240); height: 22px; text-align: center;">
				</s:if>
				<s:else>
					<tr
						style="background: rgb(248, 248, 248); height: 22px; text-align: center;">
				</s:else>
				<%-- <td align="center">
				<input name="chkEmpId" id="chkEmpId"
					type="checkbox" value="<s:property value="#emp.id" />"/></td> --%>
				
				<td><s:property value="#emp.employeename" /></td>				
				<td><s:property value="#emp.employeecode"/></td>
				<td><s:property value="#emp.evaluate" /></td>
				<td ><input type="text" id="<s:property value="#emp.id"/>" value="<s:property value="#emp.empscore"/>"></td>
				<td>操作</td>
				
			</tr>
				<s:set name="rowCount" value="#i.count"></s:set>
			</s:iterator>
			<s:iterator begin="#rowCount+1"
				end="#request.empScorePage.pageSize" status="i">
				<s:if test="(#i.count+#rowCount) % 2 != 0">
					<tr style="background: rgb(240, 240, 240); height: 22px;">
				</s:if>
				<s:else>
					<tr style="background: rgb(248, 248, 248); height: 22px;">
				</s:else>
				<td align="center">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				</tr>
			</s:iterator>
		</table>		
	<input type="hidden" id="empsScore" name="empsScore" value=""> 
	</form>
	<s:set name="pageCount"
		value="(#request.empScorePage.rowCount+#request.empScorePage.pageSize-1)/#request.empScorePage.pageSize"></s:set>
	<table border="0" width="100%" height="24px"
		style="background: rgb(192, 212, 240); font-size: 12px;"
		cellpadding="0" cellspacing="0">
		<tr>
			<td align="right">共有&nbsp;<s:property
					value="#request.empScorePage.rowCount" />&nbsp;条信息&nbsp;&nbsp;
				<s:property value="#request.empScorePage.pageSize" />条/页&nbsp;&nbsp;
				页次：<span style="color: red;"><s:property
						value="#request.empScorePage.pageIndex" /></span>/<s:property
					value="#pageCount" />页&nbsp;&nbsp; <s:if
					test="#request.empScorePage.pageIndex == 1">首页</s:if> <s:else>
					<a class="page_l" href="javascript:go(1)">[首页]</a>
				</s:else> <s:if test="#request.empScorePage.pageIndex == 1">上一页</s:if> <s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#request.empScorePage.pageIndex-1" />)">[上一页]</a>
				</s:else> <s:if test="#request.empScorePage.pageIndex >= #pageCount">下一页</s:if>
				<s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#request.empScorePage.pageIndex+1" />)">[下一页]</a>
				</s:else> <s:if test="#request.empScorePage.pageIndex >= #pageCount">尾页</s:if>
				<s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#pageCount" />)">[尾页]</a>
				</s:else>
			</td>
		</tr>
	</table>
</body>
</html>
