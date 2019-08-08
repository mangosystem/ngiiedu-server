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
<title>공간정보융합활용지원시스템</title>

<body class="edu">

<div id="footerWrap">
	<div class="footer">
		<div style="position:absolute; top:40px; margin-left:100px;">
			<img src="<%=contextPath %>/assets/images/ft_logo.png"/>
		</div>
		<div style="position:absolute; top:40px; margin-left:950px;">
			<img src="<%=contextPath %>/assets/images/logo_sub.png"/>
		</div>
		<ul>
			<li>
				<a href="<%=contextPath %>/rule/copyrightPolicy">저작권정책</a>
			</li>
			<li>
				<a href="<%=contextPath %>/rule/publicInformation">공공데이터 이용정책</a>
			</li>
			<li>
				<a href="<%=contextPath %>/rule/emailNonCollection">이메일무단수집거부</a>
			</li>
			<li>
				<a href="<%=contextPath %>/rule/openSourceLicense">오픈소스 라이센스</a>
			</li>
		</ul>
		<p style="margin-left:360px;">
           	경기도 수원시 영통구 월드컵로 92 (원천동)  팩스 : 031-210-2644
		</p>
		<p style="margin-left:360px;" class="ft-copyright">Copyright (c) 2015 NGII ALL RIGHTS RESERVED.</p>
	</div>
</div>
<!-- END FOOTER -->
</body>
</html>
