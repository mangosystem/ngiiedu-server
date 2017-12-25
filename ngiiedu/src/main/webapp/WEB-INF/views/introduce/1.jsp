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
		<li>우리지역 소음지도 만들기</li>
	</ul>
	
	<div class="contents">
		<h3 class="edge">시끄러워도(圖), 우리동네! (우리지역 소음지도 만들기)</h3>
		<button type="button" class="goList" title="수업목록">목록</button>
		<ul class="object school"><!-- 이미지변경 CSS명 noise gps population territory school map dokdo -->
			<li>
				<span>주제</span>
				스마트폰 어플을 사용하여 학교주변의 소음을 측정하고, 각 반별로 소음지도를 작성하여 소음원인, 체감소음정도를 확인하고 저감대책을 토의해봅니다.			</li>
			<li>
				#야외수업 #스마트폰 #소음측정앱 #스토리맵
			</li>
		</ul>
		
		<h4>1. 학습개요</h4>
		<p class="text">
			학교 운동장은 학생들이 학교생활을 하면서 가장 많은 시간을 보내는 공간 중 하나이다. 최근 기후변화가 전 지구적인 스케일에서 주요한 이슈로 떠오르면서 숲가꾸기의 중요성이 강조되고 있다. 학교운동장 주변 “학교숲”은 우리 생활 주변에서 가장 손쉽게 식물을 탐구할 수 있는 장소이다. 이 수업을 통해 학생들은 생물다양성에 대한 올바른 인식을 갖고, 학교 숲 생태와 환경의 가치, 지속가능한 삶을 생각해 볼 수 있다.
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
							<li>내가 사는 동네의 특징을 파악할 수 있다.</li>
							<li>소음 지역과 소음원에 대해 파악할 수 있다.</li>
						</ul>
					</td>
					<td>
						<ul>
							<li>우리 동네에 대한 소음에 대한 의식을 갖는다.</li>
							<li>우리 동네에 대한 지속적인 관심을 갖는다.</li>
						</ul>
					</td>
					<td>
						<ul>
							<li>우리 지역에 관한 정보를 공간정보기술을 활용하여 지도로 표현할 수 있다.</li>
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
								<span>사회</span>
								Ⅵ. 도시 발달과 도시 문제
							</li>
						</ul>
					</td>
					<td>
						<!-- 고등교과단원 -->
						<ul>
							<li>
								<span>1학년 사회</span>
								IV. 환경변화와 인간<br/>
								2. 공간정보기술의 변화와 일상생활
							</li>
						</ul>
					</td>
					<td>
						<ul>
							<li>
								<span>한국지리</span>
								Ⅵ. 지역 조사와 지리 정보 처리<br/>
								2. 지리 정보와 지역조사
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
					<th>2~3차시<span>야외수업</span></th>
					<th>4~5차시<span>컴퓨터실</span></th>
					<th>6차시<span>교실 내 수업</span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>소음 및 소음지도 개념을 논의하고 
					우리동네 소음원에 대해 토의</td>
					<td>모바일 기기를 활용한 우리 지역 소음원 현장 조사</td>
					<td>컴퓨터를 이용한 주제지도 및 스토리맵 작성하기</td>
					<td>팀별 소음지도 및 소음저감대책 등 발표</td>
				</tr>
			</tbody>
		</table>
		<!-- END 수업과정 -->
		
		<h4>5. 적용사례</h4>
		<div class="case">
			<figure>
				<img src="<%=contextPath %>/assets/images/school_case_1_1.jpg" alt="">
				<figcaption>서울 명일여고(2017.03.27)</figcaption>
			</figure>
			<figure>
				<img src="<%=contextPath %>/assets/images/school_case_1_2.jpg" alt="">
				<figcaption>세종과학예술영재고등학교 (2017.05.19)</figcaption>
			</figure>
		</div>
		
		<!-- END 적용사례 -->
		
		
		<h5 class="tip">활용TIP</h5>
		<ul class="tip">
			<li>스마트폰을 이용하여 소음측정 및 지점별 소음값 입력등이 이뤄지므로 팀별로 조사하도록 하는 것이 좋습니다.</li>
			<li>현장조사시 일정한 간격으로 소음을 측정하도록 하고, 값의 변화가 작아도 입력하도록 합니다.</li>
		</ul>
		
		<!-- END 우리지역 소음지도 만들기 -->
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->
</body>
</html>
