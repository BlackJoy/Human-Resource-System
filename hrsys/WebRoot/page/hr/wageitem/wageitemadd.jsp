<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

<title>My JSP 'accountadd.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="style/common.css">
<script type="text/javascript" language="javascript"
	src="js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#btn_add").click(function(){
				//alert("岗位增加");
				var msg = "";
				if($("#wageName").val().length == 0){
					msg += "帐套名字不能为空\n";
				}
				if($("#wageNo").val().length == 0){
					msg += "帐套编码不能为空\n";
				}
				
				
				
				if (msg.length != 0) {
					alert(msg);
				} else {
					//alert("将要插入一个岗位");
					$("#wageItemAddForm")[0].submit();
				}
			});
			$("#btn_return").click(function(){
				var pi = $("#pageIndex").val();
				self.location.href="<%=basePath%>wageItem_list.action?pageIndex=" + pi;
				                              }
			);
			//------------------选择部门start-------------------------
		<%-- 	$(".choose_approver").live("click",function(){
				var rtnVal = openWin();
				if(rtnVal.length > 0){
					var e = rtnVal.split(",");
					$(this).prevAll(".approverName").val(e[1]);
					$(this).prevAll(".approverId").val(e[0]);
				}
			});
			
			
			function openWin(){
				var rtnValue = window.showModalDialog("<%=basePath%>page/sysmg/approve/chooseEmp.jsp","","location:no;dialogWidth:600px;dialogHeight:400px");
				return rtnValue;
			} --%>
		//------------------选择部门end-------------------------
		
			
			
		});
		
		
		
		
		
		
</script>

</head>

<body>
	<s:if test="#request.accountMsg != null">
		<script type="text/javascript" language="javascript">
			alert("<s:property value='#request.accountMsg' escape='false'/>");
		</script>
	</s:if>
	<form id="wageItemAddForm" action="wageItem_add.action" method="post">
		<input type="hidden" id="orgId" name="orgId"
			value="<s:property value="orgId"/>" /> <input type="hidden"
			id="empId" name="empId" value="<s:property value="empId"/>" /> <input
			type="hidden" id="pageIndex" name="pageIndex"
			value="<s:property value="pageIndex"/>" />
		<table border="0" width="100%" style="background: rgb(192, 212, 240);"
			cellpadding="0" cellspacing="0">
			<tr>
				<td height="30px" valign="middle" style="padding-left: 30px;">
					<input id="btn_add" type="button" " value="保存" />&nbsp;&nbsp; <input
					type="reset" " value="重置" />&nbsp;&nbsp; <input id="btn_return"
					type="button" value="返回" />&nbsp;&nbsp;
				</td>
			</tr>
		</table>
		<table border="0" width="100%"
			style="background: rgb(240, 240, 240); font-size: 12px;"
			cellpadding="0" cellspacing="2">
			<tr height="30px;">
				<td width="15%" align="right"><span style="color: red;">*</span>工资项编码：</td>
				<td width="30%"><input type="text" name="wageItem.wageNo"
					id="wageNo" /></td>
				<td width="15%" align="right">&nbsp;</td>
				<td width="20%">&nbsp;</td>
				<td width="20%">&nbsp;</td>
			</tr>
			<tr height="30px;">
				<td align="right"><span style="color: red;">*</span>工资项名字：</td>
				<td><input type="text" name="wageItem.wageName" id="wageName" /></td>
				<td align="right"><span style="color: red;">*</span>创建日期：</td>
				<td><input type="text"  readonly name="wageItem.createDat" id="createDat" value="<%=new Date().toLocaleString()%>"/></td>
				<td>&nbsp;</td>
			</tr>
			<tr height="30px;">
				<td width="15%" align="right"><span style="color: red;">*</span>操作(+/-)：</td>
				<td width="30%"><select id="operNam" name="wageItem.operNam" >
				<option value="+">+</option>
				<option value="-">-</option>
				</select></td>
				
				<td width="15%" align="right"><span style="color: red;">*</span>选择属于的帐套：</td>
				<td width="30%"><s:select name="ztNo" list="ztList" listKey="wageTypeId" listValue="wageTypeName" theme="simple" ></s:select></td>
			</tr>
			<tr height="30px;">
				<td align="right"><span style="color: red;">*</span>是否被激活：</td>
				<td><input type="radio" name="wageItem.iactive" value="0" />否&nbsp;&nbsp;
					<input type="radio" name="wageItem.iactive" checked="checked" value="1" />是
				</td>
				<td align="right">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
<!-- 		<input type="hidden" name="deptNo" id="deptNo">
		请选择所在的部门：
		<div class='choose_approver'
			style='width: 18px; height: 18px; float: right; cursor: pointer;'>
			<img src='images/choose.jpg' />
		</div> -->
	</form>
</body>
</html>
