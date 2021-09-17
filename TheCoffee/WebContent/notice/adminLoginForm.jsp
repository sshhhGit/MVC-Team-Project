<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>관리자로그인 </h2>
<form method="post" action="${ctxpath}/notice/adminLoginPro.do">
<table>
	<tr>
		<td>AdminID</td>
		<td><input type="text" name="adminId" id="adminId"></td>
	</tr>
	
	<tr>
		<td>AdminPw</td>
		<td><input type="password" name="adminPw" id="adminPw"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="admin로그인">
			<input type="reset">
		</td>
	</tr>
</table>
</form>
</body>
</html>