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
table td{border-left:1px solid #000;border-top:1px solid #000} 
</style>
<script type="text/javascript" language="javascript"
	src="js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" language="javascript">
		$(document).ready(function(){
		   $("#btn_doReturn").click(
		   function(){
		        $("#trainPlainForm").attr("action", "trainPlain_trainPlainList");
				$("#trainPlainForm")[0].submit();		   
		   }		   
		   );
		   $("#btnOk").click(
		       function(){		      
		     $("#trainPlainForm").attr("action", "effectAction_analyseScore");
			$("#trainPlainForm")[0].submit();
		        
		       }		   
		   );
		});
</script>
</head>
<body>
  <input type="button" name="btn_doReturn" id="btn_doReturn" value="返回"><br/>
	<form name="trainPlainForm" id="trainPlainForm" action="" method="post">
		
		
		<input type="hidden" name="trainId" value="<s:property value="trainId"/>">
		<input type="hidden" name="pageIndex" value="<s:property value="pageIndex"/>">
		
		
		<table style="width: 100%; border: 1px solid black;" >		
      <tbody>
        <tr>
          <td>时间: <s:property value="trainPlain.trainStart"/>-<s:property value="trainPlain.trainEnd"/></td>
          <td colspan="1" rowspan="2">培训项目:<s:property value="trainPlain.trainSubject"/></td>
          <td> 
          <br></td>
        </tr>
        <tr>
          <td>地点:</td>
          <td>培训方式:<s:property value="trainPlain.trainManner"/></td>
        </tr>
        <tr>
        
          <td colspan="3" rowspan="1">参加培训人员名单(共<strong style="color:red"><s:property value="#scoreList.size"/></strong>人)<br/>
          <s:iterator var="score" value="#request.scoreList" status="i">
             <a href="<%=basePath%>effectAction_toIndivEnvalPage?scoreId=<s:property value="#score.id"/>">
             <s:property value="#score.employeename"/></a>、
          </s:iterator>
          </td>
        </tr>
        <tr>
          <td colspan="3" rowspan="1">培训内容摘要:<br/><textarea name="trainDetail" disabled="disabled"><s:property value="trainPlain.trainDetail"/>
          </textarea></td>
        </tr>
        <tr>
          <td colspan="3" rowspan="1">成绩分析:输入分数段:  <input name="lowerScore" placeholder="60"/>--<input name="higherScore" placeholder="90"/>
          <input type="button" name="btnOk" id="btnOk" value="确定"><br/>
          <textarea name="analyseDesc">
             <s:property value="analyseDesc"/>
          </textarea>
          </td>
        </tr>
        <tr>
          <td colspan="3" rowspan="1">考试合格率:<br/></td>
        </tr>
        <tr>
          <td colspan="3" rowspan="1">备注:<br/>
            <s:property value="trainPlain.trainRemarks"/>
          </td>
        </tr>
      </tbody>
    </table>
	
	</form>	
</body>
</html>
