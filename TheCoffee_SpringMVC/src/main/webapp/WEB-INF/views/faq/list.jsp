<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--faq_list.jsp --%>
 
<html>
<head>

<title>faq_list.jsp</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript" src="${ctxpath}/resources/js/script.js"></script>

<link rel="stylesheet" type="text/css" href="${ctxpath}/resources/css/style.css">

</head>
<body>
<table align="center">
	<td>
		<font size="+3">
			<strong>FAQ</strong><font size="+1">&nbsp;(전체글: ${pp2.cnt}) </font>
		</font>
	</td>
</table>

<table>
	<td align="right"><a href="${ctxpath}/faq/writeForm.do">글쓰기</a>
	</td>
</table>

	<c:if test="${pp2.cnt==0}">
		게시판에 저장된글이 없습니다
	</c:if>
  

	<c:if test="${pp2.cnt>0}">
		<table id="faq_list_t">
			<tr>
				<th>글번호</th>
				<th>분류</th>
				<th>제목</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>

			<c:forEach var="fdto" items="${list}">
			
				<tr>
					<!-- 글번호 -->
					<td width="70" align="center">${number} <c:set var="number" value="${number-1}" />
					</td>
 					
 					<!-- 분류 -->
 					<td width="180" align="center">${fdto.faq_category}</td>
 					
					<!-- 글제목 -->
					<td width="800">
						<!-- 제목을 클릭하면 글내용로 가기  -->
						<!-- content.do -->
						<a href="${ctxpath}/faq/content.do?faq_no=${fdto.faq_no}&pageNum=${pageNum}">
							${fdto.faq_title} </a> <!--조회수가 10번 이상이면 hot.gif표시 --> <c:if
							test="${fdto.faq_readcount>20}">
							<img src="../resources/imgs/hot.gif">
						</c:if>
					</td>
					
					<td width="100" align="center">
						<fmt:formatDate pattern="yy-MM-dd" value="${fdto.faq_regdate}"/>
					</td>
					<td width="70" align="center">${fdto.faq_readcount}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<!-- 블럭 처리 ,페이지 처리 -->
	<c:if test="${pp2.cnt>0}">
		<table width="700">
			<tr>
				<td align="center">
					<!-- 이전블럭 -->
					<c:if test="${pp2.startPage>10}">
						<a href="${ctxpath}/faq/list.do?pageNum=${pp2.startPage-10}">
							[이전블럭] </a>
					</c:if> <!-- 페이지 처리 --> <c:forEach var="i" begin="${pp2.startPage}"
						end="${pp2.endPage}">
						<a href="${ctxpath}/faq/list.do?pageNum=${i}"> [${i}] </a>
					</c:forEach> <!-- 다음 블럭 --> <c:if test="${pp2.endPage<pp2.pageCnt}">
						<a href="${ctxpath}/faq/list.do?pageNum=${pp2.startPage+10}">
							[다음블럭] </a>
					</c:if>

				</td>
			</tr>
		</table>

	</c:if>
</body>
</html>