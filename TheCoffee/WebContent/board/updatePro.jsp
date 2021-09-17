<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>    
<%--updatePro --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${check==1}">
  <meta http-equiv="Refresh" content="0;url=${ctxpath}/board/list.do">
</c:if>

<c:if test="${check==0}">
 <script>
   alert("암호가 틀립니다");
   history.back();
 </script>
 
 
</c:if>
</body>
</html>