<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
    <%-- list.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list.jsp</title>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="product_script.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<%-- 등록된 상품이 없을 경우 --%>
	<c:if test="${count<=0}">
		<table width="100%" height="100%">
			<tr>
				<td align="center" valign="middle">등록된 상품이 없습니다.</td>
			</tr>
		</table>
	
	</c:if>
	
	<%-- 등록된 상품이 있는 경우 --%>
	<c:if test="${count>0}">
		
		<table width="1000" cellpadding="3">
		 <tr align="left">
				<td>상품목록(전체 상품:${count})</td>
			</tr>
		 
		 <tr>
		<!-- 상품 반복 시작 --> 
		  <c:set var="cnt" value="0"/>
		 	<c:forEach var="dto" items="${productList}">
    		<td>
    			<table>
    				<tr>
    					<td align="center">
    					<a href="${ctxpath}/product/productDetail.do?pro_no=${dto.pro_no}">
    					<img alt="" src="${ctxpath}/imgs/${dto.image}" width="300">
    					</a>
    					</td>
    				</tr>
    				<tr>
    					<td align="center">${dto.name_ko}<img alt="" src="${ctxpath}/imgs/${dto.hc_code}.png" height="17"></td>
    				</tr>
    				<tr>
    					<td align="center">${dto.name_eng}</td>
    				</tr>
    				<tr>
    					<td align="center">${dto.subject}</td>
    				</tr>
    			</table>
    		</td>
    		<c:set var="cnt" value="${cnt+1 }"/>
    		<c:if test="${cnt%3==0}">
    		  </tr>
    		  <tr>
    		</c:if>
			</c:forEach>
		
		 </tr>
		</table>
		
		<!-- 상품 반복 끝 -->
	</c:if>
	
</body>
</html>