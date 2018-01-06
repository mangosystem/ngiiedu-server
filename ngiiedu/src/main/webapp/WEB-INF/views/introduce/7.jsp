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
		<jsp:param value="introduce" name="mainHeader"/>
		<jsp:param value="introduce" name="subHeader"/>
	</jsp:include>


<div id="contentsWrap">
	<ul class="location">
		<li>홈</li>
		<li>수업소개</li>
		<li>독도의 중요성</li>
	</ul>
	
	<div class="contents">
		<h3 class="edge">내 땅이 네 땅이냐, 네 땅이 내 땅이냐 (독도의 중요성)</h3>
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
		<ul class="object dokdo"><!-- 이미지변경 CSS명 noise gps population territory school map dokdo -->
			<li>
				<span>주제</span>
				독도의 다양한 표기법과 지점간 거리를 측정해 보는 활동을 진행합니다.
			<li>
				#독도 #거리측정 #영토문제 #인터넷지도
			</li>
		</ul>
		
		<h4>1. 학습개요</h4>
		<p class="text">
			우리나라 영토와 영해 중에서 ‘독도’와 ‘동해’를 사례로 하여 독도와 동해가 과연 세계 지도에서 어떻게 표기되고 있는 지를 비교하고자 한다. 왜 일본은 독도를 자기 땅이라고 하는지, 왜 일본은 동해를 일본해라고 하는지, 한국과 일본을 제외하고 다른 사람들에게 독도는 과연 어떤 이름으로 알려져 있는 지, 동해 바다 이름이 다른 국가 지도에는 어떻게 표기 되고 있는 지 등 인터넷 지도에서는 독도와 동해가 어떻게 표기되고 있는 지를 확인하고자 한다. 한국과 일본 정부는 독도와 동해에 대해 정부 차원에서 홍보 동영상을 만들어 세계를 상대로 홍보를 하고 있다. 우리나라 정부의 홍보 동영상과 일본 정부의 홍보 동영상을 비교하여 서로의 주장에 대한 정당성과 논리를 비교하고자 한다. 이를 통해 어떤 논리와 방식으로 독도와 동해에 대한 우리나라의 정당한 주장을 국제사회에 전달할 수 있는 지를 알아보고자 한다.
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
							<li>영토교육의 지리적 의미와 중요성을 알 수 있다.</li>
							<li>영토와 영해 개념을 알 수 있다.</li>
						</ul>
					</td>
					<td>
						<ul>
							<li>독도와 동해의 영토적 중요성과 가치를 알 수 있다.</li>
							<li>영토교육의 교육적 가치와 중요성을 알 수 있다.</li>
							<li>우리나라 영토로서 독도에 대해 지속적인 관심을 갖는다.</li>
						</ul>
					</td>
					<td>
						<ul>
							<li>영토로서의 독도와 바다이름으로서의 동해의 공통점과 차이점을 파악한다.</li>
							<li>국제사회에서 영토로서의 독도의 중요성을 논리적으로 주장할 수 있다.</li>
							<li>지도와 지명을 활용하여 영토교육을 교육적으로 전달할 수 있다.</li>
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
								<span>5~6학년 사회</span>
								세계 여러 나라의 환경과 생활 모습
							</li>
						</ul>
					</td>
					<td>
						<!-- 중등교과단원 -->
						<ul>
							<li>
								<span>사회</span>
								세계화 시대의 지역화 전략
							</li>
						</ul>
					</td>
					<td>
						<!-- 고등교과단원 -->
						<ul>
							<li>
								<span>동아시아사</span>
								오늘날의 동아시아
							</li>
						</ul>
					</td>
					<td>
						<ul>
							<li>
								<span>세계지리</span>
								세계화와 지역이해
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
					<th>2~4차시<span>컴퓨터실</span></th>
					<th>5~6차시<span>교실 내 수업</span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>영토와 영해의 개념 학습하고 독도와 동해 위치 확인하기</td>
					<td>다양한 지도에 나타나는 동해와 독도의 표기 비교하기</td>
					<td>독도와 동해를 통한 영토교육에 대한 동영상 시청</td>
				</tr>
			</tbody>
		</table>
		<!-- END 수업과정 -->
		
		<h4>5. 적용사례</h4>
		<div class="case">
			<figure>
				<img src="<%=contextPath %>/assets/images/school_case_7_1.jpg" alt="">
				<figcaption>한국교원대 부설 미호 중학교(2017.04.06)</figcaption>
			</figure>
		</div>
		
		<!-- END 적용사례 -->
		
		
		<h5 class="tip">활용TIP</h5>
		<ul class="tip">
			<li>독도 주간에 활용할 수 있는 수업입니다.</li>
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
