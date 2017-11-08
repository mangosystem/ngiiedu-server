<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.go.ngii.edu.main.users.model.User"%>
<%
	String contextPath = request.getContextPath();

	User user = (User)session.getAttribute("USER_INFO");
	
	String userId = null;
	String userEmail = null;
	String userName = null;
	String userDivision = null;
	
	if (user != null) {
		userId =user.getUserid();
		userEmail =user.getUserEmail();
		userName =user.getUserName();
		userDivision =user.getUserDivision();
	}
%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<title>공간정보융합활용지원시스템</title>
		<meta charset="utf-8">
		<meta name="csrf-token" content="{{ csrf_token() }}">
		
		<script type="text/javascript">
			var userId = "<%= userId %>";
			var userName = "<%= userName %>";
			var userDivision = <%= userDivision %>;
		</script>

		<!--openlayers  -->
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/cdn/openlayers/dist/ol.css" />
		<script type="text/javascript" src="<%=contextPath%>/assets/cdn/openlayers/dist/ol-debug.js"></script>

		<!--jquery  -->
		<script type="text/javascript" src="<%=contextPath%>/assets/cdn/jquery/jquery-3.2.1.min.js"></script>

		<!-- font-awesome -->
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/cdn/font-awesome/css/font-awesome.min.css" />

		<!-- smart-editor -->
		<script type="text/javascript" src="<%=contextPath%>/assets/cdn/editor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
		
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/dist/index.css" />
		<script type="text/javascript" src="<%=contextPath%>/assets/dist/request.js"></script>
	</head>

	<body>
		<div id="app-start">
				<!-- Content Area -->
		</div>
		<script type="text/javascript" src="<%=contextPath%>/assets/dist/bundle.js"></script>
	</body>
</html>
