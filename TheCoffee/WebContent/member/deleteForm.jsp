<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<%--deleteForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function pwCheck() {
	if(document.delForm.pw.value==''){
		alert("암호를 입력하세요");
		document.delForm.pw.focus();
		return false;
	}
	return true;
}
</script>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<h2>회원탈퇴</h2>
<form action="${ctxpath }/member/deletePro.do" onsubmit="return pwCheck()" name="delForm" method="post">
  <table width="260" border="1">
    <tr height="30">
      <td>암호입력</td>
      <td>
        <input type="password" name="pw" id="pw" size="15">
        <input type="hidden" name="id" value="${sessionScope.userId }">
      </td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <input type="submit" value="회원탈퇴">
        <input type="reset" value="취소 " onclick="location='${ctxpath}/member/main.do'">
      </td> 
    </tr>
  </table>

</form>
</body>
</html>