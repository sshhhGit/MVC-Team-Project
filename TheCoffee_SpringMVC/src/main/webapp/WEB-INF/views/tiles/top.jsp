<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp" %>
<%--top.jsp --%>
<head>
<!-- <link rel="stylesheet" type="text/css" href="../static/style.css"> -->
</head>
<html>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#372923" height="100%">
    <tbody><tr>
      <td style="padding:8px 0px;">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tbody><tr>
  <td width="400" rowspan="2"></td>
    <td width="400" rowspan="2" align="center"><a href="${ctxpath }"><img src="${ctxpath }/resources/imgs/top_logo3.png" border="0"></a></td>
    <td height="35" colspan="8"></td>
    <%-- <c:if test="${empty sessionScope.userId }"> --%>
    <c:if test="${empty userId }">
    <td colspan="2"></td>
    </c:if>
    <%-- <c:if test="${!empty sessionScope.userId }"> --%>
    <c:if test="${!empty userId }">
    <td colspan="2" valign="bottom"><div align="center" style="color:white;">${userName }님</div></td>
    </c:if>
  </tr>
  <tr valign="top">
    <td width="145"><div align="center"><a href="${ctxpath }/product/list.do"><img src="${ctxpath }/resources/imgs/top_product_button2.png"></a></div></td>
    <td width="120"><div align="center"><a href="${ctxpath }/notice/list.do"><img src="${ctxpath }/resources/imgs/top_notice_button2.png"></a></div></td>
    <td width="145"><div align="center"><a href="${ctxpath }/board/list.do"><img src="${ctxpath }/resources/imgs/top_board_button2.png"></a></div></td>
    <td width="120"><div align="center"><a href="${ctxpath }/qna/list.do"><img src="${ctxpath }/resources/imgs/top_qna_button2.png"></a></div></td>
    <td width="120"><div align="center"><a href="${ctxpath }/faq/list.do"><img src="${ctxpath }/resources/imgs/top_faq_button2.png"></a></div></td>
    <td width="120"></td>
    <td width="120"></td>
    <td width="120"></td>
    <c:if test="${empty sessionScope.userId }">
    <td width="120"><div align="right"><a href="${ctxpath }/member/loginForm.do"><img src="${ctxpath }/resources/imgs/top_login_button.png"></a></div></td>
    <td width="120"><div align="left"><a href="${ctxpath }/member/insertForm.do"><img src="${ctxpath }/resources/imgs/top_input_button.png"></a></div></td>
    </c:if>
    <c:if test="${!empty sessionScope.userId and 'admin' ne sessionScope.userId}">
    <td width="120"><div align="right"><a href="${ctxpath }/member/logOut.do"><img src="${ctxpath }/resources/imgs/top_logout_button.png"></a></div></td>
    <td width="120"><div align="left"><a href="${ctxpath }/member/myMenu.do"><img src="${ctxpath }/resources/imgs/top_mymenu_button.png"></a></div></td>
    </c:if>
    <c:if test="${!empty sessionScope.userId and 'admin' eq sessionScope.userId}">
    <td width="120"><div align="right"><a href="${ctxpath }/member/logOut.do"><img src="${ctxpath }/resources/imgs/top_logout_button.png"></a></div></td>
    <td width="120"><div align="center"><a href="${ctxpath }/admin/adminMenu.do"><img src="${ctxpath }/resources/imgs/top_adminmenu_button.png"></a></div></td>
    </c:if>
  </tr>
</tbody></table>
      </td>
    </tr>
</tbody></table>
</body>
</html>