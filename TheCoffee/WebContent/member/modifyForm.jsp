<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
  <script>
	function openDaumPostcode(){
		 
		new daum.Postcode({
			oncomplete:function(data){
				document.getElementById('zipcode').value=data.zonecode;
				document.getElementById('addr').value=data.address;
	 		}
		}).open();
	}//openDaumPostcode()---
  </script>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="script.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
<script type="text/javascript">
function modifyCheck() {
	var ff = eval("document.modifyForm");
	
	if(ff.pw.value==''){
		alert("암호를 입력하세요");
		ff.pw.focus();
		return false;
	}
	if(ff.name.value==''){
		alert("이름을 입력하세요");
		ff.name.focus();
		return false;
	}
	if(ff.email.value==''){
		alert("이메일 입력하세요");
		ff.email.focus();
		return false;
	}
	if(ff.tel.value==''){
		alert("전화번호를 입력하세요");
		ff.tel.focus();
		return false;
	}
	if(ff.zipcode.value==''){
		alert("주소를 입력하세요");
		ff.zipcode.focus();
		return false;
	}
	if(ff.addr2.value==''){
		alert("상세주소를 입력하세요");
		ff.addr2.focus();
		return false;
	}
	return true;
}
</script>
</head>
<body>
<h2>개인정보 변경</h2>
<form action="${ctxpath}/member/modifyPro.do" name="modifyForm" method="post" onsubmit="return modifyCheck()">
  <table border="1">
    <tr>
      <td>ID</td>
      <td>
        ${dto.id }
        <input type="hidden" name="id" value="${sessionScope.userId }">
      </td>
    </tr>
    <tr>
      <td>암호</td>
      <td>
        <input type="password" name="pw" id="pw" size="15">
      </td>
    </tr>
    <tr>
      <td>이름</td>
      <td>
        <input type="text" name="name" size="15" value="${dto.name }">
      </td>
    </tr>
    <tr>
      <td>이메일</td>
      <td>
        <input type="text" name="email" size="15" value="${email }">
      </td>
    </tr>
    <tr>
      <td>전화</td>
      <td>
        <select name="tel">
          <option value="${tel }">${tel }</option>
          <option value="010">010</option>
          <option value="018">018</option>
          <option value="017">017</option>
        </select>
         -
        <input type="text" name="tel2" value="${tel2 }"> -
        <input type="text" name="tel3" value="${tel3 }">
      </td>
    </tr>
    <tr>
      <td>우편번호</td>
      <td>
        <input type="text" name="zipcode" id="zipcode" size="7" value="${dto.zipcode }">
        <input type="button" value="주소검색" onclick="openDaumPostcode()">
      </td>
    </tr>
    <tr>
      <td>주소</td>
      <td>
        <input type="text" name="addr" id="addr" value="${dto.addr }" size="60" readonly="readonly">
        <br>
        상세주소 : <input type="text" name="addr2" id="addr2" value="${dto.addr2 }" size="20">
      </td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <input type="submit" value="변경하기">
        <input type="button" value="취소" onclick="location='${ctxpath}/member/main.do'">
      </td>
    </tr>
  </table>
</form>
</body>
</html>