<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ajax upload</title>
<%@ include file="../commons/_header.jspf" %>
<style>
.fileDrop {
	width : 100%;
	height : 200px;
	border : 1px dotted blue;
}
small {
	margin-left : 3px;
	font-weight : bold;
	color : gray;
}
</style>
<script>
$(function(){
	$(".fileDrop").on("dragenter dragover", function(e){
		e.preventDefault();
	});
	$(".fileDrop").on("drop", function(e){
		e.preventDefault();
		var files = e.originalEvent.dataTransfer.files;
		var file = files[0]; //첫 번째 첨부파일
		var formData = new FormData();// 폼 객체
		formData.append("file", file); //폼에 file 변수 추가
		//서버에 파일 업로드 (백그라운드에서 실행)
		//contentType : false -> multipart/form-data 처리
		$.ajax({
			type : "post",
			url : "/upload/uploadAjax",
			data : formData,
			dataType : "text",
			processData : false,
			contentType : false,
			success : function(data, status, req){
				console.log("data : " + data);//업로드된 파일 이름
				console.log("status : " + status);//성공,실패 여부
				console.log("req : " + req.status); //요청코드값
			}
		});
	});
})
</script>
</head>
<body>
<%@ include file="../commons/_top.jspf" %>
<h2>Ajax File Upload</h2>
<!-- 파일을 업로드할 영역 -->
<div class="fileDrop"></div>
<!-- 업로드된 파일 목록을 출력할 영역 -->
<div class="uploadedList"></div>
</body>
</html>