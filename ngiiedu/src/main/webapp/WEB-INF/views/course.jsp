<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.go.ngii.edu.main.users.model.User"%>
<%
	String contextPath = request.getContextPath();

	User user = (User)session.getAttribute("USER_INFO");
	
	String userId = null;
	String userEmail = null;
	String userName = null;
	String userDivision = null;
	int userIdx = -1;

	
	if (user != null) {
		userId =user.getUserid();
		userEmail =user.getUserEmail();
		userName =user.getUserName();
		userDivision =user.getUserDivision();
		userIdx =user.getIdx();
	}
%>
<!DOCTYPE html>
<html lang="ko">
	<link rel="shortcut icon" href="/ngiiedu/assets/images/nlip.ico" type="image/x-icon" />
	<head>
		<title>공간정보융합활용지원시스템</title>
		<meta charset="utf-8">
		<meta name="csrf-token" content="{{ csrf_token() }}">
		
		<script type="text/javascript">
			var userId = "<%= userId %>";
			var userName = "<%= userName %>";
			var userDivision = <%= userDivision %>;
			var userIdx = <%= userIdx %>;

		</script>
		<!-- CKEditor4 -->
		<script type="text/javascript" src="<%=contextPath%>/assets/cdn/ckeditor/ckeditor.js"></script>
		
		<!--openLayers  -->
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/cdn/openlayers/dist/ol.css" />
		<script type="text/javascript" src="<%=contextPath%>/assets/cdn/openlayers/dist/ol-debug.js"></script>
		
		<!--openLayers swipeControl -->
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/cdn/openlayers/control/swipecontrol.css" />
		<script type="text/javascript" src="<%=contextPath%>/assets/cdn/openlayers/control/swipecontrol.js"></script>
		
		<!--openLayers layerSwitcher -->
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/cdn/openlayers/control/ol3-layerswitcher.css" />
		<script type="text/javascript" src="<%=contextPath%>/assets/cdn/openlayers/control/ol3-layerswitcher.js"></script>
		
		<!--openLayers proj4 -->
		<script type="text/javascript" src="<%=contextPath%>/assets/cdn/openlayers/control/proj4.js"></script>

		<!--jQuery  -->
		<script type="text/javascript" src="<%=contextPath%>/assets/cdn/jquery/jquery-3.2.1.min.js"></script>
		<script src="<%=contextPath%>/assets/cdn/jquery-ui-1.12.1/jquery-ui.js"></script>
		<link rel="stylesheet" href="<%=contextPath%>/assets/cdn/jquery-ui-1.12.1/jquery-ui.css">
		
		<!--pyramid  -->
		<script src="<%=contextPath%>/assets/cdn/pyramid/d3.min.js"></script>

		<!-- font-awesome -->
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/cdn/font-awesome/css/font-awesome.min.css" />

		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/dist/index.css" />
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/dist/style.css" />
		
		<script type="text/javascript" src="<%=contextPath%>/assets/dist/request.js"></script>
		
		<!--Google API-->
		<script type="text/javascript" src="<%=contextPath%>/assets/dist/googleAPI.js"></script>
		<script async defer src="https://apis.google.com/js/api.js"
			onload="this.onload=function(){};handleClientLoad()"
			onreadystatechange="if (this.readyState === 'complete') this.onload()">
		</script>			
	</head>

	<body>
		<div id="app-start">
				<!-- Content Area -->
		</div>
		<script type="text/javascript" src="<%=contextPath%>/assets/dist/bundle.js"></script>
	</body>
</html>
