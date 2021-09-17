package action.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.QnaDAO;
import qna.QnaDTO;
import command.CommandAction;
import mysqlboard.*; // DAO,DTO

// DAO �޼ҵ� ȣ��
public class WriteProAction2 implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		QnaDTO dto = new QnaDTO(); // ��ü ����
		
		// Ŭ���̾�Ʈ���� ������ �����͸� �޾Ƽ� dto�� ����
		dto.setAdmin_id(request.getParameter("admin_id"));
		dto.setAdmin_content(request.getParameter("admin_content"));
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		
		QnaDAO dao = QnaDAO.getInstance(); // dao��ü ���
		dao.insertQna_A(dto); // dao�޼ҵ� ȣ��, dto�� �ѱ��.
		
		return "/qna/writePro2.jsp";
	} //requestPro()-end

} //class-end
