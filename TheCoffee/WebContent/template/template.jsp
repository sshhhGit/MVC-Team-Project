<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>

<link rel="stylesheet" type="text/css" href="../static/style.css">
</head>
  <body>
    <table border="0" height="100%" width="100%">
      <tr>
        <td>
          <jsp:include page="/module/top.jsp" flush="false"></jsp:include>
        </td>
<%--         <td align="center" bgcolor="#747474" width="90">
        <c:if test="${empty sessionScope.userId }">
          <jsp:include page="/module/login.jsp" flush="false"></jsp:include>
        </c:if>
        <c:if test="${!empty sessionScope.userId and 'admin' eq sessionScope.userId}">
          <jsp:include page="/module/admin.jsp" flush="false"></jsp:include>
        </c:if>
        <c:if test="${!empty sessionScope.userId and 'admin' ne sessionScope.userId}">
          <jsp:include page="/module/member.jsp" flush="false"></jsp:include>
        </c:if>
        </td> --%>
      </tr>
      <tr>
	    <%-- <td width="5%" valign="top" align="right">left
	      <jsp:include page="/module/left.jsp"></jsp:include>
	    </td> --%>
	    <td colspan="3" width="90%" valign="top" height="90%" align="center"><%--content --%>
	      <jsp:include page="${view }" flush="false"></jsp:include>
	    </td>
      </tr>
      <tr>
        <td><%-- bottom.jsp --%>
          <jsp:include page="/module/bottom.jsp"></jsp:include>
        </td>
      </tr>
    </table>
  </body>
</html>