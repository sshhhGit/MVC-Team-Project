<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp" %>
<%-- updateForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>updateForm.jsp</title>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${ctxpath }/resources/js/script.js"></script>
<link rel="stylesheet" type="text/css" href="${ctxpath }/resources/style.css">
</head>
<body>

<br>
<h2>Q&A 글 수정</h2>

<form name="updateForm" method="post" action="${ctxpath}/qna/updatePro.do?num=${qnaDto.num}"> 
<table width="40%" border="1" cellpadding="10" cellspacing="0">
 <tr>
  <td><b>질문자</b></td>
  <td>${qnaDto.user_id}</td>
  <td><b>질문날짜</b></td>
  <td>${qnaDto.user_regdate}</td>
 </tr>
 
 <tr>
  <td colspan="4"><textarea name="user_content" id="user_content" rows="7" cols="100%">${qnaDto.user_content}</textarea></td>
 </tr>
 
 <tr>
  <td colspan="4" align="center">
   <input type="submit" value="질문수정하기">
   <input type="reset">
   <input type="button" value="뒤로가기" onclick="history.back()">
   <input type="button" value="글목록" onclick="location='${ctxpath}/qna/list.do'">
  </td>
 </tr>
</table>
</form>
<br><br>

</body>
</html>