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
		<li>지도 정확성</li>
	</ul>
	
	<div class="contents">
		<h3 class="edge">지도는 왜 거짓말을 할까? (지도 정확성)</h3>
		<button type="button" class="goList classList" title="수업목록">
			목록
			<ul class="sub-menu">
				<li><a href="<%=contextPath%>/introduce/1" class="sub-menu-item">우리지역 소음지도 만들기</a></li>
				<li><a href="<%=contextPath%>/introduce/2" class="sub-menu-item">GPS를 이용한 위치학습기</a></li>
				<li><a href="<%=contextPath%>/introduce/3" class="sub-menu-item">우리지역 인구지도 만들기</a></li>
				<li><a href="<%=contextPath%>/introduce/4" class="sub-menu-item">통합적 영토교육</a></li>
				<li><a href="<%=contextPath%>/introduce/5" class="sub-menu-item">우리학교 운동장 생태지도 만들기</a></li>
				<li><a href="<%=contextPath%>/introduce/6" class="sub-menu-item">지도 정확성</a></li>
				<li><a href="<%=contextPath%>/introduce/7" class="sub-menu-item">독도의 중요성</a></li>
			</ul>
		</button>
		<ul class="object map"><!-- 이미지변경 CSS명 noise gps population territory school map dokdo -->
			<li>
				<span>주제</span>
				우리나라의 포털 사이트 지도, 다른 나라의 포털 사이트 지도에 지명이 어떻게 다양하게 표현되는지 탐구해봅니다.
				</li>
			<li>
				#지도정확성 #인터넷지도 #지명
			</li>
		</ul>
		
		<h4>1. 학습개요</h4>
		<p class="text">
			지도는 정확한가? 지도는 정확해야 하는가? 아니면 지도는 정확할 것인가? 현대 사회에서 지도는 아주 많은 용도로 사용되고 있다. 스마트폰에서 길찾기와 맞집 검색, 여행지 정보를 볼 때 자주 이용되고 수업시간에 사회과부도와 지리부도에서 다양한 지도를 볼 수 있다. 지도의 올바른 기능과 특성을 파악함으로서 지리적 지식의 비판적 사고력을 기를 수 있다. 본 수업은 종이지도와 인터넷상의 전자지도를 비교함으로서 지도에 담긴 정보의 정확성에 대해 다시 한번 생각해 보는 기회를 갖고자 한다.
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
							<li>종이지도와 전자지도에 대해 이해할 수 있다.</li>
							<li>내가 사는 동네와 지역의 지리정보를 인터넷 지도에서 비교해 볼 수 있다.</li>
							<li>내가 사는 동네와 지역의 지도정보를 파악할 수 있다.</li>
						</ul>
					</td>
					<td>
						<ul>
							<li>우리 생활 주변에서 활용되는 다양한 지도에 대해 지속적인 관심을 갖는다.</li>
							<li>앞으로 우리 생활에 필요한 지도와 지도 정보에 대해 지속적인 관심을 갖는다.</li>
						</ul>
					</td>
					<td>
						<ul>
							<li>현대사회에서 지도를 바라보는 시각과 지도의 정확성에 대해 자신만의 시각을 가진다.</li>
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
				<th><span class="middle">중학교</span></th>
				<th><span class="high">고등학교</span></th>
				<th></th>
			</thead>
			<tbody>
				<tr>
					<td>
						<!-- 초등교과단원 -->
						<ul>
							<li>
								<span>3학년 사회</span>
								Ⅰ. 우리가 살아가는 곳
							</li>
						</ul>
					</td>
					<td>
						<!-- 중등교과단원 -->
						<ul>
							<li>
								<span>1학년 사회</span>
								Ⅰ. 내가 사는 세계
							</li>
						</ul>
					</td>
					<td>
						<!-- 고등교과단원 -->
						<ul>
							<li>
								<span>한국지리</span>
								VI. 지역조사와 지리정보 처리
							</li>
						</ul>
					</td>
					<td>
						<ul>
							<li>
								<span>세계지리</span>
								I. 세계화와 지역 이해
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
					<th>2차시<span>학교 운동장</span></th>
					<th>3~4차시<span>컴퓨터실</span></th>
					<th>5~6차시<span>교실 내 수업</span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>지도의 개념, 지도의 요소 학습</td>
					<td>다양한 지도의 유형과 차이점에 대해 학습</td>
					<td>인터넷 포털 지도를 비교하여 지도의 정확성을 탐색</td>
					<td>지도의 정확성 개념 파악 및 정보의 가치 파악하기</td>
				</tr>
			</tbody>
		</table>
		<!-- END 수업과정 -->
		
		<h4>5. 적용사례</h4>
		<div class="case">
			<figure>
				<img src="<%=contextPath %>/assets/images/school_case_6_1.jpg" alt="">
				<figcaption>한국교원대 부설 월곡 초등학교 (2017.03.28)</figcaption>
			</figure>
		</div>
		
		<!-- END 적용사례 -->
		
		
		<h5 class="tip">활용TIP</h5>
		<ul class="tip">
			<li>교실내에서 스마트패드를 이용하여 위치를 검색할 수 있지만 인터넷 속도가 느리면 문제가 될 수 있습니다. 인터넷 속도 확인이 필요합니다.</li>
		</ul>
		
		<div class='source' >
			출처 : 강영옥외, 2017, "공간정보 융복합 교육지원을 위한 수업모듈 개발" 국토지리정보원
		</div>
		
		<!-- END 우리지역 소음지도 만들기 -->
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->
</body>
</html>

