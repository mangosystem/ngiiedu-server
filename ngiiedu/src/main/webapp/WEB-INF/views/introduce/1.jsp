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
		<li>우리학교 운동장 생태지도 만들기</li>
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
						</ul>
					</td>
					<td>
						<ul>
							<li>우리 동네에 대한 지속적인 관심을 갖는다.</li>
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
			</thead>
			<tbody>
				<tr>
					<td>
						<!-- 초등교과단원 -->
						<ul>
							<li>
								<span>3학년 사회</span>
								I. 제목샘플
							</li>
						</ul>
					</td>
					<td>
						<!-- 중등교과단원 -->
						<ul>
							<li>
								<span>사회2</span>
								V. 환경 문제와 지속 가능한 환경
							</li>
							<li>
								<span>사회2</span>
								XIV . 현대사회와 사회문제
							</li>
						</ul>
					</td>
					<td>
						<!-- 고등교과단원 -->
						<ul>
							<li>
								<span>사회</span>
								IV. 환경변화와 인간
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
					<th>1차시 <span>교실수업</span></th>
					<th>2~3차시<span>학교운동장</span></th>
					<th>4~5차시<span>컴퓨터실</span></th>
					<th>6차시<span>교실 내 수업</span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>나무의 탄소저감효과 이해하고 계산하기</td>
					<td>스마트 기
					기를 이용하여 우리학교 운동장 생물종 조사하기</td>
					<td>웹 GIS툴을 이용하여 우리학교 운동장 생태지도 그리기</td>
					<td>학교 운동장의 생물다양성과 지속가능한 삶에대해 토의</td>
				</tr>
			</tbody>
		</table>
		<!-- END 수업과정 -->
		
		<h4>5. 적용사례</h4>
		<div class="case">
			<figure>
				<img src="<%=contextPath %>/assets/images/school_case.jpg" alt="">
				<figcaption>강원 경포고등학교 (2017.03.29)</figcaption>
			</figure>
		</div>
		
		<!-- END 적용사례 -->
		
		
		<h5 class="tip">활용TIP</h5>
		<ul class="tip">
			<li>조별과제로 제출하고, 수행평가 시 활용할 수 있습니다!</li>
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
