
function inputSave(){
 //product_script.js		   
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
   if($("#image").val()==''){
	   alert("제품이미지를 등록해주세요 ");
	   $("#image").focus();
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
   }//inputSave() end
