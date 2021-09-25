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
<link rel="stylesheet" type="text/css" href="../static/style.css">
</head>
<body>
<%-- body부분 --%>
<%
// session.setAttribute("admin_id", "admin1"); // 세션에 등록(관리자)
session.setAttribute("user_id", "user1"); // 세션에 등록(유저)
%>
<br>
 <h2>Q&A&nbsp;&nbsp;(전체글:${count})</h2>
 <table width="50%" cellpadding="0" cellspacing="0">
   <tr>
    <td><b>현재 session : 
    <%
     if (session.getAttribute("user_id") != null) {
    	 out.print(session.getAttribute("user_id"));
     } else if (session.getAttribute("admin_id") != null) {
    	 out.print(session.getAttribute("admin_id"));
     } else {
    	 out.print("비회원상태");
     }
    	%>
    	</b>
    </td>
   </tr>
   
   <tr>
    <td align="right">
     <%
      if (session.getAttribute("user_id") != null) {
    	  %>
        <input type="button" value="질문작성" onclick="location='${ctxpath}/qna/writeForm.do'"><br><br>
    	  <%
      }
     %>
    </td>
   </tr>
 </table>
 <br>
 <c:if test="${count==null}">
   글이 없다. null이다.
 </c:if>
 
 <c:if test="${count==0}">
   <center>글이 없습니다.</center>
 </c:if>
 
 <c:if test="${count>0}">
   <table class="table" border="1" cellpadding="10" cellspacing="0">
     <tr bgcolor="ivory">
       <td align="center"><b>글번호</b></td>
       <td align="center"><b>질문</b></td>
     </tr>
     
     <c:forEach var="dto" items="${qnaList}">
       <tr>
        <%-- 글번호 --%>
        <td align="center">
          <c:out value="${dto.num}"/>
        </td>
        
        <td align="left">
         <%-- 글내용 --%>
         <a href="${ctxpath}/qna/content.do?num=${dto.num}&current_user_id=<%=session.getAttribute("user_id")%>&current_admin_id=<%=session.getAttribute("admin_id")%>">
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