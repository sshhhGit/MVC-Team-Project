<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%-- deletePro.jsp --%>

<c:if test="${check==1}">
 <script>
  alert("해당글이 삭제되었습니다.");
 </script>
 <meta http-equiv="Refresh" content="0;url=${ctxpath}/qna/list.do">
</c:if>

<c:if test="${check==0}"> 
 <script>
   alert("암호 틀림");
   history.back();
 </script>
</c:if>








