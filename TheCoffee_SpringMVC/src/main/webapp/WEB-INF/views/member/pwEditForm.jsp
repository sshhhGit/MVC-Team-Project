<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp" %>
<%--pwModifyForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ctxpath }/resources/js/script.js"></script>

</head>
<body>
	<h2>비밀번호 변경</h2>
	<form action="${ctxpath }/member/pwEditPro.do"
		onsubmit="return oldPwCheck()" name="pwEditForm" method="post">
		<table border="1">
			<tr>
				<td>기존 비밀번호</td>
				<td><input type="password" name="oldPw" id="oldPw" size="15">
				</td>
			</tr>
			<tr>
				<td>새 비밀번호</td>
				<td><input type="password" name="newPw" id="newPw" size="15">
				</td>
			</tr>
			<tr>
				<td>새 비밀번호 확인</td>
				<td><input type="password" name="newPw2" id="newPw2" size="15">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="변경하기">
				<input type="hidden" name="id" value="${userId }"> 
				<input type="reset" value="취소 " onclick="location='${ctxpath}/member/main.do'"></td>
			</tr>
		</table>

	</form>
</body>
</html>