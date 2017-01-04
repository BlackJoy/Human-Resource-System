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
				if($(".empName").val().length == 0){
					msg += "请选择一个员工\n";
				}
				
				var inputArr=document.getElementsByTagName("input");
				//alert(inputArr);
				for(var i=0;i<inputArr.length;i++){
					if(inputArr[i].type=='text'){
						if(inputArr[i].value.length==0){
							msg+="请填入相应工资\n";
							break;
						}
					}
				}
				
				if (msg.length != 0) {
					alert(msg);
				} else {
					//alert("将要插入一个岗位");
			
					$("#empWageAddForm")[0].submit();
				}
			});
			$("#btn_return").click(function(){
			
				self.location.href="<%=basePath%>empWage_list.action";
			});
			
			
			//-------------------选择员工start----------------------------
			$(".choose_approver").live("click",function(){
				var rtnVal = openWin();
				if(rtnVal.length > 0){
					var e = rtnVal.split(",");
				   // alert(e);
				    //alert(e[0]);
				    //alert(e[1]);
					$(this).prevAll(".empName").val(e[1]);
					$(this).prevAll(".empId").val(e[0]);
				}
			});
			
			function openWin(){
				var rtnValue = window.showModalDialog("<%=basePath%>page/sysmg/approve/chooseEmp.jsp","","location:no;dialogWidth:600px;dialogHeight:400px");
				return rtnValue;
			}
			
			//-------------------选择员工end----------------------------
			
			
			
			
			
			
			
	});
	//--------------------------帐套工资项start-------------------------------------	
		
	var arr1=<%=request.getAttribute("str1").toString()%>		
	var arr2=<%=request.getAttribute("str2").toString()%>		
		
	//alert(arr1[0].wageTypeName);	
	//alert(arr2[0]["0"][0].wageName);	
	
	var ztArr = new Array();//存放所有的帐套名字
	var wageItemArr = new Array();//存放工资项，并和帐套对应。（采用二维数组）
	var ztIdArr = new Array();//存放帐套id的数组
	for(var i=0;i<arr1.length;i++){
		ztArr[i]=arr1[i].wageTypeName;
		ztIdArr[i]=arr1[i].wageTypeNo;
		wageItemArr[i]=new Array();//比如：ztArt[0]为软件工程师帐套，则软件工程师所对应的所有工资项存放在wageItemArr[0][0-*]数组中
		for(var j=0;j<arr2[0][i].length;j++){
			wageItemArr[i][j]=arr2[0][i][j].wageName;
		}
	}
	//---------------动态创建工资项start---------------------------
	var currentRow = 0;
	function createWageItem(){
		var selectId = zt.value;//选中的帐套
		//alert(selectId);

		if (currentRow != 0) {
			for ( var n = 0; n < currentRow; n++) {
				currentRow--;
				//alert(currentRow);
				myTable.deleteRow(n);//重新创建
				
			}
		}
		if (currentRow != 0) {//不知道为什么一次删除不完整，得再来一次
			for ( var t = 0; t < currentRow; t++) {
				currentRow--;
				//alert(currentRow);
				myTable.deleteRow(t);//重新创建
				
			}
		}
		var rowNum = 2;//只有两行（工资项名字，值）
		var colNum =parseInt(wageItemArr[selectId].length);//选中帐套对应的工资项个数
		currentRow = rowNum;
		//alert(colNum);
		for ( var i = 0; i < rowNum; i++) {
			var tr = myTable.insertRow();//在mytable中添加新行<tr></tr>
			for ( var j = 0; j < colNum; j++) {
				var td = tr.insertCell();//<td></td>
				if(i==0){//第一行是工资项名称
				td.innerHTML ="<span style='color: red;'>*</span>"+wageItemArr[selectId][j]+"<input type='text' name='"+wageItemArr[selectId][j]+"'/>";
				//alert(td.innerHTML);
				}else{
					//alert(i);
					//td.innerHTML="<input type='text' name='"+wageItemArr[selectId][j]+"'/>";
				}
			}
		} 
	}
	//---------------动态创建工资项end---------------------------
	
	
	
	
</script>

</head>

<body onload="createWageItem()">
	<s:if test="#request.accountMsg != null">
		<script type="text/javascript" language="javascript">
			alert("<s:property value='#request.accountMsg' escape='false'/>");
		</script>
	</s:if>
	
	<form id="empWageAddForm" action="empWage_add.action" method="post">
	<input type="hidden" id="chooseWageTime" name="chooseWageTime" value="<s:property value='chooseWageTime'/>"/>
	<input type="hidden" id="isCwiExist" name="isCwiExist"
			value="<s:property value="#session.isCwiExist"/>" />
		<input type="hidden" id="orgId" name="orgId"
			value="<s:property value="orgId"/>" /> <input
			type="hidden" id="pageIndex" name="pageIndex"
			value="<s:property value="pageIndex"/>" />
		<table border="0" width="100%" style="background: rgb(192, 212, 240);"
			cellpadding="0" cellspacing="0">
			<tr>
				<td height="30px" valign="middle" style="padding-left: 30px;">
					<input id="btn_add" type="button" " value="保存" />&nbsp;&nbsp; <input
					type="reset" value="重置" />&nbsp;&nbsp; <input id="btn_return"
					type="button" value="返回" />&nbsp;&nbsp;
				</td>
			</tr>
		</table>
		
		
		
		<!-- *************选择员工start*************************** -->
		<span style="color:red;">*</span>选择员工：<input class='empName' name='empName' type='text' style='width: 80px;float: left' readonly='readonly' /><input class='empId' name='empId' type='hidden'/>
		
		<div class='choose_approver' style='width:18px; height:18px; float:left; cursor: pointer;'><img src='images/choose.jpg' /></div>
		
	    <!-- *************选择员工end*************************** -->
			
		
		
		
		
		
		<table border="0" width="100%"
			style="background: rgb(240, 240, 240); font-size: 12px;"
			cellpadding="0" cellspacing="2">		
			<!-- ******************帐套工资项start************************* -->
			<tr height="30px;">
				<td width="15%"><span style="color: red;">*</span>帐套：</td>
				<td width="30%"><select id="zt" name="zt" onchange="createWageItem()">
                <script>
                var zt=document.getElementById("zt");
						for ( var i = 0; i < ztArr.length; i++) {
							var ztValue = ztArr[i];
							var ztIdValue=ztIdArr[i];
							//alert(ztValue);
							
							var opt = new Option(ztValue, i);
							zt.options.add(opt);
							//zt.options[i]=opt;
						}
					</script>
			</select>
               </td>
				
			</tr>
			
		</table>
			<!-- ******************帐套工资项end************************* -->
		
		
		
		
		<!-- ******************动态表格start**************************** -->

		<table border="0" width="100%"
			style="background: rgb(240, 240, 240); font-size: 12px;"
			cellpadding="0" cellspacing="2" id="myTable">

	    </table>
		<!-- ******************动态表格end**************************** -->
		
		
		
		
		
	</form>
</body>
</html>
