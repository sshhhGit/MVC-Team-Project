<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../module/jsp-header.jsp" %>
<%--list.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list.jsp</title>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${ctxpath }/resources/js/script.js"></script>
<link rel="stylesheet" type="text/css" href="${ctxpath}/resources/css/style.css">
</head>
<body>

<%
// session.setAttribute("admin_id", "admin1"); // 세션에 등록(관리자)
// session.setAttribute("user_id", "user1"); // 세션에 등록(유저)
// session.setAttribute("admin_id", "admin"); // 세션에 등록(관리자)
// session.setAttribute("user_id", "hong11"); // 세션에 등록(유저)
%>

<%
String receivedSearch = request.getParameter("search");
if (receivedSearch == null || receivedSearch == "") {
	receivedSearch = "";
}
%>

<br>
 <h2>Q&A</h2><c:if test="${count!=null and count!=0}">총 ${count}개의 글</c:if>
 
 <table>
<%--    <tr>
    <td align="left">
      <b>현재 session : <font color="blue">
       <c:if test="${userId==null}">비회원상태</c:if>
       <c:if test="${userId!=null}">
         ${userId}
       </c:if>
	    <%
// 	     if (session.getAttribute("user_id") != null) {
// 	    	 out.print(session.getAttribute("user_id"));
// 	     } else if (session.getAttribute("admin_id") != null) {
// 	    	 out.print(session.getAttribute("admin_id"));
// 	     } else {
// 	    	 out.print("비회원상태");
// 	     }
	    %>
     </font></b>
    </td>
   </tr> --%>
   
   <tr>
    <td align="right">
      <c:if test="${userId!=null and userId!='admin'}">
        <input type="button" value="새 질문작성" onclick="location='${ctxpath}/qna/writeForm.do'"><br><br>
      </c:if>
    
     <%
//       if (session.getAttribute("user_id") != null) {
    	  %>
<%--         <input type="button" value="질문작성" onclick="location='${ctxpath}/qna/writeForm.do'"><br><br> --%>
    	  <%
//       }
     %>
    </td>
   </tr>
 </table>
 <br>
 
 <c:if test="${count==null}">
    글이 없다. count==null이다.
 </c:if>
 
 <c:if test="${count==0}">
   <center>Q&A에 등록된 글이 없습니다.</center>
 </c:if>
 
 <c:if test="${count>0}">
   <table id="qna_list_t">
     <tr>
       <th>글번호</th>
       <th>질문</th>
     </tr>
     
     <c:forEach var="qnaDto" items="${list}">
       <tr>
        <%-- 글번호 --%>
        <td align="center">
          <c:out value="${qnaDto.num}"/>
        </td>
        
        <%-- 글내용 --%>
        <td align="left">
         <a href="${ctxpath}/qna/content.do?num=${qnaDto.num}&current_user_id=<%=session.getAttribute("user_id")%>&current_admin_id=<%=session.getAttribute("admin_id")%>&pageNum=${pageNum}">
           <xmp>${qnaDto.user_content}</xmp>
         </a>
        </td>
       </tr>
     </c:forEach>
   </table>
 </c:if>
 <br><br>
 


 
<%-- 페이지처리, 블럭처리 --%>
<form name="searchForm" action="${ctxpath}/qna/list.do">
<table align="center">
  <tr>
    <td align="center">
      <c:if test="${count>0}">
	    <!-- 이전 블럭 -->
	    <c:if test="${pageTest.startPage>pageTest.pageSize}">
	      <a href="${ctxpath}/qna/list.do?pageNum=${pageTest.startPage-10}&search=<%=receivedSearch%>">≪</a>
	    </c:if>
	    <!-- 이전 페이지 -->
	    <c:if test="${pageTest.startPage>pageTest.pageSize}">
	      <a href="${ctxpath}/qna/list.do?pageNum=${pageNum-1}&search=<%=receivedSearch%>">＜</a>
	    </c:if>
	    <!-- 페이지 처리 -->
	    <c:forEach var="i" begin="${pageTest.startPage}" end="${pageTest.endPage}">
	      <a href="${ctxpath}/qna/list.do?pageNum=${i}&search=<%=receivedSearch%>">[${i}]</a>
	    </c:forEach>
	    <!-- 다음 페이지 -->
	    <c:if test="${pageTest.endPage<pageTest.pageCnt}">
	      <a href="${ctxpath}/qna/list.do?pageNum=${pageNum+1}&search=<%=receivedSearch%>">＞</a>
	    </c:if>
	    <!-- 다음 블럭 -->
	    <c:if test="${pageTest.endPage<pageTest.pageCnt}">
	      <a href="${ctxpath}/qna/list.do?pageNum=${pageTest.startPage+10}&search=<%=receivedSearch%>">≫</a>
	    </c:if>
      </c:if>
    </td>
  </tr>
  
  <tr>
    <td align="center">
      <c:if test="${count!=0}">
         ${pageNum}/${pageTest.pageCnt} 페이지
      </c:if>
    </td>
  </tr>
  
  <tr>
    <td align="center">
      <br>
        <c:if test="${count>0}">
<%--   <input type="search" name="search" onchange="location='${ctxpath}/qna/list.do'"> --%>
  <input type="search" name="search" value="<%=receivedSearch%>">
  <input type="submit" value="검색">
        </c:if>
    </td>
  </tr>
</table>
</form>
<br><br><br>
 
</body>
</html>