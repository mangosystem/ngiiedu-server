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
		<jsp:param value="qna" name="subHeader"/>
	</jsp:include>
	
<div id="contentsWrap">
	<ul class="location">
		<li>홈</li>
		<li>사용지원</li>
		<li>묻고 답하기</li>
	</ul>
	<div class="contents">
		<h3>묻고 답하기</h3>
		<div class="boardView">
		
			<ul class="title">
				<li>${postItem.title}</li>
				<li>${postItem.writer}</li>
				<li><fmt:formatDate value="${postItem.createDate}" pattern="YYYY-MM-dd" /></li>
			</ul>
			<div class="script">
				${postItem.description}
			</div>
			
			<div class="commentWrap">
				<c:forEach var="bbsReply" items="${reItems}"> 
					<ul>
						<li>
							<span class="writer">${bbsReply.writer}</span>
							<!--  style 수정필요 -->
							<span style="display:inline-block; width:630px"><fmt:formatDate value="${bbsReply.createDate}" pattern="YYYY-MM-dd" /></span>
							<c:if test="${'ADM' eq bbsrole}">
								<span style="cursor:pointer;" onClick="qnaReModify(${bbsReply.idx}, ${postItem.idx}, '${bbsReply.description}')">edit</span>
								<span style="color:white;"> | </span>
								<span style="color:red; font-weight:bpld; cursor:pointer;" onClick="qnaReDelete(${bbsReply.idx},${postItem.idx})">x</span>
							</c:if>
						</li>
						<li>
							<div id="qEditDescriptionDiv${bbsReply.idx}">
								${bbsReply.description}
							</div>
						</li>
					</ul>
				</c:forEach>
				<c:if test="${'ADM' eq bbsrole}">
					<textarea name="" id="qDescription" cols="30" rows="5" class="comment"></textarea>
					<button type="button" title="이전" class="subbtn" onclick="qnaRePost(${postItem.idx})">댓글입력</button>
				</c:if>
				</div>
			</div>
			<div class="btnBoth">
		<!--  이전글 다음글
			<button type="button" title="이전" class="default">이전</button>
			<button type="button" title="다음" class="default">다음</button>
		-->
			<c:if test="${'ADM' eq bbsrole or 'WRITER' eq bbsrole}">
				<button type="button" title="수정" class="point left" onclick="location.href='<%=contextPath %>/surport/qnaModify/'+${postItem.idx}" >수정</button>
				<button type="button" title="삭제" class="point left" onclick="qnaDelete(${postItem.idx})">삭제</button>
			</c:if>
			<button type="button" title="목록" class="point right" onclick="document.location = '<%=contextPath %>/surport/qna'">목록</button>
		</div>					
		
		<!-- END QNAVIEW -->
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->

<script>
	function qnaDelete(idx) {
		var cf = confirm("정말 삭제 하시겠습니까?");
		
		if (cf) {
			ajaxJson(
				['DELETE', apiSvr + '/board/qna/' +idx+'.json'], 
				null, 
		        function (res) {
		        	var data = res.response.data;
		        	console.log(res);
		        	console.log(data);
		        	location.href="<%=contextPath %>/surport/qna";
		    });
		} else {
			
		}
	}
	
	function qnaRePost(qnaId) {
		var cf = confirm("댓글을 등록 하시겠습니까?");
		if (cf) {
			ajaxJson(
				['POST', apiSvr + '/board/qna/re.json'], 
				{
		     		"qnaId" : qnaId,
					"description" : $('#qDescription').val()
		        }, 
		        function (res) {
		        	var data = res.response.data;
		        	console.log(res);
		        	console.log(data);
		        	location.href="<%=contextPath %>/surport/qnaView/"+qnaId;
		    });
		} else {
			
		}
	}
	
	function qnaReDelete(idx, qnaIdx) {
		
		var cf = confirm("정말 삭제 하시겠습니까?");
		
		if (cf) {
			
			ajaxJson(
				['DELETE', apiSvr + '/board/qna/re/' +idx+'.json'], 
				null, 
		        function (res) {
		        	var data = res.response.data;
		        	console.log(res);
		        	console.log(data);
		        	location.href="<%=contextPath %>/surport/qnaView/" + qnaIdx;
		    });
			
		} else {
			
		}
	}
	
	function qnaReModify(reItemIdx, qnaIdx, beforeText) {
		
		console.log(reItemIdx);
		console.log(qnaIdx);
		
		var modHtml = '<textarea name="" id="qEditDescription'+reItemIdx+'" cols="30" rows="2" class="comment">'+beforeText+'</textarea>' +
		'<button type="button" title="취소" class="subbtn" onclick="qnaReModifyCancel('+reItemIdx+',\''+beforeText+'\')">취소</button>' +
		'<button type="button" title="댓글수정" class="subbtn" onclick="qnaReModifySend('+reItemIdx+','+qnaIdx+')">댓글수정</button>' +
		'<br /><br /><br /><br />';
		
		$('#qEditDescriptionDiv'+reItemIdx).html(modHtml);
	}
	
	function qnaReModifySend(reItemIdx, qnaIdx) {
		console.log("reItemIdx : " + reItemIdx);
		console.log("qnaIdx : " + qnaIdx);
		console.log("qEditDescription value : " + $('#qEditDescription'+reItemIdx).val());
		
		var cf = confirm("댓글을 수정 하시겠습니까?");
				
		if (cf) {
			ajaxJson(
				['PUT', apiSvr + '/board/qna/re/' +reItemIdx+'.json'], 
				{
					"description" : $('#qEditDescription'+reItemIdx).val()
				}, 
		        function (res) {
		        	var data = res.response.data;
		        	console.log(res);
		        	console.log(data);
		        	location.href="<%=contextPath %>/surport/qnaView/" + qnaIdx;
		    });
		} else {
			
		}
	}
	
	function qnaReModifyCancel(reItemIdx, beforeText) {
		console.log("beforeText : " + beforeText);
		$('#qEditDescriptionDiv'+reItemIdx).html(beforeText);
	}
	
	
</script>

</body>
</html>
