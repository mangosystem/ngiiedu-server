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
<link rel="shortcut icon" href="/ngiiedu/assets/images/nlip.ico" type="image/x-icon" />
<meta charset="utf-8">
<!--[if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<!--[if lt IE 10]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<title>지리원/공간정보융합 활용지원정보</title>
</head>

<body class="edu">
	
	<jsp:include page ="../common/header.jsp" flush="false">
		<jsp:param value="surport" name="mainHeader"/>
		<jsp:param value="qna" name="subHeader"/>
	</jsp:include>

<div id="contentsWrap">
	<ul class="location">
		<li>홈</li>
		<li>사용지원</li>
		<li>묻고답하기</li>
	</ul>
	<div class="contents">
		<h3 class="edge">묻고 답하기</h3>
		
		<div class="boardList">
			<table>
				<thead>
					<th>내용</th>
					<th>작성자</th>
					<th>등록일</th>
				</thead>
				<tbody>
					<c:forEach var="bbsQuestion" items="${items}"> 
						<tr>
							<td><a href="qnaView/${bbsQuestion.idx}">${bbsQuestion.title}</a> 
								<span class="comment">${bbsQuestion.commentCount}</span>
							</td> 
							<td>${bbsQuestion.writer}</td>
							<td><fmt:formatDate value="${bbsQuestion.createDate}" pattern="YYYY-MM-dd" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<c:choose>
				<c:when test="${criteria.recordsNum ne NULL and criteria.recordsNum ne '' and criteria.recordsNum ne 0}">				
					<ul class="pagination">
						<c:if test="${criteria.currentPage gt 10}">  										
						</c:if>
						<c:if test="${criteria.currentPage gt 1}">  
							<li class="ico forward" title="앞으로"  onclick="location.href='qna?page=${criteria.currentPage - 1}'">앞으로</li>
						</c:if>
							<c:forEach var="i" begin="${criteria.startPage}" end="${criteria.endPage}" step="1">
				            <c:choose>
				                <c:when test="${i eq criteria.currentPage}"> 
				                      <li class="active"><a href="qna?page=${i}">${i}</a></li>
				                </c:when>
				                	<c:otherwise>
				                    <li><a href="qna?page=${i}">${i}</a></li> 
									</c:otherwise>
								</c:choose>
							</c:forEach>
						<c:if test="${criteria.currentPage < criteria.finalPage}"> 
							<li class="ico back" title="뒤로" onclick="location.href='qna?page=${criteria.currentPage + 1}'">뒤로</li>
						</c:if> 
					</ul>
				</c:when>
			</c:choose>
			<c:if test="${'ADM' eq bbsrole or 'USR' eq bbsrole}">
				<button class="new" onclick="document.location = '<%=contextPath %>/surport/qnaNew'">새글</button>
			</c:if>
		</div>
		<!-- END QNALIST -->
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->

</body>
</html>
