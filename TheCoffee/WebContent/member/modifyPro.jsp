<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<%-- modifyPro.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
  <form action="">
    <table width="270" cellpadding="2" cellspacing="0">
      <tr>
        <td>
          <input type="button" value="메인으로" onclick="location='${ctxpath}/member/main.do'">
        </td>
      </tr>
    </table>
  </form>
  정보가 수정되었습니다
 2초 후에 메인으로 이동합니다
 <meta http-equiv="Refresh" content="2; url=${ctxpath }/member/main.do">
</body>
</html>