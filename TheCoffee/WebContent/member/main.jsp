
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="script.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<c:if test="${empty sessionScope.userId }">
  <form name="loginForm" method="post" action="${ctxpath }/member/loginPro.do" onsubmit="return loginCheck()">
<%--     <table width="100%" border="1">
      <tr>
        <td rowspan="4" width="900" height="20" align="center">
          <a href="">옥션</a>&nbsp;
          <a href="">다나와</a>&nbsp;
          <a href="">Emart</a>&nbsp;
          
        </td>
      </tr>
      <tr>
        <td width="70" align="right">ID</td>
        <td width="70"><input type="text" name="id" id="id" size="20"></td>
      </tr>
      <tr>
        <td width="70" align="right">암호</td>
        <td width="70"><input type="password" name="pw" id="pw" size="20"></td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <input type="submit" value="로그인">
          <input type="button" value="회원가입" onclick="location='${ctxpath}/member/inputForm.do'">
        </td>
      </tr>
    </table> --%>
  </form>
</c:if>

<%-- 로그인 상태일 떄, 로그아웃, 회원정보 수정 --%>
<c:if test="${!empty sessionScope.userId }">
<%--   <table width="100%" border="1">
    <tr>
      <td width="975" height="20" align="center">
        홍길동 홈 입니다
      </td>
      <td rowspan="3" align="center">
        ${sessionScope.userId }님 방문해주셔셔 감사합니다
        
        <form method="post" action="${ctxpath }/member/logOut.do">
          <input type="submit" value="로그아웃">
          <input type="button" value="정보수정" onclick="location='${ctxpath}/member/modify.do'">
        </form>
      </td>
  </table> --%>
</c:if>

</body>
</html>