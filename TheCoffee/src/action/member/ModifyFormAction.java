package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;
import command.CommandAction;

public class ModifyFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = dao.getMember(id);
		
		String email = dto.getEmail();
		//int idx = im_email.indexOf("@"); //@의 위치
		
		//String email = im_email.substring(0, idx);
		//String email2 = im_email.substring(idx);
		
		String im_tel = dto.getTel();
		
		String tel = im_tel.substring(0, 3);
		String tel2 = im_tel.substring(3, 7);
		String tel3 = im_tel.substring(7);
		
		request.setAttribute("email", email);
		//request.setAttribute("email2", email2);
		request.setAttribute("tel", tel);
		request.setAttribute("tel2", tel2);
		request.setAttribute("tel3", tel3);
		
		request.setAttribute("dto", dto);
		request.setAttribute("id", id);
		
		return "/member/modifyForm.jsp"; //뷰 리턴
	}
	
	
}
