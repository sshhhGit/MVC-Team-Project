<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../module/jsp-header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../resources/css/style.css">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${ctxpath }/resources/js/script.js"></script>
</head>
<body>
<h2><center>회원가입</center></h2>
<table border="1" id="member_insert_t">
<tr>
<td>
		<form name="signUpForm" action="${ctxpath }/member/insertPro.do" method="post">
		<table>
			<tr>
				<td border="1">아이디</td>
				<td>
				<input type="text" name="id" id="id"  size="26">
				<input onclick="idDupleCheck()" type="button" value="아이디 중복 확인">
				<input type="hidden" name="idDupleChecked" id="idDupleChecked" value="false">
				</td>
			</tr>
			<tr>
				<td rowspan="2">비밀번호</td>
				<td rowspan="2"> 
				<input type="password" name="pw" id="inputPwForm1" size="26">
				<br>
				<input type="password" name="pw2" id="inputPwForm2"  size="26">
				<button onclick="pwEqualCheck()" type="button" class="btnChk">비밀번호  확인</button>
				<input type="hidden" name="pwCheck" id="pwCheck" value="false"></td>
			</tr>			
			<tr>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" size="26"></td>
			</tr>
			<tr>
				<td>연락처</td>
				<td>
					<select name="tel">
						<option value="010" selected="selected"> 010
						<option value="011"> 011
						<option value="018"> 018
					</select>
					 - <input type="text" name="tel2" size="4" maxlength="4">
					 - <input type="text" name="tel3" size="4" maxlength="4">
				</td>
			</tr>
			<tr>
				<td	>이메일</td>
				<td><input type="text" name="email" id="email"  size="26">
				<!-- <button onclick="emailAuthentication()" id="eamilAuthBtn" type="button" class="btnChk">인증 메일 보내기</button></td> -->
			</tr>
<!-- 			<tr>
				<td colspan="2"><input type="text" name="authCode" id="inputAuthCode"  maxlength="10" disabled="disabled">
				<button onclick="authCodeCheck()" id="authCodeCheckBtn" type="button" disabled="disabled" class="btnChk">인증</button>
				<input type="hidden" name="authPass" id="authPass" value="false">
				</td>
				<td><input type="hidden" name="authPass" id="authPass" value="false"></td>
			</tr>		
			<tr>
				<td colspan="2"><font size="2" style="color: red;">* 이메일이 도착하는데 1~2분정도 소요될 수 있습니다.</font><br></td>
			</tr> -->
			<tr>
		        <td>우편번호</td>
		        <td>
		          <input type="text" name="zipcode" id="zipcode" size="26">
		          <input type="button" value="주소검색" onclick="openDaumPostcode()">
		        </td>
		      </tr>
		      <tr>
		        <td>주소</td>
		        <td>
		          <input type="text" name="addr" id="addr" size="26" readonly="readonly">
		        </td>
		      </tr>
		      <tr>
		        <td>상세주소</td>
		        <td>
		          <input type="text" name="addr2" id="addr2" size="26">
		        </td>
		      </tr>		
				<tr>
		        <td colspan="2" align="center">
		          <input type="button" value="회원가입" onclick="validationCheck()">
		          <!-- <input type="submit" value="회원가입"> -->
		          <input type="reset" value="다시입력">
		          <input type="button" value="돌아가기" onclick="location='${ctxpath}/common/main.do'">
		        </td>
		      </tr>
		      </table>
		</form>
		</td>
		</tr>
		</table>
</body>
</html>