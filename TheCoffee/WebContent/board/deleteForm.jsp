<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>    
<%--deleteForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<link rel="stylesheet" type="text/css" href="../static/style.css">
<script>
  function check(){
	  if(document.delForm.pw.value==''){
		  alert("암호을 입력 하세요");
		  document.delForm.pw.focus();
		  return false;
	  }
	  return true;
  }
</script>

<body>
  <h2>글삭제 폼</h2>
  <form name="delForm" method="post" action="${ctxpath}/board/deletePro.do" onSubmit="return check()">
  
   <table width="30%" border="1">
   
     <tr>
      <td bgcolor="#b0e0e6">
              암호를 입력 하세요
      </td>
     </tr>
     
     <tr>
       <td align="center" bgcolor="#b0e0e6">
       
         암호입력:
       <input type="password" name="pw" size="15">
       
       <input type="hidden" name="num" value="${num}">         
       <input type="hidden" name="pageNum" value="${pageNum}">
      
       <input type="submit" value="글삭제">&nbsp;
       <input type="button" value="글목록" onClick="location='${ctxpah}/board/list.do?pageNum=${pageNum}'">
       </td>
     </tr>
     
     
     
   </table>
  </form>
</body>
</html>