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
					<p style=""
					 	<c:if test="${'ADM' eq bbsrole}">
					 		id="ccontext-menu-${bbsFAQuestion.idx}"
					 		class="context-menu btn btn-neutral"
					 		ondblclick="document.location='faqModify/${bbsFAQuestion.idx}'"
					 		value="${bbsFAQuestion.idx}"
					 	</c:if>
					>${bbsFAQuestion.title}</p>
					<div>${bbsFAQuestion.description}</div>
				</li>
			</c:forEach>
			<!-- 작업자 메모 / li class="on"일때 내용인 div가 보여짐 -->
		</ul>
		<!-- END FAQ -->
		<c:if test="${'ADM' eq bbsrole}">
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
	
	$(function() {
        $.contextMenu({
            selector: '.context-menu', 
            callback: function(itemKey, opt) {
            	
            	var targetId = this[0].getAttribute("value");
            	if ("수정"==itemKey) {
            		console.log("수정");
            		var cf = confirm("수정 화면으로 이동 하시겠습니까?");
            		if (cf) {
            			location.href="<%=contextPath %>/surport/faqModify/"+targetId;
            		} else {
            			
            		}
            	} else if ("삭제"==itemKey){
            		var cf = confirm("정말 삭제 하시겠습니까?");
            		if (cf) {
            			ajaxJson(
            				['DELETE', apiSvr + '/board/faq/'+targetId+'.json'], 
            				null, 
            		        function (res) {
            		        	console.log(res);
            		        	location.href="<%=contextPath %>/surport/faq";
            		    });
            		} else {
            			
            		}
            	}
            },
            items: {
                "수정": {name: "Edit", icon: "edit"},
                "삭제": {name: "Delete", icon: "delete"}
            }
        }); 
    });

</script>
</body>
</html>
