<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp"%>
<%--list.jsp --%>
<fmt:requestEncoding value="UTF-8" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="script.js"></script>
<link rel="stylesheet" type="text/css" href="../static/style.css"> -->
</head>
<body>
	<c:if test="${'admin' ne sessionScope.userId}">
		<h1>관리자 권한이 필요합니다</h1>
	</c:if>
	<c:if test="${'admin' eq sessionScope.userId}">
		<h2>회원정보 목록</h2>
		<table border="1">
			<tr>
				<td>번호</td>
				<td>아이디</td>
				<td>이름</td>
				<td>이메일</td>
				<td>전화</td>
				<td>우편번호</td>
				<td>주소</td>
				<td>상세주소</td>
				<td>가입일</td>
				<td><input type="button" value="탈퇴"></td>
			</tr>
			<c:set var="num" value="${1 }" />
			<c:forEach items="${list }" var="dto" varStatus="i">
				<tr>
					<td>${num}</td>
					<td>${dto.id}</td>
					<td>${dto.name}</td>
					<td>${dto.email}</td>
					<td>${dto.tel}</td>
					<td>${dto.zipcode}</td>
					<td>${dto.addr}</td>
					<td>${dto.addr2}</td>
					<td>${dto.regdate}</td>
					<td><input type="checkbox" name="adminDelete" id="adminDelete"></td>
				</tr>
				<c:set var="num" value="${num+1}" />
			</c:forEach>
		</table>
	</c:if>
</body>
</html>