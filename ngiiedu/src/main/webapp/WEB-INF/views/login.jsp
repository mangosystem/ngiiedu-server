<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ko">
	<head>
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

	<body>
		<div class="site-wrapper">
			<div class="site-wrapper-inner">
				<main style="padding: 0 1.5rem;">
					<div style="width: 500px; margin: 0 auto;">
						<h1 class="text-center">공간정보융합활용지원시스템</h1>
						<hr>
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
					</div>
				</main>
				<footer></footer>
			</div>
		</div>
	</body>
</html>