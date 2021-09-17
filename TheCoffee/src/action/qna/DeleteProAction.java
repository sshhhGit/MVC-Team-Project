package action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.QnaDAO;
import command.CommandAction;
import mysqlboard.*; // DAO, DTO

public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
//		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		String user_id = request.getParameter("user_id");
		String pw = request.getParameter("pw");
		
		System.out.println("deletePro�׼�| num : " + num);
		System.out.println("deletePro�׼�| user_id : " + user_id);
		System.out.println("deletePro�׼�| pw : " + pw);
		
		
		QnaDAO dao = QnaDAO.getInstance(); // dao ��ü ���
		int check = dao.deleteQna_Q(num, user_id, pw); // dao �޼ҵ� ȣ��, ������ �ޱ�
		// x==1 ���������� ����, x==0 ��ȣƲ�� ��������
		
		// �信�� ����� �Ӽ� ����
//		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		
		
		
		return "/qna/deletePro.jsp";
	} // requestPro()-end

} // class-end
