package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import mysqlboard.*;//DAO,DTO

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		
		String pageNum=request.getParameter("pageNum");
		int num=Integer.parseInt(request.getParameter("num"));
		String pw=request.getParameter("pw");
		
		BoardDAO dao=BoardDAO.getInstance();//dao객체 얻기 
		int check=dao.deleteBoard(num, pw);//dao메서드 호출 ,정수 받는다
		//x=1 정상적으로 삭제 
		//x=0 //삭제 실패 암호 틀림 
		
		//뷰에서 사용할 속성 지정
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
	
		return "/board/deletePro.jsp";
	}//requestPro()-end

}//class-end
