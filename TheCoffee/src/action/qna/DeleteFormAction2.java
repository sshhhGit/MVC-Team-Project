package action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class DeleteFormAction2 implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
//		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		String admin_id = request.getParameter("admin_id");
		
		System.out.println("deleteForm2�׼�| num : " + num);
		System.out.println("deleteForm2�׼�| admin_id : " + admin_id);
		
		// �信�� ����� �Ӽ� ����
//		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", new Integer(num));
		request.setAttribute("admin_id", admin_id);
		
		
		return "/qna/deleteForm2.jsp"; // �� ����
	} // requestPro()-end

} // class-end
