<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<jsp:param value="qna" name="subHeader"/>
	</jsp:include>

<div id="contentsWrap">
	<ul class="location">
		<li>홈</li>
		<li>사용지원</li>
		<li>묻고 답하기</li>
	</ul>
	<div class="contents">
		<h3>묻고 답하기</h3>
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
			<button type="button" title="취소" class="default" onclick="document.location = '<%=contextPath %>/surport/qna'">취소</button>
		</div>
		<!-- END QNA NEW -->
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->

<script>
	
	function submitPost(){
		ajaxJson(
			['POST', apiSvr + '/board/qna.json'], 
			{
	     		"title" : $('#qTitle').val(),
				"description" : $('#qDescription').val()
	        }, 
	        function (res) {
            	var data = res.response.data;
            	location.href="qna";
        });
	}
</script>
</body>
</html>
