package action.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class WriteFormAction2 implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		int num = 0;
		
		try {
			if (request.getParameter("num") != null) { 
				num = Integer.parseInt(request.getParameter("num"));
			}
		} catch (Exception e) {
			System.out.println("WriteFormAction2 Ŭ�������� ���� �߻� : " + e);
		}
		
		// �信�� ����� �Ӽ� ����
		request.setAttribute("num", num);
		
		return "/qna/writeForm2.jsp"; // �� ����
	}//requestPro()-end
	
	

}//class-end
