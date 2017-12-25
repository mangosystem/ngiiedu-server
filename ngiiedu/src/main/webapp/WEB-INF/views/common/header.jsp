<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.go.ngii.edu.main.users.model.User"%>
<%
	String contextPath = request.getContextPath();

	User user = (User)session.getAttribute("USER_INFO");
	
	String userId = null;
	String userEmail = null;
	String userName = null;
	String userDivision = null;
	int userIdx = -1;

	
	if (user != null) {
		userId =user.getUserid();
		userEmail =user.getUserEmail();
		userName =user.getUserName();
		userDivision =user.getUserDivision();
		
		userIdx =user.getIdx();
	}
	
	//헤더 매뉴 결정 파라미터
	String mainHeader = request.getParameter("mainHeader"); 
	String subHeader= request.getParameter("subHeader");
%>
	

	
<html lang="ko">
<head>
<meta charset="utf-8">
<!-- 
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
 -->
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/assets/dist/style.css" />
<!--jquery  -->
	<script type="text/javascript" src="<%=contextPath%>/assets/cdn/jquery/jquery-3.2.1.min.js"></script>
	<script src="<%=contextPath%>/assets/cdn/jquery-ui-1.12.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="<%=contextPath%>/assets/cdn/jquery-ui-1.12.1/jquery-ui.css">


<script>
	var mainHeader = "<%=mainHeader%>";
	var subHeader = "<%=subHeader%>";

	$(function(){
		$('#'+mainHeader).addClass("on");
		console.log('addclass')
		if(subHeader != null){
			$('#'+subHeader).addClass("on");	
		}
	});
	console.log(<%=userDivision%>)
	console.log("<%=userDivision.equals(new String("1"))%> ")
	console.log("<%=(userDivision).equals("1")%> ")
	console.log("<%=userDivision=="1"%>")
	console.log("<%=userDivision.getClass()%>")
	console.log("<%=userDivision.getClass().getName()%>")
	
	
	console.log("<%="1".equals(userDivision)%>")
</script>

</head>

<body class="edu">
	<div id="headerWrap">
		<div class="header">
			<h1 class="edge">공간정보융합 활용지원정보</h1>
			
			<%if((userDivision+"").equals("1")){  //교사 %>
				<div class="gnb" style="display: none;">
					<span class="student"><%=userName %></span>님, 로그인하셨습니다.
					<button type="button" title="로그아웃">로그아웃</button>
				</div>
			<%}else if(userDivision.equals("2")){ //학생 %>
				<div class="gnb" style="display: none;">
					<span class="student"><%=userName %></span>님, 로그인하셨습니다.
					<button type="button" title="로그아웃">로그아웃</button>
				</div>
			<%}else if(userDivision.equals("3")){  //관리자 %>
				<div class="gnb" style="display: none;">
					<span class="teacher"><%=userName %></span>님, 로그인하셨습니다.
					<button type="button" title="로그아웃">로그아웃</button>
				</div>
			<%}else{ %>
				<ul class="gnb">
					<li>로그인</li>
					<li><%=userDivision %></li>
					<li>회원가입</li>
				</ul>
			<%} %>
		</div>
		<div class="lnbWrap">
			<ul class="lnb">
				<li id="introduce"><a href="<%=contextPath%>/introduce">수업소개</a></li>
				<li id="gallary"><a href="<%=contextPath %>/gallary">수업활동갤러리</a></li>
				<li id="surport"><a href="<%=contextPath %>/surport/notice">사용지원</a></li>
				<li id="course" ><a href="<%=contextPath %>">수업</a></li>
			</ul>
		</div>
		<div class="snbWrap navy">
			<!-- 클래스명 navy, brown -->
			<div class="snb">
			
			<%if(mainHeader.equals("introduce")){ %>
				<h2>수업소개</h2>
			<%} else if (mainHeader.equals("gallary")){ %>
				<h2>수업활동갤러리</h2>
			<%} else if (mainHeader.equals("surport")){ %>
				<h2>사용지원</h2>
				<ul>
					<li id="notice"><a href="<%=contextPath%>/surport/notice">공지사항</a></li>
					<li id="faq"><a href="<%=contextPath%>/surport/faq">자주 묻는 질문</a></li>
					<li id="qna"><a href="<%=contextPath%>/surport/qna">문고답하기</a></li>
					<li id="download"><a href="<%=contextPath%>/surport/download">자료실</a></li>
				</ul>
			<%} else if (mainHeader.equals("course")){ %>			
				<h2>수업</h2>
			<% }%>
			</div>
		</div>
	</div>
	<!-- END HEADER -->


</body>
</html>