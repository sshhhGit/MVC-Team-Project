package action.faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import faq.*;

public class InputProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		//객체생성
		FaqDTO dto = new FaqDTO(); 
		
		//클라이언트에서 보내준 데이터를 받아서 dto에 저장
		dto.setWriter(request.getParameter("writer"));
		dto.setSubject(request.getParameter("subject"));
		dto.setCategory(request.getParameter("category"));
		dto.setContent(request.getParameter("content"));
		dto.setIp(request.getRemoteAddr());
		
		//insert 쿼리 실행
		FaqDAO dao = FaqDAO.getInstance();
		dao.insertFaq(dto);
		
		return "/faq/inputPro.jsp";
	}//requestPro()-end

}//class-end
