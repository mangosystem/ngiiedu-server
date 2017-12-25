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
		<div class="boardView">
			<ul class="title">
				<li>월성 1호기, 내년 조기폐쇄…8차 전력수급계획, 환경·수요관리 중심</li>
				<li>한비야</li>
				<li>2017-12-12</li>
			</ul>
			<div class="script">
				월성 1호기, 내년 조기폐쇄…8차 전력수급계획, 환경·수요관리 중심 
				석탄발전 4기, 추가로 LNG 전환…태양광·풍력 설비도 대폭 확대
				정부의 탈원전·탈석탄 정책을 뒷받침할 에너지 분야 청사진이 14일 공개됐다.
				산업통상자원부는 향후 15년간의 에너지 수급 전망과 설비 계획을 담은 '제8차 전력수급기본계획'(안, 2017~2031년)을 마련해 국회 산업통상자원중소벤처기업위원회 통상에너지 소위원회에 보고했다.
				전력수급계획은 정부가 2년 단위로 발표한다. 이번 계획은 국회 산업위 전체 회의 보고, 공청회(26일) 등을 거쳐 전력정책심의회에서 최종 확정된다.
				8차 계획의 골자는 원전·석탄발전의 단계적 감축과 재생에너지, 액화천연가스(LNG)발전 확대다. 문재인 정부 에너지전환 정책의 핵심이 담긴 셈이다.
				과거 수급계획이 수급 안정과 경제성에 초점을 맞췄다면 8차에는 환경성이 대폭 반영됐다. 
				경제성에 맞춰 발전기를 가동(급전)하던 국내 전력체계에 환경 관련 변수가 새롭게 추가된다. 발전단가가 높다는 이유로 석탄발전에 밀렸던 친환경 액화천연가스(LNG) 발전의 가동률을 높이기 위함이다.
			</div>
			<div class="commentWrap">
				<ul>
					<li>
						<span class="writer">한겨례</span>
						<span class="date">2017-12-12</span>
					</li>
					<li>
						산업통상자원부는 향후 15년간의 에너지 수급 전망과 설비 계획을 담은 '제8차 전력수급기본계획'(안, 2017~2031년)을 마련해 국회 산업통상자원중소벤처기업위원회 통상에너지 소위원회에 보고했다.
					</li>
				</ul>
				<ul>
					<li>
						<span class="writer">한마디</span>
						<span class="date">2017-12-12</span>
					</li>
					<li>
						산업통상자원부는 향후 15년간의 에너지 수급 전망과 설비 계획을 담은 '제8차 전력수급기본계획'(안, 2017~2031년)을 마련해 국회 산업통상자원중소벤처기업위원회 통상에너지 소위원회에 보고했다.
					</li>
				</ul>
				<textarea name="" id="" cols="30" rows="5" class="comment"></textarea>
				<button type="button" title="이전" class="subbtn">댓글입력</button>
			</div>
		</div>
		<div class="btnBoth">
			<button type="button" title="이전" class="default">이전</button>
			<button type="button" title="다음" class="default">다음</button>
			<button type="button" title="목록" class="point right" onclick="document.location = '<%=contextPath %>/surport/qna'">
				목록
			</button>
		</div>					
		
		<!-- END QNAVIEW -->
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->
</body>
</html>
