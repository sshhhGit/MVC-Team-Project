<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp" %>
<%-- writeForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>writeForm.jsp</title>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${ctxpath }/resources/js/script.js"></script>
<link rel="stylesheet" type="text/css" href="${ctxpath }/resources/style.css">
</head>
<body>

<br>
<h2>Q&A 질문 작성</h2>

<form method="post" name="writeForm" action="${ctxpath}/qna/writePro.do" onsubmit="return qnaWriteSave()">
 <table width="40%" border="1" cellpadding="10" cellspacing="0">
  <tr>
   <td><b>질문자</b></td>
   <td>
    <input type="hidden" name="user_id" value="${userId}">
    ${userId}
   </td>
   <td>
    <input type="submit" value="질문등록하기">
    <input type="reset" value="초기화">
    <input type="button" value="뒤로가기" onclick="history.back()">
    <input type="button" value="글목록" onclick="location='${ctxpath}/qna/list.do'">
   </td>
  </tr>
  
  <tr>
   <td colspan="3"><textarea name="user_content" id="user_content" rows="7" cols="100%"></textarea></td>
  </tr>
 </table>
</form>
<br><br>

</body>
</html>