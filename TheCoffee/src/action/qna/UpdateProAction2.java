package action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.QnaDAO;
import qna.QnaDTO;
import command.CommandAction;
import mysqlboard.*; // DAO,DTO

public class UpdateProAction2 implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));
//		String admin_id = request.getParameter("admin_id");
//		String pageNum = request.getParameter("pageNum");
		
		QnaDTO dto = new QnaDTO(); // ��ü ����
		
		// Ŭ���̾�Ʈ�� ������ �����͸� dto�� �����Ѵ�. 
		dto.setNum(num);
		dto.setAdmin_id(request.getParameter("admin_id"));
		dto.setAdmin_content(request.getParameter("admin_content"));
		
		QnaDAO dao = QnaDAO.getInstance(); // dao ��ü���
		int x = dao.updateQna_A(dto);
		// x = 1 ���� ����
		// x = -100 ���� ����
		
		request.setAttribute("check", x);
//		request.setAttribute("pageNum", pageNum);
		
		return "/qna/updatePro2.jsp"; // �� ����
	} // UpdateProAction()-end

} // class-end
