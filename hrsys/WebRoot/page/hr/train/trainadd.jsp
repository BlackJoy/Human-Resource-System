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
    
    <title>培训项目申请</title>
	<link rel="stylesheet" type="text/css" href="style/date_input.css">	
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="js/train/jquery.date_input.js"></script>
	<script type="text/javascript" language="javascript" src="js/train/jquery.date_input.zh_CN.js"></script>
	<script type="text/javascript">$($.date_input.initialize);</script>
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#btn_add").click(function(){
			    
				var trainType = $("#trainType").val();
				if (trainType.length == 0) {
					alert("培训类别不能为空");
					return;
				}
				var trainSubject = $("#trainSubject").val();
				if (trainSubject.length == 0) {
					alert("培训项目不能为空");
					return;
				}
				var orgFullName = $("#orgFullName").val();
				if(orgFullName.length == 0){
					alert("主办单位名称不能为空");
					return;
				}
				var trainStart = $("#trainStart").val();
				if(trainStart.length == 0){
					alert("开始时间不能为空");
					return;
				}
				var trainEnd = $("#trainEnd").val();
				if(trainEnd.length == 0){
					alert("结束时间不能为空");
					return;
				}
				var trainPrice = $("#trainPrice").val();
				if(trainPrice.length == 0){
					alert("培训价格不能为空");
					return;
				}
				var trainManner = $("#trainManner").val();
				if(trainManner.length == 0){
					alert("培训方式不能为空");
					return;
				}
				
				 var trainDetail = $('.nicEdit-main').html();
				 
				  $("#trainDetail").val(trainDetail);
				if(trainDetail.length == 0){
					alert("培训内容不能为空");
					return;
				} 
			
				$("#trainAddForm")[0].submit();
			});
			$("#btn_return").click(function(){
				self.location.href="<%=basePath%>train_trainList.action?orgId="+
				$("#orgId").val();
			});
			$("#orgFullName").click(function(){
					var rtnValue = window.showModalDialog("<%=basePath%>page/hr/train/chooseTrainOrg.jsp","","location:no;dialogWidth:420px;dialogHeight:285px");
				//	alert(rtnValue.split("&")[0]);
					 
					
					$("#orgFullName").val(rtnValue.split("&")[1]);
					
					$("#orgCode").val(rtnValue.split("&")[0]);
					$("#train_orgId").val(rtnValue.split("&")[2]);
	               				
			});
		});
	</script>
	
	
	<script type="text/javascript" language="javascript" src="js/train/nicEdit.js"></script>
	  <script type="text/javascript">	 
            bkLib.onDomLoaded(function() {
               new nicEditor({ fullPanel: true,iconsPath : '<%=basePath%>js/train/nicEditorIcons.gif' }).panelInstance('trainDetail');
               
            });
        </script>
  </head>
  
  <body>
 
  	<s:if test="#request.employMsg != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.employMsg' escape='false'/>");
  		</script>
  	</s:if>
  	<table border="0" width="100%" style="background: rgb(192, 212, 240) url(images/bg_title01.gif) repeat-x;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td>
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
		    		<td width="10px"></td>
		    		<td height="26px" width="7px"><img src="images/yq_l.gif"/></td>
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;培训信息管理&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
  	<form id="trainAddForm" action="train_doAddTrain.action" method="post">
  	<input type="hidden" name="train.orgCode" id="orgCode" value=""/> 
  	<input type="hidden" name="train.orgId" id="train_orgId" value=""/> 
  	<input type="hidden" id="orgId" name="orgId" value="<s:property value="orgId"/>"/>
  	<input type="hidden" id="employ_orgId" name="employ.orgid" value="<s:property value="#request.orgId"/>"/>
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
    	
    	
    	<tr>
    		<td width="15%" align="right"><span style="color:red;">*</span>培训类别：</td>
    		<td width="25%"><input type="text" name="train.trainType" id="trainType" /></td>
    		<td width="15%" align="right"><span style="color:red;">*</span>培训项目：</td>
    		<td>
    			<input type="text" name="train.trainSubject" id="trainSubject"/>
    		</td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>主办单位名称：</td>
    		<td><input type="text" name="train.orgFullName" id="orgFullName"/></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>开始时间：</td>
    		<td><input type="text" name="train.trainStart" id="trainStart"  class="date_input" /></td>
    			<td align="right"><span style="color:red;" >*</span>结束时间：</td>
    		<td><input type="text" name="train.trainEnd" id="trainEnd"  class="date_input"/></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>培训价格(元/人)：</td>
    		<td><input type="text" name="trainPrice" id="trainPrice"/></td>
    		<td align="right"><span style="color:red;">*</span>培训方式：</td>
    		<td><input type="text" name="train.trainManner" id="trainManner" /></td>
    	</tr>
    	<tr>
    		<td align="right"><span style="color:red;">*</span>申请人：</td>
    		<td><input type="text" name="employeeName" id="employeeName" disabled="disabled" value='<s:property value="#session.curr_employee.employeeName"/>'/></td>
    		<td align="right"><span style="color:red;">*</span>申请人编码：</td>
    		<td><input type="text" name="employeeCode"  id="employeeCode" disabled="disabled" value='<s:property value="#session.curr_employee.employeeCode"/>' /></td>
    	</tr>  
    	</table> 
			
    		 <input type="hidden" name="train.trainStatus" id="trainStatus" value="1" /> <hr/>
    	  
    		<span style="color:red;">*</span><span style="font-size:20px">申请内容：</span>
    		<textarea style="width: 100%;" name="train.trainDetail" 
    		id="trainDetail" placeholder="请输入培训内容">
    		</textarea>   		
    	 <span style="font-size:20px">备注:</span><br/>
			
    		<textarea style="width: 100%;" name="train.trainRemarks" id="trainRemarks"></textarea>
    </form>
  </body>
</html>
