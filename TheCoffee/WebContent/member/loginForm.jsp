<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta name ="google-signin-client_id" content="754807124466-32n5fi9gfnkq3pi1r916mb5nmvi641qm.apps.googleusercontent.com">
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script>

//처음 실행하는 함수
function init() {
	gapi.load('auth2', function() {
		gapi.auth2.init();
		options = new gapi.auth2.SigninOptionsBuilder();
		options.setPrompt('select_account');
        // 추가는 Oauth 승인 권한 추가 후 띄어쓰기 기준으로 추가
		options.setScope('email profile openid https://www.googleapis.com/auth/user.birthday.read');
        // 인스턴스의 함수 호출 - element에 로그인 기능 추가
        // GgCustomLogin은 li태그안에 있는 ID, 위에 설정한 options와 아래 성공,실패시 실행하는 함수들
		gapi.auth2.getAuthInstance().attachClickHandler('GgCustomLogin', options, onSignIn, onSignInFailure);
	})
}

function onSignIn(googleUser) {
	var access_token = googleUser.getAuthResponse().access_token
	$.ajax({
    	// people api를 이용하여 프로필 및 생년월일에 대한 선택동의후 가져온다.
		url: 'https://people.googleapis.com/v1/people/me'
        // key에 자신의 API 키를 넣습니다.
		, data: {personFields:'birthdays', key:'AIzaSyBpzVqf-L7xmiRmbKP8pzLrZuYctdsQ3is', 'access_token': access_token}
		, method:'GET'
	})
	.done(function(e){
        //프로필을 가져온다.
		var profile = googleUser.getBasicProfile();
		console.log(profile)
	})
	.fail(function(e){
		console.log(e);
	})
}
function onSignInFailure(t){		
	console.log(t);
}
</script>
<!-- 구글 api 사용을 위한 스크립트 -->
<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>
</head>
	<body>
		<h2>로그인</h2>
		  <form name="loginForm" method="post" action="${ctxpath }/member/loginPro.do" onsubmit="return loginCheck()">
		     <table border="1">
		      <tr>
		        <td>아이디</td>
		        <td>
		          <input type="text" name="id" id="id" size="14">
		        </td>
		      </tr>
		      <tr>
		        <td>비밀번호</td>
		        <td>
		          <input type="password" name="pw" id="pw" size="14"></td>
		      </tr>
		      <tr>
		        <td colspan="2" align="center">
		          <input type="submit" value="로그인">
		          <input type="button" value="회원가입" onclick="location='${ctxpath}/member/inputForm.do'">
		        </td>
		      </tr>
		      <tr>
		        <td colspan="2" align="center" id="GgCustomLogin">
		            <a href="javascript:void(0)"><img src="../imgs/google_login.png"></a>
		        </td>
		      </tr>
		    </table>
		</form>
</body>
</html>