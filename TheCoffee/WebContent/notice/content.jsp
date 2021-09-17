<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>

<%-- content.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>content.jsp</title>

<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
<h2>글내용 보기</h2>
<table width="65%" border="1">
	<tr>
		<td>No.</td>
		<td>${notice_no}</td>
	</tr>
	
	<tr>
		<td>조회수</td>
		<td>${dto.notice_count}</td>
	</tr>
	
	<tr>
		<td>글제목</td>
		<td>${dto.notice_title}</td>
	</tr>
	
	<tr>
		<td>글내용</td>
		<td>
			<pre>${dto.notice_content}</pre>
		</td>
	</tr>
	
	<tr>
		<td colspan="2" align="center">
			<input type="button" value="글수정" onclick="location='${ctxpath}/notice/updateForm.do?notice_no=${notice_no}&pageNo=${pageNo}'">
			
			<input type="button" value="글삭제" onclick="location='${ctxpath}/notice/deleteForm.do?notice_no=${notice_no}&pageNo=${pageNo}'">
			
			<input type="button" value="글쓰기" onclick="location='${ctxpath}/notice/writeForm.do'">
			
			<input type="button" value="리스트" onclick="location='${ctxpath}/notice/list.do?pageNo=${pageNo}'">
		
		</td>
	</tr>
	
</table>
</body>
</html>