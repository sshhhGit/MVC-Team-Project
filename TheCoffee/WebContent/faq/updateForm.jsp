<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/module/header.jsp" %>

<%--updateForm.jsp --%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript" src="script.js"></script>

<link rel="stylesheet" type="text/css" href="style.css">

<script type="text/javascript">

function init(){
	//분류 콤보박스 : 화면로드 시 서버에서 받아온 값으로 선택해준다. 
	dbOptVal = "${dto.category}";
	
	optCount = document.updateForm.category.options.length;
	
	for(i=0; i < optCount; i++) {
		optVal = document.updateForm.category.options[i].value;
	
		if(dbOptVal == optVal){ //서버에서 받아온 값과 같으면 select 처리
			document.updateForm.category.options[i].selected = "selected";
		}
	}
}
	
</script>
</head>
<body onLoad="init()">

<c:if test="${'admin' ne sessionScope.userId}">
	<h2>관리자 권한이 필요합니다</h2>
</c:if>

<c:if test="${'admin' eq sessionScope.userId}">	
	<form name="updateForm" method="post" action="${ctxpath}/faq/updatePro.do?num=${num}">
		
		<table width="60%" height ="80" cellpadding="2">
			<tr>
				<td align="left">
					<font size="+2">
						<strong>FAQ(자주묻는질문)</strong>
					</font>
				</td>
		
				<td align="right">
					<input type="submit" value="수정">&nbsp;
					<input type="reset" value="취소">&nbsp;
					<input type="button" value="목록" onClick="location='${ctxpath}/faq/list.do'">
				</td>
			</tr>
		</table>		
		
		<table width="65%" border="1" cellpadding="3">
			<tr>
				<td width="100">작성자</td>
				<td><input style="border: none; width: 100%;" type="text" name="writer" id="writer" value="${dto.writer}" size="30"></td>
			</tr>
			
			<tr>
				<td>제목</td>
				<td><input style="border: none; width: 100%;" type="text" name="subject" id="subject" value="${dto.subject}" size="70"></td>
			</tr>
					
			<tr>
				<td>분류</td>
				<td>
					<table align="left">
						<tr>					
							<td  >
								<select style="border: 1; width: 100%;" name="category" id="category" value="${dto.category}">
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
					<textarea style="border: none; width: 100%;" rows="10" cols="50" name="content" id="content"> ${dto.content}</textarea>
				</td>
			</tr>	
							
		</table>
	</form>
</c:if>

</body>
</html>