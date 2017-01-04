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
    
    <title>培训申请</title>
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<!-- 
	 -->
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="js/Calendar2.js"></script>
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#btn_return").click(function(){
				self.location.href="<%=basePath%>train_trainList.action";
			});
		});
	</script>
  </head>
  
  <body>
  	<s:if test="#request.trainMsg != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.trainMsg' escape='false'/>");
  		</script>
  	</s:if>
   <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
    		 	<td>培训类别</td>
				<td>培训项目</td>
				<td>主办单位</td>
				<td>开始时间</td>
				<td>培训价格</td>
				<td>培训方式</td>
				<td>备注</td>
    	</tr>
    	<s:iterator var="account" value="#request.train" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px; text-align: center;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px; text-align: center;">
			</s:else>
				<td><s:property value="trainType"/></td>
				<td><s:property value="trainSubject"/></td>
				<td><s:property value="orgFullName"/></td>				
				<td><s:date name="trainStart" format="yyyy-MM-dd" /></td>
				<td><s:property value="trainPrice"/></td>
				<td><s:property value="trainManner"/></td>
				<td><s:property value="trainRemarks"/></td>
    		</tr>
    	</s:iterator>
    </table>
  </body>
</html>
