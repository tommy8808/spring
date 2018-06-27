<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../../commons/_header.jspf" %>
</head>
<body>
<%@ include file="../../commons/_top.jspf" %>
	<div class="container">
		<div class="row">
			<div class="span12">
				<div class="page-header">
					<h1>공지사항 상세보기</h1>
				</div>
				<div class="controll-group">
					<label class="control-label" for="title">제목</label>
					<div class="controls">
						<input type="text" value="${noticeVO.title}"/>
					</div>
				</div>
				<div class="controll-group">
					<label class="control-label" for="writer">작성자</label>
					<div class="controls">
						<input type="text" value="${noticeVO.writer}"/>
					</div>
				</div>
				<div class="controll-group">
					<label class="control-label" for="content">내용</label>
					<div class="controls">
						<textarea>${noticeVO.content}</textarea>
					</div>
				</div>
				<div class="controll-group">
					<label class="control-label" for="file">첨부파일</label>
					<div class="controls">
						<input type="text" value="${noticeVO.atchflNm}"/>
						<img alt="img" src="/images/${noticeVO.atchflNm}" width="100px" height="100px"/>
					</div>
				</div>
				<div class="controll-group">
					<div class="controls">
						<button type="submit" class="btn btn-primary">편집</button>
						<button type="button" class="btn btn-primary">목록</button>
						<button type="reset" class="btn btn-primary">삭제</button>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>