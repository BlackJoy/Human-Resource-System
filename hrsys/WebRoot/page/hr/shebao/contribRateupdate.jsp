<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'contribRateadd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#btn_add").click(function(){
			var msg = "";
				if($("#e_Percent").val().length == 0){
					msg += "单位所占比例不能为空\n";
				}
				if($("#p_Percent").val().length == 0){
					msg += "员工所占比例不能为空\n";
				}
				
				if($("#template_Id").val()==0){
					msg += "请选择当前保险金项目\n";
				}
				
				
				if (msg.length != 0) {
					alert(msg);
				} else {
					//alert("将要插入一个岗位");
					$("#contribRateUpdateForm")[0].submit();
				}
				/* var msg = "";
				if($("#contribRatename").val().length == 0){
					msg += "账号不能为空\n";
				}
				if($("#password").val().length == 0){
					msg += "密码不能为空\n";
				}
				if($("#repassword").val().length == 0){
					msg += "确认密码不能为空\n";
				}
				if($("#password").val() != $("#repassword").val()){
					msg += "两次输入的密码不一致";
				}
				if (msg.length != 0) {
					alert(msg);
				} else {
					$("#contribRateUpdateForm")[0].submit();
				} */
			});
			$("#btn_return").click(function(){
				var pi = $("#pageIndex").val();
				self.location.href="<%=basePath%>contribRate_list.action?pageIndex=" + pi;
			});
		});
	</script>

  </head>
  
  <body>
  	<s:if test="#request.contribRateMsg != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.contribRateMsg' escape='false'/>");
  		</script>
  	</s:if>
    <form id="contribRateUpdateForm" action="contribRate_update.action" method="post">
    <input type="hidden" id="orgId" name="orgId" value="<s:property value="orgId"/>" />
    <input type="hidden" id="empId" name="empId" value="<s:property value="empId"/>" />
    <input type="hidden" id="pageIndex" name="pageIndex" value="<s:property value="pageIndex"/>" />
    <input type="hidden" id="pageIndex" name="contribRate.CId" value="<s:property value="contribRate.CId"/>" />
    <input type="hidden" id="pageIndex" name="contribRate.employeeId" value="<s:property value="contribRate.employeeId"/>" />
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="btn_add" type="button"" value="保存" />&nbsp;&nbsp;
    			<input type="reset"" value="重置" />&nbsp;&nbsp;
    			<input id="btn_return" type="button" value="返回" />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <table border="0" width="100%"
			style="background: rgb(240, 240, 240); font-size: 12px;"
			cellpadding="0" cellspacing="2">
			<tr height="30px;">
				<td align="right"><span style="color: red;">*</span>单位所占比例：</td>
				<td><input type="text" name="contribRate.p_Percent" id="p_Percent" /></td>
			</tr>
			<tr height="30px;">
				<td align="right"><span style="color: red;">*</span>员工所占比例：</td>
				<td><input type="text" name="contribRate.e_Percent" id="e_Percent" /></td>				
			</tr>
			<tr height="30px;">
				<td width="15%" align="right"><span style="color: red;">*</span>当前保险金项：</td>
				<td width="30%">
				<s:select name="contribRate.template_Id" id="template_Id" list="bXList" listKey="Id" listValue="curItem" theme="simple" >
				</s:select>
				</td>				
				<td width="15%" align="right">&nbsp;</td>
				<td width="20%">&nbsp;</td>
				<td width="20%">&nbsp;</td>
			</tr>			
		</table>
    </form>
  </body>
</html>
