package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.*;
import command.CommandAction;

public class LoginProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//main.jsp���� �Ѿ�� ������ �޴´�
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDAO dao = MemberDAO.getInstance(); //dao��ü���
		int check = dao.loginCheck(id, pw);
		//check = 1 ��������
		//      = 0 ����ġ
		//      = -1 ����ID
		
		request.setAttribute("check", check);
		request.setAttribute("id", id);
		
		// TODO Auto-generated method stub
		return "/member/loginPro.jsp"; //�� ����
	}//requestPro()-end

}//class-end
