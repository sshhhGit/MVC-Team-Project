<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp"%>
<%-- pwModifyPro.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../static/style.css">
</head>
<body>
	<c:if test="${check==1 }">
		<!-- 수정성공 -->
		<script>
			alert("변경되었습니다");
		</script>
		<meta http-equiv="Refresh" content="0; url=${ctxpath }/member/main.do">
	</c:if>
	<c:if test="${check==-1 }">
		<script>
			alert("비밀번호가 일치하지않습니다");
			history.back();
		</script>
	</c:if>
	<c:if test="${check==0 }">
		<script>
			alert("아이디가 존재하지않습니다");
			history.back();
		</script>
	</c:if>
</body>
</html>