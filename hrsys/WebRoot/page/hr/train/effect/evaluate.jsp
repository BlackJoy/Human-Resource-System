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
<link rel="stylesheet" type="text/css" href="style/train.css">
</head>

<script type="text/javascript" language="javascript"
	src="js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" language="javascript">
		$(document).ready(function(){
		   $("#doAdd").click(
		   function(){
		        $("#trainPlainForm").attr("action", "effectAction_doEvaluate");
				$("#trainPlainForm")[0].submit();		   
		   }		   
		   );
		});
		</script>
<body>	
	<form name="trainPlainForm" id="trainPlainForm" action="" method="post">	
		    <input type="hidden" name="whichIdea" value='<s:property value="whichIdea"/>'>
		    <input type="hidden" name="trainId" value='<s:property value="#request.trainId"/>'>
	    <div  id="evaluate">
	    <s:if test="whichIdea==\"1\"">理论部分评价</s:if>
		<s:if test="whichIdea==\"2\"">实作部分评价</s:if>
		<s:if test="whichIdea==\"3\"">现岗位评价</s:if>
		<s:if test="whichIdea==\"4\"">人力资源部门评价</s:if>
		<s:if test="whichIdea==\"5\"">主管领导建议</s:if>
	     </div>
		 <div id="ideaDiv">
		  <textarea rows="12" cols="40" name="idea" id="idea"></textarea>
	     </div>
	     <input type="button" id="doAdd" value="提交" >
	     <input type="reset" value="重置">
	     <input type="button" id="doReturn" value="返回">
	  </form>	   
	     评论人：<input name="emp.employeeName" value='<s:property value="emp.employeeName"/>'disabled="disabled"/>
	     <s:if test="trainPlain.theoryEval!=null">
                 <div id="theoryEvalDiv">
                                                              理论部分评价
                    <textarea disabled="disabled" value="">
                        <s:property value="trainPlain.theoryEval"/>
                    </textarea>
                    	评论人：<s:property value="trainPlain.teEmplyeeName"/>
                    	时间：<s:property value="trainPlain.teTime"/>                    	
                 </div>
                 </s:if>
      
		    <s:if test="trainPlain.practiceEval!=null">
                 <div id="practiceEvalDiv">
                                                         实作部分评价
                    <textarea disabled="disabled" value="">
                        <s:property value="trainPlain.practiceEval"/>
                    </textarea>
                    	评论人：<s:property value="trainPlain.peEmplyeeName"/>
                    	时间：<s:property value="trainPlain.peTime"/>                    	
                 </div>
                 </s:if>
        
			<s:if test="trainPlain.curPostIdea!=null">
                 <div id="curPostIdeaDiv">
                                                       现岗位评价
                    <s:property value="trainPlain.curPostIdea"/>
                    <div id="person">评论人：<s:property value="trainPlain.curPostManagerName"/>
                    	时间：<s:property value="trainPlain.curPostIdeaTime"/> </div>                   	
                 </div>
                 </s:if>
        
			<s:if test="trainPlain.hrPostIdea!=null">
                 <div id="hrPostIdeaDiv">
                                                       人力资源部门评价
                    <textarea disabled="disabled" value="">
                        <s:property value="trainPlain.hrPostIdea"/>
                    </textarea>
                    	评论人：<s:property value="trainPlain.hrPostManagerCode"/>
                    	时间：<s:property value="trainPlain.hrPostIdeaTime"/>                    	
                 </div>
                 </s:if>
			
		<s:if test="trainPlain.managerIdea!=null">
                 <div id="managerIdeaDiv">
                                                       主管领导建议
                    <textarea disabled="disabled" value="">
                        <s:property value="trainPlain.managerIdea"/>
                    </textarea>
                    	评论人：<s:property value="trainPlain.managerCode"/>
                    	时间：<s:property value="trainPlain.managerIdeaTime"/>                    	
                 </div>
         </s:if>
	
</body>
</html>
