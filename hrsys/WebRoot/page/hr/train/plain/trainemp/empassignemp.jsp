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
    
    <title>My JSP 'roleassignemp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#sel_Org").change(function(){
				var orgId = $(this).val();
				if(orgId.length != 0) {
					var path = "<%=basePath%>ajax/empAjax_getEmpsByOrgId.action?orgId="+orgId+"&time="+new Date().getTime();
					$.getJSON(path, function(data){
					   $("#sel_emp").empty();
						var opts = "";
						$.each(data, function(item){
							if($("#existing_emp option[value='"+data[item].ID+"']").size() == 0){
							   	opts += "<option value='"+data[item].ID+"'>"+data[item].EMPNAME+"</option>";
							}
						});
						$("#sel_emp").html(opts);
					});
				}
			});
			$("#sel_emp").dblclick(function(){
				var emps = $("#existing_emp").html();
				$("#sel_emp option:selected").each(function(){
					if($("#existing_emp option[value='"+$(this).val()+"']").size() == 0){
						emps += "<option value='"+$(this).val()+"'>"+$(this).text()+"</option>";
					}
				});
				$("#existing_emp").html(emps);
			});
			$("#existing_emp").dblclick(function(){
				$("#existing_emp option:selected").each(function(){
					$(this).remove();
				});
			});
			$("#btn_add").click(function(){
				var empIds = "";
				$("#existing_emp option").each(function(){
					empIds += $(this).val() + ",";
				});
				empIds = empIds.substring(0,empIds.length - 1);
				$("#emps").val(empIds);
				$("#trainAssignempForm")[0].submit();
			});
			$("#btn_return").click(function(){
				self.location.href="<%=basePath%>trainPlain_trainPlainList.action";
			});
		});
	</script>
  </head>
  
  <body>
  	<s:if test="#request.assignMsg != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.assignMsg' escape='false'/>");
  		</script>
  	</s:if>
    <table border="0" width="100%" style="background: rgb(192, 212, 240) url(images/bg_title01.gif) repeat-x;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td>
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
		    		<td width="10px"></td>
		    		<td height="26px" width="7px"><img src="images/yq_l.gif"/></td>
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;培训人员管理&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="btn_add" type="button"" value="保存" />&nbsp;&nbsp;
    			<input id="btn_return" type="button" value="返回" />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    
    <div style="padding:10px; text-align: center;">
    <select id="sel_Org" style="padding:3px;">
    	<option value="">-----请选择组织-----</option>
    	<s:iterator var="o" value="#request.orgList">
    		<option value="<s:property value="ID" />"><s:property value="FULLNAME" /></option>
    	</s:iterator>
    </select>
    </div>
    
    <table border="0" width="100%" style="font-size:12px;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td width="39%" align="right" valign="middle"><div style="color:green; width: 300px; text-align: center; font-size: 14px; font-weight: bold; padding-bottom:10px;">该组织下员工</div>
    			<select id="sel_emp" size="25" style="width: 300px;"></select>
    		</td>
    		<td width="22%" align="center" valign="middle" style="color:red;">
    			<div style="margin:10px;">注意：</div>
    			<ul style="text-align: left; padding-left: 18px;">
    				<li style="list-style-type: disc;">双击“该组织下员工”中的列表项可添加到“被选中员工”列表内</li>
    				<div style="height:20px;"></div>
    				<li style="list-style-type: disc;">双击“培训项目包含的员工”列表项可删除员工</li>
    			</ul>
    		</td>
    		<td width="39%" align="left" valign="middle" ><div style="color:red; width: 300px; text-align: center; font-size: 14px; font-weight: bold; padding-bottom:10px;">培训项目包含的员工</div>
    			<select id="existing_emp" size="25" style="width: 300px;">
			    	<s:iterator var="o" value="#request.empList">
			    		<option value="<s:property value="ID" />"><s:property value="EMPNAME" /></option>
			    	</s:iterator>
			    </select>
    		</td>
    	</tr>
    </table>
    <form id="trainAssignempForm" action="trainPlain_assignemp.action" method="post">
    	<input type="hidden" id="trainId" name="trainId" value="<s:property value="trainId"/>" />
    	<input type="hidden" id="emps" name="emps" value="" />
    </form>
  </body>
</html>
