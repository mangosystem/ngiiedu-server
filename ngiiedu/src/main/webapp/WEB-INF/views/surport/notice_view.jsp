<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<!--jquery  -->
<script type="text/javascript" src="<%=contextPath%>/assets/cdn/jquery/jquery-3.2.1.min.js"></script>
<script src="<%=contextPath%>/assets/cdn/jquery-ui-1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/assets/cdn/jquery-ui-1.12.1/jquery-ui.css">

<script type="text/javascript" src="<%=contextPath%>/assets/dist/request.js"></script>

<title>지리원/공간정보융합 활용지원정보</title>
</head>

<body class="edu">
	
	<jsp:include page ="../common/header.jsp" flush="false">
		<jsp:param value="surport" name="mainHeader"/>
		<jsp:param value="notice" name="subHeader"/>
	</jsp:include>

<div id="contentsWrap">
	<ul class="location">
		<li>홈</li>
		<li>사용지원</li>
		<li>공지사항</li>
	</ul>
	<div class="contents">
		<h3>공지사항</h3>
		<div class="boardView">
			<ul class="title">
				<li>${postItem.title} </li>
				<li><fmt:formatDate value="${postItem.createDate}" pattern="YYYY-MM-dd" /></li>
			</ul>
			<div class="script">
				${postItem.description}
			</div>
		</div>
		<div class="btnBoth">
		
			<c:if test="${'ADM' eq bbsrole}">
				<button type="button" title="수정" class="point left" onclick="location.href='<%=contextPath %>/surport/noticeModify/'+${postItem.idx}" >수정</button>
				<button type="button" title="삭제" class="point left" onclick="submitNoticeDelete(${postItem.idx})">삭제</button>
			</c:if>
			<!-- 
			<button type="button" title="이전" class="default">이전</button>
			<button type="button" title="다음" class="default">다음</button>
			 -->
			<button type="button" title="목록" class="point right"  onclick="document.location = '<%=contextPath %>/surport/notice'">목록</button>
		</div>
		<!-- END NOTICEVIEW -->
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->

<script>
	function submitNoticeDelete(idx) {
		ajaxJson(
			['DELETE', apiSvr + '/board/notice/' +idx+'.json'], 
			null, 
	        function (res) {
	        	var data = res.response.data;
	        	console.log(res);
	        	console.log(data);
	        	location.href="<%=contextPath %>/surport/notice";
	    });
	}
</script>
</body>
</html>
