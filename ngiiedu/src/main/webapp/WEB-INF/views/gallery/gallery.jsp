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
<link rel="shortcut icon" href="<%=contextPath%>/assets/images/nlip.ico" type="image/x-icon" />
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
<script>

	var apiSvr = '<%=apiSvr%>';
	var galleryData=[]
	
	$(document).ready(function(){
		$.ajax({
	         url: apiSvr + '/board/gallery.json',
	         dataType: 'json',
	         success: function(data) {
	        	 galleryData=data.response.data
	        	 //console.dir(galleryData)
	        	 for(var i = 0;i<galleryData.length;i++){
	        		 var data = galleryData[i];
	        		if(data.outputType=='layer'){
		        	 $('.galleryList').append('<li><div onclick="document.location = \'<%=contextPath%>/gallery/view/l/'+data.pinogioOutputId+'\'"></div><img src ="'+apiSvr+'/coursesWork/layers/thumbNail/'+data.pinogioOutputId+'?width:300&height=230"></li>');
	        		} else if(data.outputType=='maps'){
	        			$('.galleryList').append('<li><div onclick="document.location = \'<%=contextPath%>/gallery/view/m/'+data.pinogioOutputId+'\'"></div><img src ="<%=contextPath%>/assets/images/TAB.png" style="width:300px;height:230px"></li>');
	        		}
	        	 }
	         },
	         error: function(xhr, status, err) {
	             console.error(status, err.toString());
	         }
	     });	
	});
</script>
</head>

<body class="edu">
	
	<jsp:include page ="../common/header.jsp" flush="false">
		<jsp:param value="gallery" name="mainHeader"/>
		<jsp:param value="gallery" name="subHeader"/>
	</jsp:include>


<div id="contentsWrap">
	<ul class="location">
		<li>홈</li>
		<li>수업활동갤러리</li>
	</ul>
	<div class="contents">
		<h3 class="edge">수업활동갤러리</h3>
		<ul class="galleryList">
			<%-- <li>
				<div></div>
				<img src="<%=contextPath%>/assets/images/@test1.png" alt="" title="">
			</li> --%>
		</ul>
		<!-- <ul class="pagination">
			<li class="ico first" title="맨앞으로">앞으로</li>
			<li class="ico forward" title="앞으로">앞으로</li>
			<li>1</li>
			<li>2</li>
			<li>3</li>
			<li class="on">4</li>
			<li>5</li>
			<li>6</li>
			<li class="ico back" title="뒤로">뒤로</li>
			<li class="ico end" title="맨뒤로">맨뒤로</li>
		</ul> -->
		<!-- END GALLERYLIST -->
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->
</body>
</html>
