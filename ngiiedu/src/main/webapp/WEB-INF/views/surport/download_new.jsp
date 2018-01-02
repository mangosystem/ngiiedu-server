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
		<jsp:param value="download" name="subHeader"/>
	</jsp:include>

<div id="contentsWrap">
	<ul class="location">
		<li>홈</li>
		<li>사용지원</li>
		<li>묻고 답하기</li>
	</ul>
	<div class="contents">
		<h3>자료실</h3>
		<ul class="boardNew">
			<li class="title">
				<label for="">제목</label>
				<input id="qTitle" type="text">
			</li>
			<li class="script">
				<textarea name="" id="qDescription" cols="30" rows="20" class="comment"></textarea>	
			</li>
		</ul>
		
		<div class="file_input">
		    <label>
				파일첨부
				<form id="uploadForm" enctype="multipart/form-data">
		        	<input type="file" name="uploadfile" onchange="javascript:document.getElementById('file_route').value=this.value">
		    	</form> 
		    </label>
		    <input type="text" readonly="readonly" title="File Route" id="file_route">
		</div>
		
		<div class="btnCenter">
			<button type="button" title="작성" class="point" onClick="submitPost()">작성</button>
			<button type="button" title="취소" class="default" onclick="document.location = '<%=contextPath %>/surport/qna'">취소</button>
		</div>
		<!-- END QNA NEW -->
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->

<script>
	
	function submitPost() {
		
		console.log("submitPost");
		console.log($("input[name=uploadfile]")[0].files[0]);

		var formData = new FormData();
		formData.append("attach", $("input[name=uploadfile]")[0].files[0]);
		formData.append("title", $('#qTitle').val());
		formData.append("description", $('#qDescription').val());
		
		 $.ajax({
             method: "post",
             processData: false,
             contentType: false,
             url: "http://localhost:8080/ngiiedu/api/v1/board/pds.json",
             data: formData,
             // processData: true=> get방식, false => post방식
             //dataType: "text",
             // contentType: true => application/x-www-form-urlencoded, 
             //                false => multipart/form-data
             //processData: false,
             //contentType: false,
             success: function(data){
                 console.log(data);
             }
         })
	}
	
	
	
</script>
</body>
</html>
