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
    
    <title>日常考勤页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<style type="text/css">
	a.page_l{ text-decoration: none; color: blue;}
	a.page_l:hover{ text-decoration: underline; color: blue;}
	a.page_l:visited{ text-decoration: none; color: blue;}
	</style>
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript">
		window.onload=function load()
		{	//0缺勤，1正常出勤，2请假，3迟到
			var cq=document.getElementById("cq").value;
			if(cq==1){
				alert("已经存在记录");
				document.getElementById("cq").value='0';
			}
			
			var qj=document.getElementById("qj").value;
			if(qj==1){
				alert("已经存在记录");
				document.getElementById("qj").value='0';
			}
			
			var cd=document.getElementById("cd").value;
			if(cd==1){
				alert("已经存在记录");
				document.getElementById("cd").value='0';
			}
			
			var qq=document.getElementById("qq").value;
			if(qq==1){
				alert("已经存在记录");
				document.getElementById("qq").value='0';
			}
			
			var jb=document.getElementById("jb").value;
			if(jb==1){
				alert("已经存在记录");
				document.getElementById("jb").value='0';
			}
			if(jb==2){
				alert("加班记录已修改");
				document.getElementById("jb").value='0';
			}
			if(jb==3){
				alert("没有正常考勤记录，无法加班");
				document.getElementById("jb").value='0';
			}
			
		}
		
			
		
		$(document).ready(function(){			
			
			$("#doChuqin").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个应聘信息");
				}
				else 
				{
					var rtnValue = window.showModalDialog("<%=basePath%>page/hr/kaoqin/chooseKaoQinTime.jsp","","location:no;dialogWidth:420px;dialogHeight:285px");
					if (rtnValue.length > 0) {
					
					$("#kqTime").val(rtnValue);
					$("#kaoqinForm").attr("action", "kaoqin_Chuqin.action");
					$("#kaoqinForm")[0].submit();
					}
				}				
			});
			
			
			
			
			$("#doQingjia").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个应聘信息");
				}
				else 
				{
					var rtnValue = window.showModalDialog("<%=basePath%>page/hr/kaoqin/chooseKaoQinTime.jsp","","location:no;dialogWidth:420px;dialogHeight:285px");
					if (rtnValue.length > 0) {
					
					$("#kqTime").val(rtnValue);
					$("#kaoqinForm").attr("action", "kaoqin_Qingjia.action");
					$("#kaoqinForm")[0].submit();
					}
				}
			});
			
			
			$("#doChidao").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个应聘信息");
				}
				else 
				{
					var rtnValue = window.showModalDialog("<%=basePath%>page/hr/kaoqin/chooseKaoQinTime.jsp","","location:no;dialogWidth:420px;dialogHeight:285px");
					if (rtnValue.length > 0) {
					
					$("#kqTime").val(rtnValue);
					$("#kaoqinForm").attr("action", "kaoqin_Chidao.action");
					$("#kaoqinForm")[0].submit();
					}
				}
			});
			$("#doQueqin").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个应聘信息");
				}
				else 
				{
					var rtnValue = window.showModalDialog("<%=basePath%>page/hr/kaoqin/chooseKaoQinTime.jsp","","location:no;dialogWidth:420px;dialogHeight:285px");
					if (rtnValue.length > 0) {
					
					$("#kqTime").val(rtnValue);
					$("#kaoqinForm").attr("action", "kaoqin_Queqin.action");
					$("#kaoqinForm")[0].submit();
					}
				}
			});
			$("#doJiaban").click(function(){
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个应聘信息");
				}
				else 
				{
					var rtnValue = window.showModalDialog("<%=basePath%>page/hr/kaoqin/chooseKaoQinTime.jsp","","location:no;dialogWidth:420px;dialogHeight:285px");
					if (rtnValue.length > 0) {
					
					$("#kqTime").val(rtnValue);
					$("#kaoqinForm").attr("action", "kaoqin_Jiaban.action");
					$("#kaoqinForm")[0].submit();
					}
				}
			});
			
			
		});
		function go(pageIndex){
			var oid = document.getElementById("orgId").value;
			self.location.href="<%=basePath%>account_list.action?pageIndex="+pageIndex+"&orgId="+oid;
		}
		
		
	</script>

  </head>
  
  <body>
   
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="doChuqin" type="button" value="正常出勤" <ct:btnEnable id="1.7.1.1" /> />&nbsp;&nbsp;
    			<input id="doQingjia" type="button" value="请假" <ct:btnEnable id="1.7.1.2" /> />&nbsp;&nbsp;
    			<input id="doChidao" type="button" value="迟到" <ct:btnEnable id="1.7.1.3" /> />&nbsp;&nbsp;
    			<input id="doQueqin" type="button" value="缺勤" <ct:btnEnable id="1.7.1.4" /> />&nbsp;&nbsp;
    			<input id="doJiaban" type="button" value="加班" <ct:btnEnable id="1.7.1.5" /> />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <form name="kaoqinForm" id="kaoqinForm" action="" method="kaoqin">
  	 <input type="hidden" id="orgId" name="orgId" value="<s:property value="#request.orgId"/>"/>
 	 <input type="hidden" id="kqTime" name="kqTime" />
 	 <input type="hidden" id="pageIndex" name="pageIndex" value="<s:property value="pageIndex"/>"/>
 	 <input type="hidden" id="cq" name="cq" value="<s:property value="#request.cq"/>"/>
 	 <input type="hidden" id="qj" name="qj" value="<s:property value="#request.qj"/>"/>
 	 <input type="hidden" id="cd" name="cd" value="<s:property value="#request.cd"/>"/>
 	 <input type="hidden" id="qq" name="qq" value="<s:property value="#request.qq"/>"/>
 	 <input type="hidden" id="jb" name="jb" value="<s:property value="#request.jb"/>"/>
 	 
    <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
    		<td width="30px"><input type="checkbox"/></td>
    		<td>员工名称</td>
    		<td>所在部门</td>
    		<td>岗位名称</td>
    		
    	</tr>
    
    	<s:set name="rowCount" value="0"></s:set>
    	<s:iterator var="kaoqin" value="#request.kaoqin_list_page.list" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">

			</s:else>
				<td align="center"><input class="ckb" name="kaoqinId" type="checkbox" onclick="" value="<s:property value="ID"/>"/></td>
								
				<td><s:property value="EMPLOYEENAME"/></td>
				<td><s:property value="ORGID" /></td>
				<td><s:property value="POSTNAME" /></td>
				
				
    		</tr>
    		<s:set name="rowCount" value="#i.count"></s:set>
    	</s:iterator>
    	<s:iterator begin="#rowCount+1" end="#request.kaoqin_list_page.pageSize" status="i">
    		<s:if test="(#i.count+#rowCount) % 2 != 0">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>
				<td align="center">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
		
    		</tr>
    	</s:iterator>
    </table>
    </form>
    <s:set name="pageCount" value="(#request.kaoqin_list_page.rowCount+#request.kaoqin_list_page.pageSize-1)/#request.kaoqin_list_page.pageSize"></s:set>
    <table border="0" width="100%" height="24px" style="background: rgb(192, 212, 240); font-size:12px;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td align="right">
    			共有&nbsp;<s:property value="#request.kaoqin_list_page.rowCount" />&nbsp;条信息&nbsp;&nbsp;
    			<s:property value="#request.kaoqin_list_page.pageSize" />条/页&nbsp;&nbsp;
    			页次：<span style="color:red;"><s:property value="#request.kaoqin_list_page.pageIndex" /></span>/<s:property value="#pageCount" />页&nbsp;&nbsp;
    			<s:if test="#request.kaoqin_list_page.pageIndex == 1">首页</s:if>
    			<s:else><a class="page_l" href="javascript:go(1)">[首页]</a></s:else>
    			<s:if test="#request.kaoqin_list_page.pageIndex == 1">上一页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#request.kaoqin_list_page.pageIndex-1" />)">[上一页]</a></s:else>
    			<s:if test="#request.kaoqin_list_page.pageIndex >= #pageCount">下一页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#request.kaoqin_list_page.pageIndex+1" />)">[下一页]</a></s:else>
    			<s:if test="#request.kaoqin_list_page.pageIndex >= #pageCount">尾页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#pageCount" />)">[尾页]</a></s:else>
    		</td>
    	</tr>
    </table>
  </body>
</html>
