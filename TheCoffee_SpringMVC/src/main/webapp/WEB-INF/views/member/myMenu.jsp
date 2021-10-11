<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
  <table>
    <tr>
      <td>
        <form action="editForm.do" name="editForm" method="post">
        <input type="hidden" name="id" value="${userId }">
        <input style="width: 400px; height: 120px; font-size: 50px; background-color: #FFF2EC;" type="submit" value="개인정보 변경">
        </form>
      </td>
    </tr>
    <tr>
      <td>
        <form action="${ctxpath }/member/pwEditForm.do" name="pwEditForm" method="post">
        <input type="hidden" name="id" value="${userId }">
        <input style="width: 400px; height: 120px; font-size: 50px; background-color: #FFF2EC;" type="submit" value="비밀번호 변경">
        </form>
      </td>
	</tr>
    <tr>
      <td>
        <form action="${ctxpath }/member/deleteForm.do" name="deleteForm" method="post">
          <input type="hidden" name="id" value="${iduserId}">
          <input style="width: 400px; height: 120px; font-size: 50px; background-color: #FFF2EC;" type="submit" value="회원 탈퇴">
        </form>
      </td>
    </tr>
  </table>
</body>
</html>