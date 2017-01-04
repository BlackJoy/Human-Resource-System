<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/dtree.js"></script>
<link href="<%=request.getContextPath()%>/style/dtree.css"
	rel="stylesheet" />
<title>组织机构</title>
</head>

<body>
	<script type="text/javascript">
		function selectCate(ac) {
			//alert(ac);
			window.parent.frames["accountOpFrame"].location.href="<%=request.getContextPath()%>/"+ac;
		}
	</script>
	<div class="dtree">

		<!--<p><a href="javascript: d.openAll();">open all</a> | <a href="javascript: d.closeAll();">close all</a></p>-->

		<script type="text/javascript">
			d = new dTree('d');
		<%String str = request.getAttribute("orgDTree").toString();%>
			
		<%=str%>
			document.write(d);
		</script>
	</div>
</body>
</html>
