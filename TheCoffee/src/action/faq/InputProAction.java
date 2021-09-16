package action.faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import faq.*;

public class InputProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		//�ѱ�ó��
		request.setCharacterEncoding("UTF-8");
		
		//��ü����
		FaqDTO dto = new FaqDTO(); 
		
		//Ŭ���̾�Ʈ���� ������ �����͸� �޾Ƽ� dto�� ����
		dto.setWriter(request.getParameter("writer"));
		dto.setSubject(request.getParameter("subject"));
		dto.setCategory(request.getParameter("category"));
		dto.setContent(request.getParameter("content"));
		dto.setIp(request.getRemoteAddr());
		
		//insert ���� ����
		FaqDAO dao = FaqDAO.getInstance();
		dao.insertFaq(dto);
		
		return "/faq/inputPro.jsp";
	}//requestPro()-end

}//class-end
