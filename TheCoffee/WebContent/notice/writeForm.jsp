<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<%-- writeForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>writeForm.jsp</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript" src="script.js"></script>

<link rel="stylesheet" type="text/css" href="../static/style.css">

</head>
<body>
<br>
	<h2>공지사항 작성</h2>
<%-- 		
		<tr>
			<td align="center">
				<a href="${ctxpath}/notice/list.do">리스트</a>
			</td>
		</tr>
--%>		
	
	
	<form method="post" name="writeForm" action="${ctxpath}/notice/writePro.do" onsubmit="return writeSave()">
		<input type="hidden" name="num" value="${num}">
		
		<table width="60%" border="1" align="center" cellpading="0" cell>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="notice_title" id="notice_title" size="100%">
				</td>
			</tr>
			
			<tr>
				<td>내용</td>
				<td>
					<textarea name="notice_content" id="notice_content" rows="20" cols="90"></textarea>
				</td>
			</tr>
			
			 
			 
			<tr>
				<td colspan="2" align="center">
				    <input type="submit" value="글쓰기">
					<input type="reset" value="초기화">
					<input type="button" value="목록" onclick="location='${ctxpath}/notice/list.do'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>