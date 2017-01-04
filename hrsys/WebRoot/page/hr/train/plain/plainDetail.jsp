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
    
    <title>培训申请管理</title>
    
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
			$("#btn_return").click(function(){
				self.location.href="<%=basePath%>trainPlain_trainPlainList.action";
			});
		});
	</script>
  </head>
  
  <body>
  <s:form>
  <input type="hidden" id="orgId" name="orgId"
			value="<s:property value="#request.orgId"/>" />
  	</s:form>
  	<s:if test="#request.employMsg != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.employMsg' escape='false'/>");
  		</script>
  	</s:if>
  	<table border="0" width="100%" style="background: rgb(192, 212, 240) url(images/bg_title01.gif) repeat-x;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td>
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
		    		<td width="10px"></td>
		    		<td height="26px" width="7px"><img src="images/yq_l.gif"/></td>
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;培训申请管理&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="btn_return" type="button" value="返回" />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
  <table border="0" width="100%" style="background: rgb(240, 240, 240); font-size: 12px;" cellpadding="0" cellspacing="2">
    	<tr>
    		<td align="right">培训编码：</td>
    		<td><s:property value="trainPlain.trainCode"/></td>
    		<td align="right">主办单位编码：</td>
    		<td><s:property value="trainPlain.orgCode"/></td>
    	</tr>
    	<tr>
    		<td width="15%" align="right">培训类别：</td>
    		<td width="25%"><s:property value="trainPlain.trainType"/></td>
    		<td width="15%" align="right">培训项目：</td>
    		<td>
    			<s:property value="trainPlain.trainSubject"/>
    		</td>
    	</tr>
    	<tr>
    		<td align="right">培训对象：</td>
    		<td><s:property value="trainPlain.trainObject"/></td>
    		<td align="right">主办单位：</td>
    		<td><s:property value="trainPlain.orgFullName"/></td>
    	</tr>
    	<tr>
    		<td align="right">开始时间：</td>
    		<td><s:property value="trainPlain.trainStart"/></td>
    			<td align="right">结束时间：</td>
    		<td><s:property value="trainPlain.trainEnd"/></td>
    	</tr>
    	<tr>
    		<td align="right">培训价格(元/人)：</td>
    		<td><s:property value="trainPlain.trainPrice"/></td>
    		<td align="right">培训方式：</td>
    		<td><s:property value="trainPlain.trainManner"/></td>
    	</tr>
    	<tr>
    		<td align="right">申请人：</td>
    		<td><s:property value="trainPlain.applicantName"/></td>
    		<td align="right">申请人编码：</td>
    		<td><s:property value="trainPlain.applicantCode"/></td>
    	</tr>
    	<tr>
    		<td align="right">备注：</td>
    		<td><s:property value="trainPlain.trainRemarks"/></td>
    		<td>
    		<s:if test="trainPlain.trainStatus == 3"><font style="color:#FF9900;font:bold;">待实施</font></s:if> 
				    <s:elseif test="trainPlain.trainStatus == 6"><font style="color:#CC6600;font:bold;">实施中</font></s:elseif>
				    <s:elseif test="trainPlain.trainStatus == 7"><font style="color:green;font:bold;">评价中</font></s:elseif>
				
    		<td>&nbsp;</td>
    	</tr>
    	<%-- <tr>
    		<td align="right">申请内容：</td>
    		<td><s:property value="trainPlain.trainDetail"/></td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    	</tr> --%>
    </table>
    
    		<span style="font-size:20px">申请内容：</span><br/>
    		<div style="border-color:black;width:auto;">${trainPlain.trainDetail}  </div>
  </body>
</html>
