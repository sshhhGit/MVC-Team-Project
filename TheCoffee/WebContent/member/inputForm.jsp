<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
</style>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
	function openDaumPostcode(){
		 
		new daum.Postcode({
			oncomplete:function(data){
				document.getElementById('zipcode').value=data.zonecode;
				document.getElementById('addr').value=data.address;
	 		}
		}).open();
	}//openDaumPostcode()---
  </script>
<script type="text/javascript">

	function validationCheck(){
		var form = document.signUpForm;
		//name check
		var namePattern = /^[가-힣]{2,6}$/;
		var name = form.name.value;
		//telNo check
		var telPattern = /^\d{3}-\d{3,4}-\d{4}$/;
		var tel = form.tel.value + "-" + form.tel2.value + "-" + form.tel3.value;
		if(form.idDupleChecked.value == "false") {
			alert("아이디 중복확인을 해주세요.");
			return false;
		}
		if(form.pwCheck.value == "false") {
			alert("비밀번호 확인을 해주세요.");
			return false;
		}	
		
		if(!check(namePattern, name, "유효하지 않은 이름입니다. 2글자이상의 한글만 입력 가능합니다.")){
			return false;
		}
		if(!check(telPattern, tel, "유효하지 않은 핸드폰 번호입니다.")) {
			return false;
		} 
		if(form.authPass.value == "false") {
			alert("이메일 인증을 진행해주세요.");
			return false;
		}	
		if (sessionStorage.getItem("pwEqualCheck") == "null"){
			alert("비밀번호 일치확인을 해 주세요.");
			return false;
		}
		alert("회원가입이 성공적으로 완료되었습니다.");
	form.submit(); 
	
	}
	
	function idValCheck(){
		var form = document.signUpForm;
		var idPattern = /^[A-Za-z]{1}[A-Za-z0-9]{3,19}$/;
		var id = form.id.value;
		if(!check(idPattern, id, "아이디는 4~12자의 영문 대소문자와 숫자로만 입력하세요")) {
			return false;
		}
	}
	
	function check(pattern, taget, message) {
		if(pattern.test(taget)) {
	    	return true;
	    }
	    alert(message);
	    taget.focus();
	    return false;
	}
	
	//Ajax
	  function idDupleCheck(){
		   if($('#id').val()==''){
			   alert("아이디를 입력 하세요");
			   $('#id').focus();
		   }else{
			   $.ajax({
				   type:"POST",
				   url:'confirmID.jsp',
				   data:"id="+$('#id').val(),//서버로 보내는 인수 값
				   dataType:'JSON',//서버가 보내준 타입(Type)
				   success:function(data){
					   if(data.x==1){
						   alert("사용중인 아이디 입니다");
						   $('#id').val('').focus();
						   return;
					   }else if(data.x==-1){
						   alert("사용가능한 아이디 입니다");
						   $('#pw').focus();
						   $('#idDupleChecked').val('true');
					   }
				   }//success end
			   });
			   $('idDupleChecked').val('true');//id 중복 체크 완료
		   }//else end
	  }//confirmIDCheck() end  
	
	function pwEqualCheck(){
		var form = document.signUpForm;
		var passwordPattern = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
		var pw = form.pw.value;
		var pw2 = form.pw2.value;
		
		if(!check(passwordPattern, pw, "비밀번호는 8~15자리의 영문, 숫자, 특수문자로 조합해야 합니다.")) {
			return false;
		}
		
		if (pw != pw2) {
			alert("비밀번호가 일치하지 않습니다.");
			sessionStorage.removeItem("pwEqualCheck");
			document.getElementById('inputPwForm1').readOnly = false;
			document.getElementById('inputPwForm2').readOnly = false;
			return false;
		}
		sessionStorage.setItem("pwEqualCheck", "true");
		document.getElementById('inputPwForm1').readOnly = true;
		document.getElementById('inputPwForm2').readOnly = true;
		form.pwCheck.value = true;
		alert("사용가능한 비밀번호입니다.");
	}
	
	function emailValCheck(){
		var form = document.signUpForm;
		var emailPattern= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		var email = form.email.value;
		if(!check(emailPattern, email, "유효하지 않은 이메일 주소입니다.")) {
			return false;
		}
	}
	
	function emailAuthentication(){
		emailValCheck();
		var url = "confirmEmail.do?email=" + document.signUpForm.email.value;
		open(url, "confirm",
				"toolbar=no, location=no,menubar=no,scrollbars=no,resizable=no,width=300,height=150");
		document.signUpForm.authCodeCheckBtn.disabled = false;
		document.signUpForm.inputAuthCode.disabled = false;
	}
	
	function authCodeCheck(){
		var url = "authCodeCheck.jsp?inputedCode=" + document.signUpForm.authCode.value;
		open(url, "confirm",
				"toolbar=no, location=no,menubar=no,scrollbars=no,resizable=no,width=600,height=150");
	}
	
