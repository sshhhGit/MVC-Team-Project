<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
    <%-- productDetail.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="product_script.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<table width="700">
		<tr>
			<td colspan="1" height="50"></td>
		</tr>
		<tr>
			<td>
				<img alt="" src="${ctxpath}/imgs/${dto.image}" width="400">
			</td>
			<td align="left">
				<table border="1">
					<tr>
						<td>${dto.name_ko }<img alt="" src="${ctxpath}/imgs/${dto.hc_code}.png" height="19"></td>
					</tr>
					<tr>
						<td>${dto.name_eng }</td>
					</tr>
					<tr>
						<td>${dto.subject }</td>
					</tr>
					<tr>
						<td>${dto.content }</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="돌아가기" onclick="javascript:history.back()">
				<%-- <input type="button" value="수정하기">--%>
			</td>
		</tr>
	</table>

</body>
</html>