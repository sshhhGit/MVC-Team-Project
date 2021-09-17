package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import mysqlboard.*;//DAO,DTO

public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String pageNum=request.getParameter("pageNum");
		
		BoardDTO dto=new BoardDTO();//객체생성
		
		//클라이언트가 보내준 데이터를 dto에 저장
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setWriter(request.getParameter("writer"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		dto.setPw(request.getParameter("pw"));
		
		BoardDAO dao=BoardDAO.getInstance();//dao객체얻기
		int x=dao.updateBoard(dto);
		//x=1 정상적으로 수정
		//x=0 암호틀림
		
		request.setAttribute("check", x);
		request.setAttribute("pageNum", pageNum);
		
		return "/board/updatePro.jsp";//뷰리턴
	}//requestPro()-end

}//class-end
