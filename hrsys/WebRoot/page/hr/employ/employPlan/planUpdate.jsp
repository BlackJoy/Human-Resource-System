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
    
    <title>招聘计划书信息修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<!-- 
	 -->
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="js/Calendar2.js"></script>
	<script type="text/javascript" language="javascript">
		var en = $("#").val();
		$(document).ready(function(){
			$("#btn_add").click(function(){
				var en = $("#employname").val();
				if (en.length == 0) {
					alert("姓名不能为空");
					return;
				}
				var sex = $("#sex").val();
				if(sex.length == 0){
					alert("性别不能为空");
					return;
				}
				var spe = $("#planmajor").val();
				if (spe.length == 0) {
					alert("专业不能为空");
					return;
				}
				var num = $("#num").val();
				if (num.length == 0) {
					alert("人数不能为空");
					return;
				}
				var major = $("#major").val();
				if (major.length == 0) {
					alert("专业不能为空");
					return;
				}
				var degree = $("#englishDegree").val();
				if (degree.length == 0) {
					alert("英语水平要求不能为空");
					return;
				}
				var isTrain = $("#isTrain").val();
				if(isTrain.length == 0){
					alert("岗前培训不能为空");
					return;
				}
				var forSalary = $("#forSalary").val();
				if(forSalary.length == 0){
					alert("期望工资不能为空");
					return;
				}
				var wtime = $("#planWTime").val();
				if(wtime.length == 0){
					alert("到岗时间不能为空");
					return;
				}
				$("#planUpdateForm")[0].submit();
			});
			$("#btn_return").click(function(){
				self.location.href="<%=basePath%>employPlan_toUpdate.action";
			});
		});
	</script>
  </head>
  
  <body>
  	<s:if test="#request.planMsg != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.planMsg' escape='false'/>");
  		</script>
  	</s:if>
  	<table border="0" width="100%" style="background: rgb(192, 212, 240) url(images/bg_title01.gif) repeat-x;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td>
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
		    		<td width="10px"></td>
		    		<td height="26px" width="7px"><img src="images/yq_l.gif"/></td>
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;招聘计划书信息管理&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
  	<form id="planUpdateForm" action="employPlan_updatePlan.action" method="post">
  	<input type="hidden" id="employPlan_Id" name="eplyPlan.id" value="<s:property value="#request.planDetail.id"/>"/>
  	<input type="hidden" id="employPlan_ORG" name="eplyPlan.org" value="<s:property value="#request.planDetail.org"/>"/>
  	<!-- 
  	<input type="hidden" id="employ_orgId" name="employ.orgid" value="<s:property value="#request.planDetail.ORGID"/>"/>
  	 -->
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="btn_add" type="button"" value="保存" />&nbsp;&nbsp;
    			<input type="reset"" value="重置" />&nbsp;&nbsp;
    			<input id="btn_return" type="button" value="返回" />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <table border="0" width="100%" style="background: rgb(240, 240, 240); font-size: 12px;" cellpadding="0" cellspacing="2">
    	<tr>
    		<td width="15%" align="right"><span style="color:red;">*</span>计划书标题：</td>
    		<td width="25%"><input type="text" name="eplyPlan.name" id="employname" value="<s:property value="#request.planDetail.name"/>" /></td>
    		<td width="15%" align="right"><span style="color:red;">*</span>性别要求：</td>
    		<td>
    			<select name="eplyPlan.sex" id="sex">
    				<option value="0" <s:if test="#request.planDetail.sex == 0"> selected="selected"</s:if>>女</option>
    				<option value="1" <s:if test="#request.planDetail.sex == 1"> selected="selected"</s:if>>男</option>
    				<option value="2" <s:if test="#request.planDetail.sex == 2"> selected="selected"</s:if>>无</option>
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>招聘人数：</td>
    		<td><input type="text" name="eplyPlan.num" id="num" value="<s:property value="#request.planDetail.num"/>" /></td>
    		<td align="right"><span style="color:red;">*</span>招聘专业：</td>
    		<td><input type="text" name="eplyPlan.major" id="major" value="<s:property value="#request.planDetail.major"/>" /></td>
    	</tr>
    	<tr>
    		
    		<td align="right"><span style="color:red;">*</span>招聘岗位：</td>
    		<td>
    			<s:select list="#request.curr_post" id="position" name="eplyPlan.position" listKey="postName" listValue="postName" headerKey="%{#request.planDetail.position}" headerValue="%{#request.planDetail.position}" ></s:select>
    		</td>
    		<td align="right"><span style="color:red;">*</span>英语水平要求：</td>
    		<td>
    			<select name="eplyPlan.englishDegree" id="englishDegree">
    				<option value="0" <s:if test="#request.planDetail.englishDegree == 0"> selected="selected"</s:if>>四级</option>
    				<option value="1" <s:if test="#request.planDetail.englishDegree == 1"> selected="selected"</s:if>>六级</option>
    				<option value="2" <s:if test="#request.planDetail.englishDegree == 2"> selected="selected"</s:if>>无</option>
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>是否需要岗前培训：</td>
    		<td><select name="eplyPlan.isTrain" id="isTrain">
    				<option value="0" <s:if test="#request.planDetail.isTrain == 0"> selected="selected"</s:if>>是</option>
    				<option value="1" <s:if test="#request.planDetail.isTrain == 1"> selected="selected"</s:if>>否</option>
    			</select></td>
    		<td align="right"><span style="color:red;">*</span>职位要求：</td>
    		<td><input type="text" name="eplyPlan.positonAsk" id="positionAsk" value="<s:property value="#request.planDetail.positonAsk"/>" /></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>期望工资：</td>
    		<td><input type="text" name="eplyPlan.forSalary" id="forSalary" value="<s:property value="#request.planDetail.forSalary"/>" /></td>
    		<td align="right"><span style="color:red;">*</span>到岗时间：</td>
    		<td><input type="text" name="eplyPlan.wwT" id="planWTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" value="<s:property value="#request.planDetail.wwT"/>" /></td>
    	</tr>
    </table>
    </form>
  </body>
</html>
