<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<%--deletePro.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<c:if test="${check==1 }">
  <!-- 삭제성공 -->
  <c:remove var="userId" scope="session"/>
  <a href="${ctxpath }/member/main.do">메인으로</a>
  
  <meta http-equiv="Refresh" content="2; url=${ctxpath }/member/main.do">
  <font color="red" size="5">회원정보가 삭제되었습니다</font>
</c:if>
<c:if test="${check==-1 }">
  <script>
  alert("암호가 일치하지않습니다");
  history.back();
  </script>
</c:if>
<c:if test="${check==0 }">
  <script>
  alert("아이디가 존재하지않습니다");
  history.back();
  </script>
</c:if>
</body>
</html>