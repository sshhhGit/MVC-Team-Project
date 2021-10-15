<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TheCoffee</title>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
	<!-- <script type="text/javascript" src="script.js"></script> -->
<!-- 팝업레이어 시작 CSS 시작 -->
<style type="text/css">
#hd_pop {z-index:1000;position:relative;margin:0 auto;width:970px;height:0}
#hd_pop h2 {position:absolute;font-size:0;line-height:0;overflow:hidden}
.hd_pops {position:absolute;border:1px solid #e9e9e9;background:#fff}
.hd_pops_con {}
.hd_pops_footer {padding:10px 0;background:#000;color:#fff;text-align:right}
.hd_pops_footer button {margin-right:5px;padding:5px 10px;border:0;background:#393939;color:#fff}
	
/*  #hd_pop {z-index:1000;position:relative;margin:0 auto;width:970px;height:0} */
</style>
<!-- 팝업레이어 시작 CSS 끝  -->

<script type="text/javascript">
/* $(document).ready(function () 
		{ 
	$('.popup').show(); cookiedata = document.cookie; 
	if ( cookiedata.indexOf("popup_1=done") < 0 ){ //쿠키 변경 여부 불러오기 
		document.all['popup_1'].style.visibility = "visible"; 
		} else { 
		document.all['popup_1'].style.display = "none"; 
		} 
	PopupBgDisplay(); 
	}); 
			
function PopupNoDisplay_1() { 
	setCookie("popup_1", "done", 1); //쿠키값 변경 
	PopupHide(); 
	} 
function PopupHide() { //팝업창 지우기 
	$('#popup_1').hide(); 
	PopupBgDisplay(); 
	} 
function PopupBgDisplay() { //팝업창이 없어진 경우 배경(popupbg) 지우기 
	cookiedata1 = document.cookie; 
	if (cookiedata1.indexOf("popup_1=done") > 0) 
	{ 
	$("#popupWrap").hide(); 
	} 
} */

</script>
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
</head>
<body>
<!-- 팝업레이어 시작  -->
<div id="hd_pop">
    <h2>팝업레이어 알림</h2>
<!-- 비회원 상태일 때 -->
<!-- 로그인하면 팝업광고가 보이지않음 -->
<c:if test="${empty sessionScope.userId }">
    <div id="hd_pops_13" class="hd_pops" style="top:0px;left:-205px">
        <div class="hd_pops_con" style="width:300px;height:413px">
            <a href=""></a><a href="${ctxpath}/product/productDetail.do?pro_no=1"><img src="${ctxpath }/resources/imgs/pop1.png" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /></a>        </div>
        <div class="hd_pops_footer">
            <button class="hd_pops_reject hd_pops_13 24"><strong>24</strong>시간 동안 다시 열람하지 않습니다</button>
            <button class="hd_pops_close hd_pops_13">닫기</button>
        </div>
    </div>
</c:if>
<!--     <div id="hd_pops_1" class="hd_pops" style="top:0px;left:555px">
        <div class="hd_pops_con" style="width:300px;height:413px">
            <p><a href=""><img src="${ctxpath }/resources/imgs/pop3.png"  /></a><br style="clear:both;" /> </p><p><br style="clear:both;" /> </p><p> </p><a href=""> </a><p> </p><p><a href=""> </a></p><p><a href=""> </a></p><p><a href=""> </a></p><p><a href=> </a></p><p><a href=""> </a></p><p><a href=""> </a></p><p> </p>       </div>
        <div class="hd_pops_footer">
            <button class="hd_pops_reject hd_pops_1 24"><strong>24</strong>시간 동안 다시 열람하지 않습니다.</button>
            <button class="hd_pops_close hd_pops_1">닫기</button>
        </div>
    </div>

    <div id="hd_pops_11" class="hd_pops" style="top:0px;left:200px">
        <div class="hd_pops_con" style="width:300px;height:413px">
            <a href=""><img src="${ctxpath }/resources/imgs/pop2.png"  /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /></a>        </div>
        <div class="hd_pops_footer">
            <button class="hd_pops_reject hd_pops_11 24"><strong>24</strong>시간 동안 다시 열람하지 않습니다.</button>
            <button class="hd_pops_close hd_pops_11">닫기</button>
        </div>
    </div> -->

<!--     <div id="hd_pops_13" class="hd_pops" style="top:0px;left:-405px">
        <div class="hd_pops_con" style="width:300px;height:413px">
            <a href=""></a><a href=""><img src="${ctxpath }/resources/imgs/pop1.png" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /></a>        </div>
        <div class="hd_pops_footer">
            <button class="hd_pops_reject hd_pops_13 24"><strong>24</strong>시간 동안 다시 열람하지 않습니다.</button>
            <button class="hd_pops_close hd_pops_13">닫기</button>
        </div>
    </div> -->

<!--     <div id="hd_pops_16" class="hd_pops" style="top:0px;left:910px">
        <div class="hd_pops_con" style="width:360px;height:413px">
            <a href=""><img src="${ctxpath }/resources/imgs/pop4.png" /><br style="clear:both;" /><br style="clear:both;" /><br style="clear:both;" /> </a>       </div>
        <div class="hd_pops_footer">
            <button class="hd_pops_reject hd_pops_16 24"><strong>24</strong>시간 동안 다시 열람하지 않습니다.</button>
            <button class="hd_pops_close hd_pops_16">닫기</button>
        </div>
    </div> -->
</div>

<!--  팝업레이어 끝 -->



<!-- 메인 페이지 소개(이미지) 시작  -->
<div style="width:100%; clear:both; background-color:#e3e3e3; text-align:center; padding:50px 0px; display:inline-block;">
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><div align="center"><img src="${ctxpath }/resources/imgs/line_banner.jpg" width="1200" height="300" border="0"></div></td>
  </tr>
</table>
</div>
<!-- <div style="width:100%; clear:both; background-color:#e3e3e3; text-align:center; padding:50px 0px; display:inline-block;">
  <div style="width:1200px; margin:0px auto; text-align:center;">
    div style="margin-bottom:40px; text-align:center;"><img src="/images/main/main_video_title.jpg" /></div>
    <iframe width="640" height="360" src="https://www.youtube.com/embed/WjxawH-9b3Q?rel=0" frameborder="0" allowfullscreen></iframe
    <div style="float:left; width:590px; padding:0px 35px; border-right:1px solid #fff;">
    <div style="margin-bottom:40px; text-align:center;"><img src="/images/main/main_video_title.jpg" /></div>
    <iframe width="560" height="315" src="https://www.youtube.com/embed/WjxawH-9b3Q?rel=0" frameborder="0" allowfullscreen></iframe>
    </div>
    <div style="float:left; width:391px; padding:0px 40px;">
    <iframe width="450" height="391" scrolling="yes" frameborder="0" src="https://www.facebook.com/plugins/like_box.php?app_id=&channel=http%3A%2F%2Fstaticxx.facebook.com%2Fconnect%2Fxd_arbiter%2Fr%2FXBwzv5Yrm_1.js%3Fversion%3D42%23cb%3Dfa5df8876a4f94%26domain%3Dmegacoffee.me%26origin%3Dhttp%253A%252F%252Fmegacoffee.me%252Ffffa168dcb285%26relation%3Dparent.parent&color_scheme=light&container_width=450&header=false&height=391&href=https%3A%2F%2Fwww.facebook.com%2F2016megacoffee&locale=en_GB&sdk=joey&show_border=false&show_faces=false&stream=true&width=450"></iframe>
    </div>
  </div>
</div> -->
<table width="100%" height="1"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="EAE4DF"></td>
  </tr>
</table>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#FFD437"><div align="center"><img src="${ctxpath }/resources/imgs/01.jpg" width="1200" height="600"></div></td>
  </tr>
  <tr>
    <td bgcolor="#FFFFFF"><div align="center"><img src="${ctxpath }/resources/imgs/02.jpg" width="1200" height="600"></div></td>
  </tr>
  <tr>
    <td bgcolor="#E3E3E3"><div align="center"><img src="${ctxpath }/resources/imgs/03.jpg" width="1200" height="600"></div></td>
  </tr>
  <tr>
    <td bgcolor="#FFD437"><div align="center"><img src="${ctxpath }/resources/imgs/04.jpg" width="1200" height="600"></div></td>
  </tr>
</table>


<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#3B2518"><div align="center"><img src="${ctxpath }/resources/imgs/slide_interior.jpg" width="1200" height="700" border="0"></div></td>
  </tr>
</table>




<!-- 메인 페이지 소개(이미지) 끝  -->


<!-- 로그인 상태일 떄, 로그아웃, 회원정보 수정 -->
<%-- 
<c:if test="${!empty sessionScope.userId }">
  <table width="100%" border="1">
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
  </table>
</c:if> 
--%>
 
</body>
</html>