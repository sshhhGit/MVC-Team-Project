<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${ctxpath}/resources/js/script.js"></script>
<link rel="stylesheet" type="text/css" href="../resources/css/style.css">

<style type="text/css">

#input td{
	padding: 10px 0px 10px 0px;
}
#inbtn{
	border-top: 1px solid #913F09;
}
#ti{
	font-weight: bold;
	color: #913F09;
}
h2{
color: #913F09;
}

input[type="text"] {
	background-color: #fff2ec;
	height: 22px;
	border-width: thin;
}

textarea{
 background-color: #fff2ec;
}

</style>
</head>
<body>
	<c:if test="${'admin' ne userId}">
		<h1>관리자 권한이 필요합니다</h1>
	</c:if>
	<c:if test="${'admin' eq userId}">
<form method="post" name="inputForm" action="${ctxpath}/product/inputProductPro.do" encType="multipart/form-data" onSubmit="return inputSave()">
		<input type="hidden" name="pro_no" value="0">
		
		<table id="input">
			<tr><td colspan="2" height="15" align="center"><h2>상품 등록</h2></td></tr>
			<tr>
				<td id="ti">상품명(한글)</td>
				<td><input type="text" name="name_ko" id="name_ko" size="30"></td>
			<tr>
			<tr>
				<td id="ti">상품명(영어)</td>
				<td><input type="text" name="name_eng" id="name_eng" size="30"></td>
			<tr>
			<tr>
				<td id="ti">간략한 설명</td>
				<td><input type="text" name="subject" id="subject" size="30"></td>
			<tr>
			<tr>
				<td id="ti">상세 설명</td>
				<td><textarea name="content" id="content" rows="10" cols="50"></textarea></td>
			<tr>
			<tr>
				<td id="ti">음료 구분</td>
				<td>
					<input type="radio" name="hc_code" value="hot" checked="checked">HOT
					<input type="radio" name="hc_code" value="ice">ICE
				</td>
			</tr>
			<tr>
				<td id="ti">이벤트 아이콘</td>
				<td>
					<input type="radio" name="event_code" value="non" checked="checked">없음
					<input type="radio" name="event_code" value="new" >NEW
					<input type="radio" name="event_code" value="best" >BEST
				</td>
			</tr>
			<tr>
				<td id="ti">상품이미지</td>
				<td><input type="file" name="image_file" id="image_file">
			</tr>
			<tr>
				<td colspan="2" align="center" id="inbtn">
					<input type="submit" value="상품등록" >
					<input type="button" value="취소" onClick="javascript:history.back()">
				</td>
			</tr>
			<tr><td colspan="2" height="20"></td></tr>
		</table>
	
	</form>
	</c:if>
</body>
</html>
