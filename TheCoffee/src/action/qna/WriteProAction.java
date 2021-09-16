package action.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.QnaDAO;
import qna.QnaDTO;
import command.CommandAction;
import mysqlboard.*; // DAO,DTO

// DAO �޼ҵ� ȣ��
public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		QnaDTO dto = new QnaDTO(); // ��ü ����
		
		// Ŭ���̾�Ʈ���� ������ �����͸� �޾Ƽ� dto�� ����
		dto.setUser_id(request.getParameter("user_id"));
		dto.setUser_content(request.getParameter("user_content"));
		
		QnaDAO dao = QnaDAO.getInstance(); // dao��ü ���
		dao.insertQna_Q(dto); // dao�޼ҵ� ȣ��, dto�� �ѱ��.
		
		return "/qna/writePro.jsp";
	} //requestPro()-end

} //class-end
