<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>培训效果评价管理页</title>
<link rel="stylesheet" type="text/css" href="style/common.css">
<style type="text/css">
a.page_l,a.info {
	text-decoration: none;
	color: blue;
}

a.page_l:hover,a.info:hover {
	text-decoration: underline;
	color: blue;
}

a.page_l:visited,a.info:visited {
	text-decoration: none;
	color: blue;
}
</style>
<script type="text/javascript" language="javascript"
	src="js/jquery/jquery-1.7.1.min.js"></script>
	

<script type="text/javascript" language="javascript">
		$(document).ready(function(){
		  $("#btnInputScore").click(
		   function(){
		        
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个培训申请");
				} else if (count > 1) {
					alert("只能对单个培训申请进行修改");
				} else {
			        $("#trainPlainForm").attr("action", "effectAction_toInputEmpScore?pageIndex=1");
					$("#trainPlainForm")[0].submit();
				}		   
		     }
		   );
		   $("#btnEndTrain").click(
		   function(){
		      
				var count = $(".ckb:checked").size();
				if (count <= 0) {
					alert("请勾选一个培训申请");
				} else if (count > 1) {
					alert("只能对单个培训申请进行修改");
				} else {
			        $("#trainPlainForm").attr("action", "effectAction_endTrainPlain");
					$("#trainPlainForm")[0].submit();
				}	   
		     }
		   );
		});
		function go(pageIndex){
			var oid = document.getElementById("orgId").value;
			self.location.href="<%=basePath%>train_effectlist.action?pageIndex="
				+ pageIndex + "&orgId=" + oid;
	}
</script>

</head>

