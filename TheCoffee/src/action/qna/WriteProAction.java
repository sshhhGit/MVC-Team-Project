package action.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.QnaDAO;
import qna.QnaDTO;
import command.CommandAction;
import mysqlboard.*; // DAO,DTO

// DAO 메소드 호출
public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		QnaDTO dto = new QnaDTO(); // 객체 생성
		
		// 클라이언트에서 보내준 데이터를 받아서 dto에 저장
		dto.setUser_id(request.getParameter("user_id"));
		dto.setUser_content(request.getParameter("user_content"));
		
		QnaDAO dao = QnaDAO.getInstance(); // dao객체 얻기
		dao.insertQna_Q(dto); // dao메소드 호출, dto를 넘긴다.
		
		return "/qna/writePro.jsp";
	} //requestPro()-end

} //class-end
