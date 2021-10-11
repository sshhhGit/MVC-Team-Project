<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp"%>

<%-- notice_content.jsp --%>
<html>
<head>

<title>content.jsp</title>

<link rel="stylesheet" type="text/css" href="${ctxpath}/resources/css/style.css">

</head>
<body>
	<h2>글내용 보기</h2>
	<table border="1" id="notice_content_t">
		<tr>
			<th>글번호</th>
			<td>${ndto.notice_no}</td>
		</tr>

		<tr>
			<th>조회수</th>
			<td>${ndto.notice_readcount}</td>
		</tr>

		<tr>
			<th>글제목</th>
			<td>${ndto.notice_title}</td>
		</tr>

		<tr>
			<th>글내용</th>
			<td><pre>${ndto.notice_content}</pre></td>
		</tr>

		<tr>
			<td colspan="2" align="center"><input type="button" value="글수정"
				onClick="location='${ctxpath}/notice/updateForm.do?notice_no=${notice_no}&pageNum=${pageNum}'">

				<input type="button" value="글삭제"
				onClick="location='${ctxpath}/notice/delete.do?notice_no=${notice_no}&pageNum=${pageNum}'">

				<input type="button" value="글쓰기"
				onClick="location='${ctxpath}/notice/writeForm.do'">

				<input type="button" value="리스트"
				onClick="location='${ctxpath}/notice/list.do?pageNum=${pageNum}'">

			</td>
		</tr>

	</table>
</body>
</html>