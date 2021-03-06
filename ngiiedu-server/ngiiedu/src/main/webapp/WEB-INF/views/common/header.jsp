<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.go.ngii.edu.main.users.model.User"%>
<%
	String contextPath = request.getContextPath();
	User user; 
	String userId = null;
	String userEmail = null;
	String userName = null;
	String userDivision = null;
	int userIdx = -1;
	user = (User)session.getAttribute("USER_INFO");
	
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
	
	<script src="<%=contextPath%>/assets/cdn/jQuery-contextMenu/jquery.contextMenu.min.js"></script>
	<link rel="stylesheet" href="<%=contextPath%>/assets/cdn/jQuery-contextMenu/jquery.contextMenu.min.css">
	
<script>
	var mainHeader = "<%=mainHeader%>";
	var subHeader = "<%=subHeader%>";

	$(function(){
		if ("main" != mainHeader) {
			$('#'+mainHeader).addClass("on");
			//console.log('addclass')
			if(subHeader != null){
				$('#'+subHeader).addClass("on");	
			}
		}
	});
</script>

</head>

<body class="edu">
	<div id="headerWrap">
		<div class="header">
			<h1 class="edge" style="cursor: pointer;" onclick="document.location = '<%=contextPath %>/'">공간정보융합 활용지원시스템</h1>

			<%
				if(userDivision != null) {
					if (userDivision.trim().equals("1")) {  //교사 
			%>
				<div class="gnb">
					<span class="teacher" onclick="location.href='<%=contextPath%>/modifyUserInfo'"><%=userName %></span>님, 로그인하셨습니다.
					<button type="button" title="로그아웃" onClick="location.href='<%=contextPath%>/logout'">로그아웃</button>
				</div>
			<%
					} else if(userDivision.trim().equals("2")) {  //학생
			%>
				<div class="gnb">
					<span class="student" onclick="location.href='<%=contextPath%>/modifyUserInfo'"><%=userName %></span>님, 로그인하셨습니다.
					<button type="button" title="로그아웃" onClick="location.href='<%=contextPath%>/logout'">로그아웃</button>
				</div>
			<%
					} else if(userDivision.trim().equals("3")) {  //관리자 
			%>
				<div class="gnb">
					<span class="admin" onclick="location.href='<%=contextPath%>/cm-admin'"><%=userName %></span>님, 로그인하셨습니다.
					<button type="button" title="로그아웃" onClick="location.href='<%=contextPath%>/logout'">로그아웃</button>
				</div>
			<%
					}
				} else { 
			%>
				<ul class="gnb">
					<li ><a href="<%=contextPath%>/login">로그인</a></li>
					<li><a href="<%=contextPath%>/join">회원가입</a></li>
				</ul>
			<%
					
				}
			%>
		</div>
		<div class="lnbWrap">
			<ul class="lnb">
				<a href="<%=contextPath%>/introduce"><li id="introduce">수업소개</li></a>
				<a href="<%=contextPath %>/gallery"><li id="gallery">수업활동갤러리</li></a>
				<a href="<%=contextPath %>/surport/notice"><li id="surport">사용지원</li></a>
				<a href="<%=contextPath %>/course"><li id="course" >나의수업</li></a>
			</ul>
		</div>
		<%if (!"main".equals(mainHeader)){ %>
			<div class="snbWrap navy">
				<!-- 클래스명 navy, brown -->
				<div class="snb">
				
				<%if(mainHeader.equals("introduce")){ %>
					<h2>수업소개</h2>
				<%} else if (mainHeader.equals("gallery")){ %>
					<h2>수업활동갤러리</h2>
				<%} else if (mainHeader.equals("surport")){ %>
					<h2>사용지원</h2>
					<ul>
						<li id="notice"><a href="<%=contextPath%>/surport/notice">공지사항</a></li>
						<li id="faq"><a href="<%=contextPath%>/surport/faq">자주 묻는 질문</a></li>
						<li id="qna"><a href="<%=contextPath%>/surport/qna">묻고 답하기</a></li>
						<li id="download"><a href="<%=contextPath%>/surport/download">자료실</a></li>
					</ul>
				<%} else if (mainHeader.equals("course")){ %>			
					<h2>수업</h2>
				<%} else if (mainHeader.equals("rule")){%>
					<ul style="margin-right:150px">
						<li id="policy"><a href="<%=contextPath %>/rule/copyrightPolicy">저작권정책</a></li>
						<li id="publicInfo"><a href="<%=contextPath %>/rule/publicInformation">공공데이터 이용정책</a></li>
						<li id="emailNon"><a href="<%=contextPath %>/rule/emailNonCollection">이메일무단수집거부</a></li>
						<li id="openSource"><a href="<%=contextPath %>/rule/openSourceLicense">오픈소스 라이센스</a></li>
					</ul>
				<%} else if (mainHeader.equals("info")) {%>
					<h2>사용자정보변경</h2>
				<%} %>
				</div>
			</div>
		<% }%>
		
	</div>
	<!-- END HEADER -->
</body>
</html>