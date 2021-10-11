//회원 탈퇴 암호 확인
function pwCheck() {
	if(document.delForm.pw.value==''){
		alert("암호를 입력하세요");
		document.delForm.pw.focus();
		return false;
	}
	return true;
}

//개인정보 수정
	function editCheck() {
		var ff = eval("document.editForm");

		if (ff.pw.value == '') {
			alert("암호를 입력하세요");
			ff.pw.focus();
			return false;
		}
		if (ff.name.value == '') {
			alert("이름을 입력하세요");
			ff.name.focus();
			return false;
		}
		if (ff.email.value == '') {
			alert("이메일 입력하세요");
			ff.email.focus();
			return false;
		}
		if (ff.tel.value == '') {
			alert("전화번호를 입력하세요");
			ff.tel.focus();
			return false;
		}
		if (ff.zipcode.value == '') {
			alert("주소를 입력하세요");
			ff.zipcode.focus();
			return false;
		}
		if (ff.addr2.value == '') {
			alert("상세주소를 입력하세요");
			ff.addr2.focus();
			return false;
		}
		return true;
	}
	

//회원가입
	function openDaumPostcode(){
		 
		new daum.Postcode({
			oncomplete:function(data){
				document.getElementById('zipcode').value=data.zonecode;
				document.getElementById('addr').value=data.address;
	 		}
		}).open();
	}//openDaumPostcode()---

	function validationCheck(){
		var form = document.signUpForm;
		//name check
		var namePattern = /^[가-힣]{2,6}$/;
		var name = form.name.value;
		//telNo check
		var telPattern = /^\d{3}-\d{3,4}-\d{4}$/;
		var tel = form.tel.value + "-" + form.tel2.value + "-" + form.tel3.value;
		if(form.idDupleChecked.value == "false") {
			alert("아이디 중복확인을 해주십시오");
			return false;
		}
		if(form.pwCheck.value == "false") {
			alert("비밀번호를 확인해주십시오");
			return false;
		}	
		
		if(!check(namePattern, name, "유효하지 않은 이름입니다, 2글자이상의 한글만 입력가능합니다")){
			return false;
		}
		if(!check(telPattern, tel, "유효하지 않은 전화번호입니다")) {
			return false;
		} 
/* 		if(form.authPass.value == "false") {
			alert("이메일 인증을 진행해주세요.");
			return false;
		} */	
		if (sessionStorage.getItem("pwEqualCheck") == "null"){
			alert("비밀번호 확인을 입력해주십시오");
			return false;
		}
		alert("인증메일이 발송되었습니다");
	form.submit(); 
	
	}
	
	function idValCheck(){
		var form = document.signUpForm;
		var idPattern = /^[A-Za-z]{1}[A-Za-z0-9]{3,19}$/;
		var id = form.id.value;
		if(!check(idPattern, id, "아이디는 4~12자 사이 영문 대소문자와 숫자로만 입력가능합니다")) {
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
				   url:'idCheck.do',
			         data:"id="+$('#id').val(),
			          dataType:'JSON',
			         success:function(data){
			            //alert(data.check);////////////************
			            
			            if(data.check==-1){
			               alert("사용 중인 ID입니다.");
			               $('#id').val('').focus();
			               
			            }else if(data.check==1){//사용가능한
			               //$('#idcheck').val('1');//id중복 체크가 되었을때 
			               alert("사용 가능 한 ID입니다.");
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
		var passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{4,12}$/;
		var pw = form.pw.value;
		var pw2 = form.pw2.value;
		
		if(!check(passwordPattern, pw, "비밀번호는 4~12자 사이의 영문 및 숫자를 포함하여 입력가능합니다")) {
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
		if(!check(emailPattern, email, "유효하지 않은 이메일주소입니다")) {
			return false;
		}
	}
	
/* 	function emailAuthentication(){
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
	} */
	
//로그인	
  function loginCheck() {
	  if (document.loginForm.id.value=='') {
		  alert("아이디를 입력하세요");
		  document.loginForm.id.focus();
		  return false;
	}
	  if (document.loginForm.pw.value=='') {
		  alert("비밀번호를 입력하세요");
		  document.loginForm.pw.focus();
		  return false;
	}
	return true;
}

//비밀번호 변경
function oldPwCheck() {
	var ff = eval("document.pwEditForm");
	
	if(ff.oldPw.value==''){
		alert("기존 비밀번호를 입력하세요");
		ff.oldPw.focus();
		return false;
	}
	if(ff.newPw.value==''){
		alert("새 비밀번호를 입력하세요");
		ff.newPw.focus();
		return false;
	}
	if(ff.newPw2.value==''){
		alert("새 비밀번호 확인을 입력하세요");
		ff.newPw2.focus();
		return false;
	}
	if(ff.oldPw.value==ff.newPw.value){
		alert("기존 비밀번호와 다른 비밀번호를 입력하세요");
		ff.oldPw.value="";
		ff.newPw.value="";
		ff.newPw.focus();
		return false;
	}
	if(ff.newPw.value!=ff.newPw2.value){
		alert("새 비밀번호 확인이 일치하지않습니다");
		ff.newPw.value="";
		ff.newPw2.value="";
		ff.newPw.focus();
		return false;
	}
	return true;
}


//qna


function updateSave(){
   
	if($("#name_ko").val()==''){
	   alert("한글 상품명을 입력하세요 ");
	   $("#name_ko").focus();
	   return false;
   }
   if($("#name_eng").val()==''){
	   alert("영문 상품명을 입력하세요 ");
	   $("#name_eng").focus();
	   return false;
   }
   if($("#subject").val()==''){
	   alert("간략 설명 글을 입력하세요 ");
	   $("#subject").focus();
	   return false;
   }
   if($("#content").val()==''){
	   alert("상세 설명을 입력하세요 ");
	   $("#content").focus();
	   return false;
   }
   
   if($("#hc_code").val()==''){
	   alert("음료 구분을 선택해주세요 ");
	   $("#hc_code").focus();
	   return false;
   }
   if($("#event_code").val()==''){
	   alert("이벤트 코드를 입력해주세요 ");
	   $("#event_code").focus();
	   return false;
   }
   
     return true;
}


function qnaWriteSave(){
 //script.js		 
	
	if($("#user_id").val()==''){
	   alert("질문자를 입력하세요.");
	   $("#user_id").focus();
	   return false;
   }
   if($("#user_content").val()==''){
	   alert("질문내용을 입력하세요.");
	   $("#user_content").focus();
	   return false;
   }
   if($("#admin_id").val()==''){
	   alert("질문자를 입력하세요.");
	   $("#admin_id").focus();
	   return false;
   }
   if($("#admin_content").val()==''){
	   alert("질문내용을 입력하세요.");
	   $("#admin_content").focus();
	   return false;
   }
   
	if($("#pw").val()=='') {
		alert("암호를 입력하세요");
		$("#pw").focus();
		return false;
	}
	
	if($("#search").val()!='') {
	  
	}
	
     return true;
} // writeSave()-end

//board
function writeSave(){
	//alert("aaa")
	if(document.writeForm.writer.value==""){
	  alert("작성자를 입력하십시요.");
	  document.writeForm.writer.focus();
	  return false;
	}
	if(document.writeForm.subject.value==""){
	  alert("제목을 입력하십시요.");
	  document.writeForm.subject.focus();
	  return false;
	}
	
	if(document.writeForm.content.value==""){
	  alert("내용을 입력하십시요.");
	  document.writeForm.content.focus();
	  return false;
	}
        
	if(document.writeForm.pw.value==""){
	  alert(" 비밀번호를 입력하십시요.");
	  document.writeForm.pw.focus();
	  return false;
	}
	
	return true;
	//document.writeForm.submit();
 }//writeSave()end

//faq
function faqWriteSave(){
	//alert("aaa")

	if(document.writeForm.faq_title.value==""){
	  alert("제목을 입력하십시요.");
	  document.writeForm.faq_title.focus();
	  return false;
	}
	if(document.writeForm.faq_category.value==""){
	  alert("분류를 선택하십시오.");
	  document.writeForm.faq_category.focus();
	  return false;
	}
	if(document.writeForm.faq_content.value==""){
	  alert("내용을 입력하십시요.");
	  document.writeForm.faq_content.focus();
	  return false;
	}
	
	return true;
	//document.writeForm.submit();
 }//writeSave()end
   
//notice
function noticeWriteSave(){

	if(document.writeForm.notice_title.value==""){
	  alert("제목을 입력하십시요.");
	  document.writeForm.notice_title.focus();
	  return false;
	}
	
	if(document.writeForm.notice_content.value==""){
	  alert("내용을 입력하십시요.");
	  document.writeForm.notice_content.focus();
	  return false;
	}
        
	
	return true;
	//document.writeForm.submit();
 }//writeSave()end   
   
 
 //product
 function inputSave(){
   
	if($("#name_ko").val()==''){
	   alert("한글 상품명을 입력하세요 ");
	   $("#name_ko").focus();
	   return false;
   }
   if($("#name_eng").val()==''){
	   alert("영문 상품명을 입력하세요 ");
	   $("#name_eng").focus();
	   return false;
   }
   if($("#subject").val()==''){
	   alert("간략 설명 글을 입력하세요 ");
	   $("#subject").focus();
	   return false;
   }
   if($("#content").val()==''){
	   alert("상세 설명을 입력하세요 ");
	   $("#content").focus();
	   return false;
   }
   if($("#image_file").val()==''){
	   alert("상품 이미지를 등록해주세요 ");
	   $("#image_file").focus();
	   return false;
	   }
   
   if($("#hc_code").val()==''){
	   alert("음료 구분을 선택해주세요 ");
	   $("#hc_code").focus();
	   return false;
   }
   if($("#event_code").val()==''){
	   alert("이벤트 코드를 입력해주세요 ");
	   $("#event_code").focus();
	   return false;
   }
   
     return true;
}