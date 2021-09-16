<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<%--inputForm.jsp --%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="script.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>

<h2>FAQ</h2>
<form name="inputForm" method="post" action="${ctxpath}/faq/inputPro.do" onSubmit="return inputSave()"> 
	<input type="hidden" name="num" value="${num}"/>
	
	<table width="65%" border="1" cellpadding="3">
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer" id="writer" size="30"></td>
		</tr>
		
		<tr>
			<td>제목</td>
			<td><input type="text" name="subject" id="subject" size="70"></td>
		</tr>
		
		<tr>
			<td>분류</td>
			<td>
				<select name="category" id="category">
					<option value="">선택하세요</option>
					<option value="회원가입/정보">회원가입/정보</option>
					<option value="상품문의">상품문의</option>
					<option value="주문/결제">주문/결제</option>
					<option value="배송관련">배송관련</option>
					<option value="교환/반품/환불">교환/반품/환불</option>
					<option value="적립금관련">적립금관련</option>
					<option value="기타">기타</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<td>내용</td>
			<td>
				<textarea name="content" id="content" rows="10" cols="50"></textarea>
			</td>
		</tr>	
					
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="저장">
				<input type="reset" value="다시입력">
				<input type="button" value="FAQ목록" onClick="location='${ctxpath}/faq/list.do'">
			</td>
		</tr>		
	</table>
</form>

</body>
</html>