<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<title>공간정보융합활용지원시스템</title>
		<meta charset="utf-8">
		<!--jquery  -->
		<script type="text/javascript" src="<%=contextPath%>/assets/cdn/jquery/jquery-3.2.1.min.js"></script>
		
		<script type="text/javascript">
			
			$(document).ready(function(){
		        $("#btn-upload").on("click", function(event){
		        	console.log('btn-upload');
		            
		            var form = new FormData(document.getElementById('uploadForm')); 
		            form.append("title", "test");
		            form.append("courseWorkSubId", 25);


		            $.ajax({
		                type: "post",
		                url: "http://localhost:8080/ngiiedu/api/v1/coursesWork/dataset",
		                data: form,
		                // processData: true=> get방식, false => post방식
		                dataType: "text",
		                // contentType: true => application/x-www-form-urlencoded, 
		                //                false => multipart/form-data
		                processData: false,
		                contentType: false,
		                success: function(data){
		                    alert(data);
		                }
		            });
		        });
		    });

		</script>

	</head>
		<div>
			<form id="uploadForm" enctype="multipart/form-data"> 
				<input type="file" id="fileId" name="file-data"/> 
			</form> 
			<button id="btn-upload">file upload</button>
		</div>
	</body>
</html>
