package action.faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import faq.*;

public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		FaqDAO dao = FaqDAO.getInstance();
		FaqDTO dto = dao.getFaq(num);
		
		request.setAttribute("dto", dto);
		request.setAttribute("num", new Integer(num));
		
		return "/faq/updateForm.jsp";
	}

}
