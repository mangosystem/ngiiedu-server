<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String contextPath = request.getContextPath();

	pageContext.setAttribute("br", "<br/>");
	pageContext.setAttribute("cn", "\n");
%>
	
<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="shortcut icon" href="<%=contextPath%>/assets/images/nlip.ico" type="image/x-icon" />
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

<!-- font-awesome -->
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/cdn/font-awesome/css/font-awesome.min.css" />


<title>공간정보융합활용지원시스템</title>
</head>

<body class="edu">
	
	<jsp:include page ="../common/header.jsp" flush="false">
		<jsp:param value="surport" name="mainHeader"/>
		<jsp:param value="download" name="subHeader"/>
	</jsp:include>

<div id="contentsWrap">
	<ul class="location">
		<li>홈</li>
		<li>사용지원</li>
		<li>자료실</li>
	</ul>
	<div class="contents">
		<h3>자료실</h3>
		<div class="boardView">
			<ul class="title">
				<li>${postItem.title}</li>
				<li><fmt:formatDate value="${postItem.createDate}" pattern="YYYY-MM-dd" /></li>
			</ul>
			<div class="script">
				${fn:replace(postItem.description, cn, br)}
			</div>
		</div>
		
		<h5>파일 다운로드</h5>
		<div>
			<c:forEach var="bbsDownload" items="${fileItems}"> 
				<a class="fa fa-download" href="#" onclick="submitDownloadFile('${bbsDownload.filePath}')" > ${bbsDownload.fileName} </a>
			</c:forEach>
		</div>
		
		<div class="btnBoth">
			<!-- 
			<button type="button" title="이전" class="default">이전</button>
			<button type="button" title="다음" class="default">다음</button>
			 -->
			 <c:if test="${'ADM' eq bbsrole}">
				<button type="button" title="수정" class="point left" onclick="location.href='<%=contextPath %>/surport/downloadModify/'+${postItem.idx}" >수정</button>
				<button type="button" title="삭제" class="point left" onclick="submitDelete(${postItem.idx})">삭제</button>
			</c:if>
			<button type="button" title="목록" class="point right"  onclick="document.location = '<%=contextPath %>/surport/download'">목록</button>
		</div>
		<!-- END NOTICEVIEW -->
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->


<script>
	function submitDelete(idx) {
		ajaxJson(
			['DELETE', apiSvr + '/board/pds/' +idx+'.json'], 
			null, 
	        function (res) {
	        	var data = res.response.data;
	        	console.log(res);
	        	console.log(data);
	        	location.href="<%=contextPath %>/surport/download";
	    });
	}
	
	function submitDownloadFile(fileId) {
		location.href = apiSvr + '/board/pds/file/'+fileId;
	}
</script>
</body>
</html>
