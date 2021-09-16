<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%-- content.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>content.jsp</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="script.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>

<h2>Q&A 글 내용보기</h2>
<font size="5"><b>글번호</b>&nbsp;${dto.num}</font><br><br>
<table width="60%" border="1">
 <tr>
  <td><b>질문자</b></td>
  <td>${dto.user_id}</td>
  <td><b>작성일</b></td>
  <td>${dto.user_regdate}</td>
 </tr>
 
 <tr>
  <td colspan="6"><pre>${dto.user_content}</pre></td>
 </tr>
</table>
 
<table>
 <tr>
  <td colspan="2" align="center">
   <input type="button" value="글작성" onclick="location='${ctxpath}/qna/writeForm.do'">
   <input type="button" value="글수정" onclick="location='${ctxpath}/qna/updateForm.do?num=${num}&pageNum=${pageNum}'">
   <input type="button" value="글삭제" onclick="location='${ctxpath}/qna/deleteForm.do?num=${num}&pageNum=${pageNum}&user_id=${dto.user_id}'">
   <input type="button" value="리스트" onclick="location='${ctxpath}/qna/list.do?pageNum=${pageNum}'">
  </td>
 </tr>
</table>
 
 <br><br><br>
 
<c:if test="${admin_null_check!=0}">
 <table width="60%" border="1">
 <tr>
  <td><b>관리자</b></td>
  <td>${dto.admin_id}</td>
  <td><b>작성일</b></td>
  <td>${dto.admin_regdate}</td>
 </tr>
 
 <tr>
  <td colspan="6"><font color="blue"><pre>${dto.admin_content}</pre></font></td>
 </tr>
 </table>
</c:if>


<table>
 <tr>
  <td colspan="2" align="center">
   <input type="button" value="글작성" onclick="location='${ctxpath}/qna/writeForm.do'">
   <input type="button" value="글수정" onclick="location='${ctxpath}/qna/updateForm.do?num=${num}&pageNum=${pageNum}'">
   <input type="button" value="글삭제" onclick="location='${ctxpath}/qna/deleteForm.do?num=${num}&pageNum=${pageNum}&user_id=${dto.user_id}'">
   <input type="button" value="리스트" onclick="location='${ctxpath}/qna/list.do?pageNum=${pageNum}'">
  </td>
 </tr>
</table>

</body>
</html>