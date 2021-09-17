<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>    
<%--updateForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript" src="script.js"></script>

<link rel="stylesheet" type="text/css" href="style.css">


</head>
<body>
 <h2>글수정</h2>
 <form name="writeForm" method="post" 
 action="${ctxpath}/board/updatePro.do?num=${num}&pageNum=${pageNum}">
 
 <table border="1">
 
   <tr>
     <td>글쓴이</td>
     <td width="800"><input type="text" name="writer" value="${dto.writer}" size="30"></td>
   </tr>
   
   <tr>
     <td>제목</td>
     <td><input type="text" name="subject" value="${dto.subject}" size="50"></td>
   </tr>
   
   <tr>
     <td>내용</td>
     <td>
     <textarea name="content" rows="31" cols="110">${dto.content}</textarea>
     </td>
   </tr>
     
   <tr>
     <td>암호</td>
     <td><input type="password" name="pw" size="15"></td>
   </tr>
   <tr>
     <td colspan="2" align="center">
      <input type="submit" value="글수정">&nbsp;
      <input type="reset">
      <input type="button" value="글목록" onClick="location='${ctxpath}/board/list.do'">
     </td>
   </tr>
 </table>
 </form>
</body>
</html>