package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import mysqlboard.*;//DAO DTO

public class UpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		
		BoardDAO dao=BoardDAO.getInstance();//dao객체얻기
		BoardDTO dto=dao.getUpdate(num);//dao호출,dto받는다
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", new Integer(num));
		//request.setAttribute(key, value);
		//request.setAttribute("문자열", 객체);
		
 		return "/board/updateForm.jsp";//뷰리턴
		
	}//requestPro()-end

}//class-end
