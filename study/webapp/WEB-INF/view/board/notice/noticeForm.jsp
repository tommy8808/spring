<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>STUDY :: 공지사항</title>

<%@ include file="../../commons/_header.jspf" %>
</head>
<body>

<%@ include file="../../commons/_top.jspf" %>
	
	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography"></section>
				<div class="page-header">
					<h1>공지사항 쓰기</h1>
				</div>
				
				<form:form modelAttribute="notice" cssClass="form-horizontal" action="/board/createNotice" method="post"
					 enctype="multipart/form-data">
					<div class="controll-group">
						<label class="control-label" for="title">제목</label>
						<div class="controls">
						<c:choose>
							<c:when test="${notice.bno eq '0'}">
								<form:input path="title"/>
								<form:errors path="title" cssClass="error"/>
							</c:when>
						
							<c:otherwise>
								${notice.title}
								<%-- <form:input path="title" readonly="true"/> --%>
								<form:hidden path="title"/>
							</c:otherwise>
						</c:choose>
						</div>
					</div>
					<div class="controll-group">
						<label class="control-label" for="writer">작성자</label>
						<div class="controls">
							<form:input path="writer"/>
							<form:errors path="writer" cssClass="error"/>
						</div>
					</div>
					<div class="controll-group">
						<label class="control-label" for="content">내용</label>
						<div class="controls">
							<form:textarea path="content"/>
							<form:errors path="content" cssClass="error"/>
						</div>
					</div>
					<div class="controll-group">
						<label class="control-label" for="file">첨부파일</label>
						<div class="controls">
							<%-- <form:input path="atchflNm"/> --%>
							<%-- <form:errors path="atchflNm" cssClass="error"/> --%>
							<input type="file" name="file">
						</div>
					</div>
					<div class="controll-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary">저장</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

</body>
</html>