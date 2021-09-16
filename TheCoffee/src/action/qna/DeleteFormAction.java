package action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class DeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
//		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		String user_id = request.getParameter("user_id");
		
		System.out.println("deleteForm�׼�| num : " + num);
		System.out.println("deleteForm�׼�| user_id : " + user_id);
		
		// �信�� ����� �Ӽ� ����
//		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", new Integer(num));
		request.setAttribute("user_id", user_id);
		
		
		return "/qna/deleteForm.jsp"; // �� ����
	} // requestPro()-end

} // class-end
