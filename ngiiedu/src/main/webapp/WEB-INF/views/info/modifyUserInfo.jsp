<%@page import="kr.go.ngii.edu.config.LocalResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.go.ngii.edu.main.users.model.User"%>
<%
    String apiSvr = LocalResourceBundle.NGIIEDU_API_SERVER;
	String contextPath = request.getContextPath();
	
	User user; 
	String userName = null;

	user = (User)session.getAttribute("USER_INFO");
	
	if (user != null) {
		userName =user.getUserName();		
	}
%>
	
	
<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="shortcut icon" href="/ngiiedu/assets/images/nlip.ico" type="image/x-icon" />
<meta charset="utf-8">
<!--[if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<!--[if lt IE 10]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<title>공간정보융합활용지원시스템</title>
<!--jQuery  -->
<script type="text/javascript" src="<%=contextPath%>/assets/cdn/jquery/jquery-3.2.1.min.js"></script>
<script src="<%=contextPath%>/assets/cdn/jquery-ui-1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/assets/cdn/jquery-ui-1.12.1/jquery-ui.css">
</head>

<body class="edu">
	
	<jsp:include page ="../common/header.jsp" flush="false">
		<jsp:param value="info" name="mainHeader"/>
	</jsp:include>


<div id="contentsWrap">
	<ul class="location">
		<li>홈</li>
		<li>사용자정보변경</li>
	</ul>
	<div class="contents">
		<h3 class="edge">사용자정보변경</h3>
		<ul class="galleryList">
			<%-- <li>
				<div></div>
				<img src="<%=contextPath%>/assets/images/@test1.png" alt="" title="">
			</li> --%>
		</ul>
		<div>
			
			<ul class="modifyInfo">
				<li class="title">
					<label for="">별칭</label>
					<input id="qTitle" type="text" value="<%= userName%>">
				</li>
				<li class="title">
					<label for="">현재 비밀번호</label>
					<input type="text">
				</li>
				<li class="title">
					<label for="">새 비밀번호</label>
					<input type="text">
				</li>
				<li class="title">
					<label for="">비밀번호 재확인</label>
					<input type="text">
				</li>
			</ul>
			
			<div class="btnCenter">
				<button type="button" title="변경" class="point" >변경</button>
			</div>
		</div>
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->
</body>
</html>
