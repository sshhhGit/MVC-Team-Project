<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${ctxpath}/resources/js/script.js"></script>
<link rel="stylesheet" type="text/css" href="${ctxpath}/resources/css/style.css">

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

<title>Insert title here</title>
</head>
<body>
	<c:if test="${'admin' ne userId}">
		<h1>관리자 권한이 필요합니다</h1>
	</c:if>
	<c:if test="${'admin' eq userId}">
<form method="post" name="inputForm" action="${ctxpath}/product/updateProductPro.do" encType="multipart/form-data" onSubmit="return updateSave()">
		<table>
			<tr><td colspan="2" height="15" align="center"><h2>상품 수정</h2></td></tr>
			<tr>
				<td align="left" valign="top" width="350px">
					<div style="z-index: 50; position:absolute; "><img alt="" src="../resources/imgs/product/${pdto.event_code}.png" width="70"></div>
		    	<div style="z-index: 1;position:relative; width: 300; height: 300;">
		    		<img alt="" src="../resources/imgs/product/${pdto.image}" width="300" height="300">
					</div>
				</td>
				<td>
					<table id="input">
						<tr>
							<td id="ti">상품명(한글)</td>
							<td>
								<input type="hidden" name="pro_no" value="${pro_no}">
								<input type="text" name="name_ko" id="name_ko" size="30" value="${pdto.name_ko }">
							</td>
						<tr>
						<tr>
							<td id="ti">상품명(영어)</td>
							<td><input type="text" name="name_eng" id="name_eng" size="30" value="${pdto.name_eng }"></td>
						<tr>
						<tr>
							<td id="ti">간략한 설명</td>
							<td><input type="text" name="subject" id="subject" size="30" value="${pdto.subject }"></td>
						<tr>
						<tr>
							<td id="ti">상세 설명</td>
							<td><textarea name="content" id="content" rows="10" cols="50">${pdto.content}</textarea></td>
						<tr>
						<tr>
							<td id="ti">음료 구분</td>
							<td>
								<c:if test="${pdto.hc_code=='hot'}">
									<input type="radio" name="hc_code" value="hot" checked="checked">HOT
									<input type="radio" name="hc_code" value="ice">ICE
								</c:if>
								<c:if test="${pdto.hc_code=='ice'}">
									<input type="radio" name="hc_code" value="hot">HOT
									<input type="radio" name="hc_code" value="ice" checked="checked">ICE
								</c:if>
							</td>
						</tr>
						<tr>
							<td id="ti">이벤트 아이콘</td>
							<td>
								<c:if test="${pdto.event_code=='non'}">
									<input type="radio" name="event_code" value="non" checked="checked">없음
									<input type="radio" name="event_code" value="new" >NEW
									<input type="radio" name="event_code" value="best" >BEST
								</c:if>
								<c:if test="${pdto.event_code=='new'}">
									<input type="radio" name="event_code" value="non" >없음
									<input type="radio" name="event_code" value="new" checked="checked">NEW
									<input type="radio" name="event_code" value="best" >BEST
								</c:if>
								<c:if test="${pdto.event_code=='best'}">
									<input type="radio" name="event_code" value="non" >없음
									<input type="radio" name="event_code" value="new" >NEW
									<input type="radio" name="event_code" value="best" checked="checked">BEST
								</c:if>
							</td>
						</tr>
						<tr>
							<td id="ti">상품이미지</td>
							<td>
								<input type="hidden" name="image" id="" value="${pdto.image }">
								<input type="file" name="image_file" id="image_file">
							</td>
						</tr>
					</table>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center" id="inbtn" height="70px">
				<input type="submit" value="상품수정" >
				<input type="button" value="취소" onClick="javascript:history.back()">
			</td>
		</tr>
		<tr><td colspan="2" height="20"></td></tr>
	</table>
	
	</form>
	</c:if>
</body>
</html>