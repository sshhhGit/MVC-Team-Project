package action.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.QnaDAO;
import qna.QnaDTO;
import command.CommandAction;
import mysqlboard.*; // DAO,DTO 


public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		String current_user_id = request.getParameter("current_user_id"); // ���� ������ id
		String current_admin_id = request.getParameter("current_admin_id"); // ���� ������ ������id
		
		int num = Integer.parseInt(request.getParameter("num"));
//		String pageNum = request.getParameter("pageNum");
		
		QnaDAO dao = QnaDAO.getInstance(); // ��ü ���
		QnaDTO dto = dao.getQna(num); // dao �޼ҵ� ȣ��, dto�� �޴´�.
		int x = dao.compareWriter(dto.getNum(), current_user_id);
		int y = dao.compareAdmin(dto.getNum(), current_admin_id);
		
		// �信�� ����� �Ӽ� ���� 
		request.setAttribute("x", x);
		request.setAttribute("y", y);
		request.setAttribute("num", new Integer(num));
		request.setAttribute("dto", dto);
		
		return "/qna/content.jsp"; // �� ����
	}
	
	
	
	
} // class-end
