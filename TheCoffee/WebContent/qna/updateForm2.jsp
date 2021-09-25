<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%-- updateForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>updateForm2.jsp</title>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="script.js"></script>
<link rel="stylesheet" type="text/css" href="../static/style.css">
</head>
<body>

<br>
<h2>Q&A 답변 수정</h2>
<form name="updateForm" method="post" action="${ctxpath}/qna/updatePro2.do?num=${dto.num}"> 
<input type="hidden" name="admin_id" value="${dto.admin_id}">
<table width="40%" border="1" cellpadding="10" cellspacing="0">
 <tr>
  <td><b>관리자</b></td>
  <td>${dto.admin_id}</td>
  <td><b>답변날짜</b></td>
  <td>${dto.admin_regdate}</td>
 </tr>
 
 <tr>
  <td colspan="4"><textarea name="admin_content" id="admin_content" rows="7" cols="100%">${dto.admin_content}</textarea></td>
 </tr>
 
 <tr>
  <td colspan="4" align="center">
   <input type="submit" value="수정하기">
   <input type="reset">
   <input type="button" value="뒤로가기" onclick="history.back()">
   <input type="button" value="글목록" onclick="location='${ctxpath}/qna/list.do'">
  </td>
 </tr>
</table>
</form>

</body>
</html>