</script>
</head>
<body>
		<h2> 회원가입 </h2>
		<form name="signUpForm" action="${ctxpath }/member/inputPro.do" method="post" accept-charset="UTF-8">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td colspan="2">
				<input type="text" name="id" id="id"  size="15">
				<input onclick="idDupleCheck()" type="button" value="아이디 중복 확인">
				<input type="hidden" name="idDupleChecked" id="idDupleChecked" value="false">
				</td>
			</tr>
			<tr>
				<td rowspan="2">비밀번호</td>
				<td rowspan="2"> 
				<input type="password" name="pw" id="inputPwForm1" maxlength="15">
				<br>
				<input type="password" name="pw2" id="inputPwForm2"  size="15">
				<button onclick="pwEqualCheck()" type="button" class="btnChk">비밀번호  확인</button>
				<input type="hidden" name="pwCheck" id="pwCheck" value="false"></td>
			</tr>			
			<tr>
			</tr>
			<tr>
				<td>이름</td>
				<td colspan="2"><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>연락처</td>
				<td colspan="2">
					<select name="tel">
						<option value="010" selected="selected"> 010
						<option value="011"> 011
						<option value="018"> 018
					</select>
					<input type="text" name="tel2" size="4" maxlength="4">
					<input type="text" name="tel3" size="4" maxlength="4">
				</td>
			</tr>
			<tr>
				<td rowspan="3">이메일</td>
				<td><input type="text" name="email" id="email"  maxlength="30">
				<button onclick="emailAuthentication()" id="eamilAuthBtn" type="button" class="btnChk">인증 메일 보내기</button></td>
			</tr>
			<tr>
				<td colspan="2"><input type="text" name="authCode" id="inputAuthCode"  maxlength="10" disabled="disabled">
				<button onclick="authCodeCheck()" id="authCodeCheckBtn" type="button" disabled="disabled" class="btnChk">인증</button>
				<input type="hidden" name="authPass" id="authPass" value="false">
				</td>
				<!-- <td><input type="hidden" name="authPass" id="authPass" value="false"></td> -->
			</tr>		
			<tr>
				<td colspan="2"><font size="2" style="color: red;">* 이메일이 도착하는데 1~2분정도 소요될 수 있습니다.</font><br></td>
			</tr>
			<tr>
		        <td>우편번호</td>
		        <td>
		          <input type="text" name="zipcode" id="zipcode" size="7">
		          <input type="button" value="주소검색" onclick="openDaumPostcode()">
		        </td>
		      </tr>
		      <tr>
		        <td>주소</td>
		        <td>
		          <input type="text" name="addr" id="addr" size="50" readonly="readonly">
		          <br>
		          상세주소 : <input type="text" name="addr2" id="addr2" size="20">
		        </td>
		      </tr>		
				<tr>
		        <td colspan="2" align="center">
		          <input type="button" value="회원가입" onclick="validationCheck()">
		          <!-- <input type="submit" value="회원가입"> -->
		          <input type="reset" value="다시입력">
		          <input type="button" value="돌아가기" onclick="location='${ctxpath}/member/main.do'">
		        </td>
		      </tr>
		      </table>
		</form>
</body>
</html>