<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--notice_list.jsp --%>
 
<html>
<head>

<title>notice_list.jsp</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript" src="../resources/js/script.js"></script>

<link rel="stylesheet" type="text/css" href="../resources/css/style.css">

</head>
<body>
	<br>
	<h2>공지사항</h2>
	<%-- <h2>글목록(전체글:${cnt})</h2> --%>
	<br>
	<table id="board_list_t">
		<tr>
			<td style="text-align: right;"><a href="${ctxpath}/notice/writeForm.do">글쓰기</a>
			</td>
		</tr>
	</table>
	<c:if test="${pp2.cnt==0}">
		 게시판에 저장된글이 없습니다
	</c:if>

	<c:if test="${pp2.cnt>0}">
		<table id="notice_list_t">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>

			<c:forEach var="ndto" items="${list}">
				<tr>

					<!-- 글번호 -->
					<td width="70" align="center">${number} <c:set var="number" value="${number-1}" />
					</td>
 
					<!-- 글제목 -->
					<td width="940">
						<!-- 제목을 클릭하면 글내용로 가기  -->
						<!-- content.do -->
						<a href="${ctxpath}/notice/content.do?notice_no=${ndto.notice_no}&pageNum=${pageNum}">
							${ndto.notice_title} </a> <!--조회수가 10번 이상이면 hot.gif표시 --> <c:if
							test="${ndto.notice_readcount>20}">
							<img src="../resources/imgs/hot.gif">
						</c:if>
					</td>
 
					<td width="100" align="center">
						<fmt:formatDate pattern="yy-MM-dd" value="${ndto.notice_regdate}"/>
					</td>
					<td width="70" align="center">${ndto.notice_readcount}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
<br>
	<!-- 블럭 처리 ,페이지 처리 -->
	<c:if test="${pp2.cnt>0}">
		<table width="700">
			<tr>
				<td align="center">
					<!-- 이전블럭 -->
					<c:if test="${pp2.startPage>10}">
						<a href="${ctxpath}/notice/list.do?pageNum=${pp2.startPage-10}">
							[＜] </a>
					</c:if> <!-- 페이지 처리 --> <c:forEach var="i" begin="${pp2.startPage}"
						end="${pp2.endPage}">
						<a href="${ctxpath}/notice/list.do?pageNum=${i}"> [${i}] </a>
					</c:forEach> <!-- 다음 블럭 --> <c:if test="${pp2.endPage<pp2.pageCnt}">
						<a href="${ctxpath}/notice/list.do?pageNum=${pp2.startPage+10}">
							[＞] </a>
					</c:if>

				</td>
			</tr>
		</table>

	</c:if>
</body>
</html>