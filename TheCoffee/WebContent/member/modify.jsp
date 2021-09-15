<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
  <table>
    <tr>
      <td>
        <form action="${ctxpath }/member/modifyForm.do" name="modifyForm" method="post">
        <input type="hidden" name="id" value="${sessionScope.userId }">
        <input type="submit" value="내정보 수정">
        </form>
      </td>
      <td>
        <form action="${ctxpath }/member/deleteForm.do" name="deleteForm" method="post">
          <input type="hidden" name="id" value="${sessionScope.userId }">
          <input type="submit" value="회원 탈퇴">
        </form>
      </td>
    </tr>
  </table>
</body>
</html>