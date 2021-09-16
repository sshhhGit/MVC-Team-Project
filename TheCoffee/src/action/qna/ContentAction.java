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
		
		int num = Integer.parseInt(request.getParameter("num"));
		int admin_null_check = 1;
//		String pageNum = request.getParameter("pageNum");
		
		QnaDAO dao = QnaDAO.getInstance(); // ��ü ���
		QnaDTO dto = dao.getQna(num); // dao �޼ҵ� ȣ��, dto�� �޴´�.
		
		// �信�� ����� �Ӽ� ���� 
		request.setAttribute("num", new Integer(num));
		request.setAttribute("dto", dto);
		
		if (dto.getAdmin_id() == null || dto.getAdmin_content() == null || dto.getAdmin_regdate() == null) {
			admin_null_check = 0;
		}
		System.out.println("admin_null_check : " + admin_null_check);
		
		return "/qna/content.jsp"; // �� ����
	}
	
	
	
	
} // class-end
