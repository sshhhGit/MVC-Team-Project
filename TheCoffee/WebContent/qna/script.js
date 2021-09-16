
function writeSave(){
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
   
     return true;
} // writeSave()-end


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