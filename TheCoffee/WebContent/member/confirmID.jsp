<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="member.*"
    %>
<%--confirmID.jsp --%>
<%
String id = request.getParameter("id");
MemberDAO dao = MemberDAO.getInstance();
int check = dao.confirmID(id);
%>
{"x":<%=check %>}