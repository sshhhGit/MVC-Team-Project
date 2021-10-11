<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp"%>    
<%-- notice_updateForm.jsp --%>
<html>
<head>

<title>updateForm</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript" src="${ctxpath}/resources/js/script.js"></script>

<link rel="stylesheet" type="text/css" href="${ctxpath}/resources/css/style.css">


</head>
<body>
 <h2>글수정 폼</h2>

 <form name="updateForm" method="post" 
 action="${ctxpath}/notice/updatePro.do" onSubmit="return writeSave()">
 
 <table border="1" id="notice_update_t">
   <tr>
     <th>글제목</th>
     <td>
     <input type="text" name="notice_title" id="notice_title" value="${ndto.notice_title}" size="50">
     <input type="hidden" name="notice_no" value="${ndto.notice_no}">
     </td>
   </tr>
   
   <tr>
     <th>글내용</th>
     <td>
     <textarea name="notice_content" id="notice_content" rows="20" cols="90">${ndto.notice_content}</textarea>
     </td>
   </tr>

   <tr>
     <td colspan="2" align="center">
      <input type="submit" value="글수정">
      <input type="reset">
      <input type="button" value="글목록" onClick="location='${ctxpath}/notice/list.do'">
     </td>
   </tr>
 </table>
 </form>
</body>
</html>