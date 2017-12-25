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
		<li>우리지역 인구지도 만들기</li>
	</ul>
	
	<div class="contents">
		<h3 class="edge">할머니는 백살, 고모는 골드미스, 나는 외동딸! (우리지역 인구지도 만들기)</h3>
		<button type="button" class="goList" title="수업목록">목록</button>
		<ul class="object school"><!-- 이미지변경 CSS명 noise gps population territory school map dokdo -->
			<li>
				<span>주제</span>
				우리나라 시군구별 1인가구, 노인인구, 청소년인구, 외국인 인구등의 변화를 시계열적으로 지도화하고, 변화의 특성을 분석합니다.
			</li>
			<li>
			 #인구문제 #시각화 #통계데이터
			</li>
		</ul>
		
		<h4>1. 학습개요</h4>
		<p class="text">
			시각화 툴을 활용하여 우리 지역의 인구 변화 양상을 파악하고 분석하는 모듈로서 통계청에서 제공하는 인구 및 가구 관련 데이터를 통해 우리나라의 1인 가구, 노인인구, 유아인구, 외국인 수 등 변화 패턴을 분석하고 더 나아가 우리 지역의 특성을 이해하도록 한다. 우리나라 인구변화의 지역별 특성을 논의하고, 수업이 이루어지는 학교의 인구변화 특성은 어떠한지, 노인인구 증가나 유아인구 감소, 1인 가구 증가에 따른 사회적 변화는 어떠한 것이 있는지, 이에 따른 대책은 무엇이 있을 수 있는지 토론한다.
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
							<li>우리나라의 인구 성장 과정과 인구 분포 특징을 파악할 수 있다.</li>
							<li>우리 지역의 인구 구조와 변천 과정을 설명할 수 있다. </li>
							<li> 우리 지역의 인구 문제에 대한 대안을 제시할 수 있다.</li>
						</ul>
					</td>
					<td>
						<ul>
							<li>시각화와 분석 결과를 바탕으로 우리 지역에 관한 애향심을 가진다.</li>
							<li>지역 사회 문제에 관한 지속적인 관심을 가진다.</li>
						</ul>
					</td>
					<td>
						<ul>
							<li>내가 사는 지역에 관한 다양한 인구 데이터와 시각화 툴을 활용하여 지도로 표현할 수 있다.</li>
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
								<span>4학년 사회</span>
								Ⅱ. 사회 변화와 우리 생활<br/> 
								03. 우리나라 인구의 변화
							</li>
						</ul>
					</td>
					<td>
						<!-- 중등교과단원 -->
						<ul>
							<li>
								<span>1학년 사회</span>
								Ⅵ. 인구 변화와 인구 문제
							</li>
						</ul>
					</td>
					<td>
						<!-- 고등교과단원 -->
						<ul>
							<li>
								<span>한국지리</span>
								Ⅷ. 국토의 지속 가능한 발전 <br/>
								01. 인구 현상과 대응 방안
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
					<th>2~3차시<span>컴퓨터실</span></th>
					<th>4차시<span>교실 내 수업</span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>인구 및 가구에 대한 기본개념과 우리나라의 인구문제 학습</td>
					<td>웹 GIS 활용방법을 익히고 우리지역의 인구 및 가구 현황 시각화</td>
					<td>우리지역의 인구 현황과 문제를 발표</td>
				</tr>
			</tbody>
		</table>
		<!-- END 수업과정 -->
		
		<h4>5. 적용사례</h4>
		<div class="case">
			<figure>
				<img src="<%=contextPath %>/assets/images/school_case_3_1.jpg" alt="">
				<figcaption>세종과학예술영재고등학교(2017.04.09)</figcaption>
			</figure>
		</div>
		
		<!-- END 적용사례 -->
		
		
		<h5 class="tip">활용TIP</h5>
		<ul class="tip">
			<li>공공데이타를 시각화하는 방법을 익힌 후에 1인가구, 노인인구, 유아인구, 외국인 인구 등 특성별 조사 및 대책은 팀을 편성하여 분석하도록 하고 팀별 대책을 발표하도록 하면 좋습니다.</li>
			<li>구글 퓨전테이블을 이용한 시각화는 다양한 공공데이타에 활용가능합니다.</li>
		</ul>
		
		<!-- END 우리지역 인구지도 만들기 -->
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->
</body>
</html>
