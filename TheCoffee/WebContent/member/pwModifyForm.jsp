<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp"%>
<%--pwModifyForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function oldPwCheck() {
	var ff = eval("document.pwMoForm");
	
	if(ff.oldPw.value==''){
		alert("기존 비밀번호를 입력하세요");
		ff.oldPw.focus();
		return false;
	}
	if(ff.newPw.value==''){
		alert("새 비밀번호를 입력하세요");
		ff.newPw.focus();
		return false;
	}
	if(ff.newPw2.value==''){
		alert("새 비밀번호 확인을 입력하세요");
		ff.newPw2.focus();
		return false;
	}
	if(ff.oldPw.value==ff.newPw.value){
		alert("기존 비밀번호와 다른 비밀번호를 입력하세요");
		ff.oldPw.value="";
		ff.newPw.value="";
		ff.newPw.focus();
		return false;
	}
	if(ff.newPw.value!=ff.newPw2.value){
		alert("새 비밀번호 확인이 일치하지않습니다");
		ff.newPw.value="";
		ff.newPw2.value="";
		ff.newPw.focus();
		return false;
	}
	return true;
}
</script>
</head>
<body>
	<h2>비밀번호 변경</h2>
	<form action="${ctxpath }/member/pwModifyPro.do"
		onsubmit="return oldPwCheck()" name="pwMoForm" method="post">
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
				<input type="hidden" name="id" value="${sessionScope.userId }"> 
				<input type="reset" value="취소 " onclick="location='${ctxpath}/member/main.do'"></td>
			</tr>
		</table>

	</form>
</body>
</html>