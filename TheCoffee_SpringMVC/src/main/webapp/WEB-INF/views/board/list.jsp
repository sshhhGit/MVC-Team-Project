<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp"%>
<%--Memberlist.jsp --%>
<fmt:requestEncoding value="UTF-8" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript" src="../resources/js/script.js"></script>

<link rel="stylesheet" type="text/css" href="../resources/css/style.css">

</head>
<body>
	<br>
	<h2>고객의 소리</h2>
	<%-- <h2>글목록(전체글:${cnt})</h2> --%>
	<br>
	<table id="board_list_t">
		<tr>
			<td style="text-align: right;"><a href="${ctxpath}/board/writeForm.do">글쓰기</a>
			</td>
		</tr>
	</table>

	<c:if test="${pp2.cnt==0}">
     게시판에 저장된글이 없습니다
 </c:if>

	<c:if test="${pp2.cnt>0}">
		<table id="board_list_t">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>조회수</th>
				<th>아이피</th>
				<!--        <td>주소</td>
       <td>상세주소</td>
       <td>가입일</td> -->
			</tr>

			<%-- <c:set var="idxNum" value="${1}"/> --%>
			<c:forEach var="bdto" items="${list}">
				<tr>

					<!-- 글번호 -->
					<td>${number} <c:set var="number" value="${number-1}" />
					</td>

					<!-- 글제목 -->
					<td width="300px">
						<!-- 답글 --> <c:if test="${bdto.re_level>0}">
							<img src="../resources/imgs/levle.gif" width="${5*bdto.re_level}"
								height="16">
							<img src="../resources/imgs/re.gif">
						</c:if> <!-- 원글 --> <c:if test="${bdto.re_level==0}">
							<img src="../resources/imgs/levle.gif" width="${5*bdto.re_level}"
								height="16">
						</c:if> <!-- 제목을 클릭하면 글내용로 가기  --> <!-- content.do --> <a
						href="${ctxpath}/board/content.do?num=${bdto.num}&pageNum=${pageNum}">
							${bdto.subject} </a> <!--조회수가 10번 이상이면 hot.gif표시 --> <c:if
							test="${bdto.readcount>20}">
							<img src="../resources/imgs/hot.gif">
						</c:if>
					</td>

					<td>${bdto.writer}</td>
					<td>${bdto.regdate}</td>
					<td>${bdto.readcount}</td>
					<td>${bdto.ip}</td>
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
					<%--       
      <!-- 에러 방지  -->
      <c:if test="${pp2.endPage>pp2.pageCnt}">
       <c:set var="endPage" value="${pp2.pageCnt}"/>
      </c:if>
       --%> <!-- 이전블럭 --> <c:if test="${pp2.startPage>10}">
						<a href="${ctxpath}/board/list.do?pageNum=${pp2.startPage-10}">
							[＜] </a>
					</c:if> <!-- 페이지 처리 --> <c:forEach var="i" begin="${pp2.startPage}"
						end="${pp2.endPage}">
						<a href="${ctxpath}/board/list.do?pageNum=${i}"> [${i}] </a>
					</c:forEach> <!-- 다음 블럭 --> <c:if test="${pp2.endPage<pp2.pageCnt}">
						<a href="${ctxpath}/board/list.do?pageNum=${pp2.startPage+10}">
							[＞] </a>
					</c:if>

				</td>
			</tr>
		</table>

	</c:if>

</body>
</html>