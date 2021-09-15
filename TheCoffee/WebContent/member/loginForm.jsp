<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
  function loginWithKakao() {
    Kakao.Auth.login({
      success: function(authObj) {
        alert(JSON.stringify(authObj))
      },
      fail: function(err) {
        alert(JSON.stringify(err))
      },
    })
  }
</script>
</head>
	<body>
		<h2>로그인</h2>
		  <form name="loginForm" method="post" action="${ctxpath }/member/loginPro.do" onsubmit="return loginCheck()">
		     <table border="1">
		      <tr>
		        <td>아이디</td>
		        <td>
		          <input type="text" name="id" id="id" size="20">
		        </td>
		      </tr>
		      <tr>
		        <td>비밀번호</td>
		        <td>
		          <input type="password" name="pw" id="pw" size="20"></td>
		      </tr>
		      <tr>
		        <td colspan="2" align="center">
		          <input type="submit" value="로그인">
		          <input type="button" value="회원가입" onclick="location='${ctxpath}/member/inputForm.do'">
		        </td>
		      </tr>
		    </table>
		</form>
</body>
</html>