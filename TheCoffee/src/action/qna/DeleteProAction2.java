package action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.QnaDAO;
import command.CommandAction;
import mysqlboard.*; // DAO, DTO

public class DeleteProAction2 implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
//		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		String admin_id = request.getParameter("admin_id");
		String pw = request.getParameter("pw");
		
		System.out.println("deletePro�׼�2| num : " + num);
		System.out.println("deletePro�׼�2| admin_id : " + admin_id);
		System.out.println("deletePro�׼�2| pw : " + pw);
		
		
		QnaDAO dao = QnaDAO.getInstance(); // dao ��ü ���
		int check = dao.deleteQna_A(num, admin_id, pw); // dao �޼ҵ� ȣ��, ������ �ޱ�
		// x==1 ���������� ����, x==0 ��ȣƲ�� ��������
		
		// �信�� ����� �Ӽ� ����
//		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		
		
		
		return "/qna/deletePro2.jsp";
	} // requestPro()-end

} // class-end
