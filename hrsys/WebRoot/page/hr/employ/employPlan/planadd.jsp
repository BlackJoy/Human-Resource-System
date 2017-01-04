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
    
    <title>招聘计划表信息录入</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<!-- 
	 -->
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#btn_add").click(function(){
				var en = $("#planname").val();
				if (en.length == 0) {
					alert("招聘计划书名字不能为空");
					return;
				}
				var idcard = $("#plannum").val();
				if(idcard.length == 0){
					alert("招聘数目不能为空");
					return;
				}
				var spe = $("#planmajor").val();
				if (spe.length == 0) {
					alert("专业不能为空");
					return;
				}
				var pos = $("#position").find('option:selected').text();
				if (pos.length == 0 || pos == "") {
					alert("招聘岗位不能为空");
					return;
				}
				var gs = $("#planSex").val();
				if (gs.length == 0) {
					alert("性别要求不能为空");
					return;
				}
				var address = $("#planEnglish").val();
				if (address.length == 0) {
					alert("英语要求不能为空");
					return;
				}
				var phone = $("#planTrain").val();
				if(phone.length == 0){
					alert("是否岗前培训不能为空");
					return;
				}
				var ypt = $("#planPositionAsk").val();
				if(ypt.length == 0){
					alert("岗位要求不能为空");
					return;
				}
				var wtime = $("#planWTime").val();
				if(wtime.length == 0){
					alert("到岗时间不能为空");
					return;
				}
				var salary = $("#planForSalary").val();
				if(salary.length == 0){
					alert("月薪水平不能为空");
					return;
				}
				$("#planAddForm")[0].submit();
			});
			$("#btn_return").click(function(){
				self.location.href="<%=basePath%>employPlan_employPlanList.action";
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
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;应聘信息管理&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
  	<form id="planAddForm" action="employPlan_doAddPlan.action" method="post">
  	<input type="hidden" id="orgId" name="orgId" value="<s:property value="#request.orgId"/>"/>
  	<input type="hidden" id="eplyPlan_orgId" name="eplyPlan.org" value="<s:property value="#request.orgId"/>"/>
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
    		<td width="15%" align="right"><span style="color:red;">*</span>招聘计划书名字：</td>
    		<td width="25%"><input type="text" name="eplyPlan.name" id="planname" /></td>
    		<td width="15%" align="right"><span style="color:red;">*</span>招聘人数：</td>
    		<td width="25%"><input type="text" name="eplyPlan.num" id="plannum" /></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>招聘专业：</td>
    		<td><input type="text" name="eplyPlan.major" id="planmajor" /></td>
    		<td width="15%" align="right"><span style="color:red;">*</span>性别要求：</td>
    		<td>
    			<select name="eplyPlan.sex" id="planSex">
    				<option value="0">女</option>
    				<option value="1">男</option>
    				<option value="2" selected="selected">无</option>
    			</select>
    		</td>
    	 <tr>
    	 <td align="right"><span style="color:red;">*</span>招聘岗位：</td>
    	 <td>
    			<s:select list="#request.curr_post" id="position" name="eplyPlan.position" listKey="postName" listValue="postName" headerValue=""></s:select>
    		</td>
    		<td align="right"><span style="color:red;">*</span>英语水平要求：</td>
    		<td>
    			<select name="eplyPlan.englishDegree" id="planEnglish">
    				<option value="0">英语四级</option>
    				<option value="1">英语六级</option>
    				<option value="2" selected="selected">无</option>
    			</select>
    		</td>
    		
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>职位要求：</td>
    		<td><textarea id="planPositionAsk" name="eplyPlan.positonAsk" rows="5" cols="30"></textarea></td>
    		<td align="right"><span style="color:red;">*</span>到岗时间：</td>
    		<td><input type="text" name="eplyPlan.wwT" id="planWTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>职位月薪：</td>
    		<td><input type="text" name="eplyPlan.forSalary" id="planForSalary"/></td>
    		<td align="right"><span style="color:red;">*</span>是否岗前培训：</td>
    		<td>
    			<select name="eplyPlan.isTrain" id="planTrain">
    				<option value="0">是</option>
    				<option value="1">否</option>
    			</select>
    		</td>
    		
    	</tr>
    	<tr>
    	<td><input type="hidden" id="eplyPlanStatus" name="eplyPlan.status" value="0"/></td>
    	</tr>
    </table>
    </form>
  </body>
    	
</html>
    	