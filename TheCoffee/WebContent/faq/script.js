/*
 * 파일 : /faq/script.js
 */

function inputSave(){
	$('#category');
	
	//작성자 : writer
	if($('#writer').val() == ''){
		alert("작성자를 입력 하세요");
		$('#writer').focus();
		return false;
	}		
	//제목 : subject
	if($('#subject').val() == ''){
		alert("제목을 입력 하세요");
		$('#subject').focus();
		return false;
	}		
	//분류 : category
	if($('#category').val() == ''){
		alert("분류를 입력 하세요");
		$('#category').focus();
		return false;
	}	
	//내용 : content
	if($('#content').val() == ''){
		alert("내용을 입력 하세요");
		$('#content').focus();
		return false;
	}
	return true;
}