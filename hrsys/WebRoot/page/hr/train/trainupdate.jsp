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
    <title>应聘信息修改</title>
	<link rel="stylesheet" type="text/css" href="style/date_input.css">	
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="js/Calendar2.js"></script><script type="text/javascript" language="javascript" src="js/train/jquery.date_input.js"></script>
	<script type="text/javascript" language="javascript" src="js/train/jquery.date_input.zh_CN.js"></script>
	<script type="text/javascript">$($.date_input.initialize);</script>
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#btn_ok").click(function(){
				var trainCode = $("#trainCode").val();
				if (trainCode.length == 0) {
					alert("培训编码不能为空");
					return;
				}
				var orgCode = $("#orgCode").val();
				if(orgCode.length == 0){
					alert("主办单位编码不能为空");
					return;
				}
				var trainType = $("#trainType").val();
				if (trainType.length == 0) {
					alert("培训类别不能为空");
					return;
				}
				var trainSubject = $("#trainSubject").val();
				if (trainSubject.length == 0) {
					alert("培训项目不能为空");
					return;
				}
				var trainObject = $("#trainObject").val();
				if (trainObject.length == 0) {
					alert("培训对象不能为空");
					return;
				}
				var orgFullName = $("#orgFullName").val();
				if(orgFullName.length == 0){
					alert("主办单位名称不能为空");
					return;
				}
				var trainStart = $("#trainStart").val();
				if(trainStart.length == 0){
					alert("开始时间不能为空");
					return;
				}
				var trainEnd = $("#trainEnd").val();
				if(trainEnd.length == 0){
					alert("结束时间不能为空");
					return;
				}
				var trainPrice = $("#trainPrice").val();
				if(trainPrice.length == 0){
					alert("培训价格不能为空");
					return;
				}
				var trainManner = $("#trainManner").val();
				if(trainManner.length == 0){
					alert("培训方式不能为空");
					return;
				}
				var applicantName = $("#applicantName").val();
				if(applicantName.length == 0){
					alert("申请人姓名不能为空");
					return;
				}
				var applicantCode = $("#applicantCode").val();
				if(applicantCode.length == 0){
					alert("申请人编码不能为空");
					return;
				}
				var trainDetail = $("#trainDetail").val();
				if(trainDetail.length == 0){
					alert("培训内容不能为空");
					return;
				}
				$("#trainUpdateForm")[0].submit();
			});
			$("#btn_return").click(function(){
				self.location.href="<%=basePath%>train_trainList.action?orgId="+
				$("#orgId").val();
			});
		});
	</script>
  </head>  
  <body>
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
  	<form id="trainUpdateForm" action="train_doUpdateTrain.action" method="post">
  	
  	<input type="hidden" id="orgId" name="orgId" value="<s:property value="orgId"/>"/>
  	<table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="btn_update" type="button"" value="保存" />&nbsp;&nbsp;
    			<input type="reset"" value="重置" />&nbsp;&nbsp;
    			<input id="btn_return" type="button" value="返回" />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <table border="0" width="100%" style="background: rgb(240, 240, 240); font-size: 12px;" cellpadding="0" cellspacing="2">
    	<tr>
    		<td align="right"><span style="color:red;">*</span>培训编码：</td>
    		<td><input type="text" name="train.trainCode" id="trainCode"value='<s:property value="train.trainCode"/>'/></td>
    		<td align="right"><span style="color:red;">*</span>主办单位编码：</td>
    		<td><input type="text" name="train.orgCode" id="orgCode" value='<s:property value="train.orgCode"/>'/></td>
    	</tr>
    	<tr>
    		<td width="15%" align="right"><span style="color:red;">*</span>培训类别：</td>
    		<td width="25%"><input type="text" name="train.trainType" id="trainType" value='<s:property value="train.trainType"/>' /></td>
    		<td width="15%" align="right"><span style="color:red;">*</span>培训项目：</td>
    		<td>
    			<input type="text" name="train.trainSubject" id="trainSubject" value='<s:property value="train.trainSubject"/>'/>
    		</td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>培训对象：</td>
    		<td><input type="text" name="train.trainObject" id="trainObject"  value='<s:property value="train.trainObject"/>'/></td>
    		<td align="right"><span style="color:red;">*</span>主办单位：</td>
    		<td><input type="text" name="train.orgFullName" id="orgFullName" value='<s:property value="train.orgFullName"/>'/></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>开始时间：</td>
    		<td><input type="text" name="train.trainStart" id="trainStart"  class="date_input"  value='<s:property value="train.trainStart"/>'/></td>
    			<td align="right"><span style="color:red;">*</span>结束时间：</td>
    		<td><input type="text" name="train.trainEnd" id="trainEnd"  class="date_input" value='<s:property value="train.trainEnd"/>' /></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>培训价格(元/人)：</td>
    		<td><input type="text" name="trian.trainPrice" id="trainPrice"  value='<s:property value="train.trainPrice"/>'/></td>
    		<td align="right"><span style="color:red;">*</span>培训方式：</td>
    		<td><input type="text" name="train.trainManner" id="trainManner"  value='<s:property value="train.trainManner"/>'/></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>申请人：</td>
    		<td><input type="text" name="train.applicantName" id="applicantName"  value='<s:property value="train.applicantName"/>'/></td>
    		<td align="right"><span style="color:red;">*</span>申请人编码：</td>
    		<td><input type="text" name="train.applicantCode"  id="applicantCode" value='<s:property value="train.applicantCode"/>' /></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;"></span>备注：</td>
    		<td><input type="text" name="train.trainRemarks" id="trainRemarks" value='<s:property value="train.trainRemarks"/>'/></td>
    		<td><input type="hidden" name="train.trainStatus" id="trainStatus" value="1" /></td>
    		<td>&nbsp;</td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>申请内容：</td>
    		<td><input type="text" name="train.trainDetail" id="trainDetail"  value='<s:property value="train.trainDetail"/>'/></td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    	</tr>
    </table>
    </form>
  </body>
</html>
