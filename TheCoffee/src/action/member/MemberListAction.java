package action.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.*;
import command.CommandAction;

public class MemberListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		MemberDAO dao = MemberDAO.getInstance();
		ArrayList<MemberDTO> list = dao.getMemberList();
		
		request.setAttribute("list", list);
		
		return "/member/memberList.jsp";
	}

	
	
}//class-end
