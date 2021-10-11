<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../resources/css/style.css">
</head>
<body>
  <table>
    <tr>
      <td>
        <form action="adminMemberList.do" name="adminMenu" method="post">
        <%-- <input type="hidden" name="id" value="${sessionScope.userId }"> --%>
        <input type="submit" value="회원관리">
        </form>
      </td>
<%--       <td>
        <form action="${ctxpath }/member/deleteForm.do" name="deleteForm" method="post">
          <input type="hidden" name="id" value="${sessionScope.userId }">
          <input type="submit" value="상품등록">
        </form>
      </td> --%>
    </tr>
  </table>
</body>
</html>