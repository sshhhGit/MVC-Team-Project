<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%-- writeForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>writeForm.jsp</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="script.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>

<h2>Q&A 글 작성</h2>
<form method="post" name="writeForm" action="${ctxpath}/qna/writePro.do" onsubmit="return writeSave()">
 <input type="hidden" name="num" value="${num}">
 <table width="40%" border="1">
  <tr>
   <td>질문자</td>
<!--    <td><input type="text" name="user_id" id="user_id" size="20"></td> -->
   <td><input type="text" name="user_id" id="user_id" value="<%= session.getAttribute("user_id") %>" readonly></td>
   <td>
    <input type="submit" value="질문등록">
    <input type="reset" value="초기화">
    <input type="button" value="글목록" onclick="location='${ctxpath}/qna/list.do'">
   </td>
  </tr>
  
  <tr>
   <td colspan="3"><textarea name="user_content" id="user_content" rows="7" cols="60%"></textarea></td>
  </tr>
 </table>
</form>

</body>
</html>