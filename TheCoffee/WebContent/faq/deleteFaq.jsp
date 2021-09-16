<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="faq.*"
    %>
<%-- deleteFaq.jsp --%>

<%
int num = Integer.parseInt(request.getParameter("num"));
FaqDAO dao = FaqDAO.getInstance();
int check = dao.deleteFaq(num); //1이면 성공
%>
{"check":<%=check%>}