function loginCheck() {
	if($('#id').val()==''){
		alert("ID를 입력하세요");
		$('#id').focus();
		return false;
	}
	if($('#pw').val()==''){
		alert("암호를 입력하세요");
		$('#pw').focus();
		return false;
	}
	return true;
}//loginCheck()-end

function check() {
	//데이터 유효성체크
	if($('#id').val()==''){
		alert("ID를 입력하세요");
		$('#id').focus();
		return false;
	}
	if($('#pw').val()==''){
		alert("암호를 입력하세요");
		$('#pw').focus();
		return false;
	}
	if($('#pw2').val()==''){
		alert("암호확인을 입력하세요");
		$('#pw2').focus();
		return false;
	}
	//암호==암호확인 비교
	if($('#pw').val()!=$('#pw2').val()){
		alert("암호와 암호확인이 일치하지 않습니다");
		$('#pw2').val('');
		$('#pw').val('').focus();
		return false;
	}
	if($('#name').val()==''){
		alert("이름을 입력하세요");
		$('#name').focus();
		return false;
	}
	if($('#name').val()==''){
		alert("이름을 입력하세요");
		$('#name').focus();
		return false;
	}
	if($('#idcheck').val()=='false'){
		alert("ID 중복을 확인하세요");
		$('#id').focus();
		return false;
	}
	return true;
}//check()-end

function writeSave(){
 //script.js		   
	if($("#writer").val()==''){
	   alert("이름을 입력 하시요 ");
	   $("#writer").focus();
	   return false;
   }
   if($("#subject").val()==''){
	   alert("글제목을 입력 하시요 ");
	   $("#subject").focus();
	   return false;
   }
   if($("#content").val()==''){
	   alert("글내용을 입력 하시요 ");
	   $("#content").focus();
	   return false;
   }
   if($("#pw").val()==''){
	   alert("암호을 입력 하시요 ");
	   $("#pw").focus();
		   return false;
	   }
   
     return true;
   }//writeSave() end


/*function writeSave(){
	//alert("aaa")
	if(document.writeform.writer.value==""){
	  alert("작성자를 입력하십시요.");
	  document.writeform.writer.focus();
	  return false;
	}
	if(document.writeform.subject.value==""){
	  alert("제목을 입력하십시요.");
	  document.writeform.subject.focus();
	  return false;
	}
	
	if(document.writeform.content.value==""){
	  alert("내용을 입력하십시요.");
	  document.writeform.content.focus();
	  return false;
	}
        
	if(document.writeform.pw.value==""){
	  alert(" 비밀번호를 입력하십시요.");
	  document.writeform.pw.focus();
	  return false;
	}
	
	return true;
	//document.writeform.submit();
 }//writeSave()end
 */    