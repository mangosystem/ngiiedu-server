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

<body class="edu">
	
	<jsp:include page ="../common/header.jsp" flush="false">
		<jsp:param value="introduce" name="mainHeader"/>
		<jsp:param value="introduce" name="subHeader"/>
	</jsp:include>


<div id="contentsWrap">
	<ul class="location">
		<li>홈</li>
		<li>수업소개</li>
		<li>통합적 영토교육</li>
	</ul>
	
	<div class="contents">
		<h3 class="edge">통합적 영토교육</h3>
		<button type="button" class="goList" title="수업목록">목록</button>
		<ul class="object school"><!-- 이미지변경 CSS명 noise gps population territory school map dokdo -->
			<li>
				<span>주제</span>
				우리나라 4극의 위치를 탐색하고, 학교주변의 불편사항을 스마트폰을 활용하여 신고해보는 활동을 진행합니다.
			</li>
			<li>
				 #영토학습 #분쟁지역 #세계분쟁
			</li>
		</ul>
		
		<h4>1. 학습개요</h4>
		<p class="text">
			기존의 영토와 영역의 개념, 통상기선과 직선기선, 배타적 경제수역(EEZ), 12해리 등 수많은 교과서 속 내용에 대한 설명과 암기가 주를 이루는 현행 학교교육에서의 영토교육을 벗어나 실제로 우리 지역의 문제를 조사하고, 자료를 수집하고, 이를 표현하는 과정을 통해 영토로부터의 교육을 체득할 수 있는 경험을 제공한다.
		</p>
		<!-- END 학습개요 -->
		
		<h4>2. 학습목표</h4>
		<table class="aim">
			<thead>
				<th>지식 이해 목표</th>
				<th>가치 태도 목표</th>
				<th>실천 목표</th>
			</thead>
			<tbody>
				<tr>
					<td>
						<ul>
							<li>영토 및 영역의 의미를 파악할 수 있다.</li>
							<li>세계의 분쟁지역을 조사하고, 각 국가 간 분쟁의 원인을 파악할 수 있다.</li>
						</ul>
					</td>
					<td>
						<ul>
							<li>내가 살고 있는 마을을 더 살기 좋은 곳으로 만들기 위해 자라나는 민주시민(citizen to be)의 자세와 지역사회에 대한 관심을 갖는다.</li>
						</ul>
					</td>
					<td>
						<ul>
							<li>우리 지역에 관한 다양한 환경을 공간정보 기술을 활용하여 지도로 표현하고, 개선방안을 모색한다.</li>
						</ul>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- END 학습목표 -->
		
		<h4>3. 관련교과단원</h4>
		<table class="subject">
			<thead>
				<th><span class="first">초등학교</span></th>
				<th></th>
				<th><span class="high">고등학교</span></th>
				
			</thead>
			<tbody>
				<tr>
					<td>
						<!-- 초등교과단원 -->
						<ul>
							<li>
								<span>사회2</span>
								VI. 우리나라의 영토
							</li>
						</ul>
					</td>
					<td>
						<ul>
							<li>
								<span>사회2</span>
								VII . 통일한국과 세계시민의 역할
							</li>
						</ul>
					</td>
					<td>
						<!-- 고등교과단원 -->
						<ul>
							<li>
								<span>사회</span>
								V. 미래를 바라보는 창
							</li>
						</ul>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- END 관련교과단원 -->
		
		<h4>4. 수업과정</h4>
		<table class="lesson">
			<thead>
				<tr>
					<th>1차시 <span>교실 내 수업</span></th>
					<th>2~3차시<span>컴퓨터실,역할놀이</span></th>
					<th>4~5차시<span>컴퓨터실,현장조사</span></th>
					<th>6차시<span>교실 내 수업</span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						영토와 영역에 대한 의미를 알고 <br/>
						지도에 우리나라의 4극을 파악
					</td>
					<td>
						모의 분쟁조정위원회 역할놀이를 통해<br/> 
						세계 영토분쟁지역 파악
					</td>
					<td>컴퓨터를 이용하여 세계영토 분쟁지역을 시각화</td>
					<td>소감을 발표하고 공유</td>
				</tr>
			</tbody>
		</table>
		<!-- END 수업과정 -->
		
		<h4>5. 적용사례</h4>
		<div class="case">
			<figure>
				<img src="<%=contextPath %>/assets/images/school_case_4_1.jpg" alt="">
				<figcaption>강릉 제일고등학교 (2017.03.28)</figcaption>
			</figure>
		</div>
		
		<!-- END 적용사례 -->
		
		<!-- END 우리지역 소음지도 만들기 -->
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->
</body>
</html>
