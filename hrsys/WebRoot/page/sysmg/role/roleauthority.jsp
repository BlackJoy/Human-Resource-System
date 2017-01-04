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
    
    <title>My JSP 'roleauthority.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$(".auth_parent").click(function(event){
				if($(this).next("ul:first").css("display")=="none"){
					$(this).next("ul:first").css("display","block");
					if ($(this).attr("isend")=="true") {
						$(this).find("img:first").attr("src","images/M2.gif");
					} else {
						$(this).find("img:first").attr("src","images/P2.gif");
					}
				} else {
					$(this).next("ul:first").css("display","none");
					if ($(this).attr("isend")=="true") {
						$(this).find("img:first").attr("src","images/M1.gif");
					} else {
						$(this).find("img:first").attr("src","images/P1.gif");
					}
				}
				event.stopPropagation();
			});
			$(".ckb").click(function(event){
				//如果被勾选
				//1.需要勾选其所有的子权限
				//2.需要勾选其所有的父权限
		     	if($(this).attr("checked")){
		     		var level = $(this).attr("level"); //获取复选框level属性
		     		
		     		//将所有以level变量开头的复选框都选中(如果复选框的level属性值以level变量开头，则肯定为子权限)
		     		$(".ckb[level^='"+level+"']").attr("checked",true);
		     		
		     		//根据">"字符从后向前依次截取，截取出的字符串必定为父权限level属性值
		     		var p_level = level;
		     		while((p_level = p_level.substring(0,p_level.lastIndexOf(">"))) != ""){
		     			$(".ckb[level='"+p_level+"']").attr("checked",true);
		     		}
				} else {
					//如果被反选
					//1.需要反选其所有的子权限
					//2.需要判断其所有父权限的其他子权限是否被勾选，如果都未勾选则父权限反选，如果有被勾选的则父权限不反选
					var level = $(this).attr("level"); //获取复选框level属性
					
					//将所有以level变量开头的复选框都反选(如果复选框的level属性值以level变量开头，则肯定为子权限)
		     		$(".ckb[level^='"+level+"']").attr("checked",false);
		     		
		     		//根据">"字符从后向前依次截取，截取出的字符串必定为父权限level属性值
		     		var p_level = level;
		     		while((p_level = p_level.substring(0,p_level.lastIndexOf(">"))) != ""){
		     			//如果父权限下有被选中的子权限，则父权限被选中(子权限：p_level+">"，必须加">"，不然会将父权限也计算在内)
		     			if($(".ckb[level^='"+p_level+">']:checked").size() > 0) {
			     			//$(".ckb[level='"+p_level+"']").attr("checked",true);
			     			break;
		     			} else {
		     			//如果父权限下所有子权限都没有被选中，则父权限反选
		     				$(".ckb[level='"+p_level+"']").attr("checked",false);
		     			}
		     		}
				}
				event.stopPropagation();
			});
			$("#btn_return").click(function(){
				self.location.href="<%=basePath%>role_list.action";
			})
			$("#btn_save").click(function(){
				$("#authorizationForm")[0].submit();
			})
		});
	</script>
  </head>
  
  <body>
  	<s:if test="#request.roleDelete != null">
  		<script type="text/javascript" language="javascript">
  		alert("<s:property value='#request.authorization' escape='false'/>");
  		</script>
  	</s:if>
  	<table border="0" width="100%" style="background: rgb(192, 212, 240) url(images/bg_title01.gif) repeat-x;" cellpadding="0" cellspacing="0">
    	<tr>
    		<td>
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
		    		<td width="10px"></td>
		    		<td height="26px" width="7px"><img src="images/yq_l.gif"/></td>
		    		<td height="26px" style="background: url(images/yq_bg.gif) repeat-x; font-size:12px; font-weight: bold; color:#333;">&nbsp;角色管理&nbsp;</td>
		    		<td height="26px" width="7px"><img src="images/yq_r.gif"/></td>
	    		</tr>
    		</table>
    		</td>
    	</tr>
    </table>
  	<table border="0" width="100%" style="background: rgb(192, 212, 240);" cellpadding="0" cellspacing="0">
    	<tr>
    		<td height="30px" valign="middle" style="padding-left:30px;">
    			<input id="btn_save" type="button"" value="保存" />&nbsp;&nbsp;
    			<input id="btn_return" type="button" value="返回" />&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <form id="authorizationForm" action="role_authorization.action" method="post">
  	<input id="roleId" name="roleId" type="hidden" value="<s:property value='roleId' />">
  	<div style="margin:5px;">
	  	<div><img src="images/root.gif" align="middle"">系统权限</div>
	  	<%--自定义标签：显示权限树 --%>
	  	<div><ct:authority roleId="${roleId }"/></div>
    </div>
    </form>
  </body>
</html>
