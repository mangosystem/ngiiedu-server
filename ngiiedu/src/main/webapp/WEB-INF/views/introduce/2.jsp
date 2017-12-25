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
		<li>GPS를 이용한 위치학습기</li>
	</ul>
	
	<div class="contents">
		<h3 class="edge">내가 있는 곳은 어디? (GPS를 이용한 위치학습기)</h3>
		<button type="button" class="goList" title="수업목록">목록</button>
		<ul class="object school"><!-- 이미지변경 CSS명 noise gps population territory school map dokdo -->
			<li>
				<span>주제</span>
				운동장에서 스마트폰의 위치측정 어플을 이용하여 경위도 값을 측정하고, 경위도의 변화를 탐구해 봅니다.
			</li>
			<li>
				#경위도 #GPS 측정앱 #지역변화 #인터넷지도
			</li>
		</ul>
		
		<h4>1. 학습개요</h4>
		<p class="text">
			위치를 나타내는데 있어 경위도는 매우 기본적인 것이지만 학생들은 매우 어려워하는 개념입니다. 학교 운동장내 주요지점의 경위도를 측정해보고 경위도값이 어떻게 달라지는 지를 확인해 보면서 경위도의 개념와 위치를 표시하는 방법에 대해 학습해 봅니다. 학교 운동장에서 여러 지점을 측정해도 경위도 상에서는 값들이 크게 변하지 않으므로 우리나라의 대표적 지점, 전세계 잘 알려진 지점을 인터넷 지도에 표시하도록 하고 경위도가 어떻게 달라지는지 학습하도록 합니다.
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
							<li>학교 주요 지점들의 위치에 대한 경위도를 읽을 수 있다.</li>
							<li>내가 사는 지역이 어떻게 변화하였는지 설명할 수 있다.</li>
						</ul>
					</td>
					<td>
						<ul>
							<li>우리 지역에 지속적인 관심을 갖는다.</li>
						</ul>
					</td>
					<td>
						<ul>
							<li>인터넷 상의 지도 데이터를 다운로드 하여 사용할 수 있다.</li>
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
								Ⅳ. 우리나라의 지역 이해
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
					<th>2차시<span>현장활동</span></th>
					<th>3~4차시<span>컴퓨터실</span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>위치 및 경위도의 개념 학습 후 포털사이트에서 경위도 읽어보기</td>
					<td>모바일 기기를 활용하여 학교 주변의 경위도 좌표를 읽어보기</td>
					<td>3~4차시 (컴퓨터실)국토지리원 웹사이트를 통해 우리지역의 변화상을 살펴보기</td>
				</tr>
			</tbody>
		</table>
		<!-- END 수업과정 -->
		
		<h4>5. 적용사례</h4>
		<div class="case">
			<figure>
				<img src="<%=contextPath %>/assets/images/school_case_2_1.jpg" alt="">
				<figcaption>서울 거여초등학교 (2017.06.09)</figcaption>
			</figure>
		</div>
		
		<!-- END 적용사례 -->
		
		
		<h5 class="tip">활용TIP</h5>
		<ul class="tip">
			<li>GPS를 측정하는 어플을 이용하여 학교내 주요지점의 위치를 경위도로 측정합니다.</li>
			<li>학교내에서는 GPS값이 많이 바뀌지 않으므로 컴퓨터실에서 우리나라 주요위치, 세계 주요지점을 나타내도록 하여 경위도의 변화를 토론하도록 합니다.</li>
			<li>국토지리정보원의 항공사진 서비스 사이트를 통해 우리지역이 과거 어떠한 모습이었는지도 학습하고, 변화상을 토론할 수 있습니다.</li>
		</ul>
		
		<!-- END 내가 있는 곳은 어디? (GPS를 이용한 위치학습기)-->
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->
</body>
</html>
