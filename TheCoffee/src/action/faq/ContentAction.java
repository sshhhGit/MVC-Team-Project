package action.faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import faq.*;

public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		FaqDAO dao = FaqDAO.getInstance();
		FaqDTO dto = dao.getFaq(num);
		
		//뷰에서 사용할 속성 지정
		request.setAttribute("num", new Integer(num));
		request.setAttribute("dto", dto);
		
		return "/faq/content.jsp";
	}

}
