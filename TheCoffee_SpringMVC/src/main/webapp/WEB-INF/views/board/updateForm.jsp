<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp"%>    
<%--updateForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript" src="../resources/js/script.js"></script>

<link rel="stylesheet" type="text/css" href="../resources/css/style.css">


</head>
<body>
 <h2>글수정 폼</h2>
 <form name="writeForm" method="post" 
 action="${ctxpath}/board/updatePro.do" onSubmit="return writeSave()">
 
 <table border="1" id="board_update_t">
 
   <tr>
     <th>글쓴이</th>
     <td><input type="text" name="writer" id="writer" value="${bdto.writer}" size="30">
   		 <input type="hidden" name="num" value="${num }">  
   		 <input type="hidden" name="pageNum" value="${pageNum }">  
     </td>
   </tr>
   
   <tr>
     <th>글제목</th>
     <td><input type="text" name="subject" id="subject" value="${bdto.subject}" size="50"></td>
   </tr>
   
   <tr>
     <th>글내용</th>
     <td>
     <textarea name="content" id="content" rows="10" cols="90">${bdto.content}</textarea>
     </td>
   </tr>
     
   <tr>
     <th>암호</th>
     <td><input type="password" name="pw" id="pw" size="15" placeholder="필수입력"></td>
   </tr>
   
   <tr>
     <td colspan="2" align="center">
      <input type="submit" value="글수정">
      <input type="reset">
      <input type="button" value="글목록" onClick="location='${ctxpath}/board/list.do'">
     </td>
   </tr>
 </table>
 </form>
</body>
</html>