<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="${ctxpath }/resources/js/script.js"></script>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="../static/style.css">

</head>
<body>
	<h2>개인정보 변경</h2>
	<table border="1">
	<tr>
	<td>
	<form action="editPro.do" name="editForm" method="post"
		onsubmit="return editCheck()">
		<table border="0">
			<tr>
				<td>ID</td>
				<td>${mdto.id } <input type="hidden" name="id"
					value="${userId }">
				</td>
			</tr>
			<tr>
				<td>암호</td>
				<td><input type="password" name="pw" id="pw" size="26">
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" size="26"
					value="${mdto.name }"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" size="26"
					value="${mdto.email }"></td>
			</tr>
			<tr>
				<td>전화</td>
				<td><select name="tel">
						<option value="${tel }">${tel }</option>
						<option value="010">010</option>
						<option value="018">018</option>
						<option value="017">017</option>
				</select> - <input type="text" name="tel2" value="${tel2 }" size="4" maxlength="4">
					- <input type="text" name="tel3" value="${tel3 }" size="4" maxlength="4">
				</td>
			</tr>
			<tr>
				<td>우편번호</td>
				<td><input type="text" name="zipcode" id="zipcode" size="26"
					value="${mdto.zipcode }"> <input type="button" value="주소검색"
					onclick="openDaumPostcode()"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="addr" id="addr"
					value="${mdto.addr }" size="26" readonly="readonly"></td>
			</tr>
			<tr>
				<td>상세주소</td>
				<td><input type="text" name="addr2" id="addr2"
					value="${mdto.addr2 }" size="26"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="변경하기"> <input type="button" value="취소"
					onclick="location='${ctxpath}'"></td>
			</tr>
		</table>
	</form>
	</td>
	</tr>
	</table>
</body>
</html>