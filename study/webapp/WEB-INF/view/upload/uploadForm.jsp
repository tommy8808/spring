<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../commons/_header.jspf" %>
<style type="text/css">
iframe {
	width : 800px;
	height: 200px;
	border: 1px;
	border-style: solid;
}

</style>
<title>Insert title here</title>
</head>
<body>
<%@ include file="../commons/_top.jspf" %>
<form action="/upload/uploadForm" method="post" enctype="multipart/form-data" target="iframe1">
	<input type="file" name="file">
	<input type="submit" value="업로드">
</form>

<iframe name="iframe1"></iframe> 
</body>
</html>