<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%--top.jsp --%>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<html>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		bgcolor="#363636">
		<tbody>
			<tr>
				<td style="padding: 8px, 0px;">
					<table width="1200" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tbody>
							<tr>
								<td width="100" rowspan="2"><a
									href="${ctxpath }/member/main.do"><img
										src="../imgs/top_logo3.png" border="0" title=""></a></td>
								<!-- <td height="35" colspan="6" align="right">&nbsp;&nbsp;<a href="/bbs/login.php">로그인</a></td> -->
							</tr>
							<tr>
								<td width="145">
									<div align="center">
										<a href="${ctxpath }/product/list.do""><img src="../imgs/top_product_button.png"></a>
									</div>
								</td>
								<td width="120">
									<div align="center">
										<a href="${ctxpath }/board/list.do"><img src="../imgs/top_board_button.png"></a>
									</div>
								</td>
								<td width="145">
									<div align="center">
										<a href="${ctxpath }/notice/list.do"><img src="../imgs/top_notice_button.png"></a>
									</div>
								</td>
								<td width="120">
									<div align="center">
										<a href="${ctxpath }/qna/list.do"><img src="../imgs/top_qna_button.png"></a>
									</div>
								</td>
								<td width="120">
									<div align="center">
										<a href="${ctxpath }/faq/list.do"><img src="../imgs/top_faq_button.png"></a>
									</div>
								</td>
								<td valign="top">
									<table width="149" border="0" cellspacing="0" cellpadding="0">
										<%--     <tbody><tr>
          <td><div align="right"><a href="${ctxpath }/member/loginForm.do">로그인</a>&nbsp;<a href="${ctxpath }/member/inputForm.do">회원가입</a></div></td>
        </tr>
    </tbody> --%>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>