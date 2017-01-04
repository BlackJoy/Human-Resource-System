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
	var ztree2;
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
		ztree2 = $.fn.zTree.init($("#tree2"), setting, zNodes);
		setCheck();
		$("#py").bind("change", setCheck);
		$("#sy").bind("change", setCheck);
		$("#pn").bind("change", setCheck);
		$("#sn").bind("change", setCheck);
		ztree.expandAll(true);
		ztree2.expandAll(true);

		//返回
		$("#doReturn").click(function() {
			//alert("返回");
			self.location.href = "page/common/init-content.jsp";
		});

	});

	//-->
	function test() {
		var treeObj = $.fn.zTree.getZTreeObj("tree1");
		var nodes = treeObj.getCheckedNodes(true);
		alert(typeof (nodes));
		alert(nodes[0]);
		alert(typeof nodes[0]);
		alert(nodes[0].id);
	}

	//划转
	function saveTransfer() {
		alert("将要进行合并操作");
		var org1 = ztree.getCheckedNodes(true);
		var org2 = ztree2.getCheckedNodes(true);
		if (org1[0] == null) {
			alert("请选择将要被划转的组织");
		} else if (org2[0] == null) {
			alert("请选择将要划转到的组织");
		} else {//两个组织都选择了
			var pid1 = parseInt(org1[0].orgParentId);//被划转组织的父id（整数）
			var pid2 = parseInt(org2[0].orgParentId);//划转到组织的父id（整数）
			var id1 = parseInt(org1[0].id);//被划转组织的id（整数）
			var id2 = parseInt(org2[0].id);//划转到组织的id（整数）

			if (isNaN(pid1)) {//由于顶层组织咩有父id，因此为NaN，赋值为0便于比较
				pid1 = 0;
			}
			if (isNaN(pid2)) {
				pid2 = 0;
			}

			if (id1 == id2) {//不能划转到本身

				alert("不能把组织划转为本身");
			} else if (pid2 > pid1) {
				/* alert("id1" + id1);
				alert("id2" + id2);
				alert("pid1" + pid1);
				alert("pid2" + pid2); */
				alert("不能把组织划转为下级组织");
			} else {
				alert("正常划转范围");
				/* aa=document.getElementById("orgTransferForm");
				aa.setAttribute("action", "org_transfer.action?id1="+id1+"&id2="+id2);
				aa.submit(); */
				$("#orgTransferForm").attr("action",
						"org_transfer.action?id1=" + id1 + "&id2=" + id2);
				$("#orgTransferForm")[0].submit();
			}

		}

	}
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
				id="doTranfer" type="button" value="保存"
				<ct:btnEnable id="1.1.3.1" /> onclick="saveTransfer()" />&nbsp;&nbsp;
				<input id="doReturn" type="button" value="返回" />&nbsp;&nbsp;</td>
		</tr>
	</table>
	<form id="orgTransferForm" action="" method="post">
		<table id="showOrg" border="0" width="100%"
			style="background: #fff; font-size: 12px;" cellpadding="0"
			cellspacing="1">
			<tr height="20px;" valign="middle"
				style="background: rgb(221, 232, 247); text-align: center;">
				<td>请选择将要被划转的组织名称</td>
				<td>请选择将要划转到的组织名称</td>
			</tr>

		</table>
		<div class="showztree1" style="float: left; width: 50%">
			<ul class="ztree" id="tree1"></ul>
		</div>
		<div style="float: left">
			<ul class="ztree" id="tree2"></ul>
		</div>
	</form>
</body>
</html>