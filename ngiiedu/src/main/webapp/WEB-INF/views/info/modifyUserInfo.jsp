<%@page import="kr.go.ngii.edu.config.LocalResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.go.ngii.edu.main.users.model.User"%>
<%
    String apiSvr = LocalResourceBundle.NGIIEDU_API_SERVER;
	String contextPath = request.getContextPath();
	
	User user; 
	String userName = null;
	String userid = null;

	user = (User)session.getAttribute("USER_INFO");
	
	if (user != null) {
		userName = user.getUserName();
		userid = user.getUserid();
	}
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

<title>공간정보융합활용지원시스템</title>

</head>

<body class="edu">
	
	<jsp:include page ="../common/header.jsp" flush="false">
		<jsp:param value="info" name="mainHeader"/>
	</jsp:include>


<div id="contentsWrap">
	<ul class="location">
		<li>홈</li>
		<li>사용자정보변경</li>
	</ul>
	<div class="contents">
		<h3 class="edge">사용자정보변경</h3>
		<ul class="galleryList">
			<%-- <li>
				<div></div>
				<img src="<%=contextPath%>/assets/images/@test1.png" alt="" title="">
			</li> --%>
		</ul>
		<div class="modifyInfo">			
			<table class="table">
				<tr>
					<td>
						<label for="">별칭</label>
					</td>
					<td>
						<input id="userName" type="text" value="<%= userName%>">
					</td>
					<td>
						<button type="button" title="변경" class="default" onclick="modifyUserName('<%= userid %>')">변경</button>
					</td>
				</tr>
				<tr>
					<td>
						<label for="">비밀번호</label>
					</td>
					<td>
						<div id="table">
							<div class="row">
								<span class="cell col1"><label for="" class="password">현재 비밀번호</label></span>
								<span class="cell col2"><input id="oldpasswd" type="password"></span>
							</div>
							<div class="row">
								<span class="cell col1"><label for="" class="password">새 비밀번호</label></span>
								<span class="cell col2"><input id="newpasswd" type="password"></span>
							</div>
							<div class="row">
								<span class="cell col1"><label for="" class="password">새 비밀번호 재확인</label></span>
								<span class="cell col2"><input id="confirmpasswd" type="password"></span>
							</div>
						</div>
					</td>
					<td>
						<button type="button" title="변경" class="default"  onclick="modifyPasswd('<%= userid %>')">변경</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->

<script>
	
	function modifyUserName(userid){
				
		let url = '/users/' + userid + '/name.json';
		
		ajaxJson(
			['PUT', apiSvr + url], 
			{
	     		"userName" : $('#userName').val()
	        }, 
	        function (res) {
					console.log(res);
	        	if(res.response.data == false){
	        		alert('별칭 변경에 실패했습니다.')
	        	}else if(res.response.data == true){
	        		alert('별칭을 성공적으로 변경하였습니다.');
					window.location.reload();
	        	}
        });
	}
	
	function modifyPasswd(userid){
		var checkPw = isValidFormPassword($('#newpasswd').val());
		if(checkPw){
			if($('#confirmpasswd').val()==$('#newpasswd').val()){
				
				let url = '/users/' + userid + '/password.json';
				
				ajaxJson(
					['PUT', apiSvr + url], 
					{
			     		"oldpasswd" : $('#oldpasswd').val(),
			     		"newpasswd" : $('#newpasswd').val()
			        }, 
			        function (res) {
						console.log(res);
						if(res.response.data == false){
							alert('현재 비밀번호를 확인해 주세요.');
						}else if(res.response.data ==true){
							alert('성공적으로 비밀번호를 변경하였습니다.');
						}
						$('#oldpasswd').val("");
						$('#newpasswd').val("");
						$('#confirmpasswd').val("");
		        });
				
			}else{
				alert('입력하신 새 비밀번호가 같지 않습니다.다시 입력해 주세요.');
			}
		}else{
			alert('비밀번호는 8자리 이상, 영문, 숫자, 특수문자가 포함되어야 합니다. 다시 입력해 주세요.')
		}
	}
	
	function isValidFormPassword(pw) {
		var check = /^(?=.*[a-zA-Z])(?=.*[~`?/!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;	 
		if (!check.test(pw))     {
			return false;
		}
		         
		if (pw.length < 8 || pw.length > 20) {
			return false;
		}
	    return true;
	}
</script>
</body>
</html>
