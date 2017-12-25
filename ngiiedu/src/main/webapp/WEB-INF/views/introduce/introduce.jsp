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
	
	<style>
		.introduceList{
			display:flex;
			justify-content:center;
			flex-wrap: wrap; 
			padding:30px
		}
		
		.introduceList > .introduceItem {
			width:200px;
			height:200px;
			border:1px solid rgb(157,157,157);
			margin:10px;
			text-align:center;
 			border-radius: 15px;
 			position:relative;
 			cursor:pointer;
 		}
 		
 		.introduceList > #item1 {
			background: url(/ngiiedu/assets/images/class_noise.png) no-repeat;
			background-size: 50%;
		    background-position: 50% 30%; 
 		}
 
  		.introduceList > #item2 {
			background: url(/ngiiedu/assets/images/class_gps.png) no-repeat;
			background-size: 50%;
		    background-position: 50% 30%; 
 		}
 		
  		.introduceList > #item3 {
			background: url(/ngiiedu/assets/images/class_population.png) no-repeat;
			background-size: 50%;
		    background-position: 50% 30%; 
 		}
 		
  		.introduceList > #item4 {
			background: url(/ngiiedu/assets/images/class_territory.png) no-repeat;
			background-size: 50%;
		    background-position: 50% 30%; 
 		}
 		
  		.introduceList > #item5 {
			background: url(/ngiiedu/assets/images/class_school.png) no-repeat;
			background-size: 50%;
		    background-position: 50% 30%; 
 		}
 		
  		.introduceList > #item6 {
			background: url(/ngiiedu/assets/images/class_map.png) no-repeat;
			background-size: 50%;
		    background-position: 50% 30%; 
 		}
 		
  		.introduceList > #item7 {
			background: url(/ngiiedu/assets/images/class_dokdo.png) no-repeat;
			background-size: 50%;
		    background-position: 50% 30%; 
 		}
 		
 		
 		
 		.introduceList > .introduceItem > p{
 			position:absolute;
 			bottom:10px;
 			width:100%;
 			text-align:center;
 			font-size:16px;
 		}
	
	</style>
	  
	
	
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
	</ul>
	<div class="contents">
		<h3 class="edge">수업소개</h3>
		<div class="introduceList">
			<div id="item1" class="introduceItem" onClick="location.href='<%=contextPath%>/introduce/1'">
				<p>우리지역 소음지도</p>
			</div>
			<div class="introduceItem" id="item2" onClick="location.href='<%=contextPath%>/introduce/2'">
				<p>GPS활용 위치학습</p>
			</div>
			<div class="introduceItem" id="item3" onClick="location.href='<%=contextPath%>/introduce/3'">
				<p>우리지역 인구지도</p>
			</div>
			<div class="introduceItem" id="item4" onClick="location.href='<%=contextPath%>/introduce/4'">
				<p>통합적 영토교육</p>
			</div>
			<div class="introduceItem" id="item5" onClick="location.href='<%=contextPath%>/introduce/5'">
				<p>우리학교 운동장 생태지도</p>
			</div>
			<div class="introduceItem" id="item6" onClick="location.href='<%=contextPath%>/introduce/6'">
				<p>지도 정확성</p>
			</div>
			<div class="introduceItem" id="item7" onClick="location.href='<%=contextPath%>/introduce/7'">
				<p>독도의 중요성</p>
			</div>
		</div>
		<%-- <ul class="galleryList">
			<li>
				<div></div>
				<img src="<%=contextPath%>/assets/images/@test1.png" alt="" title="">
			</li>
			<li>
				<div></div>
				<img src="<%=contextPath%>/assets/images/@test2.png" alt="" title="">
			</li>
			<li>
				<div></div>
				<img src="<%=contextPath%>/assets/images/@test3.png" alt="" title="">
			</li>
			<li>
				<div></div>
				<img src="<%=contextPath%>/assets/images/@test4.png" alt="" title="">
			</li>
			<li>
				<div></div>
				<img src="<%=contextPath%>/assets/images/@test5.png" alt="" title="">
			</li>
		</ul> --%>
	
		<!-- END GALLERYLIST -->
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->
</body>
</html>
