<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@page import="org.apache.commons.fileupload.RequestContext"%>
<%@page import="org.omg.CORBA.Request"%>
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
				if($("#starttime").val().length == 0){
					msg += "起薪日期不能为空\n";
				}
				if($("#endtime").val().length == 0){
					msg += "截止日期不能为空\n";
				}
				if($("#pingdingtime").val().length == 0){
					msg += "评定日期不能为空\n";
				}
				if($("#tocompany").val().length == 0){
					msg += "出生日期不能为空\n";
				}if (msg.length != 0) {
					alert(msg);
				} else
				{
					$("#accountAddForm")[0].submit();
				}
			});
			$("#btn_return").click(function(){
				var oid = $("#orgId").val();
				var pi = $("#pageIndex").val();
				self.location.href="<%=basePath%>yonggong_list.action?orgId="+oid+"&pageIndex="+pi;
			});
				$(".choose_starttime").live("click",function(){
						var rtnValue = window.showModalDialog("<%=basePath%>page/hr/kaoqin2/chooseKaoQinTime_month.jsp","","location:no;dialogWidth:420px;dialogHeight:285px");					
						if (rtnValue.length > 0) {			
					    $("#starttime").val(rtnValue);														    
					}					
				});	
					$(".choose_pingdingtime").live("click",function(){
						var rtnValue = window.showModalDialog("<%=basePath%>page/hr/kaoqin2/chooseKaoQinTime_month.jsp","","location:no;dialogWidth:420px;dialogHeight:285px");					
						if (rtnValue.length > 0) {			
					    $("#pingdingtime").val(rtnValue);														    
					}					
				});	
				$(".choose_endtime").live("click",function(){
						var rtnValue = window.showModalDialog("<%=basePath%>page/hr/kaoqin2/chooseKaoQinTime_month.jsp","","location:no;dialogWidth:420px;dialogHeight:285px");					
						if (rtnValue.length > 0) {			
					    $("#endtime").val(rtnValue);														    
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
    <form id="accountAddForm" action="yonggong_BorrowOut.action" method="post">
    <input type="hidden" id="orgId" name="orgId" value="<s:property value="orgId"/>" />
    <input type="hidden" id="employeeId" name="employeeId" value="<s:property value="employeeId"/>" />
    <input type="hidden" id="pageIndex" name="pageIndex" value="<s:property value="pageIndex"/>" />
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="btn_add" type="button"" value="提交" />&nbsp;&nbsp;
    			<input type="reset"" value="重置" />&nbsp;&nbsp;
    			<input id="btn_return" type="button" value="返回" />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <table border="0" width="100%" style="background: rgb(240, 240, 240); font-size: 12px;" cellpadding="0" cellspacing="2">
      <input type="hidden" id="diao_empid" name="borrowout.empid" value="<s:property value="#request.emp.id"/>" />   	
      <input type="hidden" id="diao_isfanhui" name="borrowout.isfanhui" value="未返回" />   	      
      <input type="hidden" id="diao_fromorgid" name="borrowout.fromorgid" value="<s:property value="#request.emp.orgid"/>" />   	
      <input type="hidden" id="diao_orgId" name="borrowout.toorgid" value=""/>    
          
    		<tr height="30px;">
    		<td width="15%" align="right"><span style="color:red;">*</span>员工姓名：</td>
    		<td width="30%"><input type="text" name="borrowout.empname" id="diao_empname" value="<s:property value="#request.emp.employeeName"/>"/></td>
    		<td width="15%" align="right">&nbsp;</td>
    		<td width="20%">&nbsp;</td>
    		<td width="20%">&nbsp;</td>
    	</tr>
    	<tr height="30px;">
    		<td width="15%" align="right"><span style="color:red;">*</span>起薪时间：</td>
    		<!-- 选择日期 -->
    		<td>
		<div
			style="float: left; background: rgb(240, 240, 240); font-size: 12px;">
			
			<input type="text"
				name="borrowout.starttime" id="starttime" />
		</div>
		<div class='choose_starttime'
			style='width: 18px; height: 18px; float: left; cursor: pointer; background: rgb(240, 240, 240); font-size: 12px;'>
			<img src='images/choose.jpg' />
		</div>
        </td>
        <!-- 选择日期 -->  	
    		<td width="15%" align="right"><span style="color:red;">*</span>截止时间：</td>
    		<!-- 选择日期 -->
    		<td>
		<div
			style="float: left; background: rgb(240, 240, 240); font-size: 12px;">
			
			<input type="text"
				name="borrowout.endtime" id="endtime" />
		</div>
		<div class='choose_endtime'
			style='width: 18px; height: 18px; float: left; cursor: pointer; background: rgb(240, 240, 240); font-size: 12px;'>
			<img src='images/choose.jpg' />
		</div>
        </td>
        <!-- 选择日期 -->  
    		<td width="20%">&nbsp;</td>
    	</tr>
    	<tr height="30px;">
    		<td align="right"><span style="color:red;">*</span>学历：</td>
    		<td><input type="text" name="borrowout.education" id="education" value="<s:property value="#request.emp.education"/>"/></td>
    		<td align="right"><span style="color:red;">*</span>公司去向：</td>
    	    <td><input type="text" name="borrowout.tocompany" id="tocompany" /></td>
    		
    		<td>&nbsp;</td>
    	</tr>
    		<tr height="30px;">
    		
 		<td align="right"><span style="color:red;">*</span>职称等级：</td>
    	    <td><input type="text" name="borrowout.zhichengdj" id="zhichengdj" /></td>
    		  		
    		<td align="right"><span style="color:red;">*</span>评定时间：</td>
    	    	<!-- 选择日期 -->
    		<td>
		<div
			style="float: left; background: rgb(240, 240, 240); font-size: 12px;">
			
			<input type="text"
				name="borrowout.pingdingtime" id="pingdingtime" />
		</div>
		<div class='choose_pingdingtime'
			style='width: 18px; height: 18px; float: left; cursor: pointer; background: rgb(240, 240, 240); font-size: 12px;'>
			<img src='images/choose.jpg' />
		</div>
        </td>
        <!-- 选择日期 -->
    		<td>&nbsp;</td>
    	</tr>
    		<tr height="30px;">
    		<td align="right"><span style="color:red;">*</span>独子费：</td>
    		<td><input type="text" name="borrowout.duzifei" id="duzifei" /></td>
    		<td align="right"><span style="color:red;">*</span>社保基数：</td>
    		
    		<td><input type="text" name="borrowout.shebaojishu" id="shebaojishu" /></td>
    		 
    		<td>&nbsp;</td>
    	</tr>
    		<tr height="30px;">
    		<td align="right"><span style="color:red;">*</span>住房公积金：</td>
    		<td><input type="text" name="borrowout.gongjijin" id="gongjijin" /></td>
    		<td align="right"><span style="color:red;">*</span>调动理由：</td>
    		<td><input type="text" name="borrowout.reason" id="reason" /></td>
    		<td>&nbsp;</td>
    	</tr>
    		<tr height="30px;">
    		<td align="right"><span style="color:red;">*</span>备注：</td>
    		<td><input type="text" name="borrowout.content" id="content" /></td>
    		
    	</tr>    		    	
    </table>
    </form>
  </body>
</html>
