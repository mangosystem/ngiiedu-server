<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
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
<title>지리원/공간정보융합 활용지원정보</title>
	
</head>

<body class="edu">
	
	<jsp:include page ="../common/header.jsp" flush="false">
		<jsp:param value="introduce" name="mainHeader"/>
		<jsp:param value="introduce" name="subHeader"/>
	</jsp:include>


<div id="contentsWrap">
	<ul class="location">
		<li>홈</li>
		<li>수업소개</li>
	</ul>
	<div class="contents">
		<h3 class="edge">수업소개</h3>
		<div class="introduceList">
			<div id="item1" class="introduceItem" onClick="location.href='<%=contextPath%>/introduce/1'">
				<p>우리지역 소음지도</p>
			</div>
			<div class="introduceItem" id="item2" onClick="location.href='<%=contextPath%>/introduce/2'">
				<p>GPS활용 위치학습</p>
			</div>
			<div class="introduceItem" id="item3" onClick="location.href='<%=contextPath%>/introduce/3'">
				<p>우리지역 인구지도</p>
			</div>
			<div class="introduceItem" id="item4" onClick="location.href='<%=contextPath%>/introduce/4'">
				<p>통합적 영토교육</p>
			</div>
			<div class="introduceItem" id="item5" onClick="location.href='<%=contextPath%>/introduce/5'">
				<p>우리학교 운동장 생태지도</p>
			</div>
			<div class="introduceItem" id="item6" onClick="location.href='<%=contextPath%>/introduce/6'">
				<p>지도 정확성</p>
			</div>
			<div class="introduceItem" id="item7" onClick="location.href='<%=contextPath%>/introduce/7'">
				<p>독도의 중요성</p>
			</div>
		</div>
		<!-- END GALLERYLIST -->
		
		<div class='source' >
			출처 : 강영옥외, 2017, "공간정보 융복합 교육지원을 위한 수업모듈 개발" 국토지리정보원
		</div>
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->
</body>
</html>
