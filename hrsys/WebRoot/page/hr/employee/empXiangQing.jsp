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
    
    <title>员工相关信息主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <script type="text/javascript" language="javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
  <script type="text/javascript">
  $(document).ready(function(){
			 $("#btn_add_family").click(function(){
							var oid = $("#orgId").val();
							var pi = $("#pageIndex").val();
							var empid = $("#employeeId").val();
						
							//alert("111"+oid);
							self.location.href="<%=basePath%>employee_toAddFamily.action?orgId="+oid+"&pageIndex="+pi+"&employeeId="+empid;
						
						});
			$("#btn_add_jiangcheng").click(function(){
				var oid = $("#orgId").val();
				var pi = $("#pageIndex").val();
				var empid = $("#employeeId").val();
				//alert("111"+oid);
				self.location.href="<%=basePath%>employee_toAddJiangcheng.action?orgId="+oid+"&pageIndex="+pi+"&employeeId="+empid;
			
			});
			$("#btn_add_healthy").click(function(){
				var oid = $("#orgId").val();
				var pi = $("#pageIndex").val();
				var empid = $("#employeeId").val();
				//alert("111"+oid);
				self.location.href="<%=basePath%>employee_toAddHealthy.action?orgId="+oid+"&pageIndex="+pi+"&employeeId="+empid;
			
			});
  });
    function btn_return()
    {
        var oid = $("#orgId").val();
		var pi = $("#pageIndex").val();
		
		
				//alert("111"+oid);
		self.location.href="<%=basePath%>employee_list.action?orgId="+oid+"&pageIndex="+pi;
			
    }
    function switchTab(ProTag,ProBox)
    {
       for(var i=1;i<5;i++)
        {
            if(ProTag=="tab"+i)
            {
               document.getElementById(ProTag).getElementsByTagName("a")[0].className="on";
              
            }else
            {                   
               document.getElementById("tab"+i).getElementsByTagName("a")[0].className="";          
              
            }
            if(ProBox=="con"+i)
            {
               document.getElementById(ProBox).style.display="";
               
            }else
            {                   
               document.getElementById("con"+i).style.display="none";          
               
            }
        }
    }
  </script>
  <link rel="stylesheet" type="text/css" href="style/common.css">
	<style type="text/css">
	a.page_l{ text-decoration: none; color: blue;}
	a.page_l:hover{ text-decoration: underline; color: blue;}
	a.page_l:visited{ text-decoration: none; color: blue;}
	</style>
  <style type="text/css">
    *
    {
       padding: 0;
       margin: 0;
       line-height: 25px;
       font-size: 12px;
       list-style: none;
       
    }
    #tabContainer
    {
       margin :30px;
    }
     #tabContainer li
    {
       margin :0 3px;
       float: left;
       width: 80px;
       text-align: center;
       background:#efefef;
    }
     #tabContainer a
    {
       display:block;
    }
     #tabContainer a.on
    {
       background: pink;
    }
  </style>
  </head>
  <body>
    <div id="tabContainer">
        <ul>
            <li id="tab1"><a href="#" class="on" onclick="switchTab('tab1','con1');this.blur();return false;">学习情况</a></li>
            <li id="tab2"><a href="#" onclick="switchTab('tab2','con2');this.blur();return false;">
                家庭情况</a></li>
            <li id="tab3"><a href="#" onclick="switchTab('tab3','con3');this.blur();return false;">
                奖惩情况</a></li>
            <li id="tab4"><a href="#" onclick="switchTab('tab4','con4');this.blur();return false;">
               健康情况</a></li>
        </ul>
        </div>        
        <div style="clear: both">
        </div>
          <input type="hidden" id="employeeId" name="employeeId" value="<s:property value='employeeId'/>" />
          
        <input type="hidden" id="orgId" name="orgId" value="<s:property value='#request.orgId'/>"/>
        <div id="con1">   
    <input type="hidden" id="pageIndex" name="pageIndex" value="<s:property value='pageIndex'/>"/>      	
    <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
    		<td>培训编号</td>
    		<td>培训名称</td>
    		<td>员工成绩</td>
    		<td>评价</td>
    	
    	</tr>
    	<s:set name="rowCount" value="0"></s:set>
    	<s:iterator var="account" value="#request.train_score_list_page.list" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>
				<td align="center"><s:property value="TRAINCODE"/></td>
				<td align="center"><s:property value="TRAINSUBJECT"/></td>
				<td align="center"><s:property value="EMPSCORE"/></td>
				<td align="center"><s:property value="EVALUATE"/></td>
			
    		</tr>
    		<s:set name="rowCount" value="#i.count"></s:set>
    	</s:iterator>
    	<s:iterator begin="#rowCount+1" end="#request.train_score_list_page.pageSize" status="i">
    		<s:if test="(#i.count+#rowCount) % 2 != 0">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>
				<td align="center">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>					
    		</tr>
    	</s:iterator>
    </table> 
    <input id="btn_return" type="button" value="返回" onclick="btn_return()"/> 
        </div>
        <div id="con2" style="display: none">
    <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
    		<td>成员姓名</td>
    		<td>成员称呼</td>
    		<td>工作</td>
    		<td>出生日期</td>
    		<td>成员电话</td>   		
    	</tr>
    	<s:set name="rowCount" value="0"></s:set>
    	<s:iterator var="account" value="#request.family_member_list_page.list" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>			    	
				<td align="center"><s:property value="MEMBERNAME"/></td>				
				<td align="center"><s:property value="MEMBERRALATION"/></td>
				<td align="center"><s:property value="MEMBERJOB"/></td>
				<td align="center"><s:property value="MEMBERBIRTH"/></td>	
				<td align="center"><s:property value="MEMBERPHONE"/></td>				
    		</tr>
    		<s:set name="rowCount" value="#i.count"></s:set>
    	</s:iterator>
    	<s:iterator begin="#rowCount+1" end="#request.family_member_list_page.pageSize" status="i">
    		<s:if test="(#i.count+#rowCount) % 2 != 0">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>
				<td align="center">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>		
				<td>&nbsp;</td>
			
    		</tr>
    	</s:iterator>
    </table> 
    <input id="btn_add_family" type="button" value="添加"/>
    <input id="btn_return" type="button" value="返回" onclick="btn_return()"/>  
        </div>
        <div id="con3" style="display: none">
              <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">  		
    		<td>员工姓名</td>
    		<td>奖惩措施</td>
    		<td>原因</td>
    		<td>时间</td>
    		  		
    	</tr>
    	<s:set name="rowCount" value="0"></s:set>
    	<s:iterator var="account" value="#request.jiangcheng_list_page.list" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>	
				<td align="center"><s:property value="EMPNAME"/></td>				
				<td align="center"><s:property value="CUOSHI"/></td>
				<td align="center"><s:property value="REASON"/></td>
				<td align="center"><s:property value="TIME"/></td>									
    		</tr>
    		<s:set name="rowCount" value="#i.count"></s:set>
    	</s:iterator>
    	<s:iterator begin="#rowCount+1" end="#request.jiangcheng_list_page.pageSize" status="i">
    		<s:if test="(#i.count+#rowCount) % 2 != 0">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>
				<td align="center">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>					
    		</tr>
    	</s:iterator>
    </table> 
    <input id="btn_add_jiangcheng" type="button" value="添加"/>
    <input id="btn_return" type="button" value="返回" onclick="btn_return()"/>
        </div>
        <div id="con4" style="display: none">
                           <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">  		
    		<td>病情</td>
    		<td>时间</td>
    		<td>病因</td>
    		<td>是否治愈</td>
    		  		
    	</tr>
    	<s:set name="rowCount" value="0"></s:set>
    	<s:iterator var="account" value="#request.healthy_list_page.list" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>	
				<td align="center"><s:property value="BINGNAME"/></td>				
				<td align="center"><s:property value="TIME"/></td>
				<td align="center"><s:property value="REASON"/></td>
				<td align="center"><s:property value="ISZHIYU"/></td>	
								
    		</tr>
    		<s:set name="rowCount" value="#i.count"></s:set>
    	</s:iterator>
    	<s:iterator begin="#rowCount+1" end="#request.healthy_list_page.pageSize" status="i">
    		<s:if test="(#i.count+#rowCount) % 2 != 0">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>
				<td align="center">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>					
    		</tr>
    	</s:iterator>    	
    </table>
    <input id="btn_add_healthy" type="button" value="添加"/>
    <input id="btn_return" type="button" value="返回" onclick="btn_return()"/>
        </div>
    
    
    
</body>
  
</html>
