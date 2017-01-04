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
    
    <title>增加审批流程</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript">
		var init_width = 88;
		var append_width = 210;
		var content_width = init_width;
		$(document).ready(function(){
			$("#btn_add").click(function(){
				var an = $("#approveName").val();
				if (an.length == 0) {
					alert("审批项目名不能为空");
					return;
				}
				var at = $("#approveTimeLimit").val();
				if (at.length == 0){
					alert("审批期限不能为空");
					return;
				}
				if (isNaN(at)){
					alert("审批期限只能为数字");
					return;
				}
				if ($(".flow_item").size() <= 1){
					alert("没有审批流程，请先制定审批流程");
					return;
				}
				var ajs = "";
				$.each($(".flow_item"),function(i,item){
					if(i > 0){
						var flowOrder = $(item).attr("flowOrder");
						var an = $(item).find(".flow_approve:first").find(".approverName").val();
						var ai = $(item).find(".flow_approve:first").find(".approverId").val();
						var at = $(item).find(".flow_approve:first").find(".approveType").val();
						if(an.length == 0 || ai.length == 0){
							alert("审批人不能为空");
							return;
						}
						//alert("{'orderId':"+flowOrder+",'approverId':'"+ai+"','approverName':'"+an+"','approveType':"+at+"}");
						ajs += "{'orderId':"+flowOrder+",'approverId':'"+ai+"','approverName':'"+an+"','approveType':"+at+"},";
						$.each($(item).find(".flow_read"),function(j,ritem){
							var rn = $(ritem).find(".approverName").val();
							var ri = $(ritem).find(".approverId").val();
							var rt = $(ritem).find(".approveType").val();
							if(rn.length > 0 && ri.length > 0 && rt.length > 0){
								//alert("{'orderId':"+flowOrder+",'approverId':'"+ri+"','approverName':'"+rn+"','approveType':"+rt+"}");
								ajs += "{'orderId':"+flowOrder+",'approverId':'"+ri+"','approverName':'"+rn+"','approveType':"+rt+"},";
							}
						});
					}
				});
				if(ajs.length > 0){
					ajs = "["+ajs.substr(0,ajs.length-1)+"]";
					//alert(ajs);
					$("#approveFlow").val(ajs);
					$("#approveAddFrm")[0].submit();
				} else {
					alert("没有审批流程，请先制定审批流程");
					return;
				}
			});
			$("#btn_return").click(function(){
				self.location.href="<%=basePath%>approve_list.action";
			});
			$(".flow_add").live("click",function(){
				if($(this).parents(".flow_item").nextAll(".flow_item").length == 0){
					//计算当前宽度
					content_width += append_width;
					//如果当前宽度大于外层宽度，则重新设置外层宽度
					if(content_width > $("#flow_view").width()){
						$("#flow_view").width(content_width);
					}
					var order = parseInt($(this).attr("flowOrder"))+1;
					$("#flow_view").append(createFlowNode(order));
					$("#flow").scrollLeft($("#flow_view").width());
				}
			});
			$(".flow_node_del").live("click",function(){
				//重新计算宽度
				content_width -= ($(this).parents(".flow_item").nextAll(".flow_item").length + 1) * append_width;
				$(this).parents(".flow_item").nextAll(".flow_item").remove();
				$(this).parents(".flow_item").remove();
				//重新设置外层宽度
				$("#flow_view").width(content_width);
			});
			$(".flow_read_add").live("click",function(){
				$(this).parents(".flow_node").append(createFlowRead());
			});
			$(".flow_read_del").live("click",function(){
				$(this).parents(".flow_read").remove();
			});
			$(".choose_approver").live("click",function(){
				var rtnVal = openWin();
				if(rtnVal.length > 0){
					var e = rtnVal.split(",");
					$(this).prevAll(".approverName").val(e[1]);
					$(this).prevAll(".approverId").val(e[0]);
				}
			});
			function createFlowNode(order){
				var node = "";
				node += "<div class='flow_item' style='width: 198px; float: left; display:inline; position: relative; margin-left: 12px;' flowOrder='"+order+"'>";
				node += "<div class='flow_add' style='width: 15px; heigth: 15px; text-align: center; line-height: 15px; position: absolute; right: -15px; top:20px; cursor: pointer;' flowOrder='"+order+"'>+</div>";
				node += "<div class='flow_arrow' style='float: left;'><img src='images/flow_arrow.gif' /></div>";
				node += "<div class='flow_node' style='width: 120px; padding: 5px; border: 1px solid #999; float: left; position: relative;'>";
	    		node += "<div class='flow_node_btn'>";
	    		node += "<div class='flow_read_add' style='width: 15px; heigth: 15px; text-align: center; line-height: 15px; cursor: pointer; float: left;'>+</div>";
	    		node += "<div class='flow_node_del' style='width: 15px; heigth: 15px; text-align: center; line-height: 15px; cursor: pointer; float: right;'>×</div>";
	    		node += "<div style='clear: both;'></div>";
	    		node += "</div>";
	    		node += "<div class='flow_approve' style='width: 108px; padding: 5px; border: 1px solid #999;'>";
	    		node += "<div style='text-align: center;'>审批</div>";
	    		node += "<div style='padding-top: 5px;'>";
	    		node += "<input class='approverName' name='approverName' type='text' style='width: 80px;' readonly='readonly' /><input class='approverId' name='approverId' type='hidden'><input class='approveType' name='approveType' type='hidden' value='1'>";
	    		node += "<div class='choose_approver' style='width:18px; height:18px; float:right; cursor: pointer;'><img src='images/choose.jpg' /></div>";
	    		node += "</div>";
	    		node += "</div>";
	    		return node;
			}
			function createFlowRead(){
				var reader = "";
				reader += "<div class='flow_read' style='width: 108px; padding: 5px; margin-top: 5px; border: 1px solid #999;'>";
		    	reader += "<div>";
		    	reader += "<div class='flow_read_del' style='width: 15px; heigth: 15px; text-align: center; line-height: 15px; cursor: pointer; float: right;'>－</div>";
		    	reader += "<div style='clear: both;'></div>";
		    	reader += "</div>";
		    	reader += "<div style='text-align: center;'>传阅</div>";
		    	reader += "<div style='padding-top: 5px;'>";
		    	reader += "<input class='approverName' name='approverName' type='text' style='width: 80px;' readonly='readonly' /><input class='approverId' name='approverId' type='hidden'><input class='approveType' name='approveType' type='hidden' value='2'>";
		    	reader += "<div class='choose_approver' style='width:18px; height:18px; float:right; cursor: pointer;'><img src='images/choose.jpg' /></div>";
		    	reader += "</div>";
		    	reader += "</div>";
		    	return reader;
			}
			function openWin(){
				var rtnValue = window.showModalDialog("<%=basePath%>page/sysmg/approve/chooseEmp.jsp","","location:no;dialogWidth:600px;dialogHeight:400px");
				return rtnValue;
			}
		});
	</script>
  </head>
  
  <body>
  	<s:if test="#request.approveMsg != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.approveMsg' escape='false'/>");
  		</script>
  	</s:if>
  	<table border="0" width="100%" style="background: rgb(192, 212, 240) url(images/bg_title01.gif) repeat-x;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td>
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
		    		<td width="10px"></td>
		    		<td height="26px" width="7px"><img src="images/yq_l.gif"/></td>
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;审批流程管理&nbsp;</td>
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
    <form name="approveAddFrm" id="approveAddFrm" action="approve_doAdd.action" method="post">
    <input type="hidden" name="approveFlow" id="approveFlow" />
    <table border="0" width="100%" style="background: rgb(240, 240, 240); font-size: 12px;" cellpadding="0" cellspacing="5">
    	<tr>
    		<td width="30%" align="right"><span style="color:red;">*</span>审批项目名：</td>
    		<td><input type="text" name="approve.name" id="approveName" /></td>
    	</tr>
    	<tr>
    		<td width="30%" align="right"><span style="color:red;">*</span>审批期限（天）：</td>
    		<td><input type="text" name="approve.timeLimit" id="approveTimeLimit" /></td>
    	</tr>
    	<tr>
    		<td width="30%" align="right"><span style="color:red;">*</span>审批内容页面：</td>
    		<td>
    			<select name="approve.approveContent">
    				<option value="">-------请选择-------</option>
    				<s:iterator var="ac" value="#application.approveContent">
    					<option value="<s:property value="action" />"><s:property value="name" /></option>
    				</s:iterator>
    			</select>
    		</td>
    	</tr>
    </table>
    </form>
    <div id="flow" style="padding: 20px; font-size: 12px; overflow: auto;">
    	<div id="flow_view" style="width:100%;">
	    	<div id="flow_start" class="flow_item" style="width: 50px; height: 80px; text-align: center; line-height: 80px; border: 1px solid #999; float: left; display:inline; position: relative;" flowOrder="0">
	    		开始<div class="flow_add" style="width: 15px; heigth: 15px; text-align: center; line-height: 15px; position: absolute; right: -15px; top:20px; cursor: pointer;" flowOrder="0">+</div>
	    	</div>
	    	<!-- 
	    	<div class="flow_item" style="width: 198px; float: left; display:inline; position: relative; margin-left: 12px;">
	    		<div class="flow_add" style="width: 15px; heigth: 15px; text-align: center; line-height: 15px; position: absolute; right: -15px; top:20px; cursor: pointer;">+</div>
	    		<div class="flow_arrow" style="float: left;"><img src="images/flow_arrow.gif" /></div>
		    	<div class="flow_node" style="width: 120px; padding: 5px; border: 1px solid #999; float: left; position: relative;">
		    		<div class="flow_node_btn">
		    			<div class="flow_read_add" style="width: 15px; heigth: 15px; text-align: center; line-height: 15px; cursor: pointer; float: left;">+</div>
		    			<div class="flow_node_del" style="width: 15px; heigth: 15px; text-align: center; line-height: 15px; cursor: pointer; float: right;">×</div>
		    			<div style="clear: both;"></div>
		    		</div>
		    		<div class="flow_approve" style="width: 108px; padding: 5px; border: 1px solid #999;">
		    			<div style="text-align: center;">审批</div>
		    			<div style="padding-top: 5px;">
		    				<input name="approverName" type="text" style="width: 80px;" /><input name="approverId" type="hidden"><input name="approveType" type="hidden" value="1">
		    				<div class="choose_approver" style="width:18px; height:18px; float:right; cursor: pointer;"><img src="images/choose.jpg" /></div>
		    			</div>
		    		</div>
		    		
		    		<div class="flow_read" style="width: 108px; padding: 5px; margin-top: 5px; border: 1px solid #999;">
		    			<div>
		    				<div class="flow_read_del" style="width: 15px; heigth: 15px; text-align: center; line-height: 15px; cursor: pointer; float: right;">－</div>
		    				<div style="clear: both;"></div>
		    			</div>
		    			<div style="text-align: center;">传阅</div>
		    			<div style="padding-top: 5px;">
		    				<input name="approverName" type="text" style="width: 80px;" /><input name="approveId" type="hidden"><input name="approveType" type="hidden" value="2">
		    				<div class="choose_approver" style="width:18px; height:18px; float:right; cursor: pointer;"><img src="images/choose.jpg" /></div>
		    			</div>
		    		</div>
		    	</div>
	    	</div>
	    	 -->
    	</div>
    </div>
  </body>
</html>
