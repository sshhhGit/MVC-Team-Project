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
		
		String current_user_id = request.getParameter("current_user_id"); // 현재 접속한 id
		String current_admin_id = request.getParameter("current_admin_id"); // 현재 접속한 관리자id
		
		int num = Integer.parseInt(request.getParameter("num"));
//		String pageNum = request.getParameter("pageNum");
		
		QnaDAO dao = QnaDAO.getInstance(); // 객체 얻기
		QnaDTO dto = dao.getQna(num); // dao 메소드 호출, dto를 받는다.
		int x = dao.compareWriter(dto.getNum(), current_user_id);
		int y = dao.compareAdmin(dto.getNum(), current_admin_id);
		
		// 뷰에서 사용할 속성 지정 
		request.setAttribute("x", x);
		request.setAttribute("y", y);
		request.setAttribute("num", new Integer(num));
		request.setAttribute("dto", dto);
		
		return "/qna/content.jsp"; // 뷰 리턴
	}
	
	
	
	
} // class-end
