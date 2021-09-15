package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;
import command.CommandAction;

public class InputProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		
		//inputForm.jsp에서 보내준 데이터를 dto에 저장
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		
		//이메일
		String email = request.getParameter("email");
		dto.setEmail(email);
		
		//전화
		String tel = request.getParameter("tel");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		
		tel = tel+tel2+tel3;
		dto.setTel(tel);
		
		//주소
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddr(request.getParameter("addr"));
		dto.setAddr2(request.getParameter("addr2"));
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.insertMember(dto);
		
		request.setAttribute("id", id);
		
		return "/member/inputPro.jsp";
	}

	
}
