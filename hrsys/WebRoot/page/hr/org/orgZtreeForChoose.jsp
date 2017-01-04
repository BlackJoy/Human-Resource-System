<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="ct" uri="/custom-tags"%>
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
<title>ZTREE DEMO - checkbox</TITLE>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="style/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="js/jquery.ztree.excheck-3.5.js"></script>

<SCRIPT type="text/javascript">
	var ztree;
	var setting = {
		check : {
			enable : true,
			chkStyle : "radio",
			radioType : "all"
		},
		data : {
			simpleData : {
				enable : true,
				pIdKey : "orgParentId"//默认为pId,显示的名字默认为那么，已经在后台替换为name，也可以增加key，详键api
			}
		}
	};

	var code;

	function setCheck() {
		var zTree = $.fn.zTree.getZTreeObj("tree1"), py = $("#py").attr(
				"checked") ? "p" : "", sy = $("#sy").attr("checked") ? "s" : "", pn = $(
				"#pn").attr("checked") ? "p" : "", sn = $("#sn")
				.attr("checked") ? "s" : "", type = {
			"Y" : py + sy,
			"N" : pn + sn
		};
		zTree.setting.check.chkboxType = type;
		showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "'
				+ type.N + '" };');
	}
	function showCode(str) {
		if (!code)
			code = $("#code");
		code.empty();
		code.append("<li>" + str + "</li>");
	}

	var zNodes =
<%=request.getAttribute("orgZTree").toString()%>
	;

	$(document).ready(function() {
		ztree = $.fn.zTree.init($("#tree1"), setting, zNodes);
		setCheck();
		$("#py").bind("change", setCheck);
		$("#sy").bind("change", setCheck);
		$("#pn").bind("change", setCheck);
		$("#sn").bind("change", setCheck);
		ztree.expandAll(true);

		//返回
		$("#doClose").click(function() {
			alert("关闭");
			window.returnValue="";
			window.close();
		});
		

	});

	function confirmOrg(){
		//alert("确定");
		var org = ztree.getCheckedNodes(true);
		var tt = org[0].orgShortName+","+org[0].id;
		//alert(tt);
		
//-------------------------兼容代码start--------------------------------------------
		if (navigator.appName.indexOf("Micro") != -1) {//IE浏览器
			window.returnValue =tt ;
			window.close();
		}else{//火狐浏览器
				//alert("火狐");
			if (tt.length > 0) {
				//alert(window.parent);
				var v=tt.split(",");
				
				window.parent.opener.document.getElementById("orgName").value=v[0];
				window.parent.opener.document.getElementById("orgId").value=v[1];
				window.parent.opener.document.getElementById("diao_orgId").value=v[1];
				
			} 
			window.parent.close();
		}
	}
//-------------------------兼容代码start--------------------------------------------
</script>
</head>

<body>
	<table border="0" width="100%"
		style="background: rgb(192, 212, 240) url(images/bg_title01.gif) repeat-x;"
		cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="10px"></td>
						<td height="26px" width="7px"><img src="images/yq_l.gif" /></td>
						<td height="26px"
							style="background: url(images/yq_bg.gif) repeat-x; font-size: 12px; font-weight: bold; color: #333;">&nbsp;组织信息管理&nbsp;</td>
						<td height="26px" width="7px"><img src="images/yq_r.gif" /></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<table border="0" width="100%" style="background: rgb(192, 212, 240);"
		cellpadding="0" cellspacing="0">
		<tr>
			<td height="30px" valign="middle" style="padding-left: 30px;"><input
				id="doConfirm" type="button" value="确定"
				<ct:btnEnable id="1.1.3.1" /> onclick="confirmOrg()" />&nbsp;&nbsp;
				<input id="doClose" type="button" value="关闭" />&nbsp;&nbsp;</td>
		</tr>
	</table>
	<form id="chooseOrgForm" action="" method="post">
		
		<div class="showztree1" style="float: left;">
			<ul class="ztree" id="tree1"></ul>
		</div>
		
	</form>
</body>
</html>