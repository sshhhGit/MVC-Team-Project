<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>   
 
<%--content.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- <link rel="stylesheet" type="text/css" href="../static/style.css"> -->
</head>
<body>
<h2>글내용</h2>
<form action="">
 <table border="1">
 
   <tr>
     <td>글쓴이</td>
     <td width="800" >${dto.writer}</td>
   </tr>
   
   <tr>
     <td>제목</td>
     <td>${dto.subject}</td>
   </tr>
   
   <tr>
     <td>내용</td>
     <td height="500">
       ${dto.content}
     </td>
   </tr>
     
  <tr>
   <td colspan="2" align="center">
     <input type="button" value="글수정" 
     onClick="location='${ctxpath}/board/updateForm.do?num=${num}&pageNum=${pageNum}'">
     
     <input type="button" value="글삭제" 
     onClick="location='${ctxpath}/board/deleteForm.do?num=${num}&pageNum=${pageNum}'">
 
   <input type="button" value="글쓰기" onClick="location='${ctxpath}/board/writeForm.do'">
   
   <input type="button" value="답글 쓰기" 
   onClick="location='${ctxpath}/board/writeForm.do?num=${num}&pageNum=${pageNum}&ref=${ref}&re_step=${re_step }&re_level=${re_level}'">
   
   <input type="button" value="리스트" onClick="location='${ctxpath}/board/list.do?pageNum=${pageNum}'">
   
   </td>
  </tr>
 </table>
 
<%-- <table width="60%" border="1">
  <tr>
    <td>글번호</td>
    <td>${dto.num}</td>
  </tr>
  
  <tr>
    <td>조회수</td>
    <td>${dto.readcount}</td>
  </tr>
  
  <tr>
    <td>작성자</td>
    <td>${dto.writer}</td>
  </tr>
  
  <tr>
    <td>글제목</td>
    <td>${dto.subject}</td>
  </tr>
  
  <tr>
    <td>글내용</td>
    <td>
      <pre>${dto.content}</pre>
    </td>
  </tr>
  
  <tr>
   <td colspan="2" align="center">
     <input type="button" value="글수정" 
     onClick="location='${ctxpath}/board/updateForm.do?num=${num}&pageNum=${pageNum}'">
     
     <input type="button" value="글삭제" 
     onClick="location='${ctxpath}/board/deleteForm.do?num=${num}&pageNum=${pageNum}'">
 
   <input type="button" value="글쓰기" onClick="location='${ctxpath}/board/writeForm.do'">
   
   <input type="button" value="답글 쓰기" 
   onClick="location='${ctxpath}/board/writeForm.do?num=${num}&pageNum=${pageNum}&ref=${ref}&re_step=${re_step }&re_level=${re_level}'">
   
   <input type="button" value="리스트" onClick="location='${ctxpath}/board/list.do?pageNum=${pageNum}'">
   
   </td>
  </tr>
</table> --%>
</form>
</body>
</html>