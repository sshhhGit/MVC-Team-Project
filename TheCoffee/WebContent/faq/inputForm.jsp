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
<link rel="stylesheet" type="text/css" href="../static/style.css">

</head>
<body>

<c:if test="${'admin' ne sessionScope.userId}">
	<h2>관리자 권한이 필요합니다</h2>
</c:if>

<c:if test="${'admin' eq sessionScope.userId}">	
	<form name="inputForm" method="post" action="${ctxpath}/faq/inputPro.do" onSubmit="return inputSave()"> 
		<input type="hidden" name="num" value="${num}"/>
		
		<table width="60%" height ="80" cellpadding="3">
			<tr>
				<td align="left">
					<font size="+2">
						<strong>FAQ(자주묻는질문)</strong>
					</font>
				</td>
		
				<td align="right">
					<input type="submit" value="입력">
					<input type="reset" value="취소">
					<input type="button" value="목록" onClick="location='${ctxpath}/faq/list.do'">
				</td>
			</tr>			
		</table>
		
		<table width="60%" border="1" cellpadding="3">
			<tr>
				<td>작성자</td>
				<td><input style="border: none; width: 100%;" type="text" name="writer" id="writer" size="30"></td>
			</tr>
			
			<tr>
				<td>제목</td>
				<td><input style="border: none; width: 100%;" type="text" name="subject" id="subject" size="70"></td>
			</tr>
			
			<tr>
				<td>분류</td>
				<td>
					<table align="left">
						<tr>					
							<td>			
						<select style="border: 1; width: 100%;" name="category" id="category">
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
					</table>					
				</td>
			</tr>
			
			<tr>
				<td>내용</td>
				<td>
					<textarea style="border: none; width: 100%;" name="content" id="content" rows="10" cols="50"></textarea>
				</td>
			</tr>	
						
		</table>
	</form>
</c:if>

</body>
</html>