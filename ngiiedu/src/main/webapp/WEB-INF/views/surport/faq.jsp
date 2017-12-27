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
		<jsp:param value="faq" name="subHeader"/>
	</jsp:include>


<div id="contentsWrap">
	<ul class="location">
		<li>홈</li>
		<li>사용지원</li>
		<li>자주 묻는 질문</li>
	</ul>
	<div class="contents">
		<h3 class="edge">자주 묻는 질문</h3>
	
		<ul class="faqWrap">
		
			<c:forEach var="bbsFAQuestion" items="${items}"> 
				<li class="qTitle">
					<p>${bbsFAQuestion.title}</p>
					<div>${bbsFAQuestion.description}</div>
				</li>
			</c:forEach>
			<!-- 작업자 메모 / li class="on"일때 내용인 div가 보여짐 -->
			<!--  
			<li class="qTitle">
				<p>쇼핑몰, 상품정보 오류를 신고하려면 어떻게 해야 하나요?</p>
				<div>
					상품 가격비교 페이지의 [정보 수정요청] 또는 검색결과 리스트에 있는 [신고하기] 버튼을 클릭합니다.  
					[정보 수정요청] 또는 [신고] 팝업창에서 네이버 아이디로 로그인 후 오류 내용을 신고해 주시면, 내부적으로 확인 후 빠르게 처리하고 있습니다.
					네이버쇼핑을 이용하다가 상품정보에 오류가 있거나, 네이버쇼핑을 통한 구매과정에서 피해를 입었다면 
					[신고하기]버튼을 통해 각종 오류사항을 신고해 주세요.   
					신고된 쇼핑몰은 신고점수가 부과되어 네이버쇼핑 내 쇼핑몰 등급정책에 반영됩니다.  
				</div>
			</li>
			<li class="qTitle">
				<p>상품정보에 오류가 있거나 구매과정에서 피해를 입었을 경우 어떻게 해야 하나요?</p>
				<div>
					내용
				</div>
			</li>
			<li class="qTitle">
				<p>상품정보에 오류가 있거나 구매과정에서 피해를 입었을 경우 어떻게 해야 하나요?</p>
				<div>
					내용
				</div>
			</li>
			<li class="qTitle">
				<p>상품정보에 오류가 있거나 구매과정에서 피해를 입었을 경우 어떻게 해야 하나요?</p>
				<div>
					내용
				</div>
			</li>
			<li class="qTitle">
				<p>상품정보에 오류가 있거나 구매과정에서 피해를 입었을 경우 어떻게 해야 하나요?</p>
				<div>
					내용
				</div>
			</li>
			<li class="qTitle">
				<p>상품정보에 오류가 있거나 구매과정에서 피해를 입었을 경우 어떻게 해야 하나요?</p>
				<div>
					내용
				</div>
			</li>
			<li class="qTitle">
				<p>상품정보에 오류가 있거나 구매과정에서 피해를 입었을 경우 어떻게 해야 하나요?</p>
				<div>
					내용
				</div>
			</li>
			<li class="qTitle">
				<p>상품정보에 오류가 있거나 구매과정에서 피해를 입었을 경우 어떻게 해야 하나요?</p>
				<div>
					내용
				</div>
			</li>
			<li class="qTitle">
				<p>상품정보에 오류가 있거나 구매과정에서 피해를 입었을 경우 어떻게 해야 하나요?</p>
				<div>
					내용
				</div>
			</li>
			<li class="qTitle">
				<p>상품정보에 오류가 있거나 구매과정에서 피해를 입었을 경우 어떻게 해야 하나요?</p>
				<div>
					내용
				</div>
			</li>
			<li class="qTitle">
				<p>상품정보에 오류가 있거나 구매과정에서 피해를 입었을 경우 어떻게 해야 하나요?</p>
				<div>
					내용
				</div>
			</li>
			
			-->
		</ul>
	<!-- 	<ul class="pagination">
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
		</ul> -->
		<!-- END FAQ -->
		<c:if test="${'ADM' eq bbsrole or 'USR' eq bbsrole}">
			<button class="new" onclick="document.location = '<%=contextPath %>/surport/faqNew'">새글</button>
		</c:if>
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->

<script>
	$(".qTitle").click(function() {
	  if($(this).hasClass('on')){
		  $(this).removeClass('on');
	  }else{
		  $(this).addClass('on');
	  }
	});

</script>
</body>
</html>
