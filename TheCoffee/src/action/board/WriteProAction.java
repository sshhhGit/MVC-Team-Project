package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;
import mysqlboard.*;//DAO,DTO

//DAO메서드 호출 
public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		request.setCharacterEncoding("utf-8");
		BoardDTO dto=new BoardDTO();//객체생성
		
		//클라이언트에서 보내준 데이터를 받아서 dto에 저장
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setRef(Integer.parseInt(request.getParameter("ref")));
		dto.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		dto.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		
		dto.setWriter(request.getParameter("writer"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		dto.setPw(request.getParameter("pw"));
		
		dto.setIp(request.getRemoteAddr());//IP
		
		BoardDAO dao=BoardDAO.getInstance();//dao객에 얻기
		dao.insertBoard(dto);//dao메서드 호출 , dto를 넘긴다
		
		return "/board/writePro.jsp";//뷰리턴
	}//requestPro()-end

}//class-end
