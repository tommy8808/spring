<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>공지사항 목록</title>
<%@ include file="../../commons/_header.jspf" %>
</head>
<body>
<%@ include file="../../commons/_top.jspf" %>
<div class="container">
	<table border = "1">
		<tr>
			<td colspan="4"><a href="/board/createNotice.do">[공지사항쓰기]</a></td>
		</tr>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>내용</td>
		</tr>
		<c:if test="${empty listNotice}">
			<tr>
				<td colspan="4">게시글이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach var="list" items="${listNotice}">
			<tr>
				<td>${list.bno}</td>
				<td>
				<%-- <a href="read.do?no=${article.number}&pageNo=${articlePage.currentPage}"> --%>
					<c:out value="${list.title}"/>
				</a>
				</td>
				<td>${list.writer}</td>
				<td>${list.content}</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>