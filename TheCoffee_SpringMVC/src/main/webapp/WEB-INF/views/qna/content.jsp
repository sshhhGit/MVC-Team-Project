<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp" %>
<%-- content.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>content.jsp</title>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${ctxpath }/resources/js/script.js"></script>
<link rel="stylesheet" type="text/css" href="${ctxpath }/resources/style.css">
</head>
<body>

<%-- <c:set var="sessionCurrentUser" value='<%=(String)session.getAttribute("user_id")%>'/> --%>
<%-- <c:set var="sessionCurrentAdmin" value='<%=(String)session.getAttribute("admin_id")%>'/> --%>
<%-- <input type="hidden" name="current_user_id" value="<%=session.getAttribute("user_id")%>"> --%>
<br>

<div align="center">
  <h2>Q&A 글 내용보기</h2>
  <font size="5"><b>글번호</b>&nbsp;${qnaDto.num}</font>
</div>
<br><br><br>

<table width="40%" border="1" cellpadding="10" cellspacing="0">
 <tr>
  <td align="center"><b>질문자</b></td>
  <td align="center">${qnaDto.user_id}</td>
  <td align="center"><b>작성일</b></td>
  <td align="center">${qnaDto.user_regdate}</td>
 </tr>
 
 <tr>
  <td align="left" colspan="6" height="100"><xmp>${qnaDto.user_content}</xmp></td>
 </tr>
</table>

<c:if test="${userId!=null and userId!='admin'}">
<%
//  if (session.getAttribute("user_id") != null) {
	 %>
	 <br>
<table width="40%">
 <tr>
  <td colspan="2" align="center">
  <input type="button" value="새 질문작성" onclick="location='${ctxpath}/qna/writeForm.do'">
  
  <c:if test="${qnaDto.user_id==userId}">
   <input type="button" value="질문수정" onclick="location='${ctxpath}/qna/updateForm.do?num=${qnaDto.num}&pageNum=${pageNum}'">
   <input type="button" value="질문삭제" onclick="location='${ctxpath}/qna/deleteForm.do?num=${qnaDto.num}&pageNum=${pageNum}&user_id=${qnaDto.user_id}'">
  </c:if>
  </td>
 </tr>
</table>
</c:if>
	 <%
//  }
%>
 <br><br><br>
 
<c:if test="${qnaDto.admin_id!=null and qnaDto.admin_content!=null and qnaDto.admin_regdate!=null}">
 <table width="40%" border="1" cellpadding="10" cellspacing="0">
 <tr>
  <td align="center"><b>관리자</b></td>
  <td align="center">${qnaDto.admin_id}</td>
  <td align="center"><b>작성일</b></td>
  <td align="center">${qnaDto.admin_regdate}</td>
 </tr>
 
 <tr>
  <td colspan="6" height="100"><font color="blue"><xmp>${qnaDto.admin_content}</xmp></font></td>
 </tr>
 </table>
</c:if>

<c:if test="${userId!=null}">
<%
//  if (session.getAttribute("admin_id") != null) {
	 %>
	 <br>
 <table width="40%">
 <tr>
  <td colspan="2" align="center">
  <c:if test="${qnaDto.admin_content==null and userId=='admin'}">
   <input type="button" value="답변작성" onclick="location='${ctxpath}/qna/writeForm2.do?num=${qnaDto.num}'">
  </c:if>
  <c:if test="${qnaDto.admin_content!=null and qnaDto.admin_id==userId}">
   <input type="button" value="답변수정" onclick="location='${ctxpath}/qna/updateForm2.do?num=${qnaDto.num}&pageNum=${pageNum}'">
   <input type="button" value="답변삭제" onclick="location='${ctxpath}/qna/deleteForm2.do?num=${qnaDto.num}&pageNum=${pageNum}&admin_id=${qnaDto.admin_id}'">
  </c:if>
  </td>
 </tr>
</table>
</c:if>
 <%
//  }
%>

<br><br><br>

<div align="center">
  <input type="button" value="뒤로가기" onclick="history.back()">
  <input type="button" value="리스트" onclick="location='${ctxpath}/qna/list.do?pageNum=${pageNum}'">
</div>
<br><br>

</body>
</html>