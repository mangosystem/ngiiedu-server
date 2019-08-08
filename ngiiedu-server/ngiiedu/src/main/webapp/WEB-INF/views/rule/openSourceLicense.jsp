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
<link rel="shortcut icon" href="/ngiiedu/assets/images/nlip.ico" type="image/x-icon" />
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
</head>

<body class="edu">
	
	<jsp:include page ="../common/header.jsp" flush="false">
		<jsp:param value="rule" name="mainHeader"/>
		<jsp:param value="openSource" name="subHeader"/>
	</jsp:include>


<div id="contentsWrap">
	<ul class="location">
		<li>홈</li>
		<li>오픈소스 라이센스</li>
	</ul>
	<div class="contents">
		<h3 class="edge">오픈소스 라이센스</h3>
		<ul class="galleryList">
			<%-- <li>
				<div></div>
				<img src="<%=contextPath%>/assets/images/@test1.png" alt="" title="">
			</li> --%>
		</ul>
		<p>
			GeoServer<br/>
			(http://docs.geoserver.org/stable/en/user/introduction/license.html)<br/>
			GNU General Public License<br/>
			<br/>
			PostgreSQL<br/>
			(https://www.postgresql.org/about/licence/)<br/>
			Open Source license, similar to the BSD or MIT licenses.<br/>
			<br/>
			PostGIS<br/>
			(https://postgis.net/)<br/>
			PostGIS is released under the GNU General Public License (GPLv2 or later)<br/>
			<br/>
			GeoTools<br/>
			(http://docs.geotools.org/latest/userguide/welcome/license.html)<br/>
			GNU Lesser General Public License<br/>
			<br/>
			JUNIT<br/>
			Copyright 짤 2002-2017 JUnit. All Rights Reserved.<br/>
			(http://junit.org/junit4/license.html)<br/>
			Eclipse Public License 1.0<br/>
			<br/>
			Java Servlet<br/>
			(https://javaee.github.io/servlet-spec/LICENSE)<br/>
			COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Version 1.1<br/>
			<br/>
			Spring Framework<br/>
			(https://github.com/spring-projects/spring-framework)<br/>
			The Spring Framework is released under version 2.0 of the Apache License.<br/>
			<br/>
			Spring Security<br/>
			(https://github.com/spring-projects/spring-security)<br/>
			Spring Security is Open Source software released under the Apache 2.0 license.<br/>
			<br/>
			PostgreSQL JDBC Driver<br/>
			(https://jdbc.postgresql.org/about/license.html)<br/>
			BSD 2-clause "Simplified" License<br/>
			MyBatis<br/>
			(http://www.mybatis.org/spring/license.html)<br/>
			The Apache Software License, Version 2.0<br/>
			<br/>
			Jackson-core<br/>
			(https://github.com/FasterXML/jackson-core/blob/master/src/main/resources/META-INF/LICENSE)<br/>
			Apache (Software) License, version 2.0 ("the License").<br/>
			<br/>
			SLF4J<br/>
			(https://www.slf4j.org/license.html)<br/>
			MIT license<br/>
			<br/>
			babel-core<br/>
			(https://github.com/babel/babel/blob/master/LICENSE)<br/>
			MIT License<br/>
			<br/>
			babel-loader<br/>
			(https://github.com/babel/babel-loader)<br/>
			MIT License<br/>
			<br/>
			babel-preset-es2015<br/>
			(https://github.com/babel/babel/tree/master/packages/babel-preset-es2015)<br/>
			MIT License<br/>
			<br/>
			babel-preset-react<br/>
			(https://github.com/babel/babel/tree/master/packages/babel-preset-react)<br/>
			MIT License<br/>
			<br/>
			webpack<br/>
			(https://github.com/webpack/webpack/blob/master/LICENSE)<br/>
			MIT License<br/>
			<br/>
			bootstrap<br/>
			(https://github.com/twbs/bootstrap/blob/v4-dev/LICENSE)<br/>
			MIT License<br/>
			<br/>
			css-loader<br/>
			(https://github.com/webpack-contrib/css-loader/blob/master/LICENSE)<br/>
			jquery<br/>
			(https://jquery.org/license/)<br/>
			MIT license<br/>
			<br/>
			material-ui<br/>
			(https://github.com/mui-org/material-ui/blob/v1-beta/LICENSE)<br/>
			MIT License<br/>
			<br/>
			material-ui-color-picker<br/>
			(https://github.com/LoicMahieu/material-ui-color-picker/blob/master/LICENSE)<br/>
			MIT License<br/>
			<br/>
			material-ui-search-bar<br/>
			(https://github.com/TeamWertarbyte/material-ui-search-bar/blob/master/LICENSE)<br/>
			MIT License<br/>
			<br/>
			Popper.js<br/>
			(https://github.com/FezVrasta/popper.js/blob/master/LICENSE.md)<br/>
			MIT License<br/>
			<br/>
			React<br/>
			(https://github.com/facebook/react/blob/master/LICENSE)<br/>
			MIT License<br/>
			<br/>
			react-addons-update<br/>
			(https://github.com/facebook/react/blob/master/LICENSE)<br/>
			MIT License<br/>
			<br/>
			react-dom<br/>
			(https://github.com/facebook/react/blob/master/LICENSE)<br/>
			MIT License<br/>
			<br/>
			react-redux<br/>
			(https://github.com/reactjs/react-redux/blob/master/LICENSE.md)<br/>
			MIT License<br/>
			<br/>
			react-router-dom<br/>
			(https://github.com/ReactTraining/react-router/blob/master/LICENSE)<br/>
			MIT License<br/>
			<br/>
			react-tap-event-plugin<br/>
			(https://github.com/zilverline/react-tap-event-plugin/blob/master/LICENSE-react)<br/>
			BSD License<br/>
			<br/>
			Redux<br/>
			(https://github.com/reactjs/redux/blob/master/LICENSE.md)<br/>
			MIT License<br/>
			<br/>
			Style Loader<br/>
			(https://github.com/webpack-contrib/style-loader/blob/master/LICENSE)<br/>
			MIT License<br/>
			<br/>
			UglifyJS<br/>
			(https://github.com/mishoo/UglifyJS2/blob/master/LICENSE)<br/>
			BSD license<br/>
			<br/>
			SheetJS<br/>
			(https://github.com/SheetJS/js-xlsx/blob/master/LICENSE)<br/>
			Apache License 2.0<br/>
			<br/>
			Apache Software Foundation<br/>
			(http://www.apache.org/licenses/LICENSE-2.0)<br/>
			Apache License 2.0<br/>
		</p>
	</div>
	<!-- CONTENTS -->
</div>
<!-- END CONTENTSWRAP -->
	<jsp:include page ="../common/footer.jsp"></jsp:include>
<!-- END FOOTER -->
</body>
</html>
