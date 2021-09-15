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
		
		//�����ڵ� ����
		String AuthenticationKey = "12345";
		
		// mail server ����
		String host = "smtp.gmail.com";
		String user = ShareVar_login.hostID; // �ڽ��� ���� ����
		String password = ShareVar_login.hostPW;// �ڽ��� ���� �н�����
		
		// ���� ���� �ּ�
		String to_email = inputedEmail;
		System.out.println("inputedEmail : " + inputedEmail);

		// SMTP ���� ������ �����Ѵ�.
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
		
		// email ����
		try {
			msg.setFrom(new InternetAddress(user));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));

			// ���� ����
			msg.setSubject("���������Դϴ�.", "UTF-8");
			// ���� ����
			msg.setText("���� ��ȣ :" + AuthenticationKey );

			Transport.send(msg);
			System.out.println("�̸��� ���� : " + AuthenticationKey);
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
