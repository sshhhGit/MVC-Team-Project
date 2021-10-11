<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp"%>
<%-- notice_writeForm.jsp --%>

<html>
<head>

<title>notice_writeForm.jsp</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript" src="${ctxpath}/resources/js/script.js"></script>

<link rel="stylesheet" type="text/css" href="${ctxpath}/resources/css/style.css">
</head>
<body>


<h2>공지사항 작성</h2>


<table width="400">
	<tr>
		<td align="right">
			<a href="${ctxpath}/notice/list.do">리스트</a>
		</td>
	</tr>
</table>

<form method="post" name="writeForm" action="${ctxpath}/notice/writePro.do" onSubmit="return noticeWriteSave()">
  <input type="hidden" name="num" value="num">
  
 <table border="1">
 
	<tr>
		<td>글제목</td>
		<td>
			<input type="text" name="notice_title" id="notice_title" size="40">
		</td>
	</tr>
 
	<tr>
		<td>글내용</td>
		<td>
			<textarea name="notice_content" id="notice_content" rows="20" cols="80"></textarea>
		</td>
	</tr>
 
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="글쓰기">
			<input type="reset" value="취소">
			<input type="button" value="글목록" onClick="location='${ctxpath}/notice/list.do'">
		</td>
	</tr>
 </table>
  
</form>

</body>
</html>