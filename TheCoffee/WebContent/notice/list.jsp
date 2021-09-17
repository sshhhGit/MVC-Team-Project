<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp"%>

<%-- list.jsp --%>
<fmt:requestEncoding value="UTF-8"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list.jsp</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript" src="script.js"></script>

<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
<br>
	<h2>공지사항</h2>
<%-- 	
	(전체글:${count})
--%>
<%-- 		<c:if test="${!empty sessionScope.userId and 'admin' eq sessionScope.userId}">
          <jsp:include page="/module/admin.jsp" flush="false"></jsp:include>
        </c:if> --%>
	<c:if test="${!empty sessionScope.userId and 'admin' eq sessionScope.userId}">
	<table width="700">
		<tr>
			<td align="right"><a href="${ctxpath}/notice/writeForm.do">글등록</a>
			</td>
		</tr>
	</table>
	</c:if>

	<c:if test="${count==0}">
		게시판에 저장된 글이 없습니다.
	</c:if>

	<c:if test="${count>0}">
		<table width="700" border="1">
			<tr>
				<td>No.</td>
				<td>제목</td>
				<td>작성일</td>
				<td>조회수</td>
				<td>삭제</td>
			</tr>
			<c:set var="num" value="${1}"/>
			<c:forEach var="dto" items="${noticeList}">
				<tr>
					<!-- 글번호 -->
					<td><c:out value="${num}" /> 
					<c:set var="num" value="${num+1}" /></td>

					<!-- 글제목 -->
					<!-- 제목을 클릭하면 글내용보기로 가기 -->
					<!-- content.do -->
					<td>
						<a href="${ctxpath}/notice/content.do?notice_no=${dto.notice_no}&pageNo=${pageNo}">
							${dto.notice_title}
						</a>
					</td>
					<td>${dto.notice_date}</td>
					<td>${dto.notice_count}</td>
					<td>
						<a href="${ctxpath}/notice/deletePro.do?notice_no=${dto.notice_no}&pageNo=${pageNo}">삭제</a>
					</td>
					
					
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<%-- 블럭처리, 페이지처리 --%>
	<c:if test="${count>0}">
		<table width="700">
			<tr>
				<td align="center">
					<!-- 에러방지 -->
					<c:if test="${endPage>pageCount}">
						<c:set var="endPage" value="${pageCount}" />
					</c:if>
					
					<!-- 이전 블럭 -->
					<c:if test="${startPage>10}">
						<a href="${ctxpath}/notice/list.do?pageNo=${startPage-10}">
							[이전]
						</a>
					</c:if>
					
					<!-- 페이지 처리 -->
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<a href="${ctxpath}/notice/list.do?pageNo=${i}"> 
						[${i}] 
						</a>
					</c:forEach>
					
					<!-- 다음블럭 -->
					<c:if test="${endPage<pageCount}">
						<a href="${ctxpath}/notice/list.do?pageNo=${startPage+10}">
							[다음]
						</a>
					</c:if>
				</td>
			</tr>
		</table>
	</c:if>
</body>
</html>