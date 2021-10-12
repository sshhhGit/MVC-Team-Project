<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${ctxpath}/resources/js/script.js"></script>
<link rel="stylesheet" type="text/css" href="${ctxpath}/resources/css/style.css">
<title>상품 정보</title>
<style type="text/css">
#pd{
	height: 30px;
	border-bottom: 1px solid #913F09;
	color: #913F09;
}

#pd2{
	padding: 10px 0px 10px 0px;
	border-bottom: 1px solid #913F09;
	color: #200E02;
}

</style>

<script type="text/javascript">
function confirm() {
	const result = confirm('삭제하시겠습니까?');	
}

</script>
</head>
<body>
	<table width="800">
		<tr>
			<td height="50"></td>
		</tr>
		<tr>
			<td align="center">
				<div style="z-index: 50; position:absolute; "><img alt="" src="../resources/imgs/product/${pdto.event_code}.png" width="80"></div>
	    	<div style="z-index: 1;position:relative; width: 400; height: 400;">
	    		<img alt="" src="../resources/imgs/product/${pdto.image}" width="400" height="400">
				</div>
			</td>
			<td width="15">
			</td>
			<td align="left" valign="top">
				<table width="370">
					<tr width="370">
						<td id="pd"><b>${pdto.name_ko }</b>&nbsp;<img alt="" src="../resources/imgs/product/${pdto.hc_code}.png" height="19"></td>
					</tr>
					<tr>
						<td id="pd">${pdto.name_eng }</td>
					</tr>
					<tr>
						<td id="pd">${pdto.subject }</td>
					</tr>
					<tr>
						<td id="pd2">${pdto.content }</td>
					</tr>

					<tr>
						<td align="center" height="50">
						<c:if test="${'admin' eq userId}">
						<input type="button" value="상품수정" onClick="location.href='${ctxpath}/product/updateProductForm.do?pro_no=${pdto.pro_no}'">
						<input type="button" value="상품삭제" onClick="location.href='${ctxpath}/product/deleteProduct.do?pro_no=${pdto.pro_no}'">
						</c:if>
						<input type="button" value="상품목록" onClick="location.href='${ctxpath}/product/list.do'"> 
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="50"></td>
		</tr>
	</table>
</body>
</html>