<body>
	  <s:if test="#request.msg != null">
		<script type="text/javascript" language="javascript">
			alert("<s:property value='#request.msg' escape='false'/>");
		</script>
	</s:if> 
	<table border="0" width="100%" style="background: rgb(192, 212, 240);"
		cellpadding="0" cellspacing="0">
		<tr>
			<td height="30px" valign="middle" style="padding-left: 30px;"> 
				<input id="btnInputScore" type="button" value="录入学员成绩" /> &nbsp;&nbsp;  
				&nbsp;&nbsp;
				 <input id="btnEndTrain" type="button" value="结束项目" <ct:btnEnable id="1.10.3.3" />/> 
				<strong style="color:blue;font-size:medium;">
				</strong>
			</td>
		</tr>
	</table>
	<form name="trainPlainForm" id="trainPlainForm" action="" method="post">
		<input type="hidden" id="orgId" name="orgId"
			value="<s:property value="#request.orgId"/>" /> 
		<input type="hidden"
			id="pageIndex" name="pageIndex"
			value="<s:property value="pageIndex"/>" /> 
		<table border="0" width="100%"
			style="background: #fafafa; font-size: 12px;" cellpadding="0"
			cellspacing="1">
			<tr
				style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
				<td width="30px"><input type="checkbox" /></td>
				<td>培训项目</td>
				<td>主办单位</td>
				<td>开始时间</td>
				<td>结束时间</td>
				<td>培训价格</td>
				<td>培训方式</td>
				<td>操作</td>
			</tr>
			<s:set name="rowCount" value="0"></s:set>
			<s:iterator var="train" value="#request.effect_list_page.list"
				status="i">
				<s:if test="#i.odd">
			<tr style="background: rgb(240, 240, 240); height: 22px; text-align: center;">
				</s:if>
				<s:else>
					<tr
						style="background: rgb(248, 248, 248); height: 22px; text-align: center;">
				</s:else>
				<td align="center">
				<input class="ckb" name="trainId" id="trainId"
					type="checkbox" value="<s:property value="#train.id" />"/></td>
				<td align="center"><a class="info"
					href="<%=basePath%>trainPlain_toDetailTrainPlain.action?trainId=<s:property value="#train.id"/>">
					<s:property value="#train.trainSubject" /></a></td>
				
				<%-- <td><div id="trainObjs" style="width:40px;height:20px;display:block;cursor:pointer"onclick="showDiv()">人员
				  
	                <input type="hidden" id="objs" value=" --%><%-- <s:property value="train.trainObject" /><!-- "/> -->
				</div></td> --%>
				<td><s:property value="#train.orgFullName" /></td>				
				<td><s:date name="#train.trainStart" format="yyyy-MM-dd" /></td>
				<td><s:date name="#train.trainEnd" format="yyyy-MM-dd" /></td>
				<td><s:property value="#train.trainPrice" /></td>
				<td><s:property value="#train.trainManner" /></td>	
				<td>
				    <s:iterator var="auth" value="#train.authSet">
								<!-- 当前员工部门为允许评论的部门 -->
							   <s:if test="#auth.orgId==#request.empOrgId">
							       <!-- 查看此条评论是否已生效 -->
							       <s:if test="#auth.status==\"0\"">
							        <a href="<%=basePath%>effectAction_toEvaluate?trainId=<s:property value="#train.id"/>&whichIdea=<s:property value="#auth.whichIdea"/>">
							                 <!-- 分析是哪种类型的评论 -->
								              <s:if test="#auth.whichIdea==\"1\"">
								                         
								                          <input
				                                              id="liLun" type="button" value="理论评价" <ct:btnEnable id="1.10.3.2" /> />
								                         
								             </s:if>
									        <s:if test="#auth.whichIdea==\"2\"">
									               <input id="shiZuo" type="button" value="实作评价" <ct:btnEnable id="1.10.3.2" /> />							        
									                          <!--   <font style="color:#FF9900;font:bold;">实作评价</font> -->
												</s:if>	 
									          <s:if test="#auth.whichIdea==\"3\"">
									               <input id="xianGangWei" type="button" value="现岗位评价" <ct:btnEnable id="1.10.3.2" />/>
									                           <!--  <font style="color:#FF9900;font:bold;">现岗位评价</font> -->
								               </s:if>	
									          <s:if test="#auth.whichIdea==\"4\"">
									             <input id="renLi" type="button" value="人力资源评价" <ct:btnEnable id="1.10.3.2" />/>
									                           <!--  <font style="color:#FF9900;font:bold;">人力资源评价</font> -->
								               </s:if>	
									          <s:if test="#auth.whichIdea==\"5\"">
									            <input id="zhuGuan" type="button" value="主管领导评价" <ct:btnEnable id="1.10.3.2" />/>
								                            <!--  <font style="color:#FF9900;font:bold;">主管领导评价</font> -->
								               </s:if>
								          </a>	             
								   </s:if>
								   <!-- 以评论 -->
							       <s:if test="#auth.status==\"1\"">
										 <!-- 分析是哪种类型的评论 -->
										 <a href="<%=basePath%>effectAction_toViewEval?trainId=<s:property value="#train.id"/>">
							                 
								              <s:if test="#auth.whichIdea==\"1\"">
								                       <input id="doAdd" type="button" value="查看评价" <ct:btnEnable id="1.10.3.2" />/>
								                           <!--  <font style="color:#FF9900;font:bold;">查看评价</font> --></s:if>
									          <s:if test="#auth.whichIdea==\"2\"">
								                       <input id="doAdd" type="button" value="查看评价" <ct:btnEnable id="1.10.3.2" />/>
									                          <!--   <font style="color:#FF9900;font:bold;">查看评价</font> -->
												</s:if>	
									          <s:if test="#auth.whichIdea==\"3\"">									          
								                       <input id="doAdd" type="button" value="查看评价" <ct:btnEnable id="1.10.3.2" />/>
									                        <!--     <font style="color:#FF9900;font:bold;">查看评价</font> -->
								               </s:if>	
									          <s:if test="#auth.whichIdea==\"4\"">									          
								                       <input id="doAdd" type="button" value="查看评价" <ct:btnEnable id="1.10.3.2" />/>
									                           <!--  <font style="color:#FF9900;font:bold;">查看评价</font> -->
								               </s:if>	
									          <s:if test="#auth.whichIdea==\"5\"">									            
								                       <input id="doAdd" type="button" value="查看评价" <ct:btnEnable id="1.10.3.2" />/>
								                          <!--    <font style="color:#FF9900;font:bold;">查看评价</font> -->
								               </s:if>	
								               </a>
								  </s:if>
							   </s:if>		 						   		 
					</s:iterator> 			  
						</td>					
			</tr>
				<s:set name="rowCount" value="#i.count"></s:set>
			</s:iterator>
			<s:iterator begin="#rowCount+1"
				end="#request.effect_list_page.pageSize" status="i">
				<s:if test="(#i.count+#rowCount) % 2 != 0">
					<tr style="background: rgb(240, 240, 240); height: 22px;">
				</s:if>
				<s:else>
					<tr style="background: rgb(248, 248, 248); height: 22px;">
				</s:else>
				<td align="center">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				</tr>
			</s:iterator>
		</table>
	</form>
	<s:set name="pageCount"
		value="(#request.effect_list_page.rowCount+#request.effect_list_page.pageSize-1)/#request.effect_list_page.pageSize"></s:set>
	<table border="0" width="100%" height="24px"
		style="background: rgb(192, 212, 240); font-size: 12px;"
		cellpadding="0" cellspacing="0">
		<tr>
			<td align="right">共有&nbsp;<s:property
					value="#request.effect_list_page.rowCount" />&nbsp;条信息&nbsp;&nbsp;
				<s:property value="#request.effect_list_page.pageSize" />条/页&nbsp;&nbsp;
				页次：<span style="color: red;"><s:property
						value="#request.effect_list_page.pageIndex" /></span>/<s:property
					value="#pageCount" />页&nbsp;&nbsp; <s:if
					test="#request.effect_list_page.pageIndex == 1">首页</s:if> <s:else>
					<a class="page_l" href="javascript:go(1)">[首页]</a>
				</s:else> <s:if test="#request.effect_list_page.pageIndex == 1">上一页</s:if> <s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#request.effect_list_page.pageIndex-1" />)">[上一页]</a>
				</s:else> <s:if test="#request.effect_list_page.pageIndex >= #pageCount">下一页</s:if>
				<s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#request.effect_list_page.pageIndex+1" />)">[下一页]</a>
				</s:else> <s:if test="#request.effect_list_page.pageIndex >= #pageCount">尾页</s:if>
				<s:else>
					<a class="page_l"
						href="javascript:go(<s:property value="#pageCount" />)">[尾页]</a>
				</s:else>
			</td>
		</tr>
	</table>
	<div id="popDiv" class="mydiv" style="display: none;">

	    <div class="SContent-box">
	        <div class="Close_btn">
	             <div onclick="closeDiv()"  style="display:block; cursor:pointer">关闭</div>
	        </div>	        
	    </div>
   </div>
    <div id="bg" class="bg" style="display: none;">
</div>
</body>
</html>
