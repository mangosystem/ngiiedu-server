<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="shortcut icon" href="<%=contextPath%>/assets/images/nlip.ico" type="image/x-icon" />
<meta charset="utf-8">
<!--[if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<!--[if lt IE 10]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<!--jquery  -->
<script type="text/javascript" src="<%=contextPath%>/assets/cdn/jquery/jquery-3.2.1.min.js"></script>
<script src="<%=contextPath%>/assets/cdn/jquery-ui-1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/assets/cdn/jquery-ui-1.12.1/jquery-ui.css">

<script type="text/javascript" src="<%=contextPath%>/assets/dist/request.js"></script>

<title>공간정보융합활용지원시스템</title>

</head>

<body class="edu">
	
	<jsp:include page ="../common/header.jsp" flush="false">
		<jsp:param value="surport" name="mainHeader"/>
		<jsp:param value="notice" name="subHeader"/>
	</jsp:include>

<div id="contentsWrap">
	<ul class="location">
		<li>홈</li>
		<li>사용지원</li>
		<li>공지사항</li>
	</ul>
	<div class="contents">
		<h3>공지사항</h3>
		<ul class="boardNew">
			<li class="title">
				<label for="">제목</label>
				<input id="qTitle" type="text">
			</li>
			<li class="script">
				<textarea name="" id="qDescription" cols="30" rows="20" class="comment"></textarea>	
			</li>
		</ul>
		<div class="btnCenter">
			<button type="button" title="작성" class="point" onClick="submitPost()">작성</button>
			<button type="button" title="취소" class="default" onclick="document.location = '<%=contextPath %>/surport/notice'">취소</button>
		</div>
		<!-- END QNA NEW -->
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->

<c:if test="${'ADM' eq bbsrole}">
	<script type="text/javascript">
	function submitPost(){
		ajaxJson(
			['POST', apiSvr + '/board/notice.json'], 
			{
				'title' : $('#qTitle').val(),
				'description' : $('#qDescription').val()
		}, 
		function (res) {
			
			if (res.response.code == 500) {
				alert('접근권한이 없습니다.')
			}
			location.href="notice";
		});
	}
	</script>
</c:if>

</body>
</html>
