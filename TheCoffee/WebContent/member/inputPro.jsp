<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<%-- inputPro.jsp --%>

<%-- 세션설정--%>
<c:set var="userId" value="${id }" scope="session"></c:set>
<meta http-equiv="Refresh" content="0; url=${ctxpath }/member/main.do">
