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
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/assets/dist/style.css" />
</head>

<body class="edu">
	
	<jsp:include page ="../common/header.jsp" flush="false">
		<jsp:param value="surport" name="mainHeader"/>
		<jsp:param value="qna" name="subHeader"/>
	</jsp:include>



<div id="contentsWrap">
	<ul class="location">
		<li>홈</li>
		<li>메뉴</li>
		<li>페이지제목</li>
	</ul>
	<div class="contents">
		<h3 class="edge">묻고 답하기</h3>
		
		<div class="boardList">
			<table>
				<thead>
					<th>내용</th>
					<th>작성자</th>
					<th>등록일</th>
				</thead>
				<tbody>
					<tr>
						<td>
							한은 "대출금리 올라도 가계 끄떡없다"…금리인상 포석
							<span class="comment">3</span>
						</td>
						<td>김하나</td>
						<td>2017-12-01</td>
					</tr>
					<tr>
						<td>'국정농단 시작과 끝' 최순실 재판 휴정…구형은 오후에</td>
						<td>나잘난</td>
						<td>2017-12-01</td>
					</tr>
					<tr>
						<td>
							우병우 세번째 영장심사 출석…사찰 의혹에 "통상업무"
							<span class="comment">7</span>
						</td>
						<td>김고운</td>
						<td>2017-12-01</td>
					</tr>
					<tr>
						<td>빛바랜 美틸러슨 '대화 초청장'…北 탐색기 길어질듯</td>
						<td>박가람</td>
						<td>2017-12-01</td>
					</tr>
					<tr>
						<td>한경연, 정규-비정규직 실제 임금격차 23만원…"증가추세 아냐"</td>
						<td>이새론</td>
						<td>2017-12-01</td>
					</tr>
					<tr>
						<td>
							한중정상회담 왜 확대→소규모 순서로 진행되나
							<span class="comment">3</span>
						</td>
						<td>우슬기</td>
						<td>2017-12-01</td>
					</tr>
					<tr>
						<td>문 대통령, 오늘 시진핑 주석과 정상회담…관계복원·북핵 협의</td>
						<td>한가람</td>
						<td>2017-12-01</td>
					</tr>
					<tr>
						<td>
							文대통령, 방중계기 시진핑과 세번째 정상회담…3대 관전포인트
							<span class="comment">1</span>
						</td>
						<td>한비야</td>
						<td>2017-12-01</td>
					</tr>
					<tr>
						<td>문 대통령, 베이징 서민식당 깜짝방문…빵·두유로 아침식사</td>
						<td>신민오</td>
						<td>2017-12-01</td>
					</tr>
					<tr>
						<td>
							시진핑 '사드', 文대통령 '원유 중단' 직접 언급할까포토
							<span class="comment">6</span>
						</td>
						<td>공효상</td>
						<td>2017-12-01</td>
					</tr>
				</tbody>
			</table>
			<ul class="pagination">
				<li class="ico first" title="맨앞으로">처음</li>
				<li class="ico forward" title="앞으로">앞으로</li>
				<li>1</li>
				<li>2</li>
				<li>3</li>
				<li class="on">4</li>
				<li>5</li>
				<li>6</li>
				<li class="ico back" title="뒤로">뒤로</li>
				<li class="ico end" title="맨뒤로">마지막</li>
			</ul>
			<button class="new">새글</button>
		</div>
		<!-- END QNALIST -->
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->

	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->
</body>
</html>