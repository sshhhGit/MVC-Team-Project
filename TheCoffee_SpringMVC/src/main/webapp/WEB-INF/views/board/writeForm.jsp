<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp" %>    
<!-- writeForm.jsp -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript" src="${ctxpath }/resources/js/script.js"></script>

<link rel="stylesheet" type="text/css" href="../resources/css/style.css">
</head>
<body>

<c:if test="${num==0}">
<h2>글쓰기 폼</h2>
</c:if>

<c:if test="${num!=0}">
<h2>답글쓰기</h2>
</c:if>

 <table width="800">
    <tr>
     <td align="right">
      <a href="${ctxpath}/board/list.do">리스트</a>
     </td>
    </tr>
  </table>

<form method="post" name="writeForm" action="${ctxpath}/board/writePro.do" onSubmit="return writeSave()">
  <input type="hidden" name="num" value="${num}">
  <input type="hidden" name="ref" value="${ref}">
  <input type="hidden" name="re_step" value="${re_step}">
  <input type="hidden" name="re_level" value="${re_level}">
  
 <table border="1" id="board_write_t">
   <tr>
     <th>글쓴이</th>
     <td><input type="text" name="writer" id="writer" size="30"></td>
   </tr>
 
   <tr>
     <th>글제목</th>
     <td>
      <c:if test="${num==0}">
        <input type="text" name="subject" id="subject" size="40">
      </c:if>
     
      <c:if test="${num!=0 }">
        <input type="text" name="subject" id="subject" size="40" value="[답변]">
      </c:if>
     </td>
   </tr>
 
   <tr>
     <th>글내용</th>
     <td>
     <textarea name="content" id="content" rows="10" cols="90"></textarea>
     </td>
   </tr>
 
   <tr>
     <th>암호</th>
     <td><input type="password" name="pw" id="pw" size="15"></td>
   </tr>
 
   <tr>
    <td colspan="2" align="center">
      <c:if test="${num==0}">
         <input type="submit" value="글쓰기">
      </c:if>
      
      <c:if test="${num!=0}">
      <input type="submit" value="답글쓰기">
      </c:if>
      
      <input type="reset" value="취소">
      <input type="button" value="글목록" onClick="location='${ctxpath}/board/list.do'">
    </td>
   </tr>
 </table>
  
</form>
</body>
</html>