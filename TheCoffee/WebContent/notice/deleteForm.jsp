<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<%-- deleteForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>deleteForm.jsp</title>

<link rel="stylesheet" type="text/css" href="style.css">
<script type="text/javascript">
	function check() {
		if(document.delForm.pw.value==''){
			alert("암호를 입력하세요");
			document.delForm.pw.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<h2>글삭제</h2>
		<form name="delForm" method="post" action="${ctxpath}/notice/deletePro.do" onsubmit="return check()">
			
			<table width="30%" border="1">
				<tr>
					<td bgcolor="#ffffff">
						암호를 입력 하세요
					</td>
				</tr>
				
				<tr>
					<td align="center" bgcolor="#ffffff">
					
					암호입력 :
						 <input type="password" name="pw" size="15">
						 
						 <input type="hidden" name="notice_no" value="${notice_no}">
						 <input type="hidden" name="pageNo" value="${pageNo}">
					
						<input type="submit" value="글삭제">&nbsp;
						<input type="button" value="글목록" onclick="location='${ctxpath}/notice/list.do?pageNo=${pageNo}'">
					</td>
				</tr>
			</table>
		</form>
</body>
</html>