<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="adminId" value="${adminId}" scope="session"/>
	<meta http-equiv="Refresh" content="0; url=${ctxpath}/notice/list.do">
</body>
</html>