
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="script.js"></script>
<!-- CSS 시작 -->
<link rel="stylesheet" href="http://www.megacoffee.me/theme/basic/css/default.css">
<!-- CSS 끝  -->
</head>
<body>

<!-- 팝업레이어 시작 { -->
<div id="hd_pop">
    <h2>팝업레이어 알림</h2>


    <div id="hd_pops_1" class="hd_pops" style="top:0px;left:555px">
        <div class="hd_pops_con" style="width:300px;height:413px">
            <p><a href=""><img src="../imgs/pop3.png"  /></a><br style="clear:both;" /> </p><p><br style="clear:both;" /> </p><p> </p><a href=""> </a><p> </p><p><a href=""> </a></p><p><a href=""> </a></p><p><a href=""> </a></p><p><a href=> </a></p><p><a href=""> </a></p><p><a href=""> </a></p><p> </p>       </div>
        <div class="hd_pops_footer">
            <button class="hd_pops_reject hd_pops_1 24"><strong>24</strong>시간 동안 다시 열람하지 않습니다.</button>
            <button class="hd_pops_close hd_pops_1">닫기</button>
        </div>
    </div>

    <div id="hd_pops_11" class="hd_pops" style="top:0px;left:200px">
        <div class="hd_pops_con" style="width:300px;height:413px">
            <a href=""><img src="../imgs/pop2.png"  /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /></a>        </div>
        <div class="hd_pops_footer">
            <button class="hd_pops_reject hd_pops_11 24"><strong>24</strong>시간 동안 다시 열람하지 않습니다.</button>
            <button class="hd_pops_close hd_pops_11">닫기</button>
        </div>
    </div>

    <div id="hd_pops_13" class="hd_pops" style="top:0px;left:-155px">
        <div class="hd_pops_con" style="width:300px;height:413px">
            <a href=""></a><a href=""><img src="../imgs/pop1.png" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /></a>        </div>
        <div class="hd_pops_footer">
            <button class="hd_pops_reject hd_pops_13 24"><strong>24</strong>시간 동안 다시 열람하지 않습니다.</button>
            <button class="hd_pops_close hd_pops_13">닫기</button>
        </div>
    </div>

    <div id="hd_pops_16" class="hd_pops" style="top:0px;left:910px">
        <div class="hd_pops_con" style="width:360px;height:413px">
            <a href=""><img src="../imgs/pop4.png" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /> </a>       </div>
        <div class="hd_pops_footer">
            <button class="hd_pops_reject hd_pops_16 24"><strong>24</strong>시간 동안 다시 열람하지 않습니다.</button>
            <button class="hd_pops_close hd_pops_16">닫기</button>
        </div>
    </div>
</div>

<script>
$(function() {
    $(".hd_pops_reject").click(function() {
        var id = $(this).attr('class').split(' ');
        var ck_name = id[1];
        var exp_time = parseInt(id[2]);
        $("#"+id[1]).css("display", "none");
        set_cookie(ck_name, 1, exp_time, g5_cookie_domain);
    });
    $('.hd_pops_close').click(function() {
        var idb = $(this).attr('class').split(' ');
        $('#'+idb[1]).css('display','none');
    });
    $("#hd").css("z-index", 1000);
});
</script>
<!-- } 팝업레이어 끝 -->

<c:if test="${empty sessionScope.userId }">
  <form name="loginForm" method="post" action="${ctxpath }/member/loginPro.do" onsubmit="return loginCheck()">
<%--     <table width="100%" border="1">
      <tr>
        <td rowspan="4" width="900" height="20" align="center">
          <a href="">옥션</a>&nbsp;
          <a href="">다나와</a>&nbsp;
          <a href="">Emart</a>&nbsp;
          
        </td>
      </tr>
      <tr>
        <td width="70" align="right">ID</td>
        <td width="70"><input type="text" name="id" id="id" size="20"></td>
      </tr>
      <tr>
        <td width="70" align="right">암호</td>
        <td width="70"><input type="password" name="pw" id="pw" size="20"></td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <input type="submit" value="로그인">
          <input type="button" value="회원가입" onclick="location='${ctxpath}/member/inputForm.do'">
        </td>
      </tr>
    </table> --%>
  </form>
</c:if>

<%-- 로그인 상태일 떄, 로그아웃, 회원정보 수정 --%>
<c:if test="${!empty sessionScope.userId }">
<%--   <table width="100%" border="1">
    <tr>
      <td width="975" height="20" align="center">
        홍길동 홈 입니다
      </td>
      <td rowspan="3" align="center">
        ${sessionScope.userId }님 방문해주셔셔 감사합니다
        
        <form method="post" action="${ctxpath }/member/logOut.do">
          <input type="submit" value="로그아웃">
          <input type="button" value="정보수정" onclick="location='${ctxpath}/member/modify.do'">
        </form>
      </td>
  </table> --%>
</c:if>

</body>
</html>