<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%-- deleteForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>deleteForm2.jsp</title>
</head>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="script.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
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

<h2>Q&A 답변 삭제</h2>
<form name="deleteForm" method="post" action="${ctxpath}/qna/deletePro2.do" onsubmit="return check()">
<table width="30%" border="1" cellpadding="10" cellspacing="0">
 <tr>
  <td bgcolor="#b0e0e6">
   암호를 입력 하세요
  </td>
 </tr>
 
 <tr>
  <td bgcolor="#b0e0e6" align="center">
  암호입력 : <input type="password" name="pw" size="15" value="123">
   <input type="submit" value="글삭제">
   <input type="button" value="글목록" onclick="location='${ctxpath}/qna/list.do?pageNum=${pageNum}'">
   
   <input type="hidden" name="num" value="${num}">
   <input type="hidden" name="admin_id" value="${admin_id}">
   <input type="hidden" name="pageNum" value="${pageNum}">
  </td>
 </tr>
</table>
</form>

</body>
</html>