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
		
		//�信�� ����� �Ӽ� ����
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", new Integer(num));
		
		return "/board/deleteForm.jsp";//�� ����
	}//requestPro()-end

}//class-end
