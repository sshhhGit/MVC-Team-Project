<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp" %>
<%-- deleteForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>deleteForm2.jsp</title>
</head>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${ctxpath }/resources/js/script.js"></script>
<link rel="stylesheet" type="text/css" href="${ctxpath }/resources/style.css">
<body>

<h2>Q&A 답변 삭제</h2>
<br>

<form name="deleteForm2" method="post" action="${ctxpath}/qna/deletePro2.do?num=${num}" onsubmit="return writeSave()">
<table width="30%" border="1" cellpadding="10" cellspacing="0">
 <tr>
  <td bgcolor="#fff2ec">
   <b>해당 답변을 삭제하시겠습니까?</b>
  </td>
 </tr>
 
 <tr>
  <td align="center">
  암호입력 : <input type="password" name="pw" size="15">
   <input type="submit" value="답글삭제하기">
   <input type="button" value="뒤로가기" onclick="history.back()">
   <input type="button" value="글목록" onclick="location='${ctxpath}/qna/list.do?pageNum=${pageNum}'">
  </td>
 </tr>
</table>
</form>
<br><br>

</body>
</html>