<%@ page language="java" import="java.util.*,com.sys.menu.*" pageEncoding="utf-8"%>
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
    
    <title>左侧菜单页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<link rel="stylesheet" type="text/css" href="style/leftMenu.css">
	<script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#chooseMenu-dl").mouseover(function(){
				$("#mainMenu-dd").show();
			});
			$("#chooseMenu-dl").mouseout(function(){
				$("#mainMenu-dd").hide();
			});
			$(".branch").click(function(event){
				if($(this).next("ul:first").css("display")=="none"){
					$(this).next("ul:first").css("display","block");
					$(this).find("img:first").attr("src","images/P2.gif");
				} else {
					$(this).next("ul:first").css("display","none");
					$(this).find("img:first").attr("src","images/P1.gif");
				}
				event.stopPropagation();
			});
			$("div.subMenu").click(function(){
				$("div.subMenu + ul").hide();
				$(this).next().show();
			});
		});
	</script>

  </head>
  
  <body>
   <div class="mainMenu">
	<dl id="chooseMenu-dl" style="width: 150px;">
		<dt><a class="mm_a" href="<s:property value='#session.curr_menu.action'/>?menuId=<s:property value='#session.curr_menu.id'/>"><s:property value="#session.curr_menu.name"/></a></dt>
		<dd id="mainMenu-dd" style="display:none; position: absolute;">
		<ul>
		<s:iterator var="menu" value="#application.menuTree">
			<s:iterator var="authority" value="#session.curr_user_authority">
				<s:if test="#menu.id==#authority.menuId">
				<li><a class="mm_a1" href="<s:property value='#menu.action'/>?menuId=<s:property value='#menu.id'/>" onclick='parent.mainFrame.location.href="page/common/init-content.jsp"'><s:property value="#menu.name"/></a></li>
				</s:if>
			</s:iterator>
		</s:iterator>
		</ul>
		</dd>
	</dl>
   </div>
   <%--自定义标签显示子菜单 --%>
   <ct:menu />
  </body>
</html>
