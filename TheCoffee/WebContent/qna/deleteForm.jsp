<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%-- deleteForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>deleteForm.jsp</title>
</head>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="script.js"></script>
<link rel="stylesheet" type="text/css" href="../static/style.css">

<script>
 function check() {
	if (document.deleteForm.pw.value == '') {
		alert("암호를 입력하세요");
		document.deleteForm.pw.focus();
		return false;
	}
	
	return true;
} // check()-end
</script>
<body>

<h2>Q&A 글 삭제</h2>
<br>
<form name="deleteForm" method="post" action="${ctxpath}/qna/deletePro.do" onsubmit="return check()">
<table width="30%" border="1" cellpadding="10" cellspacing="0">
 <tr>
  <td bgcolor="#FFC6FF">
   <b>해당질문을 삭제하시겠습니까?</b>
  </td>
 </tr>
 
 <tr>
  <td align="center" bgcolor="ivory">
  암호입력 : <input type="password" name="pw" size="15" value="123">
  <br><br>
   <input type="submit" value="질문삭제하기">
   <input type="button" value="뒤로가기" onclick="history.back()">
   <input type="button" value="글목록" onclick="location='${ctxpath}/qna/list.do?pageNum=${pageNum}'">
   
   <input type="hidden" name="num" value="${num}">
   <input type="hidden" name="user_id" value="${user_id}">
   <input type="hidden" name="pageNum" value="${pageNum}">
  </td>
 </tr>
</table>
</form>

</body>
</html>