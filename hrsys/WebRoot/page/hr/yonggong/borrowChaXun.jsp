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
			 $("#btn_delete_neiborrow").click(function(){	
					$("#accountForm1").attr("action", "yonggong_neiBorrowDel.action");
					$("#accountForm1")[0].submit();
						});
			$("#btn_delete_borrowin").click(function(){	
			     $("#accountForm3").attr("action", "yonggong_borrowInDel.action");
			     $("#accountForm3")[0].submit();
			});
			$("#btn_delete_borrowout").click(function(){				   				
			       $("#accountForm2").attr("action", "yonggong_borrowOutDel.action");			   
				   $("#accountForm2").submit();
				   
				   
				   
				   //----------------
				   
				   
				   
			});
  });  
    function switchTab(ProTag,ProBox)
    {
       for(var i=1;i<4;i++)
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
       width: 120px;
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
            <li id="tab1"><a href="#" class="on" onclick="switchTab('tab1','con1');this.blur();return false;">
                内部人员借调情况</a></li>
            <li id="tab2"><a href="#" onclick="switchTab('tab2','con2');this.blur();return false;">
                外部人员借出情况</a></li>
            <li id="tab3"><a href="#" onclick="switchTab('tab3','con3');this.blur();return false;">
               外部人员介入情况</a></li> 
        </ul>
        </div>        
        <div style="clear: both">
        </div>
        <input type="hidden" id="orgId" name="orgId" value="<s:property value='#request.orgId'/>"/>
        <div id="con1">
            <form name="accountForm1" id="accountForm1" action="" method="post">         
        <input id="btn_delete_neiborrow" type="button" value="遣返"/> 
    <input type="hidden" id="pageIndex" name="pageIndex" value="<s:property value='pageIndex'/>"/>      	
    <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
    		<td width="30px"><input type="checkbox"/></td>
    		<td>员工姓名</td>
    		<td>原服务部门</td>
    		<td>现服务部门</td>
    		<td>截止日期</td>
    		<td>是否返岗</td>
    	
    	</tr>
    	<s:set name="rowCount" value="0"></s:set>
    	<s:iterator var="account" value="#request.neiborrow_list_page.list" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>
				<td align="center"><input class="ckb" name="borrowId" id="borrowId" type="checkbox" <s:if test="ISFANHUI=='已返回'">disabled="disabled"</s:if>value="<s:property value="ID"/>"/></td>								
				<td align="center"><s:property value="EMPNAME"/></td>
				<td align="center"><s:property value="FROMORGNAME"/></td>
				<td align="center"><s:property value="TOORGNAME"/></td>
				<td align="center"><s:property value="ENDTIME"/></td>
				<s:if test="ISFANHUI=='未返回'">
			    <td align="center" style="color:red"><s:property value="ISFANHUI"/></td>
			    </s:if>
			    <s:else>
	   		    <td align="center" style="color:green"><s:property value="ISFANHUI"/></td>			    
			    </s:else>
    		</tr>
    		<s:set name="rowCount" value="#i.count"></s:set>
    	</s:iterator>
    	<s:iterator begin="#rowCount+1" end="#request.neiborrow_list_page.pageSize" status="i">
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
    </form>
        </div>
        <div id="con2" style="display: none">
     <form name="accountForm2" id="accountForm2" action="" method="post">    
     <input id="btn_delete_borrowout" type="button" value="召回"/>
    <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">
    		<td width="30px"><input type="checkbox"/></td>
    		<td>员工姓名</td>
    		<td>原服务部门</td>
    		<td>现服务公司</td>
    		<td>截止日期</td>
    		<td>是否返岗</td>   		
    	</tr>
    	<s:set name="rowCount" value="0"></s:set>
    	<s:iterator var="account" value="#request.borrowout_list_page.list" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>			    	
				<td align="center"><input class="ckb" name="borrowId" type="checkbox" <s:if test="ISFANHUI=='已返回'">disabled="disabled"</s:if>value="<s:property value="ID"/>"/></td>
				
				<td align="center"><s:property value="EMPNAME"/></td>				
				<td align="center"><s:property value="FROMORGNAME"/></td>
				<td align="center"><s:property value="TOCOMPANY"/></td>
				<td align="center"><s:property value="ENDTIME"/></td>	
					<s:if test="ISFANHUI=='未返回'">
			    <td align="center" style="color:red"><s:property value="ISFANHUI"/></td>
			    </s:if>
			    <s:else>
	   		    <td align="center" style="color:green"><s:property value="ISFANHUI"/></td>			    
			    </s:else>			
    		</tr>
    		<s:set name="rowCount" value="#i.count"></s:set>
    	</s:iterator>
    	<s:iterator begin="#rowCount+1" end="#request.borrowout_list_page.pageSize" status="i">
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
    </form>   
        </div>
        <div id="con3" style="display: none">
            <form name="accountForm3" id="accountForm3" action="" method="post">
        
              <input id="btn_delete_borrowin" type="button" value="遣返"/>
              <table border="0" width="100%" style="background: #fafafa; font-size:12px;" cellpadding="0" cellspacing="1">
    	<tr style="background: rgb(221, 232, 247); text-align: center; height: 20px;">  		
    		<td width="30px"><input type="checkbox"/></td>
    		<td>员工姓名</td>
    		<td>原服务公司</td>
    		<td>现服务部门</td>
    		<td>截止日期</td>
    		<td>是否返岗</td>
    		  		
    	</tr>
    	<s:set name="rowCount" value="0"></s:set>
    	<s:iterator var="account" value="#request.borrowin_list_page.list" status="i">
    		<s:if test="#i.odd">
    		<tr style="background: rgb(240, 240, 240); height:22px;">
    		</s:if>
			<s:else>
			<tr style="background: rgb(248, 248, 248); height:22px;">
			</s:else>	
				<td align="center"><input class="ckb" name="borrowId" type="checkbox" <s:if test="ISFANHUI=='已返回'">disabled="disabled"</s:if>value="<s:property value="ID"/>"/></td>
				
				<td align="center"><s:property value="EMPNAME"/></td>				
				<td align="center"><s:property value="FROMCOMPANY"/></td>
				<td align="center"><s:property value="TOORGID"/></td>
				<td align="center"><s:property value="ENDTIME"/></td>
					<s:if test="ISFANHUI=='未返回'">
			    <td align="center" style="color:red"><s:property value="ISFANHUI"/></td>
			    </s:if>
			    <s:else>
	   		    <td align="center" style="color:green"><s:property value="ISFANHUI"/></td>			    
			    </s:else>										
    		</tr>
    		<s:set name="rowCount" value="#i.count"></s:set>
    	</s:iterator>
    	<s:iterator begin="#rowCount+1" end="#request.borrowin_list_page.pageSize" status="i">
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
    </form>
        </div> 
</body>
  
</html>
