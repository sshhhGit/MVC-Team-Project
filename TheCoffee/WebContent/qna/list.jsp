<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<%--list.jsp --%>
<fmt:requestEncoding value="UTF-8"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list.jsp</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="script.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
<%
session.setAttribute("admin_id", "admin1"); // 세션에 등록(관리자)
session.setAttribute("user_id", "user1"); // 세션에 등록(유저)
%>
<%-- body부분 --%>
<br>
 <h2>Q&A(전체글:${count})</h2>
 <table width="50%" cellpadding="1" cellspacing="0">
   <tr>
    <td align="right">
     <%
      if (session.getAttribute("user_id") != null || session.getAttribute("admin_id") != null) {
    	  %>
        <a href="${ctxpath}/qna/writeForm.do">글쓰기</a>
    	  <%
      }
     %>
    </td>
   </tr>
 </table>
 
 <c:if test="${count==null}">
   글이 없다. null이다.
 </c:if>
 
 <c:if test="${count==0}">
   <center>글이 없습니다.</center>
 </c:if>
 
 <c:if test="${count>0}">
   <table width="700" border="1">
     <tr bgcolor="ivory">
       <td width="20%">글번호</td>
       <td>질문내용</td>
     </tr>
     
     <c:forEach var="dto" items="${qnaList}">
       <tr>
       <!-- 글번호 -->
        <td>
          <c:out value="${dto.num}"/>
        </td>
        
        
        <!-- 글제목 -->
        <td>
         <!-- 제목을 클릭하면 글내용로 가기  -->
         <!-- content.do -->
         <a href="${ctxpath}/qna/content.do?num=${dto.num}">
           ${dto.user_content}
         </a>
        </td>
         
       </tr>
     </c:forEach>
   </table>
 </c:if>
 <br><br><br>
  
 
</body>
</html>