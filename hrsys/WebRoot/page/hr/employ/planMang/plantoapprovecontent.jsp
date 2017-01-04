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
    
    <title>招聘计划书审批流程</title>
    
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
		$(document).ready(function(){
			$("#btn_add").click(function(){
				var aid = $("#apId").val();
				if (aid == "0") {
					alert("审批模板不能为空");
					return;
				}
				var aname = $("#apprName").val();
				if(aname.length == 0){
					alert("审批项目名不能为空");
					return;
				}
				$("#planAppFlowForm").attr("action","<%=basePath%>employPlan_saveAppFlow.action");
				$("#planAppFlowForm").removeAttr("target");
				$("#planAppFlowForm")[0].submit();
			});
			$("#btn_return").click(function(){
				self.location.href="<%=basePath%>employ_ruzhilist.action";
			});
			$("#apId").change(function(){
				var apid = $("#apId").val();
				if (apid != "0") {
					$("#PlanAppFlowForm").attr("action","<%=basePath%>employPlan_loadAppFlow.action");
					$("#planAppFlowForm").attr("target","apFlow");
					$("#planAppFlowForm")[0].submit();
				} else {
					$("#apFlow").attr("src","");
				}
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

    <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
    		<td>招聘计划书名字</td>
    		<td>招聘人数</td>
    		<td>招聘专业</td>
    		<td>性别要求</td>
    		<td>英语水平要求</td>
    		<td>是否需要岗前培训</td>
    		<td>职位要求</td>
    		<td>到岗时间</td>
    		<td>职位月薪</td>
    	</tr>
    	<s:iterator var="account" value="#request.curr_plan" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px; text-align: center;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px; text-align: center;">
			</s:else>
				<td><s:property value="#curr_plan.name" /></td>
				<td><s:property value="#curr_plan.num" /></td>
				<td><s:property value="#curr_plan.major"/></td>
				<td><s:if test="#curr_plan.sex == 0">女</s:if><s:elseif test="#curr_planApprove.sex == 1">男</s:elseif><s:else>无</s:else></td>
				<td><s:if test="#curr_plan.englishDegree == 0">英语四级</s:if><s:elseif test="#curr_planApprove.englishDegree == 1">英语六级</s:elseif><s:else>无</s:else></td>
				<td><s:if test="#curr_plan.isTrain == 0">是</s:if><s:else>否</s:else></td>
				<td><s:property value="#ccurr_plan.positonAsk"/></td>
				<td><s:date name="#ccurr_plan.wwT" format="yyyy-MM-dd" /></td>
				<td><s:property value="#ccurr_plan.forSalary" /></td>
    		</tr>
    	</s:iterator>
    </table>
  </body>
</html>
