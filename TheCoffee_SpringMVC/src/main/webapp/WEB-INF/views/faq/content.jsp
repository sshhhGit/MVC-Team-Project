<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp"%>

<%-- faq_content.jsp --%>
<html>
<head>

<title>content.jsp</title>

<link rel="stylesheet" type="text/css" href="${ctxpath}/resources/css/style.css">

</head>
<body>
	<h2>FAQ</h2>
	<table border="1" id="faq_content_t">
		<tr>
			<th>번호</th>
			<td>${fdto.faq_no}</td>
		</tr>

		<tr>
			<th>분류</th>
			<td>${fdto.faq_category}</td>
		</tr>

		<tr>
			<th>조회수</th>
			<td>${fdto.faq_readcount}</td>
		</tr>

		<tr>
			<th>제목</th>
			<td>${fdto.faq_title}</td>
		</tr>

		<tr>
			<th>내용</th>
			<td><pre>${fdto.faq_content}</pre></td>
		</tr>

		<tr>
			<td colspan="2" align="center"><input type="button" value="글수정"
				onClick="location='${ctxpath}/faq/updateForm.do?faq_no=${faq_no}&pageNum=${pageNum}'">

				<input type="button" value="글삭제"
				onClick="location='${ctxpath}/faq/delete.do?faq_no=${faq_no}&pageNum=${pageNum}'">

				<input type="button" value="글쓰기"
				onClick="location='${ctxpath}/faq/writeForm.do'">

				<input type="button" value="리스트"
				onClick="location='${ctxpath}/faq/list.do?pageNum=${pageNum}'">

			</td>
		</tr>

	</table>
</body>
</html>