package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;
import mysqlboard.*;//DAO,DTO

public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		
		BoardDAO dao=BoardDAO.getInstance();//dao객체얻기
		BoardDTO dto=dao.getBoard(num);//dao메서드 호출 ,dto를 받는다
		
		//뷰에서 사요할 속성 지정
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", pageNum);
		
		request.setAttribute("dto", dto);
		
		int ref=dto.getRef();
		int re_step=dto.getRe_step();
		int re_level=dto.getRe_level();
		
		request.setAttribute("ref", ref);
		request.setAttribute("re_step", re_step);
		request.setAttribute("re_level", re_level);
		
		return "/board/content.jsp";//뷰 리턴
	}

	
}//class-end
