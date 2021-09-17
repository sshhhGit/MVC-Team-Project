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

<input type="hidden" name="current_user_id" value="<%=session.getAttribute("user_id")%>">
<br>
<h2>Q&A 글 내용보기</h2>
<font size="5"><b>글번호</b>&nbsp;${dto.num}</font><br><br><br>
<table width="40%" border="1" cellpadding="10" cellspacing="0">
 <tr>
  <td align="center"><b>질문자</b></td>
  <td align="center">${dto.user_id}</td>
  <td align="center"><b>작성일</b></td>
  <td align="center">${dto.user_regdate}</td>
 </tr>
 
 <tr>
  <td colspan="6" height="100"><pre>${dto.user_content}</pre></td>
 </tr>
</table>
<%
 if (session.getAttribute("user_id") != null) {
	 %>
	 <br>
<table width="40%">
 <tr>
  <td colspan="2" align="center">
  <input type="button" value="새글작성" onclick="location='${ctxpath}/qna/writeForm.do'">
  <c:if test="${x==1}">
   <input type="button" value="글수정" onclick="location='${ctxpath}/qna/updateForm.do?num=${num}&pageNum=${pageNum}'">
   <input type="button" value="글삭제" onclick="location='${ctxpath}/qna/deleteForm.do?num=${num}&pageNum=${pageNum}&user_id=${dto.user_id}'">
  </c:if>
  </td>
 </tr>
</table>
	 <%
 }
%>
 <br><br><br>
 
<c:if test="${dto.admin_id!=null and dto.admin_content!=null and dto.admin_regdate!=null}">
 <table width="40%" border="1" cellpadding="10" cellspacing="0">
 <tr>
  <td align="center"><b>관리자</b></td>
  <td align="center">${dto.admin_id}</td>
  <td align="center"><b>작성일</b></td>
  <td align="center">${dto.admin_regdate}</td>
 </tr>
 
 <tr>
  <td colspan="6" height="100"><font color="blue"><pre>${dto.admin_content}</pre></font></td>
 </tr>
 </table>
</c:if>

<%
 if (session.getAttribute("admin_id") != null) {
	 %>
	 <br>
 <table width="40%">
 <tr>
  <td colspan="2" align="center">
  <c:if test="${dto.admin_content==null}">
   <input type="button" value="답변작성" onclick="location='${ctxpath}/qna/writeForm2.do?num=${num}'">
  </c:if>
  <c:if test="${dto.admin_content!=null and y==1}">
   <input type="button" value="답변수정" onclick="location='${ctxpath}/qna/updateForm2.do?num=${num}&pageNum=${pageNum}'">
   <input type="button" value="답변삭제" onclick="location='${ctxpath}/qna/deleteForm2.do?num=${num}&pageNum=${pageNum}&admin_id=${dto.admin_id}'">
  </c:if>
  </td>
 </tr>
</table>
 <%
 }
%>

<br><br><br>

<input type="button" value="뒤로가기" onclick="history.back()">
<input type="button" value="리스트" onclick="location='${ctxpath}/qna/list.do?pageNum=${pageNum}'">

<br><br>
</body>
</html>