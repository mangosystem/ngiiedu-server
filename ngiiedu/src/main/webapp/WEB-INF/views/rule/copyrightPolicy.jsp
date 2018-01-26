<%@page import="kr.go.ngii.edu.config.LocalResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String apiSvr = LocalResourceBundle.NGIIEDU_API_SERVER;
	String contextPath = request.getContextPath();
%>
	
	
<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="shortcut icon" href="/ngiiedu/assets/images/nlip.ico" type="image/x-icon" />
<meta charset="utf-8">
<!--[if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<!--[if lt IE 10]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<title>공간정보융합활용지원시스템</title>
<!--jQuery  -->
<script type="text/javascript" src="<%=contextPath%>/assets/cdn/jquery/jquery-3.2.1.min.js"></script>
<script src="<%=contextPath%>/assets/cdn/jquery-ui-1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/assets/cdn/jquery-ui-1.12.1/jquery-ui.css">
</head>

<body class="edu">
	
	<jsp:include page ="../common/header.jsp" flush="false">
		<jsp:param value="rule" name="mainHeader"/>
		<jsp:param value="policy" name="subHeader"/>
	</jsp:include>


<div id="contentsWrap">
	<ul class="location">
		<li>홈</li>
		<li>저작권정책</li>
	</ul>
	<div class="contents">
		<h3 class="edge">저작권정책</h3>
		<ul class="galleryList">
			<%-- <li>
				<div></div>
				<img src="<%=contextPath%>/assets/images/@test1.png" alt="" title="">
			</li> --%>
		</ul>
		<p>
			저작권법 제24조의2에 따라 국토지리정보원에서 저작재산권의 전부를 보유한 저작물의 경우에는 별도의 이용 허락 없이 자유이용이 가능합니다.<br/>

			※단, 자유이용이 가능한 자료는 "공공저작물 자용이용허락 표시기준(공공누리, KOGL) 제1유형 공공저작물 자용이용허락 표시기준(공공누리, KOGL) 제1유형<img src="<%=contextPath %>/assets/images/policy.png"/>을 부착하여개방하고 있으므로 공공누리 표시가 부착된 저작물인지를 확인한 이후에 자유 이용하시기 바랍니다.<br/>
			
			자유이용의 경우에는 반드시 저작물의 출처를 구체적으로 표시해야 합니다. 또한 공간정보 등 별도의 법률에 의해 공개가 제한되는 정보를 포함하는 경우 해당 법률에서 규정하는 절차에 의해 이용하여야 합니다.<br/>
			
			공공누리가 부착되지 않은 자료들을 사용하고자 할 경우에는 해당 콘텐츠를 제공하는 담당부서의 담당자와 사전에 협의한 이후에 이용하여 주시기 바랍니다.</p>
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->
</body>
</html>
