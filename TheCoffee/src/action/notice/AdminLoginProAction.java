package action.notice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
public class AdminLoginProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		String adminId = request.getParameter("adminId");
		String adminPw = request.getParameter("adminPw");
		
		request.setAttribute("adminId", adminId);
		request.setAttribute("adminPw", adminPw);
		return "/notice/adminLoginPro.jsp";
	}

}
