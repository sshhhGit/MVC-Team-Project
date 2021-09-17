package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import mysqlboard.*;//DAO,DTO

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		
		String pageNum=request.getParameter("pageNum");
		int num=Integer.parseInt(request.getParameter("num"));
		String pw=request.getParameter("pw");
		
		BoardDAO dao=BoardDAO.getInstance();//dao��ü ��� 
		int check=dao.deleteBoard(num, pw);//dao�޼��� ȣ�� ,���� �޴´�
		//x=1 ���������� ���� 
		//x=0 //���� ���� ��ȣ Ʋ�� 
		
		//�信�� ����� �Ӽ� ����
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
	
		return "/board/deletePro.jsp";
	}//requestPro()-end

}//class-end
