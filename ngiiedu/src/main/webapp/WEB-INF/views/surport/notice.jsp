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
		<h3 class="edge">공지사항</h3>
		
		<div class="boardList">
			<table>
				<thead>
					<th>내용</th>
					<th>등록일</th>
				</thead>
				<tbody>		
					<c:forEach var="bbsNotice" items="${items}"> 
						<tr>
							<td><a href="noticeView/${bbsNotice.idx}">${bbsNotice.title}</a> 
							</td>
							<td><fmt:formatDate value="${bbsNotice.createDate}" pattern="YYYY-MM-dd" /></td>
						</tr>
					</c:forEach>
					<!-- 		
					<tr onclick="document.location = '<%=contextPath %>/surport/noticeView/1'">
						<td>한은 "대출금리 올라도 가계 끄떡없다"…금리인상 포석</td>
						<td>2017-12-01</td>
					</tr>
					<tr onclick="document.location = '<%=contextPath %>/surport//2'">
						<td>'국정농단 시작과 끝' 최순실 재판 휴정…구형은 오후에</td>
						<td>2017-12-01</td>
					</tr>
					<tr onclick="document.location = '<%=contextPath %>/surport/noticeView/3'">
						<td>우병우 세번째 영장심사 출석…사찰 의혹에 "통상업무"</td>
						<td>2017-12-01</td>
					</tr>
					<tr onclick="document.location = '<%=contextPath %>/surport/noticeView/4'">
						<td>빛바랜 美틸러슨 '대화 초청장'…北 탐색기 길어질듯</td>
						<td>2017-12-01</td>
					</tr>
					<tr>
						<td>한경연, 정규-비정규직 실제 임금격차 23만원…"증가추세 아냐"</td>
						<td>2017-12-01</td>
					</tr>
					<tr>
						<td>한중정상회담 왜 확대→소규모 순서로 진행되나…</td>
						<td>2017-12-01</td>
					</tr>
					<tr>
						<td>문 대통령, 오늘 시진핑 주석과 정상회담…관계복원·북핵 협의</td>
						<td>2017-12-01</td>
					</tr>
					<tr>
						<td>文대통령, 방중계기 시진핑과 세번째 정상회담…3대 관전포인트</td>
						<td>2017-12-01</td>
					</tr>
					<tr>
						<td>문 대통령, 베이징 서민식당 깜짝방문…빵·두유로 아침식사</td>
						<td>2017-12-01</td>
					</tr>
					<tr>
						<td>시진핑 '사드', 文대통령 '원유 중단' 직접 언급할까포토</td>
						<td>2017-12-01</td>
					</tr>
					 -->
				</tbody>
			</table>
			
			<c:choose>
				<c:when test="${criteria.recordsNum ne NULL and criteria.recordsNum ne '' and criteria.recordsNum ne 0}">				
					<ul class="pagination">
						<c:if test="${criteria.currentPage gt 10}">  										
						</c:if>
						<c:if test="${criteria.currentPage gt 1}">  										
							<li class="ico forward" title="앞으로"  onclick="location.href='notice?page=${criteria.currentPage - 1}'">앞으로</li>
						</c:if>
							<c:forEach var="i" begin="${criteria.startPage}" end="${criteria.endPage}" step="1">
				            <c:choose>
				                <c:when test="${i eq criteria.currentPage}"> 
				                      <li class="active"><a href="notice?page=${i}">${i}</a></li>
				                </c:when>
				                	<c:otherwise>
				                    <li><a href="notice?page=${i}">${i}</a></li> 
									</c:otherwise>
								</c:choose>
							</c:forEach>
						<c:if test="${criteria.currentPage < criteria.finalPage}"> 
							<li class="ico back" title="뒤로" onclick="location.href='notice?page=${criteria.currentPage + 1}'">뒤로</li>
						</c:if> 
					</ul>
				</c:when>
			</c:choose>
			<!--
			<ul class="pagination">
				<li class="ico first" title="맨앞으로">처음</li>
				<li class="ico forward" title="앞으로">앞으로</li>
				<li>1</li>
				<li>2</li>
				<li>3</li>
				<li class="on">4</li>
				<li>5</li>
				<li>6</li>
				<li class="ico back" title="뒤로">뒤로</li>
				<li class="ico end" title="맨뒤로">마지막</li>
			</ul>
			-->
			
			<c:if test="${'ADM' eq bbsrole or 'USR' eq bbsrole}">
				<button class="new" onclick="document.location = '<%=contextPath %>/surport/noticeNew'">새글</button>
			</c:if>
		</div>
		<!-- END NOTICELIST -->
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->
</body>
</html>
