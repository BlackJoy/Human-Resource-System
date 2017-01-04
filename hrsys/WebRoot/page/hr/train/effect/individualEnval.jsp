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
<script type="text/javascript" language="javascript"
	src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
		   $("#doReturn").click(
		 
		      
		   function(){
		   var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个培训申请");
				} else if (count > 1) {
					alert("只能对单个培训申请进行修改");
				} else {	
		        $("#trainPlainForm").attr("action", "effectAction_effectList");
				$("#trainPlainForm")[0].submit();		   
		     }
		     }
		   );
		});
		function go(pageIndex){
			
			self.location.href="<%=basePath%>train_effectlist.action?pageIndex="
				+ pageIndex;
	}
</script>
</head>
<body>
	  <s:if test="#request.msg != null">
		<script type="text/javascript" language="javascript">
			alert("<s:property value='#request.msg' escape='false'/>");
		</script>
	</s:if> 
	<table border="0" width="100%" style="background: rgb(192, 212, 240);"
		cellpadding="0" cellspacing="0">
		<tr>
			<td height="30px" valign="middle" style="padding-left: 30px;">
				
				<strong style="color:blue;font-size:medium;">
				</strong>
			</td>
		</tr>
	</table>
	<form name="trainPlainForm" id="trainPlainForm" action="" method="post">
		<input type="hidden" id="orgId" name="orgId"
			value="<s:property value="#request.orgId"/>" /> 
		<input type="hidden"
			id="pageIndex" name="pageIndex"
			value="<s:property value="pageIndex"/>" /> 
		<table border="0" width="100%"
			style="background: #fafafa; font-size: 12px;" cellpadding="0"
			cellspacing="1">
			<tr
				style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
				<td width="30px"><input type="checkbox" /></td>
				<td>培训项目</td>				
				<td>操作</td>
			</tr>
			<s:set name="rowCount" value="0"></s:set>
			<s:iterator var="score" value="#request.person_plain_page_list.list"
				status="i">
				<s:if test="#i.odd">
			<tr style="background: rgb(240, 240, 240); height: 22px; text-align: center;">
				</s:if>
				<s:else>
					<tr
						style="background: rgb(248, 248, 248); height: 22px; text-align: center;">
				</s:else>
				<td align="center">
				<input class="ckb" name="scoreId"
					type="checkbox" value="<s:property value="#score.id" />"/></td>
				<td align="center"><a class="info"
					href="<%=basePath%>trainPlain_toDetailTrainPlain.action?trainId=<s:property value="#score.trainid"/>">
					<s:property value="#score.trainsubject" /></a></td>
				<td>
				  <s:if test="#score.status==0 || #score.status==1">
				    <a href="<%=basePath%>effectAction_toIndivEnvalPage?scoreId=<s:property value="#score.id"/>"> 
				    <input type="button" value="个人评价" /></a>
				   </s:if>
				  <s:else>
				       <a href="<%=basePath%>effectAction_toIndivEnvalPage?scoreId=<s:property value="#score.id"/>"> 
				       <input type="button" value="查看评价" /></a>
				  </s:else>
				</td>
			</tr>
				<s:set name="rowCount" value="#i.count"></s:set>
			</s:iterator>
			
			
			<s:iterator begin="#rowCount+1"
				end="#request.person_plain_page_list.pageSize" status="i">
				<s:if test="(#i.count+#rowCount) % 2 != 0">
					<tr style="background: rgb(240, 240, 240); height: 22px;">
				</s:if>
				<s:else>
					<tr style="background: rgb(248, 248, 248); height: 22px;">
				</s:else>
				<td align="center">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				</tr>
			</s:iterator>
		</table>
	</form>
	<s:set name="pageCount"
		value="(#request.person_plain_page_list.rowCount+#request.person_plain_page_list.pageSize-1)/#request.person_plain_page_list.pageSize"></s:set>
	<table border="0" width="100%" height="24px"
		style="background: rgb(192, 212, 240); font-size: 12px;"
		cellpadding="0" cellspacing="0">
		<tr>
			<td align="right">共有&nbsp;<s:property
					value="#request.person_plain_page_list.rowCount" />&nbsp;条信息&nbsp;&nbsp;
				<s:property value="#request.person_plain_page_list.pageSize" />条/页&nbsp;&nbsp;
				页次：<span style="color: red;"><s:property
						value="#request.person_plain_page_list.pageIndex" /></span>/<s:property
					value="#pageCount" />页&nbsp;&nbsp; <s:if
					test="#request.person_plain_page_list.pageIndex == 1">首页</s:if> <s:else>
					<a class="page_l" href="javascript:go(1)">[首页]</a>
				</s:else> <s:if test="#request.person_plain_page_list.pageIndex == 1">上一页</s:if> <s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#request.person_plain_page_list.pageIndex-1" />)">[上一页]</a>
				</s:else> <s:if test="#request.person_plain_page_list.pageIndex >= #pageCount">下一页</s:if>
				<s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#request.person_plain_page_list.pageIndex+1" />)">[下一页]</a>
				</s:else> <s:if test="#request.person_plain_page_list.pageIndex >= #pageCount">尾页</s:if>
				<s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#pageCount" />)">[尾页]</a>
				</s:else>
			</td>
		</tr>
	</table>
	<div id="popDiv" class="mydiv" style="display: none;">

	    <div class="SContent-box">
	        <div class="Close_btn">
	             <div onclick="closeDiv()"  style="display:block; cursor:pointer">关闭</div>
	        </div>	        
	    </div>
   </div>
    <div id="bg" class="bg" style="display: none;">
</div>
</body>
</html>
