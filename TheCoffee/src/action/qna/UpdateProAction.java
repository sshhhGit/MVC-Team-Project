package action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.QnaDAO;
import qna.QnaDTO;
import command.CommandAction;
import mysqlboard.*; // DAO,DTO

public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String user_id = request.getParameter("user_id");
//		String pageNum = request.getParameter("pageNum");
		
		QnaDTO dto = new QnaDTO(); // ��ü ����
		
		// Ŭ���̾�Ʈ�� ������ �����͸� dto�� �����Ѵ�. 
		dto.setNum(num);
		dto.setUser_id(request.getParameter("user_id"));
		dto.setUser_content(request.getParameter("user_content"));
		
		QnaDAO dao = QnaDAO.getInstance(); // dao ��ü���
		int x = dao.updateQna(dto);
		// x = 1 ���� ����
		// x = -100 ���� ����
		
		request.setAttribute("check", x);
//		request.setAttribute("pageNum", pageNum);
		
		return "/qna/updatePro.jsp"; // �� ����
	} // UpdateProAction()-end

} // class-end
