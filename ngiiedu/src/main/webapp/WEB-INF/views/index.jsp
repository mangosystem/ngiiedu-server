<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="shortcut icon" href="/ngiiedu/assets/images/nlip.ico" type="image/x-icon" />
<meta charset="utf-8">
<!--[if lt IE 9]>4
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<!--[if lt IE 10]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<!--jquery  -->
<script type="text/javascript"
	src="<%=contextPath%>/assets/cdn/jquery/jquery-3.2.1.min.js"></script>
<script src="<%=contextPath%>/assets/cdn/jquery-ui-1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="<%=contextPath%>/assets/cdn/jquery-ui-1.12.1/jquery-ui.css">

<title>지리원/공간정보융합 활용지원정보</title>

<script>
	var images = [ 'a', 'b', 'c' ];
	$(function() {
		$('#qMainImageDiv').addClass(
				images[Math.floor(Math.random() * images.length)]);
	});
</script>
</head>

<body class="edu">

	<jsp:include page="./common/header.jsp" flush="false">
		<jsp:param value="main" name="mainHeader" />
		<jsp:param value="main" name="subHeader" />
	</jsp:include>
		<div id="qMainImageDiv" class="visual">
			<!-- 작업자메모 / a b c 계속 롤링되게 -->
			<div></div>

		</div>
		<div style="text-align:right">
		출처 : 강영옥외, 2017, "공간정보 융복합 교육지원을 위한 수업모듈개발" 국토지리정보원
		</div>
	<div id="contentsWrap">
	
		<div class="mainContents">
			<div class="classInfo">
				<h3 class="title edge">수업소개</h3>
				<ul>
					<li class="noise"
						onClick="location.href='<%=contextPath%>/introduce/1'"><span>우리지역
							소음지도</span></li>
					<li class="gps"
						onClick="location.href='<%=contextPath%>/introduce/2'"><span>GPS활용
							위치학습</span></li>
					<li class="population"
						onClick="location.href='<%=contextPath%>/introduce/3'"><span>우리지역
							인구지도</span></li>
					<li class="territory"
						onClick="location.href='<%=contextPath%>/introduce/4'"><span>통합적
							영토교육</span></li>
					<li class="school"
						onClick="location.href='<%=contextPath%>/introduce/5'"><span>우리학교운동장
							생태지도</span></li>
					<li class="map"
						onClick="location.href='<%=contextPath%>/introduce/6'"><span>지도
							정확성</span></li>
					<li class="dokdo"
						onClick="location.href='<%=contextPath%>/introduce/7'"><span>독도의
							중요성</span></li>
				</ul>
			</div>
			<!-- END 수업소개 -->

			<ul class="quick">
				<li class="attend" onClick="location.href='<%=contextPath%>/course'"><span>수업참여(로그인)</span></li>
				<li class="gallery"
					onClick="location.href='<%=contextPath%>/gallery'"><span>수업활동갤러리</span></li>
				<li class="qna"
					onClick="location.href='<%=contextPath%>/surport/faq'"><span>자주묻는질문</span></li>
			</ul>
			<!-- END 바로가기 -->

			<div class="board notice">
				<h3 class="title edge">공지사항</h3>
				<button type="button" class="go" title="바로가기"
					onClick="location.href='<%=contextPath%>/surport/notice'">바로가기</button>

				<ul>
					<c:forEach var="bbsNotice" items="${noticeItems}">
						<li><a
							href="<%=contextPath%>/surport/noticeView/${bbsNotice.idx}">${bbsNotice.title}</a></li>
					</c:forEach>
				</ul>
				<!-- 
				<ul>
					<li>한은 "대출금리 올라도 가계 끄떡없다"…</li>
					<li>국정농단 시작과 끝' 최순실 재판 휴정…구형</li>
					<li>우병우 세번째 영장심사 출석…사찰 의혹에</li>
					<li>빛바랜 美틸러슨 '대화 초청장'…北 탐색기</li>
					<li>정규-비정규직 실제 임금격차 23만원…"</li>
				</ul>
				 -->
			</div>
			<!-- END 공지사항 -->

			<div class="board faq">
				<h3 class="title edge">묻고 답하기</h3>

				<button type="button" class="go" title="바로가기"
					onClick="location.href='<%=contextPath%>/surport/qna'">바로가기</button>

				<ul>
					<c:forEach var="bbsQuestion" items="${qnaItems}">
						<li class="ellipsis"><a
							href="<%=contextPath%>/surport/qnaView/${bbsQuestion.idx}">${bbsQuestion.title}</a></li>
					</c:forEach>
				</ul>
				<!-- 
				<ul>
					<li>한은 "대출금리 올라도 가계 끄떡없다"…</li>
					<li>국정농단 시작과 끝' 최순실 재판 휴정…구형</li>
					<li>우병우 세번째 영장심사 출석…사찰 의혹에</li>
					<li>빛바랜 美틸러슨 '대화 초청장'…北 탐색기</li>
					<li>정규-비정규직 실제 임금격차 23만원…"</li>
				</ul>
				 -->
			</div>
			<!-- END 자주묻는질문 -->
		</div>
		<!-- CONTENTS -->
	</div>
	<!-- END CONTENTSWRAP -->
	<jsp:include page="./common/footer.jsp"></jsp:include>
	<!-- END FOOTER -->
</body>
</html>
