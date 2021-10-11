<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp"%>    
<%-- faq_updateForm.jsp --%>
<html>
<head>

<title>updateForm</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript" src="../resources/js/script.js"></script>

<link rel="stylesheet" type="text/css" href="../resources/css/style.css">


</head>
<body>
 <h2>글수정 폼</h2>

 <form name="updateForm" method="post" 
 action="${ctxpath}/faq/updatePro.do" onSubmit="return writeSave()">
 
 <table border="1" id="notice_update_t">
   <tr>
     <th>글제목</th>
     <td>
     <input type="text" name="faq_title" id="faq_title" value="${fdto.faq_title}" size="50">
     <input type="hidden" name="faq_no" value="${fdto.faq_no}">
     </td>
   </tr>
   
   <tr>
     <th>분류</th>
     <td>
     	<select style="border: 0;" name="faq_category" id="faq_category">
         		<option value="${fdto.faq_category}">${fdto.faq_category}</option>
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
     <th>글내용</th>
     <td>
     <textarea name="faq_content" id="faq_content" rows="20" cols="90">${fdto.faq_content}</textarea>
     </td>
   </tr>

   <tr>
     <td colspan="2" align="center">
      <input type="submit" value="글수정">
      <input type="reset">
      <input type="button" value="글목록" onClick="location='${ctxpath}/faq/list.do'">
     </td>
   </tr>
 </table>
 </form>
</body>
</html>