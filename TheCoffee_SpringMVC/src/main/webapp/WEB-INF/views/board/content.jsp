<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp"%>

<%--content.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="../resources/css/style.css">

</head>
<body>
	<h2>고객의 소리</h2>
	<table border="1" id="board_content_t">
		<tr>
			<th width="15%">번호</th>
			<td>${bdto.num}</td>
		</tr>

		<tr>
			<th>조회수</th>
			<td>${bdto.readcount}</td>
		</tr>

		<tr>
			<th>작성자</th>
			<td>${bdto.writer}</td>
		</tr>

		<tr>
			<th>제목</th>
			<td>${bdto.subject}</td>
		</tr>

		<tr>
			<th>내용</th>
			<td><pre>${bdto.content}</pre></td>
		</tr>

		<tr>
			<td colspan="2" align="center"><input type="button" value="글수정"
				onClick="location='${ctxpath}/board/updateForm.do?num=${num}&pageNum=${pageNum}'">

				<input type="button" value="글삭제"
				onClick="location='${ctxpath}/board/delete.do?num=${num}&pageNum=${pageNum}'">

				<input type="button" value="글쓰기"
				onClick="location='${ctxpath}/board/writeForm.do'"> <input
				type="button" value="답글 쓰기"
				onClick="location='${ctxpath}/board/writeForm.do?num=${num}&pageNum=${pageNum}&ref=${bdto.ref}&re_step=${bdto.re_step }&re_level=${bdto.re_level}'">

				<input type="button" value="리스트"
				onClick="location='${ctxpath}/board/list.do?pageNum=${pageNum}'">

			</td>
		</tr>

	</table>
</body>
</html>