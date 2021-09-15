<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<%--list.jsp --%>
<fmt:requestEncoding value="UTF-8"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript" src="script.js"></script>

<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
 <h2>글목록(전체글:${count})</h2>
 
 <table widht="700">
   <tr>
    <td align="right">
      <a href="${ctxpath}/board/writeForm.do">글쓰기</a>
    </td>
   </tr>
 </table>
 
 <c:if test="${count==0}">
     게시판에 저장된글이 없습니다
 </c:if>
 
 <c:if test="${count>0}">
   <table widht="700" border="1">
     <tr bgcolor="ivory">
       <td>아이디</td>
       <td>비밀번호</td>
       <td>작성자</td>
       <td>작성일</td>
       <td>조회수</td>
       <td>IP</td>
     </tr>
     
     <c:forEach var="dto" items="${boardList}">
       <tr>
       
       <!-- 글번호 -->
        <td>
          <c:out value="${number}"/>
          <c:set var="number" value="${number-1}"/>
        </td>
        
        <!-- 글제목 -->
        <td>
         <!-- 답글 -->
         <c:if test="${dto.re_level>0}">
           <img src="../imgs/levle.gif" width="${5*dto.re_level}" height="16">
           <img src="../imgs/re.gif">
         </c:if>
         
         <!-- 원글 -->
         <c:if test="${dto.re_level==0}">
          <img src="../imgs/levle.gif" width="${5*dto.re_level}" height="16">
         </c:if>
         
         <!-- 제목을 클릭하면 글내용로 가기  -->
         <!-- content.do -->
         <a href="${ctxpath}/board/content.do?num=${dto.num}&pageNum=${pageNum}">
           ${dto.subject}
         </a>
         
         <!--조회수가 10번 이상이면 hot.gif표시 -->
         <c:if test="${dto.readcount>20}">
          <img src="../imgs/hot.gif">
         </c:if>
        </td>
         
        <td>${dto.writer}</td>
        <td>${dto.regdate}</td>
        <td>${dto.readcount}</td>
        <td>${dto.ip}</td>
       </tr>
     </c:forEach>
   </table>
 </c:if>
 
 <%--블럭 처리 ,페이지 처리 --%>
 <c:if test="${count>0}">
   <table width="700">
     <tr>
      <td align="center">
      
      <!-- 에러 방지  -->
      <c:if test="${endPage>pageCount}">
       <c:set var="endPage" value="${pageCount}"/>
      </c:if>
      
      <!-- 이전블럭 -->
      <c:if test="${startPage>10}">
        <a href="${ctxpath}/board/list.do?pageNum=${startPage-10}">
         [이전블럭]
        </a>
      </c:if>
      
      
      <!-- 페이지 처리 -->
      <c:forEach var="i" begin="${startPage}" end="${endPage}">
        <a href="${ctxpath}/board/list.do?pageNum=${i}">
        [${i}]
        </a>
      </c:forEach>
      
      <!-- 다음 블럭 -->
      <c:if test="${endPage<pageCount}">
        <a href="${ctxpath}/board/list.do?pageNum=${startPage+10}">
        [다음블럭]
        </a>
      </c:if>
      
      </td>
     </tr>
   </table>
 
 </c:if>
 
</body>
</html>