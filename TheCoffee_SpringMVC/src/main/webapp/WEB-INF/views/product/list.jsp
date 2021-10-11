<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../resources/js/product_script.js"></script>
<link rel="stylesheet" type="text/css" href="../resources/css/style.css">

<style type="text/css">
#ti{
	color: #913F09;
	font-weight: bold;
}

#ti2{
	color: #913F09;
}

</style>

<title>상품소개</title>
</head>
<body>
	<%-- 등록된 상품이 없을 경우 --%>
	<c:if test="${cnt<=0}">
		<table width="100%" height="100%">
			<tr>
				<td align="center" valign="middle" height="700px"><h2>등록된 상품이 없습니다.</h2></td>
			</tr>
		</table>
	
	</c:if>
	
	<%-- 등록된 상품이 있는 경우 --%>
	<c:if test="${cnt>0}">
		<table width="1000" cellpadding="3">
			<tr><td height="20"></td></tr>
			<tr>
				<td align="left" id="ti">전체 상품 : ${cnt}</td>
				<c:if test="${'admin' eq userId}">
				<td align="right">
					<input type="button" value="상품등록" onClick="location.href='${ctxpath}/product/inputProductForm.do'">
				</td>
				</c:if>
			</tr>
		</table>
		<table width="1000" cellpadding="3">
		 <tr>
		<!-- 상품 반복 시작 --> 
		  <c:set var="cnt" value="0"/>
		 	<c:forEach var="pdto" items="${list}">
    		<td>
    			<table>
    				<tr>
	    				<td align="center">
	    					<div style="z-index: 50; position:absolute; "><img alt="" src="../resources/imgs/product/${pdto.event_code}.png" width="70"></div>
	    					<div style="z-index: 1;position:relative; width: 300; height: 300;">
	    						<a href="${ctxpath}/product/productDetail.do?pro_no=${pdto.pro_no}">
	    							<img alt="" src="../resources/imgs/product/${pdto.image}" width="300" height="300">
	    						</a>
	    					</div>
	    				</td>
    				</tr>
    				<tr>
    					<td align="center" id="ti">${pdto.name_ko}&nbsp;<img alt="" src="../resources/imgs/product/${pdto.hc_code}.png" height="17"></td>
    				</tr>
    				<tr>
    					<td align="center" id="ti2">${pdto.name_eng}</td>
    				</tr>
    				<tr>
    					<td align="center">${pdto.subject}</td>
    				</tr>
    				<tr>
    					<td height="10"></td>
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
		 <tr><td height="20"></td></tr>
		</table>
		
		<!-- 상품 반복 끝 -->
	</c:if>
</body>
</html>