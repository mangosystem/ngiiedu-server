<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
	
	
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<!--[if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<!--[if lt IE 10]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<title>지리원/공간정보융합 활용지원정보</title>
</head>

<body class="edu">
	
	<jsp:include page ="../common/header.jsp" flush="false">
		<jsp:param value="gallary" name="mainHeader"/>
		<jsp:param value="gallary" name="subHeader"/>
	</jsp:include>


<div id="contentsWrap">
	<ul class="location">
		<li>홈</li>
		<li>수업활동갤러리</li>
	</ul>
	<div class="contents">
		<h3 class="edge">수업활동갤러리</h3>
		<ul class="galleryList">
			<li>
				<div></div>
				<img src="<%=contextPath%>/assets/images/@test1.png" alt="" title="">
			</li>
			<li>
				<div></div>
				<img src="<%=contextPath%>/assets/images/@test2.png" alt="" title="">
			</li>
			<li>
				<div></div>
				<img src="<%=contextPath%>/assets/images/@test3.png" alt="" title="">
			</li>
			<li>
				<div></div>
				<img src="<%=contextPath%>/assets/images/@test4.png" alt="" title="">
			</li>
			<li>
				<div></div>
				<img src="<%=contextPath%>/assets/images/@test5.png" alt="" title="">
			</li>
		</ul>
		<ul class="pagination">
			<li class="ico first" title="맨앞으로">앞으로</li>
			<li class="ico forward" title="앞으로">앞으로</li>
			<li>1</li>
			<li>2</li>
			<li>3</li>
			<li class="on">4</li>
			<li>5</li>
			<li>6</li>
			<li class="ico back" title="뒤로">뒤로</li>
			<li class="ico end" title="맨뒤로">맨뒤로</li>
		</ul>
		<!-- END GALLERYLIST -->
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->
</body>
</html>
