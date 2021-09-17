<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<%--loginPro.jsp --%>

<c:if test="${check==1 }">
  <c:set var="userId" value="${id }" scope="session"></c:set>
  <meta http-equiv="Refresh" content="0; url=${ctxpath }/member/main.do">
</c:if>

<%--암호틀림--%>
<c:if test="${check==0 }">
  <script type="text/javascript">
  alert("암호가 일치하지않습니다");
  history.back();
  </script>
</c:if>

<%--없는 ID--%>
<c:if test="${check==-1 }">
  <script type="text/javascript">
  alert("존재하지않은 아이디 입니다");
  history.back();
  </script>
</c:if>