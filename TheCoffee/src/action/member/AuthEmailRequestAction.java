package action.member;

import java.util.Properties;
import java.util.Random;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import common.ShareVar_login;

public class AuthEmailRequestAction implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		String inputedEmail = request.getParameter("email");
		
		//인증코드 생성
		String AuthenticationKey = "12345";
		
		// mail server 설정
		String host = "smtp.gmail.com";
		String user = ShareVar_login.hostID; // 자신의 구글 계정
		String password = ShareVar_login.hostPW;// 자신의 구글 패스워드
		
		// 메일 받을 주소
		String to_email = inputedEmail;
		System.out.println("inputedEmail : " + inputedEmail);

		// SMTP 서버 정보를 설정한다.
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", host);
		//google - TLS : 587, SSL: 465
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.debug", "true");
        
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		MimeMessage msg = new MimeMessage(session);
		
		// email 전송
		try {
			msg.setFrom(new InternetAddress(user));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));

			// 메일 제목
			msg.setSubject("인증메일입니다.", "UTF-8");
			// 메일 내용
			msg.setText("인증 번호 :" + AuthenticationKey );

			Transport.send(msg);
			System.out.println("이메일 전송 : " + AuthenticationKey);
			ShareVar_login sv = ShareVar_login.getInstance();
			sv.authEamilCode = AuthenticationKey;

		} catch (AddressException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} catch (MessagingException e) { 
				// TODO Auto-generated catch block 
				e.printStackTrace(); 
		}
		
		
		return "/member/requestAuthEmail.jsp";
	}
	
	

}
