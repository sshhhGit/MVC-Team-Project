<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp"%>
<%-- updateForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>updateForm.jsp</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript" src="script.js"></script>

<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
	<h2>글수정 폼</h2>
	<form name="writeForm" method="post" action="${ctxpath}/notice/updatePro.do?pageNo=${pageNo}">
		
		<table width="50%" border="1">
			
			<tr>
				<td>글제목</td>
				<td>
				<input type="text" name="notice_title" value="${dto.notice_title}" size="50">
				<input type="hidden" name="notice_no" value="${notice_no}">
				</td>
			</tr>
			
			<tr>
				<td>글내용</td>
				<td>
					<textarea name="notice_content" rows="10" cols="50">${dto.notice_content}</textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="글수정">&nbsp;
				<input type="reset">
				<input type="button" value="글목록" onclick="location='${ctxpath}/notice/list.do'">
				</td>
			</tr>
			
		</table>		
	</form>
	
	
</body>
</html>