package action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.QnaDAO;
import qna.QnaDTO;
import command.CommandAction;
import mysqlboard.*;

public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println("num : " + num);
//		String pageNum = request.getParameter("pageNum");
		
		QnaDAO dao = QnaDAO.getInstance(); // dao 객체 얻기
		QnaDTO dto = dao.getQna(num); // dao 호출, dto 받는다. 
		
		request.setAttribute("dto", dto);
//		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", new Integer(num));
		
		
		return "/qna/updateForm.jsp"; // 뷰리턴
	} // requestPro()-end
	

}
