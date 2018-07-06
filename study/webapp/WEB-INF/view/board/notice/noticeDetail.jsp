<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../../commons/_header.jspf" %>
<script>
$(function(){
	$("#updateForm").click(function(event){
		event.preventDefault();
		/* var locate = "/board/updateNoticeForm.do?bno="+$("#bno").val(); */
		var locate = "/board/updateNotice?bno="+"${noticeVO.bno}";
		location.href=locate;
	});
	
	$("#deleteBoard").click(function(event){
		if(confirm("정말 삭제하시겠습니까?")){
			event.preventDefault();
			//$("#deleteBoard").attr({action:"/board/deleteNotice", method : 'post'}).submit();
			$.post("/board/deleteNotice", {
											bno: "${noticeVO.bno}"
										  }, "json").done(function(){
											  location.href="/board/listNotice";
										  }).fail(function(data, status, req){
											  alert("게시글 삭제가 실패했습니다. : " + data);
											  console.log("data : " + data[0]);//업로드된 파일 이름
											  console.log("status : " + status);//성공,실패 여부
											  console.log("req : " + req.status); //요청코드값
											  return;
										  });
			/* var locate = "/board/updateNoticeForm.do?bno="+$("#bno").val(); */
			//var locate = "/board/deleteNotice?bno="+"${noticeVO.bno}";
			//location.href=locate;
		}
		return;
	});
	
	$("#listNotice").click(function(){
		var test = {
				  "targetId": "user01",
				  "sender": "user02",
				  "message": "안녕하세요"
				};
		$.ajax({
			type : "post",
			url : "/message/",
			data : JSON.stringify(test),
			dataType : "json",
			processData : false,
			contentType : "application/json; charset=UTF-8",
			success : function(data, status){
				console.log("data : " + data);//업로드된 파일 이름
				console.log("status : " + status);//성공,실패 여부
				alert("dd");
			}
		});
	});
});


</script>
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
						<button type="button" id="updateForm" class="btn btn-primary">편집</button>
						<button type="button" id="listNotice" class="btn btn-primary">목록</button>
						<button type="button" id="deleteBoard" class="btn btn-primary">삭제</button>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>