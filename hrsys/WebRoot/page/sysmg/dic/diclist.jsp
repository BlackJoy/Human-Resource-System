<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="ct" uri="/custom-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>字典类型列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<style type="text/css">
	a.page_l,a#dicDatalink{ text-decoration: none; color: blue;}
	a.page_l:hover,a#dicDatalink:hover{ text-decoration: underline; color: blue;}
	a.page_l:visited,a#dicDatalink:visited{ text-decoration: none; color: blue;}
	</style>
	<script type="text/javascript" language="javascript">
	function go(pageIndex){
		window.location.href="<%=basePath%>dic_list.action?pageIndex="+pageIndex;
	}
	function doAdd(){
		self.location.href="<%=basePath%>page/sysmg/dic/dicadd.jsp";
	}
	function doUpdate(){
		var roleIds = document.getElementsByName("dtid");
		var isChecked = false;
		var count = 0;
		var ri;
		for ( var i = 0; i < roleIds.length; i++) {
			if (roleIds[i].checked==true) {
				isChecked = true;
				count++;
				ri = roleIds[i].value;
			}
		}
		if (isChecked) {
			if (count == 1) {
				self.location.href="<%=basePath%>dic_toUpdateDicType.action?dicTypeId="+ri;
			} else {
				alert("不允许对多条记录进行修改");
			}
		} else {
			alert("请勾选要修改的记录");
		}
	}
	function doDelete(){
		var roleIds = document.getElementsByName("dtid");
		var isChecked = false;
		var count = 0;
		var ri;
		for ( var i = 0; i < roleIds.length; i++) {
			if (roleIds[i].checked==true) {
				isChecked = true;
				count++;
				ri = roleIds[i].value;
			}
		}
		if (isChecked) {
			if (count == 1) {
				if (confirm("确定要删除选择的记录吗？")) {
					self.location.href="<%=basePath%>dic_deleteDicType.action?dicTypeId="+ri;
				}
			} else {
				alert("不允许对多条记录进行删除");
			}
		} else {
			alert("请勾选要删除的记录");
		}
	}
	</script>
  </head>
  
  <body>
  	<s:if test="#request.dicMsg != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.dicMsg' escape='false'/>");
  		</script>
  	</s:if>
    <table border="0" width="100%" style="background: rgb(192, 212, 240) url(images/bg_title01.gif) repeat-x;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td>
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
		    		<td width="10px"></td>
		    		<td height="26px" width="7px"><img src="images/yq_l.gif"/></td>
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;字典设置&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
    <table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="doAdd" type="button" value="增加" onclick="doAdd()" <ct:btnEnable id="2.3.2.1" /> />&nbsp;&nbsp;
    			<input id="doUpdate" type="button" value="修改" onclick="doUpdate()" <ct:btnEnable id="2.3.2.2" /> />&nbsp;&nbsp;
    			<input id="doDelete" type="button" value="删除" onclick="doDelete()" <ct:btnEnable id="2.3.2.3" /> />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
    		<td width="30px"><input type="checkbox"/></td>
    		<td width="100px">字典编码</td>
    		<td width="200px">字典名</td>
    		<td width="400px">备注</td>
    		<td>操作字典数据</td>
    	</tr>
    	<s:set name="rowCount" value="0"></s:set>
    	<s:iterator var="dicType" value="#request.dicType_list_page.list" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>
				<td align="center"><input class="dtid" name="dtid" type="checkbox" value="<s:property value="id"/>"/></td>
				<td align="center"><s:property value="dicTypeCode"/></td>
				<td align="center"><s:property value="dicTypeName"/></td>		
				<td align="center"><s:property value="dicTypeComment"/></td>
				<td align="center"><a id="dicDatalink" href="dic_toDataList.action?dicTypeId=<s:property value="id"/>">查看字典数据</a></td>		
    		</tr>
    		<s:set name="rowCount" value="#i.count"></s:set>
    	</s:iterator>
    	<s:iterator begin="#rowCount+1" end="#request.dicType_list_page.pageSize" status="i">
    		<s:if test="(#i.count+#rowCount) % 2 != 0">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>
				<td align="center"></td>
				<td width="300px"></td>
				<td></td>
				<td></td>
				<td></td>			
    		</tr>
    	</s:iterator>
    </table>
    <s:set name="pageCount" value="(#request.dicType_list_page.rowCount+#request.dicType_list_page.pageSize-1)/#request.dicType_list_page.pageSize"></s:set>
    <table border="0" width="100%" height="24px" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td align="right">
    			共有&nbsp;<s:property value="#request.dicType_list_page.rowCount" />&nbsp;条信息&nbsp;&nbsp;
    			<s:property value="#request.dicType_list_page.pageSize" />条/页&nbsp;&nbsp;
    			页次：<span style="color:red;"><s:property value="#request.dicType_list_page.pageIndex" /></span>/<s:property value="#pageCount" />页&nbsp;&nbsp;
    			<s:if test="#request.dicType_list_page.pageIndex == 1">首页</s:if>
    			<s:else><a class="page_l" href="javascript:go(1)">[首页]</a></s:else>
    			<s:if test="#request.dicType_list_page.pageIndex == 1">上一页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#request.dicType_list_page.pageIndex-1" />)">[上一页]</a></s:else>
    			<s:if test="#request.dicType_list_page.pageIndex >= #pageCount">下一页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#request.dicType_list_page.pageIndex+1" />)">[下一页]</a></s:else>
    			<s:if test="#request.dicType_list_page.pageIndex >= #pageCount">尾页</s:if>
    			<s:else><a class="page_l" href="javascript:go(<s:property value="#pageCount" />)">[尾页]</a></s:else>
    		</td>
    	</tr>
    </table>
  </body>
</html>
