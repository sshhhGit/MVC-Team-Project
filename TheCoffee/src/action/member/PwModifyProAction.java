package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import member.*; //DTO, DAO

public class PwModifyProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String oldPw = request.getParameter("oldPw");
		String newPw = request.getParameter("newPw");
		
		//클라이언트가 보내준 데이터를 받아서 DTO에 저장
		MemberDTO dto = new MemberDTO();
		
		dto.setId(id);
		dto.setPw(newPw);
		
		MemberDAO dao = MemberDAO.getInstance();
		
		//dao.updatePassword(newPw, id, oldPw); //dao메서 호출, dto를 넘겨준다
		
		int check = dao.updatePassword(newPw, id, oldPw);
		
		request.setAttribute("check", check);
		
		return "/member/pwModifyPro.jsp";
	}
	
	
}
