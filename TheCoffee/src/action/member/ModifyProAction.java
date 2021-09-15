package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import member.*; //DTO, DAO

public class ModifyProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		
		//Ŭ���̾�Ʈ�� ������ �����͸� �޾Ƽ� DTO�� ����
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		
		String email = request.getParameter("email")+request.getParameter("eamil2");
		dto.setEmail(email);
		
		String tel = request.getParameter("tel")+request.getParameter("tel2")+request.getParameter("tel3");
		dto.setTel(tel);
		
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddr(request.getParameter("addr"));
		dto.setAddr2(request.getParameter("addr2"));
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.updateMember(dto); //dao�޼� ȣ��, dto�� �Ѱ��ش�
		
		request.setAttribute("id", id);
		
		return "/member/modifyPro.jsp";
	}
	
	
}
