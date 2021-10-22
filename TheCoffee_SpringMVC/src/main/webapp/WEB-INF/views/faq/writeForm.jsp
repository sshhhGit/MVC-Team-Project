<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp"%>
<%-- faq_writeForm.jsp --%>

<html>
<head>

<title>faq_writeForm.jsp</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript" src="${ctxpath}/resources/js/script.js"></script>

<link rel="stylesheet" type="text/css" href="${ctxpath}/resources/css/style.css">
</head>
<body>
<c:if test="${'admin' ne userId}">
<h1>관리자 권한이 필요합니다</h1>
</c:if>
<c:if test="${'admin' eq userId}">
<h2>FAQ 작성</h2>


<table width="400">
	<tr>
		<td align="right">
			<a href="${ctxpath}/faq/list.do">리스트</a>
		</td>
	</tr>
</table>

<form method="post" name="writeForm" action="${ctxpath}/faq/writePro.do" onSubmit="return faqWriteSave()">
  <input type="hidden" name="num" value="num">
  
 <table border="1">
 
 	<tr>
		<td>글쓴이</td>
		<td>
			<select style="border: 0;" name="faq_writer" id="faq_writer">
         		<option value="관리자">관리자</option>
         	</select>
		</td>
	</tr>
 
	<tr>
		<td>글제목</td>
		<td>
			<input type="text" name="faq_title" id="faq_title" size="40">
		</td>
	</tr>
	
	<tr>
		<td>분류</td>
		<td>
			<select style="border: 0;" name="faq_category" id="faq_category">
         		<option value="">분류 선택</option>
          		<option value="교환/반품/환불">교환/반품/환불</option>
          		<option value="회원가입/정보">회원가입/정보</option>
          		<option value="적립금관련">적립금관련</option>
          		<option value="주문/결제">주문/결제</option>
          		<option value="상품문의">상품문의</option>
          		<option value="배송관련">배송관련</option>
          		<option value="기타">기타</option>
        	</select>
		</td>
	</tr>
 
	<tr>
		<td>글내용</td>
		<td>
			<textarea name="faq_content" id="faq_content" rows="20" cols="80"></textarea>
		</td>
	</tr>
 
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="글쓰기">
			<input type="reset" value="취소">
			<input type="button" value="글목록" onClick="location='${ctxpath}/faq/list.do'">
		</td>
	</tr>
 </table>
  
</form>
</c:if>
</body>
</html>
