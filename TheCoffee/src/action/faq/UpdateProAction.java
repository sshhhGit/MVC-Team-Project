package action.faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import faq.*;

public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		FaqDTO dto = new FaqDTO();
		
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setWriter(request.getParameter("writer"));
		dto.setCategory(request.getParameter("category"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		
		FaqDAO dao = FaqDAO.getInstance();
		dao.updateFaq(dto);
		
		request.setAttribute("num", request.getParameter("num"));
		return "/faq/updatePro.jsp";
	}

}
