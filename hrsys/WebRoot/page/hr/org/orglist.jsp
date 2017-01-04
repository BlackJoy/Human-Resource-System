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
    
    <title>组织列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<style type="text/css">
	#showOrg td{width:150px;}
	#showOrg .orgCode{width:350px;
	</style>
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("div.org_parent").click(function(event){
				var p_level = $(this).attr("id");
				if($(this).attr("isend")=="true"){
					if ($(this).find("img:first").attr("src")=="images/M1.gif") {
						$(this).find("img:first").attr("src", "images/M2.gif");
						$("tr:[parentId='"+p_level+"']").show();
					} else {
						$(this).find("img:first").attr("src", "images/M1.gif");
						$("tr:[parentId='"+p_level+"']").hide();
					}
				} else {
					if ($(this).find("img:first").attr("src")=="images/P1.gif") {
						$(this).find("img:first").attr("src", "images/P2.gif");
						$("tr:[parentId='"+p_level+"']").show();
					} else {
						$(this).find("img:first").attr("src", "images/P1.gif");
						$("tr:[parentId='"+p_level+"']").hide();
					}
				}
				event.stopPropagation();
			});
			$("#doAdd").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个组织");
				} else if (count > 1) {
					alert("只能对单个组织进行操作");
				} else {
					$("#orgForm").attr("action", "org_toAdd.action");
					$("#orgForm")[0].submit();
				}
			});
			$("#doUpdate").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个组织");
				} else if (count > 1) {
					alert("只能对单个组织进行操作");
				} else {
					$("#orgForm").attr("action", "org_toUpdate.action");
					$("#orgForm")[0].submit();
				}
			});
			$("#doDelete").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个组织");
				} else if (count > 1) {
					alert("只能对单个组织进行操作");
				} else {
					if(confirm("是否确定删除勾选的组织？")){
						$("#orgForm").attr("action", "org_delete.action");
						$("#orgForm")[0].submit();
					}
				}
			});
		});
	</script>
  </head>
  
  <body>
  	<s:if test="#request.orgMsg != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.orgMsg' escape='false'/>");
  		</script>
  	</s:if>
    <table border="0" width="100%" style="background: rgb(192, 212, 240) url(images/bg_title01.gif) repeat-x;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td>
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
		    		<td width="10px"></td>
		    		<td height="26px" width="7px"><img src="images/yq_l.gif"/></td>
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;组织信息管理&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="doAdd" type="button" value="增加子组织" <ct:btnEnable id="1.1.1.1" /> />&nbsp;&nbsp;
    			<input id="doUpdate" type="button" value="修改" <ct:btnEnable id="1.1.1.2" /> />&nbsp;&nbsp;
    			<input id="doDelete" type="button" value="删除" <ct:btnEnable id="1.1.1.3" /> />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <form id="orgForm" action="" method="post">
    <table id="showOrg" border="0" width="100%" style="background: #fff; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr height="20px;" valign="middle" style="background: rgb(221, 232, 247); text-align: center;">
    		<td class="orgCode">组织编码</td>
    		<td>组织名称</td>
    		<td>组织简称</td>
    		<td>组织类型</td>
    		<td>组织状态</td>
    	</tr>
    	<ct:orgTBL/>
    </table>
    </form>
  </body>
</html>