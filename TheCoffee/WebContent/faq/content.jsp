<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/module/header.jsp" %>    
<%--content.jsp --%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<link rel="stylesheet" type="text/css" href="../static/style.css">

<script type="text/javascript">
//Ajax
function deleteFaq() {
	
   $.ajax({
	   type:"POST",
	   url:'deleteFaq.jsp',
	   data:"num="+$('#num').val(),//서버로 보내는 인수 값
	   dataType:'JSON',//서버가 보내준 타입(Type)
	   success:function(data){
		   if(data.check == 1){
			   alert("삭제되었습니다.");
			   location.href="${ctxpath}/faq/list.do";
		   } else {
			   alert("삭제 실패");
		   }
	   }//success end
   });
}

</script>

</head>
<body>

<table width="60%" height ="80" cellpadding="5">
	<tr>
		<td align="left">
			<font size="+2">
				<strong>FAQ(자주묻는질문)</strong>
			</font>
		</td>

		<!-- TODO : 수정, 삭제 관리자 권한 처리 -->	
		<td align="right">
			<input type="hidden" name="num" id="num" value="${num}">
			<input type="button" value="수정" onClick="location='${ctxpath}/faq/updateForm.do?num=${num}'">
			
			<%--관리자인 경우 --%>
			<c:if test="${'admin' eq sessionScope.userId }">	
				<input type="button" value="삭제" onClick="deleteFaq()">
			</c:if>
			
			<input type="button" value="목록" onClick="location='${ctxpath}/faq/list.do'">
		</td>
	</tr>
</table>	

<table width="60%" align="center" cellpadding="5">
	<!-- [분류]제목 -->
	<tr height="50" bgcolor="#f2f2f2">
		<td colspan="2">
			<font size="+1" color="blue">
			<strong>[${dto.category}] ${dto.subject}</strong>
			</font>
		</td>
	</tr>
	
	<!-- 작성자, 작성일 -->
	<tr height="40" bgcolor="white">
		<td width="160"><font color="darkgrey"><strong>${dto.writer}</strong></font></td>
		<td><font size="-1">${dto.regdate}</font></td>
	</tr>
	
	<!-- 글내용 -->
	<tr bgcolor="white" height="200" valign="center">
		<td colspan="2">
			<pre>${dto.content}</pre>		
		</td>
	</tr>
	
</table>


</body>
</html>