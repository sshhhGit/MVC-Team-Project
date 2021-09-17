<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"  %>
<%-- inputForm.jsp --%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="product_script.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<form method="post" name="inputForm" action="${ctxpath}/product/inputPro.do" encType="multipart/form-data" onSubmit="return inputSave()">
		<input type="hidden" name="pro_no" value="0">
		
		<table>
			<tr>
				<td>상품명(한글)</td>
				<td><input type="text" name="name_ko" id="name_ko" size="30"></td>
			<tr>
			<tr>
				<td>상품명(영어)</td>
				<td><input type="text" name="name_eng" id="name_eng" size="30"></td>
			<tr>
			<tr>
				<td>간략한 설명</td>
				<td><input type="text" name="subject" id="subject" size="30"></td>
			<tr>
			<tr>
				<td>상세 설명</td>
				<td><textarea name="content" id="content" rows="10" cols="50"></textarea></td>
			<tr>
			<tr>
				<td>음료 구분</td>
				<td>
					<input type="radio" name="hc_code" value="hot" checked="checked">HOT
					<input type="radio" name="hc_code" value="ice">ICE
				</td>
			</tr>
			<tr>
				<td>이벤트 아이콘</td>
				<td>
					<input type="radio" name="event_code" value="non" checked="checked">없음
					<input type="radio" name="event_code" value="new" >NEW
					<input type="radio" name="event_code" value="best" >BEST
				</td>
			</tr>
			<tr>
				<td>상품이미지</td>
				<td><input type="file" name="image" id="image">
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="상품등록" >
					<input type="button" value="취소" onClick="javascript:history.back()">
				</td>
			</tr>
		</table>
	
	</form>
</body>
</html>