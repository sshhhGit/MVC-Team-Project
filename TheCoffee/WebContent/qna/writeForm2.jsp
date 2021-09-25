<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%-- writeForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>writeForm2.jsp</title>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="script.js"></script>
<link rel="stylesheet" type="text/css" href="../static/style.css">
</head>
<body>

<br>
<h2>Q&A 답변 작성</h2>
<form method="post" name="writeForm" action="${ctxpath}/qna/writePro2.do" onsubmit="return writeSave()">
 <input type="hidden" name="num" value="${num}">
 <table width="40%" border="1" cellpadding="10" cellspacing="0">
  <tr>
   <td>관리자</td>
   <td><input type="text" name="admin_id" id="admin_id" value="<%=session.getAttribute("admin_id")%>" readonly></td>
   <td>
    <input type="submit" value="질문등록">
    <input type="reset" value="초기화">
    <input type="button" value="뒤로가기" onclick="history.back()">
    <input type="button" value="글목록" onclick="location='${ctxpath}/qna/list.do'">
   </td>
  </tr>
  
  <tr>
   <td colspan="3"><textarea name="admin_content" id="admin_content" rows="7" cols="100%"></textarea></td>
  </tr>
 </table>
</form>

</body>
</html>