<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../resources/css/style.css">
<script type="text/javascript" src="${ctxpath }/resources/js/script.js"></script>
</head>
<body>
<h2>로그인</h2>

  <c:if test="${!msg.equals(null) }">
    <font color="red">
      ${msg }
    </font>
  </c:if>
<table border="1" id="admin_login">
<tr>
<td>
  <form name="loginForm" method="post" action="loginPro.do" onsubmit="return loginCheck()">
    <table>
      <tr>
        <td>아이디</td>
        <td>
          <input type="text" name="id" id="id" size="20">
        </td>
      </tr>
      <tr>
        <td>비밀번호</td>
        <td>
          <input type="password" name="pw" id="pw" size="20">
        </td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <input type="submit" value="로그인">
          <input type="reset" value="다시입력">
        </td>
      </tr>
    </table>
  </form>
  </td>
  </tr>
  </table>
 
</body>
</html>