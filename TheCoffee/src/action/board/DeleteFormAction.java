package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
public class DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		String pageNum=request.getParameter("pageNum");
		int num=Integer.parseInt(request.getParameter("num"));
		
		//뷰에서 사용할 속성 지정
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", new Integer(num));
		
		return "/board/deleteForm.jsp";//뷰 리턴
	}//requestPro()-end

}//class-end
