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
		        $("#trainPlainForm").attr("action", "effectAction_toIndividualEnval?pageIndex=1");
				$("#trainPlainForm")[0].submit();		   
		     }
		   );
		    $("#doSave").click(
		   function(){
		      
		        $("#trainPlainForm").attr("action", "effectAction_doIndividualEnval?scoreId="+$("#scoreId").val());
				$("#trainPlainForm")[0].submit();		   
		     }
		   );
		});
</script>
</head>
<body style="text-align:left;">
	  <s:if test="#request.msg != null">
		<script type="text/javascript" language="javascript">
			alert("<s:property value='#request.msg' escape='false'/>");
		</script>
	</s:if> 
	<table border="0" width="100%" style="background: rgb(192, 212, 240);"
		cellpadding="0" cellspacing="0">
		<tr>
			<td height="30px" valign="middle" style="padding-left: 30px;">
			
				<input type="button" id="doSave" value="保存"<s:if test="trainScore.status==\"2\" || trainScore.status==\"3\"">disabled="disabled"</s:if>>
				<input type="button" id="doReturn" value="返回">
				<strong style="color:blue;font-size:medium;">
				</strong>
			</td>
		</tr>
	</table>
	<form name="trainPlainForm" id="trainPlainForm" action="" method="post">
	   <table  border="0" width="100%"
			style="background: #fafafa; font-size: 12px;" cellpadding="0"
			cellspacing="1">
	     <tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
	     <td>员工姓名</td>
	     <td>培训项目</td>
	     </tr>
		<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
		<td><s:property value="trainScore.employeename"/>
		 </td>
		 <td>
		<s:property value="trainScore.trainsubject"/>
		 </td>
		 </tr>
		 </table>
		 <textarea name="evaluate" cols="50" rows="10" placeholder="请输入评价" style="text-align:left;"
		  <s:if test="trainScore.status==\"2\" || trainScore.status==\"3\"">disabled="disabled"</s:if>>
		 <s:property value="trainScore.evaluate"/>
		 </textarea>
		 <input id="scoreId" type="hidden" value="<s:property value="trainScore.id"/>" disabled="disabled"
		     name="scoreId"/>
	</form>
</body>
</html>
