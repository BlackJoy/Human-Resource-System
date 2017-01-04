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
				var en = $("#employname").val();
				if (en.length == 0) {
					alert("姓名不能为空");
					return;
				}
				var idcard = $("#identityCard").val();
				if(idcard.length == 0){
					alert("身份证不能为空");
					return;
				}
				var spe = $("#specialty").val();
				if (spe.length == 0) {
					alert("专业不能为空");
					return;
				}
				var gs = $("#graduateSchool").val();
				if (gs.length == 0) {
					alert("毕业院校不能为空");
					return;
				}
				var address = $("#address").val();
				if (address.length == 0) {
					alert("地址不能为空");
					return;
				}
				var phone = $("#phone").val();
				if(phone.length == 0){
					alert("联系电话不能为空");
					return;
				}
				var ypt = $("#yingpinTime").val();
				if(ypt.length == 0){
					alert("应聘时间不能为空");
					return;
				}
				$("#yingpinUpdateForm")[0].submit();
			});
			$("#btn_return").click(function(){
				self.location.href="<%=basePath%>employ_yingpinlist.action";
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
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;应聘信息管理&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
  	<form id="yingpinUpdateForm" action="employ_doUpdateYingPin.action" method="post">
  	<input type="hidden" id="employ_Id" name="employ.id" value="<s:property value="#request.curr_yingpin.ID"/>"/>
  	<!-- 
  	<input type="hidden" id="employ_orgId" name="employ.orgid" value="<s:property value="#request.curr_yingpin.ORGID"/>"/>
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
    		<td width="15%" align="right"><span style="color:red;">*</span>姓名：</td>
    		<td width="25%"><input type="text" name="employ.name" id="employname" value="<s:property value="#request.curr_yingpin.NAME"/>" /></td>
    		<td width="15%" align="right"><span style="color:red;">*</span>性别：</td>
    		<td>
    			<select name="employ.gender">
    				<option value="0" <s:if test="#request.curr_yingpin.GENDER == 0"> selected="selected"</s:if>>女</option>
    				<option value="1" <s:if test="#request.curr_yingpin.GENDER == 1"> selected="selected"</s:if>>男</option>
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>身份证：</td>
    		<td><input type="text" name="employ.identityCard" id="identityCard" value="<s:property value="#request.curr_yingpin.IDENTITYCARD"/>" /></td>
    		<td align="right"><span style="color:red;">*</span>学历：</td>
    		<td><ct:dicDataSelectTag name="employ.education" typeCode="hr_002" selected="${curr_yingpin['EDUCATION'] }" /></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>专业：</td>
    		<td><input type="text" name="employ.specialty" id="specialty" value="<s:property value="#request.curr_yingpin.SPECIALTY"/>" /></td>
    		<td align="right"><span style="color:red;">*</span>毕业院校：</td>
    		<td><input type="text" name="employ.graduateSchool" id="graduateSchool" value="<s:property value="#request.curr_yingpin.GRADUATESCHOOL"/>" /></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>地址：</td>
    		<td><input type="text" name="employ.address" id="address" value="<s:property value="#request.curr_yingpin.ADDRESS"/>" /></td>
    		<td align="right"><span style="color:red;">*</span>联系电话：</td>
    		<td><input type="text" name="employ.phone" id="phone" value="<s:property value="#request.curr_yingpin.PHONE"/>" /></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>所属组织：</td>
    		<td colspan="3"><!-- <input type="text" name="orgname" id="orgname" disabled="disabled" value="<s:property value="#request.curr_yingpin.ORGSHORTNAME"/>" /> -->
    			<select name="employ.orgid">
    				<s:iterator var="o" value="#request.orglist">
    					<option value="<s:property value="ID" />" <s:if test="#request.curr_yingpin.ORGID == ID">selected="selected"</s:if>><s:property value="FULLNAME" /></option>
    				</s:iterator>
    			</select>
    		</td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>应聘职位：</td>
    		<td><ct:dicDataSelectTag name="employ.position" typeCode="hr_003" selected="${curr_yingpin['POSITION']}" /></td>
    		<td align="right"><span style="color:red;">*</span>应聘时间：</td>
    		<td><input type="text" name="employ.yingpinTime" id="yingpinTime" onfocus="setday(this)" value="<s:property value="#request.curr_yingpin.YPT"/>" /><input type="hidden" name="employ.employStatus" id="employStatus" value="1" /></td>
    	</tr>
    </table>
    </form>
  </body>
</html>
