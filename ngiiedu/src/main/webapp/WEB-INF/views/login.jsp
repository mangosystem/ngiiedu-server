<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ko">
	<head>
		<link rel="shortcut icon" href="/ngiiedu/assets/images/nlip.ico" type="image/x-icon" />
		<meta charset="utf-8">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
		<style type="text/css">
			.site-wrapper {
				display: table;
				width: 100%;
				height: 100%; /* For at least Firefox */
				min-height: 100%;
			}
			
			.site-wrapper-inner {
				vertical-align: middle;
				display: table-cell;
			}
		</style>
	</head>

	<body style="background-color: #f0f0f0;">
		<div class="site-wrapper">
			<div class="site-wrapper-inner">
				<main style="width: 500px; margin: -100px auto 0; padding: 0 1.5rem;">
					<div>
						<h1 class="text-center">공간정보융합활용지원시스템</h1>
						<hr />
						<form action="<%=request.getContextPath() + "/login_process"%>" method="POST">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<div class="form-group">
									<i class="user icon"></i> <input type="text" class="form-control" name="username" placeholder="아이디" />
								</div>
								<div class="form-group">
									<i class="lock icon"></i> <input type="password" class="form-control" name="password" placeholder="비밀번호" />
								</div>
								<div class="form-group">
									<button class="btn btn-lg btn-primary btn-block" >
										로그인
									</button>
								</div>
							</div>
						</form>
						<hr />
						<div>
							<div style="float:left;">
								<a href="./join">가입하기</a>
							</div>
							<div style="float:right;">
								<nav>
									<a href="#">아이디찾기</a> | 
									<a href="#">패스워드찾기</a>
								</nav>
							</div>
						</div>
					</div>
				</main>
				<footer style="position: absolute; bottom: 0; right:0; left: 0;">
					<div style="position:relative; margin: 0 auto; width:1024px; color: #888;">
						<nav style="float: left;">
							<a href="#">이용약관</a> | 
							<a href="#">위치기반서비스이용약관</a> | 
							<a href="#">개인정보처리방침</a> | 
							<a href="#">오픈소스라이센스</a>
						</nav>
						<address style="float: right;">
							Copyright © <a href="http://www.ngii.go.kr/" target="_blank">NGII.</a>
							All rights reserved.
						</address>
					</div>
				</footer>
			</div>
		</div>
	</body>
</html>