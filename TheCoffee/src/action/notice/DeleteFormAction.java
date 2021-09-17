package action.notice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		String pageNo = request.getParameter("pageNo");
		int notice_no = Integer.parseInt(request.getParameter("notice_no"));
		
		//�信�� ����� �Ӽ�����
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("notice_no", new Integer(notice_no));
		
		return "/notice/deleteForm.jsp";
	}

}
