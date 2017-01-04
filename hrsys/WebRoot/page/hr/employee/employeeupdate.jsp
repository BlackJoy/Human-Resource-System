<%@page import="com.sys.hr.employee.Employee"%>
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
    
    <title>My JSP 'accountadd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="js/Calendar2.js"></script>
	
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#btn_add").click(function(){
				var msg = "";
						if($("#employeeCode").val().length == 0){
					msg += "员工编号不能为空\n";
				}
				if($("#employeeName").val().length == 0){
					msg += "员工姓名不能为空\n";
				}
				if($("#identityCard").val().length == 0){
					msg += "身份证号不能为空\n";
				}
				if($("#education").val().length == 0){
					msg += "学历不能为空\n";
				}
				if($("#specialty").val().length == 0){
					msg += "专业不能为空\n";
				}
				if($("#graduateSchool").val().length == 0){
					msg += "毕业院校不能为空\n";
				}
				if($("#address").val().length == 0){
					msg += "住址不能为空\n";
				}
				if($("#phone").val().length == 0){
					msg += "电话不能为空\n";
				}
				if($("#shiyongTime").val().length == 0){
					msg += "试用时间不能为空\n";
				}
				if($("#ruzhiTime").val().length == 0){
					msg += "入职时间不能为空\n";
				}
				
				if (msg.length != 0) {
					alert(msg);
				} else {
					$("#accountUpdateForm")[0].submit();
				}
			});
			$("#btn_return").click(function(){
				var oid = $("#orgId").val();
				var pi = $("#pageIndex").val();
				self.location.href="<%=basePath%>employee_list.action?orgId="+oid+"&pageIndex="+pi;
			});
				//------------------选择部门start-------------------------
				$(".choose_org").live("click",function(){
					//---------------------------------------------------
					if(navigator.appName.indexOf("Micro")!=-1){//IE浏览器
						var rtnValue = window.showModalDialog("<%=basePath%>page/hr/zhangtao/chooseOrg.jsp","","location:no;dialogWidth:600px;dialogHeight:400px");
						//alert(rtnValue);
						var a=rtnValue.split(",");
						if (rtnValue.length > 0) {
							$("#orgName").val(a[0]);//文本框中显示选中的组织名称
							$("#orgId").val(a[1]);//保存选中的组织id
							//alert($("#orgName").val());
							//alert($("#orgId").val());						
						}
						return rtnVaule;						
					}else{
						//alert("火狐浏览器");
						window.open("<%=basePath%>page/hr/zhangtao/chooseOrg.jsp","","width=600,height=500,menubar=no,toolbar=no,location=no,scrollbars=no,status=no,modal=yes");
					}
					
					//--------------------------------------------------
					
				});
			$(".choose_shiyongTime").live("click",function(){
						var rtnValue = window.showModalDialog("<%=basePath%>page/hr/kaoqin2/chooseKaoQinTime_month.jsp","","location:no;dialogWidth:420px;dialogHeight:285px");					
						if (rtnValue.length > 0) {			
					    $("#shiyongTime").val(rtnValue);														    
					}					
				});	
				$(".choose_ruzhiTime").live("click",function(){
						var rtnValue = window.showModalDialog("<%=basePath%>page/hr/kaoqin2/chooseKaoQinTime_month.jsp","","location:no;dialogWidth:420px;dialogHeight:285px");					
						if (rtnValue.length > 0) {			
					    $("#ruzhiTime").val(rtnValue);														    
					}					
				});									

		});
	</script>

  </head>
  
  <body>
  	<s:if test="#request.accountMsg != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.accountMsg' escape='false'/>");
  		</script>
  	</s:if>
    <form id="accountUpdateForm" action="employee_update.action" method="post">
    <input type="hidden" id="orgId" name="orgId" value="<s:property value="orgId"/>" />
    <input type="hidden" id="empId" name="emp.Id" value="<s:property value="#request.p_emp.Id"/>" />
    <input type="hidden" id="pageIndex" name="pageIndex" value="<s:property value="pageIndex"/>" />
   <!-- 
    <input type="hidden" id="pageIndex" name="emp.id" value="<s:property value="emp.id"/>" />
    -->
    <input type="hidden" id="pageIndex" name="emp.employeeId" value="<s:property value="emp.employeeId"/>" />
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
    	<%
    	 Employee emp= (Employee)request.getAttribute("p_org");
    	 %>
    	<tr height="30px;">
    		<td width="15%" align="right"><span style="color:red;">*</span>员工编号：</td>
    		<td width="30%"><input type="text" name="emp.employeeCode" id="employeeCode" value="<s:property value="#request.p_emp.employeeCode" />" /></td>
    		<td width="15%" align="right">&nbsp;</td>
    		<td width="20%">&nbsp;</td>
    		<td width="20%">&nbsp;</td>
    	</tr>
    		<tr height="30px;">
    		<td align="right"><span style="color:red;">*</span>员工姓名：</td>
    		<td><input type="text" name="emp.employeeName" id="employeeName" value="<s:property value="#request.p_emp.employeeName" />"/></td>
    		<td align="right"><span style="color:red;">*</span>所在部门：</td>
    		 <input type="hidden" name="emp.orgid" id="orgid" value="<s:property value="#request.p_emp.orgid" />"/> 		
    		<!-- 弹出框选择部门 start-->
		<td>
		<div
			style="float: left; background: rgb(240, 240, 240); font-size: 12px;">
			
			<input type="text"
				name="orgName" id="orgName" value='<s:property value="#request.p_orgName"/>' />
		</div>
		<div class='choose_org'
			style='width: 18px; height: 18px; float: left; cursor: pointer; background: rgb(240, 240, 240); font-size: 12px;'>
			<img src='images/choose.jpg' />
		</div>
        </td>
		<!-- 弹出框选择部门 end-->	
    		<td>&nbsp;</td>
    	</tr>
    		<tr height="30px;">
    		<td align="right"><span style="color:red;">*</span>性别：</td>
    		<td>
    			<input type="radio" name="emp.gender" <s:if test="#request.p_emp.gender == 0">checked="checked"</s:if> value="0" />男&nbsp;&nbsp;
    			<input type="radio" name="emp.gender" <s:if test="#request.p_emp.gender == 1">checked="checked"</s:if> value="1" />女
    		</td>
    		
    		<td align="right"><span style="color:red;">*</span>身份证：</td>
    		<td><input type="text" name="emp.identityCard" id="identityCard" value="<s:property value="#request.p_emp.identityCard" />"/></td>
    		<td>&nbsp;</td>
    	</tr>
    		<tr height="30px;">
    		<td align="right"><span style="color:red;">*</span>学历：</td>
    		<td><input type="text" name="emp.education" id="education" value="<s:property value="#request.p_emp.education" />"/></td>
    		<td align="right"><span style="color:red;">*</span>专业：</td>
    		<td><input type="text" name="emp.specialty" id="specialty" value="<s:property value="#request.p_emp.specialty" />"/></td>
    		<td>&nbsp;</td>
    	</tr>
    		<tr height="30px;">
    		<td align="right"><span style="color:red;">*</span>毕业院校：</td>
    		<td><input type="text" name="emp.graduateSchool" id="graduateSchool" value="<s:property value="#request.p_emp.graduateSchool" />"/></td>
    		<td align="right"><span style="color:red;">*</span>住址：</td>
    		<td><input type="text" name="emp.address" id="address" value="<s:property value="#request.p_emp.address" />"/></td>
    		<td>&nbsp;</td>
    	</tr>
    		<tr height="30px;">
    		<td align="right"><span style="color:red;">*</span>电话：</td>
    		<td><input type="text" name="emp.phone" id="phone" value="<s:property value="#request.p_emp.phone" />"/></td>
   
    		<td align="right"><span style="color:red;">*</span>试用日期：</td>
    		<!-- 选择日期 -->
    		<td>
		<div
			style="float: left; background: rgb(240, 240, 240); font-size: 12px;">
			
			<input type="text"
				name="emp.shiyongTime" id="shiyongTime" value='<s:property value="#request.p_emp.shiyongTime"/>' />
		</div>
		<div class='choose_shiyongTime'
			style='width: 18px; height: 18px; float: left; cursor: pointer; background: rgb(240, 240, 240); font-size: 12px;'>
			<img src='images/choose.jpg' />
		</div>
        </td>
        <!-- 选择日期 -->
    		
    		<td>&nbsp;</td>
    	</tr>
    		<tr height="30px;">
    		<td align="right"><span style="color:red;">*</span>入职日期：</td>   		
    		<!-- 选择日期 -->
    		<td>
		<div
			style="float: left; background: rgb(240, 240, 240); font-size: 12px;">
			
			<input type="text"
				name="emp.ruzhiTime" id="ruzhiTime" value='<s:property value="#request.p_emp.ruzhiTime"/>' />
		</div>
		<div class='choose_ruzhiTime'
			style='width: 18px; height: 18px; float: left; cursor: pointer; background: rgb(240, 240, 240); font-size: 12px;'>
			<img src='images/choose.jpg' />
		</div>
        </td>
        <!-- 选择日期 -->
        </tr>
        <tr>
    		<td align="right"><span style="color:red;">*</span>员工状态：		
    		<s:select name="emp.employeeStatus" id="employeeStatus" list="#{2:'试用',3:'待入职',4:'入职审批中',5:'在岗',6:'借调中'}"  listKey="key" listValue="value" value="#request.p_emp.employeeStatus" /></td>
    		
    	</tr>
    	
    </table>
    </form>
  </body>
</html>
