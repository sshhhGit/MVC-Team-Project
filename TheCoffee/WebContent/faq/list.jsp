<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/module/header.jsp" %>
    
<%--list.jsp --%>
<fmt:requestEncoding value="UTF-8"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="script.js"></script>
<link rel="stylesheet" type="text/css" href="../static/style.css">

<script type="text/javascript">

//검색어 처리
function check(){
	if(document.searchForm.keyWord.value==''){
		alert("검색어를 입력 하세요");
		document.searchForm.searchText.focus();
		return false;
	}//if-end
	
	location.href="${ctxpath}/faq/list.do?category="+ $('#category').val() + "&searchText="+$('#searchText').val();
	document.searchForm.submit();	//전송
}//check-end	

function listReload(){
	//$('#category').val('');
	$('#searchText').val('');
	
	
	document.searchForm.category.options[0].selected = "selected";
	
		
	location.href="${ctxpath}/faq/list.do?category="+ $('#category').val() + "&searchText="+$('#searchText').val();
	document.searchForm.submit();	//전송	
}

function init(){
	//분류 콤보박스 : 화면로드 시 서버에서 받아온 값으로 선택해준다. 	
	paramVal = "${category}";
	
	optCount = document.searchForm.category.options.length;
	
	for(i=0; i < optCount; i++) {
		optVal = document.searchForm.category.options[i].value;
	
		if(paramVal == optVal){ //파라미터 값과 같으면 select 처리
			document.searchForm.category.options[i].selected = "selected";
		}
	}
}

</script>

</head>
<body onLoad="init()">

<form name="searchForm" method="get" onSubmit="return check()">
<table class="table" cellpadding="5">
	<tr>
		<td align="left">
			<font size="+2">
				<strong>FAQ</strong><font size="-1">&nbsp;&nbsp;(전체글: ${count}) </font>
			</font>
		</td>

		<td align="right">
					
			<%--관리자인 경우 --%>
			<c:if test="${'admin' eq sessionScope.userId }">				
				<a href="${ctxpath}/faq/inputForm.do">FAQ추가</a>	
			</c:if>
			&nbsp;&nbsp;
			<a href="javascript:listReload()">새로고침</a>						
		</td>				
	</tr>
	
	<tr>
		<td colspan="2" align="right">
			분류 :
			<select name="category" id="category">
				<option value="" selected>전체</option>
				<option value="회원가입/정보">회원가입/정보</option>
				<option value="상품문의">상품문의</option>
				<option value="주문/결제">주문/결제</option>
				<option value="배송관련">배송관련</option>
				<option value="교환/반품/환불">교환/반품/환불</option>
				<option value="적립금관련">적립금관련</option>
				<option value="기타">기타</option>
			</select>			
			검색어 :
			<input type="text" name="searchText" id="searchText" value="${searchText}" size="20">
			<input type="submit" value="검색">						
		</td>
	</tr>
</table>	

<c:if test="${count == 0}">
	<center>
		<font size="+2" color="blue">
			<br><br><br>FAQ에 등록된 글이 없습니다
		</font>
	</center>
	
</c:if>

<c:if test="${count > 0}">
	<table class="table" width="60%" border="1">
		<tr align="center">
			<td>번호</td>
			<td>분류</td>			
			<td>제목</td>
			<td>조회수</td>
		</tr>
		
		<c:forEach  var="dto" items="${faqList}">
		<tr>
			<td align="center">${dto.num}</td>
			<td>${dto.category}</td>
			<td>
				<a href="${ctxpath}/faq/content.do?num=${dto.num}">
					${dto.subject}
				</a>			
			</td>
			<td align="center">${dto.readcount}</td>
		</tr>
		</c:forEach>
	</table>
</c:if>
</form>

</body>
</html>