package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import command.CommandAction;

public class ConfirmEmailAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("email");
		MemberDAO dao = MemberDAO.getInstance();
				
		String result = dao.emailDupleCheck(email);
		
		request.setAttribute("emailDupleCheckResult", result);
		request.setAttribute("email", email);
		
		return "/member/confirmEmail.jsp";
	}
 
}
